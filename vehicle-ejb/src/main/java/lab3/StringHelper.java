package lab3;

import java.util.Arrays;
import java.util.Iterator;

public final class StringHelper {

    private StringHelper() {
    }

    public static String toCamelCase(String snakeCaseString) {
        String[] words = snakeCaseString.split("_");
        if (words.length == 1) {
            return words[0];
        }
        StringBuilder builder = new StringBuilder();
        Iterator<String> wordIterator = Arrays.stream(words).iterator();
        builder.append(wordIterator.next());
        while (wordIterator.hasNext()) {
            String word = wordIterator.next();
            String firstLetter = word.substring(0, 1);
            String tail = word.substring(1);
            builder.append(firstLetter.toUpperCase()).append(tail);
        }
        return builder.toString();
    }
}
