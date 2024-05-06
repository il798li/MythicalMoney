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

    public int mm;
    public final long userID;
    public Inventory inventory;
    public LandSet landSet;
    public ToolSet toolSet;

    public Player (final int mm, final long userID, final Inventory inventory, final LandSet landSet, final ToolSet toolSet) {
        this.mm = mm;
        this.userID = userID;
        this.inventory = inventory;
        this.landSet = landSet;
        this.toolSet = toolSet;

        players.add (this);
    }

    public Player (long userID) {
        this (0, userID, new Inventory (), new LandSet (), new ToolSet ());
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

    public static void load () {
        JSONObject profiles = JSONUtility.loadProfiles ();
        Iterator <String> keys = profiles.keys ();

        keys.forEachRemaining ((String key) -> {
            final long userID = Long.parseLong (key);
            final JSONObject jsonObject = profiles.getJSONObject (key);
            fromJSON (jsonObject, userID);
        });
    }

    public static void save () {
        final JSONObject jsonObject = new JSONObject ();
        final int size = players.size ();
        for (int index = 0; index < size; index++) {
            final Player player = players.get (index);
            JSONObject playerJSON = player.toJSON ();
            jsonObject.put ("" + player.userID, playerJSON);
        }
        JSONUtility.save (jsonObject, JSONUtility.JSONFile.Profiles);
    }

    public static Player get (long userID) {
        final int size = players.size ();
        for (int index = 0; index < size; index++) {
            Player player = players.get (index);
            if (player.userID == userID) {
                return player;
            }
        }
        return new Player (userID);
    }

    public JSONObject toJSON () {
        JSONObject jsonObject = new JSONObject ();
        jsonObject.put ("coins", this.mm);
        jsonObject.put ("inventory", this.inventory.toJSON ());
        jsonObject.put ("land", this.landSet.toJSON ());
        jsonObject.put ("tools", ToolSet.toJSON (this.toolSet));
        return jsonObject;
    }
}
