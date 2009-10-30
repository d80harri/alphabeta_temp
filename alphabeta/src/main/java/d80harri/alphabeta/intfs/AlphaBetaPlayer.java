package d80harri.alphabeta.intfs;

public class AlphaBetaPlayer implements IPlayer{
	private String ident = null;
	
	private AlphaBetaPlayer(String ident) {
		this.ident = ident;
	};
	
	public static final AlphaBetaPlayer MAX = new AlphaBetaPlayer("max ");
	public static final AlphaBetaPlayer MIN = new AlphaBetaPlayer("min ");
	
	@Override
	public String toString() {
		return ident;
	}
}
