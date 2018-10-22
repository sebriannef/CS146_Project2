package project2;

import java.util.ArrayList;
import java.util.Random;
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
	private Random myRandGen; //from instructions
	
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
		myRandGen=new java.util.Random(0); //seed is 0 -- from instructions
	}
	
	/**
	 * myrandom()
	 * from instructions
	 */
	double myrandom() {
		return myRandGen.nextDouble(); //random in 0-1
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
			}
		}
		
		//generate the neighbors
		//initially i had the call inside of the previous nested for loop
		//but that generated errors.
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid.length; j++) {
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
		if (c.getX() + 1 < grid.length) { //determine eastern neighbor
			c.addNeighbor(grid[c.getX() + 1][c.getY()]);
		}
		if (c.getY() - 1 >= 0) { //determine northern neighbor
			c.addNeighbor(grid[c.getX()][c.getY() -1]);
		}
		if (c.getY() + 1 < grid.length) { //determine southern neighbor
			c.addNeighbor(grid[c.getX()][c.getY()+1]);
		}
	}
	
	/**
	 * generateMaze()
	 * creates the maze as described in the instructions.
	 */
	public void generateMaze() {
		//choose the starting cell and call it current cell.
		currentCell = grid[0][0];
		visitedCells = 1;
		while (visitedCells < totalCells){
			//find all neighbors of CurrentCell with all walls intact
			ArrayList<Coordinate> walledNeighbors = new ArrayList<Coordinate>();
			for (Coordinate neighbor: currentCell.getNeighbors()) {
				System.out.println("XY:" + neighbor.getX() + ", " + neighbor.getY());
				if (neighbor.closedCell == true) {
					walledNeighbors.add(neighbor);
				}
			}
			//if one or more found choose one at random
			if (walledNeighbors.size() >= 1) {
				Random r = new Random();
				//not sure if this works, but got the part inside nextInt() from the instructions, not sure if it should be here
				//Coordinate next = walledNeighbors.get(r.nextInt((int)(myrandom() * walledNeighbors.size()))); 
				Coordinate next = walledNeighbors.get(r.nextInt(walledNeighbors.size())); 
				//knock down the wall between it and CurrentCell
				//first have to find which side of the currentCell the neighbor is on
				if (currentCell.getX() < next.getX()) { //eastern
					currentCell.removeEWall();
					next.removeWWall();
				}
				else if (currentCell.getX() > next.getX()) { //western
					currentCell.removeWWall();
					next.removeEWall();
				}
				else if (currentCell.getY() > next.getY()) { //northern
					currentCell.removeNWall();
					next.removeSWall();
				}
				else { //southern
					currentCell.removeSWall();
					next.removeNWall();
				}
				
				//push CurrentCell location on the CellStack
				cellStack.push(currentCell);
				//make the new cell CurrentCell
				currentCell = next;
				//add 1 to VisitedCells
				visitedCells++;
			} //end if
			else {
				//pop the most recent cell entry off the CellStack
				//make it CurrentCell
				currentCell = cellStack.pop();
				
			} //end else
			
		}
		
	}
	
	/**
	 * displayMaze()
	 * displays a visual image of the maze
	 */
	public void displayMaze() {
		for (int i = 0; i < grid.length; i++) {
			
			//horizontal lines
			for (int j = 0; j < grid[0].length; j++) {
				if (grid[i][j].northernWall == true) { //if it has a northern wall
					if (j == grid[0].length - 1) {	//add an extra plus at the end
						System.out.println("+-+");
					}
					else {
						System.out.print("+-");
					}
				}
				else { //no northern wall
					if (j == grid[0].length - 1) {	//add an extra plus at the end
						System.out.println("+ +");
					}
					else {
						System.out.print("+ ");
					}
				}
			}
			
			//vertical lines
			for (int j = 0; j < grid[0].length; j++) {
				if (grid[i][j].easternWall == true) { //if it has a eatsern wall
					if (j == grid[0].length - 1) {	//add an extra | at the end
						System.out.println("| |");
					}
					else {
						System.out.print("| ");
					}
				}
				else { //no eastern wall
					if (j == grid[0].length - 1) {	//add an extra | at the end
						System.out.println("  |");
					}
					else {
						System.out.print("  ");
					}
				}
			}
			
		}
		
		//now take care of the bottom border
		for (int j = 0; j < grid[0].length; j++) {
			if (grid[grid.length-1][j].southernWall == true) { //if it has a northern wall
				if (j == grid[0].length - 1) {	//add an extra plus at the end
					System.out.println("+-+");
				}
				else {
					System.out.print("+-");
				}
			}
			else { //no northern wall
				if (j == grid[0].length - 1) {	//add an extra plus at the end
					System.out.println("+ +");
				}
				else {
					System.out.print("+ ");
				}
			}
		}
	} //end of display maze
	
	public static void main(String[] args) {
		Maze m = new Maze(3);
		m.generateGrid();
		m.generateMaze();
		m.displayMaze();
	}


}