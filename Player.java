// use abstract class? We want it to guard against overflow regardless of player, only variable really is how user inputs (ie random or user?)
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public abstract class Player {
    char tokenColour;

    public Player(char colour){
        this.tokenColour = colour;
    }

    public void printPrompt(){
        System.out.print("Player "+this.tokenColour+"'s turn.\n");
    }

    public abstract int getInput();
    // type of player, if human, take input and guard, if robot, generate random input
}

// limit input to numbers only

class Human extends Player {

    public Human(char colour){
        super(colour);
    }

    public int getInput(){
        int inp = -1;
        while(inp == -1){
            inp = getUserInput();
        }
        return inp;
    }

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

class Robot extends Player {
    int length;

    public Robot(char colour, int length){
        super(colour);
        this.length = length;
    }

    public int getInput(){
        this.printPrompt();
        return (int)(Math.random()*this.length);
    }

}
