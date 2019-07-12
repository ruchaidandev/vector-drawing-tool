package GUI;

/**
 * Rectangle shape inherits Shape class
 * @author Aidan Perera
 * @author Joshua Pinalli
 * @author Noel Martinez
 * @author Oscar Li
 * @version 1.2.1
 */
public class Rectangle extends Shape{
    /**
     * Rectangle properties
     * x1: the x1 position of the shape
     * y1: the y1 position of the shape
     * width: the width of the shape
     * height: the height of the shape
     */
    private double x1;
    private  double y1;
    private double width;
    private double height;

    /**
     * Rectangle constructor
     * @param x1 : x1 position
     * @param y1 : y1 position
     * @param width : width
     * @param height : height
     */
    public Rectangle(double x1, double y1, double width, double height) {

        // Converting to match the canvas size
        this.x1 = x1 * CANVAS_WIDTH;
        this.y1 = y1 * CANVAS_HEIGHT;
        this.width = (width - x1) * CANVAS_WIDTH;
        this.height = (height - y1) * CANVAS_HEIGHT;
    }

    /**
     * To retrieve data for the graphics package
     * @return int array of the data x1, y1, width, height respectively
     */
    public int[] getData() {


        int[] shape_data = { (int)x1, (int)y1, (int)width, (int)height};

        return shape_data;
    }

    @Override
    public int[][] getDataPolygon() {
        return new int[0][];
    }



}
