import java.io.IOException;
import java.util.Iterator;
import java.util.Set;
import java.util.List;
import java.util.Map;

public class SpellCheckTest {
	public static final String WORDS_FILE = "data/big.txt";
	
	public static void main(String[] args) throws IOException {
		WordSet wordSet = new WordSet(WORDS_FILE);
		Map<String, Integer> words = wordSet.getWords();
		System.out.println("Dictionary:\n" + words);
		SpellChecker checker = new SpellChecker(words);
		String toCheck = "atson";
		System.out.println("Checking \"" + toCheck + "\": " + checker.check(toCheck));
	
	}
}
