package tests;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import commands.CommandsFactory;
import model.Document;
import model.Line;
import text2speechapis.FakeTextToSpeechAPI;
import view.Text2SpeechEditorView;

class ReplayTest {

	@Test
	void test() {
		Text2SpeechEditorView.getSingletonView().getButton().doClick();
		
		Document newDocument = Text2SpeechEditorView.getSingletonView().getCurrentDocument();
		Text2SpeechEditorView.getSingletonView().setAPI();
		
		ArrayList<Line> lines = new ArrayList<Line>();
		
		Line newLine = new Line("hello there", Text2SpeechEditorView.getSingletonView().getTextToSpeechAPI());
		
		lines.add(newLine);
		
		newDocument.setContents(lines);

		Text2SpeechEditorView.getSingletonView().setWindow();
		
		CommandsFactory newCommandsFactory = Text2SpeechEditorView.getSingletonView().getCommandsFactory();
		
		newCommandsFactory.createCommand("rot13EncodingLine").actionPerformed(null);
		
		Text2SpeechEditorView.getSingletonView().setTextArea("uryyb gurer");
		
		newCommandsFactory.createCommand("reExecuteCommands").actionPerformed(null);
		
		String text = FakeTextToSpeechAPI.getAPI();
		
		String result = "uryyb gurer" + "\n" + "hello there";
		
		assertEquals(result, text);
	}

}
