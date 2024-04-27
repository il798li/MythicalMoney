package MythicalMoney.Data;

import java.util.ArrayList;
import java.util.Iterator;

import org.json.JSONObject;

import MythicalMoney.Main;
import MythicalMoney.Utility.JSONUtility;
import MythicalMoney.Utility.JSONUtility.JSONFile;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class Setting {
    public String guildID;
    public boolean compact;
    public String prefix;

    public static ArrayList <Setting> settings = new ArrayList <Setting> ();

    public Setting (String guildID, boolean compact, String prefix) {
        this.guildID = guildID;
        this.compact = compact;
        this.prefix = prefix;

        settings.add (this);
    }

    public Setting (Guild guild) {
        this (guild.getIdLong ());
    }
    
    public Setting (long guildID) {
        this ("" + guildID, true, "mm");
    }

    public static void load () {
        JSONObject settingsJSON = JSONUtility.loadSettings ();
        Iterator <String> keys = settingsJSON.keys ();

        while (keys.hasNext ()) {
            String guildID = keys.next ();
            JSONObject guildSettingsJSON = settingsJSON.getJSONObject (guildID);
            boolean compact = guildSettingsJSON.getBoolean ("compact");
            String prefix = guildSettingsJSON.getString ("prefix");
            new Setting (guildID, compact, prefix);
        }
    }

    public static void save () {
        JSONObject jsonObject = new JSONObject ();
        final int size = settings.size ();
        for (int index = 0; index < size; index++) {
            Setting setting = settings.get (index);
            JSONObject guildSettings = new JSONObject ();
            {
                guildSettings.put ("prefix", setting.prefix);
                guildSettings.put ("compact", setting.compact);
            }
            Main.debug (guildSettings.toString (4));
            jsonObject.put (setting.guildID, guildSettings);
        }
        JSONUtility.save(jsonObject, JSONFile.Settings);
    }

    public static Setting find (String guildID) {
        for (Setting setting : settings) {
            if (setting.guildID.equals (guildID)) {
                return setting;
            }
        }
        Setting setting = new Setting (Main.jda.getGuildById (guildID));
        return find (setting.guildID);
    }

    public static Setting find (Guild guild) {
        return find (guild.getId ());
    }

    public static Setting find (SlashCommandInteractionEvent slashCommandInteractionEvent) {
        return find (slashCommandInteractionEvent.getGuild ());
    }

    public String toString () {
        String string = "";
        {
            final String guildID = this.guildID;
            string += "Guild ID: ";
            string += guildID;
        }
        string += "\n";
        {
            final boolean compact = this.compact;
            string += "Compact: ";
            if (compact) {
                string += "true";
            } else {
                string += "false";
            }
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

