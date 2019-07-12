package VEC;

import GUI.*;

import java.util.ArrayList;

/**
 * Represents the VEC file command
 * @author Aidan Perera
 * @version 1.0.1
 */
public class Command {
    /**
     * Command to draw
     */
    private String command;
    /**
     * Parameters for the command
     */
    private ArrayList<String> parameters;

    private Shape shape;

    /**
     * Default constructor for the command
     */
    public Command(){
        this.command = null;
        this.parameters = new ArrayList<String>();
    }

    /**
     * Constructor for the command
     * @param pCommand Command for the instance
     * @param pParameters Parameters for the command
     */
    public Command(String pCommand, ArrayList<String> pParameters){
        this.command = pCommand;
        this.parameters = pParameters;
        createShape();
    }

    /**
     * Retrieving the command for the instance
     * @return Command type
     */
    public String getCommand() {
        return command;
    }

    /**
     * Setting the command for the instance
     * @param command Command for the command
     */
    public void setCommand(String command) {
        this.command = command;
    }

    /**
     * Retrieving the parameters for the command
     * @return Parameters for the command
     */
    public ArrayList<String> getParameters() {
        return parameters;
    }

    /**
     * Adding parameters to the ArrayList
     * @param parameters Parameters in an String for the command
     */
    public void addParameters(String parameters) {
        this.parameters.add(parameters);
    }

    /**
     * To String method of the instance
     * @return string of the intance
     */
    @Override
    public String toString() {
        return "Command{" +
                "command='" + command + '\'' +
                ", parameters=" + parameters +
                '}';
    }

    /**
     * Returns the shape instance of the command
     * @return Shape: shape instance
     */
    public Shape getShape(){
        return shape;
    }

    /**
     * Creates the required shape instance for the given command
     */
    public void createShape(){

        // Checks the command to create the relevant shape
        // Converts all parameter attributes to double
        if( command.equals("LINE")) {

            shape = new Line(Double.parseDouble(parameters.get(0)),
                    Double.parseDouble(parameters.get(1)),
                    Double.parseDouble(parameters.get(2)),
                    Double.parseDouble(parameters.get(3)));

        }else if(command.equals("PLOT") ){
            shape = new Plot(Double.parseDouble(parameters.get(0)),
                    Double.parseDouble(parameters.get(1)));
        }else if(command.equals("RECTANGLE")) {
            shape = new Rectangle(Double.parseDouble(parameters.get(0)),
                    Double.parseDouble(parameters.get(1)),
                    Double.parseDouble(parameters.get(2)),
                    Double.parseDouble(parameters.get(3)));
        }else if(command.equals("ELLIPSE") ){
            shape = new Ellipse(Double.parseDouble(parameters.get(0)),
                    Double.parseDouble(parameters.get(1)),
                    Double.parseDouble(parameters.get(2)),
                    Double.parseDouble(parameters.get(3)));
        } else if(command.equals("POLYGON") ) {
            ArrayList<Double> x = new ArrayList<>();
            ArrayList<Double> y = new ArrayList<>();
            int points = 0;

            for(int count =0; count < parameters.size(); count += 2){
                x.add(Double.parseDouble(parameters.get(count)) );
                y.add(Double.parseDouble(parameters.get(count + 1)) );
                points++;
            }

            shape = new Polygon(x,y, points);
        }

    }


}
