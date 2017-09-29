import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;


public class EfficientMarkov extends MarkovModel{
	Map<String,ArrayList<String>> myMap;

	public EfficientMarkov(int order) {
		super(order);
		myMap = new HashMap<String,ArrayList<String>>();
	}
	@Override
	public void setTraining(String text) {
		myText = text;
		myMap.clear();				

		for (int k = 0; k < myText.length() - myOrder; k++) {
			String gram = myText.substring(k, k + myOrder);
			if (!myMap.containsKey(gram)) {
				myMap.put(gram, new ArrayList<String>());
			}
			ArrayList<String> list = myMap.get(gram);
			list.add(myText.substring(k + myOrder, k + myOrder+1));
			myMap.put(gram, list);
		}
		String excpetion_letter = myText.substring(myText.length() - myOrder, myText.length());
		if (!myMap.containsKey(excpetion_letter)) {
			myMap.put(excpetion_letter, new ArrayList<String>());
		}
		myMap.get(excpetion_letter).add(PSEUDO_EOS);
		
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
			if (!myMap.containsKey(key)) {
				System.out.println("Caught NoSuchElementException: " + e.getMessage());
			}	
		}

		return follows;
	}
}
