package text2speechapis;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

public class FreeTTSAdapter implements TextToSpeechAPI {
	private String name;
	
	private com.sun.speech.freetts.Voice voice;

	public FreeTTSAdapter(String name) {
		this.name = name;
		System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
		this.voice = VoiceManager.getInstance().getVoice(this.name);
		this.voice.allocate();
	}
	
	public void play(String text) {
		voice.speak(text);
	}

	public void setVolume(float volume) {
		voice.setVolume(volume);
	}
	
	public void setPitch(float pitch) {
		voice.setPitch(pitch);
	}

	public void setRate(float rate) {
		voice.setRate(rate);
	}
	
	public Voice getVoice() {
		return voice;
	}

}
