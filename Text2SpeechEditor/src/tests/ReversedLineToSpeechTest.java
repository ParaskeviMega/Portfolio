package tests;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import commands.CommandsFactory;
import model.Document;
import model.Line;
import text2speechapis.FakeTextToSpeechAPI;
import view.Text2SpeechEditorView;

class ReversedLineToSpeechTest {

	@Test
	void test() {
		Document newDocument = Text2SpeechEditorView.getSingletonView().getCurrentDocument();
		Text2SpeechEditorView.getSingletonView().setAPI();
		
		ArrayList<Line> lines = new ArrayList<Line>();
		
		Line newLine = new Line("hello world how are you", Text2SpeechEditorView.getSingletonView().getTextToSpeechAPI());
		
		lines.add(newLine);
		
		newDocument.setContents(lines);

		Text2SpeechEditorView.getSingletonView().setWindow();
		
		CommandsFactory newCommandsFactory = Text2SpeechEditorView.getSingletonView().getCommandsFactory();
		
		newCommandsFactory.createCommand("lineToSpeechRev").actionPerformed(null);
		
		String text = FakeTextToSpeechAPI.getAPI();
		
		
		assertEquals("you are how world hello", text);
	}

}
