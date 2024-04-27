package MythicalMoney.Data;

import org.json.JSONArray;
import MythicalMoney.Classes.Property;

public class Land {
    public Property [] properties;
    
    public static Land fromJSON (JSONArray jsonArray) {
        final int length = jsonArray.length ();
        Property [] properties = new Property [length];
        for (int index = 0; index < length; index++) {
            final String name = jsonArray.getString (index);
            final Property property = Property.get (name);
            properties [index] = property;
        }

        final Land land = new Land ();
        land.properties = properties;
        return land;
    }

    public static JSONArray toJSON (final Land land) {
        JSONArray jsonArray = new JSONArray(land.properties.length);
        for (int index = 0; index < land.properties.length; index++) {
            final Property property = land.properties [index];
            jsonArray.put (index, property.display.name);
        }
        return jsonArray;
    }

    private Land () {
        return;
    }
}
