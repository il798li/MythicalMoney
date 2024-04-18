package MythicalMoney.Commands.Basic;

import MythicalMoney.Main;
import MythicalMoney.Classes.Display;
import MythicalMoney.Utility.DiscordUtility;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;

public class Credits {

    public static SlashCommandData slashCommandData;

    public Credits (JDA jda) {
        slashCommandData = Commands.slash ("credits", "Mythical Money");
    }

    public static void execute (SlashCommandInteractionEvent slashCommandInteractionEvent) {
        JDA jda = slashCommandInteractionEvent.getJDA ();

        Main.debug (slashCommandInteractionEvent.getCommandString());

        final Display [] displays = {
            new Display ("Inspiration", "My creation was inspired by " + DiscordUtility.display (475315771086602241L, slashCommandInteractionEvent) + " and " + DiscordUtility.display (697535361315766322L, slashCommandInteractionEvent) + "."),
            new Display ("Branding", "My name was inspired by " + DiscordUtility.display (694226805439070269L, slashCommandInteractionEvent) + ".")
        };

        DiscordUtility.deletable(slashCommandInteractionEvent, displays, false);
    }
}