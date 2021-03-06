import acm.program.CommandLineProgram;

import java.awt.event.KeyListener;
import java.util.Arrays;

public class SubstitutionCypher extends CommandLineProgram {


    public void run() {
        testAllPairs();
        testGetColumn();
        testContainsInSuffix();
        testUnique();
        testIsValid();
        testInvert();
        testCreateKey();
        testEncodeChar();
        testEncodeText();
        testDecodeText();
    }


    public boolean allPairs(char[][] key) {
        boolean isPair = true;
        if (key != null) {
            for (int i = 0; i < key.length; i++) {
                if (key[i] == null || key[i].length != 2) {
                    isPair = false;
                    break;
                }
            }
        } else {
            isPair = false;
        }
        return isPair;
        /*for (int i = 0; key[i] != null && i < key.length; i++) {
                for (int j = 0; j < key[i].length; j++) {
                    if (key[i][j] != ' ') {
                        println(key[i][j]);
                    }
                }*/
        //throw new UnsupportedOperationException("Pas 1");
    }

    public void testAllPairs() {
        printlnInfo("BEGIN allPairs");
        assertEquals("allPairs1", false, allPairs(null));
        assertEquals("allPairs2", false, allPairs(new char[][]{{'a', 'b'}, null, {'c', 'd'}}));
        assertEquals("allPairs3", true, allPairs(new char[][]{}));
        assertEquals("allPairs4", false, allPairs(new char[][]{{}}));
        assertEquals("allPairs5", true, allPairs(new char[][]{{'a', 'b'}}));
        assertEquals("allPairs6", false, allPairs(new char[][]{{'a', 'b'}, {'a'}}));

        printlnInfo("END allPairs");
        printBar();
    }

    public char[] getColumn(char[][] key, int column) {
        if (key.length == 0) {
            return new char[]{};

        }
        char[] keyColumn = new char[key.length];
        for (int i = 0; i < keyColumn.length; i++) {
            keyColumn[i] = key[i][column];

        }
        return keyColumn;
        //throw new UnsupportedOperationException("Pas 2");
    }

    public void testGetColumn() {
        printlnInfo("BEGIN getColumns");
        assertEquals("getColumns1", new char[]{}, getColumn(new char[][]{}, 0));
        assertEquals("getColumns2", new char[]{}, getColumn(new char[][]{}, 1));
        assertEquals("getColumns3", new char[]{'a'}, getColumn(new char[][]{{'a', 'b'}}, 0));
        assertEquals("getColumns4", new char[]{'b'}, getColumn(new char[][]{{'a', 'b'}}, 1));
        assertEquals("getColumns5", new char[]{'a', 'c'}, getColumn(new char[][]{{'a', 'b'}, {'c', 'd'}}, 0));
        assertEquals("getColumns6", new char[]{'b', 'd'}, getColumn(new char[][]{{'a', 'b'}, {'c', 'd'}}, 1));
        printlnInfo("END getColumns");
        printBar();
    }

    public boolean containsInSuffix(char[] chars, int initialPos, char c) {
        boolean containsInSuffix = false;
        if (chars.length > initialPos) {
            for (int i = initialPos; i < chars.length; i++) {
                if (c == chars[i]) {
                    containsInSuffix = true;
                }
            }
        }
        return containsInSuffix;
        //throw new UnsupportedOperationException("Pas 3");
    }

    public void testContainsInSuffix() {
        printlnInfo("BEGIN containsInSuffix");
        assertEquals("containsInSuffix1", false, containsInSuffix(new char[]{}, 12, 'a'));
        assertEquals("containsInSuffix2", false, containsInSuffix(new char[]{'a'}, 2, 'a'));
        assertEquals("containsInSuffix3", false, containsInSuffix(new char[]{'a', 'b'}, 1, 'a'));
        assertEquals("containsInSuffix4", true, containsInSuffix(new char[]{'a', 'b'}, 1, 'b'));
        printlnInfo("END containsInSuffix");
        printBar();
    }

    public boolean unique(char[] chars) {
        boolean isUnique = true;
        for (int i = 0; i < chars.length; i++) {
            if (containsInSuffix(chars, i + 1, chars[i])) {
                isUnique = false;
            }
        }
        return isUnique;
        //throw new UnsupportedOperationException("Pas 4");
    }

    public void testUnique() {
        printlnInfo("BEGIN unique");
        assertEquals("unique1", true, unique("".toCharArray()));
        assertEquals("unique2", true, unique("a".toCharArray()));
        assertEquals("unique3", false, unique("aa".toCharArray()));
        assertEquals("unique4", true, unique("abcdef".toCharArray()));
        assertEquals("unique5", false, unique("abcad".toCharArray()));
        printlnInfo("END unique");
        printBar();
    }

