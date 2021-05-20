package encodingstrategies;

public abstract class TemplateEncoding implements EncodingStrategy{
	
	protected final String type;
	
	public TemplateEncoding(String type) {
		this.type = type;
	}
	
	public abstract String encode(String type);
	
	public abstract char mapCharacter(char charac);

}
