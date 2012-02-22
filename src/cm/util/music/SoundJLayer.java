package cm.util.music;

import java.io.File;
import java.net.MalformedURLException;

import javazoom.jl.decoder.JavaLayerException;

import cm.util.music.JLayerPlayerPausable.PlaybackListener;

public class SoundJLayer implements Runnable {
	private File _file;
	private PlaybackListener _listener;
	private JLayerPlayerPausable _player;
	private Thread _thread;
	
	public SoundJLayer(File file, PlaybackListener listener) {
		setFile(file);
		setListener(listener);
	}

	public File getFile() {
		return _file;
	}

	public void setFile(File file) {
		_file = file;
	}

	public PlaybackListener getListener() {
		return _listener;
	}

	public void setListener(PlaybackListener listener) {
		_listener = listener;
	}

	public JLayerPlayerPausable getPlayer() {
		return _player;
	}

	public void setPlayer(JLayerPlayerPausable player) {
		_player = player;
	}

	public Thread getThread() {
		return _thread;
	}

	public void setThread(Thread thread) {
		_thread = thread;
	}
	
	@SuppressWarnings("deprecation")
	private void pause() {
		getPlayer().pause();
		getThread().stop();
		setThread(null);
	}
	
	public void pauseToggle() {
		if (getPlayer().isPaused) {
			play();
		} else {
			pause();
		}
	}
	
	public void play() {
		if (getPlayer() == null) {
			playerInitialize();
		}
		
		setThread(new Thread(this, "AudioPlayerThread"));
		getThread().start();
	}
	
	private void playerInitialize() {
		try {
			setPlayer(new JLayerPlayerPausable(getFile().toURI().toURL(), getListener()));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (JavaLayerException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		try {
			getPlayer().resume();
		} catch (JavaLayerException e) {
			e.printStackTrace();
		}
	}	
}