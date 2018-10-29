package project2;

//package sjsu.kamel.cs146.project2;

import java.util.*;

/**
 * Maze.java
 * 
 * @author Sebrianne Ferguson Last edited: 10/20/2018
 */

public class Maze {

	Coordinate[][] grid;
	Stack<Coordinate> cellStack;
	int totalCells;
	Coordinate currentCell;
	int visitedCells;
	private Random myRandGen; // from instructions
	ArrayList<Coordinate> marked; //what is this for???

	/**
	 * ctor for the class
	 * 
	 * @param size
	 *            - the size of the grid
	 */
	public Maze(int size) {
		// create a CellStack (LIFO) to hold a list of cell locations
		grid = new Coordinate[size][size]; // makes a square grid
		// set TotalCells= number of cells in grid
		totalCells = size * size;
		cellStack = new Stack<Coordinate>();
		myRandGen = new java.util.Random(0); // seed is 0 -- from instructions
		marked = new ArrayList<Coordinate>();
	}

	/**
	 * myrandom() from instructions
	 */
	double myrandom() {
		return myRandGen.nextDouble(); // random in 0-1
	}

	/**
	 * generateGrid() creates a 2D array of Coordinates and generates neighbors
	 * for each coordinate.
	 */
	public void generateGrid() {
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid.length; j++) {
				grid[i][j] = new Coordinate(i, j);
			}
		}

		// generate the neighbors
		// initially i had the call inside of the previous nested for loop
		// but that generated errors.
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid.length; j++) {
				generateNeighbors(grid[i][j]);
			}
		}
	}

	/**
	 * generateNeighbors() generates all the neighboring coordinates to the
	 * given coordinate
	 * 
	 * @param c
	 *            - the given coordinate
	 */
	public void generateNeighbors(Coordinate c) {
		if (c.getX() - 1 >= 0) { // determine western neighbor
			c.addNeighbor(grid[c.getX() - 1][c.getY()]);
		}
		if (c.getX() + 1 < grid.length) { // determine eastern neighbor
			c.addNeighbor(grid[c.getX() + 1][c.getY()]);
		}
		if (c.getY() - 1 >= 0) { // determine northern neighbor
			c.addNeighbor(grid[c.getX()][c.getY() - 1]);
		}
		if (c.getY() + 1 < grid.length) { // determine southern neighbor
			c.addNeighbor(grid[c.getX()][c.getY() + 1]);
		}
	}

	/**
	 * generateMaze() creates the maze as described in the instructions.
	 */
	public void generateMaze() {
		// choose the starting cell and call it current cell.
		currentCell = grid[0][0];
		visitedCells = 1;
		while (visitedCells < totalCells) {
			// find all neighbors of CurrentCell with all walls intact
			ArrayList<Coordinate> walledNeighbors = new ArrayList<Coordinate>();
			for (Coordinate neighbor : currentCell.getNeighbors()) {
				// System.out.println("XY:" + neighbor.getX() + ", " +
				// neighbor.getY());
				if (neighbor.closedCell == true) {
					walledNeighbors.add(neighbor);
				}
			}
			// if one or more found choose one at random
			if (walledNeighbors.size() >= 1) {
				Random r = new Random();
				// not sure if this works, but got the part inside nextInt()
				// from the instructions, not sure if it should be here
				// Coordinate next =
				// walledNeighbors.get(r.nextInt((int)(myrandom() *
				// walledNeighbors.size())));
				Coordinate next = walledNeighbors.get(r.nextInt(walledNeighbors.size()));
				// knock down the wall between it and CurrentCell
				// first have to find which side of the currentCell the neighbor
				// is on
				if (currentCell.getX() < next.getX()) { // eastern
					currentCell.removeEWall();
					currentCell.addPath(next); // connect them in the graph
					next.removeWWall();
					currentCell.addNoWallNeighbors(next);
				} else if (currentCell.getX() > next.getX()) { // western
					currentCell.removeWWall();
					currentCell.addPath(next);
					next.removeEWall();
					currentCell.addNoWallNeighbors(next);
				} else if (currentCell.getY() > next.getY()) { // northern
					currentCell.removeNWall();
					currentCell.addPath(next); // connect them in the graph
					next.removeSWall();
					currentCell.addNoWallNeighbors(next);
				} else { // southern
					currentCell.removeSWall();
					currentCell.addPath(next); // connect them in the graph
					next.removeNWall();
					currentCell.addNoWallNeighbors(next);
				}

				// push CurrentCell location on the CellStack
				cellStack.push(currentCell);
				// make the new cell CurrentCell
				currentCell = next;
				// add 1 to VisitedCells
				visitedCells++;
			} // end if
			else {
				// pop the most recent cell entry off the CellStack
				// make it CurrentCell
				currentCell = cellStack.pop();

			} // end else

		}

	}

	/**
	 * displayMaze() displays a visual image of the maze
	 */
	public void displayMaze() {

		// always have the starting and the ending point be set
		grid[0][0].northernWall = false;
		grid[grid.length - 1][grid.length - 1].southernWall = false;

		for (int y = 0; y < grid.length; y++) {
			// horizontal lines
			for (int x = 0; x < grid.length; x++) {
				Coordinate node = grid[x][y];
				if (node.northernWall == true) { // if it has a northern wall
					if (x == grid[0].length - 1) { // add an extra plus at the
													// end
						System.out.println("+-+");
					} else {
						System.out.print("+-");
					}
				} else { // no northern wall
					if (x == grid[0].length - 1) { // add an extra plus at the
													// end
						System.out.println("+ +");
					} else {
						System.out.print("+ ");
					}
				}
			}

			// vertical lines
			for (int x = 0; x < grid.length; x++) {
				if (grid[x][y].westernWall == true) { // if it has a eatsern
														// wall
					if (x == grid[0].length - 1) { // add an extra | at the end
						if (grid[x][y].order != -1) { //if it was visited 
							System.out.printf("|%d|\n", grid[x][y].order);
						}
						else {
							System.out.println("| |");
						}
						
					} else {
						if (grid[x][y].order != -1) { //if it was visited 
							System.out.printf("|%d", grid[x][y].order);
						}
						else {
							System.out.print("| ");
						}
					}
				} else { // no eastern wall
					if (x == grid[0].length - 1) { // add an extra | at the end
						if (grid[x][y].order != -1) { //if it was visited 
							System.out.printf(" %d|\n", grid[x][y].order);
						}
						else {
							System.out.println("  |");
						}
					} else {
						if (grid[x][y].order != -1) { //if it was visited  
							System.out.printf(" %d", grid[x][y].order);
						}
						else {
							System.out.print("  ");
						}
					}
				}
			}

		}

		// now take care of the bottom border
		for (int x = 0; x < grid[0].length; x++) {
			if (grid[x][grid.length - 1].southernWall == true) { // if it has a
																	// northern
																	// wall
				if (x == grid[0].length - 1) { // add an extra plus at the end
					System.out.println("+-+");
				} else {
					System.out.print("+-");
				}
			} else { // no northern wall
				if (x == grid[0].length - 1) { // add an extra plus at the end
					System.out.println("+ +");
				} else {
					System.out.print("+ ");
				}
			}
		}
	} // end of display maze

	/**
	 * Solves the maze in BFS
	 * @param c
	 * @author Adham Kamel and Sebrianne Ferguson
	 */
	public boolean solveMazeBFS(Coordinate c) {
		Queue<Coordinate> toExplore = new LinkedList<>();
		Coordinate exit = grid[grid.length - 1][grid.length - 1];
		marked.add(c); //add to the list of visited nodes
		toExplore.add(c); //add to the linked list
		
		c.order = 0;
		int counter = 1; //for displaying all of the steps
		
		while (!toExplore.isEmpty()) { //while there is still stuff in the queue
			Coordinate current = toExplore.remove(); //dequeue the element
			
			if (current.equals(exit)){ //check to see if you've gotten to the end
				this.displayMaze();
				return true;
			}
			
			for (Coordinate neighbor : current.getNeighbors()) { //changed from getNoWallNeighbors()
				
				if (!marked.contains(neighbor)) {
					
					//check to see if there is actually a knocked down wall between the 2
					//north 
					if (current.northernWall == false && current.neighborType(neighbor) == Direction.NORTH) {
						if (current.getX() != 0 && current.getY() != 0) { //youre not at the starting point
							toExplore.add(neighbor);
							marked.add(neighbor);
							neighbor.order = counter % 10;
							counter++;
						}
					}
					
					//next check the east
					else if (current.easternWall == false && current.neighborType(neighbor) == Direction.EAST) {
						toExplore.add(neighbor);
						marked.add(neighbor);
						neighbor.order = counter % 10;
						counter++;
					}
					
					//next check the south
					else if (current.southernWall == false && current.neighborType(neighbor) == Direction.SOUTH) {
						toExplore.add(neighbor);
						marked.add(neighbor);
						neighbor.order = counter % 10;
						counter++;
					}
					
					//next check the west
					else if (current.westernWall == false && current.neighborType(neighbor) == Direction.WEST) {
						toExplore.add(neighbor);
						marked.add(neighbor);
						neighbor.order = counter % 10;
						counter++;
					}
					
				}
			}

		}
		
		
		return false;
	}

	/**
	 * Solves the maze in DFS
	 * @param c
	 * @author Adham Kamel
	 */
	public boolean solveMazeDFS(Coordinate c) {
		Coordinate exit = grid[grid.length - 1][grid.length - 1];
		if (c.equals(exit)) {
			return true;
		} else {
			marked.add(c);
			this.generateNeighbors(c);
			for (Coordinate neighbor : c.getNoWallNeighbors()) {
				System.out.print(neighbor.toString());
				if (solveMazeDFS(neighbor)) {
					return true;
				}

			}
		}
		return false;
	}
	
	/**
	 * @author Adham Kamel
	 * @return the start coordinate
	 */
	public Coordinate getStart(){
		return grid[0][0];
	}

	public static void main(String[] args) {
		Maze m = new Maze(3);
		m.generateGrid();
		m.generateMaze();
		//m.displayMaze();
		m.solveMazeBFS(m.getStart());
		//m.solveMazeDFS(m.getStart());
		//m.displayMaze();
	}

}