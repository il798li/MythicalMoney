package MythicalMoney.Listeners;

import MythicalMoney.Commands.Administrator.Settings;
import MythicalMoney.Commands.Basic.Credits;
import MythicalMoney.Commands.Basic.Ping;
import MythicalMoney.Commands.Basic.Statistics;
import MythicalMoney.Commands.Economy.Information.Balance;
import MythicalMoney.Commands.Economy.Information.Inventory;
import MythicalMoney.Commands.Economy.Information.Land;
import MythicalMoney.Commands.Economy.Tasks.Chop;
import MythicalMoney.Commands.Economy.Tasks.Harvest;
import MythicalMoney.Commands.Economy.Tasks.Hunt;
import MythicalMoney.Commands.Restricted.Save;
import MythicalMoney.Main;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.managers.Presence;
import net.dv8tion.jda.api.requests.restaction.CommandListUpdateAction;

public class Ready extends ListenerAdapter {

    public static boolean owner (User user) {
        final long[] ownerIDs = {
            655263219459293210L
        };
        final long userID = user.getIdLong ();
        for (final long ownerID : ownerIDs) {
            if (userID == ownerID) {
                return true;
            }
        }
        return false;
    }

    public void onReady (ReadyEvent readyEvent) {
        final JDA jda = readyEvent.getJDA ();
        final User mm = jda.getSelfUser ();
        final String name = mm.getName ();
        Main.debug ("Successfully signed in as " + name + "!");
        final Guild mmGuild = jda.getGuildById (834113328459677747L);
        {
            final CommandListUpdateAction commandListUpdateAction = mmGuild.updateCommands ();
            commandListUpdateAction.addCommands (Land.slashCommandData);
            commandListUpdateAction.addCommands (Save.slashCommandData);
            commandListUpdateAction.queue ();
        }
        {
            CommandListUpdateAction publishedCommandListUpdateAction = jda.updateCommands ();
            publishedCommandListUpdateAction.addCommands (Ping.slashCommandData);
            publishedCommandListUpdateAction.addCommands (Settings.slashCommandData);
            publishedCommandListUpdateAction.addCommands (Credits.slashCommandData);
            publishedCommandListUpdateAction.addCommands (Statistics.slashCommandData);
            publishedCommandListUpdateAction.addCommands (Inventory.slashCommandData);
            publishedCommandListUpdateAction.addCommands (Balance.slashCommandData);
            publishedCommandListUpdateAction.addCommands (Hunt.slashCommandData);
            publishedCommandListUpdateAction.addCommands (Harvest.slashCommandData);
            publishedCommandListUpdateAction.addCommands (Chop.slashCommandData);
            publishedCommandListUpdateAction.queue ();
        }
        final long ping = Ping.ping (jda);
        presence (jda);
        Main.debug (name + " is responding to commands with " + ping + " milliseconds of latency...");
    }

    public static void presence (final JDA jda) {
        final Presence jdaPresence = jda.getPresence ();
        final Activity activity = Activity.of (Activity.ActivityType.STREAMING, "Welcome to Mythical Money, Discord's most advanced economy bot ever!");
        jdaPresence.setPresence (OnlineStatus.ONLINE, activity);
    }
}
