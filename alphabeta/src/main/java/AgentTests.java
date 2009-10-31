import d80harri.alphabeta.core.GameEngine;
import d80harri.alphabeta.core.GameEngine.AgentDescription;
import d80harri.alphabeta.intfs.AlphaBetaPlayer;
import d80harri.alphabeta.intfs.IPlayer;
import d80harri.alphabeta.sticks.FuzzySticksAgent;
import d80harri.alphabeta.sticks.SticksAgentV1;
import d80harri.alphabeta.sticks.SticksPosition;


public class AgentTests {
	public static void main(String[] args) {
		GameEngine engine = new GameEngine(
				new SticksPosition(new int[]{5,6,7}, AlphaBetaPlayer.MAX),
				new AgentDescription(AlphaBetaPlayer.MAX, new SticksAgentV1()),
				new AgentDescription(AlphaBetaPlayer.MIN, new FuzzySticksAgent())
			);
		
		IPlayer player = engine.startGame();
		
		System.out.println(engine.getPositions());
		System.out.println(player);
		
	}
}
