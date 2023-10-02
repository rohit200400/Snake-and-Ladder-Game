package Controller;

import entites.Dice;
import entites.Game;
import entites.GameStatus;
import entites.Player;

import java.util.List;
import java.util.Scanner;

public class GameController {
    private static final int WINNING_POSITION = 100;

    public Game createGame(int totalLadders, int totalSnakes, List<Player> players, int sidesOfDice) {
        try {
            return Game.builder()
                    .sidesOfDice(sidesOfDice)
                    .players(players)
                    .totalLadders(totalLadders)
                    .totalSnakes(totalSnakes).build();
        } catch (Exception e) {
            throw new RuntimeException("Could not start the game: " + e.getMessage(), e);
        }
    }

    public void displayBoard(Game game) {
        game.getBoard().printBoard();
    }

    public GameStatus getGameStatus(Game game) {
        return game.getStatus();
    }

    public Player getGameWinner(Game game) {
        return game.getWinner();
    }

    public void playNextMove(Game game) {
        Player currentPlayer = game.getCurrentPlayer();
        System.out.println("It's " + currentPlayer.getName() + "'s turn to play.");
        System.out.println("Press Enter to roll the dice.");
        Scanner sc = new Scanner(System.in);
        //String inp = sc.nextLine();
        int diceCount = game.getDice().roll();
        System.out.println(currentPlayer.getName() + " got " + diceCount + ".");

        int newPosition = currentPlayer.getCurrentPosition() + diceCount;
        currentPlayer.setCurrentPosition(newPosition);
        System.out.println("Your current position is: " + newPosition);

        checkForWinner(game, currentPlayer);

        moveToNextPlayer(game);
    }

    private int rollDice(Dice dice) {
        return dice.roll();
    }

    private void checkForWinner(Game game, Player currentPlayer) {
        if (currentPlayer.getCurrentPosition() == WINNING_POSITION) {
            game.setStatus(GameStatus.FINISHED);
            game.setWinner(currentPlayer);
        }
    }

    private void moveToNextPlayer(Game game) {
        List<Player> players = game.getPlayers();
        int currentPlayerIndex = players.indexOf(game.getCurrentPlayer());
        int nextPlayerIndex = (currentPlayerIndex + 1) % players.size();
        game.setCurrentPlayer(players.get(nextPlayerIndex));
    }
}
