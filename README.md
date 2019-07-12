# Vector Drawing Tool
Vector Drawing Tool form Java 

This is a assignment for QUT CAB302 Unit.


## Project Team

  AIDAN PERERA @ruchaidandev,
  OSCAR LI @Oscar4351315,
  NOEL MARTINEZ,
  JOSHUA PINALLI @J-markp
  
## Vector Tool Application
This project consists of two main packages, which are the GUI and VEC packages.  Package VEC contains four classes, which consist of Command, Importing, Saving, and VEC. Package GUI contains DrawingCanvas, Ellipse, Line, Plot, Polygon, Rectangle, Shape, and VectorTool. More documentation can be found in the JavaDoc folder. The unit tests for the project was placed under the Testing folder.

    PLOT,
    LINE,
    RECTANGLE,
    ELLIPSE,
    POLYGON (3 edges to many)

### Functionality
Functionality | Description |
--- | --- |
Loading VEC images |	Has appropriate file open dialog, allows users to navigate their file system. Only .VEC files are displayed by default |
Plot |	Draws dot, 1 click = 1 dot |
Draw line |	One click, drag release |
Draw rectangle |	One click, drag release |
Draw ellipse |	One click, drag release |
Draw polygon |	Enter the number of points in the textbox in the toolbar, then click the number of times to draw the polygon. Undo	Uses
Ctrl+z | Can be used repeatedly until the image is blank |
Saving VEC images |	Able to select the path |
Undo support |	Has the list of actions to undo in the right-hand panel. (Similar to Adobe Photoshop) |
Multi-image support |	Creates new canvas, does not replace the current image. (Tab control) Can work on it separately |
BMP export |	Able to select the path. |
Redo actions |	Can be used repeatedly until it comes to the last done action. Ctrl + y |

#### VEC package:
The VEC class represents the VEC file and has a constructor VEC that takes in an ArrayList of Command as a parameter. It also has getter and setter methods used to retrieve and update variable commands. The Command class represents the VEC file command; it has a default constructor that takes no parameters and another one that takes a String, and an ArrayList of Strings as parameters. The class also has its getter and setter methods for variables command and parameters and overrides default toString so that it creates the string (of commands) in a.VEC like format. It has a method createShape(), which determines if the command received contains a command for any shape (or plot), and creates a new instance of the shape. The importing class mainly contains a static method that takes the String of the filePath as a parameter and saves the selected file to a VEC instance. The last class saving contains the static method to save a VEC instance to a VEC file. This is done by taking the ArrayList of Command and java.io.File as parameters, format the commands into .VEC format and saving it.

#### GUI package:
The first class to mention is the Shape class, which is abstract and stores static int of the canvas height and width. All the methods are getters and setters. Classes that inherit Shape using the keyword extends are Ellipse, Line, Plot, Polygon, and Rectangle. Class Plot has constructor Plot that takes double x, and doubly y as a parameter, methods getData() retrieves data for the graphics package and returns int array of the data x,y respectively. getDataPolygon() overrides and gets the points of the polygon in the canvas as a 2-dimensional array.

Line class has a constructor that takes four doubles (x1,y1,x2,y2) as parameters. The getData() in Line returns an int array of the data x1,y1,x2,y2 respectively. Class Rectangle has a constructor that takes four doubles as parameters as well (x1, y1, width, height), and the getData() also retrieves data for the graphics package and returns int array of the data x1,y1, width, height respectively. Class Ellipse has the same constructor parameters as Rectangle (x1, y1, width, height all double), the getData() here is also the same, it returns int array of the data x1,y1, width, height respectively. Polygon has a constructor that takes two ArrayList<Double> and an integer as parameters. getData() in this class returns null but getDataPolygon() returns a 2D int array of data x[], and y[], as shape_data. VectorTool is the class that creates the GUI for the user to use. It has all the tabs/buttons and assigns actions to specific keys and listens for specific user inputs, doing an action corresponding to the input.

The last class is the DrawingCanvas, which is an essential class that connects the command instance so that it draws and show something. Every time a new page is created (or imported) through the use of VectorTool, a DrawingCanvas instance is created. The constructor also extracts an ArrayList of Command, through the getter method in VEC. The class inherits Jpanel and has a constructor that takes a VEC file as the parameter. The method paintComponent is overridden java.awt.Graphics as the parameter. What it does is that using the extracted ArrayList, it checks the drawing command and draws whatever it is. For example, if the first command is “RECTANGLE”, then the method will get the data (getData()) corresponding to a rectangle and draws based on values provided.

The main purpose for having a VEC instance with the ArrayList for panels was to make it easy to control the undo, redo and tab control. This procedure was then used for importing, exporting by passing the vec instance for each selected tab.



## How To Use the Application
The user manual will have instructions. (Run application > F1 or Help Menu Button)


## Dependencies
junit


Queensland University of Technology 2019

