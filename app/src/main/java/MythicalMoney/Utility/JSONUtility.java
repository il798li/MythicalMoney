package MythicalMoney.Utility;

import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import MythicalMoney.Main;

public class JSONUtility {

    public static JSONObject load (String fileNameTemplate) {
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
        return load ("Settings");
    }

    public static enum JSONFile {
        Blacklisted,
        Deletables,
        Profiles,
        Settings,
        Archive
    }

    public static boolean save (@NotNull JSONObject jsonObject, JSONFile jsonFile) {
        final String jsonString = jsonObject.toString (4);
        final String jsonFileString = "JSON\\" + jsonFile.toString () + ".json";
        final boolean success = FileUtility.writeFile (jsonString, jsonFileString);
        return success;
    }
}