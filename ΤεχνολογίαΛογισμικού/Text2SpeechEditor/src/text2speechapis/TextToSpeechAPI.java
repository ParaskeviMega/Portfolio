package text2speechapis;

import com.sun.speech.freetts.Voice;

public interface TextToSpeechAPI {
	public void play(String line);
	public void setVolume(float volume);
	public void setPitch(float pitch);
	public void setRate(float rate);
	public Voice getVoice();
}
