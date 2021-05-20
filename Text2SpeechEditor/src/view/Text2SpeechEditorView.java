package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JRadioButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.text.BadLocationException;
import javax.swing.text.Utilities;

import commands.CommandsFactory;
import commands.ReplayManager;
import model.Document;
import model.Line;
import text2speechapis.TextToSpeechAPI;
import text2speechapis.TextToSpeechAPIFactory;

import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class Text2SpeechEditorView {

	private static Text2SpeechEditorView window = new Text2SpeechEditorView();
	private TextToSpeechAPIFactory newTextToSpeechAPIFactory = new TextToSpeechAPIFactory();
	private TextToSpeechAPI audioManager =  newTextToSpeechAPIFactory.createTTSAPI("free");
	
	private JFrame frame;
	
	private JTextField titleTextField;
	private JTextField authorTextField;
	private JTextArea textArea;
	
	private boolean replay = false;
	
	private JRadioButton storeCommands;

	private Document currentDocument = new Document("", "", "", "", new ArrayList<Line>(), audioManager);
	private CommandsFactory newCommandsFactory = new CommandsFactory();
	
    private int cursorPos = 1;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @wbp.parser.entryPoint
	 */
	public Text2SpeechEditorView() {
		
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Text To Speech Editor");
		frame.setBounds(100, 100, 600, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("File");
		menuBar.add(mnNewMenu);
		
		JLabel lblTitle = new JLabel("Title:");
		lblTitle.setBounds(29, 8, 48, 14);
		frame.getContentPane().add(lblTitle);
		
		titleTextField = new JTextField();
		titleTextField.setBounds(87, 5, 96, 20);
		frame.getContentPane().add(titleTextField);
		titleTextField.setColumns(10);
		
		JLabel lblAuthor = new JLabel("Author");
		lblAuthor.setBounds(193, 8, 48, 14);
		frame.getContentPane().add(lblAuthor);
		
		authorTextField = new JTextField();
		authorTextField.setBounds(251, 5, 96, 20);
		frame.getContentPane().add(authorTextField);
		authorTextField.setColumns(10);
		
		textArea = new JTextArea();
		textArea.setBounds(10, 36,  564, 292);
		frame.getContentPane().add(textArea);
				
		JScrollPane scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBounds(10, 36,  564, 292);
		frame.getContentPane().add(scrollPane);	
		
		
		// create a new Document with given author and title, and sets the created date
		JMenuItem mntmCreate = new JMenuItem("Create");
		mntmCreate.addActionListener(newCommandsFactory.createCommand("create"));
		mnNewMenu.add(mntmCreate);
		
		// edit the values of the document
		JMenuItem mntmEdit = new JMenuItem("Edit");
		mntmEdit.addActionListener(newCommandsFactory.createCommand("edit"));
		mnNewMenu.add(mntmEdit);

		// save the document with the current title, author, created date and contents
		// sets the saved date
		JMenuItem mntmSave = new JMenuItem("Save");
		mntmSave.addActionListener(newCommandsFactory.createCommand("save"));
		mnNewMenu.add(mntmSave);

		
		// creates new document and gives the values that has read from file and sets the current document, text fields and area
		JMenuItem mntmLoad = new JMenuItem("Load");
		mntmLoad.addActionListener(newCommandsFactory.createCommand("open"));
		mnNewMenu.add(mntmLoad);
		
		
		JMenu mnTransform = new JMenu("Transform");
		menuBar.add(mnTransform);
		
		// takes the currents contents of the documents and reads them
		JMenuItem mntmDocumentToSpeech = new JMenuItem("Document To Speech");	
		mntmDocumentToSpeech.addActionListener(newCommandsFactory.createCommand("contentsToSpeechNormal"));
		mnTransform.add(mntmDocumentToSpeech);
		
		// reads the line where the cursor is
		JMenuItem mntmLineToSpeech = new JMenuItem("Line To Speech");
		mntmLineToSpeech.addActionListener(newCommandsFactory.createCommand("lineToSpeechNormal"));
		mnTransform.add(mntmLineToSpeech);
		
		// takes the currents contents of the documents, reverses them and reads
		JMenuItem mntmReversedDocumentTo = new JMenuItem("Reversed Document To Speech");
		mntmReversedDocumentTo.addActionListener(newCommandsFactory.createCommand("contentsToSpeechRev"));
		mnTransform.add(mntmReversedDocumentTo);
		
		// reads the reversed line where the cursor is
		JMenuItem mntmReversedLineTo = new JMenuItem("Reversed Line To Speech");
		mntmReversedLineTo.addActionListener(newCommandsFactory.createCommand("lineToSpeechRev"));
		mnTransform.add(mntmReversedLineTo);
		
		JMenu mnEncode = new JMenu("Encode");
		menuBar.add(mnEncode);
		
		JMenu mnNewMenu_1 = new JMenu("Document");
		mnEncode.add(mnNewMenu_1);
		
		JMenuItem mntmAtbash = new JMenuItem("Atbash");
		mntmAtbash.addActionListener(newCommandsFactory.createCommand("atbashEncodingDoc"));
		mnNewMenu_1.add(mntmAtbash);
		
		JMenuItem mntmRot = new JMenuItem("Rot-13");
		mntmRot.addActionListener(newCommandsFactory.createCommand("rot13EncodingDoc"));
		mnNewMenu_1.add(mntmRot);
		
		JMenu mnLine = new JMenu("Line");
		mnEncode.add(mnLine);
		
		JMenuItem menuItem = new JMenuItem("Atbash");
		menuItem.addActionListener(newCommandsFactory.createCommand("atbashEncodingLine"));
		mnLine.add(menuItem);
		
		JMenuItem menuItem_1 = new JMenuItem("Rot-13");
		menuItem_1.addActionListener(newCommandsFactory.createCommand("rot13EncodingLine"));
		mnLine.add(menuItem_1);
		
		JMenu mnAudioParameters = new JMenu("Audio Parameters");
		menuBar.add(mnAudioParameters);
		
		JMenuItem mntmEditAudio = new JMenuItem("Edit");
		mntmEditAudio.addActionListener(newCommandsFactory.createCommand("editPar"));
		mnAudioParameters.add(mntmEditAudio);
		
		JMenuItem mntmRestore = new JMenuItem("Restore");
		mntmRestore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Text2SpeechEditorView.getSingletonView().getTextToSpeechAPI().setRate(150);
				Text2SpeechEditorView.getSingletonView().getTextToSpeechAPI().setPitch(100);
				Text2SpeechEditorView.getSingletonView().getTextToSpeechAPI().setVolume(1);
			}
		});
		
		mnAudioParameters.add(mntmRestore);
		
		JMenu mnMore = new JMenu("Replay Commands");
		menuBar.add(mnMore);
		
		storeCommands = new JRadioButton("Store Commands");
		mnMore.add(storeCommands);
		
		JMenuItem clearCommands = new JMenuItem("Clear Stored Commands");
		clearCommands.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ReplayManager.clearList();
			}
		});
		mnMore.add(clearCommands);
		
		JMenuItem reExecuteCommands = new JMenuItem("Re-execute Commands");
		reExecuteCommands.addActionListener(newCommandsFactory.createCommand("reExecuteCommands"));
		mnMore.add(reExecuteCommands);
		
		frame.getContentPane().setLayout(null);
		
	}

	public JRadioButton getButton() {
		return storeCommands;
	}
	
	public CommandsFactory getCommandsFactory() {
		return newCommandsFactory;
	}
	
	public boolean isReplay() {
		return replay;
	}
	
	public void setReplay(boolean state) {
		replay = state;
	}
	
	public String getVolume() {
		String s=String.valueOf(audioManager.getVoice().getVolume());
		return s;
	}
	
	public String getPitch() {
		String s=String.valueOf(audioManager.getVoice().getPitch()); 
		return s;
	}
	
	public String getRate() {
		String s=String.valueOf(audioManager.getVoice().getRate());
		return s;
	}
	
	public Document getCurrentDocument() {
		currentDocument.setAuthor(authorTextField.getText());
		currentDocument.setTitle(titleTextField.getText());
		
		ArrayList<Line> linesList = new ArrayList<Line>();
		
		String contents = textArea.getText();

		if (!contents.equals("")) {
			String[] lines = contents.split("\\n");
	
			for(String line: lines) {
				Line newLine = new Line(line, audioManager);
				linesList.add(newLine);
			} 
		}
		
		currentDocument.setContents(linesList);
		
		return currentDocument;
	}
	
	public TextToSpeechAPI getTextToSpeechAPI() {
		return audioManager;
	}
	
	public int getCursorPosition() {
		int caretPos = textArea.getCaretPosition();
		int rowNum = (caretPos == 0) ? 1 : 0;
		for (int offset2 = caretPos; offset2 > 0;) {
		    try {
				offset2 = Utilities.getRowStart(textArea, offset2) - 1;
			} catch (BadLocationException e) {
				e.printStackTrace();
			}
		    rowNum++;
		}
		cursorPos = rowNum - 1;
		return cursorPos;
	}
	
	public void setAPI() {
		audioManager = newTextToSpeechAPIFactory.createTTSAPI("fake");
	}
	
	public void setWindow() {
		titleTextField.setText(currentDocument.getTitle());
		authorTextField.setText(currentDocument.getAuthor());
		String cont = currentDocument.getStringContents();
		textArea.setText(cont);
	}
	
	public void setTextArea(String contents) {
		textArea.setText(contents);
	}
	
	public static Text2SpeechEditorView getSingletonView() {
		return window;
	}
}
