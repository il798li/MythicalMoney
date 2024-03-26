package MythicalMoney.Listeners;

import MythicalMoney.Main;
import MythicalMoney.Classes.Item;
import MythicalMoney.Utility.DiscordUtility;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.restaction.CommandCreateAction;

public class ReadyListener extends ListenerAdapter {

    public static Guild mmGuild;

    public void onReady (ReadyEvent readyEvent) {
        final JDA jda = readyEvent.getJDA ();
        final String name = jda.getSelfUser ().getName ();
        Main.debug ("Successfully signed in as " + name + "!");
        Main.debug (Item.toList() [1].toString ());

        mmGuild = jda.getGuildById (834113328459677747L);        
    }
}
