//package sjsu.kamel.cs146.project2;

/**
 * @authors Sebrianne Ferguson and Adham Kamel
 * test class for the maze
 * still need to incorporate the seed and probably assert statements as well
 */

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class MazeTest {

	int graphSize4 = 4;
	int graphSize5 = 5;
	int graphSize6 = 6;
	int graphSize7 = 7;
	int graphSize8 = 8;
	int graphSize10 = 10;
	Maze maze4;
	Maze maze5;
	Maze maze6;
	Maze maze7;
	Maze maze8;
	Maze maze10;

	@Before
	public void setUpGraphs() {
		maze4 = new Maze(graphSize4);
		maze5 = new Maze(graphSize5);
		maze6 = new Maze(graphSize6);
		maze7 = new Maze(graphSize7);
		maze8 = new Maze(graphSize8);
		maze10 = new Maze(graphSize10);
	}

	@Test
	public void testSize4() {
		// BFS test
		System.out.println("Graph Size: " + graphSize4);
		maze4.generateGrid();
		maze4.generateMaze();
		System.out.println("Empty Maze:");
		maze4.displayMaze();
		System.out.println("\nBFS:");
		maze4.solveMazeBFS(maze4.getStart());
		System.out.println("");
		maze4.displayHashtag();

		// DFS test
		System.out.println("\nDFS:");
		maze4.solveMazeDFS(maze4.getStart());
		System.out.println("");
		maze4.displayHashtag();
		System.out.println("=================");
		System.out.println("Program Completed");
		System.out.println("=================\n");
	}

	@Test
	public void testSize5() {
		// BFS test
		System.out.println("Graph Size: " + graphSize5);
		maze5.generateGrid();
		maze5.generateMaze();
		System.out.println("Empty Maze:");
		maze5.displayMaze();
		System.out.println("\nBFS:");
		maze5.solveMazeBFS(maze5.getStart());
		System.out.println("");
		maze5.displayHashtag();

		// DFS test
		System.out.println("\nDFS:");
		maze5.solveMazeDFS(maze5.getStart());
		System.out.println("");
		maze5.displayHashtag();
		System.out.println("=================");
		System.out.println("Program Completed");
		System.out.println("=================\n");
	}

	@Test
	public void testSize6() {
		// BFS test
		System.out.println("Graph Size: " + graphSize6);
		maze6.generateGrid();
		maze6.generateMaze();
		System.out.println("Empty Maze:");
		maze6.displayMaze();
		System.out.println("\nBFS:");
		maze6.solveMazeBFS(maze6.getStart());
		System.out.println("");
		maze6.displayHashtag();

		// DFS test
		System.out.println("\nDFS:");
		maze6.solveMazeDFS(maze6.getStart());
		System.out.println("");
		maze6.displayHashtag();
		System.out.println("=================");
		System.out.println("Program Completed");
		System.out.println("=================\n");
	}

	@Test
	public void testSize7() {
		// BFS test
		System.out.println("Graph Size: " + graphSize7);
		maze7.generateGrid();
		maze7.generateMaze();
		System.out.println("Empty Maze:");
		maze7.displayMaze();
		System.out.println("\nBFS:");
		maze7.solveMazeBFS(maze7.getStart());
		System.out.println("");
		maze7.displayHashtag();

		// DFS test
		System.out.println("\nDFS:");
		maze7.solveMazeDFS(maze7.getStart());
		System.out.println("");
		maze7.displayHashtag();
		System.out.println("=================");
		System.out.println("Program Completed");
		System.out.println("=================\n");
	}

	@Test
	public void testSize8() {
		// BFS test
		System.out.println("Graph Size: " + graphSize8);
		maze8.generateGrid();
		maze8.generateMaze();
		System.out.println("Empty Maze:");
		maze8.displayMaze();
		System.out.println("\nBFS:");
		maze8.solveMazeBFS(maze8.getStart());
		System.out.println("");
		maze8.displayHashtag();

		// DFS test
		System.out.println("\nDFS:");
		maze8.solveMazeDFS(maze8.getStart());
		System.out.println("");
		maze8.displayHashtag();
		System.out.println("=================");
		System.out.println("Program Completed");
		System.out.println("=================\n");
	}

	@Test
	public void testSize10() {
		// BFS test
		System.out.println("Graph Size: " + graphSize10);
		maze10.generateGrid();
		maze10.generateMaze();
		System.out.println("Empty Maze:");
		maze10.displayMaze();
		System.out.println("\nBFS:");
		maze10.solveMazeBFS(maze10.getStart());
		System.out.println("");
		maze10.displayHashtag();

		// DFS test
		System.out.println("\nDFS:");
		maze10.solveMazeDFS(maze10.getStart());
		System.out.println("");
		maze10.displayHashtag();
		System.out.println("=================");
		System.out.println("Program Completed");
		System.out.println("=================\n");
	}
}
