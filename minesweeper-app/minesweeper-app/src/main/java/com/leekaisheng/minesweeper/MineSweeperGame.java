package com.leekaisheng.minesweeper;

import java.util.Scanner;

public class MineSweeperGame {
    private Board board;
    private int uncoveredCells;
    private boolean gameOver;

    public MineSweeperGame() {}

    private void initializeGame(int size, int numberOfMines) {
        this.board = new Board(size, numberOfMines);
        this.uncoveredCells = 0;
        this.gameOver = false;
    }
    
    public void setBoard(Board board) {
        this.board = board;
    }

    public boolean processMove(String move) {
        move = move.trim().toUpperCase();
        int size = board.getSize();      
        
        if (size < 1 || size > 26) {
            System.out.println("Invalid board size");
            return false;
        }
        
        if (validateMove(move, board)) {
            System.out.println("Invalid move, please try again.");
            return false;
        }

        int row = move.charAt(0) - 'A';  
        int col = Integer.parseInt(move.substring(1)) - 1; 
        
        boolean isOutsideRowAndColBound = row < 0 || row >= board.getSize() || col < 0 || col >= board.getSize();
        if (isOutsideRowAndColBound) {
            System.out.println("Invalid move, out of bounds. Please try again.");
            return false;
        }

        Cell cell = board.getCell(row, col);
        
        return processCell(cell, row, col);
    }
    
    private boolean processCell(Cell cell, int row, int col) {
    	 if (cell.isRevealed()) {
             System.out.println("This square has already been revealed. Please choose another one.");
             return false;
         }

         if (cell.isMine()) {
             System.out.println("Oh no, you detonated a mine! Game Over.");
             gameOver = true;
             return false;
         } else {
             if (cell.getAdjacentMines() == 0) {
                 revealAdjacentZeros(row, col);
             } else {
                 cell.reveal();
                 uncoveredCells++;
             }
             return uncoveredCells == (board.getSize() * board.getSize() - board.getMines());
         }
    }

    private void revealAdjacentZeros(int row, int col) {
        if (!board.isInBounds(row, col)) return;

        Cell cell = board.getCell(row, col);
        if (cell.isRevealed() || cell.isMine()) return;

        cell.reveal();
        uncoveredCells++;

        if (cell.getAdjacentMines() == 0) {
            for (int dr = -1; dr <= 1; dr++) {
                for (int dc = -1; dc <= 1; dc++) {
                    if (dr == 0 && dc == 0) continue;
                    revealAdjacentZeros(row + dr, col + dc);
                }
            }
        }
    }

    public void play() {
        Scanner scanner = new Scanner(System.in);
        boolean playAgain = true;

        while (playAgain) {
            int size = getSizeInput(scanner);
            int numberOfMines = getNumberOfMinesInput(scanner, size);

            System.out.println("Starting a new game...\n");
            initializeGame(size, numberOfMines);
            board.printBoard(); 
            uncoveredCells = 0;
            gameOver = false;

            while (!gameOver) {
            	
                System.out.print("Select a square to reveal (e.g. A1): ");
                String move = scanner.nextLine().toUpperCase();

                if (validateMove(move, board)) {
                    System.out.println("Invalid move, please try again.");
                    continue;
                }

                boolean won = processMove(move);

                board.printBoard();

                if (gameOver) {
                    break;
                }

                if (won) {
                    System.out.println("Congratulations, you have won the game!");
                    break;
                }
            }

            System.out.println("\nPress any key to play again...");
            scanner.nextLine();
            System.out.println("Starting a new game...\n");
        }

        System.out.println("Thanks for playing Minesweeper!");
    }

    public boolean isGameOver() {
		return gameOver;
	}
    
    private boolean validateMove(String move, Board board) {
    	String regex = generateMoveRegex(board.getSize());
    	return !move.matches(regex);
    }
    
    private String generateMoveRegex(int size) {
        char maxRowChar = (char) ('A' + size - 1);

        //Rows are A-Z and Columns are 1-26
        return "[A-" + maxRowChar + "](?:[1-" + size + "])";
    }

	private int getSizeInput(Scanner scanner) {
        int size = 0;
    	System.out.println("Welcome to Minesweeper!");
    	System.out.println();
        while (true) {
            System.out.print("Enter the size of the grid(2 to 26) (e.g. 4 for a 4x4 grid):");
            if (scanner.hasNextInt()) {
                size = scanner.nextInt(); // Retrieve input
                if (size >= 2 && size <= 26) {
                    scanner.nextLine(); // Clear \n
                    break;
                } else {
                    System.out.println("Size must be between 2 and 26.");
                }
            } else {
                System.out.println("Invalid input. Please enter a number between 1 and 26.");
                scanner.next(); // Clear invalid input
            }
        }
        return size;
    }


    private int getNumberOfMinesInput(Scanner scanner, int size) {
        int numberOfMines = 0;
        int maxMines = (int) (size * size * 0.35);
        while (true) {
            System.out.print("Enter the number of mines to place on the grid (maximum is 35% of the total squares):");
            if (scanner.hasNextInt()) {
                numberOfMines = scanner.nextInt(); //Retrieve input
                if (numberOfMines >= 1 && numberOfMines <= maxMines) {
                    scanner.nextLine(); // // Clear \n
                    break;
                } else {
                    System.out.println("Number of mines must be between 1 and " + maxMines + ".");
                }
            } else {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.next(); // Clear invalid input
            }
        }
        return numberOfMines;
    }

}