    public boolean isValid(char[][] key) {
        boolean valid = allPairs(key);
        if (valid && key.length != 0) {
            for (int j = 0; j < key[0].length; j++) {
                char[] column = new char[key[0].length];
                for (int i = 0; i < key.length; i++) {
                    column[i] = key[i][j];
                    if (!unique(column)) {
                        valid = false;
                    }
                }
            }


        }
        return valid;
        //throw new UnsupportedOperationException("Pas 5");
    }

    public void testIsValid() {
        printlnInfo("BEGIN isValid");
        assertEquals("isValid1", false, isValid(null));
        assertEquals("isValid2", true, isValid(new char[][]{}));
        assertEquals("isValid3", false, isValid(new char[][]{null}));
        assertEquals("isValid4", true, isValid(new char[][]{{'a', 'b'}}));
        assertEquals("isValid5", false, isValid(new char[][]{{'a'}}));
        assertEquals("isValid6", false, isValid(new char[][]{{'a', 'b'}, {'a', 'c'}}));
        assertEquals("isValid7", false, isValid(new char[][]{{'a', 'b'}, {'c', 'b'}}));
        assertEquals("isValid8", true, isValid(new char[][]{{'a', 'b'}, {'c', 'd'}}));
        printlnInfo("END isValid");
        printBar();
    }

    public char[][] invert(char[][] key) {
        if (key.length == 0) {
            return new char[0][0];
        }
        char[][] keyInverted = new char[key.length][key[0].length];
        for (int i = 0; i < key.length; i++) {
            keyInverted[i][1] = key[i][0];
            keyInverted[i][0] = key[i][1];
        }
        return keyInverted;
        //throw new UnsupportedOperationException("Pas 6");
    }

    public void testInvert() {
        printlnInfo("BEGIN invert");
        assertEquals("invert1", new char[][]{}, invert(new char[][]{}));
        assertEquals("invert2", new char[][]{{'b', 'a'}}, invert(new char[][]{{'a', 'b'}}));
        assertEquals("invert3", new char[][]{{'b', 'a'}, {'d', 'c'}}, invert(new char[][]{{'a', 'b'}, {'c', 'd'}}));
        printlnInfo("END invert");
        printBar();
    }

    public char[][] createKey(String left, String right) {
        int pairsOfNumbers = 0;
        if (left.length() <= right.length()) {
            pairsOfNumbers = left.length();
        } else {
            pairsOfNumbers = right.length();
        }
        if (pairsOfNumbers != 0) {
            char[][] keyCreated = new char[pairsOfNumbers][2];
            for (int i = 0; i < pairsOfNumbers; i++) {
                keyCreated[i][0] = left.charAt(i);
                keyCreated[i][1] = right.charAt(i);
            }
            return keyCreated;
        } else {
            return new char[0][0];
        }


        //throw new UnsupportedOperationException("Pas 7");

    }

    public void testCreateKey() {
        printlnInfo("BEGIN createKey");
        assertEquals("createKey1", new char[][]{}, createKey("", "anything"));
        assertEquals("createKey2", new char[][]{}, createKey("anything", ""));
        assertEquals("createKey3", new char[][]{{'a', 'b'}}, createKey("a", "bc"));
        assertEquals("createKey4", new char[][]{{'a', 'c'}}, createKey("ab", "c"));
        assertEquals("createKey5", new char[][]{{'a', 'c'}, {'b', 'd'}}, createKey("ab", "cd"));
        printlnInfo("END createKey");
        printBar();
    }

    public int encodeChar(char[][] key, char c) {
        int num = -1;
        if (key.length != 0) {
            for (int i = 0; i < key.length; i++) {
                if (c == key[i][0]) {
                    num = key[i][1];
                }
            }
        }
        return num;

        //throw new UnsupportedOperationException("Pas 8");
    }

    public void testEncodeChar() {
        printlnInfo("BEGIN encodeChar");
        assertEquals("encodeChar1", -1, encodeChar(new char[][]{}, 'a'));
        assertEquals("encodeChar2", 'b', encodeChar(new char[][]{{'a', 'b'}}, 'a'));
        assertEquals("encodeChar3", 'd', encodeChar(new char[][]{{'a', 'b'}, {'c', 'd'}}, 'c'));
        printlnInfo("END encodeChar");
        printBar();
    }

