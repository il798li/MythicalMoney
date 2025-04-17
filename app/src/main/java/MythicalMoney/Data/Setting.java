package MythicalMoney.Data;

import MythicalMoney.Utility.JSONUtility;
import MythicalMoney.Utility.JSONUtility.JSONFile;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

public class Setting {

    public static final ArrayList <Setting> settings = new ArrayList <Setting> ();
    public final long guildID;
    public final String prefix;
    public boolean compact;

    public Setting (long guildID) {
        this (guildID, true, "mm");
    }

    public Setting (final long guildID, final boolean compact, String prefix) {
        this.guildID = guildID;
        this.compact = compact;
        this.prefix = prefix;
        settings.add (this);
    }

    public Setting () {
        this.guildID = 0;
        this.compact = true;
        this.prefix = "mm";
    }

    public static void load () {
        final JSONObject settingsJSON = JSONUtility.load (JSONFile.Settings);
        final Iterator <String> keys = settingsJSON.keys ();
        while (keys.hasNext ()) {
            final String guildID = keys.next ();
            final long guildIDLong = Long.parseLong (guildID);
            final JSONObject guildSettingsJSON = settingsJSON.getJSONObject (guildID);
            final boolean compact = guildSettingsJSON.getBoolean ("compact");
            final String prefix = guildSettingsJSON.getString ("prefix");
            new Setting (guildIDLong, compact, prefix);
        }
    }

    public static void save () {
        JSONObject jsonObject = new JSONObject ();
        for (final Setting setting : settings) {
            final JSONObject guildSettings = new JSONObject ();
            {
                guildSettings.put ("prefix", setting.prefix);
                guildSettings.put ("compact", setting.compact);
            }
            jsonObject.put ("" + setting.guildID, guildSettings);
        }
        JSONUtility.save (jsonObject, JSONFile.Settings);
    }

    public static Setting get (final long guildID) {
        final String guildIDString = "" + guildID;
        for (Setting setting : settings) {
            if (setting.guildID == guildID) {
                return setting;
            }
        }
        return get (guildID);
    }

    public static Setting get (final Guild guild) {
        if (guild == null) {
            return new Setting ();
        }
        final long guildIDLong = guild.getIdLong ();
        return get (guildIDLong);
    }

    public static Setting get (SlashCommandInteractionEvent slashCommandInteractionEvent) {
        final Guild guild = slashCommandInteractionEvent.getGuild ();
        return get (guild);
    }

    public String toString () {
        String string = "";
        {
            final String guildID = "" + this.guildID;
            string += "Guild ID: ";
            string += guildID;
        }
        string += "\n";
        {
            string += "Compact: ";
            string += this.compact;
        }
        string += "\n";
        {
            final String prefix = this.prefix;
            string += "Prefix: \"";
            string += prefix;
            string += "\"";
        }
        return string;
    }
}

