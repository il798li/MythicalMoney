package MythicalMoney.Utility;

import MythicalMoney.Classes.Helpers.Display;
import MythicalMoney.Data.Setting;
import MythicalMoney.Main;
import net.dv8tion.jda.api.utils.messages.MessageCreateBuilder;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.InteractionHook;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import net.dv8tion.jda.api.requests.restaction.WebhookMessageAction;

import java.awt.*;
import java.time.Instant;

public class DiscordUtility {

    public static final Color blurple = new Color (114, 137, 218);

    public static void deletable (SlashCommandInteractionEvent slashCommandInteractionEvent, Display[] displays, boolean deletable) {
        MessageCreateBuilder messageBuilder = new MessageCreateBuilder ();
        {
            //MessageEmbed embed = embed (slashCommandInteractionEvent, displays);
            MessageEmbed embed = smartEmbed (slashCommandInteractionEvent, displays);
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

    public static MessageEmbed smartEmbed (final SlashCommandInteractionEvent slashCommandInteractionEvent, final Display[] displays) {
        Main.debug ("Displays length: " + displays.length);
        final EmbedBuilder embedBuilder = new EmbedBuilder ();
        Column column = Column.left;
        if (displays.length % 3 == 0) {
            for (int index = 0; index < displays.length; index++) {
                final Display display = displays[index];
                embedBuilder.addField (display.single, display.plural, true);
                column = nextColumn (column);
            }
        } else if (displays.length % 2 == 0) {
            for (final Display display : displays) {
                embedBuilder.addField (display.single, display.plural, true);
                if (column == Column.left) {
                    embedBuilder.addField ("", "", true);
                    column = nextColumn (column);
                }
                column = nextColumn (column);
            }
        } else {
            for (final Display display : displays) {
                embedBuilder.addField (display.single, display.plural, false);
            }
        }
        embedBuilder.setColor(blurple);
        final MessageEmbed messageEmbed = embedBuilder.build ();
        return messageEmbed;
    }

    public static int columns (Object[] objects) {
        for (int columns = 3; columns > 0; columns--) {
            if (objects.length % columns == 0) {
                return columns;
            }
        }
        return 1;
    }

    public enum Column {
        left,
        middle,
        right
    }

    public static Column nextColumn (final Column column) {
        switch (column) {
            case left: {
                return Column.middle;
            }
            case middle: {
                return Column.right;
            }
            case right: {
                return Column.left;
            }
        }
        return null;
    }

    public static MessageEmbed embed (final SlashCommandInteractionEvent slashCommandInteractionEvent, final Display[] displays) {
        final String embedDescription = embedDescription (slashCommandInteractionEvent);
        final StringBuilder description = new StringBuilder (embedDescription);
        Setting guildSetting = Setting.get (slashCommandInteractionEvent);
        for (int index = 0; index < displays.length; index += 1) {
            if (index == 0 && guildSetting.compact) {
                description.append ("**");
            } else {
                description.append ("\n\n**");
            }
            final Display display = displays[index];
            description.append (display.single);
            description.append ("**\n");
            description.append (display.plural);
        }
        {
            final String embedEnding = embedEnding (slashCommandInteractionEvent);
            description.append ("\n");
            description.append (embedEnding);
        }
        final EmbedBuilder embedBuilder = new EmbedBuilder ();
        if (!guildSetting.compact) {
            embedBuilder.setTitle ("Mythical Money");
        }
        embedBuilder.setDescription (description);
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

    public static String timestamp (TimestampFormat timestampFormat) {
        long timestamp = timestamp ();
        return timestamp (timestampFormat, timestamp);
    }

    public static long timestamp () {
        Instant now = Instant.now ();
        final long timestampMilli = now.toEpochMilli ();
        return timestampMilli / 1000;
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

    public static long userID (SlashCommandInteractionEvent slashCommandInteractionEvent) {
        User user = slashCommandInteractionEvent.getUser ();
        final OptionMapping optionMapping = slashCommandInteractionEvent.getOption ("user");
        if (optionMapping != null) {
            user = optionMapping.getAsUser ();
        }
        return user.getIdLong ();
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
