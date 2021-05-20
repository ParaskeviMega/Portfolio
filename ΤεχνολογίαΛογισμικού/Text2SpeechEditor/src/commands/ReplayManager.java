package commands;

import java.util.ArrayList;

import view.Text2SpeechEditorView;

public class ReplayManager {
	private static ArrayList<String> commandList = new ArrayList<String>();
	
	public ReplayManager() {
		
	}
	
	public void replay() {	
		Text2SpeechEditorView.getSingletonView().setReplay(true);
		CommandsFactory newCommandsFactory = Text2SpeechEditorView.getSingletonView().getCommandsFactory();
		
		for (int i = 0; i < commandList.size(); i++) {
			newCommandsFactory.createCommand(commandList.get(i)).actionPerformed(null);
		}
		Text2SpeechEditorView.getSingletonView().setReplay(false);
	}
	
	public static void addToList(String command) {
		commandList.add(command);
	}
	
	public static void clearList() {
		commandList.clear();
	}
}
