package Commands;

import Commands.Parameters.Parameter;
import Commands.Parameters.ParameterType;
import Main.Message;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.awt.*;
import java.util.Arrays;
import java.util.Collections;

/**
 *
 */

public class Scramble extends Command {

    /**
     * Setting up {@link Scramble Scramble} Arguments of Abstract Class {@link Command Command}
     */

    public Scramble() {
        this.name = "scramble";
        this.description = "Create two random teams";
        this.parameters = new Parameter[]{new Parameter("Players", ParameterType.free)};
        this.isParameter = true;
    }

    /**
     * Execution of Scramble Teams command
     * Output: Team 1 in Bold, Random Players, each listed with a • (should be approx. half of the amount of players given)
     * Team 2 in Bold, Random Players, same as Team 1.
     *
     * @param event {@link MessageReceivedEvent MessageReceivedEvent} of {@link Main.Main#onMessageReceived(MessageReceivedEvent)}
     */

    @Override
    public void execute(MessageReceivedEvent event) {
        if (!validate(event.getMessage().getContentRaw()))
            return;
        String[] msg = event.getMessage().getContentRaw().substring(event.getMessage().getContentRaw().indexOf(' ') + 1).split(" ");
        StringBuilder output = new StringBuilder("**Team 1**\n");

        // In case the command includes extra spaces
        Arrays.sort(msg);
        String[] tmp = new String[msg.length - Arrays.asList(msg).lastIndexOf("") - 1];
        for (int i = Arrays.asList(msg).lastIndexOf("") + 1; i < msg.length; i++) {
            tmp[i - Arrays.asList(msg).lastIndexOf("") - 1] = msg[i];
        }
        msg = tmp;

        Collections.shuffle(Arrays.asList(msg));
        String[] team1 = new String[msg.length / 2];
        String[] team2 = new String[msg.length - team1.length];

        System.arraycopy(msg, 0, team1, 0, team1.length);
        System.arraycopy(msg, team1.length, team2, 0, msg.length - team1.length);

        for (String player : team1)
            output.append("• ").append(player).append("\n");

        output.append("**Team 2**\n");
        for (String player : team2)
            output.append("• ").append(player).append("\n");


        event.getChannel().sendMessage(Message.wrapMessageInEmbed(
                output.toString(), null, "https://wp-time.com/wp-content/uploads/2015/09/wordpress-random-banners-icon.png", "Random Teams", Color.CYAN
        )).queue();
    }

}
