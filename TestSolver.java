package sudoku;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;


import java.util.Arrays;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

// Make sure that the imports are correct.
import sudoku.Sudoku;
import sudoku.SudokuSolver;

public class TestSolver {
	private SudokuSolver solver;
	
	/**
	 * Runs before each test and creates a new instance of the solver class.
	 * */
	@BeforeEach public void setUp() {
		// Make sure you use the correct class of your solver.
		this.solver = new Sudoku();
	}
	
	/**
	 * Runs after each test and destorys the instance of the solver class.
	 */
	@AfterEach public void tearDown() {
		this.solver = null;
	}

	//testing solved on unsolveable board
	@Test public void testSolvedOnUnsolvableBoard(){
		int[][] board = new int[][] {
			{ 5, 5, 4, 6, 7, 8, 9, 1, 2 },
			{ 6, 7, 0, 1, 9, 5, 3, 4, 8 },
			{ 1, 9, 8, 3, 4, 2, 5, 6, 7 },
			{ 8, 5, 9, 7, 6, 1, 4, 2, 3 },
			{ 4, 2, 6, 8, 5, 3, 7, 9, 1 },
			{ 7, 1, 3, 0, 2, 4, 8, 5, 6 },
			{ 9, 6, 1, 5, 3, 0, 0, 8, 4 },
			{ 2, 8, 7, 4, 1, 9, 6, 3, 5 },
			{ 3, 4, 5, 2, 8, 6 ,1 ,7 ,9 }
		};

		solver.setGrid(board);
		solver.solve();
		assertFalse(solver.isAllValid(), "The board should be valid after solving.");
	}



	//testing solved on unsolved board
	@Test public void testSolvedonUnsolvedBoard(){
		int[][] board = new int[][] {
			{ 0, 0, 8, 0, 0, 9, 0, 6, 2 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 5 },
			{ 1, 0, 2, 5, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 2, 1, 0, 0, 9, 0 },
			{ 0, 5, 0, 0, 0, 0, 6, 0, 0 },
			{ 6, 0, 0, 0, 0, 0, 0, 2, 8 },
			{ 4, 1, 0, 6, 0, 8, 0, 0, 0 },
			{ 8, 6, 0, 0, 3, 0, 1, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 4, 0, 0 }
		};
		solver.setGrid(board);
		solver.solve();
		assertTrue(solver.isAllValid(), "The board should be valid after solving.");
	}



	//testing solved on solved board
	@Test public void testSolveOnSolvedBoard() {
		int[][] solvedBoard = new int[][] {
			{ 5, 3, 4, 6, 7, 8, 9, 1, 2 },
			{ 6, 7, 2, 1, 9, 5, 3, 4, 8 },
			{ 1, 9, 8, 3, 4, 2, 5, 6, 7 },
			{ 8, 5, 9, 7, 6, 1, 4, 2, 3 },
			{ 4, 2, 6, 8, 5, 3, 7, 9, 1 },
			{ 7, 1, 3, 9, 2, 4, 8, 5, 6 },
			{ 9, 6, 1, 5, 3, 7, 2, 8, 4 },
			{ 2, 8, 7, 4, 1, 9, 6, 3, 5 },
			{ 3, 4, 5, 2, 8, 6 ,1 ,7 ,9 }
		};
		
		solver.setGrid(solvedBoard);
		
		boolean solved = solver.solve();
		
		assertTrue(solved);
		
		assertTrue(solver.isAllValid(), "The solved board should be valid.");
		
		int[][] grid = solver.getGrid();
		
		assertTrue(Arrays.deepEquals(grid , solvedBoard), "The grid should be the same as the solved board.");
	}

	//testing solve on empty board
	@Test public void testSolveOnEmptyBoard() {
		solver.clearAll();
		boolean solved = solver.solve();
		assertTrue(solved, "An empty board should be solvable.");
	}


