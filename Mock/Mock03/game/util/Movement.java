package game.util;

import java.util.HashSet;
import java.util.Set;

import game.board.Board;
import game.piece.Bishop;
import game.piece.Knight;
import game.piece.Piece;
import game.position.Position;

public class Movement {

	protected Position current;
	protected Set<Position> moves = new HashSet<>();
	protected Board board;

	public Movement(Position current, Board board) {
		setCurrent(current);
		setBoard(board);
	}

	public void diagonalMove() { // x sign movement

		int row = current.getRow();
		int col = current.getCol();

		for (int s = 0; s < 4; s++) {

			int dx = s < 2 ? 1 : -1;
			int dy = s % 2 == 0 ? 1 : -1;

			for (int i = 1; i < Math.max(Constant.ROW, Constant.COL); i++) {

				Position p = new Position(row + dy * i, col + dx * i);

				if (!board.isInBound(p))
					break;

				if (board.isEmpty(p))
					moves.add(p);
				else {

					if (board.isSameColor(current, p))
						moves.add(p);
					break;
				}

			}
		}
	}

	public void lShapeMove() { // L sign movement

		int row = current.getRow();
		int col = current.getCol();

		int[][] kMoves = { { -2, -1 }, { -2, 1 }, { -1, -2 }, { -1, 2 }, { 1, -2 }, { 1, 2 }, { 2, -1 }, { 2, 1 } };

		for (int[] move : kMoves) {

			Position p = new Position(row + move[0], col + move[1]);

			if (board.isInBound(p) && (board.isEmpty(p) || board.isSameColor(current, p)))
				moves.add(p);

		}
	}

	public void setCurrent(Position current) {
		this.current = current;
	}

	public Set<Position> getMoves() {
		return this.moves;
	}

	public Board getBoard() {
		return this.board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	public void getMovePositions(Piece p) {
		// TODO: move according to the actual piece.

		





	}

}
