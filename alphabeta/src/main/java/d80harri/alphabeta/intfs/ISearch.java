package d80harri.alphabeta.intfs;

public interface ISearch {
	public class SearchResult{
		private ITurn turn;
		private IPosition position;
		private double fitness;
		public SearchResult(ITurn turn, IPosition position, double fitness) {
			super();
			this.setTurn(turn);
			this.setPosition(position);
			this.setFitness(fitness);
		}
		public void setFitness(double fitness) {
			this.fitness = fitness;
		}
		public double getFitness() {
			return fitness;
		}
		public void setTurn(ITurn turn) {
			this.turn = turn;
		}
		public ITurn getTurn() {
			return turn;
		}
		public void setPosition(IPosition position) {
			this.position = position;
		}
		public IPosition getPosition() {
			return position;
		}
	}
	
	public SearchResult doSearch(IPosition position);
}
