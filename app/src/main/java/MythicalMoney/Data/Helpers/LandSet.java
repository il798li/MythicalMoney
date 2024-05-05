package MythicalMoney.Data.Helpers;

import java.util.ArrayList;

import org.json.JSONArray;
import MythicalMoney.Classes.Land;
import org.json.JSONObject;

public class LandSet {
    public Land farm;
    public Land forest;
    public Land mine;
    public Land huntingGround;
    
    public static LandSet fromJSON (JSONObject jsonObject) {
        final LandSet landSet = new LandSet();
        {
            final String farm = jsonObject.getString("farm");
            landSet.farm = Land.get(farm);
        }
        {
            final String mine = jsonObject.getString ("mine");
            landSet.mine = Land.get (mine);
        }
        {
            final String forest = jsonObject.getString("forest");
            landSet.forest = Land.get(forest);
        }
        {
            final String huntingGround = jsonObject.getString("hunting ground");
            landSet.huntingGround = Land.get(huntingGround);
        }
        return landSet;
    }

    public static JSONObject toJSON (final LandSet landSet) {
        final JSONObject jsonObject = new JSONObject();
        jsonObject.put ("farm", landSet.farm.display.plural);
        jsonObject.put ("mine", landSet.mine.display.plural);
        jsonObject.put ("hunting ground", landSet.huntingGround.display.plural);
        jsonObject.put ("forest", landSet.forest.display.plural);
        return jsonObject;
    }

    public JSONObject toJSON () {
        return toJSON (this);
    }

    public LandSet() {
        this.farm = Land.starterFarm;
        this.forest = Land.starterForest;
        this.mine = Land.starterMine;
        this.huntingGround = Land.starterHuntingGround;
    }
}