import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.leekaisheng.minesweeper.Board;
import com.leekaisheng.minesweeper.Cell;
import com.leekaisheng.minesweeper.MineSweeperGame;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class MineSweeperGameTest {

    private MineSweeperGame game;
    private Board mockBoard;
    private Cell mockCell;

    @BeforeEach
    public void setUp() {
        game = new MineSweeperGame();
        mockBoard = mock(Board.class);
        mockCell = mock(Cell.class);
        game.setBoard(mockBoard);
    }

    @Test
    public void testProcessMove_validNonMineCell_revealsCell() {
        when(mockBoard.getSize()).thenReturn(2);
        when(mockBoard.getCell(0, 0)).thenReturn(mockCell);
        when(mockCell.isRevealed()).thenReturn(false);
        when(mockCell.isMine()).thenReturn(false);
        when(mockCell.getAdjacentMines()).thenReturn(1);

        game.processMove("A1");

        verify(mockCell).reveal();
        assertFalse(game.isGameOver());
    }

    @Test
    public void testProcessMove_onMine_setsGameOver() {
        when(mockBoard.getSize()).thenReturn(2);
        when(mockBoard.getCell(0, 0)).thenReturn(mockCell);
        when(mockCell.isRevealed()).thenReturn(false);
        when(mockCell.isMine()).thenReturn(true);

        game.processMove("A1");

        assertTrue(game.isGameOver());
    }

    @Test
    public void testProcessMove_onAlreadyRevealedCell() {
        when(mockBoard.getSize()).thenReturn(2);
        when(mockBoard.getCell(0, 0)).thenReturn(mockCell);
        when(mockCell.isRevealed()).thenReturn(true);

        game.processMove("A1");

        verify(mockCell, never()).reveal();
    }

    @Test
    public void testProcessMove_invalidInputFormat() {
        game.processMove("InvalidInput");

        assertFalse(game.isGameOver());
    }

    @Test
    public void testProcessMove_outOfBoundsInput() {
        when(mockBoard.getSize()).thenReturn(2);
        game.processMove("C3");

        // Should handle without exception
        assertFalse(game.isGameOver());
    }

    @Test
    public void testRevealAdjacentZeros_recursivelyReveals() {
        Board board = new Board(3, 0);
        game.setBoard(board);

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                Cell cell = board.getCell(row, col);
                cell.setMine(false);
                cell.setAdjacentMines(0);
            }
        }

        game.processMove("A1");

        int revealed = 0;
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (board.getCell(row, col).isRevealed()) {
                    revealed++;
                }
            }
        }

        assertEquals(9, revealed);
    }
}
