package cm.util.music;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Random;

import cm.util.music.JLayerPlayerPausable.PlaybackEvent;
import cm.util.music.JLayerPlayerPausable.PlaybackListener;

public class Player {
	private static Random RAND = new Random();
	private File _dir;
	private File _file;
	private SoundJLayer _player;

	public Player(File dir) {
		setDir(dir);
	}

	private File getDir() {
		return _dir;
	}

	private void setDir(File dir) {
		_dir = dir;
	}

	private File getFile() {
		return _file;
	}

	private void setFile(File file) {
		_file = file;
	}

	private SoundJLayer getPlayer() {
		return _player;
	}

	private void setPlayer(SoundJLayer player) {
		_player = player;
	}
	
	public void pause() {
		getPlayer().pauseToggle();
	}

	public void play() {
		File[] list = getDir().listFiles(new FilenameFilter() {
			
			@Override
			public boolean accept(File dir, String name) {
				return name.toLowerCase().endsWith(".mp3");
			}
		});
		
		setFile(list[RAND.nextInt(list.length)]);
		setPlayer(new SoundJLayer(getFile(), new PlaybackListener() {
			public void playbackFinished(PlaybackEvent event) {
				play();
			}
		}));
		getPlayer().play();
	}
	
	public void playOnce(File song, final Boolean resume) {
		if (!getPlayer().getPlayer().isPaused) {
			pause();
		}
		
		SoundJLayer once = new SoundJLayer(song, new PlaybackListener() {
			public void playbackFinished(PlaybackEvent event) {
				if (resume) {
					pause();
				}
			}
		});
		once.play();
	}
}
