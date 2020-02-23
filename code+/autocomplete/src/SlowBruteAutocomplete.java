import java.util.*;
/**
 *
 * Using a list of Term objects that start with prefix, this implementation uses the Collections.sort() method
 * to find the top term(s).
 * SlowBruteAutocomplete is the slowest of the Autocomplete methods
 * For example, when search is az, size is 889, and # matches is 50, BruteAutocomplete had time	0.01631432,
 * BinarySearchAutocomplete had time 0.00015440, HashListAutocomplete had time 0.00000933,
 * and SlowBruteAutocomplete had time 0.02344717, the longest of the times.
 *
 * Where N is the total number of Terms in myTerms, M is the total number of Terms that start with the prefix,
 * and k is a parameter of the method topMatches,
 * SlowBrute AutoComplete has runtime O(N + M log(M) + k)
 * It has to go through the elements at all of the indexes of terms and not just the ones between the firstIndex and lastIndex
 * And then sort the matches and take the sublist of the first k matches
 * @author Luke Zhuo
 */
public class SlowBruteAutocomplete extends BruteAutocomplete {
    public SlowBruteAutocomplete(String[] terms, double[] weights){
        super(terms, weights);
    }

    /**
     * Required by the Autocompletor interface. Returns a list containing the
     * 	  k words in myTerms with the largest weight which match the given prefix,
     * 	  in descending weight order. If less than k words exist matching the given
     * 	  prefix (including if no words exist), then the array instead contains all
     * 	  those words.
     * @param prefix - A prefix which all returned words must start with
     * @param k- The (maximum) number of words to be returned
     * @return A list of the k words with the largest weights among all words
     * 	          starting with prefix, in descending weight order. If less than k
     * 	          such words exist, return a list containing all those words. If
     * 	          no such words exist, return an empty array
     */
    @Override
    public List<Term> topMatches(String prefix, int k) {
        if(k<0) return null;
        List<Term> list = new ArrayList<>();
        for(Term t : myTerms){
            if(t.getWord().startsWith(prefix)){
                list.add(t);
            }
        }
        Collections.sort(list, Comparator.comparing(Term::getWeight).reversed());
        return list.subList(0, Math.min(list.size(), k));
    }
}
