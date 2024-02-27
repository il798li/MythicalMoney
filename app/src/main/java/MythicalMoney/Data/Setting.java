package MythicalMoney.Data;

import java.util.ArrayList;
import java.util.Iterator;

import org.json.JSONObject;

import MythicalMoney.Main;
import MythicalMoney.Utility.JSONUtility;
import net.dv8tion.jda.api.entities.Guild;

public class Setting {
    public String guildID;
    public boolean compact;
    public String prefix;

    public static ArrayList <Setting> settings = new ArrayList <Setting> ();

    public Setting (String guildID, boolean compact, String prefix) {
        this.guildID = guildID;
        this.compact = compact;
        this.prefix = prefix;
    }

    public Setting (Guild guild) {
        this.guildID = guild.getId ();
        this.compact = true;
        this.prefix = "mm";
    }

    public Guild guild () {
        return Main.jda.getGuildById (this.guildID);
    }

    public static void load () {
        JSONObject settingsJSON = JSONUtility.load (JSONUtility.Files.Settings);
        Iterator <String> keys = settingsJSON.keys ();

        while (keys.hasNext ()) {
            String guildID = keys.next ();
            JSONObject guildSettingsJSON = settingsJSON.getJSONObject (guildID);
            boolean compact = guildSettingsJSON.getBoolean ("compact");
            String prefix = guildSettingsJSON.getString ("prefix");
            Setting setting = new Setting (guildID, compact, prefix);
            settings.add (setting);
        }
    }
}
