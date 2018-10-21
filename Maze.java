package project2;

import java.util.Stack;

/**
 * Maze.java
 * @author Sebrianne Ferguson
 * Last edited: 10/20/2018
 */

public class Maze {
	
	Coordinate[][] grid;
	Stack<Coordinate> cellStack;
	int totalCells;
	Coordinate currentCell;
	int visitedCells;
	
	/**
	 * ctor for the class
	 * @param size - the size of the grid
	 */
	public Maze(int size) {
		//create a CellStack (LIFO) to hold a list of cell locations
		grid = new Coordinate[size][size]; //makes a square grid
		//set TotalCells= number of cells in grid
		totalCells = size*size;
		cellStack = new Stack<Coordinate>();
	}
	
	/**
	 * generateGrid()
	 * creates a 2D array of Coordinates and generates neighbors for each
	 * coordinate.
	 */
	public void generateGrid() {
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid.length; j++) {
				grid[i][j] = new Coordinate(i, j);
				generateNeighbors(grid[i][j]);
			}
		}
	}
	
	/**
	 * generateNeighbors()
	 * generates all the neighboring coordinates to the given coordinate
	 * @param c - the given coordinate
	 */
	public void generateNeighbors(Coordinate c) {
		if (c.getX() - 1 >= 0) { //determine western neighbor
			c.addNeighbor(grid[c.getX() - 1][c.getY()]);
		}
		else if (c.getX() + 1 < grid.length) { //determine eastern neighbor
			c.addNeighbor(grid[c.getX() + 1][c.getY()]);
		}
		else if (c.getY() - 1 >= 0) { //determine northern neighbor
			c.addNeighbor(grid[c.getX()][c.getY() -1]);
		}
		else if (c.getY() + 1 < grid.length) { //determine southern neighbor
			c.addNeighbor(grid[c.getX()][c.getY()+1]);
		}
	}
	
	public void generateMaze() {
		
		
	}

	/**
	 * create a CellStack (LIFO) to hold a list of cell locations
		set TotalCells= number of cells in grid
		choose the starting cell and call it CurrentCell
		set VisitedCells = 1
		while VisitedCells < TotalCells
		find all neighbors of CurrentCell with all walls intact
		if one or more found choose one at random
		knock down the wall between it and CurrentCell
		push CurrentCell location on the CellStack
		make the new cell CurrentCell
		add 1 to VisitedCells
		else
		pop the most recent cell entry off the CellStack
		make it CurrentCell
	 */

}