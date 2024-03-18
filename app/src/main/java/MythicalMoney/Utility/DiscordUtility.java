package MythicalMoney.Utility;

import MythicalMoney.Data.Setting;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class DiscordUtility {
    public static void embed (SlashCommandInteractionEvent slashCommandInteractionEvent) {
        EmbedBuilder embedBuilder = new EmbedBuilder ();
        embedBuilder.setTitle ("Mythical Money");
    }

    public static String description (SlashCommandInteractionEvent slashCommandInteractionEvent) {
        Guild guild = slashCommandInteractionEvent.getGuild ();
        if (guild == null) {
            return "_ _";
        }
        final Setting guildSettings = Setting.find (guild);
        if (guildSettings.compact) {
            return "_ _";
        }

        String description = "This embed was requested by ";
        {
            final User user = slashCommandInteractionEvent.getUser ();
            final String userMention = user.getAsMention ();
            description += userMention;
        }
        description += " in ";
        {
            MessageChannel channel = slashCommandInteractionEvent.getChannel ();
            final String channelMention = channel.getAsMention ();
            description += channelMention;
        }
        description += " of **";
        {
            final String rawGuildName = guild.getName ();
            final String formattedGuildName = cancelMarkdown(rawGuildName);
            description += formattedGuildName;
        }
        description += "**.";
        return description;
    }

    public static String cancelMarkdown (final String string) {
        String newString = "";
        final String symbols = "`-=~!@#$%^&*()_+[]\\{}|:;'\",./<>?";
        final char [] originalString = string.toCharArray ();
        for (char character : originalString) {
            if (symbols.contains ("" + character)) {
                newString += "\\";
            }
            newString += character;
        }
        return newString;
    }
}