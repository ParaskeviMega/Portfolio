package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TuneAudioWindow extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField volumeField;
	private JTextField rateField;
	private JTextField pitchField;
	private static TuneAudioWindow dialog = new TuneAudioWindow();
	
	private JButton okButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public TuneAudioWindow() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblPitch = new JLabel("Pitch:");
		lblPitch.setBounds(92, 112, 48, 14);
		contentPanel.add(lblPitch);
		
		JLabel lblVolume = new JLabel("Volume:");
		lblVolume.setBounds(92, 26, 48, 14);
		contentPanel.add(lblVolume);
		
		JLabel lblSpeechRate = new JLabel("Speech Rate:");
		lblSpeechRate.setBounds(92, 68, 105, 14);
		contentPanel.add(lblSpeechRate);
		
		volumeField = new JTextField();
		volumeField.setBounds(183, 23, 96, 20);
		contentPanel.add(volumeField);
		volumeField.setColumns(10);
		
		volumeField.setText(Text2SpeechEditorView.getSingletonView().getVolume());
		
		rateField = new JTextField();
		rateField.setBounds(183, 65, 96, 20);
		contentPanel.add(rateField);
		rateField.setColumns(10);
		
		rateField.setText(Text2SpeechEditorView.getSingletonView().getRate());
		
		pitchField = new JTextField();
		pitchField.setBounds(183, 109, 96, 20);
		contentPanel.add(pitchField);
		pitchField.setColumns(10);
		
		pitchField.setText(Text2SpeechEditorView.getSingletonView().getPitch());
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						float volume = 0, pitch = 0, rate = 0;
						
						if (!rateField.getText().equals("")) {
							rate = Float.parseFloat(rateField.getText());
							Text2SpeechEditorView.getSingletonView().getTextToSpeechAPI().setRate(rate);
						}
						if (!pitchField.getText().equals("")) {
							pitch = Float.parseFloat(pitchField.getText());
							Text2SpeechEditorView.getSingletonView().getTextToSpeechAPI().setPitch(pitch);
						}
							
						if (!volumeField.getText().equals("")) {
							volume = Float.parseFloat(volumeField.getText());
							Text2SpeechEditorView.getSingletonView().getTextToSpeechAPI().setVolume(volume);
						}
							
						
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
	public static TuneAudioWindow getSingletonView() {
		return dialog;
	}
		
	public JButton getOKButton() {
		return okButton;
	}
}
