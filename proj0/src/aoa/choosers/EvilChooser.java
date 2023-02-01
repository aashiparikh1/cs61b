package aoa.choosers;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.ArrayList;

import edu.princeton.cs.algs4.StdRandom;
import aoa.utils.FileUtils;

public class EvilChooser implements Chooser {
    private String pattern;
    private List<String> wordPool;

    public EvilChooser(int wordLength, String dictionaryFile) {
        // TODO: Fill in this constructor.
        pattern = "";
        //throw exception if input length < 1
        if(wordLength < 1)
            throw new IllegalArgumentException();
        //read words of wordLength from dictionary
        wordPool = FileUtils.readWordsOfLength(dictionaryFile, wordLength);
        //throw exception if no words found
        if(wordPool.size() == 0)
            throw new IllegalStateException();
        //choose random word
        int numWords = wordPool.size();
        int randomlyChosenWordNumber = StdRandom.uniform(numWords);
        for(int i = 0; i < wordLength; i++) {
            pattern += "-";
        }
    }

    @Override
    public int makeGuess(char letter) {
        // TODO: Fill in this method.
        Map<String, ArrayList> wordFamilies = new TreeMap<String, ArrayList>();
        String tempPattern = "";
        for(int i = 0; i < wordPool.size(); i++) {
            String curr_word = wordPool.get(i);
            for(int j = 0; j < curr_word.length(); j++) {
                char curr_char = curr_word.charAt(j);
                if (curr_char == letter)
                    tempPattern += letter;
                else
                    tempPattern += "-";
            }
            if (!wordFamilies.containsKey(tempPattern)) {
                wordFamilies.put(tempPattern, new ArrayList<String>());
            }
            wordFamilies.get(tempPattern).add(curr_word);
            tempPattern = "";
        }
        int largestFamilySize = 0;
        String largestFamilyPattern = "";
        for (Map.Entry<String, ArrayList> entry : wordFamilies.entrySet()) {
            if (entry.getValue().size() > largestFamilySize) {
                largestFamilySize = entry.getValue().size();
                largestFamilyPattern = entry.getKey();
            }
        }
        wordPool = wordFamilies.get(largestFamilyPattern);
        pattern = largestFamilyPattern;
        int letterOccurrances = 0;
        for(int i = 0; i < pattern.length(); i++) {
            if(pattern.charAt(i) == letter)
                letterOccurrances ++;
        }
        return letterOccurrances;
    }

    @Override
    public String getPattern() {
        // TODO: Fill in this method.
        return pattern;
    }

    @Override
    public String getWord() {
        // TODO: Fill in this method.
        if(wordPool.size() == 1) {
            return wordPool.get(0);
        }
        int i = (int) (Math.random() * wordPool.size());
        return wordPool.get(i);
    }
}
