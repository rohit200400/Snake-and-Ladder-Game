import entites.Game;
import entites.GameStatus;
import entites.Player;
import entites.PlayerType;
import Controller.*;
import java.util.ArrayList;
import java.util.List;

public class SnakeAndLadderGame {
    public static void main(String[] args) {
        // Create players
        List<Player> players = new ArrayList<>();
        players.add(new Player("Player 1", PlayerType.HUMAN));
        players.add(new Player("Player 2", PlayerType.HUMAN));

        // Create a GameController
        GameController gameController = new GameController();
        // Create a game with 10 ladders, 5 snakes, 6 sides on the dice, and the list of players
        Game game = gameController.createGame(10, 5, players, 6);

        // Start the game loop
        while (game.getStatus() == GameStatus.IN_PROGRESS) {

            // Play the next move for the current player
            gameController.playNextMove(game);

            // Display the current game board
            gameController.displayBoard(game);
        }

        // Determine the winner and display the result
        Player winner = gameController.getGameWinner(game);
        System.out.println("The winner is: " + winner.getName());
    }
}
