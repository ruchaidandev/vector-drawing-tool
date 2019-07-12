package GUI;

import VEC.Command;
import VEC.VEC;

import javax.swing.*;
import java.awt.Polygon;
import java.awt.*;
import java.util.ArrayList;

/**
 * Drawing Canvas, this is used to connect the Command instance to draw
 * @author Aidan Perera
 * @version 1.1
 */
public class DrawingCanvas extends JPanel {

    /**
     * Variables for the canvas
     */
    private ArrayList<Command> commands = null;
    private Graphics2D g2d;

    /**
     * Constructor mapping values to command
     * @param vecFile: VECFile object
     */
    public DrawingCanvas(VEC vecFile ) {
        ArrayList<Command> commands = vecFile.getCommands();
        this.commands = commands;
    }


    /**
     * This will be used to paint, overrides the canvas method
     * @param g Graphics
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Initiating the graphics 2D
        g2d = (Graphics2D)g;

        g2d.setColor(Color.WHITE);
        g2d.fillRect(0,0,Shape.getCanvasWidth(), Shape.getCanvasHeight());

        // Default values of the fill Colour and pen Colour
        Color penColour = Color.BLACK;
        Color fillColour = Color.WHITE;
        g2d.setColor(penColour);
        // For each command will check the shape to draw
        for (Command cmd : commands) {
            // Retrieves the shape instance
            Shape shape = cmd.getShape();

            if (cmd.getCommand().equals("PEN")) {
                // PEN
                String hex = cmd.getParameters().get(0);
                penColour = Color.decode(hex);
                g2d.setColor(penColour);
            } else if (cmd.getCommand().equals("FILL")) {
                // FILL
                if (cmd.getParameters().get(0).equals("OFF")) {
                    fillColour = Color.WHITE;
                } else {
                    String hex = cmd.getParameters().get(0);
                    fillColour = Color.decode(hex);
                }
            } else if (cmd.getCommand().equals("ELLIPSE")) {
                // ELLIPSE
                int[] data = shape.getData();
                if (fillColour == Color.WHITE) {
                    g2d.drawOval(data[0], data[1], data[2], data[3]);
                } else {
                    g2d.setColor(fillColour);
                    g2d.fillOval(data[0], data[1], data[2], data[3]);
                    g2d.setColor(penColour);
                    // Checks whether it requires a border to draw
                    if(!penColour.equals(fillColour)){
                        g2d.setColor(penColour);
                        g2d.drawOval(data[0], data[1], data[2], data[3]);
                    }
                }

            }  else if (cmd.getCommand().equals("LINE")) {
                // LINE
                int[] data = shape.getData();
                g2d.drawLine(data[0], data[1], data[2], data[3]);


            }  else if (cmd.getCommand().equals("PLOT")) {
                // PLOT
                int[] data = shape.getData();
                g2d.drawOval(data[0], data[1],1 ,1);

            }  else if (cmd.getCommand().equals("POLYGON")) {
                // POLYGON
                int[][] data = shape.getDataPolygon();
                Polygon polygon = new Polygon(data[0], data[1], data[2][0] );
                if (fillColour == Color.WHITE) {

                    g2d.drawPolygon(polygon);
                } else {
                    g2d.setColor(fillColour);
                    g2d.fillPolygon(polygon);
                    g2d.setColor(penColour);
                    // Checks whether it requires a border to draw
                    if(!penColour.equals(fillColour)){
                        g2d.setColor(penColour);
                        g2d.drawPolygon(polygon);
                    }
                }

            }  else if (cmd.getCommand().equals("RECTANGLE")) {
                // RECTANGLE
                int[] data = shape.getData();
                if (fillColour == Color.WHITE) {
                    g2d.drawRect(data[0], data[1], data[2], data[3]);
                } else {
                    g2d.setColor(fillColour);
                    g2d.fillRect(data[0], data[1], data[2], data[3]);
                    g2d.setColor(penColour);
                    // Checks whether it requires a border to draw
                    if(!penColour.equals(fillColour)){
                        g2d.setColor(penColour);
                        g2d.drawRect(data[0], data[1], data[2], data[3]);
                    }

                }

            } else {
                // DEFAULT

            }


        }


    }



}
