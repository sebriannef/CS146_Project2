package project2;
/**
 * @authors Sebrianne Ferguson and Adham Kamel
 * test class for the maze
 * still need to incorporate the seed and probably assert statements as well
 */

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MazeTest {

	@Test
	void test() {
		//BFS test
		Maze maze1 = new Maze(3);
		maze1.generateGrid();
		maze1.generateMaze();
		maze1.displayMaze();
		maze1.solveMazeBFS(maze1.getStart());
		maze1.displayHashtag();
		
		//DFS Test
		Maze maze2 = new Maze(3);
		maze2.generateGrid();
		maze2.generateMaze();
		maze2.displayMaze();
		maze2.solveMazeDFS(maze2.getStart());
		maze2.displayHashtag();
	}

}
