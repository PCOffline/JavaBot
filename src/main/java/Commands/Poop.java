package Commands;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class Poop extends Command {


    @Override
    public void execute(MessageReceivedEvent event) {
        event.getChannel().sendMessage("poop").queue();
    }
}
