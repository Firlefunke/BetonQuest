package org.betonquest.betonquest.compatibility.mmogroup.mmocore;

import net.Indyuce.mmocore.api.event.PlayerLevelUpEvent;
import net.Indyuce.mmocore.experience.Profession;
import org.betonquest.betonquest.BetonQuest;
import org.betonquest.betonquest.Instruction;
import org.betonquest.betonquest.api.Objective;
import org.betonquest.betonquest.api.profiles.Profile;
import org.betonquest.betonquest.exceptions.InstructionParseException;
import org.betonquest.betonquest.utils.PlayerConverter;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;

@SuppressWarnings("PMD.CommentRequired")
public class MMOCoreProfessionObjective extends Objective implements Listener {

    private final String professionName;
    private final int targetLevel;

    public MMOCoreProfessionObjective(final Instruction instruction) throws InstructionParseException {
        super(instruction);

        template = ObjectiveData.class;
        professionName = instruction.next();
        targetLevel = instruction.getInt();

    }

    @EventHandler(ignoreCancelled = true)
    public void onLevelUp(final PlayerLevelUpEvent event) {
        final Profile profile = PlayerConverter.getID(event.getPlayer());
        if (!containsPlayer(profile) || !checkConditions(profile)) {
            return;
        }
        final Profession profession = event.getProfession();
        if (profession == null) {
            return;
        }

        if (!profession.getName().equalsIgnoreCase(professionName) || event.getNewLevel() < targetLevel) {
            return;
        }
        completeObjective(profile);
    }

    @Override
    public void start() {
        Bukkit.getPluginManager().registerEvents(this, BetonQuest.getInstance());
    }

    @Override
    public void stop() {
        HandlerList.unregisterAll(this);
    }

    @Override
    public String getDefaultDataInstruction() {
        return "";
    }

    @Override
    public String getProperty(final String name, final Profile profile) {
        return "";
    }

}
