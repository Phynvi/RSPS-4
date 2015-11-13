import java.util.HashMap;
import java.util.Map;

/**
 * Protects the Server from Mass-Clicking and other Flooding activities
 * @author surfer25
 */
public final class ActionFloodProtector {

    private final Map<client, Long> protector = new HashMap<client, Long>();

    /**
     * Adds a new <code>client</code> to the protector
     * @param c {@link client}
     */
    public final void addPlayer(final client c) {
        protector.put(c, System.currentTimeMillis());
    }

    /**
     * Removes a client from the protector
     * @param c {@link client}
     */
    public final void removePlayer(final client c) {
        protector.remove(c);
    }

    /**
     * Whether the <code>client</code> can preform the desired action.
     * @param c {@link client}
     * @return <code>true</code> the Player can continue with the action
     */
    public boolean canDoAction(client c) {
        if (protector.containsKey(c)) {
            long l = protector.get(c);
            if (System.currentTimeMillis() - l >= 1000) {
                protector.remove(c);
                return true;
            } else {
                return false;
            }
        } else {
            return true;
        }
    }
	
	/**
     * Whether the <code>client</code> can preform the desired action.
     * @param c {@link client}
      * @param delay the time the player has to wait in seconds
     * @return <code>true</code> the Player can continue with the action
     */
    public boolean canDoAction(client c, final long delay) {
        if (protector.containsKey(c)) {
            long l = protector.get(c);
            if (System.currentTimeMillis() - l >= (delay * 1000)) {
                protector.remove(c);
                return true;
            } else {
                return false;
            }
        } else {
            return true;
        }
    }
}