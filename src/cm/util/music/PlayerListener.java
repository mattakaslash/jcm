package cm.util.music;

/**
 * Provides a mechanism for {@link Player} callbacks.
 * 
 * @author Matthew Rinehart &lt;gomamon2k at yahoo.com&gt;
 * @since 2.0
 */
public abstract class PlayerListener {

	public abstract void playbackStarted();

	public abstract void playbackStopped();

}
