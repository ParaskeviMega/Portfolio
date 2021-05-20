package model;

import java.util.ArrayList;

import encodingstrategies.EncodingStrategy;
import text2speechapis.TextToSpeechAPI;

public class Line {
	private ArrayList<String> words;
	private TextToSpeechAPI audioManager;
	private EncodingStrategy newEncodingStrategy;
	
	public Line(String aLine, TextToSpeechAPI audioManager) {
		words = new ArrayList<String>(); 
		
		String lines[] = aLine.split(" ");

		for(String line: lines) {
			words.add(line);
		} 
		this.audioManager = audioManager;
	}
	
	public ArrayList<String> getWords(){
		return words;
	}
	
	public String getCont(String type) {
		String contents = "";
		
		for (int j = 0; j < words.size(); j++) {
			if (j == 0)
				contents = words.get(j);
			else
				if (type.equals("norm"))
					contents = contents + " " + words.get(j);
				else
					contents = words.get(j) + " " + contents;
		}
		return contents;
	}
	
	public void playLine() {
		audioManager.play(getCont("norm"));
	}
	
	public void playReverseLine() {
		audioManager.play(getCont("Rev"));
	}
	
	public void playEncodedLine() {
		String encoded = newEncodingStrategy.encode(getCont("norm"));
		audioManager.play(encoded);
	}
	
	public void tuneEncodingStrategy(EncodingStrategy newEncodingStrategy) {
		this.newEncodingStrategy = newEncodingStrategy;
	}
}
