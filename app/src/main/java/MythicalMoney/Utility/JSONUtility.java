package MythicalMoney.Utility;

import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

public class JSONUtility {

    public static JSONObject load (String fileNameTemplate) {
        final String fileName = "JSON\\" + fileNameTemplate + ".json";
        final String jsonData = FileUtility.readFile (fileName);
        return new JSONObject (jsonData);
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
        return load ("Settings");
    }

    public static void save (@NotNull JSONObject jsonObject, JSONFile jsonFile) {
        final String jsonString = jsonObject.toString (4);
        final String jsonFileString = "JSON\\" + jsonFile.toString () + ".json";
        FileUtility.writeFile (jsonString, jsonFileString);
    }

    public enum JSONFile {
        Blacklisted,
        Deletables,
        Profiles,
        Settings
    }
}