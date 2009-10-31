package d80harri.alphabeta.sticks;

import d80harri.alphabeta.core.AbstractCmdAgent;
import d80harri.alphabeta.core.VectorTurn;
import d80harri.alphabeta.intfs.IPlayer;
import d80harri.alphabeta.intfs.ITurn;

public class SticksCmdAgent extends AbstractCmdAgent {
	private static class SticksStringToTurnConverter implements
			StringToTurnConverter {

		@Override
		public ITurn convert(String out, IPlayer player) {
			String[] split = out.split(" ");
			VectorTurn<Integer> result = new VectorTurn<Integer>(player,
					new Integer[] { Integer.parseInt(split[0]),
							Integer.parseInt(split[1]) });
			return result;
		}
	}

	public SticksCmdAgent() {
		super(new SticksStringToTurnConverter());
	}

}
