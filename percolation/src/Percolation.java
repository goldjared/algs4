package src;
import java.lang.IllegalArgumentException;
import java.util.Arrays;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;
	
	public class Percolation {
		private int[][] grid;
		private WeightedQuickUnionUF network;
		private int gridSize;
		private int gridSizeSquared;
		private int numOpenSites = 0;
		private int virtualTop;
		private int virtualBottom;
		
		
		
		// creates n-by-n grid, with all sites initially blocked
		public Percolation(int n) {
			// Throw an IllegalArgumentException in the constructor if n ≤ 0
			if(n<=0) throw new IllegalArgumentException("n <= 0");
			
			this.grid = new int[n][n];
			// + 2 account for virtual top and bottom.
			this.network = new WeightedQuickUnionUF(n * n + 2);
			this.virtualTop = n*n;
			this.virtualBottom = n*n + 1;
			this.gridSize = n;
			
			// link virt top to top row, link virt bottom to bottom row.
			for(int i = 0; i<n;i++) {
				this.network.union(virtualTop, xyTo1d(0,i));
				this.network.union(virtualBottom, xyTo1d(n-1,i));
			}
			
		}
		
		private int xyTo1d(int x, int y) {
			// x * column length +1 + y
			return x*this.gridSize+y;
		}
		
		// opens the site (row, col) if it is not open already
		public void open(int row, int col) {
			if(row<0 || col<0) throw new IllegalArgumentException("n <= 0");
			int[][] testCases = {{row+1,col}, {row-1, col}, {row, col+1}, {row, col-1}};
			// if not open, open it
			if(!isOpen(row, col)) {
				this.grid[row][col] = 1;
				this.numOpenSites++;
				// if this spot is touch any of 4 other spot that are open, union them.
				for(int[] testCase : testCases) {
					// if adjacent piece is on, union them.
					if(testCase[0] >= 0 && testCase[0] < gridSize && testCase[1] >= 0 && testCase[1] < gridSize) {
						System.out.println("row: " +row + "col: " + col);
						if(isOpen(testCase[0], testCase[1])) {
							this.network.union(xyTo1d(row, col), xyTo1d(testCase[0], testCase[1]));
							this.numOpenSites--;
						}
					}

				}
				
			} else {
				System.out.println("Spot: ["+row+"],["+col+"] is already open.");
			}
		}
		//
//// is the site (row, col) open?
		public boolean isOpen(int row, int col) {
			if(row<0 || col<0) throw new IllegalArgumentException("n <= 0, row: " + row + "col: " +col);
			return this.grid[row][col] == 1;
		}
		//
//// is the site (row, col) full?
//// eg from this spot, is connecting to top?
		public boolean isFull(int row, int col) {
			if(row<0 || col<0) throw new IllegalArgumentException("n <= 0");
			if(!isOpen(row, col)) return false;
			
			// this would be better probably with check if this spot's root, equal virtTop root.
			// if virt top root, equal the root of [row,col]
			return this.virtualTop == this.network.find(xyTo1d(row,col));
		}
		//
//// returns the number of open sites
		public int numberOfOpenSites() { return numOpenSites; }
		//
//// does the system percolate?
		public boolean percolates() {
			// get last row in array, then for each item
			// check isFull, if one is true, stop return true (sys percolates)
			int lastRowIndex = grid.length-1;
			for(int i = 0; i< grid.length; i++) {
				if(isFull(lastRowIndex, i)) return true;
			}
			return false;
		}
		
		
		
		// test client (optional)
		public static void main(String[] args) {
			System.out.println("This is the test.");
			Percolation test1 = new Percolation(3);
			System.out.println(test1.isOpen(0,0) + "should be false, 0,0");
			System.out.println(test1.isOpen(1,0) + "should be false, 1,0");
			System.out.println(test1.isOpen(2,0) + "should be false, 2,0");
			test1.open(0,0);
			test1.open(1,0);
			test1.open(2,0);
			test1.open(2,0);
			System.out.println(test1.percolates() + "should be true for perco");
		}
	}
	
	

