//  ChessPiece.java
//
//  Created by Henry Leitner
//  Last modified on March 19, 20181

import javax.swing.*;

abstract class ChessPiece
{
    protected int pieceRow;
    protected int pieceColumn;
    protected ImageIcon image;

    protected void setRow ( int r)
    {
        pieceRow = r;
    }

    protected void setColumn (int c)
    {
        pieceColumn = c;
    }

    abstract protected boolean attackingThisLocation(int row, int column);
}
