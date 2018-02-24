import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class WordSet {
	private final Map<String,Integer> mWords = new HashMap<String, Integer>();
	
	public WordSet(String filename) throws IOException {
		super();
		loadWordFile(filename);
	}
	
	public Map<String, Integer> getWords() { return mWords; }
	
	public void loadWordFile(String filename) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(filename));
		mWords.clear();
		String line = reader.readLine();
		while (line != null) {
			String[] ws = line.split("\\W+");
			for (String w: ws){
				w = w.toLowerCase();
				if(mWords.containsKey(w))
					mWords.put(w, mWords.get(w) + 1);
				else
					mWords.put(w, 1);
			}		
			line = reader.readLine();
		}
		reader.close();
	}

}
