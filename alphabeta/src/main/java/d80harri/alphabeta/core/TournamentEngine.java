package d80harri.alphabeta.core;

import java.util.ArrayList;
import java.util.HashMap;

import d80harri.alphabeta.core.GameEngine.AgentDescription;
import d80harri.alphabeta.intfs.AlphaBetaPlayer;
import d80harri.alphabeta.intfs.IAgent;
import d80harri.alphabeta.intfs.IStartPositionCreator;
import d80harri.alphabeta.intfs.IPosition.GameResult;

public class TournamentEngine {
	public static class TournamentResult {
		private IAgent agent = null;
		private int wins;
		private int losses;
		private int remis;
		public TournamentResult(IAgent agent, int wins, int losses, int remis) {
			super();
			this.agent = agent;
			this.wins = wins;
			this.losses = losses;
			this.remis = remis;
		}
		
	}
	
	private ArrayList<IAgent> agents = null;
	private IStartPositionCreator startPositionCreator = null;
	
	
	public GameResult[] startTurnament() {
		HashMap<IAgent, TournamentResult> tournamentResults = new HashMap<IAgent, TournamentResult>();
		for (IAgent maxAgent : agents){
			for (IAgent minAgent : agents) {
				if (maxAgent != minAgent){
					AgentDescription[] descs = new AgentDescription[2]; // TODO games with more than two players
					descs[0] = new AgentDescription(AlphaBetaPlayer.MAX, maxAgent);
					descs[1] = new AgentDescription(AlphaBetaPlayer.MIN, minAgent);
					
					GameEngine ge = new GameEngine(startPositionCreator.createStartPosition(), descs);
					GameResult[] gameResult = ge.startGame();
					
				}
			}
		}
		
	}
}
