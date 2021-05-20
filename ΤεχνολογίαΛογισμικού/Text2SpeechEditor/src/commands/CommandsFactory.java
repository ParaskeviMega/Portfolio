package commands;

public class CommandsFactory {

	public CommandsFactory() {
	}
	
	public MyActionListener createCommand(String command) {
		if (command.equals("create")) 
			return new NewDocument();
		else if(command.equals("edit")) 
			return new EditDocument();
		else if(command.equals("save")) 
			return new SaveDocument();
		else if(command.equals("open")) 
			return new OpenDocument();
		else if(command.equals("contentsToSpeechNormal")) 
			return new DocumentToSpeech("Normal");
		else if(command.equals("lineToSpeechNormal")) 
			return new LineToSpeech("Normal");
		else if(command.equals("contentsToSpeechRev")) 
			return new DocumentToSpeech("Rev");
		else if(command.equals("lineToSpeechRev")) 
			return new LineToSpeech("Rev");
		else if(command.equals("rot13EncodingDoc")) 
			return new TuneEncoding("rot13", "Doc");
		else if(command.equals("atbashEncodingDoc")) 
			return new TuneEncoding("atbash", "Doc");
		else if(command.equals("rot13EncodingLine")) 
			return new TuneEncoding("rot13", "Line");
		else if(command.equals("atbashEncodingLine")) 
			return new TuneEncoding("atbash", "Line");
		else if(command.equals("reExecuteCommands")) 
			return new ReplayCommand(new ReplayManager());
		else if(command.equals("editPar")) 
			return new TuneAudio();
		else
			return null;
	}
}