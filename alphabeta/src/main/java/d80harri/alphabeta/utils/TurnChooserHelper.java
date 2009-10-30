package d80harri.alphabeta.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collection;

import d80harri.alphabeta.core.VectorTurn;
import d80harri.alphabeta.intfs.AlphaBetaPlayer;
import d80harri.alphabeta.intfs.IFitnessFunction;
import d80harri.alphabeta.intfs.IPlayer;
import d80harri.alphabeta.intfs.IPosition;
import d80harri.alphabeta.intfs.ITurn;
import d80harri.alphabeta.intfs.ITurnGenerator;
import d80harri.alphabeta.search.AlphaBetaSearch;
import d80harri.alphabeta.sticks.SticksFitnessFunction;
import d80harri.alphabeta.sticks.SticksPosition;
import d80harri.alphabeta.sticks.SticksTurnGenerator;
import d80harri.alphabeta.ttt.TTTFitnessFunction;
import d80harri.alphabeta.ttt.TTTPosition;
import d80harri.alphabeta.ttt.TTTTurn;
import d80harri.alphabeta.ttt.TTTTurnGenerator;

public class TurnChooserHelper {
	private static interface StringToTurnConverter{
		public ITurn convert(String str, IPlayer player);
	}
	
	private static class TTTStringToTurnConverter implements StringToTurnConverter {

		@Override
		public ITurn convert(String out, IPlayer player) {
			String[] split = out.split(" ");
			TTTTurn result = new TTTTurn(player, Integer.parseInt(split[0]), Integer
					.parseInt(split[1]));
			return result;
		}
	}
	
	private static class SticksStringToTurnConverter implements StringToTurnConverter {

		@Override
		public ITurn convert(String out, IPlayer player) {
			String[] split = out.split(" ");
			VectorTurn<Integer> result = new VectorTurn<Integer>(player, new Integer[]{Integer.parseInt(split[0]), Integer
					.parseInt(split[1])});
			return result;
		}
	}
	
	public static ITurn chooseBestTurn(ITurnGenerator gen, IPosition pos,
			IFitnessFunction fit) {
		Collection<ITurn> turns = gen.generateTurns(pos);
		double bestFitness = 0.0;
		ITurn bestTurn = null;

		for (ITurn turn : turns) {
			double temp = fit.calculateFitness(pos.doTurn(turn));
			if (bestTurn == null
					|| pos.getPlayerOnTurn() == AlphaBetaPlayer.MAX
					&& bestFitness < temp
					|| pos.getPlayerOnTurn() == AlphaBetaPlayer.MIN
					&& bestFitness > temp) {
				bestTurn = turn;
				bestFitness = temp;
			}
		}
		System.out.println("found best turn " + bestTurn);
		return bestTurn;
	}

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

	private static ITurn promptTurn(IPlayer human, BufferedReader reader, StringToTurnConverter converter)
			throws IOException {
		String out = prompt("Which turn? ", reader);
		return converter.convert(out, human);
	}

	private static ITurn chooseTurn(ITurnGenerator gen, IPosition pos,
			IFitnessFunction fit, IPlayer human, BufferedReader reader, StringToTurnConverter converter)
			throws IOException {
		ITurn result = null;
		if (pos.getPlayerOnTurn() == human) {
			result = promptTurn(human, reader, converter);
		} else {
			result = chooseBestTurn(gen, pos, fit);
		}
		return result;
	}

	public static void main(String[] args) throws IOException {
//		startTTT();
		startSticks();
	}

	private static void startSticks() throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				System.in));

		ITurnGenerator gen = new SticksTurnGenerator();
		IFitnessFunction fit = new SticksFitnessFunction();
		AlphaBetaSearch ab = new AlphaBetaSearch(fit, gen, 18);
		IPosition pos = new SticksPosition(new int[]{5,6,7}, AlphaBetaPlayer.MAX);

		startGame(pos, reader, gen, ab, new SticksStringToTurnConverter());
	}

	private static void startTTT() throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				System.in));

		TTTTurnGenerator gen = new TTTTurnGenerator();
		TTTFitnessFunction fit = new TTTFitnessFunction();
		AlphaBetaSearch ab = new AlphaBetaSearch(fit, gen, 9);
		IPosition pos = new TTTPosition(/*
										 * new IPlayer[]{ AlphaBetaPlayer.MIN,
										 * AlphaBetaPlayer.MAX, null, null,
										 * AlphaBetaPlayer.MAX, null, null,
										 * null, null }, AlphaBetaPlayer.MIN
										 */
		);

		startGame(pos, reader, gen, ab, new TTTStringToTurnConverter());
	}

	private static void startGame(IPosition pos, BufferedReader reader,
			ITurnGenerator gen, AlphaBetaSearch ab, StringToTurnConverter converter) throws IOException {
		AlphaBetaPlayer human = promptPlayer(reader);

		System.out.println(pos);
		ITurn turn = chooseTurn(gen, pos, ab, human, reader, converter);

		while (turn != null) {
			pos = pos.doTurn(turn);
			System.out.println(pos);
			turn = chooseTurn(gen, pos, ab, human, reader, converter);
		}
	}
}
