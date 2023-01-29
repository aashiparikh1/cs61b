package aoa.guessers;

import aoa.utils.FileUtils;
import org.antlr.v4.runtime.tree.Tree;

import java.util.*;

public class PAGALetterFreqGuesser implements Guesser {
    private final List<String> words;

    public PAGALetterFreqGuesser(String dictionaryFile) {
        words = FileUtils.readWords(dictionaryFile);
    }

    @Override
    /** Returns the most common letter in the set of valid words based on the current
     *  PATTERN and the GUESSES that have been made. */
    public char getGuess(String pattern, List<Character> guesses) {
        return findGuess(pattern, guesses);
    }

    public List<String> matchPattern(String pattern, List<Character> guesses) {
       //keep words with correctly guessed letters or words without incorrect letters
        HashSet<String> words1 = new HashSet<>();
        HashSet<String> wordsHash = new HashSet<>(words);
        for (int i = 0; i < guesses.size(); i++) {
            if (pattern.contains(guesses.get(i).toString())) {
                for (String value : wordsHash) {
                    if (value.contains(guesses.get(i).toString())) {
                        if(!words1.contains(value))
                            words1.add(value);
                    }
                }
            } else {
                for (String val : wordsHash) {
                    if (!(val.contains(guesses.get(i).toString()))) {
                        if(!words1.contains(val))
                            words1.add(val);

                    }
                }
            }
        }
        //remove words with missing guess in pattern
        HashSet<String> words2 = new HashSet<>(words1);
        for (int i = 0; i < guesses.size(); i++) {
            if (!pattern.contains(guesses.get(i).toString())) {
                for (String val : words2) {
                    if (val.contains(guesses.get(i).toString()))
                        words1.remove(val);
                }
            }
        }
        // eliminate words of different length
        HashSet<String> newWords = new HashSet<>();
         for (String val : words1) {
            if (val.length() == pattern.length()) {
                newWords.add(val);
            }
        }
        /**
         *keep words that have matched letter at correct index
         *keep words that don't have more of the letter in different positions
         */
        HashSet<String> newWords2 = new HashSet<>();
        for(String val : newWords) {
            for(int j = 0; j < val.length(); j++) {
                if(pattern.charAt(j) != '-' && pattern.charAt(j) != val.charAt(j)) {
                    break;
                }
                if (j == val.length() - 1) {
                    newWords2.add(val);
                }
            }
        }
        // remove repeat characters
        HashSet<String> newWords3 = new HashSet<>();
        int count = 0;
        for(int i = 0; i < pattern.length(); i++) {
            if (pattern.charAt(i) == '-')
                count ++;
        }
        if(count == pattern.length())
            newWords3 = newWords2;
        else {
            for(int i = 0; i < pattern.length(); i++) {
                if(pattern.charAt(i) != '-') {
                    for(String val :  newWords2) {
                        for(int k = 0; k < val.length(); k++) {
                            if(k != i && val.charAt(k) == pattern.charAt(i) && pattern.charAt(k) != val.charAt(k)) {
                                break;
                            }
                            if(k == val.length() - 1) {
                                newWords3.add(val);
                            }
                        }
                    }
                }
            }
        }
        // remove all words with chars occurring > in pattern
        // map of chars and counts in pattern
        Map<Character, Integer> patternMap= new TreeMap<>();
        for(int i = 0; i < pattern.length(); i++) {
            char curr_char = pattern.charAt(i);
            if (curr_char != '-' && !patternMap.containsKey(curr_char))
                patternMap.put(curr_char, 1);
            else if (curr_char != '-') {
                int old_count = patternMap.get(curr_char);
                patternMap.replace(curr_char, old_count, old_count + 1);
            }
        }
        for(Map.Entry<Character, Integer> entry : patternMap.entrySet()) {
            HashSet<String> temp = new HashSet<>(newWords3);
            for(String val : temp) {
                int counter = 0;
                for(int i = 0; i < val.length(); i++) {
                    if (val.charAt(i) == entry.getKey()) {
                        counter ++;
                    }
                }
                if (counter != entry.getValue()) {
                    newWords3.remove(val);
                }
            }
        }
        ArrayList<String> nw3
                = new ArrayList<>(newWords3);

        return nw3;
    }


    public Map<Character, Integer> getMap(String pattern, List<Character> guesses) {
        List<String> wordList;
        int letters = 0;
        for(int i = 0; i < pattern.length(); i++) {
            if (Character.isLetter(pattern.charAt(i))) {
                letters ++;
            }
        }
        if(letters == 0 && guesses.size()==0) {
            wordList = words;
        }
        else {
            wordList = matchPattern(pattern, guesses);
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
        Map <Character, Integer> freqMap = getMap(pattern, guesses);
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
        PAGALetterFreqGuesser pagalfg = new PAGALetterFreqGuesser("data/example.txt");
        System.out.println(pagalfg.getGuess("----", List.of('e')));
    }
}
