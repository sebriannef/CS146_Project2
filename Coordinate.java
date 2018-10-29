package project2;

//package sjsu.kamel.cs146.project2;

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
	ArrayList<Coordinate> noWallNeighbors; //added by Adham -- not really needed but whatever 
	ArrayList<Coordinate> walledNeighbors;
	Coordinate path;
	//added by Sebrianne on 10/28
	int order;
	
	/**
	 * Coordinate() ctor
	 * @param x position
	 * @param y position
	 */
	public Coordinate(int x, int y) {
		this.x = x;
		this.y = y;
		this.order = -1;//gonna be used to display the order in search
		this.neighbors = new ArrayList<Coordinate>();
		this.walledNeighbors = new ArrayList<Coordinate>();
		this.noWallNeighbors = new ArrayList<Coordinate>(); //added by Adham
		this.path = null;
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
	
	/**
	 * addPath()
	 * @param neighbor - a node to add a pointer to
	 */
	public void addPath(Coordinate c) {
		this.path = c;
	}
	
	/**
	 * getNeighbors()
	 * @return neighbors of the node
	 */
	public ArrayList<Coordinate> getNeighbors() {
		return this.neighbors;
	}
	
	/**
	 * removeNWall()
	 * removes the northern wall
	 */
	public void removeNWall() {
		this.northernWall = false;
		this.closedCell= false;
	}
	
	/**
	 * removeSWall()
	 * removes the southern wall
	 */
	public void removeSWall() {
		this.southernWall = false;
		this.closedCell = false;
	}
	
	/**
	 * removeEWall()
	 * removes the eastern wall
	 */
	public void removeEWall() {
		this.easternWall = false;
		this.closedCell = false;
	}
	
	/**
	 * removeWWall()
	 * removes the western wall
	 */
	public void removeWWall() {
		this.westernWall = false;
		this.closedCell = false;
	}

	/**
	 * @author Adham Kamel
	 * Prints the XY coordinate in string format
	 */
	public String toString(){
		System.out.println("XY: " + this.getX() + ", " + this.getY());
		return "";
	}
	
	/**
	 * adds neighbor of the coordinate without a wall
	 * @author Adham Kamel
	 * @param c
	 */
	public void addNoWallNeighbors(Coordinate c){
		if (c.southernWall == false || c.northernWall == false || c.easternWall == false || c.westernWall == false){
			this.noWallNeighbors.add(c);
		}
	}
	
	/**
	 * @author Adham Kamel
	 * @return the coordinate without a wall connected to it
	 */
	public ArrayList<Coordinate> getNoWallNeighbors(){
		return this.noWallNeighbors;
	}
	
	
	/**added by Sebrianne Ferguson
	 * neighborType()
	 * @param c - neighbor coordinate
	 */
	public Direction neighborType(Coordinate c) {
		if (c.getX() > this.getX()) {
			return Direction.EAST;
		}
		else if (c.getX() < this.getX()) {
			return Direction.WEST;
		}
		else if (c.getY() < this.getY()) {
			return Direction.NORTH;
		}
		else if (c.getY() > this.getY()) {
			return Direction.SOUTH;
		}
		return Direction.NA; //shouldnt get to this point
	}

}