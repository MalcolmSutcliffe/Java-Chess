import java.util.*;

public class Rook extends Piece{
  
  public Rook(Square spawn, int color){
    identifier = 'R';
    isAlive = true;
    isSelected = false;
    currentSquare = spawn;
    this.color = color;
    spawn.piece = this;
    value = 5;
  }
  
  public ArrayList<Square> generateAttackSquaresForKing(){
    
    ArrayList<Square> a = new ArrayList<Square>();
    
    if (!this.isAlive){
      attackSquares = a;
      return a;
    }
    
    for (int i = currentSquare.column + 1; i < 8; i++){
      Square squareCheck = Chess.board.array[currentSquare.row][i];
      if(squareCheck.piece == null){
        a.add(squareCheck);
      } else {
        a.add(squareCheck);
        break;
      } 
    }
    for (int i = currentSquare.column - 1; i >= 0; i--){
      Square squareCheck = Chess.board.array[currentSquare.row][i];
      if(squareCheck.piece == null){
        a.add(squareCheck);
      } else {
        a.add(squareCheck);
        break;
      } 
    }
    for (int i = currentSquare.row + 1; i < 8; i++){
      Square squareCheck = Chess.board.array[i][currentSquare.column];
      if(squareCheck.piece == null){
        a.add(squareCheck);
      } else {
        a.add(squareCheck);
        break;
      } 
    }
    for (int i = currentSquare.row - 1; i >= 0; i--){
      Square squareCheck = Chess.board.array[i][currentSquare.column];
      if(squareCheck.piece == null){
        a.add(squareCheck);
      } else {
        a.add(squareCheck);
        break;
      } 
    }
    
    
    attackSquares = a;
    return a;
  }
 
  public ArrayList<Square> generateAttackSquares(){
    
    ArrayList<Square> a = new ArrayList<Square>();
    
    if (!this.isAlive){
      attackSquares = a;
      return a;
    }
    
    for (int i = currentSquare.column + 1; i < 8; i++){
      Square squareCheck = Chess.board.array[currentSquare.row][i];
      if(squareCheck.piece == null){
        a.add(squareCheck);
      } else if (squareCheck.piece.color != this.color){
        a.add(squareCheck);
        break;
      } else {
        break;
      }
    }
    for (int i = currentSquare.column - 1; i >= 0; i--){
      Square squareCheck = Chess.board.array[currentSquare.row][i];
      if(squareCheck.piece == null){
        a.add(squareCheck);
      } else if (squareCheck.piece.color != this.color){
        a.add(squareCheck);
        break;
      } else {
        break;
      }
    }
    for (int i = currentSquare.row + 1; i < 8; i++){
      Square squareCheck = Chess.board.array[i][currentSquare.column];
      if(squareCheck.piece == null){
        a.add(squareCheck);
      } else if (squareCheck.piece.color != this.color){
        a.add(squareCheck);
        break;
      } else {
        break;
      }
    }
    for (int i = currentSquare.row - 1; i >= 0; i--){
      Square squareCheck = Chess.board.array[i][currentSquare.column];
      if(squareCheck.piece == null){
        a.add(squareCheck);
      } else if (squareCheck.piece.color != this.color){
        a.add(squareCheck);
        break;
      } else {
        break;
      }
    }
    
    
    attackSquares = a;
    return a;
  }
}