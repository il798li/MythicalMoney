package MythicalMoney.Commands.Basic;

import MythicalMoney.Main;
import MythicalMoney.Classes.Display.Display;
import MythicalMoney.Utility.DiscordUtility;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;

public class Credits {
    public static SlashCommandData slashCommandData = Commands.slash ("credits", "[Basic] See all the people who contributed to my creation.");

    public static void execute (SlashCommandInteractionEvent slashCommandInteractionEvent) {

        final String anirvdh = DiscordUtility.display (475315771086602241L, slashCommandInteractionEvent);
        final String electrochess = DiscordUtility.display (697535361315766322L, slashCommandInteractionEvent);
        final String diamonddust9 = DiscordUtility.display (694226805439070269L, slashCommandInteractionEvent);
        final String il798li = DiscordUtility.display (655263219459293210L, slashCommandInteractionEvent);

        final Display [] displays = {
            new Display ("Inspiration", "My creation was inspired by " + electrochess + " and " + anirvdh + "."),
            new Display ("Branding", "My name was inspired by " + diamonddust9 + ".\nMy logo was designed by " + il798li + "."),
            new Display ("Development", "I was programmed using [Java Discord API](https://docs.jda.wiki/) by " + il798li + ".")
        };

        DiscordUtility.deletable(slashCommandInteractionEvent, displays, false);
    }
}