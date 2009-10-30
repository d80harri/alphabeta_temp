package d80harri.alphabeta.ttt;

import d80harri.alphabeta.intfs.IFitnessFunction;
import d80harri.alphabeta.intfs.IPosition;
import d80harri.alphabeta.ttt.utils.FitnessCalculation;

public class TTTFitnessFunction implements IFitnessFunction{

	@Override
	public double calculateFitness(IPosition position) {
		TTTPosition pos = (TTTPosition) position;
		return FitnessCalculation.calculateFitness(pos);
	}

	


}
