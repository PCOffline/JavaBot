package Commands;

import Commands.Parameters.Parameter;
import Commands.Parameters.ParameterType;
import Main.Memory;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class Prefix extends Command {

    public Prefix() {
        this.name = "prefix";
        this.description = "Command to change prefix";
        this.isParameter = true;
        this.parameters = new Parameter[]{new Parameter("New Prefix", ParameterType.free)};
    }

    @Override
    public boolean validate(String message) {
        String[] msg = message.split(" ");
        if (msg.length != 2)
            return false;
        if (!msg[0].equalsIgnoreCase(name))
            return false;
        return !msg[1].isEmpty();
    }

    @Override
    public void execute(MessageReceivedEvent event) {
        if (validate(event.getMessage().getContentRaw()))
            Memory.editArgument("prefix", event.getMessage().getContentRaw().split(" ")[1], false);
    }
}
