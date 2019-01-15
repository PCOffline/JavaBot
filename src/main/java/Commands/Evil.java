package Commands;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class Evil extends Command {

    private final String[] sentences = new String[]{
            "youre mom gay",
            "you are so retarded that you're not on the Bad List just so Santa can gift you condoms so you won't populate",
            "commit pew pew",
            "you're so unimportant im not giving a single byte about your life",
            "what's your name? because if it was for me to decide, you'd be called job accident number 14",
            "youre so special that not only mommy calls u like that... games give u a special rank just so people will know how retarded you are",
            "im not even going to waste brain cells to think of a good roast, you dont deserve it",
            "liar liar you are a nigger and i hope you die from fire",
            "you are so bad at gaming that your computer should get a refund on you"
    };

    public Evil() {
        this.name = "evil";
        this.description = "Something evil";
        this.parameters = null;
        this.isParameter = false;
    }

    @Override
    public void execute(MessageReceivedEvent event) {
        if (!validate(event.getMessage().getContentRaw()))
            return;
        Collections.shuffle(Arrays.asList(sentences));
        event.getChannel().sendMessage("hey, " + event.getAuthor().getAsMention() + "! " + sentences[new Random().nextInt(sentences.length)]).queue();
    }

}
