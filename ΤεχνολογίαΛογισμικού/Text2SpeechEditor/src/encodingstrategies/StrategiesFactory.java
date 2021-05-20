package encodingstrategies;

public class StrategiesFactory {
	
	public StrategiesFactory() {
		
	}
	
	public TemplateEncoding createStrategy(String text) {
		if (text.equals("rot13")) 
			return new Rot13Encoding(text);
		else
			return new AtBashEncoding(text);
		
	}
}
