package app;

/**
 * @author Jenni Unkuri jenni.unkuri@cs.tamk.fi
 * @version 2016.1130
 * @since 1.7
 */
public class InputParser {

    /**
     * Splits input to string array.
     *
     * @param input input to split
     * @param c character to split by
     * @return array
     */
    public String[] inputToArray(String input, char c) {
        String[] array = input.split(String.valueOf(c));

        // Trims leading, trailing and excess whitespace
        for (int i = 0; i < array.length; i++) {
            array[i] = array[i].trim();
            array[i] = array[i].replaceAll("(\\s){2,}", " ");
        }

        return array;
    }

    /**
     * Checks if first words in items of array are integers.
     *
     * @param array array to check
     * @return true if first words are integer, false if not
     */
    public boolean firstIsInteger(String[] array) {
        for (String string : array) {
            String[] words = string.split(" ");
            try {
                Integer.parseInt(words[0]);
            } catch (NumberFormatException e) {
                System.err.println("Incorrect input \"" + string
                        + "\", list not added");
                return false;
            }
        }

        return true;
    }
}
