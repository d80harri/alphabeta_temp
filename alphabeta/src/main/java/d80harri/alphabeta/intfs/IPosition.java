package d80harri.alphabeta.intfs;

public interface IPosition {
	public IPlayer getPlayerOnTurn();

	public IPosition doTurn(ITurn turn);
}
