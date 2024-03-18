package MythicalMoney.Utility;

import org.json.JSONObject;

public class JSONUtility {

    private static JSONObject load (String fileNameTemplate) {
        final String fileName = "JSON\\" + fileNameTemplate.toString () + ".json";
        final String jsonData = FileUtility.readFile (fileName);
        final JSONObject jsonObject = new JSONObject (jsonData);
        return jsonObject;
    }

    public static JSONObject loadBlacklisted () {
        return load ("Blacklisted");
    }

    public static JSONObject loadDeletables () {
        return load ("Deletables");
    }

    public static JSONObject loadProfiles () {
        return load ("Profiles");
    }

    public static JSONObject loadSettings () {
        return load ("Settngs");
    }
}