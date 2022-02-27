import java.util.ArrayList;

public class ConnectFour {
    private ArrayList<Player> playerList;
    private Board board;

    public ConnectFour(){
        this.board = new Board(6,7);
        this.playerList = new ArrayList<>();
    }

    /*
    public static void main(String[] args){
        ConnectFour cf = new ConnectFour();
        cf.board.setElementInColumn(0,0,'r');
        cf.board.setElementInColumn(1,1,'r');
        cf.board.setElementInColumn(2,2,'r');
        cf.board.setElementInColumn(3,3,'r');
        System.out.print(cf.checkForWinner('r'));
        cf.board.setElementInColumn(5,0,'r');
        cf.board.setElementInColumn(4,1,'r');
        cf.board.setElementInColumn(3,2,'r');
        cf.board.setElementInColumn(2,3,'r');
        System.out.print(cf.checkForWinner('r'));
    }
     */

    public void addPlayer(Player p){
        this.playerList.add(p);
    }

    public void getInput(Player p){
        boolean successChecker = false;
        while(!successChecker){
            int input = p.getInput();
            successChecker=(
                    this.board.placeCounter(
                            input,
                            p.tokenColour)
            );
        }
    }

    public void playGame(){
        displayToCmd disp = new displayToCmd(this.board);
        boolean breakGame = false;

        disp.printBoard();
        while(!breakGame){

            for(Player p : this.playerList){

                this.getInput(p);
                disp.printBoard();
                if(checkForWinner(p.tokenColour)){
                    System.out.print("Player "+p.tokenColour+" wins!!\n");
                    breakGame = true;
                    break;
                }
            }
        }
    }

    private boolean checkForWinner(char player){
        boolean check = false;
        if(
            checkForHorizontalWinners(player) ||
            checkForVerticalWinners(player) ||
            //checkForWinners(player,"Vertical") ||
            //checkForWinners(player, "Horizontal") ||
            checkForDiagonalWinners(player, 1) ||
            checkForDiagonalWinners(player, -1)
        ){
            check = true;
        }
        return check;
    }

    private boolean checkForHorizontalWinners(char player){
        // check horizontal
        boolean hasWon = false;
        int count = 0;
        int columns = this.board.getBoardSize();
        int rows = this.board.getColumnSize();
        for(int row=0; row<=rows; row++){
            for(int col=0; col<=columns; col++){
                char currElem = this.board.getElementInColumn(row,col);
                if(currElem == player){
                    count = count + 1;
                    if(count >= 4){
                        hasWon = true;
                    }
                }
                else{
                    count = 0;
                }
            }
        }
        return hasWon;
    }

    private boolean checkForVerticalWinners(char player){
        boolean hasWon = false;
        int count = 0;
        int columns = this.board.getBoardSize();
        int rows = this.board.getColumnSize();
        for(int col=0; col<=columns; col++){
            for(int row=0; row<=rows; row++){
                char currElem = this.board.getElementInColumn(row,col);
                if(currElem == player){
                    count = count + 1;
                    if(count >= 4){
                        hasWon = true;
                    }
                }
                else{
                    count = 0;
                }
            }
        }
        return hasWon;
    }

    private int[] checkMappings(String direction, int columns, int rows){
        int[] orderOfOps = new int[2];
        if(direction=="Vertical"){
            orderOfOps[0] = columns;
            orderOfOps[1] = rows;
        } else if (direction == "Horizontal"){
            orderOfOps[0] = rows;
            orderOfOps[1] = columns;
        }

        return orderOfOps;
    }

    private boolean checkForWinners(char player, String direction){
        boolean hasWon = false;
        int count = 0;
        int columns = this.board.getBoardSize();
        int rows = this.board.getColumnSize();
        int[] Ops = checkMappings(direction, columns, rows);
        for(int col=0; col<=Ops[0]; col++){
            for(int row=0; row<=Ops[1]; row++){
                char currElem = this.board.getElementInColumn(row,col);
                if(currElem == player){
                    count = count + 1;
                    if(count >= 4){
                        hasWon = true;
                    }
                }
                else{
                    count = 0;
                }
            }
        }
        return hasWon;
    }

    private boolean checkForDiagonalWinners(char player, int direction){
        boolean hasWon = false;
        int count = 0;

        int columns = this.board.getBoardSize();
        int rows = this.board.getColumnSize();
        for(int col=0; col<=columns; col++){
            for(int row=0; row<=rows; row++){
                int counter = 0;
                ArrayList<Integer> checkVal = new ArrayList<>();
                while(counter<=4){
                    try {
                        char currElem = this.board.getElementInColumn
                                (
                                        row + (direction*counter),
                                        col + (direction*counter)
                                );

                        if(currElem == player) {
                            count = count + 1;
                            checkVal.add(row + (direction*counter));
                            checkVal.add(col + (direction*counter));
                            if (count >= 4) {
                                hasWon = true;
                            }
                        } else{
                            count = 0;
                        }
                    }catch(ArrayIndexOutOfBoundsException e){
                        count = 0;
                        break;
                    }
                    counter++;
                }
            }
        }
        return hasWon;
    }

}
