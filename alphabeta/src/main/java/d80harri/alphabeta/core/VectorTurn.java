package d80harri.alphabeta.core;

import java.util.Arrays;

import d80harri.alphabeta.intfs.IPlayer;

public class VectorTurn<T> extends AbstractTurn{
	private T[] values = null;
	
	public VectorTurn(IPlayer player, T[] values) {
		super(player);
		this.values = values;
	}
	
	public T getValueAt(int i){
		return values[i];
	}
	
	@Override
	public String toString() {
		return Arrays.toString(values);
	}
}
