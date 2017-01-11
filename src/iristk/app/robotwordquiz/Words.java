package iristk.app.robotwordquiz;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;

import javax.xml.bind.JAXBException;

import org.mozilla.javascript.edu.emory.mathcs.backport.java.util.Collections;
import org.xml.sax.SAXException;

import iristk.situated.SituatedDialogSystem;
import iristk.speech.OpenVocabularyContext;
import iristk.speech.SemanticGrammarContext;
import iristk.xml.flow.Random;

public class Words {
	private ArrayList<String> selectedWords = new ArrayList<String>();
	private SituatedDialogSystem system;
	
	public Words(SituatedDialogSystem s) {
		system = s;
	}
	
	public void setWords(int numberOfWords) {
		selectedWords = getRandomWords(numberOfWords);
		system.loadContext("words", new OpenVocabularyContext(system.getLanguage(), selectedWords));
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
			BufferedReader in = new BufferedReader(new FileReader(system.getPackageFile("words.txt").getAbsolutePath()));
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
