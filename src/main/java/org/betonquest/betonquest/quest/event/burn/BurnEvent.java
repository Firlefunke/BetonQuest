package org.betonquest.betonquest.quest.event.burn;

import org.betonquest.betonquest.VariableNumber;
import org.betonquest.betonquest.api.profiles.Profile;
import org.betonquest.betonquest.api.quest.event.Event;
import org.betonquest.betonquest.exceptions.QuestRuntimeException;
import org.bukkit.entity.Player;

/**
 * The burn event. Sets the player on fire.
 */
public class BurnEvent implements Event {
    /**
     * Duration of the burn effect
     */
    private final VariableNumber duration;

    /**
     * Create a burn event that sets the player on fire for the given duration.
     *
     * @param duration duration of burn
     */
    public BurnEvent(final VariableNumber duration) {
        this.duration = duration;
    }

    @Override
    public void execute(final Profile profile) throws QuestRuntimeException {
        final Player player = profile.getOnlineProfile().getOnlinePlayer();
        player.setFireTicks(duration.getInt(profile) * 20);
    }
}
