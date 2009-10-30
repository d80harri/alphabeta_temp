package d80harri.alphabeta.intfs;

import java.util.Collection;

public interface ITurnGenerator {
	public Collection<ITurn> generateTurns(IPosition position);
}
