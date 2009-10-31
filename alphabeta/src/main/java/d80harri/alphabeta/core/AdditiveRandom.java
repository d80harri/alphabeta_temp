package d80harri.alphabeta.core;

import java.util.Random;

import d80harri.alphabeta.intfs.IRandom;

public class AdditiveRandom implements IRandom{
	private Random random = new Random();
	private double minValue = -1.0;
	private double maxValue = 1.0;
	private int diffusion = 2;
	
	public AdditiveRandom(double minValue, double maxValue, int diffusion){
		this.minValue = minValue;
		this.maxValue = maxValue;
		this.diffusion = diffusion;
	}
	
	@Override
	public double nextDouble() {
		double result = 0.0;
		double  spreading = maxValue - minValue;
		
		for (int i=0; i<this.diffusion; i++){
			result = result + random.nextDouble() * spreading / diffusion;
		}
		return result + minValue;
	}

}
