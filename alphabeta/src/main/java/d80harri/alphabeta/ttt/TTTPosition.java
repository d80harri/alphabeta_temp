package d80harri.alphabeta.ttt;

import d80harri.alphabeta.intfs.AlphaBetaPlayer;
import d80harri.alphabeta.intfs.IPlayer;
import d80harri.alphabeta.intfs.IPosition;
import d80harri.alphabeta.intfs.ITurn;
import d80harri.alphabeta.search.AlphaBetaSearch;

public class TTTPosition implements IPosition{	
	public static final int NUM_ROWS = 3;
	public static final int NUM_COLS = 3;
	public static final int NUM_FIELDS = NUM_ROWS * NUM_COLS;
	
	private IPlayer[] fields = new IPlayer[NUM_FIELDS];
	private AlphaBetaPlayer onTurn = AlphaBetaPlayer.MAX;
	
	
	public TTTPosition(){}
	
	public TTTPosition(IPlayer[] field, AlphaBetaPlayer onTurn){
		if (field.length > NUM_FIELDS){
			throw new RuntimeException("TODO"); // TODO
		}
		this.fields = field;
		this.onTurn = onTurn;
	}
	
	public IPlayer getPlayerOnTurn(){
		return this.onTurn;
	}
	
	public AlphaBetaPlayer getStone(int x, int y){
		int idx = y * NUM_COLS + x;
		if (idx > NUM_FIELDS){
			throw new RuntimeException("TODO"); // TODO
		}
		return (AlphaBetaPlayer)this.fields[idx];
	}
	
	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		for (int i=0; i<NUM_COLS; i++){
			for (int j=0; j<NUM_ROWS; j++){
				buffer.append(fields[i * NUM_ROWS + j]);
			}
			buffer.append("\n");
		}
		return buffer.toString();
	}
	@Override
	public IPosition doTurn(ITurn arg0) {
		TTTTurn turn = (TTTTurn) arg0;
		if (turn.getPlayer() != this.onTurn)
			throw new RuntimeException("TODO"); //TODO
		int idx = turn.getRow() * NUM_COLS + turn.getCol();
		TTTPosition result = new TTTPosition(this.fields.clone(), null);
		result.fields[idx] = turn.getPlayer();
		if (this.onTurn == AlphaBetaPlayer.MAX)
			result.onTurn = AlphaBetaPlayer.MIN;
		else
			result.onTurn = AlphaBetaPlayer.MAX;
		return result;
	}


}
