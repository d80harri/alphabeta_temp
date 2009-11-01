package d80harri.alphabeta.intfs;

public interface IPosition {
	public static class GameResult {
		private IPlayer player = null;
		private int losses = 0;
		private int wins = 0;
		private int remis = 0;
		public GameResult(IPlayer player, int losses, int wins, int remis) {
			super();
			this.player = player;
			this.losses = losses;
			this.wins = wins;
			this.remis = remis;
		}
		
		
	}
	
	public IPlayer getPlayerOnTurn();

	public IPosition doTurn(ITurn turn);
	
	public boolean isGameOver();
	
	public GameResult[] gameResults();
}
