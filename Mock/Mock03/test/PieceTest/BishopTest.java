package test.PieceTest;

import game.board.Board;
import game.piece.Bishop;
import game.position.Position;

import org.junit.*;

import static org.junit.Assert.*;

import java.util.Set;

public class BishopTest {

	Board b; // Board
	Position posC8; // White Dark Square Bishop
	Position posF1; // White Light Square Bishop
	Position posF8; // Black Dark Square Bishop
	Position posC1; // Black Light Square Bishop

	@Before
	public void setup() {
		b = new Board();
		posC8 = new Position("c8");
		posF1 = new Position("f1");
		posF8 = new Position("f8");
		posC1 = new Position("c1");
	}

	@Test
	public void bishopConstructorTest() {
		Bishop wDB = new Bishop(true, posC8, b);

		assertTrue(wDB.isWhite());
		assertEquals(posC8, wDB.getPosition());
		assertEquals(b, wDB.getBoard());

		Bishop bDB = new Bishop(false, posF8, b);

		assertFalse(bDB.isWhite());
		assertEquals(posF8, bDB.getPosition());
		assertEquals(b, bDB.getBoard());
	}

	@Test
	public void bishopMoveStartingPositionTest() {
		Bishop wDB = new Bishop(true, posC8, b);
		Set<Position> moves = wDB.getLegalMove();
		assertEquals(7, moves.size());
		Position p = new Position("a6");
		assertTrue(moves.contains(p));

		p = new Position("b7");
		assertTrue(moves.contains(p));

		p = new Position("d7");
		assertTrue(moves.contains(p));

		p = new Position("e6");
		assertTrue(moves.contains(p));

		p = new Position("f5");
		assertTrue(moves.contains(p));

		p = new Position("g4");
		assertTrue(moves.contains(p));

		p = new Position("h3");
		assertTrue(moves.contains(p));

		Bishop bDB = new Bishop(false, posF8, b);
		moves = bDB.getLegalMove();
		assertEquals(7, moves.size());

		p = new Position("a3");
		assertTrue(moves.contains(p));

		p = new Position("b4");
		assertTrue(moves.contains(p));

		p = new Position("c5");
		assertTrue(moves.contains(p));

		p = new Position("d6");
		assertTrue(moves.contains(p));

		p = new Position("e7");
		assertTrue(moves.contains(p));

		p = new Position("g7");
		assertTrue(moves.contains(p));

		p = new Position("h6");
		assertTrue(moves.contains(p));

	}

	@Test
	public void bishopAtCenterTest() {
		Position middle_w = new Position("d4");
		Bishop wDB = new Bishop(true, middle_w, b);
		Set<Position> moves = wDB.getLegalMove();
		assertEquals(13, moves.size());

		Position p = new Position("a1");
		assertTrue(moves.contains(p));

		p = new Position("b2");
		assertTrue(moves.contains(p));

		p = new Position("c3");
		assertTrue(moves.contains(p));

		p = new Position("a7");
		assertTrue(moves.contains(p));

		p = new Position("b6");
		assertTrue(moves.contains(p));

		p = new Position("c5");
		assertTrue(moves.contains(p));

		p = new Position("e3");
		assertTrue(moves.contains(p));

		p = new Position("f2");
		assertTrue(moves.contains(p));

		p = new Position("g1");
		assertTrue(moves.contains(p));

		p = new Position("e5");
		assertTrue(moves.contains(p));

		p = new Position("f6");
		assertTrue(moves.contains(p));

		p = new Position("g7");
		assertTrue(moves.contains(p));

		p = new Position("h8");
		assertTrue(moves.contains(p));

		Position middle_b = new Position("g4");
		Bishop bWB = new Bishop(false, middle_b, b);
		moves = bWB.getLegalMove();
		assertEquals(9, moves.size());

		p = new Position("d1");
		assertTrue(moves.contains(p));

		p = new Position("e2");
		assertTrue(moves.contains(p));

		p = new Position("f3");
		assertTrue(moves.contains(p));

		p = new Position("c8");
		assertTrue(moves.contains(p));

		p = new Position("d7");
		assertTrue(moves.contains(p));

		p = new Position("e6");
		assertTrue(moves.contains(p));

		p = new Position("f5");
		assertTrue(moves.contains(p));

		p = new Position("h3");
		assertTrue(moves.contains(p));

		p = new Position("h5");
		assertTrue(moves.contains(p));

	}

