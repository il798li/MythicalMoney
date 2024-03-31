package MythicalMoney.Commands.Basic;

import MythicalMoney.Main;
import MythicalMoney.Classes.Display;
import MythicalMoney.Listeners.Ready;
import MythicalMoney.Utility.DiscordUtility;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.SubcommandData;

public class Credits {
    public static SubcommandData subcommandData;

    public Credits () {
        subcommandData = new SubcommandData("credits", "Mythical Money");
        Ready.mmBaseCommand.addSubcommands (subcommandData);
    }

    public static void execute (SlashCommandInteractionEvent slashCommandInteractionEvent) {
        JDA jda = slashCommandInteractionEvent.getJDA ();

        final Display [] displays = {
            new Display ("Inspiration", "My creation was inspired by @" + name (475315771086602241L, jda) + " and @" + name (697535361315766322L, jda) + "."),
            new Display ("Branding", "My name, Mythical Money, was inspired by @" + name (694226805439070269L, jda) + ".")
        };

        DiscordUtility.deletable(slashCommandInteractionEvent, displays, true);
    }

    public static String name (final long id, JDA jda) {
        final User user = jda.getUserById ("" + id);
        if (user == null) {
            Main.debug (id + "is invalid.");
            return "UKNOWN_NAME";
        }
        final String name = user.getName ();
        return name;
    }
}
