import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;


public class EfficientMarkov extends MarkovModel{
	Map<String,ArrayList<String>> myMap; //Created instance map that can be used throughout the class

	public EfficientMarkov(int order) {
		super(order);
		myMap = new HashMap<String,ArrayList<String>>();
	}
	@Override
	public void setTraining(String text) {
		myText = text;
		myMap.clear();				

		for (int k = 0; k < myText.length() - myOrder; k++) { //loops through the text minus the size of the order
			String gram = myText.substring(k, k + myOrder);
			if (!myMap.containsKey(gram)) { //checks if the map already contains the gram
				myMap.put(gram, new ArrayList<String>()); //If map doesn't contain the gram, it adds it with an empty arraylist
			}
			ArrayList<String> list = myMap.get(gram); //grabs the value for the gram
			list.add(myText.substring(k + myOrder, k + myOrder+1)); //added the substring following the order as a value into the arraylist
			myMap.put(gram, list); //puts the list back into the map with its corresponding key. 
		}
		String exception_letter = myText.substring(myText.length() - myOrder, myText.length());
		if (!myMap.containsKey(exception_letter)) {
			myMap.put(exception_letter, new ArrayList<String>());
		}
		myMap.get(exception_letter).add(PSEUDO_EOS); //adds PESUDO_EOS if there is no more chars following the gram.
		
	}
	@Override
	public ArrayList<String> getFollows(String key){
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
