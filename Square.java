public class Square{
  
  public int column;
  public int row;
  public Piece piece;
  
  public Square(int row, int column){ 
    this.column = column;
    this.row = row;
    piece = null;
  }
  
  public String toString(){
    return "" + (char) (column+97) + (8-row);
  }
  
}