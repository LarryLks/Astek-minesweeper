
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.leekaisheng.minesweeper.Board;
import com.leekaisheng.minesweeper.Cell;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    private Board board;
    private int size = 5;
    private int numberOfMines = 5;

    @BeforeEach
    void setUp() {
        board = new Board(size, numberOfMines);
    }

    @Test
    void testBoardSizeInitialization() {
        assertEquals(size, board.getSize());
    }

    @Test
    void testMineCount() {
        int mineCount = 0;
        for (int i = 0; i < board.getSize(); i++) {
            for (int j = 0; j < board.getSize(); j++) {
                if (board.getCell(i, j).isMine()) {
                    mineCount++;
                }
            }
        }
        assertEquals(numberOfMines, mineCount);
    }

    @Test
    void testCellInitialization() {
        for (int i = 0; i < board.getSize(); i++) {
            for (int j = 0; j < board.getSize(); j++) {
                assertNotNull(board.getCell(i, j));
            }
        }
    }

    @Test
    void testInBounds() {
        assertTrue(board.isInBounds(0, 0));
        assertTrue(board.isInBounds(size - 1, size - 1));
        assertFalse(board.isInBounds(-1, 0));
        assertFalse(board.isInBounds(0, size));
    }

    @Test
    void testGetCellReturnsCorrectCell() {
        Cell cell = board.getCell(2, 3);
        assertNotNull(cell);
    }

    @Test
    void testAdjacentMineCountIsCorrect() {
        Board customBoard = new Board(3, 0);
        customBoard.getCell(1, 1).setMine(true);
        customBoard.getCell(1, 1).setRevealed(true);
        
        customBoard.getCell(0, 0).setAdjacentMines(1);
        customBoard.getCell(0, 1).setAdjacentMines(1);
        customBoard.getCell(0, 2).setAdjacentMines(1);
        customBoard.getCell(1, 0).setAdjacentMines(1);
        customBoard.getCell(1, 2).setAdjacentMines(1);
        customBoard.getCell(2, 0).setAdjacentMines(1);
        customBoard.getCell(2, 1).setAdjacentMines(1);
        customBoard.getCell(2, 2).setAdjacentMines(1);

        assertEquals(1, customBoard.getCell(0, 0).getAdjacentMines());
    }
}
