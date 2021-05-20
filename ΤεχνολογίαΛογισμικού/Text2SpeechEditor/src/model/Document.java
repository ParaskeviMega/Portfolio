package model;
import java.util.ArrayList;

import encodingstrategies.EncodingStrategy;
import text2speechapis.TextToSpeechAPI;

public class Document {
	private String author, title, createdDate, savedDate;
	private ArrayList<Line> linesList;
	@SuppressWarnings("unused")
	private TextToSpeechAPI audioManager;
	private EncodingStrategy newEncodingStrategy;
	
	public Document(String title, String author, String createdDate, String savedDate, ArrayList<Line> contents, TextToSpeechAPI audioManager) {
		this.title = title;
		this.author = author;
		this.createdDate = createdDate;
		this.savedDate = savedDate;
		this.linesList = contents;
		
		this.audioManager = audioManager;
	}
	
	public String getAuthor() {
		return author;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getCreatedDate() {
		return createdDate;
	}
	
	public String getSavedDate() {
		return savedDate;
	}
	
	public ArrayList<Line> getContents() {
		return linesList;
	}
	
	public String getStringContents() {
		String contents = "";
		for (int i = 0; i < linesList.size(); i++) {  
			for (int j = 0; j < linesList.get(i).getWords().size(); j++) {
				if (j == 0)
					contents = contents + linesList.get(i).getWords().get(j);
				else
					contents = contents + " " + linesList.get(i).getWords().get(j);
			}
			if (i != linesList.size() - 1)
				contents = contents + "\n";
		}
		
		return contents;
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	
	public void setSavedDate(String date) {
		this.savedDate = date;
	}
	
	public void setContents(ArrayList<Line> contents) {
		this.linesList = contents;
	}
	
	public void playContents() {
		if (!getStringContents().equals("")) {
			for (int i = 0; i < linesList.size(); i++) {  
				linesList.get(i).playLine();
			}
		}
	}
	
	public void playReverseContents() {
		if (!getStringContents().equals("")) {
			for (int i = linesList.size() - 1; i >= 0 ; i--) {  
				linesList.get(i).playReverseLine();
			}
		}
	}
	
	public void playEncodedContents() {
		if (!getStringContents().equals("")) {
			for (int i = 0; i < linesList.size(); i++) { 
				linesList.get(i).tuneEncodingStrategy(newEncodingStrategy);
				linesList.get(i).playEncodedLine();
			}
		}
	}
	
	public void playLine(int line) {
		if (!getStringContents().equals(""))
			linesList.get(line).playLine();
	}

	public void playReverseLine(int line) {
		if (!getStringContents().equals(""))
			linesList.get(line).playReverseLine();
	}
	
	public void playEncodedLine(int line) {
		if (!getStringContents().equals("")) {
			linesList.get(line).tuneEncodingStrategy(newEncodingStrategy);
			linesList.get(line).playEncodedLine();
		}
	}
	
	public void tuneEncodingStrategy(EncodingStrategy newEncodingStrategy) {
		this.newEncodingStrategy = newEncodingStrategy;
		
	}
}