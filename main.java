import java.util.Scanner;
public class main {
    public static void playCard(int index, Hand hand,Board board){
        board.addToBoard(hand.getCard(index));
        hand.removeCard(index);
    }
    public static void main(String[] args){
        Deck deck = new Deck();
        Hand player = new Hand();
        Hand enemy = new Hand();
        Board board = new Board();
        Scanner sc = new Scanner(System.in);
        String input="";
        /*char test */
        //System.out.println("Symbol test: ♠, ♣,♥, ♦");
        System.out.println("Welcome to Pişti!");
        deck.create();
        deck.see();
        deck.shuffle();
        System.out.println();
        deck.see();
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

        boolean okInput = true;
        while(okInput) {
            try {
                input = sc.nextLine();
                playCard(Integer.parseInt(input) - 1, player, board);
                okInput = false;
            } catch (Exception e) {
                System.out.print("Entered a faulty input try again(1-4): ");
            }
        }
        board.seeBoard();
        System.out.println();
        player.see();
    }
}
