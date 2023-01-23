import java.util.ArrayList;
import java.util.List;

public class ListExercises {

    /** Returns the total sum in a list of integers */
	public static int sum(List<Integer> L) {
        int sum = 0;
        for(int i = 0; i < L.size(); i++) {
            sum += L.get(i);
        }
        return sum;
    }

    /** Returns a list containing the even numbers of the given list */
    public static List<Integer> evens(List<Integer> L) {
        ArrayList<Integer> evens = new ArrayList<Integer>();
        for (int i = 0; i < L.size(); i++) {
            if (L.get(i)%2 == 0) {
                evens.add(L.get(i));
            }
        }
        return evens;
    }

    /** Returns a list containing the common item of the two given lists */
    public static List<Integer> common(List<Integer> L1, List<Integer> L2) {
        ArrayList<Integer> common = new ArrayList<Integer>();
        for (int i = 0; i < L1.size(); i++){
            for (int j = 0; j < L2.size(); j++){
                if(L1.get(i) == L2.get(j)) {
                    common.add(L1.get(i));
                }
            }
        }
        return common;
    }


    /** Returns the number of occurrences of the given character in a list of strings. */
    public static int countOccurrencesOfC(List<String> words, char c) {
        int numC = 0;
        for (int i = 0; i < words.size(); i++) {
            for(int j = 0; j < words.get(i).length(); j++) {
                if(words.get(i).charAt(j) == c) {
                    numC ++;
                }
            }
        }
        return numC;
    }
}
