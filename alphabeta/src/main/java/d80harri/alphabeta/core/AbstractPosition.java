package d80harri.alphabeta.core;

import d80harri.alphabeta.intfs.AlphaBetaPlayer;
import d80harri.alphabeta.intfs.IPlayer;
import d80harri.alphabeta.intfs.IPosition;

public abstract class AbstractPosition implements IPosition{
	private IPlayer onTurn = AlphaBetaPlayer.MAX;
	public IPlayer getPlayerOnTurn(){
		return this.onTurn;
	}
	protected void setPlayerOnTurn(IPlayer player){
		this.onTurn = player;
	}
	
	public AbstractPosition(IPlayer onTurn){
		this.onTurn = onTurn;
	}
}
