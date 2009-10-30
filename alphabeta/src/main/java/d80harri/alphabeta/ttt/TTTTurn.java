package d80harri.alphabeta.ttt;

import d80harri.alphabeta.intfs.IPlayer;
import d80harri.alphabeta.intfs.ITurn;

public class TTTTurn implements ITurn{
	private IPlayer player = null;
	private int col;
	private int row;
	
	public TTTTurn(IPlayer player, int col, int row){
		this.player = player;
		this.col = col;
		this.row = row;
	}
	
	@Override
	public String toString() {
		return "[Turn [" + player + ": "+ col + ", " + row + "]";
	}

	public int getRow(){
		return this.row;
	}
	
	public int getCol() {
		return this.col;
	}
	
	@Override
	public IPlayer getPlayer() {
		return player;
	}
}
