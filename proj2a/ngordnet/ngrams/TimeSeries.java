package ngordnet.ngrams;

import java.util.*;

/**
 * An object for mapping a year number (e.g. 1996) to numerical data. Provides
 * utility methods useful for data analysis.
 *
 * @author Josh Hug
 */
public class TimeSeries extends TreeMap<Integer, Double> {

    private static final int MIN_YEAR = 1400;
    private static final int MAX_YEAR = 2100;

    /**
     * Constructs a new empty TimeSeries.
     */
    public TimeSeries() {
        super();
    }

    /**
     * Creates a copy of TS, but only between STARTYEAR and ENDYEAR,
     * inclusive of both end points.
     */
    public TimeSeries(TimeSeries ts, int startYear, int endYear) {
        super();
        Set<Integer> keyset = ts.keySet();
        for (Integer key : keyset) {
            if (key >= startYear && key <= endYear) {
                this.put(key, ts.get(key));
            }
        }
    }

    /**
     * Returns all years for this TimeSeries (in any order).
     */
    public List<Integer> years() {
        ArrayList<Integer> yearList = new ArrayList<>();
        Set<Integer> keyset = this.keySet();
        for (Integer key : keyset) {
            yearList.add(key);
        }
        return yearList;
    }

    /**
     * Returns all data for this TimeSeries (in any order).
     * Must be in the same order as years().
     */
    public List<Double> data() {
        ArrayList<Double> dataList = new ArrayList<>();
        Collection<Double> values = this.values();
        for (Double val : values) {
            dataList.add(val);
        }
        return dataList;
    }

    /**
     * Returns the year-wise sum of this TimeSeries with the given TS. In other words, for
     * each year, sum the data from this TimeSeries with the data from TS. Should return a
     * new TimeSeries (does not modify this TimeSeries).
     *
     * If both TimeSeries don't contain any years, return an empty TimeSeries.
     * If one TimeSeries contains a year that the other one doesn't, the returned TimeSeries
     * should store the value from the TimeSeries that contains that year.
     */
    public TimeSeries plus(TimeSeries ts) {
        if (this == null && ts == null) {
            return new TimeSeries();
        }
        TimeSeries combinedTS = new TimeSeries();
        for (int i = 0; i < this.years().size(); i++) {
            int year = this.years().get(i);
            if (!ts.containsKey(year)) {
                combinedTS.put(year, this.get(year));
            } else {
                combinedTS.put(year, this.get(year) + ts.get(year));
            }
        }
        for (int i = 0; i < ts.years().size(); i++) {
            int year = ts.years().get(i);
            if (!combinedTS.containsKey(year)) {
                combinedTS.put(year, ts.get(year));
            }
        }
        return combinedTS;
    }

    /**
     * Returns the quotient of the value for each year this TimeSeries divided by the
     * value for the same year in TS. Should return a new TimeSeries (does not modify this
     * TimeSeries).
     *
     * If TS is missing a year that exists in this TimeSeries, throw an
     * IllegalArgumentException.
     * If TS has a year that is not in this TimeSeries, ignore it.
     */
    public TimeSeries dividedBy(TimeSeries ts) {
        TimeSeries quotients = new TimeSeries();
        for (int i = 0; i < this.size(); i++) {
            int year = this.years().get(i);
            if (!ts.containsKey(year)) {
                throw new IllegalArgumentException();
            }
            quotients.put(year, this.get(year) / ts.get(year));
        }
        return quotients;
    }
}
