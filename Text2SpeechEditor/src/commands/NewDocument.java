package commands;

import java.awt.event.ActionEvent;

import view.NewDocumentWindow;
import view.Text2SpeechEditorView;

public class NewDocument implements MyActionListener{

	public NewDocument() {
	
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if ((!Text2SpeechEditorView.getSingletonView().isReplay()) && Text2SpeechEditorView.getSingletonView().getButton().isSelected())
			ReplayManager.addToList("create");
		
		NewDocumentWindow newDocumentWindow = new NewDocumentWindow();
		newDocumentWindow.setVisible(true);
		
	}
}
