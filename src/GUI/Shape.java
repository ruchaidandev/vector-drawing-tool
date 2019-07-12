package GUI;

/**
 * Shape Abstract Class, used to create the shapes and store all its properties
 * @author Aidan Perera
 * @author Joshua Pinalli
 * @author Noel Martinez
 * @author Oscar Li
 * @version 1.2.1
 */
public abstract class Shape {

    /**
     * Shape properties stores the the width and the height for drawing
     */
    public static int CANVAS_WIDTH = 500;
    public static int CANVAS_HEIGHT = 500;

    /**
     * Default Constructor of the Shape class
     */
    public Shape(){

    }

    /**
     * Abstract method to getData from the shape, which will be
     * formatted according to the canvas
     * @return Int array of the data
     */
    abstract public int[] getData();

    /**
     * Abstract method to getDataPolygon from the polygon, which will be
     * formatted according to the canvas and will have all points for the polygon
     * @return 2D int array of the data
     */
    public abstract int[][] getDataPolygon();


    /**
     * To get the canvas width value
     * @return int canvas width
     */
    public static int getCanvasWidth() {
        return CANVAS_WIDTH;
    }

    /**
     * To set the canvas width value
     * @param canvasWidth: int
     */
    public static void setCanvasWidth(int canvasWidth) {
        CANVAS_WIDTH = canvasWidth;
    }

    /**
     * To get the canvas height value
     * @return int canvas height
     */
    public static int getCanvasHeight() {
        return CANVAS_HEIGHT;
    }

    /**
     * To set the canvas height value
     * @param canvasHeight: int
     */
    public static void setCanvasHeight(int canvasHeight) {
        CANVAS_HEIGHT = canvasHeight;
    }


}
