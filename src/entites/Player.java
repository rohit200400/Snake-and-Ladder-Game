package entites;

public class Player {
    private String name;
    private Integer currentPosition;
    private PlayerType playerType;

    public Player(String name, PlayerType playerType) {
        this.name = name;
        this.currentPosition = 0;
        this.playerType = playerType;
    }

    public Integer getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(Integer currentPosition) {
        this.currentPosition = currentPosition;
    }

    public String getName() {
        return name;
    }

    public PlayerType getPlayerType() {
        return playerType;
    }
}
