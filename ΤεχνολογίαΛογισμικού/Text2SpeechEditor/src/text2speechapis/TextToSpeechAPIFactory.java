package text2speechapis;

public class TextToSpeechAPIFactory {
	public TextToSpeechAPIFactory() {
		
	}
	
	public TextToSpeechAPI createTTSAPI(String type) {
		if (type.equals("free")) {
			return new FreeTTSAdapter("kevin16");
		}else if (type.equals("fake")) {
			return new FakeTextToSpeechAPI();
		}else {
			return null;
		}
	}
}
