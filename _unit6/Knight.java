//  Knight.java
//
//  Created by Henry Leitner
//  Modified on March 22, 2018

import javax.swing.*;

class Knight extends ChessPiece
{
    public Knight()
    {
      image = new ImageIcon ("knight.jpg");
    }

    protected boolean attackingThisLocation (int indexRow, int indexColumn)
    {
      int columnDiff = pieceColumn - indexColumn;
      int rowDiff = pieceRow - indexRow;

      if ((columnDiff * columnDiff + rowDiff * rowDiff == 5) ||
          ( (columnDiff == 0) && (rowDiff == 0))) return true;
      else return false;

    }
}
