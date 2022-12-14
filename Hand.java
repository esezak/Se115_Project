import java.util.Scanner;
import java.util.InputMismatchException;
public class Hand {
    private Card[] hand = new Card[4];
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
    public void removeCard(int index){
        Card temp = new Card();
        temp.setNumber(0);
        temp.setSymbol("0");
        hand[index]=temp;
    }
}
