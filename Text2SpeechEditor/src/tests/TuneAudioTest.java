package tests;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import commands.CommandsFactory;

import view.Text2SpeechEditorView;
import view.TuneAudioWindow;

class TuneAudioTest {

	@Test
	void test() {
		CommandsFactory newCommandsFactory = Text2SpeechEditorView.getSingletonView().getCommandsFactory();
		
		newCommandsFactory.createCommand("editPar").actionPerformed(null);
		
		TuneAudioWindow.getSingletonView().getOKButton().doClick();
		
		assertEquals(Text2SpeechEditorView.getSingletonView().getRate(),"150.0");
	}

}
