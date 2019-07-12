package GUI;

/**
 * Plot inherits Shape class
 * @author Aidan Perera
 * @author Joshua Pinalli
 * @author Noel Martinez
 * @author Oscar Li
 * @version 1.2.1
 */
public class Plot extends Shape {
    /**
     * Plot properties
     * x: x position
     * y: y position
     */
    private double x;
    private  double y;

    /**
     * Constructor of the Plot shape
     * @param x: x position
     * @param y: y position
     */
    public Plot(double x, double y) {
        // Converting to match the canvas size
        this.x = x * CANVAS_WIDTH;
        this.y = y * CANVAS_HEIGHT;
    }

    /**
     * To retrieve the data for the graphics package
     * @return int array of the data x,y respectively
     */
    public int[] getData() {
        int[] shape_data = { (int)x, (int)y};

        return shape_data;
    }

    @Override
    public int[][] getDataPolygon() {
        return new int[0][];
    }


}
