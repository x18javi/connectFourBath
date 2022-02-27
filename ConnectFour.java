import java.util.ArrayList;

// logic specific to the game connect four
public class ConnectFour extends boardGame{

    // instantiate the class with the known board size of 6x7
    public ConnectFour(){
        super(6,7);
    }

    // helper function to return the number of columns in the board
    public int getBoardLength(){
        return this.board.getBoardSize()+1;
    }

    // validate player input, takes a Player class as the parameter
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

    // play the game. Loop through the players who are stored in the playerlist, getting the players input and checking for a winner each time
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

    // consolidate the different checks that are run in connect for, vertical, horizontal, diagonal from left-to-right and right-to-left
    private boolean checkForWinner(char player){
        boolean check = false;
        if(
            checkForHorizontalWinners(player) ||
            checkForVerticalWinners(player) ||
            checkForDiagonalWinners(player, 1) ||
            checkForDiagonalWinners(player, -1)
        ){
            check = true;
        }
        return check;
    }

    // logic to check for a horizontal winner on the board
    private boolean checkForHorizontalWinners(char player){
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

    // logic to check for a vertical winner on the board
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

    /* check for diagonal winners, the direction parameter is either -1 or 1, and dictates whether the check
    * is going left to right, or right to left. It is not guarded against (ie someone attempting to use value 2)
    * because the function has no public exposure
    * */
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
