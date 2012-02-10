package cm.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.Collection;
import java.util.Hashtable;

import javax.swing.JOptionPane;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

import org.xml.sax.InputSource;

import com.sun.xml.internal.ws.api.streaming.XMLStreamReaderFactory;
import com.sun.xml.internal.ws.api.streaming.XMLStreamWriterFactory;

public class StatLibrary {
	private Hashtable<String, Stats> _library = new Hashtable<String, Stats>();
	
	/**
	 * Add a statblock to the library.
	 * @param stats the statblock
	 * @param overwrite if true, overwrite any existing statblock with the provided statblock's handle
	 * @return true on successful addition
	 */
	public Boolean add(Stats stats, Boolean overwrite) {
		if (!stats.isValid()) {
			return false;
		}
		
		if(haveKey(stats.getHandle())) {
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
	 * Add a statblock to the library. Calls {@link #add(Stats, Boolean)} with overwrite = false.
	 * @param stats the statblock
	 * @return true on successful addition
	 */
	public Boolean add(Stats stats) {
		return add(stats, false);
	}

	/**
	 * Adds an entry to the library, overwriting a previous entry with the same handle.
	 * Stats are only added if they are valid.
	 * @param handle the statblock's handle
	 * @param stats the statblock
	 */
	private void add(String handle, Stats stats) {
		if (stats.isValid()) {
			getLibrary().put(handle, stats);
		}
	}

	/**
	 * Returns the statblock library hashtable.
	 * @return the library
	 */
	private Hashtable<String, Stats> getLibrary() {
		return _library;
	}

	/**
	 * Checks for handle in the library.
	 * @param handle the handle to search
	 * @return true if the handle exists in the library
	 */
	private Boolean haveKey(String handle) {
		return getLibrary().containsKey(handle);
	}
	
	/**
	 * Removes a statblock from the library.
	 * @param handle the statblock's handle
	 * @return true if the entry had existed and was removed
	 */
	public Boolean remove(String handle) {
		if (haveKey(handle)) {
			getLibrary().remove(handle);
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Returns the statblock identified by the handle in the library.
	 * @param handle the statblock's handle
	 * @return the statblock
	 */
	public Stats get(String handle) {
		return getLibrary().get(handle);
	}
	
	/**
	 * Returns the statblock with the given numeric index.
	 * @param index the index
	 * @returnthe statblock
	 */
	private Stats get(Integer index) {
		return getLibrary().values().toArray(new Stats[0])[index];
	}
	
	/**
	 * Deletes all entries from the stat library.
	 */
	private void clear() {
		getLibrary().clear();
	}
	
	/**
	 * Indicates if a given handle is present in the library.
	 * @param handle the handle
	 * @return true, if the handle is present in the library
	 */
	public Boolean contains(String handle) {
		return getLibrary().containsKey(handle);
	}
	
	/**
	 * Returns the values from the library.
	 * @return 
	 * @return the values
	 */
	public Collection<Stats> values() {
		return getLibrary().values();
	}

	/**
	 * Writes the statblock library to an XML stream.
	 * @param writer the XML stream
	 * @throws XMLStreamException from the writer
	 */
	private void exportXML(XMLStreamWriter writer) throws XMLStreamException {
		writer.writeStartElement("statblocklibrary");
		
		for(Stats stat : getLibrary().values()) {
			stat.exportXML(writer);
		}
		
		writer.writeEndElement();
	}
	
	/**
	 * Loads a statlibrary from an XML stream.
	 * @param reader the XML stream
	 * @return true on success
	 * @throws XMLStreamException from the reader
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
	 * @param filename the location of the XML file
	 * @param clearBeforeLoading if true, clears the existing library before loading
	 * @return true on success
	 */
	public Boolean loadFromFile(String filename, Boolean clearBeforeLoading) {
		File libraryBackup = new File(filename + ".bak");
		
		if (libraryBackup.exists()) {
			// may be a problem if this happens
			JOptionPane.showMessageDialog(null, "Previous library backup found. It is possible a previous " +
					"session failed. Backup has been copied to prevent data loss.", "Backup Found",
					JOptionPane.ERROR_MESSAGE);
			try {
				FileChannel src = new FileInputStream(libraryBackup).getChannel();
				FileChannel dst = new FileOutputStream(new File(filename + ".saved.bak")).getChannel();
				dst.transferFrom(src, 0, src.size());
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
				FileChannel src = new FileInputStream(library).getChannel();
				FileChannel dst = new FileOutputStream(new File(filename + ".bak")).getChannel();
				dst.transferFrom(src, 0, src.size());
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
				// this shouldn't happen, should it? We checked for file existence above with library.exists()
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
	 * @param filename the location of the XML file
	 * @return true on success
	 */
	public Boolean saveToFile(String filename) {
		String tempFilename = filename + ".tmp";
		File tempFile = new File(tempFilename);
		if (tempFile.exists()) {
			tempFile.delete();
		}
		
		try {
			XMLStreamWriter writer = XMLStreamWriterFactory.create(new FileOutputStream(tempFile));
			exportXML(writer);
			
			FileChannel src = new FileInputStream(tempFile).getChannel();
			FileChannel dst = new FileOutputStream(new File(filename)).getChannel();
			
			dst.transferFrom(src, 0, src.size());
			tempFile.delete();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (XMLStreamException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		
		File backupFile = new File(filename + ".bak");
		if (backupFile.exists()) {
			backupFile.delete();
		}
		
		return true;
	}
}