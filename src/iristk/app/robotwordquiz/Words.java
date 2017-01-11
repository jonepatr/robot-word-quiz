package iristk.app.robotwordquiz;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.mozilla.javascript.edu.emory.mathcs.backport.java.util.Collections;

public class Words {
	private ArrayList<String> selectedWords = new ArrayList<String>();
	private ContextLoader contextLoader;
	private String wordsFile;

	public Words(String wordsFile, ContextLoader contextLoader) {
		this.wordsFile = wordsFile;
		this.contextLoader = contextLoader;
	}

	public void setWords(int numberOfWords) {
		selectedWords = getRandomWords(numberOfWords);
		contextLoader.load(selectedWords);
	}
	
	private String[] getWordList(Object words) {
		String theAnswer = words.toString().toLowerCase();
		String[] splittedAnswer = theAnswer.split(" ");
		return splittedAnswer;
	}
	
	public boolean isWord(Object answer) {
		boolean hasWord = false;
		if (answer != null) {
			for (String w : getWordList(answer)) {
				if (selectedWords.contains(w)) {
					hasWord = true;
				}
			}
		}
		return hasWord;
	}
	
	public String saidWord(Object answer) {
		ArrayList<String> unused = new ArrayList<String>();
		for (String w : getWordList(answer)) {
			if (selectedWords.contains(w)) {
				selectedWords.remove(w);
			} else {
				unused.add(w);
			}
		}
		if (unused.size() > 0)  {
			return unused.toString();
		} else {
			return null;
		}
	}
	
	public int wordsLeft() {
		return selectedWords.size();
	}
	
	public String getWordList() {
		return selectedWords.toString();
	}
	
	private ArrayList<String> getAllWords() {
		ArrayList<String> words = new ArrayList<String>();
		try {
			BufferedReader in = new BufferedReader(new FileReader(wordsFile));
			String inputLine;
			while((inputLine = in.readLine()) != null) {
				words.add(inputLine);
			}
	        in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return words;
	}
	
	private ArrayList<String> getRandomWords(int amount) {
		ArrayList<String> allWords = getAllWords();
		Collections.shuffle(allWords);
		ArrayList<String> someWords = new ArrayList<String>();
		for (int j = 0; j < amount; j++) {
			someWords.add(allWords.get(j).toLowerCase());
		}
		return someWords;
	}
}
