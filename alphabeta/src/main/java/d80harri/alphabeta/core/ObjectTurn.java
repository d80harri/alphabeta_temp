package d80harri.alphabeta.core;

import d80harri.alphabeta.intfs.IPlayer;

public class ObjectTurn<T> extends AbstractTurn{
	private T value = null;
	
	public ObjectTurn(IPlayer player, T value) {
		super(player);
		this.value = value;
	}


}
