package d80harri.alphabeta.sticks;

import d80harri.alphabeta.intfs.IAgent;
import d80harri.alphabeta.intfs.IPosition;
import d80harri.alphabeta.intfs.ITurn;
import d80harri.alphabeta.search.AlphaBetaSearch;

public class SticksAgentV1 implements IAgent {
	private int depth = 20;
	private SticksTurnGenerator gen = new SticksTurnGenerator();
	private SticksFitnessFunction fit = new SticksFitnessFunction();
	private AlphaBetaSearch search = new AlphaBetaSearch(fit, gen, depth);



	@Override
	public ITurn calculateTurn(IPosition position) {
		return search.doSearch(position).getTurn();
	}

}
