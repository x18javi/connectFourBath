public class displayToCmd {
    Board board;

    public displayToCmd(Board board){
        this.board = board;
    }

    public void getRowCount(int rowCount){
        StringBuilder xAxis = new StringBuilder();
        for(int i=0; i<= rowCount; i++){
            xAxis.append("  ").append(i + 1).append(" ");
        }
        System.out.print(xAxis);
    }

    public void printBoard(){
        int rowCount = this.board.getBoardSize();
        int columnCount = this.board.getColumnSize();

        for(int i=0; i<=columnCount; i++) { //x
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
