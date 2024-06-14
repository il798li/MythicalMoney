package MythicalMoney.Listeners;

import MythicalMoney.Commands.Economy.Land;
import MythicalMoney.Main;
import net.dv8tion.jda.api.events.interaction.component.SelectMenuInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.json.JSONObject;

public class StringSelectInteraction extends ListenerAdapter {

    public void onSelectMenuInteraction (SelectMenuInteractionEvent selectMenuInteractionEvent) {
        final String customID = selectMenuInteractionEvent.getComponentId ();
        final JSONObject identifierJSONObject = new JSONObject (customID);
        final String command = identifierJSONObject.getString ("command");
        switch (command) {
            case "land": {
                Land.respond (selectMenuInteractionEvent);
            }
            default: {
                return;
            }
        }
    }
}
