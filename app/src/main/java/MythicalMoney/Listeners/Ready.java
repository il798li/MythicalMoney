package MythicalMoney.Listeners;

import MythicalMoney.Main;
import MythicalMoney.Commands.Basic.Credits;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;
import net.dv8tion.jda.api.requests.restaction.CommandListUpdateAction;

public class Ready extends ListenerAdapter {

    public static Guild mmGuild;
    public static SlashCommandData mmBaseCommand;

    public void onReady (ReadyEvent readyEvent) {
        JDA jda = readyEvent.getJDA ();

        final String name = jda.getSelfUser ().getName ();
        Main.debug ("Successfully signed in as " + name + "!");

        mmGuild = jda.getGuildById (834113328459677747L);
        
        {
            new Credits (jda);
        }

        mmGuild.upsertCommand (Credits.slashCommandData);

        CommandListUpdateAction commandListUpdateAction = mmGuild.updateCommands();
        commandListUpdateAction.addCommands (Credits.slashCommandData);
        commandListUpdateAction.queue ();
    }
}
