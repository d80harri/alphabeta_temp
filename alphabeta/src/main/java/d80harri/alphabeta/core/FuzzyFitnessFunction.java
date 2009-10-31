package d80harri.alphabeta.core;

import d80harri.alphabeta.intfs.IFitnessFunction;
import d80harri.alphabeta.intfs.IPosition;
import d80harri.alphabeta.intfs.IRandom;

public class FuzzyFitnessFunction implements IFitnessFunction {
	private IFitnessFunction fitnessFunction = null;
	private IRandom random = null;

	public FuzzyFitnessFunction(IFitnessFunction fitnessFunction, IRandom random) {
		this.fitnessFunction = fitnessFunction;
		this.random = random;
	}

	@Override
	public double calculateFitness(IPosition position) {
		return random.nextDouble() + fitnessFunction.calculateFitness(position);
	}

}
