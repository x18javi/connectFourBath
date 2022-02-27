import java.util.ArrayList;

// board game class
public class boardGame {
    protected ArrayList<Player> playerList;
    protected Board board;

    // all board games have players, and a board
    public boardGame(int row, int column){
        this.board = new Board(row,column);
        this.playerList = new ArrayList<>();
    }

    // setter method, add a player
    public void addPlayer(Player p){
        this.playerList.add(p);
    }
}
