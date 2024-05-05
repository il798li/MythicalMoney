package MythicalMoney.Utility;

import MythicalMoney.Classes.Helpers.Display;
import MythicalMoney.Data.Setting;
import MythicalMoney.Main;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.InteractionHook;
import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import net.dv8tion.jda.api.requests.restaction.WebhookMessageAction;

import java.awt.*;
import java.time.Instant;

public class DiscordUtility {

    public static final Color blurple = new Color (114, 137, 218);

    public static MessageEmbed embed (SlashCommandInteractionEvent slashCommandInteractionEvent, String[] names, String[] values) {
        EmbedBuilder embedBuilder = new EmbedBuilder ();
        String description = embedDescription (slashCommandInteractionEvent);

        Setting guildSetting = Setting.get (slashCommandInteractionEvent);

        if (guildSetting.compact == false) {
            embedBuilder.setTitle ("Mythical Money");
        }
        for (int index = 0; index < names.length && index < values.length; index += 1) {
            if (index == 0 && guildSetting.compact == true) {
                description += "**";
            } else {
                description += "\n\n**";
            }
            description += names[index];
            description += "**\n";
            description += values[index];
        }
        description += "\n" + embedEnding (slashCommandInteractionEvent);
        embedBuilder.setDescription (description);
        embedBuilder.setColor (blurple);
        MessageEmbed embed = embedBuilder.build ();
        return embed;
    }

    public static String embedDescription (SlashCommandInteractionEvent slashCommandInteractionEvent) {
        Guild guild = slashCommandInteractionEvent.getGuild ();
        if (guild == null) {
            return "_ _";
        }
        final Setting guildSettings = Setting.get (guild);
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
            final String formattedGuildName = cancelMarkdown (rawGuildName);
            description += formattedGuildName;
        }
        description += "**.";
        return description;
    }

    public static String embedEnding (SlashCommandInteractionEvent slashCommandInteractionEvent) {
        final Guild guild = slashCommandInteractionEvent.getGuild ();
        final Setting setting = Setting.get (guild);
        if (setting.compact) {
            return "";
        }

        String ending = "\nThis embed was sent ";
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
        final char[] originalString = string.toCharArray ();
        for (char character : originalString) {
            boolean isSymbol = symbols.contains ("" + character);
            if (isSymbol) {
                newString += "\\";
            }
            newString += character;
        }
        return newString;
    }

    public static long timestamp () {
        Instant now = Instant.now ();
        final long timestampMilli = now.toEpochMilli ();
        final long timestamp = timestampMilli / 1000;
        return timestamp;
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

    public static String timestamp (TimestampFormat timestampFormat, long timestamp) {
        String timestampString = "<t:";
        String timestampSuffix = ":" + timestampSuffix (timestampFormat);
        if (timestampSuffix.length () == 1) {
            timestampSuffix = "";
        }
        timestampString += timestamp;
        timestampString += timestampSuffix;
        timestampString += ">";
        return timestampString;
    }

    public static String timestamp (TimestampFormat timestampFormat) {
        long timestamp = timestamp ();
        String timestampDisplay = timestamp (timestampFormat, timestamp);
        return timestampDisplay;
    }

    public static void deletable (SlashCommandInteractionEvent slashCommandInteractionEvent, Display[] displays, boolean deletable) {
        MessageBuilder messageBuilder = new MessageBuilder ();
        {
            String[] names = new String[displays.length];
            String[] values = new String[displays.length];
            for (int index = 0; index < displays.length; index++) {
                names[index] = displays[index].single;
                values[index] = displays[index].plural;
            }
            MessageEmbed embed = embed (slashCommandInteractionEvent, names, values);
            messageBuilder.setEmbeds (embed);
        }
        if (deletable) {
            Display display = new Display ("Delete", "delete");
            Button delete = display.button ();
            ActionRow actionRow = ActionRow.of (delete);
            messageBuilder.setActionRows (actionRow);
        }
        InteractionHook interactionHook = slashCommandInteractionEvent.getHook ();
        Message message = messageBuilder.build ();
        WebhookMessageAction <Message> messageAction = interactionHook.sendMessage (message);
        messageAction.queue ();
    }

    public static String display (final long userID, SlashCommandInteractionEvent slashCommandInteractionEvent) {
        JDA jda = slashCommandInteractionEvent.getJDA ();
        Guild guild = slashCommandInteractionEvent.getGuild ();
        final String display = display (userID, jda, guild);
        return display;
    }

    public static String display (final long userID, JDA jda, Guild guild) {
        User user = jda.getUserById (userID);
        if (user == null) {
            Main.debug (userID + " is a null user.");
            return "UNKNOWN_NAME";
        }

        if (guild == null) {
            final String name = "@" + user.getName ();
            return name;
        }

        Member member = guild.getMemberById (userID);
        if (member == null) {
            final String name = "@" + user.getName ();
            return name;
        }

        return user.getAsMention ();
    }

    public static String hyperlink (String display, String link) {
        return "[" + display + "](" + link + ")";
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
}