    public String encodeText(char[][] key, String clearText) {
        char[] encodedText = new char[clearText.length()];
        if (clearText.length() == 0) {
            return new String("");
        } else {
            for (int j = 0; j < clearText.length(); j++) {
                int notCoincidences = 0;
                for (int i = 0; i < key.length; i++) {
                    if (clearText.charAt(j) == key[i][0]) {
                        encodedText[j] = key[i][1];
                        notCoincidences = 0;
                        break;
                    } else {
                        notCoincidences++;
                    }

                }
                if (notCoincidences == key.length) {
                    return null;
                }
            }
        }
        return new String(encodedText);
    }

    //throw new UnsupportedOperationException("Pas 9");


    public void testEncodeText() {
        printlnInfo("BEGIN encodeText");
        assertEquals("encodeText1", "", encodeText(new char[][]{}, ""));
        assertEquals("encodeText2", null, encodeText(new char[][]{{'a', 'b'}}, "aca"));
        assertEquals("encodeText3", "bddb", encodeText(new char[][]{{'a', 'b'}, {'c', 'd'}}, "acca"));
        printlnInfo("END encodeText");
        printBar();
    }

    public String decodeText(char[][] key, String encodedText) {
        key = invert(key);
        return new String(encodeText(key, encodedText));
    }
        /*char[] clearTextArray = encodedText.toCharArray();
        char[] encodeddText = new char[encodedText.length()];
        boolean isNull = false;
        int notCoincidences = 0;
        int coincidences = 0;
        if (clearTextArray.length == 0) {
            return new String("");
        } else {
            for (int j = 0; j < clearTextArray.length; j++) {
                for (int i = 0; i < key.length; i++) {

                    if (clearTextArray[j] == key[i][1]) {
                        encodeddText[coincidences] = key[i][0];
                        notCoincidences = 0;
                        coincidences++;
                        break;
                    } else {
                        notCoincidences++;
                    }

                }
                if (notCoincidences == key.length) {
                    return null;
                }
            }
        }
        return new String(encodeddText);*/
    //throw new UnsupportedOperationException("Pas 10");

    public void testDecodeText() {
        printlnInfo("BEGIN decodeText");
        assertEquals("decodeText1", "", decodeText(new char[][]{}, ""));
        assertEquals("decodeText2", "acca", decodeText(new char[][]{{'a', 'b'}, {'c', 'd'}}, "bddb"));
        printlnInfo("END decodeText");
        printBar();
    }

    // Assert functions

    public void assertEquals(String message, boolean expected, boolean computed) {
        if (expected == computed) {
            printlnOk(message);
        } else {
            printlnError(message + " Expected " + expected + " but found " + computed);
        }
    }

    public void assertEquals(String message, char[] expected, char[] computed) {
        if (Arrays.equals(expected, computed)) {
            printlnOk(message);
        } else {
            printlnError(message + " Expected " + Arrays.toString(expected) + " but found " + Arrays.toString(computed));
        }
    }

    public void assertEquals(String message, int expected, int computed) {
        if (expected == computed) {
            printlnOk(message);
        } else {
            printlnError(message + " Expected " + expected + " but found " + computed);
        }
    }

    public void assertEquals(String message, char[][] expected, char[][] computed) {
        if (expected.length != computed.length) {
            printlnError(message + " Expected has length " + expected.length + " but computed has " + computed.length);
            return;
        }
        for (int i = 0; i < computed.length; i += 1) {
            char[] er = expected[i];
            char[] cr = computed[i];
            if (!Arrays.equals(er, cr)) {
                printlnError("\tRow " + i + " of expected is " + Arrays.toString(er) + " in computed is " + Arrays.toString(cr));
                return;
            }
        }
        printlnOk(message);
    }

    public void assertEquals(String message, String expected, String computed) {
        if (expected == null ? computed == null : expected.equals(computed)) {
            printlnOk(message);
        } else {
            printlnError(message + " Expected " + expected + " but found " + computed);
        }
    }

    // Colorize output for CommandLineProgram

    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_BLUE = "\u001B[34m";

    public void printlnInfo(String message) {
        if (acm.program.CommandLineProgram.class.isInstance(this)) println(ANSI_BLUE + message + ANSI_RESET);
        else println(message);
    }

    public void printlnOk(String message) {
        if (acm.program.CommandLineProgram.class.isInstance(this)) println(ANSI_GREEN + "OK: " + message + ANSI_RESET);
        else println("OK: " + message);
    }

    public void printlnError(String message) {
        if (acm.program.CommandLineProgram.class.isInstance(this)) println(ANSI_RED + "ERROR: " + message + ANSI_RESET);
        else println("ERROR: " + message);
    }

    public void printBar() {
        println("--------------------------------------------------");
    }

    public static void main(String[] args) {
        new SubstitutionCypher().start(args);
    }
}