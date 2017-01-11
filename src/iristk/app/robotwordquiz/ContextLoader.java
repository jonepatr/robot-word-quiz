package iristk.app.robotwordquiz;

import java.util.ArrayList;

import iristk.furhat.skill.SkillHandler;
import iristk.situated.SituatedDialogSystem;
import iristk.speech.OpenVocabularyContext;
import iristk.speech.RecognizerException;
import iristk.util.Language;

public class ContextLoader {
	
	SituatedDialogSystem situatedDialogSystem;
	SkillHandler skillHandler;
	Language language;
	
	public ContextLoader(SituatedDialogSystem s) {
		situatedDialogSystem = s;
	}
	
	public ContextLoader(SkillHandler s, Language l) {
		skillHandler = s;
		language = l;
	}
	
	public void load(ArrayList<String> words) {
		if (situatedDialogSystem != null) {
			situatedDialogSystem.loadContext("words", new OpenVocabularyContext(situatedDialogSystem.getLanguage(), words));
		} else {
			try {
				skillHandler.loadContext("words", new OpenVocabularyContext(language, words));
			} catch (RecognizerException e) {
				e.printStackTrace();
			}
		}
	}
}
