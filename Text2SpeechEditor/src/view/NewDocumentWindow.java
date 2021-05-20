package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Document;
import model.Line;

import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.awt.event.ActionEvent;

public class NewDocumentWindow extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JButton okButton;
	
	private JTextField authorTextField;
	private JTextField titleTextField;
	
	private Document newDocument;
	
	private static NewDocumentWindow dialog = new NewDocumentWindow();
	
	private static final DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			//dialog = new NewDocumentWindow();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public NewDocumentWindow() {
		setTitle("New Document");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblAuthor = new JLabel("Author");
		lblAuthor.setBounds(76, 102, 48, 14);
		contentPanel.add(lblAuthor);
		
		JLabel lblTitle = new JLabel("Title");
		lblTitle.setBounds(76, 69, 48, 14);
		contentPanel.add(lblTitle);
		
		authorTextField = new JTextField();
		authorTextField.setBounds(154, 99, 96, 20);
		contentPanel.add(authorTextField);
		authorTextField.setColumns(10);
		
		titleTextField = new JTextField();
		titleTextField.setBounds(154, 66, 96, 20);
		contentPanel.add(titleTextField);
		titleTextField.setColumns(10);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String title = titleTextField.getText();
						String author = authorTextField.getText();
						
						Calendar cal = Calendar.getInstance();
						String createdDate = sdf.format(cal.getTime());
						
						newDocument = Text2SpeechEditorView.getSingletonView().getCurrentDocument();
						
						newDocument.setTitle(title);
						newDocument.setAuthor(author);
						newDocument.setCreatedDate(createdDate);
						newDocument.setSavedDate("");
						newDocument.setContents(new ArrayList<Line>());
						
						Text2SpeechEditorView.getSingletonView().setWindow();
						
						dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	// χρησιμοποιούνται για το τεστάκι του create
	public static NewDocumentWindow getSingletonView() {
		return dialog;
	}
	
	public JButton getOKButton() {
		return okButton;
	}
}
