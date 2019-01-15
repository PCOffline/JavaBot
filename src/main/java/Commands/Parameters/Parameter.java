package Commands.Parameters;

import java.util.Arrays;

/**
 * Parameter of Commands
 */

public class Parameter {

    private String name;
    private ParameterType type;
    private String regex;
    private String regexDescription;
    private String[] limit;
    private Parameter child;
    private boolean isParent = false;
    private boolean isChild = false;

    /**
     * Childless parameter that is either free or constant
     *
     * @param name Name of parameter
     * @param type Type of parameter (can be free or constant)
     */

    public Parameter(String name, ParameterType type) {
        this.name = name;

        if (type == ParameterType.limited || type == ParameterType.regex)
            try {
                throw new InvalidTypeException();
            } catch (InvalidTypeException e) {
                this.type = null;
                e.printStackTrace();
            }

        this.type = type;
    }

    /**
     * Childless parameter that is of type regex
     *
     * @param name             Name of parameter
     * @param regex            Regex of Parameter
     * @param regexDescription Regex description, user won't understand Java regex
     */

    public Parameter(String name, String regex, String regexDescription) {
        this.type = ParameterType.regex;
        this.name = name;
        this.regex = regex;
        this.regexDescription = regexDescription;
    }

    /**
     * Childless parameter that is of type limited
     *
     * @param name  Name of parameter
     * @param limit Limit of parameter
     */

    public Parameter(String name, String[] limit) {
        this.type = ParameterType.limited;
        this.name = name;
        this.limit = limit;
    }

    /**
     * Parameter that has a child that is of type regex
     *
     * @param name             Name of parameter
     * @param regex            Regex of parameter
     * @param regexDescription Regex description, user won't understand Java regex
     * @param child            Child parameter
     */

    public Parameter(String name, String regex, String regexDescription, Parameter child) {
        this.type = ParameterType.regex;
        this.name = name;
        this.regex = regex;
        this.regexDescription = regexDescription;
        this.child = child;
        this.isParent = true;
        this.child.setChild(true);
    }

    public Parameter(String name, String[] limit, Parameter child) {
        this.type = ParameterType.limited;
        this.name = name;
        this.limit = limit;
        this.child = child;
        this.isParent = true;
        this.child.setChild(true);
    }

    public Parameter(String name, ParameterType type, Parameter child) {
        this.name = name;

        if (type == ParameterType.limited || type == ParameterType.regex)
            try {
                throw new InvalidTypeException();
            } catch (InvalidTypeException e) {
                this.type = null;
                e.printStackTrace();
            }

        this.type = type;
        this.child = child;
        this.isParent = true;
        this.child.setChild(true);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ParameterType getType() {
        return type;
    }

    public void setType(ParameterType type) {
        this.type = type;
    }

    public String getRegex() {
        return regex;
    }

    public void setRegex(String regex) {
        this.regex = regex;
    }

    public String getRegexDescription() {
        return regexDescription;
    }

    public void setRegexDescription(String regexDescription) {
        this.regexDescription = regexDescription;
    }

    public String[] getLimit() {
        return limit;
    }

    public void setLimit(String[] limit) {
        this.limit = limit;
    }

    public Parameter getChild() {
        return child;
    }

    public void setChild(Parameter child) {
        this.child = child;
    }

    public boolean isParent() {
        return isParent;
    }

    public void setParent(boolean parent) {
        isParent = parent;
    }

    public boolean isChild() {
        return isChild;
    }

    public void setChild(boolean child) {
        isChild = child;
        this.isParent = this.child != null;
    }

    public boolean typeCheck(String param) {
        if (this.type == ParameterType.free)
            return !param.isEmpty();
        if (this.type == ParameterType.regex)
            return param.matches(regex);
        if (this.type == ParameterType.limited)
            return Arrays.asList(limit).contains(param.toLowerCase());
        if (this.type == ParameterType.constant)
            return param.equalsIgnoreCase(this.name);
        return false;
    }

    @Override
    public String toString() {
        String res = "";
        if (this.type == ParameterType.free)
            res += "<" + this.name + ">";
        if (this.type == ParameterType.regex)
            res += "<" + this.name + " " + regexDescription + ">";
        if (this.type == ParameterType.limited)
            res += "<" + this.name + "> [" + Arrays.toString(limit) + "]";
        if (this.type == ParameterType.constant)
            res += this.name.toUpperCase();
        if (this.isParent)
            res += " -> " + this.child.toString();
        return res;
    }

}
