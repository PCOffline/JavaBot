package Main;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

/**
 * Class to maintain memory files of the bot
 */

public class Memory {

    // Read

    /**
     * Read a file and return a string with all the content of the file
     *
     * @param path Path of the file to read
     * @return String with all the content of the file
     */

    private static String readFile(String path) {
        BufferedReader reader;
        StringBuffer raw;
        String line;
        String text = null;

        try {

            reader = new BufferedReader(new FileReader(path));
            raw = new StringBuffer();
            line = reader.readLine();

            while (line != null) {
                raw.append(line);
                raw.append("\n");
                line = reader.readLine();
            }

            text = raw.toString();
            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return text;
    }

    /**
     * Read a file and return a string with all the content of the file until the limit
     *
     * @param path  Path of the file to read
     * @param limit The last line to read
     * @return String with all the content of the file until the limit
     */

    private static String readFile(String path, long limit) {
        BufferedReader reader;
        StringBuffer raw;
        String line;
        String text = null;
        int count = 0;

        try {

            reader = new BufferedReader(new FileReader(path));
            raw = new StringBuffer();
            line = reader.readLine();

            while (line != null && count <= limit) {
                count++;
                raw.append(line);
                raw.append("\n");
                line = reader.readLine();
            }

            text = raw.toString();
            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return text;
    }

    /**
     * Read a file and return a string with all the content of the file from the limit
     *
     * @param path  Path of the file to read
     * @param limit The first line to read
     * @return String with all the content of the file from the limit
     */

    private static String readFile(long limit, String path) {
        BufferedReader reader;
        StringBuffer raw;
        String line;
        String text = null;
        int count = 1;

        try {

            reader = new BufferedReader(new FileReader(path));
            raw = new StringBuffer();
            line = reader.readLine();

            while (line != null && count < limit) {
                line = reader.readLine();
                ++count;
            }

            while (line != null && count >= limit) {
                raw.append(line);
                raw.append("\n");
                line = reader.readLine();
            }

            text = raw.toString();
            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return text;
    }

    // Write

    /**
     * Write a value into the last line of a file
     *
     * @param path  Path of the file to write to
     * @param value The value to write in the file
     */

    private static void writeFile(String path, String value) {
        FileOutputStream outputStream;

        try {
            outputStream = new FileOutputStream(path);
            outputStream.write((readFile(path) + "\n" + value).getBytes());
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Write a value into the selected line
     *
     * @param path  Path of the file to write to
     * @param value The value to write in the file
     * @param line  line to write the value into the selected line
     */

    private static void writeFile(String path, String value, long line) {
        FileOutputStream outputStream;

        try {
            outputStream = new FileOutputStream(path);
            outputStream.write((readFile(path, line - 1) + "\n" + value + "\n" + readFile(line, path)).getBytes());
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Delete

    /**
     * Delete line of a file
     *
     * @param path Path of the file where the line is deleted
     * @param line The line number to be deleted
     */

    private static void deleteLine(String path, long line) {
        FileOutputStream outputStream;

        try {
            outputStream = new FileOutputStream(path);
            outputStream.write((readFile(path, line - 1) + "\n" + readFile(line + 1, path)).getBytes());
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Checks if a keyword exists in a file
     *
     * @param path          Path of file
     * @param keyword       The keyword to search
     * @param ignoreCase    Should the search ignore case or not
     * @param keywordIsLine Is the keyword a whole line or a part of a line in the file
     * @return True if keyword is found
     */

    // Keyword

    private static boolean keywordExist(String path, String keyword, boolean ignoreCase, boolean keywordIsLine) {
        BufferedReader reader;
        String line;

        try {
            reader = new BufferedReader(new FileReader(path));
            line = reader.readLine();

            while (line != null) {

                if (keywordIsLine) {
                    if (line.equals(keyword))
                        return true;
                    if (ignoreCase) {
                        if (line.equalsIgnoreCase(keyword))
                            return true;
                    }

                } else {
                    if (line.contains(keyword))
                        return true;
                    if (line.toLowerCase().contains(keyword.toLowerCase()))
                        return true;
                }
                line = reader.readLine();
            }

            return false;
        } catch (java.io.IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Checks if a keyword exists in a file, starting from a specific line
     *
     * @param path          Path of file
     * @param keyword       The keyword to search
     * @param ignoreCase    Should the search ignore case or not
     * @param keywordIsLine Is the keyword a whole line or a part of a line in the file
     * @param start         Line to start from
     * @return True if keyword is found
     */

    private static boolean keywordExist(String path, String keyword, boolean ignoreCase, boolean keywordIsLine, long start) {
        BufferedReader reader;
        String line;
        int count = 0;

        try {
            reader = new BufferedReader(new FileReader(path));
            line = reader.readLine();

            while (line != null) {

                while (count < start) {
                    line = reader.readLine();
                    count++;
                }

                if (keywordIsLine) {
                    if (line.equals(keyword))
                        return true;
                    if (ignoreCase) {
                        if (line.equalsIgnoreCase(keyword))
                            return true;
                    }

                } else {
                    if (line.contains(keyword))
                        return true;
                    if (line.toLowerCase().contains(keyword.toLowerCase()))
                        return true;
                }
                line = reader.readLine();
            }

            return false;
        } catch (java.io.IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Replaces a word in a file with another word.
     * @param path Path of the file
     * @param prev Word to replace
     * @param value New word
     * @param ignoreCase Should the method ignore case
     * @param keywordIsLine Is the keyword an entire line or a part of a line
     */

    private static void editKeyword (String path, String prev, String value, boolean ignoreCase, boolean keywordIsLine) {
            long line = getLine(path, prev, ignoreCase, keywordIsLine);
            writeFile(path, keywordIsLine ? value : goLine(path, line).replace(prev, value), line + 1);
            deleteLine(path, line);
    }

    // Line

    /**
     * Find the line of the first occurrence of a keyword as a whole line or as a part of a line in the file
     *
     * @param path          Path of the file to find the line of the keyword in
     * @param keyword       The keyword to find the line of
     * @param ignoreCase    Should the search ignore case or not
     * @param keywordIsLine Is the keyword a whole line or a part of a line in the file
     * @return The line of the first occurrence of the keyword as a whole line or as a part of a line in the file
     */

    private static long getLine(String path, String keyword, boolean ignoreCase, boolean keywordIsLine) throws  {
        BufferedReader reader;
        String line;
        int count = 1;

        try {

            reader = new BufferedReader(new FileReader(path));
            line = reader.readLine();
            if (keywordIsLine) {
                while (line != null && (line.equals(keyword) || (ignoreCase && line.equalsIgnoreCase(keyword)))) {
                    line = reader.readLine();
                    count++;
                }
            } else {
                while (line != null && (line.contains(keyword) || (ignoreCase && line.toLowerCase().contains(keyword.toLowerCase())))) {
                    line = reader.readLine();
                    count++;
                }
            }

            reader.close();

            return count;

        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        }
    }

    /**
     * Find the line of the first occurrence of a keyword as a whole line or as a part of a line in the file from the line inputted
     *
     * @param path          Path of the file to find the line of the keyword in
     * @param keyword       The keyword to find the line of
     * @param ignoreCase    Should the search ignore case or not
     * @param keywordIsLine Is the keyword a whole line or a part of a line in the file
     * @param start         Line to start from
     * @return The line of the first occurrence of the keyword as a whole line or as a part of a line in the file
     */

    private static long getLine(String path, String keyword, boolean ignoreCase, boolean keywordIsLine, long start) {
        BufferedReader reader;
        String line;
        int count = 1;

        try {

            reader = new BufferedReader(new FileReader(path));
            line = reader.readLine();
            while (count < start) {
                line = reader.readLine();
                count++;
            }

            if (keywordIsLine) {
                while (line != null && (line.equals(keyword) || (ignoreCase && line.equalsIgnoreCase(keyword)))) {
                    line = reader.readLine();
                    count++;
                }
            } else {
                while (line != null && (line.contains(keyword) || (ignoreCase && line.toLowerCase().contains(keyword.toLowerCase())))) {
                    line = reader.readLine();
                    count++;
                }
            }

            reader.close();

            return count;

        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        }
    }


    /**
     * Returns the content of a specific line
     *
     * @param path - Path of the file
     * @param line Number of the line to get the content of
     * @return The content of a specific line
     */

    private static String goLine(String path, long line) {
        return readFile(line, path).split("\n")[0];
    }

    // Guild

    /**
     * Edit guild value
     *
     * @param prev       Previous value
     * @param value      New value
     * @param ignoreCase Should the search ignore case
     */

    public static void editArgument (String prev, String value, boolean ignoreCase) {
        long line = getLine(Strings.CONFIG_PATH, prev, ignoreCase, false);
        writeFile(Strings.CONFIG_PATH, value, line + 1);
        deleteLine(Strings.CONFIG_PATH, line);
    }

    /**
     * Checks if a guild argument exists in memory
     *
     * @param argument Argument of the guild
     * @return True if the argument and the guild exist
     */

    private static boolean argumentExist (String argument) {
        return keywordExist(Strings.CONFIG_PATH, argument + ": true", true, true);
    }

    /**
     * Gets the line of a guild argument in the memory
     *
     * @param argument Argument of the guild
     * @return Line of the argument
     */

    public static String getArgument(String argument) {
        if (argumentExist(argument))
            return goLine(Strings.CONFIG_PATH, getLine(Strings.CONFIG_PATH, argument, true, false)).split(" ")[1];

        return null;
    }
}
