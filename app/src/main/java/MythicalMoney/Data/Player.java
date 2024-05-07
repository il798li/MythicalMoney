package MythicalMoney.Data;

import MythicalMoney.Data.Helpers.Inventory;
import MythicalMoney.Data.Helpers.LandSet;
import MythicalMoney.Data.Helpers.ToolSet;
import MythicalMoney.Utility.JSONUtility;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

public class Player {

    public static final ArrayList <Player> players = new ArrayList <Player> ();
    public final long userID;
    public int mm;
    public Inventory inventory;
    public LandSet landSet;
    public ToolSet toolSet;

    public Player (long userID) {
        this (0, userID, new Inventory (), new LandSet (), new ToolSet ());
    }

    public Player (final int mm, final long userID, final Inventory inventory, final LandSet landSet, final ToolSet toolSet) {
        this.mm = mm;
        this.userID = userID;
        this.inventory = inventory;
        this.landSet = landSet;
        this.toolSet = toolSet;
        players.add (this);
    }

    public static void load () {
        JSONObject profiles = JSONUtility.load (JSONUtility.JSONFile.Profiles);
        Iterator <String> keys = profiles.keys ();
        while (keys.hasNext ()) {
            final String key = keys.next ();
            final long userID = Long.parseLong (key);
            final JSONObject jsonObject = profiles.getJSONObject (key);
            fromJSON (jsonObject, userID);
        }
    }

    public static void fromJSON (JSONObject jsonObject, final long userID) {
        final Player player = new Player (userID);
        {
            player.mm = jsonObject.getInt ("coins");
        }
        {
            JSONObject inventoryJSON = jsonObject.getJSONObject ("inventory");
            player.inventory = Inventory.fromJSON (inventoryJSON);
        }
        {
            JSONObject landJSON = jsonObject.getJSONObject ("land");
            player.landSet = LandSet.fromJSON (landJSON);
        }
        {
            JSONObject toolSetJSON = jsonObject.getJSONObject ("tools");
            player.toolSet = ToolSet.fromJSON (toolSetJSON);
        }
    }

    public static void save () {
        final JSONObject jsonObject = new JSONObject ();
        for (final Player player : players) {
            JSONObject playerJSON = player.toJSON ();
            jsonObject.put ("" + player.userID, playerJSON);
        }
        JSONUtility.save (jsonObject, JSONUtility.JSONFile.Profiles);
    }

    public static Player get (long userID) {
        final int size = players.size ();
        for (Player player : players) {
            if (player.userID == userID) {
                return player;
            }
        }
        return new Player (userID);
    }

    public JSONObject toJSON () {
        final JSONObject playerJSON = new JSONObject ();
        {
            playerJSON.put ("coins", this.mm);
        }
        {
            final JSONObject inventoryJSON = this.inventory.toJSON ();
            playerJSON.put ("inventory", inventoryJSON);
        }
        {
            final JSONObject landSetJSON = this.landSet.toJSON ();
            playerJSON.put ("land", landSetJSON);
        }
        {
            final JSONObject toolSetJSON = this.toolSet.toJSON ();
            playerJSON.put ("tools", toolSetJSON);
        }
        return playerJSON;
    }
}
