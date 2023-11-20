//  Bishop.java
//
//  Last modified on March 22, 2018

import javax.swing.*;

class Bishop extends ChessPiece
{
    public Bishop ()
    {
        image = new ImageIcon("bishop.jpg");
    }

    protected boolean attackingThisLocation (int indexRow, int indexColumn)
    {
      int columnDiff = pieceColumn - indexColumn;
      int rowDiff = pieceRow - indexRow;

      if ((columnDiff + rowDiff == 0) ||  (columnDiff == rowDiff))
           return true;
      else return false;

    }
}
