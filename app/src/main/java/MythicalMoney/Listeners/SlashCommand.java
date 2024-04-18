package MythicalMoney.Listeners;

import MythicalMoney.Commands.Basic.Credits;
import MythicalMoney.Commands.Basic.Ping;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.restaction.interactions.ReplyCallbackAction;

public class SlashCommand extends ListenerAdapter {
    
    public void onSlashCommandInteraction(SlashCommandInteractionEvent slashCommandInteractionEvent) {
        ReplyCallbackAction replyCallbackAction = slashCommandInteractionEvent.deferReply();
        replyCallbackAction.queue ();
        final String name = slashCommandInteractionEvent.getName();
        switch (name) {
            case "credits":
                Credits.execute (slashCommandInteractionEvent);
            case "ping":
                Ping.execute (slashCommandInteractionEvent);
        }
    }
}
