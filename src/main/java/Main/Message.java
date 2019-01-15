package Main;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.MessageEmbed;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.awt.*;

/**
 * Class to be used as a tool to shorten processes such as comparing and generating messages
 */

public class Message {

    /**
     * Checks if a {@link MessageReceivedEvent MessageReceivedEvent}'s content is equal to str
     *
     * @param event      a {@link MessageReceivedEvent MessageReceivedEvent} the bot gets when a message is received
     * @param str        the string to compare to the message received
     * @param ignoreCase should the method ignore case or not
     * @return whether the message is equal to the string or not
     */

    public static boolean checkMessage(MessageReceivedEvent event, String str, boolean ignoreCase) {
        return ignoreCase ? event.getMessage().getContentRaw().equalsIgnoreCase(str) : event.getMessage().getContentRaw().equals(str);
    }

    /**
     * Send a private message to a user
     *
     * @param user    {@link User User} to send private message to
     * @param message Content of the private message to send to the user
     */

    static void sendPrivateMessage(User user, MessageEmbed message) {
        user.openPrivateChannel().queue(privateChannel -> privateChannel.sendMessage(message).queue());
    }

    /**
     * Send a private message to a user
     *
     * @param user    {@link User User} to send private message to
     * @param message Content of the private message to send to the user
     */

    static void sendPrivateMessage(User user, String message) {
        user.openPrivateChannel().queue(privateChannel -> privateChannel.sendMessage(message).queue());
    }

    /**
     * Create an embedded message
     *
     * @param content   Content of the message
     * @param footer    Footer of the message
     * @param footerURL URL of footer icon of the message
     * @param title     Title of the message
     * @param color     Color of the message
     * @return Embedded message
     */

    public static MessageEmbed wrapMessageInEmbed(String content, String footer, String footerURL, String title,
                                                  Color color) {
        EmbedBuilder eb = new EmbedBuilder();
        eb.setAuthor(Strings.BOT_NAME);
        eb.setFooter(footer, footerURL);
        eb.setColor(color);
        eb.setDescription(content);
        eb.setTitle(title);
        return eb.build();
    }
}
