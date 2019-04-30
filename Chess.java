public class Chess{
  
  public static int turn = 0;
  public static Board board = new Board();
  public static Board virtualBoard = new Board();
  public static Player white = new Player(0);
  public static Player black = new Player(1);
  public static Player[] players = new Player[]{white, black};
  
   
}