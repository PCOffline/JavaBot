package Commands;

import Commands.Parameters.Parameter;
import Commands.Parameters.ParameterType;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.util.Arrays;
import java.util.Random;

public class Cookie extends Command {

    public Cookie() {
        super();
        this.name = "cookie";
        this.description = "Give a cookie to a random or specific person";
        this.parameters = new Parameter[]{new Parameter("member", ParameterType.free)};
        this.isParameter = true;
    }

    @Override
    public boolean validate(String message) {
        if (this.name == null || message == null)
            return false;
        return message.split(" ")[0].equalsIgnoreCase(this.name);
    }

    @Override
    public void execute(MessageReceivedEvent event) {
        if (!validate(event.getMessage().getContentRaw()))
            return;

        String[] str = event.getMessage().getContentRaw().split(" ");
        Arrays.sort(str);
        int count = 0;

        for (String s : str) {
            if (s == null || s.equals("")) {
                count++;
            }
        }

        if (str.length > 1)
            event.getMessage().getChannel().sendMessage("Hey, " + str[count] + "! " + event.getAuthor().getAsMention() + " gave you a cookie! \uD83C\uDF6A").queue();
        else {
            String random = event.getGuild().getMembers().get(new Random().nextInt(event.getGuild().getMembers().size())).getAsMention();
            event.getMessage().getChannel().sendMessage("Hey, " + random + "! you have been randomly awarded with a cookie thanks to " + event.getAuthor().getAsMention() + "\uD83C\uDF6A").queue();
        }

    }

}
