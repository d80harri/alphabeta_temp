package d80harri.alphabeta.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.Collection;

import d80harri.alphabeta.intfs.AlphaBetaPlayer;
import d80harri.alphabeta.intfs.IFitnessFunction;
import d80harri.alphabeta.intfs.IPlayer;
import d80harri.alphabeta.intfs.IPosition;
import d80harri.alphabeta.intfs.ITurn;
import d80harri.alphabeta.intfs.ITurnGenerator;
import d80harri.alphabeta.search.AlphaBetaSearch;
import d80harri.alphabeta.ttt.TTTFitnessFunction;
import d80harri.alphabeta.ttt.TTTPosition;
import d80harri.alphabeta.ttt.TTTTurn;
import d80harri.alphabeta.ttt.TTTTurnGenerator;

public class TurnChooserHelper {
	public static ITurn chooseBestTurn(ITurnGenerator gen, IPosition pos, IFitnessFunction fit){
		Collection<ITurn> turns = gen.generateTurns(pos);
		double bestFitness = 0.0;
		ITurn bestTurn = null;
		
		for (ITurn turn : turns){
			double temp = fit.calculateFitness(pos.doTurn(turn));
			if (bestTurn == null ||
					pos.getPlayerOnTurn() == AlphaBetaPlayer.MAX && bestFitness < temp ||
					pos.getPlayerOnTurn() == AlphaBetaPlayer.MIN && bestFitness > temp){
				bestTurn = turn;
				bestFitness = temp;
			}
		}
		
		return bestTurn;
	}
	
	private static String prompt(String msg, BufferedReader reader) throws IOException{
		System.out.println(msg);
		String result = reader.readLine();
		return result;
	}
	
	private static AlphaBetaPlayer promptPlayer(BufferedReader reader) throws IOException {
		String out = prompt("Which player a/b ", reader);
		while (!out.equals("a") && !out.equals("b")){
			out = prompt("Which player a/b ", reader);
		}
		if (out.equals("a")){
			return AlphaBetaPlayer.MAX;
		} else {
			return AlphaBetaPlayer.MIN;
		}
	}
	
	private static ITurn promptTurn(IPlayer human, BufferedReader reader) throws IOException{
		String out = prompt("Which turn? ", reader);
		String[] split = out.split(" ");
		TTTTurn result = new TTTTurn(human, Integer.parseInt(split[0]), Integer.parseInt(split[1]));
		return result;
	}
	
	private static ITurn chooseTurn(ITurnGenerator gen, IPosition pos, IFitnessFunction fit, IPlayer human, BufferedReader reader) throws IOException{
		ITurn result = null;
		if (pos.getPlayerOnTurn() == human){
			result = promptTurn(human, reader);
		} else {
			result = chooseBestTurn(gen, pos, fit);
		}
		return result;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		
		TTTTurnGenerator gen = new TTTTurnGenerator();
		TTTFitnessFunction fit = new TTTFitnessFunction();
		AlphaBetaSearch ab = new AlphaBetaSearch(fit, gen, 9);

		AlphaBetaPlayer human = promptPlayer(reader);
		
		
		IPosition pos = new TTTPosition(new IPlayer[]{
				AlphaBetaPlayer.MIN, AlphaBetaPlayer.MAX, null,
				null, AlphaBetaPlayer.MAX, null,
				null, null, null
		}, AlphaBetaPlayer.MIN);
		System.out.println(pos);
		ITurn turn = chooseTurn(gen, pos, ab, human, reader);
		
		while (turn != null){
			pos = pos.doTurn(turn);
			System.out.println(pos);
			turn = chooseTurn(gen, pos, ab, human, reader);
		}

		
	}
}
