package MythicalMoney;


import javax.security.auth.login.LoginException;

import MythicalMoney.Listeners.ReadyListener;
import MythicalMoney.Utility.FileUtility;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

public class Main {
    public String getGreeting () {
        return "Hello World!";
    }

    public static JDA jda;

    public static void main(String[] args) {
        debug ("Starting.h..");
        try {
            debug ("Trying to login...");
            jda = JDABuilder.createDefault (token ()).build ();
            System.out.println ("\tFinished logging in!");

        } catch (LoginException loginException) {
            System.out.println ("Could not login using token: \"" + token () + "\"");
            return;
        } catch (Exception exception) {
            exception.printStackTrace ();
            return;
        }
        jda.addEventListener (new ReadyListener ());
    }

    public static String token () {
        final String token = FileUtility.readFile ("Token.txt");
        if (token.contains ("\n")) {
            String [] lines = token.split ("\n");
            return lines [0];
        }
        return token;
    }

    public static void debug (final String debug) {
        System.out.println ("\tDebug: \"" + debug + "\"");
        final String test = "test success";
    }
    
    public static void debug (final int debug) {
        debug ("" + debug);
    }
}