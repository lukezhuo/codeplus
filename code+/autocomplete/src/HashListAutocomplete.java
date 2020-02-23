import java.util.*;
/**
 *
 * This implementation uses a HashSet
 * to find the top term(s).
 *
 *
 * @author Luke Zhuo
 */
public class HashListAutocomplete implements Autocompletor {
    private static final int MAX_PREFIX = 10;
    private Map<String, List<Term>>  myMap;
    private int mySize;

    /**
     * Given arrays of words and weights, initialize myMap to a HashMap with Terms as values that are sorted.
     *
     *
     * @param terms
     *            - A list of words to form terms from
     * @param weights
     *            - A corresponding list of weights, such that terms[i] has
     *            weight[i].
     * @return a HashListAutocomplete whose myMap object has keys prefixes and values Term objects.
     * @throws a
     *             NullPointerException if either argument passed in is null
     */
    public HashListAutocomplete(String[] terms, double[] weights){
        if (terms == null || weights == null) {
            throw new NullPointerException("One or more arguments null");
        }
        initialize(terms, weights);
    }
    /**
     * Required by the Autocompletor interface. Returns a list containing the
     * k words in myMap with the largest weight which match the given prefix,
     * in descending weight order. If less than k words exist matching the given
     * prefix (including if no words exist), then the list instead contains all
     * those words.
     *
     * @param prefix
     *            - A prefix which all returned words must start with
     * @param k
     *            - The (maximum) number of words to be returned
     * @return A list of the k words with the largest weights among all words
     *         starting with prefix, in descending weight order. If less than k
     *         such words exist, return a list containing all those words. If
     *         no such words exist, return an empty list
     * @throws NullPointerException if prefix is null
     */
    @Override
    public List<Term> topMatches(String prefix, int k) {
        if(prefix == null){
            throw new NullPointerException("One or more arguments null");
        }
        String usedPrefix = prefix.substring(0, Math.min(prefix.length(), MAX_PREFIX));
        if(myMap.containsKey(usedPrefix)){
            List<Term> all = myMap.get(usedPrefix);
            List<Term> list = all.subList(0, Math.min(k, all.size()));
            return list;
        }
        else {
            return new ArrayList<>();
        }
    }

    /**
     * Assigns the initial values to the keys and values of myMap from the parameters and sorts the values
     * @param terms is array of Strings for words in each Term
     * @param weights is corresponding weight for word in terms
     */
    @Override
    public void initialize(String[] terms, double[] weights) {
        myMap = new HashMap<>();
        for(int i=0; i<terms.length; i++){
            String term = terms[i];
            int size = term.length();
            if(term.length() > MAX_PREFIX) size = MAX_PREFIX;
            for(int j=0; j<=size; j++) {
                String pfix = terms[i].substring(0, j);
                myMap.putIfAbsent(pfix, new ArrayList<Term>());
                myMap.get(pfix).add(new Term(terms[i], weights[i]));
            }
        }
        for(String a: myMap.keySet()){
            Collections.sort(myMap.get(a),Comparator.comparing(Term::getWeight).reversed());
        }
    }

    /**
     * Sums all of the Bytes of every key and every element of every value of myMap
     * @return an int which is equivalent to the number of Bytes of memory utilized by myMap
     */
    @Override
    public int sizeInBytes() {
        if (mySize == 0) {
            for(List <Term> t : myMap.values()) {
                for(Term at : t) {
                    mySize += BYTES_PER_DOUBLE +
                            BYTES_PER_CHAR * at.getWord().length();
                }
            }
            for(String ky: myMap.keySet()){
                mySize += BYTES_PER_CHAR * ky.length();
            }
        }
        return mySize;
    }
}
