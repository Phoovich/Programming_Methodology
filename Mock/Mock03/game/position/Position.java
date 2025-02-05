package game.position;

import game.util.Constant;

import java.util.Objects;

public class Position {

    private int row;
    private int col;

    public Position(int row, int col) {
        setRow(row);
        setCol(col);
    }

    public Position(String s) {
        if (s.length() != 2 || 
            !Character.isLetter(s.charAt(0)) || 
            !Character.isDigit(s.charAt(1))) {
            throw new IllegalArgumentException(String.format(
                    "Invalid position format. Expected format: [a-%c][1-%c]",
                    (char) ('a' + Constant.COL - 1),
                    (char) ('1' + Constant.ROW - 1)
            ));
        }

        int colIndex = s.charAt(0) - 'a';
        int rowIndex = s.charAt(1) - '1';

        setRow(rowIndex);
        setCol(colIndex);
    }

    public String toString() {
        return "" + (char) (col + 'a') + (char) (row + '1');
    }

    @Override
    public boolean equals(Object object) {

        if (object instanceof Position p) {
            return p.row == row && p.col == col;
        }

        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(toString());
    }

    public int getRow() {
        return this.row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return this.col;
    }

    public void setCol(int col) {
        this.col = col;
    }

}
