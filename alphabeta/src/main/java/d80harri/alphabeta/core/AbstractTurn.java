package d80harri.alphabeta.core;

import d80harri.alphabeta.intfs.IPlayer;
import d80harri.alphabeta.intfs.ITurn;

public abstract class AbstractTurn implements ITurn{
	private IPlayer player = null;
	
	public AbstractTurn(IPlayer player){
		this.player = player;
	}
	@Override
	public IPlayer getPlayer() {
		return this.player;
	}
	
	protected void setPlayer(IPlayer player){
		this.player = player;
	}

}
