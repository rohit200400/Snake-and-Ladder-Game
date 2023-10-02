package entites;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Board {
    private final List<Integer> board;

    public Board(int totalLadders, int totalSnakes) {
        this.board = new ArrayList<>(101);
        for (int i = 0; i <= 100; i++) {
            this.board.add(0);
        }
        /*for a ladder min will be the current number and max will be 99.
         * A ladder and a snake should not overlap on the same cell*/
        Random random = new Random();
        // create ladders
        int ladderCount = 0;
        while (ladderCount != totalLadders) {
            int max = 96;
            int min = random.nextInt(3, 90);
            // Calculate the range.
            int range = max - min;
            // Generate a random number within the range and add the minimum value.
            int randomNumber = random.nextInt(range) + min;

            this.board.set(min, randomNumber);
            ladderCount++;
        }
        // create snakes
        int snakeCount = 0;
        while (snakeCount != totalLadders) {
            int max = random.nextInt(11, 99);
            int min = 2;
            // Calculate the range excluding the max value.
            int range = max - min;
            // Generate a random number within the range and add the minimum value.
            int randomNumber = max - random.nextInt(range);

            if (this.board.get(max) == 0) {
                this.board.set(max, randomNumber);
                snakeCount++;
            }
        }
    }

    public void printBoard() {
        for (int i = 1; i <= 9; i++) {
            if (this.board.get(i) != 0) {
                System.out.print(this.board.get(i) + " ");
            } else {
                System.out.print(" " + i + " ");
            }
        }
        for (int i = 10; i <= 100; i++) {
            if (this.board.get(i) != 0) {
                if (this.board.get(i) <= 9) {
                    System.out.print(" " + this.board.get(i) + " ");
                } else {
                    System.out.print(this.board.get(i) + " ");
                }
            } else {
                System.out.print(i + " ");
            }
            if (i % 10 == 0) System.out.println();
        }
    }

}
