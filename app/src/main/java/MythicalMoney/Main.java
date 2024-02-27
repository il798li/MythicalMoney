/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package MythicalMoney;

import javax.security.auth.login.LoginException;

import MythicalMoney.Utility.FileUtility;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

public class Main {
    public static JDA jda;
    public String getGreeting() {
        return "Hello World!";
    }

    public static void main(String[] args) {
        System.out.println ("\"" + token () + "\"");
        try {
            jda = JDABuilder.createDefault( token ()).build();
        } catch (LoginException loginException) {
            System.out.println ("Could not login using token: \"" + token () + "\"");
        }
    }

    public static String token () {
        return FileUtility.readFile ("app\\src\\main\\java\\MythicalMoney\\Token.txt");
    }
}
