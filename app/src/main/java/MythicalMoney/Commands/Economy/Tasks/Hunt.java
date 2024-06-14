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
public class Hunt {
    public static final SlashCommandData slashCommandData = Commands.slash ("hunt", "[Economy] [Tasks] Hunts Monsters on your Hunting Ground.");
    public static final long slashCommandID = 1240814637352751146L;

    public static void execute (SlashCommandInteractionEvent slashCommandInteractionEvent) {
        final User user = slashCommandInteractionEvent.getUser ();
        final long userID = user.getIdLong ();
        final Player player = Player.get (userID);
        {
            final boolean cooldown = CooldownUtility.cooldown (slashCommandInteractionEvent, player.toolSet.weapon.cooldown);
            if (cooldown == false) {
                final CooldownUtility cooldownUtility = CooldownUtility.find (slashCommandInteractionEvent);
                final String nextTimestampFormatted = DiscordUtility.timestamp (DiscordUtility.TimestampFormat.accurateTime, cooldownUtility.nextTimestamp);
                final Display[] displays = new Display[1];
                displays[0] = new Display ("Hunting Cooldown", "You cannot Hunt until " + nextTimestampFormatted + " because your " + player.toolSet.weapon.display.single + " has a cooldown of " + player.toolSet.weapon.cooldown + " seconds.");
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
        final int[] monsters = {
            0,
            0,
            0,
            0
        };
        for (int loop = 0; loop < player.landSet.huntingGround.harvests; loop++) {
            final int monster = player.landSet.huntingGround.chances.chance ();
            loot[monster] += player.toolSet.weapon.purity;
            monsters[monster] += 1;
        }
        final Display[] displays = new Display[4];
        final Obtainable[] obtainableMonsters = Obtainable.get (Obtainable.ToolType.Weapon);
        for (int index = 0; index < 4; index++) {
            final int hunted = monsters[index];
            final int obtained = loot[index];
            final Obtainable monster = obtainableMonsters[index];
            final String monsterDisplay = monster.display.display (hunted);
            final Item item = Item.get (monster);
            final String itemDisplay = item.display.display (obtained);
            player.inventory.add (item, obtained);
            displays[index] = new Display (monsterDisplay, "You hunted " + hunted + " " + monsterDisplay + " and obtained " + obtained + " " + itemDisplay + "!");
        }
        DiscordUtility.deletable (slashCommandInteractionEvent, displays, false);
    }
}
