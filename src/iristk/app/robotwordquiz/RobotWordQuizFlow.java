package iristk.app.robotwordquiz;

import java.util.List;
import java.io.File;
import iristk.xml.XmlMarshaller.XMLLocation;
import iristk.system.Event;
import iristk.flow.*;
import iristk.util.Record;
import static iristk.util.Converters.*;
import static iristk.flow.State.*;

public class RobotWordQuizFlow extends iristk.flow.Flow {

	private Words words;
	private iristk.situated.SystemAgentFlow agent;
	private iristk.situated.SystemAgent system;
	private int guess;
	private int winningScore;
	private String[] saidWords;

	private void initVariables() {
		system = (iristk.situated.SystemAgent) agent.getSystemAgent();
		guess = asInteger(0);
		winningScore = asInteger(10);
		saidWords = (String[]) new String[20];
	}

	public iristk.situated.SystemAgent getSystem() {
		return this.system;
	}

	public void setSystem(iristk.situated.SystemAgent value) {
		this.system = value;
	}

	public int getGuess() {
		return this.guess;
	}

	public void setGuess(int value) {
		this.guess = value;
	}

	public int getWinningScore() {
		return this.winningScore;
	}

	public void setWinningScore(int value) {
		this.winningScore = value;
	}

	public String[] getSaidWords() {
		return this.saidWords;
	}

	public void setSaidWords(String[] value) {
		this.saidWords = value;
	}

	public Words getWords() {
		return this.words;
	}

	public iristk.situated.SystemAgentFlow getAgent() {
		return this.agent;
	}

	@Override
	public Object getVariable(String name) {
		if (name.equals("system")) return this.system;
		if (name.equals("guess")) return this.guess;
		if (name.equals("winningScore")) return this.winningScore;
		if (name.equals("saidWords")) return this.saidWords;
		if (name.equals("words")) return this.words;
		if (name.equals("agent")) return this.agent;
		return null;
	}


	public RobotWordQuizFlow(Words words, iristk.situated.SystemAgentFlow agent) {
		this.words = words;
		this.agent = agent;
		initVariables();
	}

	@Override
	public State getInitialState() {return new Greeting();}


	private class Idle extends State {

		final State currentState = this;


		@Override
		public void setFlowThread(FlowRunner.FlowThread flowThread) {
			super.setFlowThread(flowThread);
		}

		@Override
		public void onentry() throws Exception {
			int eventResult;
			Event event = new Event("state.enter");
			// Line: 18
			try {
				EXECUTION: {
					int count = getCount(1174290147) + 1;
					incrCount(1174290147);
					iristk.situated.SystemAgentFlow.attendNobody state0 = agent.new attendNobody();
					if (!flowThread.callState(state0, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\kalins\\IrisTK\\app\\robot-word-quiz\\src\\iristk\\app\\quiz\\RobotWordQuizFlow.xml"), 18, 12)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\kalins\\IrisTK\\app\\robot-word-quiz\\src\\iristk\\app\\quiz\\RobotWordQuizFlow.xml"), 18, 12));
			}
		}

