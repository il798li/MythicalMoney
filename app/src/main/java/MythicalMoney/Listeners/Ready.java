package MythicalMoney.Listeners;

import MythicalMoney.Commands.Administrator.Settings;
import MythicalMoney.Commands.Basic.Credits;
import MythicalMoney.Commands.Basic.Ping;
import MythicalMoney.Commands.Basic.Statistics;
import MythicalMoney.Commands.Economy.Balance;
import MythicalMoney.Commands.Economy.Inventory;
import MythicalMoney.Commands.Restricted.Save;
import MythicalMoney.Main;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.restaction.CommandListUpdateAction;

public class Ready extends ListenerAdapter {

    public static boolean owner (User user) {
        final long[] ownerIDs = {
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

    public void onReady (ReadyEvent readyEvent) {
        JDA jda = readyEvent.getJDA ();

        User mm = jda.getSelfUser ();
        final String name = mm.getName ();
        Main.debug ("Successfully signed in as " + name + "!");

        Guild mmGuild = jda.getGuildById (834113328459677747L);

        CommandListUpdateAction commandListUpdateAction = null;
        if (mmGuild != null) {
            commandListUpdateAction = mmGuild.updateCommands ();
        }
        commandListUpdateAction.addCommands (Save.slashCommandData);
        commandListUpdateAction.queue ();

        CommandListUpdateAction publishedCommandListUpdateAction = jda.updateCommands ();
        publishedCommandListUpdateAction.addCommands (Ping.slashCommandData);
        publishedCommandListUpdateAction.addCommands (Settings.slashCommandData);
        publishedCommandListUpdateAction.addCommands (Credits.slashCommandData);
        publishedCommandListUpdateAction.addCommands (Statistics.slashCommandData);
        publishedCommandListUpdateAction.addCommands (Inventory.slashCommandData);
        publishedCommandListUpdateAction.addCommands (Balance.slashCommandData);
        publishedCommandListUpdateAction.queue ();

        final long ping = Ping.ping (jda);
        Main.debug (name + " is responding to commands with " + ping + " milliseconds of latency...");
    }
}
