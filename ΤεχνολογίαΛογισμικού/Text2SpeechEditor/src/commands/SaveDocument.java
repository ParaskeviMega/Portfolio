package commands;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import model.Document;
import model.FileTypeFilter;
import view.Text2SpeechEditorView;

public class SaveDocument implements MyActionListener {

	public SaveDocument() {
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if ((!Text2SpeechEditorView.getSingletonView().isReplay()) && Text2SpeechEditorView.getSingletonView().getButton().isSelected())
			ReplayManager.addToList("save");
		
		final DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		String savedDate = sdf.format(cal.getTime());
		
		Document currentDocument = Text2SpeechEditorView.getSingletonView().getCurrentDocument();
		
		currentDocument.setSavedDate(savedDate);
		
		if (currentDocument.getCreatedDate().equals("")) {
			currentDocument.setCreatedDate(savedDate);
		}
		
		JFileChooser fs = new JFileChooser(new File("C:\\Users\\vivi-\\Documents"));
		fs.setDialogTitle("Save File");
		fs.setFileFilter(new FileTypeFilter(".text", "TextFile"));
		int result = fs.showSaveDialog(null);
		if (result == JFileChooser.APPROVE_OPTION) {
			File newFile = fs.getSelectedFile();
			try {
				String content = currentDocument.getTitle() + "-" + currentDocument.getAuthor() + "-" + currentDocument.getCreatedDate() + "-" + currentDocument.getSavedDate() + "\n" + currentDocument.getStringContents();
				
				FileWriter fw = new FileWriter(newFile.getPath());
				fw.write(content);
				fw.flush();
				fw.close();
			}catch (Exception e2) {
				JOptionPane.showMessageDialog(null, e2.getMessage());
			}
		}
	}
}
