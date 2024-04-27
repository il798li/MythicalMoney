package MythicalMoney.Utility;

import java.util.ArrayList;

import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class CooldownUtility {
    public long userID;
    public String command;
    public long nextTimestamp;

    public static ArrayList <CooldownUtility> cooldowns = new ArrayList <CooldownUtility> ();
    
    public CooldownUtility (long userID, String command, long nextTimestamp) {
        this.userID = userID;
        this.command = command;
        this.nextTimestamp = nextTimestamp;

        cooldowns.add (this);
    }

    public static CooldownUtility find (long userID, String command) {
        final int size = cooldowns.size ();
        for (int index = 0; index < size; index++) {
            CooldownUtility cooldownUtility = cooldowns.get (index);

            final boolean userIDMatch = cooldownUtility.userID == userID;
            final boolean commandMatch = cooldownUtility.command.equals (command);

            if (userIDMatch && commandMatch) {
                return cooldownUtility;
            }
        }

        final long timestamp = DiscordUtility.timestamp ();
        CooldownUtility cooldownUtility = new CooldownUtility(userID, command, timestamp);
        return cooldownUtility;
    }

    public static boolean cooldown (final SlashCommandInteractionEvent slashCommandInteractionEvent, final int cooldown) {
        final User user = slashCommandInteractionEvent.getUser ();
        final long userID = user.getIdLong ();

        final String command = slashCommandInteractionEvent.getName ();

        CooldownUtility cooldownUtility = find (userID, command);
        long currentTimestamp = DiscordUtility.timestamp ();

        if (currentTimestamp < cooldownUtility.nextTimestamp) {
            return false;
        }

        cooldownUtility.nextTimestamp += cooldown;
        return true;
    }
}