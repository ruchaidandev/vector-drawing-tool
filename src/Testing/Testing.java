package Testing;

import VEC.*;
import GUI.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.opentest4j.AssertionFailedError;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;


public class Testing {

    Command Command;
    VEC VEC;

    @BeforeEach
    //resets to default settings
    public void setupVEC() {
        Command = null;

    }

    // testing creating of default constructor of Command
    @Test
    public void testCommandConstructor(){
        Command = new Command();
        ArrayList<String> parameters = new ArrayList<String>();

        assertEquals(null ,Command.getCommand());
        assertEquals(parameters ,Command.getParameters());
    }

    @Test
    // testing creating of constructor of Command
    public void testCommandConstructor2(){
        ArrayList<String> parameters = new ArrayList<String>();
        parameters.add("0.1");
        parameters.add("0.1");
        parameters.add("0.9");
        parameters.add("0.9");
        Command = new Command("LINE", parameters);


        assertEquals("LINE" ,Command.getCommand());
        assertEquals(parameters ,Command.getParameters());

        Assertions.assertThrows(AssertionFailedError.class,
                ()->{
                    // assertion here has to be wrong
                    assertEquals("LINEE" ,Command.getCommand());
                });
        Assertions.assertThrows(NullPointerException.class,
                ()->{
                    // command is wrong because it does not check for "LINEE"
                    // createShape() does not create instance of shape, resulting in NullPointerException
                    Command = new Command("LINEE", parameters);
                    Command.createShape();
                    Command.getShape().getData();
                });
    }

    @Test
    // testing more getter and setters of Command
    public void testCommandGetSet(){
        Command = new Command();
        ArrayList<String> parameters = new ArrayList<String>();
        parameters.add("0.11");
        parameters.add("0.11");
        parameters.add("0.987");
        parameters.add("0.987");

        Command.setCommand("POLYGON");
        assertEquals("POLYGON" ,Command.getCommand());

        Command.setCommand("RECTANGLE");
        assertEquals("RECTANGLE" ,Command.getCommand());

        // testing Command method - addParameters()
        Command.addParameters("0.11");
        Command.addParameters("0.11");
        Command.addParameters("0.987");
        Command.addParameters("0.987");

        assertEquals(parameters ,Command.getParameters());
    }

    @Test
    //testing addParameters
    public void testAddParameters(){
        Command = new Command();
        ArrayList<String> parameters = new ArrayList<String>();
        Command.setCommand("RECTANGLE");
        Command.addParameters("0.11");
        Command.addParameters("0.11");
        Command.addParameters("0.987");
        Command.addParameters("0.987");

        assertEquals("0.11",Command.getParameters().get(1));
        assertEquals("0.987",Command.getParameters().get(2));
    }

    @Test
    // testing toSring() of Command
    public void testCommandToString(){
        Command = new Command();
        Command.setCommand("RECTANGLE");
        Command.addParameters("0.11");
        Command.addParameters("0.11");
        Command.addParameters("0.987");
        Command.addParameters("0.987");

        assertEquals("Command{command='RECTANGLE', parameters=[0.11, 0.11, 0.987, 0.987]}" ,Command.toString());
    }

    @Test
    // testing method getShape() and createShape
    public void testShapeInCommand(){
        Command = new Command();
        Command.setCommand("LINE");
        Command.addParameters("0.1");
        Command.addParameters("0.1");
        Command.addParameters("0.9231213");
        Command.addParameters("0.9231213");


        Line Line = new Line(0.1,0.1,0.9231213,0.9231213);
        Command.createShape();
        assertEquals(Line.getClass() ,Command.getShape().getClass());

        Assertions.assertThrows(AssertionFailedError.class,
                ()->{
                    Command.setCommand("RECTANGLE");
                    Command.createShape();
                    assertEquals(Line.getClass() ,Command.getShape().getClass());
                });

        Assertions.assertThrows(NullPointerException.class,
                ()->{
                    Command C2 = new Command();
                    C2.setCommand("QWEQWEQWE");
                    C2.createShape();
                    C2.getShape().getData();
                });
    }

    @Test
    // Test if createShape Polygon takes in proper arguments
    public void testCreateShapePolygon(){
        Command = new Command();
        Command.setCommand("POLYGON");
        Command.addParameters("0.3");
        Command.addParameters("0.3");
        Command.addParameters("0.5");
        Command.addParameters("0.7");
        Command.addParameters("0.8");
        Command.addParameters("0.2");
        Command.addParameters("0.3");
        Command.addParameters("0.3");

        ArrayList<Double> x = new ArrayList<Double>();
        ArrayList<Double> y = new ArrayList<Double>();
        x.add(0.3);
        x.add(0.5);
        x.add(0.8);
        x.add(0.3);
        y.add(0.3);
        y.add(0.7);
        y.add(0.2);
        y.add(0.3);

        Command.createShape();
        Polygon Polygon = new Polygon(x,y,4);

        assertEquals(Polygon.getClass(), Command.getShape().getClass());
        assertEquals(Arrays.toString(Polygon.getDataPolygon()[0]), Arrays.toString(Command.getShape().getDataPolygon()[0]));
        assertEquals(Arrays.toString(Polygon.getDataPolygon()[1]), Arrays.toString(Command.getShape().getDataPolygon()[1]));
        assertEquals(Arrays.toString(Polygon.getDataPolygon()[2]), Arrays.toString(Command.getShape().getDataPolygon()[2]));

    }

    @Test
    // Test class VEC, constructor and getter
    public void testVEC(){
        Command c1= new Command();
        c1.setCommand("PLOT");
        c1.addParameters("0.1");
        c1.addParameters("0.1");

        Command c2 = new Command();
        c2.setCommand("PLOT");
        c2.addParameters("0.512312315");
        c2.addParameters("0.512312315");

        ArrayList<Command> commands = new ArrayList<Command>();
        commands.add(c1);
        commands.add(c2);

        VEC = new VEC(commands);

        assertEquals(commands, VEC.getCommands());
    }
}
