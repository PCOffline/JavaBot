package Commands;

import Commands.Parameters.Parameter;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

/**
 * Abstract Command class which is used as a general command template
 */

public abstract class Command {

    String name;
    String description;
    Parameter[] parameters;
    boolean isParameter = false;

    Command() {

    }

    public Command(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Command(String name, String description, Parameter[] parameters) {
        this.name = name;
        this.description = description;
        this.parameters = parameters;
        this.isParameter = true;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Parameter[] getParameters() {
        return parameters;
    }

    public void setParameters(Parameter[] parameters) {
        this.parameters = parameters;
        this.isParameter = parameters != null;
    }

    public boolean isParameter() {
        return isParameter;
    }

    public void setParameter(boolean parameter) {
        isParameter = parameter;
    }

    public boolean validate(String message) {
        if (this.name == null || message == null)
            return false;
        if ((!message.contains(" ")) && isParameter)
            return false;
        if (!isParameter)
            return message.equalsIgnoreCase(this.name);
        return message.split(" ")[0].equalsIgnoreCase(name) && (!message.split(" ")[1].isEmpty());
    }

    public abstract void execute(MessageReceivedEvent event);

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        if (parameters == null || name == null || description == null)
            return null;
        for (Parameter param : parameters)
            res.append(param.toString()).append(" ");
        return String.format("%s - %s\n Usage: %s %s", this.name, this.description, this.name, res);
    }
}