		@Override
		public int onFlowEvent(Event event) throws Exception {
			int eventResult;
			int count;
			// Line: 21
			try {
				count = getCount(1285044316) + 1;
				if (event.triggers("sense.user.enter")) {
					incrCount(1285044316);
					eventResult = EVENT_CONSUMED;
					EXECUTION: {
						iristk.situated.SystemAgentFlow.attend state1 = agent.new attend();
						state1.setTarget(event.get("user"));
						if (!flowThread.callState(state1, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\kalins\\IrisTK\\app\\robot-word-quiz\\src\\iristk\\app\\quiz\\RobotWordQuizFlow.xml"), 21, 36)))) {
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
						// Line: 23
						Greeting state2 = new Greeting();
						flowThread.gotoState(state2, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\kalins\\IrisTK\\app\\robot-word-quiz\\src\\iristk\\app\\quiz\\RobotWordQuizFlow.xml"), 23, 28)));
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					if (eventResult != EVENT_IGNORED) return eventResult;
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\kalins\\IrisTK\\app\\robot-word-quiz\\src\\iristk\\app\\quiz\\RobotWordQuizFlow.xml"), 21, 36));
			}
			eventResult = super.onFlowEvent(event);
			if (eventResult != EVENT_IGNORED) return eventResult;
			eventResult = callerHandlers(event);
			if (eventResult != EVENT_IGNORED) return eventResult;
			return EVENT_IGNORED;
		}

	}


	private class Dialog extends State {

		final State currentState = this;


		@Override
		public void setFlowThread(FlowRunner.FlowThread flowThread) {
			super.setFlowThread(flowThread);
		}

		@Override
		public void onentry() throws Exception {
			int eventResult;
			Event event = new Event("state.enter");
		}

		@Override
		public int onFlowEvent(Event event) throws Exception {
			int eventResult;
			int count;
			// Line: 28
			try {
				count = getCount(1588970020) + 1;
				if (event.triggers("sense.user.leave")) {
					if (system.isAttending(event)) {
						incrCount(1588970020);
						eventResult = EVENT_CONSUMED;
						EXECUTION: {
							// Line: 29
							if (system.hasUsers()) {
								iristk.situated.SystemAgentFlow.attendRandom state3 = agent.new attendRandom();
								if (!flowThread.callState(state3, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\kalins\\IrisTK\\app\\robot-word-quiz\\src\\iristk\\app\\quiz\\RobotWordQuizFlow.xml"), 29, 33)))) {
									eventResult = EVENT_ABORTED;
									break EXECUTION;
								}
								// Line: 31
								flowThread.reentryState(this, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\kalins\\IrisTK\\app\\robot-word-quiz\\src\\iristk\\app\\quiz\\RobotWordQuizFlow.xml"), 31, 15)));
								eventResult = EVENT_ABORTED;
								break EXECUTION;
								// Line: 32
							} else {
								// Line: 33
								Goodbye state4 = new Goodbye();
								flowThread.gotoState(state4, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\kalins\\IrisTK\\app\\robot-word-quiz\\src\\iristk\\app\\quiz\\RobotWordQuizFlow.xml"), 33, 28)));
								eventResult = EVENT_ABORTED;
								break EXECUTION;
							}
						}
						if (eventResult != EVENT_IGNORED) return eventResult;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\kalins\\IrisTK\\app\\robot-word-quiz\\src\\iristk\\app\\quiz\\RobotWordQuizFlow.xml"), 28, 70));
			}
			// Line: 36
			try {
				count = getCount(1066376662) + 1;
				if (event.triggers("sense.user.speech.start")) {
					if (system.isAttending(event) && eq(event.get("speakers"), 1)) {
						incrCount(1066376662);
						eventResult = EVENT_CONSUMED;
						EXECUTION: {
							iristk.situated.SystemAgentFlow.gesture state5 = agent.new gesture();
							state5.setName("smile");
							if (!flowThread.callState(state5, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\kalins\\IrisTK\\app\\robot-word-quiz\\src\\iristk\\app\\quiz\\RobotWordQuizFlow.xml"), 36, 102)))) {
								eventResult = EVENT_ABORTED;
								break EXECUTION;
							}
						}
						if (eventResult != EVENT_IGNORED) return eventResult;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\kalins\\IrisTK\\app\\robot-word-quiz\\src\\iristk\\app\\quiz\\RobotWordQuizFlow.xml"), 36, 102));
			}
			eventResult = super.onFlowEvent(event);
			if (eventResult != EVENT_IGNORED) return eventResult;
			eventResult = callerHandlers(event);
			if (eventResult != EVENT_IGNORED) return eventResult;
			return EVENT_IGNORED;
		}

	}


	public class Greeting extends Dialog implements Initial {

		final State currentState = this;


		@Override
		public void setFlowThread(FlowRunner.FlowThread flowThread) {
			super.setFlowThread(flowThread);
		}

		@Override
		public void onentry() throws Exception {
			int eventResult;
			Event event = new Event("state.enter");
			// Line: 42
			try {
				EXECUTION: {
					int count = getCount(476402209) + 1;
					incrCount(476402209);
					iristk.situated.SystemAgentFlow.say state6 = agent.new say();
					StringCreator string7 = new StringCreator();
					string7.append("Hi there");
					state6.setText(string7.toString());
					if (!flowThread.callState(state6, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\kalins\\IrisTK\\app\\robot-word-quiz\\src\\iristk\\app\\quiz\\RobotWordQuizFlow.xml"), 42, 12)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					// Line: 44
					StartGame state8 = new StartGame();
					flowThread.gotoState(state8, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\kalins\\IrisTK\\app\\robot-word-quiz\\src\\iristk\\app\\quiz\\RobotWordQuizFlow.xml"), 44, 29)));
					eventResult = EVENT_ABORTED;
					break EXECUTION;
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\kalins\\IrisTK\\app\\robot-word-quiz\\src\\iristk\\app\\quiz\\RobotWordQuizFlow.xml"), 42, 12));
			}
		}

		@Override
		public int onFlowEvent(Event event) throws Exception {
			int eventResult;
			int count;
			eventResult = super.onFlowEvent(event);
			if (eventResult != EVENT_IGNORED) return eventResult;
			eventResult = callerHandlers(event);
			if (eventResult != EVENT_IGNORED) return eventResult;
			return EVENT_IGNORED;
		}

	}


	private class StartGame extends Dialog {

		final State currentState = this;


		@Override
		public void setFlowThread(FlowRunner.FlowThread flowThread) {
			super.setFlowThread(flowThread);
		}

		@Override
		public void onentry() throws Exception {
			int eventResult;
			Event event = new Event("state.enter");
			// Line: 49
			try {
				EXECUTION: {
					int count = getCount(1919892312) + 1;
					incrCount(1919892312);
					// Line: 51
					SayWords state9 = new SayWords();
					flowThread.gotoState(state9, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\kalins\\IrisTK\\app\\robot-word-quiz\\src\\iristk\\app\\quiz\\RobotWordQuizFlow.xml"), 51, 28)));
					eventResult = EVENT_ABORTED;
					break EXECUTION;
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\kalins\\IrisTK\\app\\robot-word-quiz\\src\\iristk\\app\\quiz\\RobotWordQuizFlow.xml"), 49, 12));
			}
		}

		@Override
		public int onFlowEvent(Event event) throws Exception {
			int eventResult;
			int count;
			eventResult = super.onFlowEvent(event);
			if (eventResult != EVENT_IGNORED) return eventResult;
			eventResult = callerHandlers(event);
			if (eventResult != EVENT_IGNORED) return eventResult;
			return EVENT_IGNORED;
		}

	}


	private class Goodbye extends State {

		final State currentState = this;


		@Override
		public void setFlowThread(FlowRunner.FlowThread flowThread) {
			super.setFlowThread(flowThread);
		}

		@Override
		public void onentry() throws Exception {
			int eventResult;
			Event event = new Event("state.enter");
			// Line: 56
			try {
				EXECUTION: {
					int count = getCount(358699161) + 1;
					incrCount(358699161);
					iristk.situated.SystemAgentFlow.say state10 = agent.new say();
					StringCreator string11 = new StringCreator();
					string11.append("Goodbye");
					state10.setText(string11.toString());
					if (!flowThread.callState(state10, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\kalins\\IrisTK\\app\\robot-word-quiz\\src\\iristk\\app\\quiz\\RobotWordQuizFlow.xml"), 56, 12)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					// Line: 58
					Idle state12 = new Idle();
					flowThread.gotoState(state12, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\kalins\\IrisTK\\app\\robot-word-quiz\\src\\iristk\\app\\quiz\\RobotWordQuizFlow.xml"), 58, 24)));
					eventResult = EVENT_ABORTED;
					break EXECUTION;
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\kalins\\IrisTK\\app\\robot-word-quiz\\src\\iristk\\app\\quiz\\RobotWordQuizFlow.xml"), 56, 12));
			}
		}

		@Override
		public int onFlowEvent(Event event) throws Exception {
			int eventResult;
			int count;
			eventResult = super.onFlowEvent(event);
			if (eventResult != EVENT_IGNORED) return eventResult;
			eventResult = callerHandlers(event);
			if (eventResult != EVENT_IGNORED) return eventResult;
			return EVENT_IGNORED;
		}

	}


	private class SayWords extends AwaitAnswer {

		final State currentState = this;


		@Override
		public void setFlowThread(FlowRunner.FlowThread flowThread) {
			super.setFlowThread(flowThread);
		}

		@Override
		public void onentry() throws Exception {
			int eventResult;
			Event event = new Event("state.enter");
			// Line: 63
			try {
				EXECUTION: {
					int count = getCount(425918570) + 1;
					incrCount(425918570);
					iristk.situated.SystemAgentFlow.say state13 = agent.new say();
					StringCreator string14 = new StringCreator();
					string14.append("How many words would you like to repeat?");
					state13.setText(string14.toString());
					if (!flowThread.callState(state13, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\kalins\\IrisTK\\app\\robot-word-quiz\\src\\iristk\\app\\quiz\\RobotWordQuizFlow.xml"), 63, 12)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					iristk.situated.SystemAgentFlow.listen state15 = agent.new listen();
					state15.setContext("numbers");
					if (!flowThread.callState(state15, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\kalins\\IrisTK\\app\\robot-word-quiz\\src\\iristk\\app\\quiz\\RobotWordQuizFlow.xml"), 63, 12)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\kalins\\IrisTK\\app\\robot-word-quiz\\src\\iristk\\app\\quiz\\RobotWordQuizFlow.xml"), 63, 12));
			}
		}

		@Override
		public int onFlowEvent(Event event) throws Exception {
			int eventResult;
			int count;
			// Line: 68
			try {
				count = getCount(2143192188) + 1;
				if (event.triggers("sense.user.speak")) {
					incrCount(2143192188);
					eventResult = EVENT_CONSUMED;
					EXECUTION: {
						// Line: 69
						if (asInteger(event.get("text")) > 0) {
							// Line: 70
							words.setWords(asInteger(event.get("text")));
							iristk.situated.SystemAgentFlow.say state16 = agent.new say();
							StringCreator string17 = new StringCreator();
							string17.append("Okay.");
							state16.setText(string17.toString());
							if (!flowThread.callState(state16, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\kalins\\IrisTK\\app\\robot-word-quiz\\src\\iristk\\app\\quiz\\RobotWordQuizFlow.xml"), 69, 44)))) {
								eventResult = EVENT_ABORTED;
								break EXECUTION;
							}
							iristk.situated.SystemAgentFlow.say state18 = agent.new say();
							StringCreator string19 = new StringCreator();
							string19.append("Repeat these words after me. You can say them in any order you want.");
							state18.setText(string19.toString());
							if (!flowThread.callState(state18, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\kalins\\IrisTK\\app\\robot-word-quiz\\src\\iristk\\app\\quiz\\RobotWordQuizFlow.xml"), 69, 44)))) {
								eventResult = EVENT_ABORTED;
								break EXECUTION;
							}
							iristk.situated.SystemAgentFlow.say state20 = agent.new say();
							StringCreator string21 = new StringCreator();
							// Line: 70
							string21.append(words.getWordList());
							state20.setText(string21.toString());
							if (!flowThread.callState(state20, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\kalins\\IrisTK\\app\\robot-word-quiz\\src\\iristk\\app\\quiz\\RobotWordQuizFlow.xml"), 69, 44)))) {
								eventResult = EVENT_ABORTED;
								break EXECUTION;
							}
							// Line: 74
							AwaitAnswer state22 = new AwaitAnswer();
							flowThread.gotoState(state22, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\kalins\\IrisTK\\app\\robot-word-quiz\\src\\iristk\\app\\quiz\\RobotWordQuizFlow.xml"), 74, 32)));
							eventResult = EVENT_ABORTED;
							break EXECUTION;
							// Line: 75
						} else if (asInteger(event.get("text")) == 0) {
							iristk.situated.SystemAgentFlow.say state23 = agent.new say();
							StringCreator string24 = new StringCreator();
							string24.append("hahaha, are you trying to be funny? Give me a number between 1 and 6");
							state23.setText(string24.toString());
							if (!flowThread.callState(state23, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\kalins\\IrisTK\\app\\robot-word-quiz\\src\\iristk\\app\\quiz\\RobotWordQuizFlow.xml"), 69, 44)))) {
								eventResult = EVENT_ABORTED;
								break EXECUTION;
							}
							// Line: 77
							flowThread.reentryState(this, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\kalins\\IrisTK\\app\\robot-word-quiz\\src\\iristk\\app\\quiz\\RobotWordQuizFlow.xml"), 77, 16)));
							eventResult = EVENT_ABORTED;
							break EXECUTION;
							// Line: 78
						} else {
							iristk.situated.SystemAgentFlow.say state25 = agent.new say();
							StringCreator string26 = new StringCreator();
							string26.append("Sorry, that's not an option.");
							state25.setText(string26.toString());
							if (!flowThread.callState(state25, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\kalins\\IrisTK\\app\\robot-word-quiz\\src\\iristk\\app\\quiz\\RobotWordQuizFlow.xml"), 69, 44)))) {
								eventResult = EVENT_ABORTED;
								break EXECUTION;
							}
							// Line: 80
							flowThread.reentryState(this, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\kalins\\IrisTK\\app\\robot-word-quiz\\src\\iristk\\app\\quiz\\RobotWordQuizFlow.xml"), 80, 16)));
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
					}
					if (eventResult != EVENT_IGNORED) return eventResult;
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\kalins\\IrisTK\\app\\robot-word-quiz\\src\\iristk\\app\\quiz\\RobotWordQuizFlow.xml"), 68, 36));
			}
			eventResult = super.onFlowEvent(event);
			if (eventResult != EVENT_IGNORED) return eventResult;
			eventResult = callerHandlers(event);
			if (eventResult != EVENT_IGNORED) return eventResult;
			return EVENT_IGNORED;
		}

	}


	private class Done extends State {

		final State currentState = this;


		@Override
		public void setFlowThread(FlowRunner.FlowThread flowThread) {
			super.setFlowThread(flowThread);
		}

		@Override
		public void onentry() throws Exception {
			int eventResult;
			Event event = new Event("state.enter");
			// Line: 89
			try {
				EXECUTION: {
					int count = getCount(1865127310) + 1;
					incrCount(1865127310);
					iristk.situated.SystemAgentFlow.say state27 = agent.new say();
					StringCreator string28 = new StringCreator();
					string28.append("Good job!! You made it");
					state27.setText(string28.toString());
					if (!flowThread.callState(state27, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\kalins\\IrisTK\\app\\robot-word-quiz\\src\\iristk\\app\\quiz\\RobotWordQuizFlow.xml"), 89, 12)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\kalins\\IrisTK\\app\\robot-word-quiz\\src\\iristk\\app\\quiz\\RobotWordQuizFlow.xml"), 89, 12));
			}
		}

		@Override
		public int onFlowEvent(Event event) throws Exception {
			int eventResult;
			int count;
			eventResult = super.onFlowEvent(event);
			if (eventResult != EVENT_IGNORED) return eventResult;
			eventResult = callerHandlers(event);
			if (eventResult != EVENT_IGNORED) return eventResult;
			return EVENT_IGNORED;
		}

	}


	private class AwaitAnswer extends Dialog {

		final State currentState = this;


		@Override
		public void setFlowThread(FlowRunner.FlowThread flowThread) {
			super.setFlowThread(flowThread);
		}

		@Override
		public void onentry() throws Exception {
			int eventResult;
			Event event = new Event("state.enter");
			// Line: 95
			try {
				EXECUTION: {
					int count = getCount(1694819250) + 1;
					incrCount(1694819250);
					iristk.situated.SystemAgentFlow.listen state29 = agent.new listen();
					state29.setContext("words");
					if (!flowThread.callState(state29, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\kalins\\IrisTK\\app\\robot-word-quiz\\src\\iristk\\app\\quiz\\RobotWordQuizFlow.xml"), 95, 12)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\kalins\\IrisTK\\app\\robot-word-quiz\\src\\iristk\\app\\quiz\\RobotWordQuizFlow.xml"), 95, 12));
			}
		}

		@Override
		public int onFlowEvent(Event event) throws Exception {
			int eventResult;
			int count;
			// Line: 98
			try {
				count = getCount(1651191114) + 1;
				if (event.triggers("sense.user.speak")) {
					incrCount(1651191114);
					eventResult = EVENT_CONSUMED;
					EXECUTION: {
						// Line: 101
						if (words.isWord(event.get("text"))) {
							// Line: 102
							String w = words.saidWord(event.get("text"));
							// Line: 103
							if (asInteger(words.wordsLeft()) == 0) {
								// Line: 104
								Done state30 = new Done();
								flowThread.gotoState(state30, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\kalins\\IrisTK\\app\\robot-word-quiz\\src\\iristk\\app\\quiz\\RobotWordQuizFlow.xml"), 104, 26)));
								eventResult = EVENT_ABORTED;
								break EXECUTION;
								// Line: 105
							} else if (w != null) {
								iristk.situated.SystemAgentFlow.say state31 = agent.new say();
								StringCreator string32 = new StringCreator();
								string32.append("Okay.");
								// Line: 105
								string32.append(w);
								// Line: 105
								if (w.split(", ").length == 1) {
									string32.append("was");
									// Line: 105
								} else {
									string32.append("were");
								}
								string32.append("wrong, but the rest was good.");
								state31.setText(string32.toString());
								if (!flowThread.callState(state31, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\kalins\\IrisTK\\app\\robot-word-quiz\\src\\iristk\\app\\quiz\\RobotWordQuizFlow.xml"), 103, 50)))) {
									eventResult = EVENT_ABORTED;
									break EXECUTION;
								}
								iristk.situated.SystemAgentFlow.say state33 = agent.new say();
								StringCreator string34 = new StringCreator();
								string34.append("Only");
								// Line: 105
								string34.append(words.wordsLeft());
								string34.append("more word");
								// Line: 105
								if (words.wordsLeft() == 1) {
									string34.append("s");
								}
								string34.append("to go.");
								state33.setText(string34.toString());
								if (!flowThread.callState(state33, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\kalins\\IrisTK\\app\\robot-word-quiz\\src\\iristk\\app\\quiz\\RobotWordQuizFlow.xml"), 103, 50)))) {
									eventResult = EVENT_ABORTED;
									break EXECUTION;
								}
								// Line: 109
								AwaitAnswer state35 = new AwaitAnswer();
								flowThread.gotoState(state35, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\kalins\\IrisTK\\app\\robot-word-quiz\\src\\iristk\\app\\quiz\\RobotWordQuizFlow.xml"), 109, 33)));
								eventResult = EVENT_ABORTED;
								break EXECUTION;
								// Line: 110
							} else {
								iristk.situated.SystemAgentFlow.say state36 = agent.new say();
								StringCreator string37 = new StringCreator();
								string37.append("Good! Only");
								// Line: 110
								string37.append(words.wordsLeft());
								string37.append("more word");
								// Line: 110
								if (words.wordsLeft() == 1) {
									string37.append("s");
								}
								string37.append("to go.");
								state36.setText(string37.toString());
								if (!flowThread.callState(state36, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\kalins\\IrisTK\\app\\robot-word-quiz\\src\\iristk\\app\\quiz\\RobotWordQuizFlow.xml"), 103, 50)))) {
									eventResult = EVENT_ABORTED;
									break EXECUTION;
								}
								// Line: 112
								AwaitAnswer state38 = new AwaitAnswer();
								flowThread.gotoState(state38, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\kalins\\IrisTK\\app\\robot-word-quiz\\src\\iristk\\app\\quiz\\RobotWordQuizFlow.xml"), 112, 33)));
								eventResult = EVENT_ABORTED;
								break EXECUTION;
							}
							// Line: 114
						} else {
							iristk.situated.SystemAgentFlow.say state39 = agent.new say();
							StringCreator string40 = new StringCreator();
							string40.append("Nope,");
							// Line: 114
							string40.append(event.get("text"));
							string40.append("wasn't among the words I said.");
							state39.setText(string40.toString());
							if (!flowThread.callState(state39, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\kalins\\IrisTK\\app\\robot-word-quiz\\src\\iristk\\app\\quiz\\RobotWordQuizFlow.xml"), 101, 40)))) {
								eventResult = EVENT_ABORTED;
								break EXECUTION;
							}
							// Line: 116
							AwaitAnswer state41 = new AwaitAnswer();
							flowThread.gotoState(state41, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\kalins\\IrisTK\\app\\robot-word-quiz\\src\\iristk\\app\\quiz\\RobotWordQuizFlow.xml"), 116, 32)));
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
					}
					if (eventResult != EVENT_IGNORED) return eventResult;
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\kalins\\IrisTK\\app\\robot-word-quiz\\src\\iristk\\app\\quiz\\RobotWordQuizFlow.xml"), 98, 36));
			}
			// Line: 119
			try {
				count = getCount(405662939) + 1;
				if (event.triggers("sense.user.speak")) {
					incrCount(405662939);
					eventResult = EVENT_CONSUMED;
					EXECUTION: {
					}
					if (eventResult != EVENT_IGNORED) return eventResult;
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\kalins\\IrisTK\\app\\robot-word-quiz\\src\\iristk\\app\\quiz\\RobotWordQuizFlow.xml"), 119, 36));
			}
			// Line: 123
			try {
				count = getCount(653305407) + 1;
				if (event.triggers("sense.user.silence")) {
					incrCount(653305407);
					eventResult = EVENT_CONSUMED;
					EXECUTION: {
						// Line: 124
						Event raiseEvent42 = new Event("skip");
						if (flowThread.raiseEvent(raiseEvent42, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\kalins\\IrisTK\\app\\robot-word-quiz\\src\\iristk\\app\\quiz\\RobotWordQuizFlow.xml"), 124, 25))) == State.EVENT_ABORTED) {
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
					}
					if (eventResult != EVENT_IGNORED) return eventResult;
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\kalins\\IrisTK\\app\\robot-word-quiz\\src\\iristk\\app\\quiz\\RobotWordQuizFlow.xml"), 123, 38));
			}
			// Line: 126
			try {
				count = getCount(1404928347) + 1;
				if (event.triggers("skip")) {
					incrCount(1404928347);
					eventResult = EVENT_CONSUMED;
					EXECUTION: {
						iristk.situated.SystemAgentFlow.say state43 = agent.new say();
						StringCreator string44 = new StringCreator();
						string44.append("Give me your best guess");
						state43.setText(string44.toString());
						if (!flowThread.callState(state43, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\kalins\\IrisTK\\app\\robot-word-quiz\\src\\iristk\\app\\quiz\\RobotWordQuizFlow.xml"), 126, 24)))) {
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
						// Line: 128
						AwaitAnswer state45 = new AwaitAnswer();
						flowThread.gotoState(state45, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\kalins\\IrisTK\\app\\robot-word-quiz\\src\\iristk\\app\\quiz\\RobotWordQuizFlow.xml"), 128, 31)));
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					if (eventResult != EVENT_IGNORED) return eventResult;
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\kalins\\IrisTK\\app\\robot-word-quiz\\src\\iristk\\app\\quiz\\RobotWordQuizFlow.xml"), 126, 24));
			}
			eventResult = super.onFlowEvent(event);
			if (eventResult != EVENT_IGNORED) return eventResult;
			eventResult = callerHandlers(event);
			if (eventResult != EVENT_IGNORED) return eventResult;
			return EVENT_IGNORED;
		}

	}


}
