/*
Deck:
Spades[0]: 1,2,3,4,5,6,7,8,9,10, Jack(11), queen(12), king(13)♠
Hears[1]: 1,2,3,4,5,6,7,8,9,10, Jack(11), queen(12), king(13)♥
Diamonds[2]: 1,2,3,4,5,6,7,8,9,10, Jack(11), queen(12), king(13)♦
Clubs[3]: 1,2,3,4,5,6,7,8,9,10, Jack(11), queen(12), king(13)♣
52 cards
*/

public class Deck {
    private Card[] deck = new Card[52];
    public void seeDeck(Card[] deck){
        for(int i=0; i<deck.length;i++){
            System.out.print(deck[i].getNumber()+deck[i].getSymbol()+" ,");//print i card number + symbol (untested)
        }
    }
    public void createDeck(){//untested
        for(int i = 0; i<deck.length;i++){
            if(i<13){
                deck[i].setNumber(i+1);
                deck[i].setSymbol("♠");
            }
            else if(i<26){
                deck[i].setNumber((i%13)+1);
                deck[i].setSymbol("♥");
            }
            else if(i<39){
                deck[i].setNumber((i%13)+1);
                deck[i].setSymbol("♦");
            }
            else{
                deck[i].setNumber((i%13)+1);
                deck[i].setSymbol("♣");
            }
        }
    }
}
