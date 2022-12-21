public class Board {
    private Card[] board = new Card[55];
    // 55 card "number" actually means points
    //since few cards have extra points they are added with "dead space" on board (pun not intended)
    private int topindex=0;
    public void startBoard(Deck deck){
        Card empty = new Card();
        for(int i=0;i<5;i++) {
            Card temp = new Card();
            temp = deck.getCard(deck.getTopcard());
            board[i]=temp;
            deck.addToTopCard(1);
        }
        empty.setSymbol("0");
        empty.setNumber(0);
        for(int i=4;i< board.length;i++){
            board[i]=empty;
        }
        deck.addToTopCard(-1);
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
        if(newCard.getCard().equals("10♦")){
            topindex +=3;
        }else if(newCard.getCard().equals("2♣")){
            topindex +=2;
        }else{
            topindex++;
        }
        board[topindex]=newCard;
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
    }
    //topindex+1 is the number of cards on the board
    public int getTopindex(){return topindex+1;}
    public int getTopCardNum(){ return board[topindex].getNumber();}
}
