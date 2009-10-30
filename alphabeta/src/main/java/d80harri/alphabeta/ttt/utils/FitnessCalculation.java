package d80harri.alphabeta.ttt.utils;

import d80harri.alphabeta.intfs.AlphaBetaPlayer;
import d80harri.alphabeta.intfs.IPosition;
import d80harri.alphabeta.ttt.TTTPosition;

public class FitnessCalculation {
	
	public static double calculateFitness(IPosition arg0) {
		TTTPosition position = (TTTPosition) arg0;
		
		for (int i=0; i<TTTPosition.NUM_COLS; i++){
			if (checkCols(position, i, AlphaBetaPlayer.MAX))
				return 1;
			if (checkCols(position, i, AlphaBetaPlayer.MIN))
				return -1;
		}
		
		for (int i=0; i<TTTPosition.NUM_ROWS; i++){
			if (checkRows(position, i, AlphaBetaPlayer.MAX))
				return 1;
			if (checkRows(position, i, AlphaBetaPlayer.MIN))
				return -1;
		}
		
		// TODO Diagonals
		
		return 0;
	}
	
	public static boolean isFinished(IPosition arg0){
		return calculateFitness(arg0) != 0;
	}

	private static boolean checkRows(TTTPosition position, int i, AlphaBetaPlayer stone) {
		return position.getStone(0, i) == stone &&
		position.getStone(1, i) == stone &&
		position.getStone(2, i) == stone;
	}

	private static boolean checkCols(TTTPosition position, int i, AlphaBetaPlayer stone) {
		return position.getStone(i, 0) == stone &&
		position.getStone(i, 1) == stone &&
		position.getStone(i, 2) == stone;
	}

}
