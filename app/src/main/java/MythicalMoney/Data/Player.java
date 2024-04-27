package MythicalMoney.Data;

import java.util.ArrayList;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONObject;

import MythicalMoney.Data.Helpers.Inventory;
import MythicalMoney.Data.Helpers.Land;
import MythicalMoney.Data.Helpers.ToolSet;
import MythicalMoney.Utility.JSONUtility;

public class Player {
    public static ArrayList <Player> players = new ArrayList <Player> ();

    public int mm;
    public long userID;
    public Inventory inventory;
    public Land land;
    public ToolSet toolSet;

    public static Player fromJSON (JSONObject jsonObject, final long userID) {
        final Player player = new Player (userID);
        {
            player.mm = jsonObject.getInt("mm");
        }
        {
            JSONObject inventoryJSON = jsonObject.getJSONObject ("inventory");
            player.inventory = Inventory.fromJSON (inventoryJSON);
        }
        {
            JSONArray landJSON = jsonObject.getJSONArray("land");
            player.land = Land.fromJSON(landJSON);
        }
        {
            JSONObject toolSetJSON = jsonObject.getJSONObject("tools");
            player.toolSet = ToolSet.fromJSON (toolSetJSON);
        }
        return player;
    }

    public JSONObject toJSON () {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put ("coins", mm);
        jsonObject.put ("inventory", inventory.toJSON());
        jsonObject.put ("land", land.toJSON());
        jsonObject.put ("tools",
        return jsonObject;
    }

    public Player (final int mm, final long userID, final Inventory inventory, final Land land, final ToolSet toolSet) {
        this.mm = mm;
        this.userID = userID;
        this.inventory = inventory;
        this.land = land;
        this.toolSet = toolSet;

        players.add (this);
    }

    public Player (long userID) {
        this (0, userID, new Inventory(), new Land (), new ToolSet());
    }

    public static void load () {
        JSONObject profiles = JSONUtility.loadProfiles ();
        Iterator <String> keys = profiles.keys();

        keys.forEachRemaining ( (String key) -> {
            final long userID = Long.parseLong (key);
            final JSONObject jsonObject = profiles.getJSONObject(key);
            fromJSON(jsonObject, userID);
        });
    }

    public static void save () {
        
    }
}
