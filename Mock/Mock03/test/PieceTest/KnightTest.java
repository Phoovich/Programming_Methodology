package test.PieceTest;

import game.board.Board;
import org.junit.Before;
import org.junit.Test;

import game.piece.Bishop;
import game.piece.Knight;
import game.position.Position;

import static org.junit.Assert.*;

import java.util.Set;

public class KnightTest {

	Board b;
	Position posB1;
	Position posG1;

	Position posB8;
	Position posG8;

	@Before
	public void setup() {
		b = new Board();

		posB1 = new Position("b1");
		posG1 = new Position("g1");

		posB8 = new Position("b8");
		posG8 = new Position("g8");
	}

	@Test
	public void knightConstructorWithBoardTest() {

		Knight k1 = new Knight(true, posB1, b);

		assertTrue(k1.isWhite());
		assertFalse(k1.isMoved());
		assertEquals(posB1, k1.getPosition());
		assertEquals(b, k1.getBoard());

		Knight k2 = new Knight(false, posB8, b);
		assertFalse(k2.isWhite());
		assertFalse(k2.isMoved());
		assertEquals(posB8, k2.getPosition());
		assertEquals(b, k2.getBoard());

	}

	@Test
	public void knightMoveStartingPositonTest() {

		Knight kWL = new Knight(true, posB1, b);

		assertEquals(3, kWL.getLegalMove().size());

		assertTrue(kWL.getLegalMove().contains(new Position("a3")));
		assertTrue(kWL.getLegalMove().contains(new Position("c3")));
		assertTrue(kWL.getLegalMove().contains(new Position("d2")));

		Knight kWR = new Knight(true, posG1, b);

		assertEquals(3, kWR.getLegalMove().size());

		assertTrue(kWR.getLegalMove().contains(new Position("f3")));
		assertTrue(kWR.getLegalMove().contains(new Position("h3")));
		assertTrue(kWR.getLegalMove().contains(new Position("e2")));

		Knight kBL = new Knight(false, posB8, b);

		assertEquals(3, kBL.getLegalMove().size());

		assertTrue(kBL.getLegalMove().contains(new Position("a6")));
		assertTrue(kBL.getLegalMove().contains(new Position("c6")));
		assertTrue(kBL.getLegalMove().contains(new Position("d7")));

		Knight kBR = new Knight(false, posG8, b);

		assertEquals(3, kBR.getLegalMove().size());

		assertTrue(kBR.getLegalMove().contains(new Position("f6")));
		assertTrue(kBR.getLegalMove().contains(new Position("h6")));
		assertTrue(kBR.getLegalMove().contains(new Position("e7")));

	}

	@Test
	public void knightAtCornerMove() {

		Knight kW = new Knight(true, new Position("a1"), b);

		assertEquals(2, kW.getLegalMove().size());

		assertTrue(kW.getLegalMove().contains(new Position("b3")));
		assertTrue(kW.getLegalMove().contains(new Position("c2")));

		Knight kB = new Knight(false, new Position("h8"), b);

		assertEquals(2, kB.getLegalMove().size());

		assertTrue(kB.getLegalMove().contains(new Position("g6")));
		assertTrue(kB.getLegalMove().contains(new Position("f7")));

	}

	@Test
	public void knightMoveObstructed() {

		Knight kW = new Knight(true, new Position("a1"), b);
		new Knight(true, new Position("b3"), b);

		assertEquals(1, kW.getLegalMove().size());

		assertFalse(kW.getLegalMove().contains(new Position("b3")));
		assertTrue(kW.getLegalMove().contains(new Position("c2")));

		Knight kB = new Knight(false, new Position("h8"), b);
		new Knight(false, new Position("g6"), b);
		new Knight(false, new Position("f7"), b);

		assertEquals(0, kB.getLegalMove().size());

		assertFalse(kB.getLegalMove().contains(new Position("g6")));
		assertFalse(kB.getLegalMove().contains(new Position("f7")));

	}

