package d80harri.alphabeta.ttt;

import java.util.ArrayList;
import java.util.Collection;

import d80harri.alphabeta.intfs.IPosition;
import d80harri.alphabeta.intfs.ITurn;
import d80harri.alphabeta.intfs.ITurnGenerator;
import d80harri.alphabeta.ttt.utils.FitnessCalculation;

public class TTTTurnGenerator implements ITurnGenerator {

	@Override
	public Collection<ITurn> generateTurns(IPosition arg0) {
		ArrayList<ITurn> result = new ArrayList<ITurn>();
		TTTPosition position = (TTTPosition) arg0;
		if (FitnessCalculation.isFinished(position) == null) {
			for (int i = 0; i < TTTPosition.NUM_COLS; i++) {
				for (int j = 0; j < TTTPosition.NUM_ROWS; j++) {
					if (position.getStone(i, j) == null) {
						result
								.add(new TTTTurn(position.getPlayerOnTurn(), i,
										j));
					}
				}
			}
		}
		return result;
	}

}
