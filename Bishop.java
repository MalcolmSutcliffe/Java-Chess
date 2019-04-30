import java.util.*;

public class Bishop extends Piece{
  
  public Bishop(Square spawn, int color){
    identifier = 'B';
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
      attackSquares = a;
      return a;
    }
    
    for (int i = 1; currentSquare.row + i < 8 && currentSquare.column + i < 8; i++){
      Square squareCheck = Chess.board.array[currentSquare.row + i][currentSquare.column + i];
      if(squareCheck.piece == null){
        a.add(squareCheck);
      } else {
        a.add(squareCheck);
        break;
      }
    }
    for (int i = 1; currentSquare.row - i >= 0 && currentSquare.column - i >= 0; i++){
      Square squareCheck = Chess.board.array[currentSquare.row - i][currentSquare.column - i];
      if(squareCheck.piece == null){
        a.add(squareCheck);
      } else {
        a.add(squareCheck);
        break;
      }
    }
for (int i = 1; currentSquare.row + i < 8 && currentSquare.column - i >= 0; i++){
      Square squareCheck = Chess.board.array[currentSquare.row + i][currentSquare.column - i];
      if(squareCheck.piece == null){
        a.add(squareCheck);
      } else {
        a.add(squareCheck);
        break;
      }
    }
    for (int i = 1; currentSquare.row - i >= 0 && currentSquare.column + i < 8; i++){
      Square squareCheck = Chess.board.array[currentSquare.row - i][currentSquare.column + i];
      if(squareCheck.piece == null){
        a.add(squareCheck);
      } else {
        a.add(squareCheck);
        break;
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
    
    for (int i = 1; currentSquare.row + i < 8 && currentSquare.column + i < 8; i++){
      Square squareCheck = Chess.board.array[currentSquare.row + i][currentSquare.column + i];
      if(squareCheck.piece == null){
        a.add(squareCheck);
      } else if (squareCheck.piece.color != this.color){
        a.add(squareCheck);
        break;
      } else {
        break;
      }
    }
    for (int i = 1; currentSquare.row - i >= 0 && currentSquare.column - i >= 0; i++){
      Square squareCheck = Chess.board.array[currentSquare.row - i][currentSquare.column - i];
      if(squareCheck.piece == null){
        a.add(squareCheck);
      } else if (squareCheck.piece.color != this.color){
        a.add(squareCheck);
        break;
      } else {
        break;
      }
    }
for (int i = 1; currentSquare.row + i < 8 && currentSquare.column - i >= 0; i++){
      Square squareCheck = Chess.board.array[currentSquare.row + i][currentSquare.column - i];
      if(squareCheck.piece == null){
        a.add(squareCheck);
      } else if (squareCheck.piece.color != this.color){
        a.add(squareCheck);
        break;
      } else {
        break;
      }
    }
    for (int i = 1; currentSquare.row - i >= 0 && currentSquare.column + i < 8; i++){
      Square squareCheck = Chess.board.array[currentSquare.row - i][currentSquare.column + i];
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