package com.leekaisheng.minesweeper;

public class Cell {
    private boolean isMine;
    private int adjacentMines;
    private boolean revealed;

    public Cell() {
        this.isMine = false;
        this.adjacentMines = 0;
        this.revealed = false;
    }

    public boolean isMine() {
        return isMine;
    }

    public void setMine(boolean mine) {
        isMine = mine;
    }

    public int getAdjacentMines() {
        return adjacentMines;
    }

    public void setAdjacentMines(int adjacentMines) {
        this.adjacentMines = adjacentMines;
    }

    public boolean isRevealed() {
        return revealed;
    }

    public void reveal() {
        this.revealed = true;
    }

	public void setRevealed(boolean revealed) {
		this.revealed = revealed;
		
	}
}
