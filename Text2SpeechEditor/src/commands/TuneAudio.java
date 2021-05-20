package commands;

import java.awt.event.ActionEvent;

import view.TuneAudioWindow;

public class TuneAudio implements MyActionListener {

	public TuneAudio() {
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		TuneAudioWindow newTuneAudioWindow = new TuneAudioWindow();
		newTuneAudioWindow.setVisible(true);
	}
}