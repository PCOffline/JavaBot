package Commands;

import Exceptions.MemberNotInVoiceChannelException;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.util.Objects;

public class AutoMove extends Command {

    private final String csgo = "515560050002100239";
    private final String rainbow = "515544981273051156";
    private final String hng = "518186174100144128";
    private final String other = "518186287333638204";

    private final String rainbow_ranked = "515545119592546324";
    private final String rainbow_casual = "515545010930974746";
    private final String rainbow_custom = "515545209468092432";

    public AutoMove() {
        this.name = "automove";
        this.description = "Automatically move players between voice channels depending on their current game";
        this.parameters = null;
        this.isParameter = false;
    }

    @Override
    public void execute(MessageReceivedEvent event) {
        if (!validate(event.getMessage().getContentRaw()))
            return;
        Member member = event.getMember();

        if (member.getGame() == null)
            return;

        try {
            System.out.println("SUCCESS");
            event.getGuild().getController().moveVoiceMember(event.getMember(), event.getGuild().getVoiceChannelById(gameToVoice(event.getMember()))).queue();
            System.out.println(member.getGame() + " " + member.getNickname() + " " + gameToVoice(event.getMember()));
        } catch (MemberNotInVoiceChannelException e) {
            event.getChannel().sendMessage(member.getAsMention() + " you must be in a voice channel for this command to work!").queue();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("ERROR");
        }
    }

    private String gameToVoice(Member member) {
        if (!member.getVoiceState().inVoiceChannel())
            throw new MemberNotInVoiceChannelException("Member is not in a voice channel");

        switch (member.getGame().getName().toLowerCase()) {
            case "csgo":
            case "cs:go":
            case "counter-strike: global offensive":
            case "counter strike: global offensive":
            case "counter-strike global offensive":
            case "counter strike global offensive":
                //TODO: Add prompt to decide what channel is for what, if even exists - if bot will be public
                return this.csgo;
            case "rainbow six siege":
            case "tom clacny's rainbow six siege":
            case "tom clancy's rainbow six® siege":
                if (member.getGame().isRich()) {
                    if (Objects.requireNonNull(member.getGame().asRichPresence().getDetails()).contains("ranked"))
                        return this.rainbow_ranked;
                    if (Objects.requireNonNull(member.getGame().asRichPresence().getDetails()).contains("casual"))
                        return this.rainbow_casual;
                    if (Objects.requireNonNull(member.getGame().asRichPresence().getDetails()).contains("custom match"))
                        return this.rainbow_custom;
                } else {
                    return this.rainbow;
                }
            case "heroes & generals":
            case "heroes and generals":
            case "h&g":
            case "hng":
                return this.hng;
            default:
                return this.other;
        }
    }
}