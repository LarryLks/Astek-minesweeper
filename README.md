# Minesweeper App

## Introduction
This Minesweeper game on a square grid. The game is played in the console and is built using Java.

## Assumptions
- The game board is square and its size can range from 2x2 to 26x26.
- The user can specify the number of mines (up to 35% of the total number of cells).
- The game is played by selecting a cell to reveal by providing a row (A-Z) and a column (1-26) in the format `A1`, `B5`, etc.
- If a mine is revealed, the game is over.
- If all non-mine cells are revealed, the player wins the game.

## Prerequisites

- **Java Development Kit (JDK) 17** or higher
- **Eclipse IDE** or another IDE that supports Java development
- **Maven** (to manage dependencies and build the project)

## Step-by-Step Instructions

### 1. Unzip the Project

1. Extract the zipped project to a location of your choice.

### 2. Import Project into Eclipse

1. Open **Eclipse IDE**.
2. Navigate to **File** -> **Import**.
3. In the **Import** dialog, choose **Maven** -> **Existing Maven Projects**.
4. Browse to the folder where you extracted the project and select it.
5. Click **Finish** to import the project into Eclipse.

### 3. Install Required Dependencies

The project uses Maven for dependency management. Eclipse should automatically download the required dependencies. If not:

1. Right-click on the project in the **Project Explorer**.
2. Select **Maven** -> **Update Project**.

### 4. Running the Project

1. Inside Eclipse, locate the `Main.java` file under `src/main/java/com/leekaisheng/minesweeper/Main.java`.
2. Right-click on `Main.java` and select **Run As** -> **Java Application**.
3. The game will start, and you’ll be prompted to enter the size of the board and the number of mines.

### 5. Game Instructions

- Once the game starts, you will be asked to input the size of the grid (between 2 and 26).
- You will then specify the number of mines (between 1 and 35% of the grid size).
- During the game, select a square by entering its coordinates in the format `A1`, `B5`, etc.
- If you hit a mine, the game ends, and you will be informed that you lost.
- If you reveal all non-mine cells, you will win the game.

### 6. Running Tests

The project includes unit tests for the core classes (`Board`, `Cell`, and `MineSweeperGame`) located in `src/test/java`.
To run tests:

1. Right-click on the **Test** class you want to run.
2. Select **Run As** -> **JUnit Test**.
