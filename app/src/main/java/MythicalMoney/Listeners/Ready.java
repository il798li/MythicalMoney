package MythicalMoney.Listeners;

import MythicalMoney.Main;
import MythicalMoney.Commands.Administrator.Settings;
import MythicalMoney.Commands.Basic.Credits;
import MythicalMoney.Commands.Basic.Ping;
import MythicalMoney.Commands.Basic.Statistics;
import MythicalMoney.Commands.Restricted.Save;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.restaction.CommandListUpdateAction;

public class Ready extends ListenerAdapter {

    public void onReady (ReadyEvent readyEvent) {
        JDA jda = readyEvent.getJDA ();

        User mm = jda.getSelfUser ();
        final String name = mm.getName ();
        Main.debug ("Successfully signed in as " + name + "!");

        Guild mmGuild = jda.getGuildById (834113328459677747L);

        CommandListUpdateAction commandListUpdateAction = mmGuild.updateCommands();
        commandListUpdateAction.addCommands (Ping.slashCommandData);
        commandListUpdateAction.addCommands (Settings.slashCommandData);
        commandListUpdateAction.addCommands (Save.slashCommandData);
        commandListUpdateAction.queue ();

        CommandListUpdateAction publishedCommandListUpdateAction = jda.updateCommands ();
        publishedCommandListUpdateAction.addCommands (Credits.slashCommandData);
        publishedCommandListUpdateAction.addCommands (Statistics.slashCommandData);
        publishedCommandListUpdateAction.queue ();

        final long ping = Ping.ping (jda);
        Main.debug (name + " is responding to commands with " + ping + " milliseconds of latency...");
    }

    public static boolean owner (User user) {
        final long [] ownerIDs = {
            655263219459293210L
        };
        final long userID = user.getIdLong ();
        
        for (long ownerID : ownerIDs) {
            if (userID == ownerID) {
                return true;
            }
        }
        return false;
    }
}
