package GUI;

/**
 * Plot inherits Shape class
 * @author Aidan Perera
 * @author Joshua Pinalli
 * @author Noel Martinez
 * @author Oscar Li
 * @version 1.2.1
 */
public class Line extends Shape {
    /**
     * Line properties
     * x1: x1 position of the shape
     * y1: y1 position of the shape
     * x2: x2 position of the shape
     * y2: y2 position of the shape
     */
    private double x1;
    private  double y1;
    private double x2;
    private double y2;

    /**
     * Line constructor
     * @param x1: x1 posiiton
     * @param y1: y1 position
     * @param x2: x2 position
     * @param y2: y2 position
     */
    public Line(double x1, double y1, double x2, double y2) {
        // Converting to match the canvas size
        this.x1 = x1 * CANVAS_WIDTH;
        this.y1 = y1 * CANVAS_HEIGHT;
        this.x2 = x2 * CANVAS_WIDTH;
        this.y2 = y2 * CANVAS_HEIGHT;
    }

    /**
     * To retrieve data for the graphics package
     * @return int array of the data x1, y1, x2, y2 respectively
     */
    public int[] getData() {
        int[] shape_data = { (int)x1, (int)y1, (int)x2, (int)y2};

        return shape_data;
    }

    @Override
    public int[][] getDataPolygon() {
        return new int[0][];
    }


}
