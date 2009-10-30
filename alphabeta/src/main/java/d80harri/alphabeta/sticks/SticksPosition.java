package d80harri.alphabeta.sticks;

import d80harri.alphabeta.core.AbstractPosition;
import d80harri.alphabeta.core.VectorTurn;
import d80harri.alphabeta.intfs.AlphaBetaPlayer;
import d80harri.alphabeta.intfs.IPosition;
import d80harri.alphabeta.intfs.ITurn;

public class SticksPosition extends AbstractPosition{
	public static final int TURN_HEAP_IDX = 0;
	public static final int TURN_NUM_IDX = 1;
	
	private int[] heaps = null;
	
	public SticksPosition(int[] sticks, AlphaBetaPlayer player){
		super(player);
		this.heaps = sticks.clone();
	}
	
	public int getNumHeaps(){
		return this.heaps.length;
	}
	
	public int getHeap(int idx){
		return heaps[idx];
	}
	
	@Override
	public IPosition doTurn(ITurn arg0) {
		SticksPosition result = new SticksPosition(this.heaps.clone(), 
				this.getPlayerOnTurn() == AlphaBetaPlayer.MAX 
				? AlphaBetaPlayer.MIN 
				: AlphaBetaPlayer.MAX);
		VectorTurn<Integer> turn = (VectorTurn<Integer>) arg0;
		int heap = turn.getValueAt(TURN_HEAP_IDX);
		int num = turn.getValueAt(TURN_NUM_IDX);
		
		result.heaps[heap] = this.heaps[heap] - num;
		
		if (this.heaps[heap] < 0) throw new RuntimeException("TODO"); // TODO Exception
		return result;
	}

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		for (int i=0; i<this.getNumHeaps(); i++){
			buffer.append(this.getHeap(i) + " ");
		}
		return buffer.toString();
	}
}