	@Test
	public void bishopCaptureTest() {
		Bishop wDB = new Bishop(true, posC8, b);
		new Bishop(false, new Position("f5"), b);
		Set<Position> moves = wDB.getLegalMove();
		assertEquals(5, moves.size());

		Position p = new Position("a6");
		assertTrue(moves.contains(p));

		p = new Position("b7");
		assertTrue(moves.contains(p));

		p = new Position("d7");
		assertTrue(moves.contains(p));

		p = new Position("e6");
		assertTrue(moves.contains(p));

		p = new Position("f5");
		assertTrue(moves.contains(p));

	}

	@Test
	public void bishopCantCaptureTest() {
		Bishop wDB = new Bishop(true, posC8, b);
		new Bishop(true, new Position("f5"), b);
		Set<Position> moves = wDB.getLegalMove();

		assertEquals(4, moves.size());
		Position p = new Position("a6");
		assertTrue(moves.contains(p));

		p = new Position("b7");
		assertTrue(moves.contains(p));

		p = new Position("d7");
		assertTrue(moves.contains(p));

		p = new Position("e6");
		assertTrue(moves.contains(p));

		p = new Position("f5");
		assertFalse(moves.contains(p));

	}

	@Test
	public void bishopFullDiagonalMovementTest() {
		Bishop wDB = new Bishop(true, posC8, b);
		Set<Position> moves = wDB.getLegalMove();
		assertEquals(7, moves.size());
		Position p = new Position("a6");
		assertTrue(moves.contains(p));

		p = new Position("b7");
		assertTrue(moves.contains(p));

		p = new Position("d7");
		assertTrue(moves.contains(p));

		p = new Position("e6");
		assertTrue(moves.contains(p));

		p = new Position("f5");
		assertTrue(moves.contains(p));

		p = new Position("g4");
		assertTrue(moves.contains(p));

		p = new Position("h3");
		assertTrue(moves.contains(p));

		Bishop bDB = new Bishop(false, new Position("a1"), b);
		moves = bDB.getLegalMove();
		assertEquals(7, moves.size());
		p = new Position("b2");
		assertTrue(moves.contains(p));

		p = new Position("c3");
		assertTrue(moves.contains(p));

		p = new Position("d4");
		assertTrue(moves.contains(p));

		p = new Position("e5");
		assertTrue(moves.contains(p));

		p = new Position("f6");
		assertTrue(moves.contains(p));

		p = new Position("g7");
		assertTrue(moves.contains(p));

		p = new Position("h8");
		assertTrue(moves.contains(p));

	}

	@Test
	public void bishopEdgeCaseTest() {
		Bishop bDB = new Bishop(false, posC1, b);
		Set<Position> moves = bDB.getLegalMove();
		assertEquals(7, moves.size());

		Position p = new Position("a3");
		assertTrue(moves.contains(p));

		p = new Position("b2");
		assertTrue(moves.contains(p));

		p = new Position("d2");
		assertTrue(moves.contains(p));

		p = new Position("e3");
		assertTrue(moves.contains(p));

		p = new Position("f4");
		assertTrue(moves.contains(p));

		p = new Position("g5");
		assertTrue(moves.contains(p));

		p = new Position("h6");
		assertTrue(moves.contains(p));

		Bishop wLB = new Bishop(true, posF1, b);
		moves = wLB.getLegalMove();
		assertEquals(7, moves.size());
		p = new Position("a6");
		assertTrue(moves.contains(p));

		p = new Position("b5");
		assertTrue(moves.contains(p));

		p = new Position("c4");
		assertTrue(moves.contains(p));

		p = new Position("d3");
		assertTrue(moves.contains(p));

		p = new Position("e2");
		assertTrue(moves.contains(p));

		p = new Position("g2");
		assertTrue(moves.contains(p));

		p = new Position("h3");
		assertTrue(moves.contains(p));

	}
	
	@Test
	public void deepCopyTest() {
		Bishop b1 = new Bishop(false, posC1, b);
		Bishop b2 = (Bishop)b1.deepCopy();
		assertFalse(b1 == b2);
		assertEquals(b1,b2);
		
		b1.hadMoved();
		Bishop b3 = (Bishop)b1.deepCopy();

		assertFalse(b1 == b3);
		assertEquals(b1,b3);
		assertEquals(b1.isMoved(), b3.isMoved());
		
		
		
		
	}
	
}
