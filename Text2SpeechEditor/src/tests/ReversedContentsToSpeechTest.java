package tests;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import commands.CommandsFactory;
import model.Document;
import model.Line;
import text2speechapis.FakeTextToSpeechAPI;
import view.Text2SpeechEditorView;

class ReversedContentsToSpeechTest {

	@Test
	void test() {
		Document newDocument = Text2SpeechEditorView.getSingletonView().getCurrentDocument();
		Text2SpeechEditorView.getSingletonView().setAPI();
		
		ArrayList<Line> lines = new ArrayList<Line>();
		
		Line newLine = new Line("hello world", Text2SpeechEditorView.getSingletonView().getTextToSpeechAPI());
		Line newLine2 = new Line("how are you", Text2SpeechEditorView.getSingletonView().getTextToSpeechAPI());
		
		lines.add(newLine);
		lines.add(newLine2);
		
		newDocument.setContents(lines);
		
		CommandsFactory newCommandsFactory = Text2SpeechEditorView.getSingletonView().getCommandsFactory();

		newCommandsFactory.createCommand("contentsToSpeechRev").actionPerformed(null);
		
		String text = FakeTextToSpeechAPI.getAPI();
		
		assertEquals(newDocument.getStringContents(), text);
	}

}
