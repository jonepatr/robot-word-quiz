/*******************************************************************************
 * Copyright (c) 2014 Gabriel Skantze.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 * 
 * Contributors:
 *     Gabriel Skantze - initial API and implementation
 ******************************************************************************/
package iristk.app.robotwordquiz;

import iristk.situated.SituatedDialogSystem;
import iristk.situated.SystemAgentFlow;
import iristk.speech.OpenVocabularyContext;
import iristk.speech.Voice.Gender;
import iristk.speech.google.GoogleRecognizerFactory;
import iristk.speech.windows.WindowsSynthesizer;
import iristk.util.Language;

import java.io.File;
import java.util.ArrayList;

import iristk.app.robotwordquiz.RobotWordQuizFlow;
import iristk.flow.FlowModule;

public class RobotWordQuizSystem {

	public RobotWordQuizSystem() throws Exception {

		SituatedDialogSystem system = new SituatedDialogSystem(this.getClass());
		SystemAgentFlow systemAgentFlow = system.addSystemAgent();
		system.setLanguage(Language.ENGLISH_US);
		system.setupLogging(new File("c:/iristk_logging"), true);
		system.setupGUI();
		system.setupMonoMicrophone(new GoogleRecognizerFactory());
		system.setupFace(new WindowsSynthesizer(), Gender.FEMALE);
		Words words = new Words(system); 
		system.addModule(new FlowModule(new RobotWordQuizFlow(words, systemAgentFlow)));
		ArrayList<String> numbers = new ArrayList<String>();
		for (int i = 0; i < 100; i++) {
			numbers.add(String.valueOf(i));
		}
		system.loadContext("numbers", new OpenVocabularyContext(system.getLanguage(), numbers));
		system.setDefaultContext("default");
		system.loadPositions(system.getPackageFile("situation.properties"));		
		system.sendStartSignal();
	}
	
	public static void main(String[] args) throws Exception {
		new RobotWordQuizSystem();
	}

}
