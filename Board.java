public class Board {
    private char[][] board;

    // constructor, initialise the board at a size specified by the user
    public Board(int row, int column) {
        this.board = new char[row][column];
    }

    // public helper to get the size of a column (ie vertical on board)
    public int getColumnSize(){
        return this.board.length-1;
    }

    // helper to get the board size (ie horizontal length)
    public int getBoardSize(){
        return this.board[0].length-1;
    }

    /*
    public facing placeCounter method, it has all the guarding in place to prevent the private
    placeCounterInBoard method that modifies the table, and returns a boolean if the
    placeCounterInBoard function is successful
     */
    public boolean placeCounter(int column, char player){
        boolean successBool = false;
        if(guardBoardLength(column) && guardColumnOverflow(column)){
            placeCounterInBoard(column, player);
            successBool = true;
        } else {
            System.out.print("You cannot add a counter there, try again.\n");
        }
        return successBool;
    }

    // public get element in board method
    public char getElementInColumn(int row, int column){
        return this.board[row][column];
    }

    // private setter method
    private void setElementInColumn(int row, int column, char val){
        this.board[row][column] = val;
    }

    // function to iterate over the board and actually modify the board by adding the token
    private void placeCounterInBoard(int column, char player){
        int columnSize = getColumnSize();
        for(int i = columnSize; i>=0; i--){
            char currentValue = getElementInColumn(i, column);
            if (currentValue == 0){
                setElementInColumn(i, column, player);
                break;
            } else {
                continue;
            }
        }
    }

    // return true if the column is within the boards range
    private boolean guardBoardLength(int column){
        return column <= getBoardSize();
    }

    // return true if there is space in the current column (array)
    private boolean guardColumnOverflow(int column){
        int rowCount = getColumnSize();
        int countOfUsedElements = 0;
        for(int i = rowCount; i>=0; i--){
            char currElem = getElementInColumn(i, column);
            if(currElem != 0){
                countOfUsedElements++;
            }
        }
        return countOfUsedElements != rowCount+1;
    }
}

