package commands;

import java.awt.event.ActionEvent;

import encodingstrategies.StrategiesFactory;
import view.Text2SpeechEditorView;

public class TuneEncoding implements MyActionListener{
	String type, lines;
	
	public TuneEncoding(String type, String lines) {
		this.type = type;
		this.lines = lines;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		StrategiesFactory newStrategiesFactory = new StrategiesFactory();
		
		if (type.equals("rot13")) {
			if ((!Text2SpeechEditorView.getSingletonView().isReplay()) && Text2SpeechEditorView.getSingletonView().getButton().isSelected())
				ReplayManager.addToList("rot13Encoding" + lines);
			Text2SpeechEditorView.getSingletonView().getCurrentDocument().tuneEncodingStrategy(newStrategiesFactory.createStrategy("rot13"));
		}
		else {
			if ((!Text2SpeechEditorView.getSingletonView().isReplay()) && Text2SpeechEditorView.getSingletonView().getButton().isSelected())
				ReplayManager.addToList("atbashEncoding" + lines);
			Text2SpeechEditorView.getSingletonView().getCurrentDocument().tuneEncodingStrategy(newStrategiesFactory.createStrategy("atbash"));
		}
		
		if (lines.equals("Doc")) {
			Text2SpeechEditorView.getSingletonView().getCurrentDocument().playEncodedContents();			
		}else {
			int lineNumber = Text2SpeechEditorView.getSingletonView().getCursorPosition();
			Text2SpeechEditorView.getSingletonView().getCurrentDocument().playEncodedLine(lineNumber);
		} 
		
	}

}