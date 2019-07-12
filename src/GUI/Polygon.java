package GUI;

import java.util.ArrayList;

/**
 * Polygon shape inherits Shape class
 * @author Aidan Perera
 * @author Joshua Pinalli
 * @author Noel Martinez
 * @author Oscar Li
 * @version 1.2.1
 */
public class Polygon extends Shape{
    /**
     * Polygon properties
     * x: array of x positions
     * y: array of y positions
     * points: the number of points in the polygon
     */
    private ArrayList<Double> x;
    private ArrayList<Double> y;
    private int points;

    /**
     * Constructor for the polygon shape
     * @param x_array: x positions ArrayList
     * @param y_array: y positions ArrayList
     * @param points: number of points in the shape
     */
    public Polygon(ArrayList<Double> x_array, ArrayList<Double> y_array, int points) {
        x = new ArrayList<>();
        y = new ArrayList<>();
        this.points = points;
        for (int counter = 0; counter < points; counter++){
            double x_temp = x_array.get(counter) * CANVAS_WIDTH;
            double y_temp = y_array.get(counter) * CANVAS_WIDTH;
            this.x.add(x_temp);
            this.y.add(y_temp);
        }

    }

    public int[] getData() {
        return null;
    }

    /**
     * To retrieve the data for the graphics package
     * @return 2D int array of the data, x[], y[], [point]: 0th index will be the points value
     */
    public int[][] getDataPolygon(){
        int[] x_array = new int[points];
        int[] y_array = new int[points];

        // Looping the x, y arrays to format it to the canvas size
        for (int counter = 0; counter < points; counter++){
            x_array[counter] = x.get(counter).intValue();
            y_array[counter] = y.get(counter).intValue();
        }

        // Adding all values to the int array
        int[][] shape_data = {
                x_array,
                y_array,
                {points}
                };

        return shape_data;
    }

}
