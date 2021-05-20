package text2speechapis;

import com.sun.speech.freetts.Voice;

public class FakeTextToSpeechAPI implements TextToSpeechAPI {
	private static String text = "";
	@SuppressWarnings("unused")
	private float volume, pitch, rate;

	public FakeTextToSpeechAPI() {
	
	}
	
	public void play(String line) {
		if (text.equals("")) {
			text = line;
		}else {
			text = text + "\n" + line;
		}	
	}

	public void setVolume(float volume) {
		this.volume = volume;

	}
	
	public void setPitch(float pitch) {
		this.pitch = pitch;

	}
	
	public void setRate(float rate) {
		this.rate = rate;

	}
	
	public Voice getVoice() {
		return null;
	}
	
	public static String getAPI() {
		return text;
	}

}
