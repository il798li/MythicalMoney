package MythicalMoney.Utility;

import org.json.JSONObject;

public class JSONUtility {
    public static enum Files {
        Blacklisted,
        Deletables,
        Profiles,
        Settings
    };

    public static JSONObject load (Files fileNameTemplate) {
        String fileName = "JSON\\" + fileNameTemplate.toString () + ".json";
        String jsonData = FileUtility.readFile (fileName);
        JSONObject jsonObject = new JSONObject (jsonData);
        return jsonObject;
    }
}
