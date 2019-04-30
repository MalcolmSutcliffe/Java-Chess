import java.util.*;

public class Pawn extends Piece{
  
  public Pawn(Square spawn, int color){
    identifier = ' ';
    isAlive = true;
    isSelected = false;
    currentSquare = spawn;
    this.color = color;
    spawn.piece = this;
    value = 1;
  }
  
  public void move(int row, int column, boolean print){
    if (Math.abs(row - this.currentSquare.row) == 2){
      this.enPassant = true;
    }
    if (row == 7*this.color){
      this.isAlive = false;
      Scanner s = new Scanner(System.in);
      boolean select = false;
      char selection;
      System.out.println("\nSelect piece: Q for Queen, R for Rook, N for Knight and B for Bishop");
      Piece newPiece;
      while (!select){
        String type = s.nextLine();
        try {
            selection = type.charAt(0);
        } catch (Exception e){
          System.out.println("Error, try again");
          continue;
        }
        if (selection == 'Q' || selection == 'q'){
          selection = 'Q';
          newPiece = new Queen(Chess.board.array[row][column], this.color);
        }
        else if (selection == 'R' || selection == 'r'){
          selection = 'R';
          newPiece = new Rook(Chess.board.array[row][column], this.color);
        }
        else if (selection == 'N' || selection == 'n'){
          selection = 'N';
          newPiece = new Knight(Chess.board.array[row][column], this.color);
        }
        else if (selection == 'B' || selection == 'b'){
          selection = 'B';
          newPiece = new Bishop(Chess.board.array[row][column], this.color);
        }
        else {
          System.out.println("Incorrect input.");
          continue;
        }
        if (!(Chess.board.array[row][column].piece == null)){
          if (Chess.turn == 0){
            System.out.print("\n" +(char)(this.currentSquare.column+97)+"x"+Chess.board.array[row][column]+"="+selection);
          }
          else{
            System.out.print("\t" +(char)(this.currentSquare.column+97)+"x"+Chess.board.array[row][column]+"="+selection);
          }
          Chess.board.array[row][column].piece = null;
        }else{
          if (Chess.turn == 0){
            System.out.print("\n" +Chess.board.array[row][column]+"="+selection);
          }
          else{
            System.out.print("\t" +Chess.board.array[row][column]+"="+selection);
          }
        }
        Chess.board.array[row][column].piece = newPiece;
        select = true;
        break;
      }
      s.close();
      
    } else {
      if (!(Chess.board.array[row][column].piece == null)){
        if (Chess.turn == 0){
          System.out.print("\n" +(char)(this.currentSquare.column+97)+"x"+Chess.board.array[row][column]);
        }
        else{
          System.out.print("\t" +(char)(this.currentSquare.column+97)+"x"+Chess.board.array[row][column]);
        }
        Chess.board.array[row][column].piece = null;
      }else{
        if (Chess.turn == 0){
          System.out.print("\n" +Chess.board.array[row][column]);
        }
        else{
          System.out.print("\t" +Chess.board.array[row][column]);
        }
      }
      Chess.board.array[row][column].piece = this;
    }
    this.currentSquare.piece = null;
    this.currentSquare = Chess.board.array[row][column];
  }
  
