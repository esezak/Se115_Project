import java.util.Random;
import java.util.Scanner;
public class main {
    /*---------------------Static functions-------------------------*/
    public static void playCard(int index, Hand hand,Board board){
        board.addToBoard(hand.getCard(index));
        hand.removeCard(index);
    }
    public static boolean isPisti(Board board){
        if(board.countCard()==1){
            return true;
        }
        return false;
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
        String input="";
        int playerNum=0;  // last cards number selected by player
        int pcardnum=0;     // variables to store the number of cards
        int ecardnum=0;
        int enemyNum=0;   // last cards number selected by enemy
        int enemyIndex;   //selected index of card (basically enemy input)
        boolean okInput = true;// boolean to verify if a user input is verified
        int turnkeeper =0;//to keep track of which turn we are (to deal new cards)
        final String error ="Entered a faulty input try again ";
        boolean lastpickup=true;

        /*-------------Classes and variables end---------------*/


        /*--------------Environmental setup---------------------*/
        deck.create();
        deck.see();
        System.out.println(deck.getCard(35).getPoint());//10♦
        System.out.println(deck.getCard(40).getPoint());//2♣
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
                        okInput = false;
                    }
                    else{
                        System.out.print("This spot is empty try again: ");
                    }
                } catch (Exception e) {
                    System.out.print(error+" (1-4): ");
                }
            }okInput = true;//  End of player input

            //----------------------------Check if player takes cards--------------------------

            if(board.getTopCardNum()==playerNum || playerNum==11){    //check for equal number or jack
                if(isPisti(board)&&board.getTopCardNum()==11&&playerNum==11){
                    System.out.println("Pişti x2!");
                    playCard(Integer.parseInt(input) - 1, player, board);
                    player.addToPoint(20);
                    pcardnum +=2;
                }else if(isPisti(board)&&board.getTopCardNum()==playerNum){
                    System.out.println("Pişti!");
                    playCard(Integer.parseInt(input) - 1, player, board);
                    player.addToPoint(10);
                    pcardnum +=2;
                }else{
                    playCard(Integer.parseInt(input) - 1, player, board); // place card
                    player.addToPoint(board.getTopindex());            //add to player score
                    pcardnum += board.countCard();
                    board.flushBoard();
                }
                lastpickup =true;                                  //reset board
            }else{
                playCard(Integer.parseInt(input) - 1, player, board); //if different place card
            }

      
            
            /*-------------------Enemy Turn----------------------------*/
            //-----------------Debug----------------------
            System.out.print("\nEnemy Hand: ");
            enemy.see();System.out.println();
            enemyIndex = aiPlay(board,enemy);
            System.out.println("Enemy index  : "+ enemyIndex);
            System.out.println("Enemy played: "+enemy.getCard(enemyIndex).getCard());
            //----------------/Debug end----------------

            enemyNum= enemy.selectedCardNum(enemyIndex);
            if(enemyNum==board.getTopCardNum()||enemyNum==11){
                if(isPisti(board)&&board.getTopCardNum()==11&&enemyNum==11){
                    playCard(enemyIndex, enemy, board);
                    enemy.addToPoint(20);
                    ecardnum +=2;
                }else if(isPisti(board)&&board.getTopCardNum()==enemyNum){
                    playCard(enemyIndex, enemy, board);
                    enemy.addToPoint(10);
                    ecardnum +=2;
                }else{
                    playCard(enemyIndex, enemy, board); // place card
                    enemy.addToPoint(board.getTopindex());            //add to player score
                    ecardnum += board.countCard();
                    board.flushBoard();
                }
                lastpickup = false;
            }else{
                playCard(enemyIndex, enemy, board);
            }

            turnkeeper++;
            if(turnkeeper==4){
                turnkeeper=0;
                player.fillHand(deck, enemy);
            }   


            System.out.println("Player point: "+player.getPoint());
            System.out.println("Enemy point: "+enemy.getPoint());
            System.out.println();


        }/*--------------------turn loop end-----------------------*/

        sc.close();
        //remaining cards will go to:
        if(lastpickup){
            player.addToPoint(board.getTopindex());
        }else{
            enemy.addToPoint(board.getTopindex());
        }
        /*-----------------Most Card reward------------------------*/
        if(pcardnum>ecardnum){player.addToPoint(3);}
        else if(ecardnum>pcardnum){enemy.addToPoint(3);}
        /*-----------------Final Scores----------------------------*/
        System.out.println("-Final Scores-");
        System.out.println("You scored: "+player.getPoint());
        System.out.println("With "+pcardnum+" cards");
        System.out.println("Enemy scored: "+ enemy.getPoint());
        System.out.println("With "+ecardnum+" cards");
        if(player.getPoint()>enemy.getPoint()){
            System.out.println("You Win!");
        }else{
            System.out.println("You lost!");
        }





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
            if(hand.selectedCardNum(i)==board.getTopCardNum()&& hand.selectedCardNum(i)!=0){ // if top card is at hand play it
                return i;
            }if(hand.selectedCardNum(i)==11 && board.getTopCardNum()!=0){ //if board empty do not play jack
                System.out.println("It's here");
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
