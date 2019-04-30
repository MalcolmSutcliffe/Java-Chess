public class Player{
  
  public int color;
  public int score;
  public Piece[] pieces = new Piece[16];
  public boolean isInCheck;
  
  public Player(int color){
    this.color = color;
    this.score = 0;
    for (int i = 0; i < 8; i++){
      pieces[i] = new Pawn(Chess.board.array[6-(5*color)][i], this.color);
    }
    pieces[8] = new Rook(Chess.board.array[7-(7*color)][0], this.color);
    pieces[9] = new Rook(Chess.board.array[7-(7*color)][7], this.color);
    pieces[10] = new Bishop(Chess.board.array[7-(7*color)][2], this.color);
    pieces[11] = new Bishop(Chess.board.array[7-(7*color)][5], this.color);
    pieces[12] = new Queen(Chess.board.array[7-(7*color)][3], this.color);
    pieces[13] = new Knight(Chess.board.array[7-(7*color)][6], this.color);
    pieces[14] = new Knight(Chess.board.array[7-(7*color)][1], this.color);
    pieces[15] = new King(Chess.board.array[7-(7*color)][4], this.color);
  }
  
  public String toString(){
    if (this.color == 0){
      return "White";
    }
    else {
      return "Black";
    }
  }
  
}