package MythicalMoney.Commands.Economy.Information;

import MythicalMoney.Classes.Helpers.Display;
import MythicalMoney.Classes.Item;
import MythicalMoney.Data.Player;
import MythicalMoney.Utility.DiscordUtility;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;

public class Inventory {
    public static final SlashCommandData slashCommandData = slashCommandData ();

    public static SlashCommandData slashCommandData () {
        final SlashCommandData slashCommandData = Commands.slash ("inventory", "[Economy] Shows all items and their amount in your Inventory.");
        slashCommandData.addOption (OptionType.USER, "user", "See another user's Inventory. Leave this blank to see your own Inventory.");
        return slashCommandData;
    }

    public static void execute (final SlashCommandInteractionEvent slashCommandInteractionEvent) {
        final OptionMapping optionMapping = slashCommandInteractionEvent.getOption ("user");
        final User author = slashCommandInteractionEvent.getUser ();
        final long authorID = author.getIdLong ();
        User user = author;
        if (optionMapping != null) {
            user = optionMapping.getAsUser ();
        }
        final long userID = user.getIdLong ();
        String startingString = "You have ";
        if (userID != authorID) {
            startingString = user.getAsMention ();
            startingString += " has ";
        }
        final Player player = Player.get (userID);
        final Item[] items = Item.toList ();
        Display[] displays = new Display[items.length];
        for (int index = 0; index < items.length; index++) {
            final int amount = player.inventory.items[index];
            final Item item = items[index];
            String label = item.display.plural;
            if (amount == 1) {
                label = item.display.single;
            }
            displays[index] = new Display (item.display.plural, startingString + amount + " " + label + ".");
        }
        DiscordUtility.deletable (slashCommandInteractionEvent, displays, false);
    }
}