	@Test
	public void knightMoveAtBoardCenter() {

		Knight kW = new Knight(true, new Position("e4"), b);

		Set<Position> moves = kW.getLegalMove();
		assertEquals(8, moves.size());
		assertTrue(moves.contains(new Position("c3")));
		assertTrue(moves.contains(new Position("c5")));
		assertTrue(moves.contains(new Position("d2")));
		assertTrue(moves.contains(new Position("d6")));
		assertTrue(moves.contains(new Position("f2")));
		assertTrue(moves.contains(new Position("f6")));
		assertTrue(moves.contains(new Position("g3")));
		assertTrue(moves.contains(new Position("g5")));

		Knight kB = new Knight(false, new Position("f6"), b);
		moves = kB.getLegalMove();
		assertEquals(8, moves.size());
		assertTrue(moves.contains(new Position("d5")));
		assertTrue(moves.contains(new Position("d7")));
		assertTrue(moves.contains(new Position("e4")));
		assertTrue(moves.contains(new Position("e8")));
		assertTrue(moves.contains(new Position("g4")));
		assertTrue(moves.contains(new Position("g8")));
		assertTrue(moves.contains(new Position("h5")));
		assertTrue(moves.contains(new Position("h7")));

	}

	@Test
	public void knightCapturableOpposePiece() {

		Knight kW = new Knight(true, new Position("e4"), b);
		new Knight(false, new Position("d2"), b);
		new Knight(true, new Position("c3"), b);

		Set<Position> moves = kW.getLegalMove();
		assertEquals(7, moves.size());
		assertFalse(moves.contains(new Position("c3")));
		assertTrue(moves.contains(new Position("c5")));
		assertTrue(moves.contains(new Position("d2")));
		assertTrue(moves.contains(new Position("d6")));
		assertTrue(moves.contains(new Position("f2")));
		assertTrue(moves.contains(new Position("f6")));
		assertTrue(moves.contains(new Position("g3")));
		assertTrue(moves.contains(new Position("g5")));

		new Knight(false, new Position("c5"), b);
		new Knight(false, new Position("d6"), b);
		moves = kW.getLegalMove();
		assertEquals(7, moves.size());
		assertFalse(moves.contains(new Position("c3")));
		assertTrue(moves.contains(new Position("c5")));
		assertTrue(moves.contains(new Position("d2")));
		assertTrue(moves.contains(new Position("d6")));
		assertTrue(moves.contains(new Position("f2")));
		assertTrue(moves.contains(new Position("f6")));
		assertTrue(moves.contains(new Position("g3")));
		assertTrue(moves.contains(new Position("g5")));

		Knight kB = new Knight(false, new Position("b7"), b);
		moves = kB.getLegalMove();
		assertEquals(2, moves.size());
		assertTrue(moves.contains(new Position("a5")));
		assertTrue(moves.contains(new Position("d8")));
		assertFalse(moves.contains(new Position("c5")));
		assertFalse(moves.contains(new Position("d6")));

	}

	@Test
	public void knightCapturableOpposeKnight() {

		Knight kW = new Knight(true, new Position("d4"), b);
		Knight kB = new Knight(false, new Position("c6"), b);

		assertTrue(kW.getLegalMove().contains(new Position("c6")));
		assertTrue(kB.getLegalMove().contains(new Position("d4")));
	}

	@Test
	public void knightCantMoveSurroundedByOwnPieces() {
		Knight kW = new Knight(true, new Position("e4"), b);
		new Knight(true, new Position("d6"), b);
		new Knight(true, new Position("f6"), b);
		new Knight(true, new Position("d2"), b);
		new Knight(true, new Position("f2"), b);
		new Knight(true, new Position("c3"), b);
		new Knight(true, new Position("g3"), b);
		new Knight(true, new Position("c5"), b);
		new Knight(true, new Position("g5"), b);

		assertEquals(0, kW.getLegalMove().size());
	}

	@Test
	public void knightCantMoveSurroundedByOpposePieces() {
		Knight kW = new Knight(false, new Position("e4"), b);
		new Knight(true, new Position("d6"), b);
		new Knight(true, new Position("f6"), b);
		new Knight(true, new Position("d2"), b);
		new Knight(true, new Position("f2"), b);
		new Knight(true, new Position("c3"), b);
		new Knight(true, new Position("g3"), b);
		new Knight(true, new Position("c5"), b);
		new Knight(true, new Position("g5"), b);

		assertEquals(8, kW.getLegalMove().size());
	}

	@Test
	public void deepCopyTest() {
		Knight b1 = new Knight(false, posB8, b);
		Knight b2 = (Knight) b1.deepCopy();
		assertFalse(b1 == b2);
		assertEquals(b1, b2);

		b1.hadMoved();
		Knight b3 = (Knight) b1.deepCopy();

		assertFalse(b1 == b3);
		assertEquals(b1, b3);
		assertEquals(b1.isMoved(), b3.isMoved());

	}
}
