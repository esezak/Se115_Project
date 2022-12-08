public class main {
    public static void main(String[] args){
        /*char test */
        //System.out.println("Symbol test: ♠, ♣,♥, ♦");
        System.out.println("Welcome to Pişti!");
        Deck deck = new Deck();
        deck.create();
        deck.see();
        deck.shuffle();
        System.out.println();
        deck.see();
        deck.cut(26);
        System.out.println();
        deck.see();

    }
}
