package commands;

import java.awt.event.ActionEvent;

public class ReplayCommand implements MyActionListener{
	
	ReplayManager newReplayManager;
	
	public ReplayCommand(ReplayManager newReplayManager) {
		this.newReplayManager = newReplayManager;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		newReplayManager.replay();
	}
}
