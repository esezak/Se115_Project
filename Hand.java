public class Hand {
    private Card[] hand = new Card[4];
    private int point;
    public void see(){
        for(int i=0; i< hand.length;i++){
            Card temp = new Card();
            //hand[i]= temp;
            temp = hand[i];
            System.out.print(temp.getCard()+",");
        }
    }
    public void setHand(int pos, Card newCard){
        hand[pos] = newCard;
    }
    public void fillHand(Deck deck, Hand dealer){
        for(int i=0; i< hand.length;i++){
            hand[i] =deck.getCard(i + deck.getTopcard());
            deck.addToTopCard(1);
            dealer.setHand(i, deck.getCard(i+deck.getTopcard()));
        }
        deck.addToTopCard(4);
    }
    public Card getCard(int index){
        return hand[index];
    }
    public void removeCard(int index){
        Card temp = new Card();
        temp.setNumber(0);
        temp.setSymbol("0");
        hand[index]=temp;
    }
    public void addToPoint(int point){this.point +=point;}
    public int getPoint(){return point;}
    public int selectedCardNum(int input){//for comparison with the card on deck
        return hand[input].getNumber();
    }
}
