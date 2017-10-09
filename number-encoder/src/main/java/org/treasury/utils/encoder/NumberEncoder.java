package org.treasury.utils.encoder;

import java.util.*;

import static java.util.Collections.singletonList;

/**
 * Created by kuzmende on 7/11/15.
 */
public class NumberEncoder {

    private static final String WORD_REGEX = "[a-zA-Z\"-]+";
    private static final String PHONE_REGEX = "[0-9/-]+";

    public static final String SPACE = " ";

    private static final Map<Integer, String> LETTER_MAPPING = new HashMap<Integer, String>() {{
        put(0, "e"); put(1, "jnq"); put(2, "rwx"); put(3, "dsy"); put(4, "ft");
        put(5, "am"); put(6, "civ"); put(7, "bku"); put(8, "lop"); put(9, "ghz");
    }};

    private static final Map<Character, Integer> INVERSE_LETTER_MAPPING = new HashMap<Character, Integer>() {{
        for (Map.Entry<Integer, String> entry : LETTER_MAPPING.entrySet()) {
            for (char character : entry.getValue().toCharArray()) {
                put(character, entry.getKey());
            }
        }
    }};

    private final SortedMap<String, List<String>> numberToWords;

    public NumberEncoder(final List<String> dictionary) {
        numberToWords = new TreeMap<>();

        for (String word : dictionary) {
            if (isValidWord(word)) {
                String number = convertToNumber(word);
                insertIntoDictionary(number, word);
            }
        }
    }

    public List<String> encode(String number) {
        if (isValidPhoneNumber(number)) {
            String normalizedPhoneNumber = normalizePhoneNumber(number);

            List<String> results = new ArrayList<>();
            search(normalizedPhoneNumber, new Stack<String>(), results, false);

            return results;

        } else {
            throw new IllegalArgumentException(number);
        }
    }

    private void search(String number, Stack<String> numbers, List<String> results, boolean leftIsDigit) {
        boolean resultIsEmpty = true;

        for (int i = 0; i < number.length(); i++) {
            String subnumber = number.substring(0, i + 1);

            if(numberToWords.subMap(subnumber, subnumber + Character.MAX_VALUE).isEmpty()){
                break;
            }

            if (numberToWords.keySet().contains(subnumber)) {
                numbers.push(subnumber);

                if (i == number.length() - 1) {
                    List<String> result = combineResults(numbers);
                    results.addAll(result);

                } else {
                    search(number.substring(i + 1), numbers, results, false);
                }

                resultIsEmpty = false;
                numbers.pop();
            }
        }

        if (resultIsEmpty && !leftIsDigit) {
            numbers.push(number.substring(0, 1));

            if (number.length() == 1) {
                List<String> result = combineResults(numbers);
                results.addAll(result);

            } else {
                search(number.substring(1), numbers, results, true);
            }

            numbers.pop();
        }
    }

    private List<String> combineResults(Stack<String> numbers) {
        List<String> result = new ArrayList<>();

        for (String number : numbers) {
            List<String> words = numberToWords.get(number);

            List<String> list = (words != null) ?
                    new ArrayList<>(words) : singletonList(number);

            if (result.isEmpty()) {
                result = list;
            } else {
                result = combine(result, list);
            }
        }
        return result;
    }

    private List<String> combine(List<String> leftWords, List<String> rightWords) {
        List<String> result = new ArrayList<>();
        for (String left : leftWords) {
            for (String right : rightWords) {
                result.add(left + SPACE + right);
            }
        }
        return result;
    }

    private void insertIntoDictionary(String number, String word) {
        List<String> list = numberToWords.get(number);
        if (list == null) {
            list = new ArrayList<>();
            numberToWords.put(number, list);
        }

        list.add(word);
    }

    private String convertToNumber(String word) {
        String normalizedWord = normalizeWord(word);

        StringBuilder builder = new StringBuilder();
        for (char character : normalizedWord.toCharArray()) {
            builder.append(INVERSE_LETTER_MAPPING.get(character));
        }

        return builder.toString();
    }

    private String normalizeWord(String word) {
        return word.replace("\"", "").replace("-", "").trim().toLowerCase();
    }

    private String normalizePhoneNumber(String phoneNumber) {
        return phoneNumber.replace("/", "").replace("-", "").trim();
    }

    private boolean isValidWord(String word) {
        return word.matches(WORD_REGEX);
    }


    private boolean isValidPhoneNumber(String phoneNumber) {
        return phoneNumber.matches(PHONE_REGEX);
    }

}
