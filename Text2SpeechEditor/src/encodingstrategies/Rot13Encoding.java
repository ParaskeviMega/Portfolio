package encodingstrategies;

public class Rot13Encoding extends TemplateEncoding {
	
	public Rot13Encoding(String type) {
		super(type);
	}

	@Override
	public char mapCharacter(char charac) {
		return 0;
	}

	public String encode(String contents) {
		String converted = "";
		
		for (int i = 0; i < contents.length(); i++) {
			char c = contents.charAt(i);
			if       (c >= 'a' && c <= 'm') c += 13;
			else if  (c >= 'A' && c <= 'M') c += 13;
			else if  (c >= 'n' && c <= 'z') c -= 13;
			else if  (c >= 'N' && c <= 'Z') c -= 13;
			converted = converted + Character.toString(c);
		}

		return converted;
	}
	
}
