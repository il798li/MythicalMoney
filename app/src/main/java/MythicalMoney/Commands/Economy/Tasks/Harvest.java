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
public class Harvest {
    public static final SlashCommandData slashCommandData = Commands.slash ("harvest", "[Economy] [Tasks] Harvests Crops on your Farm.");

    public static void execute (final SlashCommandInteractionEvent slashCommandInteractionEvent) {
        final User user = slashCommandInteractionEvent.getUser ();
        final long userID = user.getIdLong ();
        final Player player = Player.get (userID);
        {
            final boolean cooldown = CooldownUtility.cooldown (slashCommandInteractionEvent, player.toolSet.hoe.cooldown);
            if (cooldown == false) {
                final CooldownUtility cooldownUtility = CooldownUtility.find (slashCommandInteractionEvent);
                final String nextTimestampFormatted = DiscordUtility.timestamp (DiscordUtility.TimestampFormat.accurateTime, cooldownUtility.nextTimestamp);
                final Display[] displays = new Display[1];
                displays[0] = new Display ("Harvesting Cooldown", "You cannot Harvest until " + nextTimestampFormatted + " because your " + player.toolSet.hoe.display.single + " has a cooldown of " + player.toolSet.hoe.cooldown + " seconds.");
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
        final int[] crops = {
            0,
            0,
            0,
            0
        };
        for (int loop = 0; loop < player.landSet.farm.harvests; loop++) {
            final int crop = player.landSet.farm.chances.chance ();
            loot[crop] += player.toolSet.hoe.purity;
            crops[crop] += 1;
        }
        final Display[] displays = new Display[4];
        final Obtainable[] obtainableCrops = Obtainable.get (Obtainable.ToolType.Hoe);
        for (int index = 0; index < 4; index++) {
            final int harvested = crops[index];
            final int obtained = loot[index];
            final Obtainable crop = obtainableCrops[index];
            final String cropDisplay = crop.display.display (harvested);
            final Item item = Item.get (crop);
            final String itemDisplay = item.display.display (obtained);
            displays[index] = new Display (cropDisplay, "You harvested " + harvested + " " + cropDisplay + " and obtained " + obtained + " " + itemDisplay + "!");
        }
        DiscordUtility.deletable (slashCommandInteractionEvent, displays, false);
    }
}
