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
        StringBuilder description = new StringBuilder (embedDescription (slashCommandInteractionEvent));

        Setting guildSetting = Setting.get (slashCommandInteractionEvent);

        if (!guildSetting.compact) {
            embedBuilder.setTitle ("Mythical Money");
        }
        for (int index = 0; index < names.length && index < values.length; index += 1) {
            if (index == 0 && guildSetting.compact) {
                description.append ("**");
            } else {
                description.append ("\n\n**");
            }
            description.append (names[index]);
            description.append ("**\n");
            description.append (values[index]);
        }
        description.append ("\n").append (embedEnding (slashCommandInteractionEvent));
        embedBuilder.setDescription (description.toString ());
        embedBuilder.setColor (blurple);
        return embedBuilder.build ();
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
        StringBuilder newString = new StringBuilder ();
        final String symbols = "`-=~!@#$%^&*()_+[]\\{}|:;'\",./<>?";
        final char[] originalString = string.toCharArray ();
        for (char character : originalString) {
            boolean isSymbol = symbols.contains ("" + character);
            if (isSymbol) {
                newString.append ("\\");
            }
            newString.append (character);
        }
        return newString.toString ();
    }

    public static long timestamp () {
        Instant now = Instant.now ();
        final long timestampMilli = now.toEpochMilli ();
        return timestampMilli / 1000;
    }

    public static String timestampSuffix (TimestampFormat timestampFormat) {
        return switch (timestampFormat) {
            case accurateDate -> "D";
            case accurateDateBasicTime -> "f";
            case numberDate -> "d";
            case specificDateBasicTime -> "F";
            case relative -> "R";
            case accurateTime -> "T";
            case basicTime -> "t";
        };
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
        return timestamp (timestampFormat, timestamp);
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
        return display (userID, jda, guild);
    }

    public static String display (final long userID, JDA jda, Guild guild) {
        User user = jda.getUserById (userID);
        if (user == null) {
            Main.debug (userID + " is a null user.");
            return "UNKNOWN_NAME";
        }

        if (guild == null) {
            return "@" + user.getName ();
        }

        Member member = guild.getMemberById (userID);
        if (member == null) {
            return "@" + user.getName ();
        }

        return user.getAsMention ();
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