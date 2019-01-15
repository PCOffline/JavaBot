package Main;

import java.lang.reflect.Field;

/**
 * Class to contain all global strings used in the code such as default values, token, etc.
 */

public class Strings {

    // DEFAULT VALUES

    protected static final String DEFAULT_PREFIX = ",";

    // GLOBAL VALUES

    static final String BOT_NAME = "Botato";
    static final String token = "*******";
    static final String CONFIG_PATH = "src/main/resources/Config.txt";
    // IMAGES
    static final String QUESTION_MARK = "https://www.kuleuven.be/studentenvoorzieningen/kot-leuven/icoontjes/question-mark-on-a-circular-black-background.png/image";
    // Arguments
    public static Argument prefix = new Argument("prefix");

    public static class Argument {

        private String name;
        private String value;

        Argument(String name) {
            this.name = name;
            // Set default value of each argument by
            // To the matching default value
            // e.g. name = "prefix" --> value = DEFAULT_PREFIX
            try {
                Field field = Strings.class.getDeclaredField("DEFAULT_" + name.toUpperCase());
                this.value = (String) field.get(field.getName());
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        public Argument(String name, String value) {
            this.name = name;
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

}
