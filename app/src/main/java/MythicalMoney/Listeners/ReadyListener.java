package MythicalMoney.Listeners;

import MythicalMoney.Main;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.SelfUser;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class ReadyListener extends ListenerAdapter {

    public void onReady (ReadyEvent readyEvent) {
        JDA jda = readyEvent.getJDA ();
        SelfUser user = jda.getSelfUser ();
        String name = user.getName ();
        Main.debug ("Signed in as " + name + "!");
    }
}
