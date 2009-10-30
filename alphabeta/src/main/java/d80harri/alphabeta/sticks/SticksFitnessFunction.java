package d80harri.alphabeta.sticks;

import d80harri.alphabeta.intfs.AlphaBetaPlayer;
import d80harri.alphabeta.intfs.IFitnessFunction;
import d80harri.alphabeta.intfs.IPosition;

public class SticksFitnessFunction implements IFitnessFunction{

	@Override
	public double calculateFitness(IPosition arg0) {
		SticksPosition position = (SticksPosition) arg0;
		
		if (countSticks(position) == 0){
			if (position.getPlayerOnTurn() == AlphaBetaPlayer.MAX){
				return 1;
			} else if (position.getPlayerOnTurn() == AlphaBetaPlayer.MIN){
				return -1;
			} else {
				throw new RuntimeException("TODO"); //TODO Exception
			}
		}
		
		return 0;
	}
	
	private int countSticks(SticksPosition position){
		int count = 0;
		for (int i=0; i<position.getNumHeaps(); i++){
			count += position.getHeap(i);
		}
		return 0;
	}

}
