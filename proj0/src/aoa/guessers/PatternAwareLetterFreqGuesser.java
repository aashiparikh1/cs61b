package aoa.guessers;

import aoa.utils.FileUtils;

import java.lang.reflect.Array;
import java.util.*;

public class PatternAwareLetterFreqGuesser implements Guesser {
    private final List<String> words;

    public PatternAwareLetterFreqGuesser(String dictionaryFile) {
        words = FileUtils.readWords(dictionaryFile);
    }

    @Override
    /** Returns the most common letter in the set of valid words based on the current
     *  PATTERN. */

    public char getGuess(String pattern, List<Character> guesses) {
        return findGuess(pattern, guesses);
    }

    public List<String> matchPattern(String pattern) {
        List<String> newWords = new ArrayList<String>();
        for (int i = 0; i < words.size(); i++) {
            if (words.get(i).length() == pattern.length()) {
                newWords.add(words.get(i));
            }
        }
        List<String> newWords2 = new ArrayList<String>();
        for(int i = 0; i < newWords.size(); i++) {
            String currWord = newWords.get(i);
            for(int j = 0; j < currWord.length(); j++) {
                if(pattern.charAt(j) != '-' && pattern.charAt(j) != currWord.charAt(j)) {
                    break;
                }
                if (j == currWord.length() - 1) {
                    newWords2.add(currWord);
                }
            }
        }
        return newWords2;
    }

    public Map<Character, Integer> getMap(String pattern) {
        List<String> wordList;
        int letters = 0;
        for(int i = 0; i < pattern.length(); i++) {
            if (Character.isLetter(pattern.charAt(i))) {
                letters ++;
            }
        }
        if(letters == 0) {
            wordList = words;
        }
        else {
            wordList = matchPattern(pattern);
        }
        Map<Character, Integer> freqMap = new TreeMap<Character, Integer>();
        for(int i = 0; i < wordList.size(); i++) {
            String curr_word = wordList.get(i);
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

    public char findGuess(String pattern, List<Character> guesses) {
        Map <Character, Integer> freqMap = getMap(pattern);
        for(int i = 0; i < guesses.size(); i++) {
            if(freqMap.containsKey(guesses.get(i))) {
                freqMap.remove(guesses.get(i));
            }
        }
        Set<Character> keyset = freqMap.keySet();
        List<Character> keylist = new ArrayList<>(keyset);
        char commonKey = ' ';
        int highestFreq = 0;
        for(int i = 0; i < keylist.size(); i++) {
            if(freqMap.get(keylist.get(i)) > highestFreq) {
                highestFreq = freqMap.get(keylist.get(i));
                commonKey = keylist.get(i);
            }
        }
        return commonKey;
    }

    public static void main(String[] args) {
        PatternAwareLetterFreqGuesser palfg = new PatternAwareLetterFreqGuesser("data/example.txt");
        System.out.println(palfg.getGuess("-e--", List.of('e')));
        String pattern = "-e--";
        List<Character> guesses = new ArrayList<Character>();
        guesses.add('e');
        List<String> testWords = palfg.matchPattern(pattern);
        System.out.println(testWords);
        System.out.println(palfg.getMap(pattern));
        System.out.println(palfg.findGuess(pattern, guesses));
    }
}