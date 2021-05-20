package commands;

import java.awt.event.ActionEvent;

import model.Document;

import view.Text2SpeechEditorView;

public class DocumentToSpeech implements MyActionListener{
	String type;
	public DocumentToSpeech(String type) {
		this.type = type;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		Document currentDocument = Text2SpeechEditorView.getSingletonView().getCurrentDocument();
		
		if ((!Text2SpeechEditorView.getSingletonView().isReplay()) && Text2SpeechEditorView.getSingletonView().getButton().isSelected())
			ReplayManager.addToList("contentsToSpeech" + type);
		
		if (type.equals("Normal"))
			currentDocument.playContents();
		else
			currentDocument.playReverseContents();
		
	}

}