package MythicalMoney.Commands.Basic;

import java.util.ArrayList;

import MythicalMoney.Classes.Display;
import MythicalMoney.Data.Setting;
import MythicalMoney.Utility.DiscordUtility;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;

public class Settings {
    public static SlashCommandData slashCommandData = slashCommandData ();

    public static SlashCommandData slashCommandData () {
        SlashCommandData slashCommandData = Commands.slash ("settings", "Mythical Money");
        slashCommandData.addOption (OptionType.BOOLEAN, "compact", "compact", false);
        return slashCommandData;
    }

    public static void execute (SlashCommandInteractionEvent slashCommandInteractionEvent) {
        ArrayList <Display> displayArrayList = new ArrayList <Display> ();
        {
            OptionMapping optionMapping = slashCommandInteractionEvent.getOption ("compact");
            if (optionMapping != null) {
                boolean compact = optionMapping.getAsBoolean();
                Setting guildSetting = Setting.find (slashCommandInteractionEvent);
                guildSetting.compact = compact;

                String compactAction = "Compact mode was successfully disabled! From now on, you will no longer see contexts or timestamps on embeds sent by me.";
                if (compact == true) {
                    compactAction = "Compact mode was successfully enabled! From now on, you will see contexts and timestamps on embeds sent by me.";
                }

                Display compactDisplay = new Display ("Compact", compactAction);
                displayArrayList.add (compactDisplay);
            }
        }
        final int size = displayArrayList.size ();
        Display [] displayList = new Display [size];
        for (int index = 0; index < displayList.length; index++) {
            Display display = displayArrayList.get (index);
            displayList [index] = display;
        }

        DiscordUtility.deletable(slashCommandInteractionEvent, displayList, false);
    }
}
