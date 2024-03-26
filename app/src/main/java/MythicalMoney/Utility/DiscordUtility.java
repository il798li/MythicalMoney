package MythicalMoney.Utility;

import MythicalMoney.Main;
import MythicalMoney.Data.Setting;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import java.time.Instant;

public class DiscordUtility {
    public static void embed (SlashCommandInteractionEvent slashCommandInteractionEvent, String [] names, String [] values) {
        EmbedBuilder embedBuilder = new EmbedBuilder ();
        final Setting setting = Setting.find (slashCommandInteractionEvent.getGuild ());
		if (setting.compact == false) {
			embedBuilder.setTitle ("Mythical Money");
		}
		String description = embedDescription (slashCommandInteractionEvent);
		for (int index = 0; index < names.length && index < values.length; index += 1) {
			description += "\n\n**";
			description += names [index];
			description += "**\n";
			description += values [index];
		}
        description += "\n" + embedEnding (slashCommandInteractionEvent);
		embedBuilder.setDescription (description);
    }

    public static String embedDescription (SlashCommandInteractionEvent slashCommandInteractionEvent) {
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

    public static String embedEnding (SlashCommandInteractionEvent slashCommandInteractionEvent) {
        final Guild guild = slashCommandInteractionEvent.getGuild ();
        final Setting setting = Setting.find (guild);
        if (setting.compact) {
            return "";
        }

        String ending = "\nThis embed was sent on ";
        ending += timestamp (TimestampFormat.relative);
        ending += " at ";
        ending += timestamp (TimestampFormat.accurateTime);
        ending += " on ";
        ending += timestamp (TimestampFormat.accurateDate);
        ending += ".";
        return ending;
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

    public static long timestamp () {
        Instant now = Instant.now ();
        long timestamp = now.toEpochMilli () / 1000;
        Main.debug (timestamp);
        return timestamp;
    }

    public enum TimestampFormat {
        accurateDateBasicTime,
        accurateDate,
        numberDate,
        specificDateBasicTime,
        relative,
        accurateTime,
        basicTime
    }

    public static String timestampSuffix (TimestampFormat timestampFormat) {
        switch (timestampFormat) {
            case accurateDate:
                return "D";
            case accurateDateBasicTime:
                return "f";
            case numberDate:
                return "d";
            case specificDateBasicTime:
                return "F";
            case relative:
                return "R";
            case accurateTime:
                return "T";
            case basicTime:
                return "t";
        }
        return "";
    }

    public static String timestamp (TimestampFormat timestampFormat) {
        String timestamp = "<t:";
        timestamp += timestamp ();
        String timestampSuffix = ":" + timestampSuffix (timestampFormat);
        if (timestampSuffix.length () == 1) {
            timestampSuffix = "";
        }
        timestamp += timestampSuffix;
        timestamp += ">";
        return timestamp;
    }


}