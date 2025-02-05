package game.board;

import game.piece.*;
import game.position.Position;
import game.position.Status;
import game.util.Constant;

public class Board {

	private final Piece[][] board;
	private Status status = Status.NORMAL;

	public Board() {
		board = new Piece[Constant.COL][Constant.ROW];
	}

	private void setPiece(Piece piece, Position position) {
		board[position.getRow()][position.getCol()] = piece;
	}

	public boolean placePiece(Piece piece, Position position) {
		if (!isEmpty(position))
			return false;
		setPiece(piece, position);
		return true;
	}

	public Piece getPiece(Position position) {
		return board[position.getRow()][position.getCol()];
	}

	private boolean movePiece(Position from, Position to) {

		if (!isInBound(from) || !isInBound(to))
			return false;

		// General Move

		if (isSameColor(from, to) || isEmpty(to)) {
			Piece piece = getPiece(from);

			if (piece == null)
				throw new IllegalArgumentException("Piece is null at " + from);

			setPiece(piece, to);
			piece.moveHandle(to);
			setPiece(null, from);

			return true;
		}

		return false;
	}

	public boolean isSameColor(Position position1, Position position2) {
		return isEmpty(position1) || isEmpty(position2)
				|| getPiece(position1).isWhite() != getPiece(position2).isWhite();
	}

	public boolean isEmpty(Position position) {
		return board[position.getRow()][position.getCol()] == null;
	}

	public boolean isInBound(Position position) {
		return position.getRow() >= 0 && position.getRow() < Constant.COL && position.getCol() >= 0
				&& position.getCol() < Constant.COL;
	}

	public Board copyBoard() {
		Board newBoard = new Board();
		for (int i = 0; i < Constant.COL; i++) {
			for (int j = 0; j < Constant.ROW; j++) {
				Position position = new Position(i, j);
				Piece piece = getPiece(position);

				if (piece == null)
					continue;

				Piece newPiece = (Piece) piece.deepCopy();

				newBoard.setPiece(newPiece, position);
				newPiece.setBoard(newBoard);
			}
		}

		return newBoard;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Board other) {
			for (int i = 0; i < Constant.COL; i++) {
				for (int j = 0; j < Constant.ROW; j++) {
					Position position = new Position(i, j);
					if (getPiece(position) != other.getPiece(position))
						return false;
				}
			}
		}

		return true;
	}

	public Piece[][] getBoard() {
		return board;
	}

	public Status getStatus() {
		return status;
	}
}