	//testing clear all on nonemptyboard
	@Test public void testClearAllOnNonEmptyBoard() {
		int[][] board = new int[][] {
			{ 0, 0, 8, 0, 0, 9, 0, 6, 2 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 5 },
			{ 1, 0, 2, 5, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 2, 1, 0, 0, 9, 0 },
			{ 0, 5, 0, 0, 0, 0, 6, 0, 0 },
			{ 6, 0, 0, 0, 0, 0, 0, 2, 8 },
			{ 4, 1, 0, 6, 0, 8, 0, 0, 0 },
			{ 8, 6, 0, 0, 3, 0, 1, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 4, 0, 0 }
		};
		solver.setGrid(board);
		solver.clearAll();
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				assertTrue(solver.get(i, j) == 0, "After clearAll all cells should be empty.");
			}
		}
	}
	//testing clear all on empty board
	@Test public void testClearAllOnEmptyBoard() {
		solver.clearAll();
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				assertTrue(solver.get(i, j) == 0, "If the board is empty, clearAll should not change the values.");
			}
		}
	}
	//testing clear on non emptycell
	@Test public void testClearOnNonEmptyCell() {
		solver.set(1, 1, 5);
		solver.clear(1, 1);
		int g = solver.get(1, 1);
		assertTrue(g == 0, "If the cell is not empty, clear should set it to 0.");
	}
	//testing clear on empty cell
	@Test public void testClearOnEmptyCell() {
		solver.clear(1, 1);
		int g = solver.get(1, 1);
		assertTrue(g == 0, "If the cell is empty, clear should not change the value.");
	}

	//testing clear on wrong boundries of board
	@Test public void testClearOnWrongBoundries() {
		assertThrows(IndexOutOfBoundsException.class, () -> solver.clear(10, 10), "If row or col is out of range, an exception should be thrown.");
	}
	//testing get on empty 
	@Test public void testGetOnEmpty() {
		int g = solver.get(1, 1);
		assertTrue(0 == g, "If the board is empty, get should return 0.");
	}
	//testing get on out of bound exception
	@Test public void testGetOnOutOfBound() {
		assertThrows(IndexOutOfBoundsException.class, () -> solver.get(10, 10), "If row or col is out of range, an exception should be thrown.");
	}
	//testing that get doesnt modify the board
	@Test public void getConsecutiveCalls() {
		int g = 7;

		solver.set(0, 0, g);
		int g2 = solver.get(0,0);
		assertTrue(g == g2, "Solver::get should return the same value as was set with Solver::set.");

		int g3 = solver.get(0,0);
		assertTrue(g2 == g3, "Solver::get consecutive calls to get should be copies and not the same copy.");
		g3 = 8;
		assertTrue(solver.get(0,0) != g3, "Solver::get should return a new copy and not the same reference.");
	}
	
	//testing set on board with digit out of range
	@Test public void testSetOnDigitOutofrange() {
		assertThrows(IllegalArgumentException.class, () -> solver.set(1, 1, 10), "A board with values out of range should not be valid.");
	}

	//Testing set on board out of valid range
	@Test public void testSetOnBoardOutOfRange() {
		assertThrows(IndexOutOfBoundsException.class, () -> solver.set(10, 10, 8), "if row eller col dålig så throw");
	}

	//Testing isAllValid on empty board
	@Test public void testIsAllValidEmptyBoard() {
		assertFalse(solver.isAllValid(), "An empty board should not be valid.");
	}
	//Testing is allvalid on invalid board
	@Test public void testIsAllValidInvalidBoard() {
		int[][] solvedBoard = new int[][] {
			{ 5, 3, 4, 6, 7, 8, 9, 1, 2 },
			{ 6, 7, 2, 1, 9, 5, 3, 4, 8 },
			{ 1, 9, 8, 3, 4, 2, 5, 6, 7 },
			{ 8, 5, 9, 7, 6, 1, 4, 2, 3 },
			{ 4, 2, 6, 8, 5, 3, 7, 9, 1 },
			{ 7, 1, 3, 9, 2, 4, 8, 5, 6 },
			{ 9, 6, 1, 5, 3, 7, 2, 8, 4 },
			{ 2, 8, 7, 4, 1, 9, 6, 3, 5 },
			{ 3, 4, 5, 2, 8, 6, 1, 7, 9 }
		};
		solver.setGrid(solvedBoard);
		solver.set(0, 0, 1);
		solver.set(0, 1, 1);
		assertFalse(solver.isAllValid(), "A board with duplicates should not be valid.");
	}
	//testing isallvalid on solved board
	@Test public void testIsAllValidSolvedBoard(){
		int[][] solvedBoard = new int[][] {
			{ 5, 3, 4, 6, 7, 8, 9, 1, 2 },
			{ 6, 7, 2, 1, 9, 5, 3, 4, 8 },
			{ 1, 9, 8, 3, 4, 2, 5, 6, 7 },
			{ 8, 5, 9, 7, 6, 1, 4, 2, 3 },
			{ 4, 2, 6, 8, 5, 3, 7, 9, 1 },
			{ 7, 1, 3, 9, 2, 4, 8, 5, 6 },
			{ 9, 6, 1, 5, 3, 7, 2, 8, 4 },
			{ 2, 8, 7, 4, 1, 9, 6, 3, 5 },
			{ 3, 4, 5, 2, 8, 6, 1, 7, 9 }
		};
		solver.setGrid(solvedBoard);
		assertTrue(solver.isAllValid(), "The solved board should be valid.");
	}

	/**
	 * Tests that the setGrid method doesn't throw 
	 * an exception when everything is correct.
	 * */
	@Test public void testSetGridNotThrows() {
		int[][] board = new int[][] {
			{ 0, 0, 8, 0, 0, 9, 0, 6, 2 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 5 },
			{ 1, 0, 2, 5, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 2, 1, 0, 0, 9, 0 },
			{ 0, 5, 0, 0, 0, 0, 6, 0, 0 },
			{ 6, 0, 0, 0, 0, 0, 0, 2, 8 },
			{ 4, 1, 0, 6, 0, 8, 0, 0, 0 },
			{ 8, 6, 0, 0, 3, 0, 1, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 4, 0, 0 }
		};
			
		assertDoesNotThrow(
			() -> solver.setGrid(board),
			"Solver::setGrid shouldn't throw an exception if everything is in range [1..9]."
		);
	}
	
	/**
	 * Tests that the setGrid method does throw 
	 * an exception when something is wrong.
	 * */
	@Test public void testSetGridThrows() {
		int[][] board = new int[][] {
			{ 0, 0, 8, 0, 0, 9, 0, 6, 10 },
			{ 0, 0, 0, 0, 0, 0, 0, 4, 5 },
			{ 1, 0, 2, 5, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 2, 1, 0, 124, 9, 0 },
			{ 0, 5, 0, 0, 0, 0, 6, -3, 0 },
			{ 6, 0, 0, 0, 0, 0, 0, 2, 8 },
			{ 4, 1, 0, 6, 0, 8, 0, 0, 0 },
			{ 8, 6, 0, 0, 3, 0, 1, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 4, 0, 0 }
		};
			
		assertThrows(
			IllegalArgumentException.class, () -> solver.setGrid(board),
			"Solver::setGrid should thrown an exception if any number is out of bounds."
		);
			
		
		assertThrows(
			IllegalArgumentException.class, () -> solver.setGrid(new int[8][9]),
			"Solver::setGrid should thrown an exception if wrong dimension."
		);
			
		assertThrows(
			IllegalArgumentException.class, () -> solver.setGrid(new int[9][10]),
			"Solver::setGrid should thrown an exception if wrong dimension."
		);
	}
	
	/**
	 * Tests that solve doesn't modify the matrix that is
	 * provided to setGrid.
	 * */
	@Test public void testNoExternalModificationSet() {
		int[][] board = new int[][] {
			{ 0, 0, 8, 0, 0, 9, 0, 6, 2 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 5 },
			{ 1, 0, 2, 5, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 2, 1, 0, 0, 9, 0 },
			{ 0, 5, 0, 0, 0, 0, 6, 0, 0 },
			{ 6, 0, 0, 0, 0, 0, 0, 2, 8 },
			{ 4, 1, 0, 6, 0, 8, 0, 0, 0 },
			{ 8, 6, 0, 0, 3, 0, 1, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 4, 0, 0 }
		};
		
		int[][] board2 = new int[][] {
			{ 0, 0, 8, 0, 0, 9, 0, 6, 2 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 5 },
			{ 1, 0, 2, 5, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 2, 1, 0, 0, 9, 0 },
			{ 0, 5, 0, 0, 0, 0, 6, 0, 0 },
			{ 6, 0, 0, 0, 0, 0, 0, 2, 8 },
			{ 4, 1, 0, 6, 0, 8, 0, 0, 0 },
			{ 8, 6, 0, 0, 3, 0, 1, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 4, 0, 0 }
		};
		
		solver.setGrid(board);
		solver.solve();
		assertTrue(
			Arrays.deepEquals(board, board2),
			"Solver::setGrid should copy the elements of the board and not the references to the arrays. " +
			"After Solver:solve the provided board should stay the same."
		);
	}
	
	/**
	 * Test that setGrid and getGrid does a deep copy.
	 * */
	@Test public void testSetAndGetGrid() {
		int[][] board = new int[][] {
			{ 0, 0, 8, 0, 0, 9, 0, 6, 2 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 5 },
			{ 1, 0, 2, 5, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 2, 1, 0, 0, 9, 0 },
			{ 0, 5, 0, 0, 0, 0, 6, 0, 0 },
			{ 6, 0, 0, 0, 0, 0, 0, 2, 8 },
			{ 4, 1, 0, 6, 0, 8, 0, 0, 0 },
			{ 8, 6, 0, 0, 3, 0, 1, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 4, 0, 0 }
		};
		
		solver.setGrid(board);
		int[][] board2 = solver.getGrid();
		
		assertNotSame(board, board2, "Solver::getGrid and Solver::setGrid should do a deep copy of the matrix.");
		for (int i = 0; i < board.length; i++) {
			assertNotSame(
				board[i], board2[i],
				"Solver:getGrid and Solver:setGrid shoud do a deep copy of the matrix, meaning the internal arrays should also be copied."
			);
		}
	}
	
	/**
	 * Makes sure that consecutive calls to getGrid yields different copies of the board.
	 * */
	@Test public void getGridConsecutiveCalls() {
		int[][] board = new int[][] {
			{ 0, 0, 8, 0, 0, 9, 0, 6, 2 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 5 },
			{ 1, 0, 2, 5, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 2, 1, 0, 0, 9, 0 },
			{ 0, 5, 0, 0, 0, 0, 6, 0, 0 },
			{ 6, 0, 0, 0, 0, 0, 0, 2, 8 },
			{ 4, 1, 0, 6, 0, 8, 0, 0, 0 },
			{ 8, 6, 0, 0, 3, 0, 1, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 4, 0, 0 }
		};
		
		solver.setGrid(board);
		int[][] board2 = solver.getGrid();
		assertNotSame(board, board2, "Solver::getGrid and Solver::setGrid should do a deep copy of the matrix.");
		for (int i = 0; i < board.length; i++) {
			assertNotSame(
				board[i], board2[i],
				"Solver:getGrid and Solver:setGrid shoud do a deep copy of the matrix, meaning the internal arrays should also be copied."
			);
		}
		
		int[][] board3 = solver.getGrid();
		assertNotSame(board2, board3, "Solver::getGrid consecutive calls to getGrid should be copies and not the same copy.");
		for (int i = 0; i < board.length; i++) {
			assertNotSame(
				board2[i], board3[i],
				"Solver:getGrid consecutive calls should be deepcopies of the matrix."
			);
		}
	}
	
	/**
	 * Tests that the copies of the arrays from getGrid and setGrid 
	 * are correct.
	 * */
	@Test public void testCorrectCopies() {
		int[][] board = new int[][] {
			{ 0, 0, 8, 0, 0, 9, 0, 6, 2 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 5 },
			{ 1, 0, 2, 5, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 2, 1, 0, 0, 9, 0 },
			{ 0, 5, 0, 0, 0, 0, 6, 0, 0 },
			{ 6, 0, 0, 0, 0, 0, 0, 2, 8 },
			{ 4, 1, 0, 6, 0, 8, 0, 0, 0 },
			{ 8, 6, 0, 0, 3, 0, 1, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 4, 0, 0 }
		};
		
		solver.setGrid(board);
		assertTrue(Arrays.deepEquals(
			board, solver.getGrid()), "Solver::getGrid and Solver::setGrid should copy the elements."
		);
	}
}
