package MythicalMoney.Commands.Economy.Tasks;

import MythicalMoney.Classes.Helpers.Display;
import MythicalMoney.Classes.Item;
import MythicalMoney.Classes.Obtainable;
import MythicalMoney.Data.Player;
import MythicalMoney.Utility.CooldownUtility;
import MythicalMoney.Utility.DiscordUtility;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;
public class Chop {
    public static final SlashCommandData slashCommandData = Commands.slash ("chop", "[Economy] [Tasks] Chops Trees on your Forest.");

    public static void execute (final SlashCommandInteractionEvent slashCommandInteractionEvent) {
        final User user = slashCommandInteractionEvent.getUser ();
        final long userID = user.getIdLong ();
        final Player player = Player.get (userID);
        {
            final boolean cooldown = CooldownUtility.cooldown (slashCommandInteractionEvent, player.toolSet.axe.cooldown);
            if (cooldown == false) {
                final CooldownUtility cooldownUtility = CooldownUtility.find (slashCommandInteractionEvent);
                final String nextTimestampFormatted = DiscordUtility.timestamp (DiscordUtility.TimestampFormat.accurateTime, cooldownUtility.nextTimestamp);
                final Display[] displays = new Display[1];
                displays[0] = new Display ("Chopping Cooldown", "You cannot Chop until " + nextTimestampFormatted + " because your " + player.toolSet.axe.display.single + " has a cooldown of " + player.toolSet.axe.cooldown + " seconds.");
                DiscordUtility.deletable (slashCommandInteractionEvent, displays, false);
                return;
            }
        }
        final int[] loot = {
            0,
            0,
            0,
            0
        };
        final int[] trees = {
            0,
            0,
            0,
            0
        };
        for (int loop = 0; loop < player.landSet.forest.harvests; loop++) {
            final int crop = player.landSet.forest.chances.chance ();
            loot[crop] += player.toolSet.axe.purity;
            trees[crop] += 1;
        }
        final Display[] displays = new Display[4];
        final Obtainable[] obtainableTrees = Obtainable.get (Obtainable.ToolType.Axe);
        for (int index = 0; index < 4; index++) {
            final int chopped = trees[index];
            final int obtained = loot[index];
            final Obtainable tree = obtainableTrees[index];
            final String treeDisplay = tree.display.display (chopped);
            final Item item = Item.get (tree);
            final String itemDisplay = item.display.display (obtained);
            player.inventory.add (item, obtained);
            displays[index] = new Display (treeDisplay, "You harvested " + chopped + " " + treeDisplay + " and obtained " + obtained + " " + itemDisplay + "!");
        }
        DiscordUtility.deletable (slashCommandInteractionEvent, displays, false);
    }
}
