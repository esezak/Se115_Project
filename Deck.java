/*
Deck:
Spades[0]: 1,2,3,4,5,6,7,8,9,10, Jack(11), queen(12), king(13)♠
Hears[1]: 1,2,3,4,5,6,7,8,9,10, Jack(11), queen(12), king(13)♥
Diamonds[2]: 1,2,3,4,5,6,7,8,9,10, Jack(11), queen(12), king(13)♦
Clubs[3]: 1,2,3,4,5,6,7,8,9,10, Jack(11), queen(12), king(13)♣
52 cards
*/
import java.util.Random;
public class Deck {
    private Card[] deck = new Card[52];
    public void see(){
        for(int i=0; i<deck.length;i++){
            System.out.print(deck[i].getNumber()+deck[i].getSymbol()+" ,");//print i card number + symbol
        }
    }
    public void create(){
        for(int i = 0; i<deck.length;i++){
            Card temp = new Card();
            if(i<13){
                temp.setNumber(i+1);
                temp.setSymbol("♠");
                deck[i]=temp;
            }
            else if(i<26){
                temp.setNumber((i%13)+1);
                temp.setSymbol("♥");
                deck[i]=temp;
            }
            else if(i<39){
                temp.setNumber((i%13)+1);
                temp.setSymbol("♦");
                deck[i]=temp;
            }
            else{
                temp.setNumber((i%13)+1);
                temp.setSymbol("♣");
                deck[i]=temp;
            }
        }
    }
    public void shuffle() {//Fisher-Yates Algorithm
        Random r = new Random();
        int random;
        for(int i = deck.length-1; i>0;i--){
            Card temp = new Card();
            random = r.nextInt(i);
            temp = deck[random];
            deck[random]=deck[i];
            deck[i]=temp;
        }
    }//choose swap decrement
    public void cut(){}
}
