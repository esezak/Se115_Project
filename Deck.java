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
                temp.setPoint(1);
                deck[i]=temp;
            }
            else if(i<26){//second 13
                temp.setNumber((i%13)+1);
                temp.setSymbol("♥");
                temp.setPoint(1);
                deck[i]=temp;
            }
            else if(i<39){//third 13
                temp.setNumber((i%13)+1);
                temp.setSymbol("♦");
                temp.setPoint(1);
                deck[i]=temp;
            }
            else{//forth 13
                temp.setNumber((i%13)+1);
                temp.setSymbol("♣");
                temp.setPoint(1);
                deck[i]=temp;
            }
            Card temp2 = new Card();
            temp2.setNumber(10);
            temp2.setSymbol("♦");
            temp2.setPoint(3);
            deck[35]=temp2;
            Card temp3 = new Card();
            temp3.setNumber(2);
            temp3.setSymbol("♣");
            temp3.setPoint(2);
            deck[40]=temp3;
        }
        
        //deck[35].setPoint(3);
        //deck[40].setPoint(2);


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
    public void cut(int cutLocation){
        Card[] tempdeck = new Card[52];
        int j=0;    // cutlocation variable to store where we last are
        for(int i=cutLocation; i< deck.length;i++ ){
            tempdeck[j]=deck[i];j++;//take 52-cutlocation cards move them to front
        }
        for(int i=0;i<cutLocation;i++){
            tempdeck[j]=deck[i];j++;//insert the remaining cards to where we left off
        }
        System.arraycopy(tempdeck, 0, deck, 0, deck.length); //arraycopy
    }
    public void addToTopCard(int add){topcard = topcard + add;}
    public int getTopcard(){return topcard;}
    public Card getCard(int index){return deck[index];}
    public String seeTopcard(){return deck[topcard].getCard();}
}
