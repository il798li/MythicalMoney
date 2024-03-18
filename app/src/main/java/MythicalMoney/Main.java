/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package MythicalMoney;

import javax.security.auth.login.LoginException;

import MythicalMoney.Listeners.ReadyListener;
import MythicalMoney.Utility.FileUtility;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

public class Main {
    public String getGreeting() {
        return "Hello World!\n\tFrom: Main.getGreeting()";
    }

    public static JDA jda;

    public static void main(String[] args) {
        debug ("New Program has started...");
        final String token = token ();
        try {
            debug ("Trying to login...");
            jda = JDABuilder.createDefault( token).build ();
            debug ("Finished logging in!");
        } catch (LoginException loginException) {
            debug ("Could not login using this token: \"" + token + "\"");
            return;
        } catch (Exception exception) {
            debug ("An unknown error occured.");
            exception.printStackTrace ();
        }

        jda.addEventListener( new ReadyListener ());
    }

    public static String token () {
        final String token = FileUtility.readFile ("Token.txt");
        if (token.contains ("\n")) {
            return token.substring (0, token.indexOf ("\n"));
        }
        return token;
    }

    public static void debug (final String debug) {
        System.out.println ("\tDebug: " + debug);
    }
    
    public static void debug (final int debug) {
        debug ("" + debug);
    }
}
