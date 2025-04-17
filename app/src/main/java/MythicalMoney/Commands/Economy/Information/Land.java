package MythicalMoney.Commands.Economy.Information;

import MythicalMoney.Classes.Helpers.Display;
import MythicalMoney.Classes.Obtainable;
import MythicalMoney.Data.Player;
import MythicalMoney.Utility.DiscordUtility;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.StringSelectInteractionEvent;
import net.dv8tion.jda.api.interactions.InteractionHook;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;
import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.interactions.components.selections.SelectMenu;
import net.dv8tion.jda.api.interactions.components.selections.SelectMenuInteraction;
import net.dv8tion.jda.api.interactions.components.selections.SelectOption;
import net.dv8tion.jda.api.interactions.components.selections.StringSelectMenu;
import net.dv8tion.jda.api.requests.restaction.WebhookMessageCreateAction;
import net.dv8tion.jda.api.utils.messages.MessageCreateBuilder;
import net.dv8tion.jda.api.utils.messages.MessageCreateData;
import org.json.JSONObject;

import java.util.List;
public class Land {
    public static final SlashCommandData slashCommandData = slashCommandData ();

    public static SlashCommandData slashCommandData () {
        final SlashCommandData slashCommandData = Commands.slash ("land", "Shows what Land is owned by a user.");
        slashCommandData.addOption (OptionType.USER, "user", "Shows what Land is owned by a user other than yourself.");
        return slashCommandData;
    }

    public static void execute (final SlashCommandInteractionEvent slashCommandInteractionEvent) {
        final long userID = DiscordUtility.userID (slashCommandInteractionEvent);
        final User author = slashCommandInteractionEvent.getUser ();
        final long authorID = author.getIdLong ();
        String starterString = "Your ";
        if (authorID != userID) {
            starterString = DiscordUtility.display (userID, slashCommandInteractionEvent);
            final boolean endsWithS = starterString.endsWith ("s") || starterString.endsWith ("S");
            starterString += "'";
            if (!endsWithS) {
                starterString += "s";
            }
            starterString += " ";
        }
        final Obtainable.ToolType[] toolTypes = Obtainable.ToolType.values ();
        final Display[] displays = new Display[toolTypes.length];
        final Player player = Player.get (userID);
        {
            {
                final Display huntingGroundDisplay = new Display ("Hunting Ground", starterString + "Hunting Ground is a " + player.landSet.huntingGround.display.single + ".");
                displays[0] = huntingGroundDisplay;
            }
            {
                final Display mineDisplay = new Display ("Mine", starterString + "Mine is a " + player.landSet.mine.display.single + ".");
                displays[1] = mineDisplay;
            }
            {
                final Display forestDisplay = new Display ("Forest", starterString + "Forest is a " + player.landSet.forest.display.single + ".");
                displays[2] = forestDisplay;
            }
            {
                final Display farmDisplay = new Display ("Farm", starterString + "Farm is a " + player.landSet.farm.display.single + ".");
                displays[3] = farmDisplay;
            }
        }
        final MessageEmbed messageEmbed = DiscordUtility.embed (slashCommandInteractionEvent, displays);
        final JSONObject selectMenuJSON = new JSONObject ();
        {
            selectMenuJSON.put ("command", "land");
            selectMenuJSON.put ("author", authorID);
            selectMenuJSON.put ("user", userID);
            selectMenuJSON.put ("starter", starterString);
        }
        final String selectMenuJSONString = selectMenuJSON.toString ();
        final StringSelectMenu.Builder selectMenuBuilder = StringSelectMenu.create (selectMenuJSONString);
        selectMenuBuilder.setPlaceholder ("Info");
        {
            selectMenuBuilder.addOption ("Hunting Ground", "hunting ground");
            selectMenuBuilder.addOption ("Mine", "mine");
            selectMenuBuilder.addOption ("Forest", "forest");
            selectMenuBuilder.addOption ("Farm", "farm");
        }
        final SelectMenu selectMenu = selectMenuBuilder.build ();
        final ActionRow actionRow = ActionRow.of (selectMenu);
        MessageCreateBuilder messageCreateBuilder = new MessageCreateBuilder ();
        messageCreateBuilder.addEmbeds (messageEmbed);
        messageCreateBuilder.addComponents (actionRow);
        InteractionHook interactionHook = slashCommandInteractionEvent.getHook ();
        MessageCreateData messageCreateData = messageCreateBuilder.build ();
        WebhookMessageCreateAction <Message> messageWebhookMessageAction = interactionHook.sendMessage (messageCreateData);
        messageWebhookMessageAction.queue ();
    }

    public static void respond (StringSelectInteractionEvent stringSelectInteractionEvent) {
        final String customID = stringSelectInteractionEvent.getComponentId ();
        final SelectMenuInteraction selectMenuInteraction = stringSelectInteractionEvent.getInteraction ();
        final List <SelectOption> selectedOptionsList = stringSelectInteractionEvent.getSelectedOptions ();
        final SelectOption selectOption = selectedOptionsList.get (0);
        final String selectOptionValue = selectOption.getValue ();
        final JSONObject jsonObject = new JSONObject (customID);
        final long userID = jsonObject.getLong ("user");
        final Player player = MythicalMoney.Data.Player.get (userID);
        final MythicalMoney.Classes.Land land = player.landSet.get (selectOptionValue);
        final String verbPastTense = land.verbPastTense ();
        final Display[] displays = new Display[2];
        final String starterString = jsonObject.getString ("starter");
    }
}
