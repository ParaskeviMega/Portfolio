package tests;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import commands.CommandsFactory;
import model.Document;

import view.NewDocumentWindow;
import view.Text2SpeechEditorView;

class CreateTest {

	@Test
	void testCreate() {		
		CommandsFactory newCommandsFactory = Text2SpeechEditorView.getSingletonView().getCommandsFactory();

		newCommandsFactory.createCommand("create").actionPerformed(null);
		
		NewDocumentWindow.getSingletonView().getOKButton().doClick();
			
		Document newDocument = Text2SpeechEditorView.getSingletonView().getCurrentDocument();
		
		assertEquals(newDocument.getStringContents(), "");
	}

}
