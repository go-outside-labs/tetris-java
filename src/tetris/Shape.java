/* The Shape Class for the Awesome Tetris
 *  
 *  Purpose of this class: 
 *  	-> create the pieces's seven shape types
 *  	-> The way we define the pieces is by their coordinates
 *  				inside a coordinates Table.
 *
 * @author Mari & Samer
 * @version 1.0 Jul/14
 * 
 * TO DO:
 * 
 */

package tetris;

import java.util.Random;
import java.lang.Math;


public class Shape {

	/*
	 * Here we define all the possible piece shapes.
	 * 
	 * An enum type is a data type that enables 
	 * for a variable to be a set of predefined constants. 
	 * 
	 * Unlike in C, enum is a full class
	 * 
	 */
    enum Pieces { 		NoShape, 
    					ZShape, 
    					SShape, 
    					LineShape, 
    					TShape, 
    					SquareShape, 
    					LShape, 
    					MirrorLShape };

    private Pieces pieceShape;
    private int coords[][];
    private int[][][] coordsTable;

    

    public Shape() {

    	/*
    	 * Define the dimensions for coords
         * Since all the pieces have a limit in 4 spaces in X
         * and 2 in Y, it fills the piece's coordinates properly.
         */
        this.coords = new int[4][2];
        
        
        /*
         * Here we define the coordinate table that includes all the possible
         * shapes for each piece. It gets a value x and select from the coordsTable
         * 
         * Z: { -1, 1 },  { 0, 1 },   { 0, 0 },  { 0, 1 }
         *                       |
         *                       2
         *                       |
         *                    X  X
         *                       |
         *               --2--1--X--X--2----
         *                       |
         *                       1
         *                       |
         *                       2
         *                       
         *                       
         *  S: { 0, -1 },  { 0, 0 },   { 1, 0 },   { 1, 1 }                                   |
         *                       2
         *                       |
         *                       1  X
         *                       |
         *               --2--1--X--X--2----
         *                       |
         *                       X
         *                       |
         *                       2
         * 
         * Line: { 0, -2 },  { 0, -1 },   { 0, 0 },   { 0, 1 }
         *                       2
         *                       |
         *                       X  
         *                       |
         *               --2--1--X--1--2----
         *                       |
         *                       X
         *                       |
         *                       X
         * 
         * T: { { -1, 0 },  { 0, 0 },   { 1, 0 },   { 0, -1 } }
         *                       2
         *                       |
         *                       1  
         *                       |
         *               --2--X--X--X--2----
         *                       |
         *                       X
         *                       |
         *                       2
         *  
         * Square: { { 0, 0 },   { 1, 0 },   { 0, 1 },   { 1, 1 } }
         *                       2
         *                       |
         *                       X  X
         *                       |
         *               --2--1--X--X--2----
         *                       |
         *                       1
         *                       |
         *                       2
         * 
         *  L: { { 0, 1 }, { 0, 0 },  { 0, -1 },   { 1, -1 } }
         *                       2
         *                       |
         *                       X   
         *                       |
         *               --2--1--X--1--2----
         *                       |
         *                       X  X
         *                       |
         *                       2
         *  
         *  Mirror L: { { 0, 1 }, { 0, 0 },  { 0, -1 },   { -1, -1 } }  
         *                       2
         *                       |
         *                       X  
         *                       |
         *               --2--1--X--1--2----
         *                       |
         *                    X  X
         *                       |
         *                       2
         * 
         */
        this.coordsTable = new int[][][] {
                { { 0, 0 },   { 0, 0 },   { 0, 0 },   { 0, 0 } }, // no shape
                { { -1, 1 },  { 0, 1 },   { 0, 0 },   { 0, 1 } }, // z
                { { 0, -1 },  { 0, 0 },   { 1, 0 },   { 1, 1 } }, // s
                { { 0, -2 },  { 0, -1 },  { 0, 0 },   { 0, 1 } }, // line
                { { -1, 0 },  { 0, 0 },   { 1, 0 },   { 0, -1 } }, // T
                { { 0, 0 },   { 1, 0 },   { 0, 1 },   { 1, 1 } }, // square
                { { 0, 1 },   { 0, 0 },  { 0, -1 },   { 1, -1 } },  // L
                { { 0, 1 },   { 0, 0 },  { 0, -1 },   { -1, -1 }}  // mirror L
            };
        
    	// NoShape has no coordinates (initialize)
        this.setShape(Pieces.NoShape);

    }

    

    /*
     * Set Methods: coordinates and shape
     */
    
    public void setShape(Pieces shape) {      
        /*
         * The java.lang.Enum.ordinal() method returns the ordinal 
         * of this enumeration constant (its position in its enum declaration, 
         * where the initial constant is assigned an ordinal of zero).
         */
        for (int c = 0; c < 4 ; c++) {
            for (int xy = 0; xy < 2; ++xy) {
            	// i for each of the four {}, j for the x,y inside {}
                this.coords[c][xy] = this.coordsTable[shape.ordinal()][c][xy];
                //System.out.println(coords[j]); --> objects, doesnt work
            }
        }
        
        // We have a shape!!!!
        this.pieceShape = shape;

    }

    private void setX(int idx, int x) 
    { 
    	this.coords[idx][0] = x; 
    }
    
    private void setY(int idx, int y) 
    { 
    	this.coords[idx][1] = y; 
    }
    
    
    
