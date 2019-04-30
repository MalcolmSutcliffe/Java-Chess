
public class Board{
  
  public Square[][] array;
  
  public Board(){
    array = new Square[8][8];
    for (int i = 0; i < 8; i++){
      for (int j = 0; j < 8; j++){
        array[i][j] = new Square(i, j); //i = row, j = column
      }
    }
  }
  
  
  
  public String toString(){
    String output = "";
    for (int i = 0; i < 8; i++){
      for (int j = 0; j < 8; j++){
        output += array[i][j] + " ";
      }
      output += "\n";
    }
    return output;
  }
}