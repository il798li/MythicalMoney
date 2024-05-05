package MythicalMoney.Commands.Basic;

import MythicalMoney.Classes.Helpers.Display;
import MythicalMoney.Utility.DiscordUtility;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;

public class Ping {
    public static SlashCommandData slashCommandData = Commands.slash ("ping", "[Basic] Check my response time in milliseconds.");

    public static void execute (SlashCommandInteractionEvent slashCommandInteractionEvent) {
        JDA jda = slashCommandInteractionEvent.getJDA ();
        long pingMilli = ping (jda);

        final Display[] displays = {
            new Display ("Latency", "I am currently responding to commands within approximately " + pingMilli + " milliseconds.")
        };

        DiscordUtility.deletable (slashCommandInteractionEvent, displays, false);
    }

    public static long ping (JDA jda) {
        return jda.getGatewayPing ();
    }
}
