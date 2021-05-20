package tests;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.junit.jupiter.api.Test;

import commands.CommandsFactory;
import model.Document;
import view.Text2SpeechEditorView;

class SaveTest {

	@Test
	void testSave() {
		Text2SpeechEditorView.getSingletonView().setTextArea("hello world!" + "\n" + "today" + "\n" + "everything");
		
		CommandsFactory newCommandsFactory = Text2SpeechEditorView.getSingletonView().getCommandsFactory();

		newCommandsFactory.createCommand("save").actionPerformed(null);
		
		Document newDocument = Text2SpeechEditorView.getSingletonView().getCurrentDocument();
		
		File newFile = new File("C:\\Users\\vivi-\\Documents\\new.text");
		String line = "";
		String cont = "";
		int counter = 0;
		try {
			BufferedReader br = new BufferedReader(new FileReader(newFile));
			try {
				while ((line = br.readLine()) != null) {
					
					// για να αποφύγουμε περιττά κενά
					if (counter != 0) {
						if (counter == 1) {
							cont = cont + line;
						}else {
							cont = cont + "\n";
							cont = cont + line;
						}
					}
					counter++;
				}
				
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		
		assertEquals(newDocument.getStringContents(), cont);
	}
}