import aima.core.environment.tictactoe.TicTacToeGame;
import aima.core.environment.tictactoe.TicTacToeState;
import aima.core.search.adversarial.AdversarialSearch;
import aima.core.search.adversarial.MinimaxSearch;
import aima.core.util.datastructure.XYLocation;

public class Minimax {
	public static void main(String[] args){
		System.out.println("MINI MAX DEMO\n");
		TicTacToeGame game = new TicTacToeGame();
		TicTacToeState currState = game.getInitialState();
		AdversarialSearch<TicTacToeState, XYLocation> search = MinimaxSearch
				.createFor(game);
		while (!(game.isTerminal(currState))) {
			System.out.println(game.getPlayer(currState) + "  playing ... ");
			XYLocation action = search.makeDecision(currState);
			currState = game.getResult(currState, action);
			System.out.println(currState);
		}
	}
}
