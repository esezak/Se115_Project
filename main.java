public class main {
    public static void main(String[] args){
        /*char test */
        //System.out.println("Symbol test: ♠, ♣,♥, ♦");
        System.out.println("Welcome to Pişti!");
        Deck deck = new Deck();
        Hand player = new Hand();
        Hand enemy = new Hand();
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
        System.out.println("\n"+deck.seeTopcard());

    }
}
