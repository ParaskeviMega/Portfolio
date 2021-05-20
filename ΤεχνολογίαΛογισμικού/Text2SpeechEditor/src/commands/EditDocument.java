package commands;

import java.awt.event.ActionEvent;

import view.Text2SpeechEditorView;

public class EditDocument implements MyActionListener{
	public EditDocument() {
			
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if ((!Text2SpeechEditorView.getSingletonView().isReplay()) && Text2SpeechEditorView.getSingletonView().getButton().isSelected())
			ReplayManager.addToList("edit");
		
		Text2SpeechEditorView.getSingletonView().getCurrentDocument();
	}
}