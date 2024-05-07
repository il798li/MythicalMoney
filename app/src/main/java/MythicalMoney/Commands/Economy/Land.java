package MythicalMoney.Commands.Economy;

import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;
public class Land {
    public SlashCommandData slashCommandData = this.slashCommandData ();

    public SlashCommandData slashCommandData () {
        SlashCommandData slashCommandData = Commands.slash ("land", "Shows what Land is owned by a user.");
        slashCommandData.addOption (OptionType.USER, "user", "Shows what Land is owned by a user other than yourself.");
        return slashCommandData;
    }

    public static void execute
}
