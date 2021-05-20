package commands;

import java.awt.event.ActionEvent;

import model.Document;
import view.Text2SpeechEditorView;

public class LineToSpeech implements MyActionListener {
	
	String type;
	
	public LineToSpeech(String type) {
		this.type = type;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if ((!Text2SpeechEditorView.getSingletonView().isReplay()) && Text2SpeechEditorView.getSingletonView().getButton().isSelected())
			ReplayManager.addToList("lineToSpeech" + type);
		
		Document currentDocument = Text2SpeechEditorView.getSingletonView().getCurrentDocument();
		int lineNumber = Text2SpeechEditorView.getSingletonView().getCursorPosition();
		
		if (type.equals("Normal"))
			currentDocument.playLine(lineNumber);
		else
			currentDocument.playReverseLine(lineNumber);

	}
}