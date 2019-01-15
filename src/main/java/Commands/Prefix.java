package Commands;

import Commands.Parameters.Parameter;
import Commands.Parameters.ParameterType;
import Main.Main;
import Main.Strings;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class Prefix extends Command {

    public Prefix() {
        this.name = "prefix";
        this.description = "Command to change prefix";
        this.isParameter = true;
        this.parameters = new Parameter[]{new Parameter("New Prefix", ParameterType.free)};
    }

    @Override
    public void execute(MessageReceivedEvent event) {
        if (!validate(event.getMessage().getContentRaw()))
            return;
        String temp = Strings.prefix.getValue();
        Main.updateConfig("prefix", event.getMessage().getContentRaw().split(" ")[1]);
        event.getChannel().sendMessage("Successfully changed the prefix from " + temp + " to " + Strings.prefix.getValue()).queue();
    }
}
