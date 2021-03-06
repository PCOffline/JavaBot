package Main;

import Exceptions.KeywordNotFoundException;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import javax.security.auth.login.LoginException;
import java.lang.reflect.Field;

/**
 * The main class, all main processes are being executed here.
 */

public class Main extends ListenerAdapter {


    /**
     * Main method - first method to execute. Builds a {@link JDA} to connect the bot to Discord
     *
     * @param args args of main method (not used)
     * @throws LoginException if token is invalid
     */

    public static void main(String[] args) throws LoginException {

        JDA jda = new JDABuilder(Strings.token).build();
        jda.addEventListener(new Main());
    }

    /**
     * Updates configuration of Bot
     *
     * @param argument Argument to update
     * @param value    New value
     */

    public static void updateConfig(String argument, String value) {
        if (Memory.argumentEmpty(argument))
            Memory.addArgument(argument, value);
        else
            try {
                Memory.editArgument(argument, value, true);
            } catch (KeywordNotFoundException e) {
                Memory.addArgument(argument, value);
            }

        try {
            Field field = Strings.class.getField(argument);
            field.set(argument, new Strings.Argument(argument, value));
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * Executes when a message on any guild is received
     *
     * @param event a {@link MessageReceivedEvent MessageReceivedEvent} of the message received
     */

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        Commands.execute(event);
    }
}