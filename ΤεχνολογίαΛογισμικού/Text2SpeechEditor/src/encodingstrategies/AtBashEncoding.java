package encodingstrategies;

public class AtBashEncoding extends TemplateEncoding {

	
	public AtBashEncoding(String text) {
		super(text);
	}

	@Override
	public char mapCharacter(char charac) {
		return 0;
	}

	public String encode(String contents) {

		String words[] = contents.split("\\n");  
        String converted = ""; 
        int j = 0;
        
        for (String i : words){  
        	StringBuilder sb = new StringBuilder(i);  
            sb.reverse();  
            
            if (j != words.length - 1)
            	converted += sb.toString() + "\n";  
            else
            	converted += sb.toString(); 
            
            j++;
        } 
		return converted;
	}
}
