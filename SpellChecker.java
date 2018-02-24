import java.util.*;
import java.util.Map.Entry;


public class SpellChecker {
	private Map<String,Integer> mWords;
	private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";
	public SpellChecker(Map<String, Integer> words) {
		if (words == null)
			mWords = new HashMap<String,Integer>();
		else
			mWords = words;
	}
	
	public static Set<String> edits1(String word) {
		Set<String> edits = new HashSet<String>();
		List<SplitWord> splits = SplitWord.allSplits(word);
		for (SplitWord split: splits) {
			String a = split.prefix;
			String b = split.suffix;
			int lb = b.length();
			if (lb > 0) {
				edits.add(a + b.substring(1)); // delete
				for (int i = 0; i < ALPHABET.length(); ++i)
					edits.add(a + ALPHABET.charAt(i) + b.substring(1)); // replace
			}
			if (lb > 1)
				edits.add(a + b.charAt(1) + b.charAt(0) + b.substring(2)); // transpose
			for (int i = 0; i < ALPHABET.length(); ++i)
				edits.add(a + ALPHABET.charAt(i) + b); // insert
		}
		return edits;
	}
	// This method is the solution of Question-2
	public Set<String> edits2(String word) {
		Set<String> edits = new HashSet<String>();
		Set<String> first_edits = edits1(word);
		for(String first_edit : first_edits){
			Set<String> second_edits = edits1(first_edit);
			for( String second_edit : second_edits){
				if(mWords.containsKey(second_edit)){
					edits.add(second_edit);
				}
			}
		}
		return edits;
				
	} 
	
	// We only check for single edits and return empty of none of the possibilities are in the dictionary
	List<String> check(String word) {
		List<String> alternatives = new ArrayList<String>();
		word = word.toLowerCase();
		
		if (mWords.containsKey(word)) {
			alternatives.add(word);
			return alternatives;
		}
		// The commented below line is for the solution of Question-1
		//Set<String> edits = edits1(word);
		Set<String> edits = edits2(word);
		System.out.println("Edits of "+word+":\n"+edits);
	    Map<String,Integer> alternative_mWords = new HashMap<String, Integer>();

		for (String w: edits){
			if (mWords.containsKey(w)){
				alternative_mWords.put(w, mWords.get(w));
			}
		}
				
	
	    List<Integer> words_by_occurance = new ArrayList<Integer>(alternative_mWords.values());
	    Collections.sort(words_by_occurance);
	    Collections.reverse(words_by_occurance);
	    
	    for(int i = 0 ; i < words_by_occurance.size(); i++){
	    	
	    	for (Entry<String, Integer> entry : alternative_mWords.entrySet()) {	
	    		 if(words_by_occurance.get(i) == entry.getValue()){
	    			 if(alternatives.contains(entry.getKey())){
	    				 continue;
	    			 }
	    			 alternatives.add(entry.getKey());	    			 
	    		 }
	    	}
	    }
	    System.out.println("Alternatives for \""+word+"\" (Sorted):");
	    for (int i = 0 ; i < alternatives.size(); i++) {
	    	System.out.println("word = " + alternatives.get(i) + ", number of occurences = " + alternative_mWords.get(alternatives.get(i)));
	    }
	    
		return alternatives;
	}

}
