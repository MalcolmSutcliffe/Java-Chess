import java.util.*;

public class Knight extends Piece{
  
  public Knight(Square spawn, int color){
    identifier = 'N';
    isAlive = true;
    isSelected = false;
    currentSquare = spawn;
    this.color = color;
    spawn.piece = this;
    value = 3;
  }
  
  public ArrayList<Square> generateAttackSquaresForKing(){
    
    ArrayList<Square> a = new ArrayList<Square>();
    
    if (!this.isAlive){
      return a;
    }
    
    for (int i = 0; i < 8; i++){
      for (int j = 0; j < 8; j++){
        Square squareCheck = Chess.board.array[i][j];
        if ((Math.abs(i-currentSquare.row) == 1 && Math.abs(j-currentSquare.column) == 2) || (Math.abs(i-currentSquare.row) == 2 && Math.abs(j-currentSquare.column) == 1)){
            a.add(squareCheck);
        }
      }
    }
    return a;
  }
 
  public ArrayList<Square> generateAttackSquares(){
    
    ArrayList<Square> a = new ArrayList<Square>();
    
    if (!this.isAlive){
      attackSquares = a;
      return a;
    }
    
    for (int i = 0; i < 8; i++){
      for (int j = 0; j < 8; j++){
        Square squareCheck = Chess.board.array[i][j];
        if ((Math.abs(i-currentSquare.row) == 1 && Math.abs(j-currentSquare.column) == 2) || (Math.abs(i-currentSquare.row) == 2 && Math.abs(j-currentSquare.column) == 1)){
          if(squareCheck.piece == null){
            a.add(squareCheck);
          } else if (squareCheck.piece.color != this.color){
            a.add(squareCheck);
          } 
        }
      }
    }
    
    
    attackSquares = a;
    return a;
  }
}