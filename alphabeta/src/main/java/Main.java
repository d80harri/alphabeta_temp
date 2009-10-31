import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import d80harri.alphabeta.core.GameEngine;
import d80harri.alphabeta.core.GameEngine.AgentDescription;
import d80harri.alphabeta.intfs.AlphaBetaPlayer;
import d80harri.alphabeta.intfs.IAgent;
import d80harri.alphabeta.intfs.IPosition;
import d80harri.alphabeta.sticks.SticksAgentV1;
import d80harri.alphabeta.sticks.SticksCmdAgent;
import d80harri.alphabeta.sticks.SticksPosition;

public class Main {

	private static String prompt(String msg, BufferedReader reader)
			throws IOException {
		System.out.println(msg);
		String result = reader.readLine();
		return result;
	}

	private static AlphaBetaPlayer promptPlayer(BufferedReader reader)
			throws IOException {
		String out = prompt("Which player a/b ", reader);
		while (!out.equals("a") && !out.equals("b")) {
			out = prompt("Which player a/b ", reader);
		}
		if (out.equals("a")) {
			return AlphaBetaPlayer.MAX;
		} else {
			return AlphaBetaPlayer.MIN;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in)); 
		
		GameEngine engine = null;
		IPosition position = new SticksPosition(new int[] { 5, 6, 7 },
				AlphaBetaPlayer.MAX);
		IAgent compAgent = new SticksAgentV1();
		IAgent humaAgent = new SticksCmdAgent();
		AgentDescription[] ad = new AgentDescription[2];
		
		if (promptPlayer(reader) == AlphaBetaPlayer.MAX){
			ad[0] = new AgentDescription(AlphaBetaPlayer.MAX, humaAgent);
			ad[1] = new AgentDescription(AlphaBetaPlayer.MIN, compAgent);
		} else {
			ad[0] = new AgentDescription(AlphaBetaPlayer.MIN, humaAgent);
			ad[1] = new AgentDescription(AlphaBetaPlayer.MAX, compAgent);
		}
		
		engine = new GameEngine(position, ad);
		
		engine.startGame();
		
		reader.close();
	}
}
