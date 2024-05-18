package MythicalMoney.Listeners;

import MythicalMoney.Commands.Administrator.Settings;
import MythicalMoney.Commands.Basic.Credits;
import MythicalMoney.Commands.Basic.Ping;
import MythicalMoney.Commands.Basic.Statistics;
import MythicalMoney.Commands.Economy.Balance;
import MythicalMoney.Commands.Economy.Inventory;
import MythicalMoney.Commands.Economy.Land;
import MythicalMoney.Commands.Economy.Tasks.Harvest;
import MythicalMoney.Commands.Economy.Tasks.Hunt;
import MythicalMoney.Commands.Restricted.Save;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.restaction.interactions.ReplyCallbackAction;

public class SlashCommandInteraction extends ListenerAdapter {

    public void onSlashCommandInteraction (SlashCommandInteractionEvent slashCommandInteractionEvent) {
        ReplyCallbackAction replyCallbackAction = slashCommandInteractionEvent.deferReply ();
        replyCallbackAction.queue ();
        final String name = slashCommandInteractionEvent.getName ();
        switch (name) {
            case "credits": {
                Credits.execute (slashCommandInteractionEvent);
                break;
            }
            case "ping": {
                Ping.execute (slashCommandInteractionEvent);
                break;
            }
            case "statistics": {
                Statistics.execute (slashCommandInteractionEvent);
                break;
            }
            case "settings": {
                Settings.execute (slashCommandInteractionEvent);
                break;
            }
            case "save": {
                Save.execute (slashCommandInteractionEvent);
                break;
            }
            case "inventory": {
                Inventory.execute (slashCommandInteractionEvent);
                break;
            }
            case "balance": {
                Balance.execute (slashCommandInteractionEvent);
                break;
            }
            case "land": {
                Land.execute (slashCommandInteractionEvent);
                break;
            }
            case "hunt": {
                Hunt.execute (slashCommandInteractionEvent);
                break;
            }
            case "harvest": {
                Harvest.execute (slashCommandInteractionEvent);
                break;
            }
        }
    }
}
