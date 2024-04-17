/*
 * This Java source file was generated by the Gradle 'init' task.
 */
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
        debug ("Mythical Money is loading...");
        
        String token = "UNKNOWN_TOKEN";

        try {
            debug ("Trying to login...");

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
            Setting.setup ();
        }

        jda.addEventListener (new Ready ());
        jda.addEventListener (new SlashCommand ());
    }

    public static String token () {
        final String token = FileUtility.readFile ("Token.txt");
        if (token.contains ("\n")) {
            return token.substring (0, token.indexOf ("\n"));
        }
        return token;
    }

    public static void debug (final String debug) {
        final String startingString = "\t[Debug] ";

        String formattedDebug = startingString;
        for (int index = 0; index < debug.length (); index++) {
            String character = debug.charAt (index) + "";
            if (character.equals ("\n")) {
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
}
