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
		for (int k = 0; k < myWords.length - myOrder; k++) { //loops through the text minus the size of the order
			WordGram new_gram = new WordGram(myWords, k, myOrder); //created a wordgram with the constraint  of myWords, k, and the order
			if (!myMap.containsKey(new_gram)) { //checks if the map already contains the wordgram
				myMap.put(new_gram, new ArrayList<String>()); //If map doesn't contain the gram, it adds it with an empty arraylist
			}

			ArrayList<String> list = myMap.get(new_gram); //grabs the value for the wordgram
			list.add(myWords[k + myOrder]); //added the words following the order as a value into the arraylist
			myMap.put(new_gram, list); //puts the list back into the map with its corresponding key. 
		}
		WordGram exception_letter = new WordGram(myWords, myWords.length - myOrder, myOrder);
		if (!myMap.containsKey(exception_letter)) {
			myMap.put(exception_letter, new ArrayList<String>());
		}
		myMap.get(exception_letter).add(PSEUDO_EOS); //adds PESUDO_EOS if there is no more chars following the wordgram.
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
			if (!myMap.containsKey(key)) { //If the key isn't present in the map a new NoSuchElementException is thrown with its appropriate String
				System.out.println("Caught NoSuchElementException: " + e.getMessage());
			}	
		}
		return follows;
	}
}
