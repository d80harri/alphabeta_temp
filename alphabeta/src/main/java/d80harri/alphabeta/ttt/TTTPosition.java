package d80harri.alphabeta.ttt;

import d80harri.alphabeta.core.AbstractPosition;
import d80harri.alphabeta.intfs.AlphaBetaPlayer;
import d80harri.alphabeta.intfs.IPlayer;
import d80harri.alphabeta.intfs.IPosition;
import d80harri.alphabeta.intfs.ITurn;
import d80harri.alphabeta.ttt.utils.FitnessCalculation;

public class TTTPosition extends AbstractPosition{	
	public static final int NUM_ROWS = 3;
	public static final int NUM_COLS = 3;
	public static final int NUM_FIELDS = NUM_ROWS * NUM_COLS;
	
	private IPlayer[] fields = new IPlayer[NUM_FIELDS];
	
	public TTTPosition(){
		super(AlphaBetaPlayer.MAX);
	}
	
	public TTTPosition(IPlayer[] field, AlphaBetaPlayer onTurn){
		super(onTurn);
		if (field.length > NUM_FIELDS){
			throw new RuntimeException("TODO"); // TODO
		}
		this.fields = field;
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
		if (turn.getPlayer() != this.getPlayerOnTurn())
			throw new RuntimeException("TODO"); //TODO
		int idx = turn.getRow() * NUM_COLS + turn.getCol();
		TTTPosition result = new TTTPosition(this.fields.clone(), null);
		result.fields[idx] = turn.getPlayer();
		if (this.getPlayerOnTurn() == AlphaBetaPlayer.MAX)
			result.setPlayerOnTurn(AlphaBetaPlayer.MIN);
		else
			result.setPlayerOnTurn(AlphaBetaPlayer.MAX);
		return result;
	}

	@Override
	public boolean isGameOver() {
		return FitnessCalculation.isFinished(this);
	}

	@Override
	public GameResult[] gameResults() {
		GameResult[] results = null;
		if (isGameOver()){
			results = new GameResult[2];
			if (FitnessCalculation.calculateFitness(this) < 0){
				results[0] = new GameResult(AlphaBetaPlayer.MIN, 0, 1, 0);
				results[1] = new GameResult(AlphaBetaPlayer.MAX, 1, 0, 0);
			} else if (FitnessCalculation.calculateFitness(this) >  0) {
				results[0] = new GameResult(AlphaBetaPlayer.MIN, 1, 0, 0);
				results[1] = new GameResult(AlphaBetaPlayer.MAX, 0, 1, 0);
			} else {
				results[0] = new GameResult(AlphaBetaPlayer.MIN, 0, 0, 1);
				results[1] = new GameResult(AlphaBetaPlayer.MAX, 0, 0, 1);
			}
		}
		return results;
	}


}
