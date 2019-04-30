import java.awt.*;
import java.awt.event.*;

public class Draw extends Frame{
  
  public static final Font FONT = new Font("Times New Roman",Font.BOLD,16);
  public static final Color BACKGROUND = new Color(0, 0, 0);
  public static final Color BLACK_SQUARE = new Color(100, 100, 100);
  public static final Color WHITE_SQUARE = new Color(200, 200, 200);
  public static final Color HIGHLIGHTED_BLACK_SQUARE = new Color(178, 166, 35);
  public static final Color HIGHLIGHTED_WHITE_SQUARE = new Color(249, 233, 49);
  public static final Color IS_SELECTED = new Color(21, 145, 135);
  public static final Color IN_CHECK = new Color(255, 2, 2);
  
  public static void main(String[] args){
    new Draw();

  }
  
  public Draw(){
    
    //set name
    super("Malcolm's Chess");
    
    //set size
    setSize(570, 624);
    
    //set visible
    setVisible(true);
    
    addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e){
        dispose(); 
        System.exit(0);
      }
    }
    );
    addMouseListener(new MouseListener(){
      @Override
      public void mouseClicked(MouseEvent e){
        
        int x = e.getX();
        int y = e.getY();
        
        int column = (x-22)/64;
        int row = (y-72)/64;
        
        if(column >= 0 && column < 8 && row >= 0 && row < 8 && !(Chess.board.array[row][column].piece == null) && Chess.turn == Chess.board.array[row][column].piece.color && Chess.board.array[row][column].piece.isSelected){
          Chess.board.array[row][column].piece.isSelected = false;
          repaint();
          return;
        }
        
        for (Square[] i : Chess.board.array){
          for (Square j : i){
            if (!(j.piece == null) && j.piece.isSelected){
              j.piece.isSelected = false;
              for (Square s : j.piece.generatePossibleMoves()){
                if (s.column == column && s.row == row){
                  if (j.piece.color == 0 && j.piece.identifier == ' ' && !(Chess.board.array[row+1][column].piece == null) && Chess.board.array[row+1][column].piece.color == 1
                        && Chess.board.array[row+1][column].piece.identifier == ' ' && Chess.board.array[row+1][column].piece.enPassant){
                    Chess.board.array[row+1][column].piece = null;
                  }
                  if (j.piece.color == 1 && j.piece.identifier == ' ' && !(Chess.board.array[row-1][column].piece == null) && Chess.board.array[row-1][column].piece.color == 0
                        && Chess.board.array[row-1][column].piece.identifier == ' ' && Chess.board.array[row-1][column].piece.enPassant){
                    Chess.board.array[row-1][column].piece = null;
                  }
                  j.piece.move(row, column, true);
                  for (Piece p : Chess.players[1-Chess.turn].pieces){
                    p.enPassant = false;
                  }
                  Chess.turn = 1-Chess.turn;
                  if (Chess.players[Chess.turn].pieces[15].isInCheck()){
                    int legalMoves = 0;
                    for (Piece p  : Chess.players[Chess.turn].pieces){
                      legalMoves += p.generatePossibleMoves().size();
                    }
                    if (legalMoves < 1){
                      System.out.print("#");
                    }else{
                      System.out.print("+");
                    }   
                  }             
                  repaint();
                  break;     
                }
              }
              break;
            }
          }
        }
        
        if(column >= 0 && column < 8 && row >= 0 && row < 8 && !(Chess.board.array[row][column].piece == null) && Chess.turn == Chess.board.array[row][column].piece.color && !Chess.board.array[row][column].piece.isSelected){
          Chess.board.array[row][column].piece.isSelected = true;
          repaint();
        }
      }
      
      public void mouseEntered(MouseEvent arg0) { }

      @Override
     public void mouseExited(MouseEvent arg0) { }

     @Override
     public void mousePressed(MouseEvent arg0) { }

     @Override
     public void mouseReleased(MouseEvent arg0) { }
    }
    );
  }
  
  public void paint(Graphics g){
    g.setColor(BACKGROUND);
    g.fillRect(0,0,1000,1000);     
    drawBoard(g);
    g.setColor(Color.white);
    g.setFont(FONT);
//    g.drawString("" + (Chess.players[0].score - Chess.players[1].score), 280, 56);
    
    for (Square[] i : Chess.board.array){
      for (Square s : i){
        if (!(s.piece == null) && s.piece.isSelected){
          drawSquare(s.piece.currentSquare.row, s.piece.currentSquare.column, IS_SELECTED, g);
          drawPossibleMoves(g, s.piece);
        }
      }
    }
    if (Chess.players[Chess.turn].pieces[15].isInCheck()){
      drawSquare(Chess.players[Chess.turn].pieces[15].currentSquare.row, Chess.players[Chess.turn].pieces[15].currentSquare.column, IN_CHECK, g);
    }
    drawPieces(g); 
  }
  
  public void drawSquare(int row, int column, Color color, Graphics g){
    g.setColor(color);
    g.fillRect(64*column + 28, 64*row + 72, 64, 64);
  }
  
  public void drawBoard(Graphics g){
    for (int i = 0; i < 8; i++){
      for (int j = 0; j < 8; j++){
        if ((j+i)%2 == 0){
          drawSquare(i, j, WHITE_SQUARE, g);
        } else {
          drawSquare(i, j, BLACK_SQUARE, g);
        }    
      }
    }
  }
  
  public void drawPieces(Graphics g){
    for (int i = 0; i < 8; i++){
      for (int j = 0; j < 8; j++){
        if (!(Chess.board.array[i][j].piece == null) && Chess.board.array[i][j].piece.isAlive){
          if (Chess.board.array[i][j].piece.color == 0){
            g.setColor(Color.white);
          } else {
            g.setColor(Color.black);
          }
//          g.setFont(FONT);
          if (Chess.board.array[i][j].piece.identifier == ' '){
            g.fillRect(64*j + 28 + 28, 64*i + 72 + 28, 8, 8);
            continue;
          }
          g.drawString("" + Chess.board.array[i][j].piece.identifier, 64*j + 28 + 28, 64*i + 72 + 36);
        }        
      }
    }
  }
  public void drawPossibleMoves(Graphics g, Piece p){
    for (Square i : p.generatePossibleMoves()){
      if ((i.row + i.column)%2 == 0){
          drawSquare(i.row, i.column, HIGHLIGHTED_WHITE_SQUARE, g);
        } else {
          drawSquare(i.row, i.column, HIGHLIGHTED_BLACK_SQUARE, g);
        }
    }
  }
 
}