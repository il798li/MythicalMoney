package MythicalMoney.Listeners;

import MythicalMoney.Commands.Economy.Information.Land;
import net.dv8tion.jda.api.events.interaction.component.StringSelectInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.json.JSONObject;

public class StringSelectInteraction extends ListenerAdapter {

    public void onSelectMenuInteraction (StringSelectInteractionEvent stringSelectInteractionEvent) {
        final String customID = stringSelectInteractionEvent.getComponentId ();
        final JSONObject identifierJSONObject = new JSONObject (customID);
        final String command = identifierJSONObject.getString ("command");
        switch (command) {
            case "land": {
                Land.respond (stringSelectInteractionEvent);
            }
            default: {
            }
        }
    }
}
