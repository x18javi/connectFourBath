// display a board, its only requirement is it takes a board object only

public class displayToCmd {
    Board board;

    // constructor
    public displayToCmd(Board board){
        this.board = board;
    }

    // format and print the x axis
    private void getRowCount(int rowCount){
        StringBuilder xAxis = new StringBuilder();
        for(int i=0; i<= rowCount; i++){
            xAxis.append("  ").append(i + 1).append(" ");
        }
        System.out.print(xAxis);
    }

    // print the board, it can be any size
    public void printBoard(){
        int rowCount = this.board.getBoardSize();
        int columnCount = this.board.getColumnSize();

        for(int i=0; i<=columnCount; i++) {
            for (int j = 0; j <= rowCount; j++) {
                char elem = this.board.getElementInColumn(i, j);
                if (elem != 0) {
                    System.out.print("| "+elem+" ");
                } else {
                    System.out.print("|   ");
                }
            }
            System.out.println("|");
        }
        this.getRowCount(rowCount);
        System.out.print("\n");
    }
}
