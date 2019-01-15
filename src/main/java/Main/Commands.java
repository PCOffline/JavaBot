package Main;

import Commands.*;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

/**
 * Class that executes all commands
 */

class Commands {

    private static Prefix prefix = new Prefix();
    private static Scramble scramble = new Scramble();
    private static Cookie cookie = new Cookie();
    private static Evil evil = new Evil();
    private static AutoMove autoMove = new AutoMove();
    private static Archive archive = new Archive();

    static void execute(MessageReceivedEvent event) {
        prefix.execute(event);
        scramble.execute(event);
        cookie.execute(event);
        evil.execute(event);
        autoMove.execute(event);
        archive.execute(event);

    }
}
