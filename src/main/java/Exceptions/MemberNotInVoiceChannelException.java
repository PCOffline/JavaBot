package Exceptions;

import net.dv8tion.jda.core.entities.User;

/**
 * Exception to be thrown when a voice-related action is being performed on a {@link User User} that is not in a voice channel.
 */

public class MemberNotInVoiceChannelException extends RuntimeException {
    public MemberNotInVoiceChannelException(String message) {
        super(message);
    }
}
