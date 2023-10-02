package entites;

import Exceptions.*;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class Game {
    private final Board board;
    private final List<Player> players;
    private final Dice dice;
    private Player currentPlayer;
    private GameStatus status;
    private Player winner;

    private Game(Board board, List<Player> players, Player currentPlayer, GameStatus status, Dice dice) {
        this.board = board;
        this.players = players;
        this.currentPlayer = currentPlayer;
        this.status = status;
        this.dice = dice;
    }

    public static Builder builder() {
        return new Builder();
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public Dice getDice() {
        return dice;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public GameStatus getStatus() {
        return status;
    }

    public void setStatus(GameStatus status) {
        this.status = status;
    }

    public Board getBoard() {
        return board;
    }

    public static class Builder {
        private int totalLadders;
        private int totalSnakes;
        private List<Player> players;
        private int sidesOfDice;

        public Builder totalLadders(int totalLadders) {
            this.totalLadders = totalLadders;
            return this;
        }

        public Builder totalSnakes(int totalSnakes) {
            this.totalSnakes = totalSnakes;
            return this;
        }

        public Builder players(List<Player> players) {
            Collections.shuffle(players);
            this.players = players;
            return this;
        }

        public Builder sidesOfDice(int sides) {
            this.sidesOfDice = sides;
            return this;
        }

        public void validateBotCount() throws InvalidBotCountException {
            int botCount = 0;
            for (Player player : players) {
                if (player.getPlayerType().equals(PlayerType.BOT)) {
                    botCount++;
                }
            }
            if (botCount > 3) {
                throw new InvalidBotCountException("Bot count cannot be more than 3, current count : " + botCount);
            }
        }

        public void validateSnakeCount() throws InvalidNumberOfSnakesException {
            if (totalSnakes < 3 || totalSnakes > 10) {
                throw new InvalidNumberOfSnakesException("Board size should be >=3 and <=10, current board size" + totalSnakes);
            }
        }

        public void validateLadderCount() throws InvalidNumberOfLadderException {
            if (totalLadders < 3 || totalLadders > 10) {
                throw new InvalidNumberOfLadderException("Board size should be >=3 and <=10, current board size" + totalLadders);
            }
        }

        public void validatePlayerNumber() throws InvalidNumberOfPlayersException {
            if (players.size() > 4) {
                throw new InvalidNumberOfPlayersException("Number of players is invalid, current count : " + players.size());
            }
        }

        public void validateDiceSides() throws InvalidDiceException {
            if (sidesOfDice < 2 || sidesOfDice > 8) {
                throw new InvalidDiceException("Number of sides of dice is invalid, current count : " + sidesOfDice);
            }
        }

        public void validateDuplicateName() throws DuplicatePlayerNameException {
            HashSet<String> symbolSet = new HashSet<>();
            for (Player player : players) {
                symbolSet.add(player.getName());
            }

            if (symbolSet.size() != players.size()) {
                throw new DuplicatePlayerNameException("All players should have unique name");
            }
        }

        public void validate() throws InvalidBotCountException, InvalidNumberOfPlayersException, InvalidDiceException,
                DuplicatePlayerNameException, InvalidNumberOfSnakesException, InvalidNumberOfLadderException {
            validateSnakeCount();
            validateLadderCount();
            validateBotCount();
            validatePlayerNumber();
            validateDuplicateName();
            validateDiceSides();
        }

        public Game build() throws InvalidBotCountException, InvalidNumberOfPlayersException, InvalidDiceException,
                DuplicatePlayerNameException, InvalidNumberOfSnakesException, InvalidNumberOfLadderException {
            validate();
            return new Game(new Board(totalLadders, totalSnakes), players, players.get(0),
                    GameStatus.IN_PROGRESS, new Dice(sidesOfDice));
        }
    }

}