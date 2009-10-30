package d80harri.alphabeta.sticks;

import java.util.ArrayList;
import java.util.Collection;

import d80harri.alphabeta.core.VectorTurn;
import d80harri.alphabeta.intfs.IPosition;
import d80harri.alphabeta.intfs.ITurn;
import d80harri.alphabeta.intfs.ITurnGenerator;

public class SticksTurnGenerator implements ITurnGenerator {

	@Override
	public Collection<ITurn> generateTurns(IPosition arg0) {
		Collection<ITurn> result = new ArrayList<ITurn>();
		SticksPosition position = (SticksPosition) arg0;
		for (int i = 0; i < position.getNumHeaps(); i++) {
			for (int j = 1; j <= position.getHeap(i); j++) {
				Integer[] vec = new Integer[2];
				vec[SticksPosition.TURN_HEAP_IDX] = i;
				vec[SticksPosition.TURN_NUM_IDX] = j;
				result.add(new VectorTurn<Integer>(position.getPlayerOnTurn(),
						vec));
			}
		}
		return result;
	}

}
