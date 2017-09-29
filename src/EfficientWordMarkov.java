import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public class EfficientWordMarkov extends WordMarkovModel {
	Map<WordGram,ArrayList<String>> myMap;

	public EfficientWordMarkov(int order) {
		super(order);
		myMap = new HashMap<WordGram,ArrayList<String>>();
	}

	@Override
	public void setTraining(String text) {
		myWords = text.split("\\s+");
		myMap.clear();				
		for (int k = 0; k < myWords.length - myOrder; k++) {
			WordGram new_gram = new WordGram(myWords, k, myOrder);
			if (!myMap.containsKey(new_gram)) {
				myMap.put(new_gram, new ArrayList<String>());
			}

			ArrayList<String> list = myMap.get(new_gram);
			list.add(myWords[k + myOrder]);
			myMap.put(new_gram, list);
		}
		WordGram excpetion_gram = new WordGram(myWords, myWords.length - myOrder, myOrder);
		if (!myMap.containsKey(excpetion_gram)) {
			myMap.put(excpetion_gram, new ArrayList<String>());
		}
		myMap.get(excpetion_gram).add(PSEUDO_EOS);
	}

	@Override
	public ArrayList<String> getFollows(WordGram key){
		ArrayList<String> follows = new ArrayList<String>();
		try {
			if(myMap.containsKey(key)) {
				return myMap.get(key);
			}
		}
		catch (NoSuchElementException e) {
			if (!myMap.containsKey(key)) {
				System.out.println("Caught NoSuchElementException: " + e.getMessage());
			}	
		}
		return follows;
	}
}
