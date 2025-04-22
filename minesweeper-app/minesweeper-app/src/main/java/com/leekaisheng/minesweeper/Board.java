package com.leekaisheng.minesweeper;

import java.util.Random;

public class Board {
    private final Cell[][] grid;
    private final int size;
    private final int numberOfMines;

    public Board(int size, int numberOfMines) {
        this.size = size;
        this.numberOfMines = numberOfMines;
        this.grid = new Cell[size][size];
        initializeBoard();
    }

    private void initializeBoard() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                grid[i][j] = new Cell();
            }
        }

        Random rand = new Random();
        int minesPlaced = 0;
        while (minesPlaced < numberOfMines) {
            int row = rand.nextInt(size);
            int col = rand.nextInt(size);

            if (!grid[row][col].isMine()) {
                grid[row][col].setMine(true);
                minesPlaced++;
                updateAdjacentMines(row, col);
            }
        }
    }

    private void updateAdjacentMines(int row, int col) {
        for (int i = Math.max(0, row - 1); i <= Math.min(size - 1, row + 1); i++) {
            for (int j = Math.max(0, col - 1); j <= Math.min(size - 1, col + 1); j++) {
                if (!grid[i][j].isMine()) {
                    grid[i][j].setAdjacentMines(grid[i][j].getAdjacentMines() + 1);
                }
            }
        }
    }
    
    
    public boolean isInBounds(int row, int col) {
        return row >= 0 && row < size && col >= 0 && col < size;
    }

    public Cell getCell(int row, int col) {
        return grid[row][col];
    }

    public int getSize() {
        return size;
    }

    public int getMines() {
        return numberOfMines;
    }

    public void printBoard() {
        System.out.print("   ");
        for (int i = 1; i <= size; i++) {
            System.out.printf("%2d ", i);
        }
        System.out.println();

        for (int i = 0; i < size; i++) {
            System.out.printf("%-2s ", (char) ('A' + i));
            for (int j = 0; j < size; j++) {
                if (grid[i][j].isRevealed()) {
                    if (grid[i][j].isMine()) {
                        System.out.printf("%2s ", "X");
                    } else {
                        System.out.printf("%2d ", grid[i][j].getAdjacentMines());
                    }
                } else {
                    System.out.printf("%2s ", "_");
                }
            }
            System.out.println();
        }
    }


}