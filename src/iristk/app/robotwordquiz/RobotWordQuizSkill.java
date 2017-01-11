package iristk.app.robotwordquiz;

import java.io.File;
import java.util.ArrayList;

import org.slf4j.Logger;

import iristk.furhat.skill.FlowResource;
import iristk.furhat.skill.Skill;
import iristk.furhat.skill.SkillHandler;
import iristk.furhat.skill.TextFileResource;
import iristk.speech.OpenVocabularyContext;
import iristk.system.IrisUtils;
import iristk.util.Language;
import iristk.util.Record;

public class RobotWordQuizSkill extends Skill {

	private static final String RECOGNIZER_OPEN = "open";
	
	private static Logger logger = IrisUtils.getLogger(RobotWordQuizSkill.class); 
	
	private RobotWordQuizFlow flow;
	private File propertiesFile;
	private String name = "RobotWordQuizSkill";
	private Language language = Language.ENGLISH_US;
	private String recognizer = "open";

	public RobotWordQuizSkill() {
		this.propertiesFile = getPackageFile("skill.properties");
		addResource(new TextFileResource(this, "Properties", propertiesFile));
		try {
			Record config = Record.fromProperties(propertiesFile);
			name = config.getString("name", name);
			language = new Language(config.getString("language", language.getCode()));
			recognizer = config.getString("recognizer", recognizer);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		addResource(new FlowResource(this, "Flow", getSrcFile("RobotWordQuizFlow.xml")));
		getRequirements().setLanguage(language);
		getRequirements().setOpenVocabulary(recognizer.equals(RECOGNIZER_OPEN));
		addEntriesFromFlow(RobotWordQuizFlow.class, () -> flow);
	}
	
	@Override
	public String getName() {
		return name;
	}

	@Override
	public void init(SkillHandler handler) throws Exception {
		ArrayList<String> numbers = new ArrayList<String>();
		for (int i = 0; i < 100; i++) {
			numbers.add(String.valueOf(i));
		}
		handler.loadContext("numbers", new OpenVocabularyContext(language, numbers));
		handler.setDefaultContext("default");
		Words words = new Words(
			getPackageFile("words.txt").getAbsolutePath(),
			new ContextLoader(handler, language)
		); 
		flow = new RobotWordQuizFlow(words, handler.getSystemAgentFlow());
	}

	@Override
	public void stop(SkillHandler handler) throws Exception {
	}

}
