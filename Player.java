import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// abstract class player, cannot be instantiated. Set some default methods like printing the generic players token colour
public abstract class Player {
    char tokenColour;

    public Player(char colour){
        this.tokenColour = colour;
    }

    public void printPrompt(){
        System.out.print("Player "+this.tokenColour+"'s turn.\n");
    }

    // define abstract method get input that subclasses must implement
    public abstract int getInput();
}

// human player
class Human extends Player {

    public Human(char colour){
        super(colour);
    }

    // human input gets read from cmd, calls getUserInput which has further error handling. Loops until it can return a valid input
    public int getInput(){
        int inp = -1;
        while(inp == -1){
            inp = getUserInput();
        }
        return inp;
    }

    // getUserInput and protect against values which are not numbers
    public int getUserInput(){
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int userin = -1;
        String userinput;

        this.printPrompt();
        try{
            userinput = br.readLine();
            try{
                userin = Integer.parseInt(userinput)-1;
            } catch(NumberFormatException e){
                System.out.print("Error with value, try again.\n");
            }
        } catch(IOException e){
            System.out.print("Error, try again.\n");
        }
        return userin;
    }
}

// create robot player to play against
class Robot extends Player {
    int length;

    // robot requires an extra length parameter to be passed upon creation, this should be the size number of columns in the board
    public Robot(char colour, int length){
        super(colour);
        this.length = length;
    }

    // the length parameter limits the range of the random number generator, so the returned value is always between 1 and the number of columns
    public int getInput(){
        this.printPrompt();
        return (int)(Math.random()*this.length);
    }

}
