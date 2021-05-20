package tests;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import commands.CommandsFactory;
import model.Document;

import view.Text2SpeechEditorView;

class EditTest {

	@Test
	void testEdit() {
		Text2SpeechEditorView.getSingletonView().setTextArea("hello there!");
		
		CommandsFactory newCommandsFactory = Text2SpeechEditorView.getSingletonView().getCommandsFactory();
		
		newCommandsFactory.createCommand("edit").actionPerformed(null);
		
		Document newDocument = Text2SpeechEditorView.getSingletonView().getCurrentDocument();
					
		assertEquals(newDocument.getStringContents(), "hello there!");
	}

}
