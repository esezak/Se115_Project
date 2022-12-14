import java.util.Scanner;
public class main {
    /*---------------------Static functions-------------------------*/
    public static void playCard(int index, Hand hand,Board board){
        board.addToBoard(hand.getCard(index));
        hand.removeCard(index);
    }
    /*---------------------Start of the game------------------------*/
    public static void main(String[] args){
        /*--------------Classes and variables------------------*/
        Deck deck = new Deck();
        Hand player = new Hand();
        Hand enemy = new Hand();
        Board board = new Board();
        Scanner sc = new Scanner(System.in);
        String input="";
        boolean okInput = true;
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
        /*------------------------------------------*/

        deck.cut(26);
        System.out.println();
        deck.see();
        player.fillHand(deck, enemy);
        System.out.println();
        player.see();
        System.out.println();
        enemy.see();
        System.out.println("\n"+deck.seeTopcard()+"\n");
        board.startBoard(deck);
        board.seeBoard();
        System.out.println("\n"+deck.seeTopcard()+"\n");
        deck.see();
        System.out.print("\nPlay a card: ");


        while(okInput) {
            try {
                input = sc.nextLine();
                playCard(Integer.parseInt(input) - 1, player, board);
                okInput = false;
            } catch (Exception e) {
                System.out.print(error+" (1-4): ");
            }
        }
        board.seeBoard();
        System.out.println();
        player.see();
    }
    /*----------------------End of the Game-----------------------------------*/

    /*-----------------------Ai Code Here-------------------------------*/


    /*-----------------------Ai Code end here---------------------------*/
}
