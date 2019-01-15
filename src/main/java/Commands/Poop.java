package Commands;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class Poop extends Command {

    public Poop() {
        super();
        this.name = "poop";
        this.description = "rolands wants some poop";
        this.parameters = null;
        this.isParameter = false;
    }

    @Override
    public void execute(MessageReceivedEvent event) {
        if (!validate(event.getMessage().getContentRaw()))
            return;
        event.getChannel().sendMessage("poop").queue();
    }
}