package d80harri.alphabeta.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import d80harri.alphabeta.intfs.IAgent;
import d80harri.alphabeta.intfs.IPlayer;
import d80harri.alphabeta.intfs.IPosition;
import d80harri.alphabeta.intfs.ITurn;

public class GameEngine {
	public static class AgentDescription {
		private IPlayer player = null;
		private IAgent agent = null;
		public AgentDescription(IPlayer player, IAgent agent) {
			super();
			this.player = player;
			this.agent = agent;
		}
	}
	
	private IPosition position = null;
	private Map<IPlayer, IAgent> agents = new HashMap<IPlayer, IAgent>();
	private ArrayList<IPosition> positions = new ArrayList<IPosition>();
	
	public GameEngine(IPosition position, AgentDescription... descriptors){
		this.position = position;
		
		for (AgentDescription descriptor : descriptors){
			this.agents.put(descriptor.player, descriptor.agent);
		}
	}
	
	public IPlayer startGame() {
		positions.clear();
		while (position.isGameOver() == null) {
			IPlayer playerOnTurn = position.getPlayerOnTurn();
			IAgent agentOnTurn = agents.get(playerOnTurn);
			
			if (agentOnTurn == null)
				throw new RuntimeException(); // TODO Exception
			
			ITurn turn = agentOnTurn.calculateTurn(position);
			
			positions.add(this.position);
			position = position.doTurn(turn);
		}
		positions.add(this.position);
		return position.isGameOver();
	}
	
	public ArrayList<IPosition> getPositions() {
		return positions;
	}
}
