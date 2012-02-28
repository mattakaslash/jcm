package cm.util.music;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.util.Random;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;
import javazoom.jl.player.advanced.PlaybackEvent;
import javazoom.jl.player.advanced.PlaybackListener;

/**
 * Provides a music player.
 * 
 * @author Matthew Rinehart &lt;gomamon2k at yahoo.com&gt;
 * @since 2.0
 */
public class Player {
	private static Boolean COMPLETED = false;
	private static File DIR = null;
	private static File FILE = null;
	private static PlayerListener LISTENER = null;
	private static AdvancedPlayer ONCE = null;
	private static AdvancedPlayer PLAYER = null;
	private static Random RAND = new Random();
	private static Boolean STOPPED = false;

	/**
	 * Returns the File currently being played.
	 * @return the file
	 */
	public static File getFile() {
		return FILE;
	}

	/**
	 * Returns an indicator of if the one-off song has finished playing.
	 * @return true, if the song has finished playing
	 */
	public static boolean isCompletedOnce() {
		return COMPLETED;
	}

	/**
	 * Selects a random file from the current directory and plays it.
	 */
	public static void play() {
		if (STOPPED) {
			STOPPED = false;
			return;
		}
		
		if (DIR == null) {
			return;
		}
		
		File[] list = DIR.listFiles(new FilenameFilter() {
			
			@Override
			public boolean accept(File dir, String name) {
				return name.toLowerCase().endsWith(".mp3");
			}
		});
		
		if (list.length <= 0) {
			return;
		}
		
		FILE = list[RAND.nextInt(list.length)];
		
		try {
			PLAYER = new AdvancedPlayer(new FileInputStream(FILE));
			PLAYER.setPlayBackListener(new PlaybackListener() {
				@Override
				public void playbackFinished(PlaybackEvent event) {
					play();
				}
			});
			new Thread() {
				@Override
				public void run() {
					try {
						PLAYER.play();
					} catch (JavaLayerException e) {
						e.printStackTrace();
					}
				}
			}.start();
			
			if (LISTENER != null) {
				LISTENER.playbackStarted();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (JavaLayerException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Plays the provided song once.
	 * @param song the song
	 * @param listener a {@link PlaybackListener} for callbacks
	 */
	public static void playOnce(File song, PlaybackListener listener) {
		FILE = song;
		COMPLETED = false;
		try {
			ONCE = new AdvancedPlayer(new FileInputStream(song));
			if (listener != null) {
				ONCE.setPlayBackListener(listener);
			}
			new Thread() {
				@Override
				public void run() {
					try {
						ONCE.play();
					} catch (JavaLayerException e) {
						e.printStackTrace();
					}
				}
			}.start();
			
			if (LISTENER != null) {
				LISTENER.playbackStarted();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (JavaLayerException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Sets the completed flag.
	 * @param completed true, if the one-off song finished playing
	 */
	public static void setCompletedOnce(Boolean completed) {
		COMPLETED = completed;
	}

	/**
	 * Sets the directory from which to retreive a list of MP3s.
	 * @param dir the directory
	 */
	public static void setDir(File dir) {
		DIR = dir;
	}

	/**
	 * Sets the {@link PlayerListener} to use for callbacks.
	 * @param playerListener the listener
	 */
	public static void setListener(PlayerListener playerListener) {
		LISTENER = playerListener;
	}

	/**
	 * Stops playback of the loop.
	 */
	public static void stop() {
		STOPPED = true;
		PLAYER.stop();
		
		if (LISTENER != null) {
			LISTENER.playbackStopped();
		}
	}

	/**
	 * Stops playback of the one-off song.
	 */
	public static void stopOnce() {
		ONCE.stop();
		COMPLETED = true;
	}
}
