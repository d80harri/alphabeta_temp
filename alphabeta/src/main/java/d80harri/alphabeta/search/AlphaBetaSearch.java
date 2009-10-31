package d80harri.alphabeta.search;

import java.util.Collection;

import d80harri.alphabeta.intfs.AlphaBetaPlayer;
import d80harri.alphabeta.intfs.IFitnessFunction;
import d80harri.alphabeta.intfs.IPlayer;
import d80harri.alphabeta.intfs.IPosition;
import d80harri.alphabeta.intfs.ISearch;
import d80harri.alphabeta.intfs.ITurn;
import d80harri.alphabeta.intfs.ITurnGenerator;

public class AlphaBetaSearch implements ISearch, IFitnessFunction {
	private IFitnessFunction fit = null;
	private ITurnGenerator gen = null;
	private int depth = 1;

	public AlphaBetaSearch(IFitnessFunction fit, ITurnGenerator gen, int depth) {
		this.fit = fit;
		this.gen = gen;
		this.depth = depth;
	}

	@Override
	public SearchResult doSearch(IPosition position) {
		Collection<ITurn> turns = gen.generateTurns(position);
		IPlayer onTurn = position.getPlayerOnTurn();
		SearchResult result = null;
		
		for (ITurn turn : turns){
			IPosition nextPos = position.doTurn(turn);
			double fitness = this.calculateFitnessHelper(nextPos, depth-1);
			if (result == null){
				result = new SearchResult(turn, nextPos, fitness);
			} else if (onTurn == AlphaBetaPlayer.MAX && fitness > result.getFitness() ||
					onTurn == AlphaBetaPlayer.MIN && fitness < result.getFitness()){
				result.setTurn(turn);
				result.setPosition(nextPos);
				result.setFitness(fitness);
			}
		}
		return result;
	}

	@Override
	public double calculateFitness(IPosition position) {
		return calculateFitnessHelper(position, this.depth);
	}
	
	private double calculateFitnessHelper(IPosition position, int depth){
		if (position.getPlayerOnTurn() == AlphaBetaPlayer.MAX)
			return max(depth, position, Integer.MIN_VALUE, Integer.MAX_VALUE);
		else
			return min(depth, position, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}

	public double max(int depth, IPosition pos, double alpha, double beta) {
		Collection<ITurn> turns = gen.generateTurns(pos);
		if (depth <= 0 || turns.size() == 0) {
			return fit.calculateFitness(pos);
		}
		double fit = 0.0;

		for (ITurn turn : turns) {
			IPosition nextPosition = pos.doTurn(turn);
			fit = min(depth - 1, nextPosition, alpha, beta);
			if (fit >= beta)
				return beta;
			if (fit > alpha)
				alpha = fit;
		}
		return alpha;
	}

	private double min(int depth, IPosition pos, double alpha, double beta) {
		Collection<ITurn> turns = gen.generateTurns(pos);
		if (depth == 0 || turns.size() == 0) {
			return fit.calculateFitness(pos);
		}
		double fit = 0.0;

		for (ITurn turn : turns) {
			IPosition nextPosition = pos.doTurn(turn);
			fit = max(depth - 1, nextPosition, alpha, beta);
			if (fit <= alpha)
				return alpha;
			if (fit < beta)
				beta = fit;
		}
		return beta;
	}

}
