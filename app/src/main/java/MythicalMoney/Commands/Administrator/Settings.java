package MythicalMoney.Commands.Administrator;

import java.util.ArrayList;

import MythicalMoney.Main;
import MythicalMoney.Classes.Display.Display;
import MythicalMoney.Data.Setting;
import MythicalMoney.Utility.BasicUtility;
import MythicalMoney.Utility.DiscordUtility;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.DefaultMemberPermissions;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;

public class Settings {
    public static SlashCommandData slashCommandData = slashCommandData ();

    public static SlashCommandData slashCommandData () {
        SlashCommandData slashCommandData = Commands.slash ("settings", "[Administrator] Change my settings for this Discord server.");
        slashCommandData.addOption (OptionType.BOOLEAN, "compact", "Remove additional information from embeds with Compact mode.", false);

        { // Without this code section, the command was working as intended.
            DefaultMemberPermissions defaultMemberPermissions = DefaultMemberPermissions.DISABLED;
            slashCommandData.setDefaultPermissions (defaultMemberPermissions);
        }
        return slashCommandData;
    }

    public static void execute (SlashCommandInteractionEvent slashCommandInteractionEvent) {
        ArrayList <Display> displayArrayList = new ArrayList <Display> ();
        {
            final OptionMapping optionMapping = slashCommandInteractionEvent.getOption ("compact");
            if (optionMapping != null) {
                final boolean compact = optionMapping.getAsBoolean();
                final Setting guildSetting = Setting.get(slashCommandInteractionEvent);
                guildSetting.compact = compact;
                Main.debug (BasicUtility.toString (Setting.get(slashCommandInteractionEvent).compact));

                String compactAction = "Compact mode was successfully disabled! From now on, you will see contexts and timestamps on embeds sent by me.";
                if (compact == true) {
                    compactAction = "Compact mode was successfully enabled! From now on, you will no longer see contexts or timestamps on embeds sent by me.";
                }

                final Display compactDisplay = new Display ("Compact Mode", compactAction);
                displayArrayList.add (compactDisplay);
            }
        }
        final int size = displayArrayList.size ();
        Display [] displayList = new Display [size];
        if (size == 0) {
            final Display display = new Display ("Server Settings", "No settings were modified by this command.");
            displayList = new Display [1];
            displayList [0] = display;
        } else {
            for (int index = 0; index < displayList.length; index++) {
                Display display = displayArrayList.get(index);
                displayList[index] = display;
            }
        }

        DiscordUtility.deletable(slashCommandInteractionEvent, displayList, false);
    }
}
