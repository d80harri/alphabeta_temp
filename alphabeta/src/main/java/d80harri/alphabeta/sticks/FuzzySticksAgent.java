package d80harri.alphabeta.sticks;

import d80harri.alphabeta.core.AdditiveRandom;
import d80harri.alphabeta.core.FuzzyFitnessFunction;
import d80harri.alphabeta.intfs.IPosition;
import d80harri.alphabeta.intfs.ITurn;
import d80harri.alphabeta.search.AlphaBetaSearch;

public class FuzzySticksAgent extends SticksAgentV1{
	private int depth = 20;
	private SticksTurnGenerator gen = new SticksTurnGenerator();
	private FuzzyFitnessFunction fit = new FuzzyFitnessFunction(new SticksFitnessFunction(), new AdditiveRandom(-0.01, 0.01, 2));
	private AlphaBetaSearch search = new AlphaBetaSearch(fit, gen, depth);



	@Override
	public ITurn calculateTurn(IPosition position) {
		return search.doSearch(position).getTurn();
	}

}
