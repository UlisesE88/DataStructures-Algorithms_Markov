import java.util.Arrays;

public class WordGram implements Comparable<WordGram>{

	private int myHash;
	private String[] myWords;

	public WordGram(String[] source, int start, int size) {
		myWords = Arrays.copyOfRange(source, start, start + size);
		hashCode();
	}

	@Override
	public int hashCode() {
		int hash = 0;
		for(int k=0; k < myWords.length; k++) {
			hash += myWords[k].hashCode();
			hash *= 11111;
		}
		myHash = hash;
		return myHash;
	}

	@Override
	public String toString() {
		return String.join(" ", myWords);
	}

	@Override
	public boolean equals(Object other) {
		if (other == null || ! (other instanceof WordGram)) {
			return false;
		}
		WordGram wg = (WordGram)other;
		if (wg.myHash == this.myHash) {
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
		return s1.compareTo(s2);
	}

	public int length() {
		return myWords.length; 
	}

	public WordGram shiftAdd(String last) {
		String[] new_WordGram = new String[myWords.length];
		for (int i = 1; i<myWords.length; i++) {
			new_WordGram[i-1] = myWords[i];
		}
		int new_WordGram_size = new_WordGram.length -1;
		new_WordGram[new_WordGram_size] = last;
		return new WordGram(new_WordGram, 0, new_WordGram.length);
	}
}
