import java.util.Arrays;

public class WordGram implements Comparable<WordGram>{

	private int myHash;
	private String[] myWords;

	public WordGram(String[] source, int start, int size) {
		myWords = Arrays.copyOfRange(source, start, start + size); //created an array with a certain range
		hashCode();
	}

	@Override
	public int hashCode() { //method returns a value based on all the strings in instance field myWords.
		int hash = 0;
		for(int k=0; k < myWords.length; k++) {
			hash += myWords[k].hashCode();
			hash *= 11111; //this line is added to differentiate between two myWords with same elements but different order
		}
		myHash = hash; //value returned is stored in myHash
		return myHash;
	}

	@Override
	public String toString() {
		return String.join(" ", myWords); //grabs myWords and adds a space between each element. 
	}

	@Override
	public boolean equals(Object other) {
		if (other == null || ! (other instanceof WordGram)) {
			return false;
		}
		WordGram wg = (WordGram)other;
		if (wg.myHash == this.myHash) { //checks if the hashcode between two wordGram objects has the same words in the same order
			return true;	
		}
		else {
			return false;
		}
	}

	@Override
	public int compareTo(WordGram wg) {
		String s1 = this.toString(); 
		String s2 = wg.toString();
		return s1.compareTo(s2); //compares if the strings are the same or not
	}

	public int length() {
		return myWords.length;  //gets the length of myWords
	}

	public WordGram shiftAdd(String last) { //shifts the wordgram over by ine index and adds the string last to the final position
		String[] new_WordGram = new String[myWords.length]; 
		for (int i = 1; i<myWords.length; i++) {
			new_WordGram[i-1] = myWords[i]; //adds the elements in myWords into the new array representing the new wordgram.
		}
		int new_WordGram_size = new_WordGram.length -1;
		new_WordGram[new_WordGram_size] = last;
		return new WordGram(new_WordGram, 0, new_WordGram.length); //creates a new wordGram thats been shifted over with the string last in the lats index
	}
}
