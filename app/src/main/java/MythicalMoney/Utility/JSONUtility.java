package MythicalMoney.Utility;

import org.json.JSONObject;

import MythicalMoney.Enums.JSONFiles;

public class JSONUtility {

    public static JSONObject load (JSONFiles fileNameTemplate) {
        final String fileName = "JSON\\" + fileNameTemplate.toString () + ".json";
        final String jsonData = FileUtility.readFile (fileName);
        final JSONObject jsonObject = new JSONObject (jsonData);
        return jsonObject;
    }
}
