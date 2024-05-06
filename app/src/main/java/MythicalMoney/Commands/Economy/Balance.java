package MythicalMoney.Commands.Economy;

import MythicalMoney.Classes.Helpers.Display;
import MythicalMoney.Data.Player;
import MythicalMoney.Utility.BasicUtility;
import MythicalMoney.Utility.DiscordUtility;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;

public class Balance {
    public static final SlashCommandData slashCommandData = slashCommandData ();

    public static SlashCommandData slashCommandData () {
        SlashCommandData slashCommandData = Commands.slash ("balance", "[Economy] Checks how much Mythical Money a user has.");
        slashCommandData.addOption (OptionType.USER, "user", "See another user's Balance. Leave this blank to see your own Balance.");
        return slashCommandData;
    }

    public static void execute (SlashCommandInteractionEvent slashCommandInteractionEvent) {
        final OptionMapping optionMapping = slashCommandInteractionEvent.getOption ("user");
        User author = slashCommandInteractionEvent.getUser ();
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
        final String formattedBalance = BasicUtility.formatNumber (player.mm);

        final Display display = new Display ("Mythical Money Balance", startingString + "M$" + formattedBalance + ".");
        final Display[] displays = {
            display
        };
        DiscordUtility.deletable (slashCommandInteractionEvent, displays, false);
    }
}
