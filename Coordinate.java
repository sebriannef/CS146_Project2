package project2;

import java.util.ArrayList;

public class Coordinate {
	int x;
	int y;
	boolean northernWall;
	boolean southernWall;
	boolean easternWall;
	boolean westernWall;
	boolean closedCell;
	ArrayList<Coordinate> neighbors;
	
	/**
	 * Coordinate() ctor
	 * @param x position
	 * @param y position
	 */
	public Coordinate(int x, int y) {
		this.x = x;
		this.y = y;
		this.neighbors = new ArrayList<Coordinate>();
		northernWall = true;
		southernWall = true;
		easternWall = true;
		westernWall = true;
		closedCell = true;
	}
	
	/**
	 * getX()
	 * @return x value
	 */
	public int getX() {
		return this.x;
	}
	
	/**
	 * getY()
	 * @return y value
	 */
	public int getY() {
		return this.y;
	}
	
	/**
	 * addNeighbor()
	 * adds an outgoing node neighbor to this node's neighbors list.
	 * @param c
	 */
	public void addNeighbor(Coordinate c) {
		this.neighbors.add(c);
	}

}
