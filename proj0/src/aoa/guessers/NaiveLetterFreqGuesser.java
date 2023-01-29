package aoa.guessers;

import aoa.utils.FileUtils;

import java.util.*;

public class NaiveLetterFreqGuesser implements Guesser {
    private final List<String> words;

    public NaiveLetterFreqGuesser(String dictionaryFile) {
        words = FileUtils.readWords(dictionaryFile);
    }

    @Override
    /** Makes a guess which ignores the given pattern. */
    public char getGuess(String pattern, List<Character> guesses) {
        return getGuess(guesses);
    }

    /** Returns a map from a given letter to its frequency across all words.
     *  This task is similar to something you did in hw0b! */
    public Map<Character, Integer> getFrequencyMap() {
        Map<Character, Integer> freqMap = new TreeMap<Character, Integer>();
        for(int i = 0; i < words.size(); i++) {
            String curr_word = words.get(i);
            for(int j = 0; j < curr_word.length(); j++) {
                if (!(freqMap.containsKey(curr_word.charAt(j)))) {
                    freqMap.put(curr_word.charAt(j), 0);
                }
                int oldCount = freqMap.get(curr_word.charAt(j));
                freqMap.replace(curr_word.charAt(j), oldCount, oldCount + 1);
            }
        }
        return freqMap;
    }

    /** Returns the most common letter in WORDS that has not yet been guessed
     *  (and therefore isn't present in GUESSES). */
    public char getGuess(List<Character> guesses) {
        /** call getFrequencyMap
         *remove guesses from map
         * find and return most frequent
         */


        Map<Character, Integer> frequencies = getFrequencyMap();
        for(int i = 0; i < guesses.size(); i++) {
            if(frequencies.containsKey(guesses.get(i))) {
                frequencies.remove(guesses.get(i));
            }
        }
        Set<Character> keyset = frequencies.keySet();
        List<Character> keylist = new ArrayList<>(keyset);
        char commonKey = ' ';
        int highestFreq = 0;
        for(int i = 0; i < keylist.size(); i++) {
            if(frequencies.get(keylist.get(i)) > highestFreq) {
                highestFreq = frequencies.get(keylist.get(i));
                commonKey = keylist.get(i);
            }
        }
        return commonKey;
    }

    public static void main(String[] args) {
        NaiveLetterFreqGuesser nlfg = new NaiveLetterFreqGuesser("data/example.txt");
        System.out.println("list of words: " + nlfg.words);
        System.out.println("frequency map: " + nlfg.getFrequencyMap());

        List<Character> guesses = List.of('e', 'l');
        System.out.println("guess: " + nlfg.getGuess(guesses));
    }
}
