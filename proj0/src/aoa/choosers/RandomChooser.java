package aoa.choosers;

import edu.princeton.cs.algs4.StdRandom;
import aoa.utils.FileUtils;

import java.util.ArrayList;
import java.util.List;
public class RandomChooser implements Chooser {
    private final String chosenWord;
    private String pattern;

    public RandomChooser(int wordLength, String dictionaryFile) {
        // TODO: Fill in/change this constructor.
        //chosenWord = "";
        //throw exception if input length < 1
        pattern = "";
        if(wordLength < 1)
            throw new IllegalArgumentException();
        //read words of wordLength from dictionary
        List<String> words = FileUtils.readWordsOfLength(dictionaryFile, wordLength);
        //throw exception if no words found
        if(words.size() == 0)
            throw new IllegalStateException();
        //choose random word
        int numWords = words.size();
        int randomlyChosenWordNumber = StdRandom.uniform(numWords);
        chosenWord = words.get(randomlyChosenWordNumber);
        //set pattern to wordLength
        for(int i = 0; i < wordLength; i++) {
            pattern += "-";
        }
    }

    @Override
    public int makeGuess(char letter) {
        // TODO: Fill in this method.
        int counter = 0;
        StringBuilder patternBuilder = new StringBuilder(this.getPattern());
        for(int i = 0; i < chosenWord.length(); i++) {
            if (chosenWord.charAt(i) == letter) {
                counter++;
                patternBuilder.setCharAt(i, letter);
            }
        }
        pattern = patternBuilder.toString();
        return counter;
    }

    @Override
    public String getPattern() {
        // TODO: Fill in this method.
        return pattern;
    }

    @Override
    public String getWord() {
        // TODO: Fill in this method.
        return chosenWord;
    }
}
