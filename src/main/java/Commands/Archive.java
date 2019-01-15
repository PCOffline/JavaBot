package Commands;

/**
 * Grants a role that allows access to the Archives
 */

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class Archive extends Command {

    public Archive() {
        this.name = "archive";
        this.description = "Access to Archive categories for 15 minutes";
        this.parameters = null;
        this.isParameter = false;
    }

    @Override
    public void execute(MessageReceivedEvent event) {
        if (!validate(event.getMessage().getContentRaw()))
            return;

        if (event.getMember().getRoles().contains(event.getGuild().getRolesByName("Archive Access", true).get(0))) {
            event.getGuild().getController().removeRolesFromMember(event.getMember(), event.getGuild().getRolesByName("Archive Access", true).get(0)).queue();
            event.getChannel().sendMessage(event.getMember().getAsMention() + " the Archive Access role has been removed! You can no longer read messages in the archived categories!").queue();
        } else {
            event.getGuild().getController().addRolesToMember(event.getMember(), event.getGuild().getRolesByName("Archive Access", true).get(0)).queue();
            event.getChannel().sendMessage(event.getMember().getAsMention() + " you have been given the Archive Access role! You can now read messages in the archived categories!").queue();
        }
    }
}
