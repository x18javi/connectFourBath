public class Main {
    public static void main(String[] args) {
        ConnectFour cFour = new ConnectFour();

        Player player1 = new Human('r');
        Player player2 = new Robot('y',7);

        cFour.addPlayer(player1);
        cFour.addPlayer(player2);

        cFour.playGame();
    }
}