    /*
     * Get methods: coordinates and shapes
     */
    
    public Pieces getShape()  
    { 
    	return this.pieceShape; 
    }
    
    public int getX(int idx) 
    { 
    	return this.coords[idx][0]; 
    }
    
    public int getY(int idx) 
    { 
    	return this.coords[idx][1]; 
    }
    
    
    
    /*
     *  We use a pseudo-random function to select the first piece
     */
    public void setRandomShape()
    {
    	/*
    	 * We create a pseudo-random object and from this we generate
    	 * num as a int from 1 to 7 (possible shapes).
    	 * 
    	 * An instance of random class is used to generate a stream 
    	 * of pseudorandom numbers. The class uses a 48-bit seed.
    	 * 
    	 * nextInt() returns the next pseudorandom, uniformly distributed 
    	 * int value from this random number generator's sequence.
    	 */
        Random ran = new Random();
        //System.out.println("Testing random... " + ran);
        int num = Math.abs(ran.nextInt()) % 7 + 1;
        //System.out.println(ran.nextInt());
        //System.out.println( Math.abs(ran.nextInt()) % 7 + 1);
        //System.out.println(num);
        
        // instance a type pieces and set shape to it
        Pieces[] valueHere = Pieces.values(); 
        //System.out.println(valueHere); // whats Ltetris.Shape$Pieces?
        setShape(valueHere[num]);
    }

    
    /*
     * These are the minimum x, y coordinates for the piece.
     * Without this, the piece would go beyond the wall and
     * would not stop at the bottom
     */
    public int minX()
    {
      int lim = this.coords[0][0]; // [0] is all the x coordinates
      for (int i = 0; i < 4; i++) {
    	  lim = Math.min(lim, this.coords[i][0]); // [0] is all the x coordinates
      }
      return lim;
    }


    public int minY() 
    {
      int lim = this.coords[0][1];	// [1] is all the y coordinates
      for (int i = 0; i < 4; i++) {
    	  lim = Math.min(lim, this.coords[i][1]); // [1] is all the y coordinates
      }
      return lim;
    }

    
    /*
     * These methods to rotate the pieces.
     * 
     *  Suggestion from Samer:
     *  Every time you move the piece, you are actually creating
     *  a new pice in the new coordinates
     *  
     *  Rotating is the same as inverting all X to be Y and vice versa
     *  
     *  For example, if we hava a L:
     *    L: { { 0, 1 }, { 0, 0 },  { 0, -1 },   { 1, -1 } }
     *                       2
     *                       |
     *                       X   				X
     *                       |
     *               --2--1--X--1--2----  or    X  
     *                       |		
     *                       X  X				X  X
     *                       |
     *                       2
     *                       
     *  In coord array [i][j], i defines the four {x,y} and j the values for x or y   
     *  If we rotate once, for each idx from 1 to 4:
     *     setX(idx, - x) --> coords[idx][0] = x
     *         where x is getY(idx) --> coords[idx][1]
     *  (and same for setY but without -).
     *   
     *  So the new coordinates become:
     *         { -1, 0 }, { 0, 0 },  { 1, 0 },   { 1, 1 }
     *                       2
     *                       |
     *                       1  X   
     *                       |						  X
     *               --2--X--X--X--2----   or   X  X  X 
     *                       |
     *                       1  
     *                       |
     *                       2               
     * 
     *  Rotating once more:
     *         { 0, -1 }, { 0, 0 },  { 0, 1 },   { -1, 1 }
     *                       2
     *                       |
     *                    X  X   
     *                       |				   X  X
     *               --2--1--X--1--2----   or     X 
     *                       |					  X	
     *                       X  
     *                       |
     *                       2    
     *                           
     *  Rotating once more:
     *         { 1, 0 }, { 0, 0 },  { -1, 0 },   { -1, -1 }
     *                       2
     *                       |
     *                       1   
     *                       |				   
     *               --2--X--X--X--2----   or     X X X
     *                       |					  X	
     *                    X  1  
     *                       |
     *                       2  
     *                       
     *  Rotating once more we return to the origin:
     *         { 0, 1 }, { 0, 0 },  { 0, -1 },   { 1, -1 }
     *                       2
     *                       |
     *                       X   
     *                       |				      X
     *               --2--1--X--1--2----   or     X  
     *                       |					  X X    	
     *                       X  X
     *                       |
     *                       2    
     *                       
     *   what would happen if we did not inverted with -?
     *   
     *  Using the same example for L, we would have:
     *         { 1, 0 }, { 0, 0 },  { -1, 0 },   { -1, 1 }
     *                       2
     *                       |
     *                    X  1   
     *                       |				     X 
     *               --2--X--X--X--2----   or    X X X   
     *                       |					     	
     *                       1  
     *                       |
     *                       2   
     *                       
     *    We would invert the PARITY!!!!                                                                        
     */

    
    public Shape rotate() 
    {
    	// square pieces don't rotate
        if (this.pieceShape == Pieces.SquareShape){
            return this;
        }

        // create new piece
        Shape result = new Shape();
        result.pieceShape = this.pieceShape;

        // set new coordinates to this new piece
        for (int idx = 0; idx < 4; ++idx) {
            result.setX(idx, -this.getY(idx));
            result.setY(idx, this.getX(idx)); 
        }
        
        return result;
    }
}