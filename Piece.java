import java.util.*;

public class Piece{
  
  public boolean castlingRights = true;
  public boolean enPassant;
  public char identifier;
  public boolean isAlive;
  public boolean isSelected;
  public int color; //0 = white, 1 = black
  public int value;
  public Square currentSquare;
  public ArrayList<Square> attackSquares;
  
  public Piece(){
    
  }
  
  public boolean isInCheck(){
    return false;
  }
  
  public ArrayList<Square> generatePossibleMoves(){
    
    ArrayList<Square> a = this.generateAttackSquares();

    
    ArrayList<Square> toRemove = new ArrayList<Square>();
    int tempColumn = this.currentSquare.column;
    int tempRow = this.currentSquare.row;
    
    for (Square s : a){
      this.currentSquare = s;
      if (Chess.board.array[s.row][s.column].piece == null){
        Chess.board.array[s.row][s.column].piece = this;
        Chess.board.array[tempRow][tempColumn].piece = null;
        if (Chess.players[Chess.turn].pieces[15].isInCheck()){
          toRemove.add(s);
        }
        Chess.board.array[tempRow][tempColumn].piece = this;
        Chess.board.array[s.row][s.column].piece = null;
      }
      else {
       Piece temp = Chess.board.array[s.row][s.column].piece;
       Chess.board.array[s.row][s.column].piece.isAlive = false;
       Chess.board.array[s.row][s.column].piece = this;
       Chess.board.array[tempRow][tempColumn].piece = null;
        if (Chess.players[Chess.turn].pieces[15].isInCheck()){
          toRemove.add(s);
        }
        Chess.board.array[tempRow][tempColumn].piece = this;
        Chess.board.array[s.row][s.column].piece = temp;
        Chess.board.array[s.row][s.column].piece.isAlive = true;
      }    
    }
    this.currentSquare = Chess.board.array[tempRow][tempColumn];
    
    a.removeAll(toRemove);
    return a;
  }
  
  public ArrayList<Square> generateAttackSquares(){
    return null;
  }
  public ArrayList<Square> generateAttackSquaresForKing(){
    return null;
  }
  
  public String toString(){
    return "" + identifier + currentSquare;
  }
  
  public void move(int row, int column, boolean print){
    if(!(Chess.board.array[row][column].piece == null)){
      if (Chess.turn == 0 && print){
        System.out.print("\n"+this.identifier+"x"+Chess.board.array[row][column]);
      }
      else if (print){
        System.out.print("\t"+this.identifier+"x"+Chess.board.array[row][column]);
      }
      Chess.players[Chess.turn].score += Chess.board.array[row][column].piece.value;
      Chess.board.array[row][column].piece.isAlive = false;
    } else {
      if (Chess.turn == 0 && print){
        System.out.print("\n"+this.identifier+Chess.board.array[row][column]);
      }
      else if (print){
        System.out.print("\t"+this.identifier+Chess.board.array[row][column]);
      }
    }
    this.castlingRights = false;
    this.currentSquare.piece = null;
    this.currentSquare = Chess.board.array[row][column];
    Chess.board.array[row][column].piece = this;
  }
  
}