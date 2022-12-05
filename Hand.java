public class Hand {
    private Card[] hand = new Card[4];
    public void see(){
        for(int i=0; i< hand.length;i++){
            System.out.print(hand[i]+",");
        }
    }
}
