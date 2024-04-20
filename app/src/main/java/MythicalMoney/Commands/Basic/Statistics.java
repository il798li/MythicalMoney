package MythicalMoney.Commands.Basic;

import MythicalMoney.Classes.Display;
import MythicalMoney.Utility.DiscordUtility;
import MythicalMoney.Utility.DiscordUtility.TimestampFormat;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.User;

import java.util.List;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;

public class Statistics {
    public static SlashCommandData slashCommandData = Commands.slash ("statistics", "[Basic] See some basic statistics about me.");

    public static void execute (SlashCommandInteractionEvent slashCommandInteractionEvent) {
        JDA jda = slashCommandInteractionEvent.getJDA ();

        final List <Guild> guildList = jda.getGuilds ();
        final int guildCount = guildList.size ();
        final Display guildDisplay = new Display ("Servers", "I am currently participating in " + guildCount + " servers.");

        final List <User> userList = jda.getUsers ();
        final int userCount = userList.size ();
        final Display userDisplay = new Display ("Users", "I am currently being used by " + userCount + " Discord accounts.");

        final long pingMilli = jda.getGatewayPing ();
        final Display pingDisplay = new Display ("Latency", "I am currently responding to commands within approximately " + pingMilli + " milliseconds.");

        final int timestamp = 1600805599;
        final String relativeBirthday = DiscordUtility.timestamp (TimestampFormat.relative, timestamp);
        final String exactBirthday = DiscordUtility.timestamp(TimestampFormat.specificDateBasicTime, timestamp);
        final Display birthdayDisplay = new Display ("Birthday", "I was created " + relativeBirthday + " on " + exactBirthday + ".");
    
        Display [] displays = {
            guildDisplay,
            userDisplay,
            pingDisplay,
            birthdayDisplay
        };
        DiscordUtility.deletable(slashCommandInteractionEvent, displays, false);
    }
}
