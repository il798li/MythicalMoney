package MythicalMoney;

import java.util.EnumSet;

import javax.security.auth.login.LoginException;

import MythicalMoney.Data.Setting;
import MythicalMoney.Listeners.Ready;
import MythicalMoney.Listeners.SlashCommand;
import MythicalMoney.Utility.FileUtility;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;

public class Main {
    public String getGreeting() {
        return "\nHello World!\n\t- Main.getGreeting";
    }

    public static JDA jda;

    public static void main(String[] args) {
        for (int loop = 0; loop < 100; loop++) {
            debug ();
        }
        debug ("Mythical Money is loading...");
        
        String token = "UNKNOWN_TOKEN";

        try {
            token = token ();
            final EnumSet <GatewayIntent> intents = GatewayIntent.getIntents (GatewayIntent.ALL_INTENTS);

            JDABuilder jdaBuilder = JDABuilder.create (token, intents);
            jda = jdaBuilder.build ();
        } catch (LoginException loginException) {
            debug ("Could not login using this token: \"" + token + "\"");
            return;
        } catch (Exception exception) {
            debug ("An unknown error occured.");
            exception.printStackTrace ();
        }

        debug ("Finished logging in!");

        {
            Setting.load ();
        }
        {
            {
                Ready ready = new Ready();
                jda.addEventListener(ready);
            }
            {
                SlashCommand slashCommand = new SlashCommand ();
                jda.addEventListener (slashCommand);
            }
        }
    }

    public static String token () {
        final String token = FileUtility.readFile ("Token.txt");
        if (token.contains ("\n")) {
            final int index = token.indexOf ("\n");
            return token.substring (0, index);
        }
        return token;
    }

    public static void debug (final String debug) {
        final String startingString = "\t[Debug] ";

        String formattedDebug = startingString;
        final int length = debug.length ();
        for (int index = 0; index < length; index++) {
            String character = debug.charAt (index) + "";
            boolean newLine = character.equals ("\n");
            if (newLine) {
                character += startingString;
            }
            formattedDebug += character;
        }
        System.out.println (formattedDebug);
    }
    
    public static void debug (final int debug) {
        debug ("" + debug);
    }

    public static void debug (final long debug) {
        debug ("" + debug);
    }

    public static void debug () {
        System.out.println ("\n");
    }
}
