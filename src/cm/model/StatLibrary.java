package cm.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.swing.JOptionPane;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

import org.xml.sax.InputSource;

import com.sun.xml.internal.ws.api.streaming.XMLStreamReaderFactory;
import com.sun.xml.internal.ws.api.streaming.XMLStreamWriterFactory;

/**
 * Defines a statblock library to store creature information.
 * 
 * @author Matthew Rinehart &lt;gomamon2k at yahoo.com&gt;
 * @since 1.0
 */
public class StatLibrary {
	/**
	 * A mapping of handles to their {@link Stats}.
	 */
	private SortedMap<String, Stats> _library = new TreeMap<String, Stats>();

	/**
	 * Add a statblock to the library.
	 * 
	 * @param stats
	 *            the statblock
	 * @param overwrite
	 *            if true, overwrite any existing statblock with the provided
	 *            statblock's handle
	 * @return true on successful addition
	 */
	public Boolean add(Stats stats, Boolean overwrite) {
		if (!stats.isValid()) {
			return false;
		}

		if (contains(stats.getHandle())) {
			if (overwrite) {
				add(stats.getHandle(), stats);
			} else {
				return false;
			}
		} else {
			add(stats.getHandle(), stats);
		}
		return true;
	}

	/**
	 * Add a statblock to the library. Calls {@link #add(Stats, Boolean)} with
	 * overwrite = false.
	 * 
	 * @param stats
	 *            the statblock
	 * @return true on successful addition
	 */
	public Boolean add(Stats stats) {
		return add(stats, false);
	}

	/**
	 * Adds an entry to the library, overwriting a previous entry with the same
	 * handle. Stats are only added if they are valid.
	 * 
	 * @param handle
	 *            the statblock's handle
	 * @param stats
	 *            the statblock
	 */
	private void add(String handle, Stats stats) {
		if (stats.isValid()) {
			getLibrary().put(handle, stats);
		}
	}

	/**
	 * Deletes all entries from the stat library.
	 */
	private void clear() {
		getLibrary().clear();
	}

	/**
	 * Indicates if a given handle is present in the library.
	 * 
	 * @param handle
	 *            the handle
	 * @return true, if the handle is present in the library
	 */
	public Boolean contains(String handle) {
		return getLibrary().containsKey(handle);
	}

	/**
	 * Returns the statblock identified by the handle in the library.
	 * 
	 * @param handle
	 *            the statblock's handle
	 * @return the statblock
	 */
	public Stats get(String handle) {
		return getLibrary().get(handle);
	}

	/**
	 * Returns the statblock library hashtable.
	 * 
	 * @return the library
	 */
	private SortedMap<String, Stats> getLibrary() {
		return _library;
	}

	/**
	 * Removes a statblock from the library.
	 * 
	 * @param handle
	 *            the statblock's handle
	 * @return true if the entry had existed and was removed
	 */
	public Boolean remove(String handle) {
		if (contains(handle)) {
			getLibrary().remove(handle);
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Returns the values from the library.
	 * 
	 * @return the values
	 */
	public Collection<Stats> values() {
		return getLibrary().values();
	}

	/**
	 * Writes the statblock library to an XML stream.
	 * 
	 * @param writer
	 *            the XML stream
	 * @throws XMLStreamException
	 *             from the writer
	 */
	private void exportXML(XMLStreamWriter writer) throws XMLStreamException {
		writer.writeStartElement("statblocklibrary");

		for (Stats stat : getLibrary().values()) {
			stat.exportXML(writer);
		}

		writer.writeEndElement();
	}

	/**
	 * Loads a statlibrary from an XML stream.
	 * 
	 * @param reader
	 *            the XML stream
	 * @return true on success
	 * @throws XMLStreamException
	 *             from the reader
	 */
	private Boolean importXML(XMLStreamReader reader) throws XMLStreamException {
		Stats newStat;

		if (reader.isStartElement() && reader.getName().toString().contentEquals("statblocklibrary")) {
			while (reader.hasNext()) {
				reader.next();
				if (reader.isStartElement()) {
					if (reader.getName().toString().contentEquals("statblock")) {
						newStat = new Stats();
						newStat.importXML(reader);
						add(newStat);
					}
				} else if (reader.isEndElement()) {
					if (reader.getName().toString().contentEquals("statblocklibrary")) {
						return true;
					}
				}
			}
		}
		return false;
	}

	/**
	 * Reads a statblock library from an XML file.
	 * 
	 * @param filename
	 *            the location of the XML file
	 * @param clearBeforeLoading
	 *            if true, clears the existing library before loading
	 * @return true on success
	 */
	public Boolean loadFromFile(String filename, Boolean clearBeforeLoading) {
		File libraryBackup = new File(filename + ".bak");

		if (libraryBackup.exists()) {
			// may be a problem if this happens
			JOptionPane.showMessageDialog(null, "Previous library backup found. It is possible a previous "
					+ "session failed. Backup has been copied to prevent data loss.", "Backup Found", JOptionPane.ERROR_MESSAGE);
			try {
				FileInputStream src = new FileInputStream(libraryBackup);
				FileOutputStream dst = new FileOutputStream(new File(filename + ".saved.bak"));
				dst.getChannel().transferFrom(src.getChannel(), 0, src.getChannel().size());
				src.close();
				dst.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				return false;
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
		}

		File library = new File(filename);

		if (library.exists()) {
			try {
				FileInputStream src = new FileInputStream(library);
				FileOutputStream dst = new FileOutputStream(new File(filename + ".bak"));
				dst.getChannel().transferFrom(src.getChannel(), 0, src.getChannel().size());
				src.close();
				dst.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				return false;
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}

			try {
				InputSource input = new InputSource(new FileInputStream(library));
				XMLStreamReader reader = XMLStreamReaderFactory.create(input, false);
				while (reader.hasNext() && !reader.isStartElement()) {
					reader.next();
				}
				if (clearBeforeLoading) {
					clear();
				}
				importXML(reader);
			} catch (FileNotFoundException e) {
				// this shouldn't happen, should it? We checked for file
				// existence above with library.exists()
				e.printStackTrace();
				return false;
			} catch (XMLStreamException e) {
				e.printStackTrace();
				return false;
			}
		} else {
			return false;
		}

		return true;
	}

	/**
	 * Saves the stat library to an XML file.
	 * 
	 * @param filename
	 *            the location of the XML file
	 * @return true on success
	 */
	public Boolean saveToFile(String filename) {
		FileInputStream src = null;
		FileOutputStream dst = null;
		File tempFile = null;
		try {
			tempFile = File.createTempFile(filename, ".tmp");

			XMLStreamWriter writer = XMLStreamWriterFactory.create(new FileOutputStream(tempFile));
			exportXML(writer);

			src = new FileInputStream(tempFile);
			dst = new FileOutputStream(new File(filename));

			dst.getChannel().transferFrom(src.getChannel(), 0, src.getChannel().size());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (XMLStreamException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				if (src != null) {
					src.close();
				}
				if (dst != null) {
					dst.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (tempFile != null) {
				tempFile.delete();
			}
		}

		File backupFile = new File(filename + ".bak");
		if (backupFile.exists()) {
			backupFile.delete();
		}

		return true;
	}
}