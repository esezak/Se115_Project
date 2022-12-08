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
    private Card[] deck = new Card[52];//a deck of cards
    private int topcard;
    public void see(){
        for(int i=0; i<deck.length;i++){
            System.out.print(deck[i].getCard()+", ");//see what is inside the deck
        }
    }//see what is inside the deck (for debug)

    public void create(){//create an unshuffled deck
        topcard =0;
        for(int i = 0; i<deck.length;i++){
            Card temp = new Card(); // temp object for assigning to Card[]
            if(i<13){//first 13
                temp.setNumber(i+1);
                temp.setSymbol("♠");
                deck[i]=temp;
            }
            else if(i<26){//second 13
                temp.setNumber((i%13)+1);
                temp.setSymbol("♥");
                deck[i]=temp;
            }
            else if(i<39){//third 13
                temp.setNumber((i%13)+1);
                temp.setSymbol("♦");
                deck[i]=temp;
            }
            else{//forth 13
                temp.setNumber((i%13)+1);
                temp.setSymbol("♣");
                deck[i]=temp;
            }
        }
    }//create an unshuffled deck

    public void shuffle() {//Fisher-Yates Algorithm (New version)
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
    public void cut(int a){
        Card[] tempdeck = new Card[52];
        int j=0;    // a variable to store where we last are
        for(int i=a; i< deck.length;i++ ){
            tempdeck[j]=deck[i];j++;//take 52-a cards move them to front
        }
        for(int i=0;i<a;i++){
            tempdeck[j]=deck[i];j++;//insert the remaining cards to where we left off
        }
        System.arraycopy(tempdeck, 0, deck, 0, deck.length); //arraycopy
    }
    public void addToTopCard(int a){
        topcard += a;
    }
    public int getTopcard(){return topcard;}
    public Card getCard(int a){return deck[a];}
}
