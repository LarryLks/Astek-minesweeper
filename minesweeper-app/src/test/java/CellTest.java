import org.junit.jupiter.api.Test;

import com.leekaisheng.minesweeper.Cell;

import static org.junit.jupiter.api.Assertions.*;

class CellTest {

    @Test
    void testDefaultCellValues() {
        Cell cell = new Cell();
        assertFalse(cell.isMine());
        assertEquals(0, cell.getAdjacentMines());
        assertFalse(cell.isRevealed());
    }

    @Test
    void testSetMine() {
        Cell cell = new Cell();
        cell.setMine(true);
        assertTrue(cell.isMine());
    }

    @Test
    void testSetAdjacentMines() {
        Cell cell = new Cell();
        cell.setAdjacentMines(3);
        assertEquals(3, cell.getAdjacentMines());
    }

    @Test
    void testReveal() {
        Cell cell = new Cell();
        cell.reveal();
        assertTrue(cell.isRevealed());
    }

    @Test
    void testSetRevealed() {
        Cell cell = new Cell();
        cell.setRevealed(true);
        assertTrue(cell.isRevealed());
    }
}
