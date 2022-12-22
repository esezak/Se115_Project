public class Board {
    private Card[] board = new Card[52];
    private int topindex=0;
    private int point;
    public void startBoard(Deck deck){
        Card empty = new Card();
        point =0;
        for(int i=0;i<4;i++) {
            Card temp = new Card();
            temp = deck.getCard(deck.getTopcard());
            board[i]=temp;
            point += temp.getPoint();
            deck.addToTopCard(1);
        }
        empty.setSymbol("0");
        empty.setNumber(0);
        empty.setPoint(0);
        for(int i=4;i< board.length;i++){
            board[i]=empty;
        }
        //deck.addToTopCard(-1);
        topindex=3;
    }
    public void seeBoard(){
        for(int i=0;i< board.length;i++){
            if(!board[i].getCard().equals("00")) {
                System.out.print(board[i].getCard() + " ");
            }
        }
    }
    public void addToBoard(Card newCard){
        topindex++;
        board[topindex]=newCard;
        point += newCard.getPoint();
    }
    public int countCard(){// basicaly seeBoard but it gives the number of cards
        int counter =0;
        for(int i=0;i< board.length;i++){
            if(!board[i].getCard().equals("00")) {
                counter++;
            }
        }
        return counter;
    }


    public void flushBoard(){
        Card empty = new Card();
        empty.setSymbol("0");
        empty.setNumber(0);
        for(int i=0; i< board.length;i++){
            board[i]=empty;
        }
        topindex =0;
        point =0;
    }
    //topindex+1 is the number of cards on the board
    public int getTopindex(){return topindex+1;}
    public int getTopCardNum(){ return board[topindex].getNumber();}
    public int getPoint(){return point;}
}
