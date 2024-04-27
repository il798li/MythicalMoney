package MythicalMoney.Data.Helpers;

import java.util.ArrayList;

import org.json.JSONArray;
import MythicalMoney.Classes.Property;

public class Land {
    public ArrayList <Property> properties;
    
    public static Land fromJSON (JSONArray jsonArray) {
        final int length = jsonArray.length ();
        ArrayList <Property> properties = new ArrayList <Property> ();
        for (int index = 0; index < length; index++) {
            final String name = jsonArray.getString (index);
            final Property property = Property.get (name);
            properties.add (property);
        }

        final Land land = new Land ();
        land.properties = properties;
        return land;
    }

    public static JSONArray toJSON (final Land land) {
        final int size = land.properties.size ();
        JSONArray jsonArray = new JSONArray(size);
        for (int index = 0; index < size; index++) {
            final Property property = land.properties.get (index);
            jsonArray.put (index, property.display.plural);
        }
        return jsonArray;
    }

    public JSONArray toJSON () {
        JSONArray jsonArray = toJSON (this);
        return jsonArray;
    }

    public Land () {
        return;
    }
}
