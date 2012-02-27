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
	private static Random RAND = new Random();
	private static File DIR = null;
	private static File FILE = null;
	private static AdvancedPlayer PLAYER = null;
	private static PlayerListener LISTENER = null;
	private static Boolean STOPPED = false;

	public static void setDir(File dir) {
		DIR = dir;
	}

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
				public void playbackFinished(PlaybackEvent event) {
					play();
				}
			});
			new Thread() {
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

	public static void playOnce(File song, PlaybackListener listener) {
		try {
			final AdvancedPlayer once = new AdvancedPlayer(new FileInputStream(song));
			if (listener != null) {
				once.setPlayBackListener(listener);
			}
			new Thread() {
				public void run() {
					try {
						once.play();
					} catch (JavaLayerException e) {
						e.printStackTrace();
					}
				}
			}.start();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (JavaLayerException e) {
			e.printStackTrace();
		}
	}

	public static void stop() {
		STOPPED = true;
		PLAYER.stop();
	}
}
