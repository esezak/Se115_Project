import java.util.Random;
import java.util.Scanner;
import java.nio.file.Paths;
import java.util.Formatter;
import java.io.FileWriter;
public class main {
    /*---------------------Static functions-------------------------*/
    public static void playCard(int index, Hand hand,Board board){// function for playing cards
        board.addToBoard(hand.getCard(index));
        hand.removeCard(index);
    }
    public static boolean isPisti(Board board){//
        return board.countCard() == 1;
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
        int enemyNum;   // last cards number selected by enemy
        int pcardnum=0;     // variable to store the number of cards (player)
        int ecardnum=0;     // variable to store the number of cards (enemy)
        int enemyIndex;   //selected index of card (basically enemy input)
        boolean okInput = true;// boolean to verify if a user input is verified
        int turnkeeper =0;//to keep track of which turn we are (to deal new cards)
        final String error ="Entered a faulty input try again ";
        boolean lastpickup=true;

        /*-------------Classes and variables end---------------*/


        /*--------------Environmental setup---------------------*/
        deck.create();
        //deck.see();
        deck.shuffle();
        //deck.see();
        /*------------------------------------------------------*/

        /*---------------------Game Start-----------------------*/

        System.out.println("Welcome to Pişti!");
        System.out.print("Select where you want to cut the deck: ");
        
        /*----------------Cut deck-----------------*/
        while(okInput) {
            try {
                sc = new Scanner(System.in);
                input = sc.nextLine();
                deck.cut(Integer.parseInt(input));
                okInput = false;
            } catch (Exception e) {
                System.out.print(error + "(0-52): ");
            }
        }okInput = true;
        System.out.println();
        //deck.see();
        /*------------------------------------------*/
        
        System.out.println("Dealing Cards");
        player.fillHand(deck, enemy);               // deal the cards
        board.startBoard(deck);                     // board init
        System.out.println();System.out.println();
        for(int i=0; i< 24;i++){          //loop until cards are finished (52-4)/2 number of turns

            /*-----------------Debug----------------------
            System.out.println("----------------DEBUG----------------");
            System.out.print("Enemy Hand: ");enemy.see();System.out.println();
            System.out.println("Board point: "+board.getPoint());
            System.out.println("Board card count: "+board.countCard());
            System.out.println("Board top num: "+board.getTopCardNum());
            System.out.println("Board topindex: "+ board.getTopindex());
            System.out.println("--------------------------------");
            //----------------/Debug end----------------*/

            System.out.print("Board: ");
            board.seeBoard();
            System.out.println("\nChoose a card to play 1 to 4:");
            player.see();//System.out.println();


            /*--------------------------------Player turn-----------------------------*/
            while(okInput) {//place card on board
                try {
                    sc = new Scanner(System.in);
                    input = sc.nextLine();
                    //check if selected place at hand is empty
                    if(!player.getCard(Integer.parseInt(input)-1).getCard().equals("00")) {
                        playerNum = player.selectedCardNum(Integer.parseInt(input)-1); // validation of user input
                        okInput = false;
                    }
                    else{
                        System.out.print("\nThis spot is empty try again: ");
                    }
                } catch (Exception e) {
                    System.out.print("\n"+error+" (1-4): ");
                }
            }okInput = true;    //  End of player input

            //----------------------------Check if player takes cards--------------------------

            if((board.getTopCardNum()==playerNum || playerNum==11)&&board.getTopindex()!=1){    //check for equal number or jack
                if(isPisti(board)&&board.getTopCardNum()==11&&playerNum==11){   // check for pisti w jack
                    System.out.println("Pişti x2!");
                    playCard(Integer.parseInt(input) - 1, player, board);
                    player.addToPoint(20);
                    pcardnum +=2;
                    board.flushBoard();
                }else if(isPisti(board)&&board.getTopCardNum()==playerNum){     //check for pisti
                    System.out.println("Pişti!");
                    playCard(Integer.parseInt(input) - 1, player, board);
                    player.addToPoint(10);
                    pcardnum +=2;
                    board.flushBoard();
                }else{
                    playCard(Integer.parseInt(input) - 1, player, board); // place card
                    player.addToPoint(board.getPoint());              //add to player score
                    pcardnum += board.countCard();
                    board.flushBoard();
                }
                lastpickup =true;                                  //reset board
            }else{
                playCard(Integer.parseInt(input) - 1, player, board); //if different place card
            }

      
            
            /*-------------------Enemy Turn----------------------------*/

            enemyIndex = aiPlay(board,enemy);   // gets the index of played card
            enemyNum= enemy.selectedCardNum(enemyIndex); //number of selected card
            String played = enemy.getCard(enemyIndex).getCard(); //card played as string Ex: 9♣
            if((enemyNum==board.getTopCardNum()||enemyNum==11)&&board.getTopindex()!=1){      //check for equal or jack
                if(isPisti(board)&&board.getTopCardNum()==11&&enemyNum==11){ // pisti w double jack
                    playCard(enemyIndex, enemy, board);
                    enemy.addToPoint(20);
                    board.flushBoard();
                    ecardnum +=2;
                }else if(isPisti(board)&&board.getTopCardNum()==enemyNum){ // check pisti 
                    playCard(enemyIndex, enemy, board);
                    board.flushBoard();
                    enemy.addToPoint(10);
                    ecardnum +=2;
                }else{
                    playCard(enemyIndex, enemy, board);                 // place card
                    enemy.addToPoint(board.getPoint());            //add to player score
                    ecardnum += board.countCard();
                    board.flushBoard();
                }
                lastpickup = false;
            }else{
                playCard(enemyIndex, enemy, board);
            }
            //System.out.println("Enemy index  : "+ enemyIndex);


            turnkeeper++;
            if(turnkeeper==4&&deck.getTopcard()!=52){
                turnkeeper=0;
                player.fillHand(deck, enemy);
            }
            System.out.println();
            System.out.println("Enemy played: "+played);
            System.out.println("Player point: "+player.getPoint());
            //System.out.println("Player Card Num: "+pcardnum);
            System.out.println("Enemy point: "+enemy.getPoint());
            //System.out.println("Enemy Card Num: "+ecardnum);
            System.out.println();


        }   /*--------------------turn loop end-----------------------*/

        //remaining cards will go to:
        if(lastpickup){
            player.addToPoint(board.getPoint());
            pcardnum += board.countCard();
        }else{
            enemy.addToPoint(board.getPoint());
            ecardnum += board.countCard();
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

        Scanner reader = null;
        Formatter formatter = null;
        FileWriter writer = null;
        String[] names = new String[11];
        String[] scores = new String[11];

        //-------------get name------------------
        try {
            sc = new Scanner(System.in);
            System.out.print("Enter name: ");
            input = sc.nextLine();
        } catch (Exception e) {
            System.out.println("Error");
        } finally {
            if (sc != null) {
                sc.close();
            }
        }
        //---------------------read file / save file-----------------
        try {
            reader = new Scanner(Paths.get("scores.txt"));
            int counter = 0;
            while (reader.hasNextLine()) {
                String[] info = reader.nextLine().split(",");
                names[counter] = info[0].trim();
                scores[counter] = info[1].trim();
                counter++;
            }
        } catch (Exception e) {
            System.out.println("It seems this is the first time playing.");
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
        String point = String.valueOf(player.getPoint());

        //------------------compare values and relist------------------
        for (int i = 0; i < names.length; i++) {
            if (scores[i] != null) {
                if (Integer.parseInt(scores[i]) < player.getPoint()) {
                    for (int j = names.length - 1; j > i; j--) {//make place for new value
                        names[j] = names[j - 1];
                        scores[j] = scores[j - 1];
                    }
                    names[i] = input;// add values
                    scores[i] = point;
                    break;
                }
            } else if (scores[i] == null) {// if empty add the value
                scores[i] = point;
                names[i] = input;
                break;
            }
        }

        //------------------write file -------------
        try {
            writer = new FileWriter("scores.txt", false);
            for(int i=0; i< 10;i++){
                formatter = new Formatter(writer);
                if(scores[i]!=null) {
                    formatter.format("%s,%s\n", names[i], scores[i]);
                }
            }
        } catch (Exception e) {
            System.out.println("Something went wrong.");
        }finally{
            if(formatter!=null)
                formatter.close();
        }

        //-------------Display Score list------------------
        System.out.println();
        System.out.println("----------Hi Scores----------");
        for (int i = 0; i < names.length-1; i++) {
            if (names[i] == null) {
                System.out.println((i+1)+"# Name:        Score:      ");
            } else {
                System.out.println((i+1)+"# Name: " + names[i] + "   Score: " + scores[i]);
            }
        }

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
