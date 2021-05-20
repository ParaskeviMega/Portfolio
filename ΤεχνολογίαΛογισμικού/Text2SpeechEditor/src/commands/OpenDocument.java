package commands;

import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import model.Document;
import model.Line;
import model.FileTypeFilter;
import view.Text2SpeechEditorView;

public class OpenDocument implements MyActionListener{
	
	public OpenDocument() {

	}

	public void actionPerformed(ActionEvent e) {
		if ((!Text2SpeechEditorView.getSingletonView().isReplay()) && Text2SpeechEditorView.getSingletonView().getButton().isSelected())
			ReplayManager.addToList("open");
		
		ArrayList<Line> linesList = new ArrayList<Line>();
		Document newDocument = Text2SpeechEditorView.getSingletonView().getCurrentDocument();
		
		JFileChooser fs = new JFileChooser(new File("C:\\Users\\vivi-\\Documents"));
		fs.setDialogTitle("Load File");
		fs.setFileFilter(new FileTypeFilter(".text","Text File"));
		
		int result = fs.showOpenDialog(null);
		
		if (result == JFileChooser.APPROVE_OPTION) {
			try {
				File newFile = fs.getSelectedFile();
				BufferedReader br = new BufferedReader(new FileReader(newFile.getPath()));
				
				String line = "";

				boolean firstLine = true;
				try {
					while ((line = br.readLine()) != null) {
						
						if (firstLine) {
							String[] words = line.split("-", 5); 
							
							newDocument.setTitle(words[0]);
							newDocument.setAuthor(words[1]);
							newDocument.setCreatedDate(words[2]);
							newDocument.setSavedDate(words[3]);
							
							firstLine = false;
						}else {
							Line newLine = new Line(line, Text2SpeechEditorView.getSingletonView().getTextToSpeechAPI());
							linesList.add(newLine);
						}
						
					}
					newDocument.setContents(linesList);
					if (br != null)
						br.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
				Text2SpeechEditorView.getSingletonView().setWindow();
										
			}catch (Exception e2) {
				JOptionPane.showMessageDialog(null, e2.getMessage());
			}
		}
	}
}
