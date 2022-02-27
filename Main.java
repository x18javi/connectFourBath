public class Main {
    public static void main(String[] args) {
        // instantiate game
        ConnectFour cFour = new ConnectFour();

        // create a human player (us)
        Player player1 = new Human('r');

        //create a robot player
        Player player2 = new Robot('y',cFour.getBoardLength());

        // add players to our game
        cFour.addPlayer(player1);
        cFour.addPlayer(player2);

        // play the game!
        cFour.playGame();
    }
}
