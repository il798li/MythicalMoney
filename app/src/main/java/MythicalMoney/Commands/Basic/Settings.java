package MythicalMoney.Commands.Basic;

import MythicalMoney.Listeners.Ready;
import net.dv8tion.jda.api.interactions.commands.build.SubcommandData;

public class Settings {
    public static SubcommandData subcommandData;

    public Settings () {
        subcommandData = new SubcommandData("settings", "Mythical Money");
        Ready.mmBaseCommand.addSubcommands (subcommandData);
    }
}
