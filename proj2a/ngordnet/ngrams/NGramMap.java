package ngordnet.ngrams;

import edu.princeton.cs.algs4.In;

import java.io.File;

import java.io.FileNotFoundException;
import java.util.*;

/**
 * An object that provides utility methods for making queries on the
 * Google NGrams dataset (or a subset thereof).
 *
 * An NGramMap stores pertinent data from a "words file" and a "counts
 * file". It is not a map in the strict sense, but it does provide additional
 * functionality.
 *
 * @author Josh Hug
 */
public class NGramMap {

    private static final int MIN_YEAR = 1400;
    private static final int MAX_YEAR = 2100;
    private TreeMap<String, TimeSeries> freqByWord;
    private TimeSeries freqByYear;

    /**
     * Constructs an NGramMap from WORDSFILENAME and COUNTSFILENAME.
     */
    public NGramMap(String wordsFilename, String countsFilename) {
        In wordsFile = new In(wordsFilename);
        In countsFile = new In(countsFilename);
        freqByWord = new TreeMap<>();
        String prevWord = null;
        TimeSeries ts = new TimeSeries();
        while (wordsFile.hasNextLine()) {
            try {
                String word = wordsFile.readString();
                int year = wordsFile.readInt();
                int freq = wordsFile.readInt();
                wordsFile.readInt(); // this is only needed to skip over column 4
                if (!word.equals(prevWord) && prevWord != null) {
                    freqByWord.put(prevWord, ts);
                    ts = new TimeSeries();
                    ts.put(year, (double) freq);
                } else {
                    ts.put(year, (double) freq);
                }
                prevWord = word;
            } catch (NoSuchElementException e) {
                //do nothing
            }
        }

        freqByYear = new TimeSeries();
        try {
            File r = new File(countsFilename);
            Scanner sc = new Scanner(r);
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                StringTokenizer tokens = new StringTokenizer(line, ",");
                int year = Integer.parseInt(tokens.nextToken());
                double freq = Double.parseDouble(tokens.nextToken());
                freqByYear.put(year, freq);
            }
        } catch (FileNotFoundException e) {
           // do nothing
        }
    }

    /**
     * Provides the history of WORD between STARTYEAR and ENDYEAR, inclusive of both ends. The
     * returned TimeSeries should be a copy, not a link to this NGramMap's TimeSeries. In other
     * words, changes made to the object returned by this function should not also affect the
     * NGramMap. This is also known as a "defensive copy".
     */
    public TimeSeries countHistory(String word, int startYear, int endYear) {
        return new TimeSeries(freqByWord.get(word), startYear, endYear);
    }

    /**
     * Provides the history of WORD. The returned TimeSeries should be a copy,
     * not a link to this NGramMap's TimeSeries. In other words, changes made
     * to the object returned by this function should not also affect the
     * NGramMap. This is also known as a "defensive copy".
     */
    public TimeSeries countHistory(String word) {
        Set<Integer> keyset = freqByWord.get(word).keySet();
        ArrayList<Integer> keyArray = new ArrayList<>(keyset);
        int startYear = keyArray.get(0);
        int endYear = keyArray.get(keyArray.size() - 1);
        return new TimeSeries(freqByWord.get(word), startYear, endYear);
    }

    /**
     * Returns a defensive copy of the total number of words recorded per year in all volumes.
     */
    public TimeSeries totalCountHistory() {
        Set<Integer> keyset = freqByYear.keySet();
        ArrayList<Integer> keyArray = new ArrayList<>(keyset);
        int startYear = keyArray.get(0);
        int endYear = keyArray.get(keyArray.size() - 1);
        return new TimeSeries(freqByYear, startYear, endYear);
    }

    /**
     * Provides a TimeSeries containing the relative frequency per year of WORD between STARTYEAR
     * and ENDYEAR, inclusive of both ends.
     */
    public TimeSeries weightHistory(String word, int startYear, int endYear) {
        TimeSeries ts = countHistory(word, startYear, endYear);
        TimeSeries wHTS = new TimeSeries();
        Set<Integer> keyset = ts.keySet();
        for (Integer key : keyset) {
            wHTS.put(key, ts.get(key) / freqByYear.get(key));
        }
        return wHTS;
    }

    /**
     * Provides a TimeSeries containing the relative frequency per year of WORD compared to
     * all words recorded in that year. If the word is not in the data files, return an empty
     * TimeSeries.
     */
    public TimeSeries weightHistory(String word) {
        TimeSeries ts = countHistory(word);
        TimeSeries wHTS = new TimeSeries();
        Set<Integer> keyset = ts.keySet();
        for (Integer key : keyset) {
            wHTS.put(key, ts.get(key) / freqByYear.get(key));
        }
        return wHTS;
    }

    /**
     * Provides the summed relative frequency per year of all words in WORDS
     * between STARTYEAR and ENDYEAR, inclusive of both ends. If a word does not exist in
     * this time frame, ignore it rather than throwing an exception.
     */
    public TimeSeries summedWeightHistory(Collection<String> words,
                                          int startYear, int endYear) {
        TimeSeries sWH = new TimeSeries();
        for (String word : words) {
            sWH = sWH.plus(weightHistory(word, startYear, endYear));
        }
        return sWH;
    }

    /**
     * Returns the summed relative frequency per year of all words in WORDS.
     */
    public TimeSeries summedWeightHistory(Collection<String> words) {
        TimeSeries sWH = new TimeSeries();
        for (String word : words) {
            sWH = sWH.plus(weightHistory(word));
        }
        return sWH;
    }
}
