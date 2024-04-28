package MythicalMoney.Commands.Restricted;

import MythicalMoney.Classes.Display.Display;
import MythicalMoney.Data.Helpers.Inventory;
import MythicalMoney.Data.Player;
import MythicalMoney.Data.Setting;
import MythicalMoney.Listeners.Ready;
import MythicalMoney.Utility.DiscordUtility;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;

public class Save {
    public static SlashCommandData slashCommandData = slashCommandData ();

    public static SlashCommandData slashCommandData () {
        SlashCommandData slashCommandData = Commands.slash ("save", "[Restricted] Save all current data to JSON.");
        return slashCommandData;
    }

    public static void execute (SlashCommandInteractionEvent slashCommandInteractionEvent) {
        User user = slashCommandInteractionEvent.getUser ();
        final boolean owner = Ready.owner (user);
        if (owner == false) {
            final Display errorDisplay = new Display ("Data Saving Error", "This command is only accessible by authorized users.");
            final Display [] displays = {
                errorDisplay
            };
            DiscordUtility.deletable(slashCommandInteractionEvent, displays, false);
            return;
        }
        save ();
        final Display successDisplay = new Display ("Data Save Success", "All data regarding Discord server settings was successfully saved!");
        final Display [] displays = {
            successDisplay
        };
        DiscordUtility.deletable(slashCommandInteractionEvent, displays, false);
    }

    public static void save () {
        Setting.save ();
        Player.save ();
    }
}
