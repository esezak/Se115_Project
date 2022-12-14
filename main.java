import java.time.format.SignStyle;
import java.util.Scanner;
public class main {
    /*---------------------Static functions-------------------------*/
    public static void playCard(int index, Hand hand,Board board){
        board.addToBoard(hand.getCard(index));
        hand.removeCard(index);
    }
    /*--------------------------------------------------------------*/
    /*---------------------Start of the game------------------------*/
    public static void main(String[] args){

        /*--------------Classes and variables------------------*/

        Deck deck = new Deck();
        Hand player = new Hand();
        Hand enemy = new Hand();
        Board board = new Board();
        Scanner sc = new Scanner(System.in);
        String input;
        boolean okInput = true;// boolean to verify if a user input is verified
        int turnkeeper =0;//to keep track of which turn we are (to deal new cards)
        final String error ="Entered a faulty input try again ";

        /*-------------Classes and variables end---------------*/


        /*--------------Enviromental setup---------------------*/
        deck.create();
        deck.shuffle();
        /*------------------------------------------------------*/

        /*---------------------Game Start-----------------------*/

        System.out.println("Welcome to Pişti!");
        System.out.print("Select where you want to cut the deck: ");
        
        /*----------------Cut deck-----------------*/
        while(okInput) {
            try {
                input = sc.nextLine();
                deck.cut(Integer.parseInt(input));
                okInput = false;
            } catch (Exception e) {
                System.out.print(error + "(0-52): ");
            }
        }okInput = true;
        // 10 lines and 3 indents for executing a single function that require user input
        // I think I am starting to understand why people do not like Java
        /*------------------------------------------*/

        player.fillHand(deck, enemy);// deal the cards
        board.startBoard(deck);
        System.out.println();System.out.println();

        while(deck.getTopcard()<52){//loop until cards are finished

            System.out.print("Board: ");
            board.seeBoard();
            System.out.println("\nChoose a card to play 1 to 4:");
            player.see();System.out.println();

            while(okInput) {//place card on board
                try {
                    input = sc.nextLine();
                    //check if selected place at hand is empty
                    if(!player.getCard(Integer.parseInt(input)-1).getCard().equals("00")) {
                        playCard(Integer.parseInt(input) - 1, player, board);
                        okInput = false;
                    }
                    else{
                        System.out.print("This spot is empty try again: ");
                    }
                } catch (Exception e) {
                    System.out.print(error+" (1-4): ");
                }
            }okInput = true;

            /*------------------TO-DO------------------------*/
            //check if the player takes the cards or is there a pişti

            //computer plays a card

            //check if the copmuter takes the cars or is there a pişti

        }

    //calculate points

    //Check who won

    //Check the scores on the top 10 list if the player managed to get in the list

    //Print the list to player to see

    //Game finish



    }
    /*----------------------End of the Game-----------------------------------*/

    /*-----------------------Ai Code Here-------------------------------*/
/*
Basic functions:
1- if top card on board in hand play card
2- if not play a random valid card in deck

 */

    /*-----------------------Ai Code end here---------------------------*/
}
