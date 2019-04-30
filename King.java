import java.util.*;

public class King extends Piece{
  
  public King(Square spawn, int color){
    identifier = 'K';
    isAlive = true;
    isSelected = false;
    currentSquare = spawn;
    this.color = color;
    spawn.piece = this;
  }
  
  public void move(int row, int column, boolean print){
      if (Math.abs(currentSquare.column - column) > 1){
        if(column == 6){
          Chess.board.array[7*(1-this.color)][7].piece.move(7*(1-this.color), 5, false);
          if (Chess.turn == 0){
            System.out.print("\nO-O");
          }
          else{
            System.out.print("\tO-O");
          }
        } else if(column == 2){
          Chess.board.array[7*(1-this.color)][0].piece.move(7*(1-this.color), 3, false);
          if (Chess.turn == 0){
            System.out.print("\nO-O-O");
          }
          else{
            System.out.print("\tO-O-O");
          }
        }
      } else {
        if(!(Chess.board.array[row][column].piece == null)){
          if (Chess.turn == 0){
            System.out.print("\n"+this.identifier+"x"+Chess.board.array[row][column]);
          }
          else{
            System.out.print("\t"+this.identifier+"x"+Chess.board.array[row][column]);
          }
        } else {
          if (Chess.turn == 0){
            System.out.print("\n"+this.identifier+Chess.board.array[row][column]);
          }
          else{
            System.out.print("\t"+this.identifier+Chess.board.array[row][column]);
          }
        }  
      }
      this.castlingRights = false;
      this.currentSquare.piece = null;
      this.currentSquare = Chess.board.array[row][column];
      Chess.board.array[row][column].piece = this;
  }
    
  public ArrayList<Square> generateAttackSquaresForKing(){
    
    ArrayList<Square> a = new ArrayList<Square>();
    
    if (!this.isAlive){
      return a;
    }
    
    for (int i = currentSquare.row-1; i <= currentSquare.row+1; i++){
      for (int j = currentSquare.column-1; j <= currentSquare.column+1; j++){
        if (i >= 0 && i < 8 && j >= 0 && j < 8 && !(i == currentSquare.row && j == currentSquare.column)){
            a.add(Chess.board.array[i][j]);
        }
      }
    }
    return a;
  }
  
  public boolean isInCheck(){
  
    for (Square[] k : Chess.board.array){
      for (Square s : k){
        if (!(s.piece == null) && s.piece.color == (1-this.color) && s.piece.isAlive && s.piece.generateAttackSquaresForKing().contains(this.currentSquare)){
          return true;
        }
      }
    }
    
    return false;
  }
  
  
  
  
 
  public ArrayList<Square> generateAttackSquares(){
    
    ArrayList<Square> a = new ArrayList<Square>();
    
    for (int i = currentSquare.row-1; i <= currentSquare.row+1; i++){
      for (int j = currentSquare.column-1; j <= currentSquare.column+1; j++){
        if (i >= 0 && i < 8 && j >= 0 && j < 8 && !(i == currentSquare.row && j == currentSquare.column)){
          if (Chess.board.array[i][j].piece == null){
            a.add(Chess.board.array[i][j]);
          } else if (!(Chess.board.array[i][j].piece == null) && Chess.board.array[i][j].piece.color != this.color){
            a.add(Chess.board.array[i][j]);
          }
        }
      }
    }
    
    ArrayList<Square> illegalSquares = new ArrayList<Square>();
    
    
    for (Square s : a){
      if (!(s.piece == null) && s.piece.color != this.color){
        illegalSquares.addAll(s.piece.generateAttackSquaresForKing());
      }
    }
    
    if (this.castlingRights && Chess.board.array[7*(1-this.color)][5].piece == null && Chess.board.array[7*(1-this.color)][6].piece == null
          && !this.isInCheck() && !illegalSquares.contains(Chess.board.array[7*(1-this.color)][6]) && !illegalSquares.contains(Chess.board.array[7*(1-this.color)][5])
          && !(Chess.board.array[7*(1-this.color)][7].piece == null) && Chess.board.array[7*(1-this.color)][7].piece.castlingRights){
      a.add(Chess.board.array[7*(1-Chess.players[this.color].color)][6]);
    }
    if (this.castlingRights && Chess.board.array[7*(1-this.color)][3].piece == null && Chess.board.array[7*(1-this.color)][2].piece == null && Chess.board.array[7*(1-this.color)][1].piece == null
          && !this.isInCheck() && !illegalSquares.contains(Chess.board.array[7*(1-this.color)][3]) && !illegalSquares.contains(Chess.board.array[7*(1-this.color)][2]) && !illegalSquares.contains(Chess.board.array[7*(1-this.color)][1])
          && !(Chess.board.array[7*(1-this.color)][0].piece == null) && Chess.board.array[7*(1-this.color)][0].piece.castlingRights){
      a.add(Chess.board.array[7*(1-this.color)][2]);
    }
    
    a.removeAll(illegalSquares);
    attackSquares = a;
    return a;
  }
}