  public ArrayList<Square> generateAttackSquaresForKing(){
    
    ArrayList<Square> a = new ArrayList<Square>();
      
    if (this.color == 0){
      if (this.currentSquare.column == 0){
        a.add(Chess.board.array[currentSquare.row-1][currentSquare.column+1]);
      } else if (this.currentSquare.column == 7){
        a.add(Chess.board.array[currentSquare.row-1][currentSquare.column-1]);
      } else {
        a.add(Chess.board.array[currentSquare.row-1][currentSquare.column-1]);
        a.add(Chess.board.array[currentSquare.row-1][currentSquare.column+1]);
      } 
    }else if (this.color == 1){
      if (this.currentSquare.column == 0){
        a.add(Chess.board.array[currentSquare.row+1][currentSquare.column+1]);
      } else if (this.currentSquare.column == 7){
        a.add(Chess.board.array[currentSquare.row+1][currentSquare.column-1]);
      } else {
        a.add(Chess.board.array[currentSquare.row+1][currentSquare.column-1]);
        a.add(Chess.board.array[currentSquare.row+1][currentSquare.column+1]);
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
    
    if (this.color == 0){
      if (currentSquare.row == 6 && Chess.board.array[currentSquare.row-1][currentSquare.column].piece == null){
        a.add(Chess.board.array[currentSquare.row-1][currentSquare.column]);
        if (Chess.board.array[currentSquare.row-2][currentSquare.column].piece == null){
          a.add(Chess.board.array[currentSquare.row-2][currentSquare.column]);
        }
      } else if (Chess.board.array[currentSquare.row-1][currentSquare.column].piece == null){
        a.add(Chess.board.array[currentSquare.row-1][currentSquare.column]);
      }
      if (currentSquare.column == 0){
        if (!(Chess.board.array[currentSquare.row-1][currentSquare.column+1].piece == null) && Chess.board.array[currentSquare.row-1][currentSquare.column+1].piece.color == 1){
          a.add(Chess.board.array[currentSquare.row-1][currentSquare.column+1]);
        }
        if (!(Chess.board.array[currentSquare.row][currentSquare.column+1].piece == null) && Chess.board.array[currentSquare.row][currentSquare.column+1].piece.color == 1 
              && Chess.board.array[currentSquare.row][currentSquare.column+1].piece.identifier == ' ' && Chess.board.array[currentSquare.row][currentSquare.column+1].piece.enPassant){
          a.add(Chess.board.array[currentSquare.row-1][currentSquare.column+1]);
        }
      }
      else if (currentSquare.column == 7){
        if (!(Chess.board.array[currentSquare.row-1][currentSquare.column-1].piece == null) && Chess.board.array[currentSquare.row-1][currentSquare.column-1].piece.color == 1){
          a.add(Chess.board.array[currentSquare.row-1][currentSquare.column-1]);
        }
        if (!(Chess.board.array[currentSquare.row][currentSquare.column-1].piece == null) && Chess.board.array[currentSquare.row][currentSquare.column-1].piece.color == 1 
              && Chess.board.array[currentSquare.row][currentSquare.column-1].piece.identifier == ' ' && Chess.board.array[currentSquare.row][currentSquare.column-1].piece.enPassant){
          a.add(Chess.board.array[currentSquare.row-1][currentSquare.column-1]);
        }
      } else {
        if (!(Chess.board.array[currentSquare.row-1][currentSquare.column-1].piece == null) && Chess.board.array[currentSquare.row-1][currentSquare.column-1].piece.color == 1){
          a.add(Chess.board.array[currentSquare.row-1][currentSquare.column-1]);
        }
        if (!(Chess.board.array[currentSquare.row-1][currentSquare.column+1].piece == null) && Chess.board.array[currentSquare.row-1][currentSquare.column+1].piece.color == 1){
          a.add(Chess.board.array[currentSquare.row-1][currentSquare.column+1]);
        }
        if (!(Chess.board.array[currentSquare.row][currentSquare.column+1].piece == null) && Chess.board.array[currentSquare.row][currentSquare.column+1].piece.color == 1 
              && Chess.board.array[currentSquare.row][currentSquare.column+1].piece.identifier == ' ' && Chess.board.array[currentSquare.row][currentSquare.column+1].piece.enPassant){
          System.out.println(Chess.board.array[currentSquare.row][currentSquare.column+1].piece.enPassant);
          a.add(Chess.board.array[currentSquare.row-1][currentSquare.column+1]);
        }
        if (!(Chess.board.array[currentSquare.row][currentSquare.column-1].piece == null) && Chess.board.array[currentSquare.row][currentSquare.column-1].piece.color == 1 
              && Chess.board.array[currentSquare.row][currentSquare.column-1].piece.identifier == ' ' && Chess.board.array[currentSquare.row][currentSquare.column-1].piece.enPassant){
          a.add(Chess.board.array[currentSquare.row-1][currentSquare.column-1]);
        }
        
      }
    } else if (this.color == 1){
      if (currentSquare.row == 1 && Chess.board.array[currentSquare.row+1][currentSquare.column].piece == null){
        a.add(Chess.board.array[currentSquare.row+1][currentSquare.column]);
        if (Chess.board.array[currentSquare.row+2][currentSquare.column].piece == null){
          a.add(Chess.board.array[currentSquare.row+2][currentSquare.column]);
        }
      } else if (Chess.board.array[currentSquare.row+1][currentSquare.column].piece == null){
        a.add(Chess.board.array[currentSquare.row+1][currentSquare.column]);
      }
      if (currentSquare.column == 0){
        if (!(Chess.board.array[currentSquare.row+1][currentSquare.column+1].piece == null) && Chess.board.array[currentSquare.row+1][currentSquare.column+1].piece.color == 0){
          a.add(Chess.board.array[currentSquare.row+1][currentSquare.column+1]);
        }
        if (!(Chess.board.array[currentSquare.row][currentSquare.column+1].piece == null) && Chess.board.array[currentSquare.row][currentSquare.column+1].piece.color == 0 
              && Chess.board.array[currentSquare.row][currentSquare.column+1].piece.identifier == ' ' && Chess.board.array[currentSquare.row][currentSquare.column+1].piece.enPassant){
          a.add(Chess.board.array[currentSquare.row+1][currentSquare.column+1]);
        } 
      } 
      else if (currentSquare.column == 7){
        if (!(Chess.board.array[currentSquare.row+1][currentSquare.column-1].piece == null) && Chess.board.array[currentSquare.row+1][currentSquare.column-1].piece.color == 0){
          a.add(Chess.board.array[currentSquare.row+1][currentSquare.column-1]);
        }
        if (!(Chess.board.array[currentSquare.row][currentSquare.column-1].piece == null) && Chess.board.array[currentSquare.row][currentSquare.column-1].piece.color == 0 
              && Chess.board.array[currentSquare.row][currentSquare.column-1].piece.identifier == ' ' && Chess.board.array[currentSquare.row][currentSquare.column-1].piece.enPassant){
          a.add(Chess.board.array[currentSquare.row-1][currentSquare.column-1]);
        }
      } else {
        if (!(Chess.board.array[currentSquare.row+1][currentSquare.column-1].piece == null) && Chess.board.array[currentSquare.row+1][currentSquare.column-1].piece.color == 0){
          a.add(Chess.board.array[currentSquare.row+1][currentSquare.column-1]);
        }
        if (!(Chess.board.array[currentSquare.row+1][currentSquare.column+1].piece == null) && Chess.board.array[currentSquare.row+1][currentSquare.column+1].piece.color == 0){
          a.add(Chess.board.array[currentSquare.row+1][currentSquare.column+1]);
        }
        if (!(Chess.board.array[currentSquare.row][currentSquare.column+1].piece == null) && Chess.board.array[currentSquare.row][currentSquare.column+1].piece.color == 0 
              && Chess.board.array[currentSquare.row][currentSquare.column+1].piece.identifier == ' ' && Chess.board.array[currentSquare.row][currentSquare.column+1].piece.enPassant){
          a.add(Chess.board.array[currentSquare.row+1][currentSquare.column+1]);
        } 
//        System.out.println("\n"+ this + " "  + Chess.board.array[currentSquare.row][currentSquare.column-1] + " " + Chess.board.array[currentSquare.row][currentSquare.column-1].containsPiece + " " + Chess.board.array[currentSquare.row][currentSquare.column-1].piece);
        if (!(Chess.board.array[currentSquare.row][currentSquare.column-1].piece == null) && Chess.board.array[currentSquare.row][currentSquare.column-1].piece.color == 0 
              && Chess.board.array[currentSquare.row][currentSquare.column-1].piece.identifier == ' ' && Chess.board.array[currentSquare.row][currentSquare.column-1].piece.enPassant){
          a.add(Chess.board.array[currentSquare.row+1][currentSquare.column-1]);
        }
        
      }
    }
    attackSquares = a;
    return a;
  }
    
}
