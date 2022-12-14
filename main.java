import java.util.Random;
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
        int playerNum;  // last cards number selected by player
        int enemyNum=0;   // last cards number selected by enemy
        int enemyIndex;   //selected index of card (basically enemy input)
        boolean okInput = true;// boolean to verify if a user input is verified
        int turnkeeper =0;//to keep track of which turn we are (to deal new cards)
        final String error ="Entered a faulty input try again ";

        /*-------------Classes and variables end---------------*/


        /*--------------Environmental setup---------------------*/
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


            /*--------------------------------Player turn-----------------------------*/
            while(okInput) {//place card on board
                try {
                    input = sc.nextLine();
                    //check if selected place at hand is empty
                    if(!player.getCard(Integer.parseInt(input)-1).getCard().equals("00")) {
                        playerNum = player.selectedCardNum(Integer.parseInt(input)-1);
                        if(board.getTopCardNum()==playerNum || playerNum==11){    //check for equal number or jack
                            player.addToPoint(board.getTopindex()+1);            //add to player score
                            playCard(Integer.parseInt(input) - 1, player, board); // place card
                            board.flushBoard();                                  //reset board
                        }else{
                            playCard(Integer.parseInt(input) - 1, player, board); //if different place card
                        }
                        okInput = false;
                    }
                    else{
                        System.out.print("This spot is empty try again: ");
                    }
                } catch (Exception e) {
                    System.out.print(error+" (1-4): ");
                }
            }okInput = true;//  End of player input
            System.out.println("Player point: "+player.getPoint());
            /**/
            /*-------------------Enemy Turn----------------------------*/
            enemyIndex = aiPlay(board,enemy);
            enemyNum= enemy.selectedCardNum(enemyIndex);

            //Note to self: write if the enemy takes the cards




            /*------------------TO-DO------------------------*/
            //check if the player takes the cards or is there a pişti

            //computer plays a card

            //check if the computer takes the cars or is there a pişti
            turnkeeper++;
            if(turnkeeper==4){
                turnkeeper=0;
                player.fillHand(deck, enemy);
            }
        }/*--------------------turn loop end-----------------------*/

    //calculate points

    //Check who won

    //Check the scores on the top 10 list if the player managed to get in the list

    //Print the list to player to see

    //Game finish



    }/*----------------------End of the Game-----------------------------------*/

    /*-----------------------Ai Code Here-------------------------------*/
/*
Basic functions:
1- if top card on board in hand play card
2- if jack at hand play jack
3- if not play a random valid card in deck
Edge case: if board empty and have only jack then play jack
Returns the index value of the card
 */
    public static int aiPlay(Board board, Hand hand){ // returns the index of selection
        Random r = new Random(); boolean hasjack = false; int jacki=0;
        for(int i=0;i<4;i++){
            if(hand.selectedCardNum(i)==board.getTopCardNum()){ // if top card is at hand play it
                return hand.selectedCardNum(i);
            }if(hand.selectedCardNum(i)==11 && board.getTopCardNum()!=0){ //if board empty do not play jack
                hasjack = true;
                jacki = i;
            }
        }
        if(hasjack){
            return jacki;
        }
        else{
            while(true){
                int random = r.nextInt(4);
                if(!hand.getCard(random).getCard().equals("00")){
                    return random;
                }
            }
        }
    }
    /*-----------------------Ai Code end here---------------------------*/
}
