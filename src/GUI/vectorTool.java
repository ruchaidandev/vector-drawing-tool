package GUI;

import VEC.Command;
import VEC.Importing;
import VEC.Saving;
import VEC.VEC;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * VectorTool Class
 * This class will create the GUI for the application
 * @author Aidan Perera
 * @author Joshua Pinalli
 * @author Noel Martinez
 * @author Oscar Li
 * @version 2.3.1
 */
public class vectorTool {

    /**
     * GUI Variables
     */

    // JPanels
    private JPanel mainPanel;
    private JPanel statusBarPanel;
    private JTabbedPane tabbedPages;

    // JButtons
    private JButton paintButton;
    private JButton colourButton;
    private JButton clearPaintButton;
    private JButton clearToolButton;
    private JButton currentColourButton;
    private JButton currentToolButton;
    private JButton fillColourButton;
    private JButton currentFillColourButton;
    private JButton closeTabButton;
    private JButton noFillButton;

    // TextArea/ Panels/ Fields
    private JTextArea txtAreaVEC;
    private JList undoSupportList;
    private JTextField txtPointPolygon;

    // Labels
    private JLabel statusBarLabel;

    // Additional popups and dialogs
    private JColorChooser jcc;
    private JFileChooser fc;
    private static JMenuBar menuBar;
    private JPopupMenu toolPopUpMenu;

    /**
     * Application variables
     */
    private static int tabCount = 0;
    private static int scaleFactor = 1;
    private static int polygonPointCounter = 0;

    // Lists and arrays
    private static ArrayList<JPanel> panels;
    private ArrayList<VEC> panelVEC;
    private ArrayList<Command> commands_buffer;
    private static DefaultListModel undoList;

    private String currentTool;

    // Boolean values
    private static boolean isEnabled = false;
    private static boolean isFillColour = false;

    /**
     * Vector Tool GUI Implementation
     */
    public vectorTool() {
        /* Menu bar Items */
        JMenu fileMenu = new JMenu("File");
        JMenu editMenu = new JMenu("Edit");
        JMenu settingsMenu = new JMenu("Settings");
        JMenu helpMenu = new JMenu("Help");
        /* File Menu Items */
        JMenuItem newItem = new JMenuItem("New");
        fileMenu.add(newItem);
        JMenuItem importItem = new JMenuItem("Import");
        fileMenu.add(importItem);
        JMenuItem SaveItem = new JMenuItem("Save");
        fileMenu.add(SaveItem);
        JMenuItem Export = new JMenuItem("Export");
        fileMenu.add(Export);
        JMenuItem exitItem = new JMenuItem("Exit");
        fileMenu.add(exitItem);
        /* Edit Menu Items */
        JMenuItem undoItem = (new JMenuItem("Undo"));
        editMenu.add(undoItem);
        JMenuItem redoItem = (new JMenuItem("Redo"));
        editMenu.add(redoItem);
        JMenuItem zoomInItem = (new JMenuItem("Zoom In"));
        editMenu.add(zoomInItem);
        JMenuItem zoomOutItem = (new JMenuItem("Zoom Out"));
        editMenu.add(zoomOutItem);
        /* Settings Menu Items */
        JMenuItem readVECItem = (new JMenuItem("Read VEC"));
        settingsMenu.add(readVECItem);
        /* Help Menu Items */
        JMenuItem userGuideItem = (new JMenuItem("User Guide"));
        helpMenu.add(userGuideItem);
        /* Build Menu bar with menu items */
        menuBar = new JMenuBar();
        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(settingsMenu);
        menuBar.add(helpMenu);
        /*PopUP Menu For tools */
        toolPopUpMenu = new JPopupMenu();
        /* Pen tool */
        JMenuItem penItem = new JMenuItem("",
                new ImageIcon(getClass().getResource("/GUI/icons/pen.png")));
        /* Ellipse Tool */
        JMenuItem ellipseItem = new JMenuItem("",
                new ImageIcon(getClass().getResource("/GUI/icons/ellipse.png")));
        /* Polygon Tool */
        JMenuItem polyItem = new JMenuItem("",
                new ImageIcon(getClass().getResource("/GUI/icons/polygon.png")));
        /* Rectangle Tool */
        JMenuItem rectItem = new JMenuItem("",
                new ImageIcon(getClass().getResource("/GUI/icons/rect.png")));
        /* Line Tool  */
        JMenuItem lineItem = new JMenuItem("",
                new ImageIcon(getClass().getResource("/GUI/icons/line.png")));

        /* Plot Tool */
        JMenuItem plotItem = new JMenuItem("",
                new ImageIcon(getClass().getResource("/GUI/icons/plot.png")));
        /* Add JMenuItems to PopUpMenu */
        toolPopUpMenu.add(plotItem);
        toolPopUpMenu.add(lineItem);
        toolPopUpMenu.add(ellipseItem);
        toolPopUpMenu.add(polyItem);
        toolPopUpMenu.add(rectItem);

        /* Status Bar */
        statusBarPanel.setLayout(new BoxLayout(statusBarPanel, BoxLayout.X_AXIS));
        statusBarLabel = new JLabel(" Status Bar");
        statusBarLabel.setHorizontalAlignment(SwingConstants.LEFT);
        statusBarPanel.add(statusBarLabel);

        // Disabling the buttons
        paintButton.setEnabled(false);
        colourButton.setEnabled(false);
        fillColourButton.setEnabled(false);

        jcc = new JColorChooser();
        jcc.setColor(Color.BLACK);

        /* Stores all panels for tabbed pages */
        panels = new ArrayList<>();
        panelVEC = new ArrayList<>();
        commands_buffer = new ArrayList<Command>();

        // Undo Support
        undoList = new DefaultListModel();
        undoSupportList.setModel(undoList);


        /** Action listener for import Menu item opens file explorer with JFileChooser Component
        @param e ActionEvent  */
        importItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fc = new JFileChooser();
                JFrame fileFrame = new JFrame("Import File");

                fileFrame.setDefaultCloseOperation(fileFrame.DISPOSE_ON_CLOSE);
                fileFrame.setSize(500,500);
                FileNameExtensionFilter filter = new FileNameExtensionFilter("VEC Files", "vec");
                fc.setFileFilter(filter);

                // Making the dialog box to be center
                fileFrame.setLocationRelativeTo(null);

                int returnVal = fc.showDialog(fileFrame,"Insert");

                if (returnVal == JFileChooser.APPROVE_OPTION){

                    // Call file import
                    VEC vecfile = Importing.fileUpload(fc.getSelectedFile().getPath());

                    // Adding the vec file to the array while setting it to the panels
                    panelVEC.add(vecfile);
                    JPanel page = new DrawingCanvas(panelVEC.get(tabCount));
                    page.setBackground(Color.GRAY);
                    page.setName(fc.getSelectedFile().getName());

                    panels.add(page);
                    tabbedPages.add(panels.get(tabCount));

                    // Making default buttons enabled
                    paintButton.setEnabled(true);
                    colourButton.setEnabled(true);
                    fillColourButton.setEnabled(true);
                    isEnabled = true;

                    tabCount++;

                    for(Command cmd : panelVEC.get(tabbedPages.getSelectedIndex()).getCommands() ){
                        txtAreaVEC.append((cmd.getCommand() + " " + cmd.getParameters()
                                .toString()+ "\n").replace("[", " ").replace("]", " ")
                                .replace(",",""));
                    }

                }
            }
        });

        /** Assigns action to Key Event new page when user presses N
         @param e ActionEvent  */
        Action newAction = new AbstractAction("New (Ctrl + n)") {

            @Override
            public void actionPerformed(ActionEvent e) {
                // Creates new VEC file
                VEC vecfile = new VEC( new ArrayList<Command>());
                panelVEC.add(vecfile);

                JPanel page = new DrawingCanvas(panelVEC.get(tabCount));
                page.setBackground(Color.GRAY);
                page.setName("Page " + tabCount);

                panels.add(page);

                tabbedPages.add(panels.get(tabCount));


                // Making default buttons enabled
                paintButton.setEnabled(true);
                colourButton.setEnabled(true);
                fillColourButton.setEnabled(true);
                isEnabled = true;

                tabCount++;
            }
        };

        newAction.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_N);
        newItem.setAction(newAction);
        newItem.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
                KeyStroke.getKeyStroke("ctrl N"), "New (Ctrl + n)");
        newItem.getActionMap().put("New (Ctrl + n)", newAction);

        /**
         * Export action
         * @exception IOException
         */
        Action exportAction = new AbstractAction("Export") {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Getting panel and graphics
                JPanel exportPanel = panels.get(tabbedPages.getSelectedIndex());
                BufferedImage image = new BufferedImage(Shape.getCanvasWidth(), Shape.getCanvasHeight(),
                        BufferedImage.TYPE_INT_RGB);
                Graphics2D g = image.createGraphics();
                exportPanel.paint(g);

                // Getting saving location
                fc = new JFileChooser();
                JFrame fileFrame = new JFrame("Save File");
                fileFrame.setContentPane(fc);
                fileFrame.setDefaultCloseOperation(fileFrame.DISPOSE_ON_CLOSE);
                fileFrame.setSize(500, 500);
                // Making the dialog box to be center
                fileFrame.setLocationRelativeTo(null);

                // Formats
                FileNameExtensionFilter filterBMP = new FileNameExtensionFilter("BMP Format", "bmp");
                fc.setFileFilter(filterBMP);

                if (fc.showSaveDialog(fileFrame) == JFileChooser.APPROVE_OPTION) {
                    // Get selected file
                    File file = fc.getSelectedFile();
                    String fileName = file.getAbsolutePath();
                    // Checks whether it has the extension
                    // If not it adds the extension to the end
                    if(!fileName.endsWith(".bmp")){
                        String filePath = fileName+".bmp";
                        file = new File(filePath);
                    }
                    // Write Image to BMP format
                    try {
                        ImageIO.write(image, "bmp" , file );
                    } catch (IOException error) {
                        error.printStackTrace();
                    }
                }


            }
        };

        Export.setAction(exportAction);


        /**
         * Export action
         */
        Action readVECAction = new AbstractAction("Read VEC") {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtAreaVEC.setText("");
                ArrayList<Command> commands = panelVEC.get(tabbedPages.getSelectedIndex()).getCommands();
                for (Command cmd : commands){
                    txtAreaVEC.append( (cmd.getCommand() + " " + cmd.getParameters()
                            .toString()+ "\n").replace("[", " ").replace("]", " ")
                            .replace(",", "") );


                }

            }
        };

        // Setting action for the button
        readVECItem.setAction(readVECAction);
        readVECItem.getActionMap().put("Read VEC", readVECAction);


        /** Assigns action to Key Event saves page when user presses S
         @param e ActionEvent  */
        Action saveAction = new AbstractAction("Save (Ctrl + s)") {

            @Override
            public void actionPerformed(ActionEvent e) {
                fc = new JFileChooser();
                JFrame fileFrame = new JFrame("Save File");
                fileFrame.setContentPane(fc);
                fileFrame.setDefaultCloseOperation(fileFrame.DISPOSE_ON_CLOSE);
                fileFrame.setSize(500, 500);
                // Making the dialog box to be center
                fileFrame.setLocationRelativeTo(null);
                if (fc.showSaveDialog(fileFrame) == JFileChooser.APPROVE_OPTION) {
                    File file = fc.getSelectedFile();
                    Saving.fileSave(panelVEC.get(tabbedPages.getSelectedIndex()).getCommands(), file);
                }

            }
        };

        saveAction.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_S);
        SaveItem.setAction(saveAction);
        SaveItem.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
                KeyStroke.getKeyStroke("ctrl S"), "Save (Ctrl + s)");
        SaveItem.getActionMap().put("Save (Ctrl + s)", saveAction);



        /** Assigns action to Key Event exit GUI when user presses E
         @param e ActionEvent  */
        Action exitAction = new AbstractAction("Exit (Ctrl + e)") {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        };

        exitAction.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_E);
        exitItem.setAction(exitAction);
        exitItem.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
                KeyStroke.getKeyStroke("ctrl E"), "Exit (Ctrl + e)");
        exitItem.getActionMap().put("Exit (Ctrl + e)", exitAction);


         /** Action listener for close Menu Item opens closes current page and creates a new one
        @param e ActionEvent  */
        exitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        /** Assigns action to Key Event exit GUI when user presses ctrl + Z
         @param e ActionEvent  */
        Action undoAction = new AbstractAction("Undo (Ctrl + z)") {

            @Override
            public void actionPerformed(ActionEvent e) {
                // Retrieve commands from the current vec file
                ArrayList<Command> commands = panelVEC.get(tabbedPages.getSelectedIndex()).getCommands();
                if(commands.size() > 0){
                    // Update status bar
                    statusBarLabel.setText("Undo action, command: "+commands.get(commands.size() - 1).getCommand());
                    // Adding it to the buffer for redo action
                    commands_buffer.add(commands.get(commands.size() - 1));
                    commands.remove(commands.size() - 1);
                    panels.get(tabbedPages.getSelectedIndex()).repaint();

                    // removes from the list
                    undoList.remove(commands.size());
                }


            }
        };

        undoAction.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_Z);
        undoItem.setAction(undoAction);
        undoItem.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
                KeyStroke.getKeyStroke("ctrl Z"), "Undo (Ctrl + z)");
        undoItem.getActionMap().put("Undo (Ctrl + z)", undoAction);



        /** Assigns action to Key Event exit GUI when user presses ctrl + y
         @param e ActionEvent  */
        Action redoAction = new AbstractAction("Redo (Ctrl + y)") {

            @Override
            public void actionPerformed(ActionEvent e) {
                if(commands_buffer.size() > 0 ){
                    ArrayList<Command> commands = panelVEC.get(tabbedPages.getSelectedIndex()).getCommands();
                    statusBarLabel.setText("Redo action, command: "+commands_buffer.get(commands_buffer.size() - 1).getCommand());
                    commands.add(commands_buffer.get(commands_buffer.size() - 1));

                    undoList.add(undoList.size(), commands_buffer.get(commands_buffer.size() - 1).getCommand()+" "+
                            commands_buffer.get(commands_buffer.size() - 1).getParameters().toString() );
                    commands_buffer.remove(commands_buffer.size() - 1);
                    panels.get(tabbedPages.getSelectedIndex()).repaint();

                }

            }
        };

        redoAction.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_Y);
        redoItem.setAction(redoAction);
        redoItem.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
                KeyStroke.getKeyStroke("ctrl Y"), "Redo (Ctrl + y)");
        redoItem.getActionMap().put("Redo (Ctrl + y)", redoAction);


        /**
         * Zoom in Action
         * TODO: Finalise the zoom scale
         */
        zoomInItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Shape.setCanvasWidth(Shape.getCanvasWidth() + 100);
                Shape.setCanvasHeight(Shape.getCanvasHeight() + 100);
                panels.get(tabbedPages.getSelectedIndex()).repaint();
            }
        });

        /**
         * Zoom Out Action
         * TODO: Finalise the zoom scale
         */
        zoomOutItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Shape.setCanvasWidth(Shape.getCanvasWidth() - 100);
                Shape.setCanvasHeight(Shape.getCanvasHeight() - 100);
                panels.get(tabbedPages.getSelectedIndex()).repaint();
            }
        });

        /**
         * Opens the User Manual PDF from desktop default client ACTION
         * Will open when F1 is pressed
         * @exception Exception
         */
        Action userGuide = new AbstractAction("User Manual (F1)") {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Gets PDF file from resource
                    URL res = getClass().getResource("/Documents/UserManual.pdf");
                    File pdfFile = Paths.get(res.toURI()).toFile();
                    if (pdfFile.exists()) {

                        if (Desktop.isDesktopSupported()) {
                            // Opening the user manual from default PDF application
                            Desktop.getDesktop().open(pdfFile);
                        } else {
                            System.out.println("Awt Desktop is not supported!");
                        }

                    } else {
                        System.out.println("File is not exists!");
                    }

                    System.out.println("Done");

                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }

        };

        userGuide.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_F1);
        userGuideItem.setAction(userGuide);
        userGuideItem.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
                KeyStroke.getKeyStroke("F1"), "User Manual (F1)");
        userGuideItem.getActionMap().put("User Manual (F1)", userGuide);



        /** Action listener for PenItem in PopUpMenu changes the Icon of currentTool with Pen Icon
         @param e ActionEvent */
        penItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ImageIcon pen = new ImageIcon(getClass().getResource("/GUI/icons/pen.png"));
                currentToolButton.setIcon(pen);
                currentToolButton.setEnabled(true);


            }
        });

        /** Action listener for ellipseItem in PopUpMenu changes the Icon of currentTool with ellipse Icon
         @param e ActionEvent  */
        ellipseItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ImageIcon ellipse = new ImageIcon(getClass().getResource("/GUI/icons/ellipse.png"));
                currentToolButton.setIcon(ellipse);
                currentTool = "ELLIPSE";
                currentToolButton.setEnabled(true);
            }
        });

        /** Action listener for polyItem in PopUpMenu changes the Icon of currentTool with Polygon Icon
         @param e ActionEvent  */
        polyItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ImageIcon poly = new ImageIcon(getClass().getResource("/GUI/icons/polygon.png"));
                currentToolButton.setIcon(poly);
                currentTool = "POLYGON";
                currentToolButton.setEnabled(true);
                txtPointPolygon.setEnabled(true);
            }
        });

        /** Action listener for recItem in PopUpMenu changes the Icon of currentTool with Rectangle Icon
         @param e ActionEvent  */
        rectItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ImageIcon rect = new ImageIcon(getClass().getResource("/GUI/icons/rect.png"));
                currentToolButton.setIcon(rect);
                currentTool = "RECTANGLE";
                currentToolButton.setEnabled(true);
            }
        });

        /** Action listener for lineItem in PopUpMenu changes the Icon of currentTool with Line Icon
         @param e ActionEvent  */
        lineItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ImageIcon brush = new ImageIcon(getClass().getResource("/GUI/icons/line.png"));
                currentToolButton.setIcon(brush);
                currentTool = "LINE";
                currentToolButton.setEnabled(true);

            }
        });

        /** Action listener for plotItem in PopUpMenu changes the Icon of currentTool with Plot Icon
         @param e ActionEvent  */
        plotItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ImageIcon plot = new ImageIcon(getClass().getResource("/GUI/icons/plot.png"));
                currentToolButton.setIcon(plot);
                currentTool = "PLOT";
                currentToolButton.setEnabled(true);
            }
        });

        /** Action listener for fillColour in PopUpMenu changes the Icon of currentTool with Fill Icon
         * Opens colour chooser for fill
         @param e ActionEvent */
        fillColourButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isFillColour = true;
                jcc.setPreviewPanel(new JPanel());
                jcc.getSelectionModel().addChangeListener(new ColorSelection());
                JFrame colourFrame = new JFrame("Pick Fill Colour");
                colourFrame.setContentPane(jcc);
                colourFrame.setDefaultCloseOperation(colourFrame.DISPOSE_ON_CLOSE);
                colourFrame.setSize(500,500);
                colourFrame.setVisible(true);
                colourFrame.setLocationRelativeTo(null);

                // Update current tool button
                ImageIcon fill = new ImageIcon(getClass().getResource("/GUI/icons/fill.png"));
                currentToolButton.setIcon(fill);
                currentToolButton.setEnabled(true);


            }
        });



        /** Action listener for colourButton creates a JColourChooser PopUp to select a colour
         @param e ActionEvent  */
        colourButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isFillColour = false;
                jcc.setPreviewPanel(new JPanel());
                jcc.getSelectionModel().addChangeListener(new ColorSelection());
                JFrame colourFrame = new JFrame("Pick Pen Colour");
                colourFrame.setContentPane(jcc);
                colourFrame.setDefaultCloseOperation(colourFrame.DISPOSE_ON_CLOSE);
                colourFrame.setSize(500,500);
                // Making the window center
                colourFrame.setLocationRelativeTo(null);
                colourFrame.setVisible(true);
                jcc.setColor(Color.BLACK);
            }
        });

        /** Action listener for paintButton creates PopUpMenu so user can select a tool
         @param e MouseEvent  */
        paintButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if(paintButton.isEnabled()){
                    if (SwingUtilities.isLeftMouseButton(e)) {
                        showPopup(e);
                    }
                }

            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if(paintButton.isEnabled()) {
                    if (SwingUtilities.isLeftMouseButton(e)) {
                        showPopup(e);
                    }
                }
            }

            private void showPopup(MouseEvent e) {
                if(paintButton.isEnabled()){
                    if (SwingUtilities.isLeftMouseButton(e)) {
                        toolPopUpMenu.show(e.getComponent(),
                                e.getX(), e.getY());
                    }
                }
            }
        });

        /** Action listener for clearToolButton clears the currently selected tool for the default one
         @param e ActionEvent  */
        clearToolButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ImageIcon toolDefault = new ImageIcon(getClass().getResource("/GUI/icons/pen.png"));
                currentToolButton.setIcon(toolDefault);
                currentToolButton.setEnabled(false);
            }
        });


        /** Action Listener for the clearPaintButton, will change the colours to default
         @param e ActionEvent  */
        clearPaintButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Setting the colour panels to default values
                currentColourButton.setBackground(Color.BLACK);
                currentColourButton.setForeground(Color.BLACK);
                currentFillColourButton.setBackground(Color.WHITE);
                currentFillColourButton.setForeground(Color.WHITE);
                // Setting the colour selection panel colour to default
                jcc.setColor(Color.BLACK);
                currentColourButton.repaint();
                fillColourButton.repaint();
            }
        });


        tabbedPages.addContainerListener(new ContainerAdapter() {
        });
        tabbedPages.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);



            }
        });

        /**
         * Tab change event
         */
        tabbedPages.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {

                tabbedPages.revalidate();
                int iconSize = 30; // to reduce the distance from the click and the retrieved value
                /**
                 * Mouse Click Event
                 */
                tabbedPages.getSelectedComponent().addMouseListener(new MouseAdapter() {

                    ArrayList<String> parametersPolygon = new ArrayList<> ();
                    String[] parameters = new String[4];

                    @Override
                    public void mousePressed(MouseEvent e) {
                        if(isEnabled && currentToolButton.isEnabled()){
                            super.mouseClicked(e);
                            int x = e.getX();
                            int y = e.getY()- iconSize;
                            // Prevent triggering event if clicked outside the bounds
                            if( (x >= 0 && x <= Shape.getCanvasWidth()) && (y >= 0 && y <= Shape.getCanvasHeight()) ) {
                                if (currentTool.equals("POLYGON") && !currentTool.equals("PLOT")) {

                                    int polygonValue = Integer.valueOf(txtPointPolygon.getText());
                                    // Trigger event if the value is bigger than 2
                                    if(polygonValue > 2){
                                        if (polygonPointCounter < polygonValue) {
                                            parametersPolygon.add(String.valueOf(x / (double) Shape.getCanvasWidth()));
                                            parametersPolygon.add(String.valueOf(y / (double) Shape.getCanvasHeight()));
                                            polygonPointCounter++;
                                        }

                                        if (polygonPointCounter == polygonValue) {
                                            // Creates command
                                            Command cmd = new Command(currentTool, parametersPolygon);
                                            // retrieves the tabbed panel vec file
                                            panelVEC.get(tabbedPages.getSelectedIndex()).getCommands().add(cmd);
                                            panels.get(tabbedPages.getSelectedIndex()).repaint();
                                            statusBarLabel.setText("Ending points points (x,y): " + x + "," + y);
                                            if(!undoList.contains(cmd.getCommand()+" "+cmd.getParameters().toString()+"\n")){
                                                undoList.add(undoList.size(),cmd.getCommand()+" "+cmd.getParameters().toString()+"\n");
                                            }

                                            txtPointPolygon.setEnabled(false);
                                            // Setting the default values to the variables
                                            polygonPointCounter = 0;
                                            txtPointPolygon.setText("");

                                        }
                                    }else{
                                        // Alert box
                                        JOptionPane.showMessageDialog(null, "Error: You have to select a value greater than 2");
                                    }


                                    statusBarLabel.setText("Selected points (x,y): " + x + "," + y);

                                } else {
                                    // For Plots
                                    if ((x >= 0 && x <= Shape.getCanvasWidth()) && (y >= 0 && y <= Shape.getCanvasHeight())) {
                                        parameters[0] = String.valueOf(x / (double) Shape.getCanvasWidth());
                                        parameters[1] = String.valueOf(y / (double) Shape.getCanvasHeight());

                                    }
                                    statusBarLabel.setText("Selected points (x,y): " + x + "," + y);
                                }
                            }

                        }


                    }

                    @Override
                    public void mouseReleased(MouseEvent e) throws NullPointerException{
                        // Checks whether the tool is enabled and it is not polygon
                        if(isEnabled && !currentTool.equals("POLYGON") && currentToolButton.isEnabled()) {
                            super.mouseReleased(e);
                            int x = e.getX();
                            int y = e.getY() - iconSize;
                            // Prevent triggering event if clicked outside the bounds
                            if( (x >= 0 && x <= Shape.getCanvasWidth()) && (y >= 0 && y <= Shape.getCanvasHeight()) ){
                                // Checks whether it is a plot to not add the other 2 elements
                                if(!currentTool.equals("PLOT")){
                                    parameters[2] = String.valueOf(x/ (double)Shape.getCanvasWidth());
                                    parameters[3] = String.valueOf(y/ (double)Shape.getCanvasHeight());
                                }

                                Command cmd = new Command(currentTool,
                                        new ArrayList<String>(Arrays.asList(parameters)));
                                // retrieves the tabbed panel vec file
                                if(!panelVEC.get(tabbedPages.getSelectedIndex()).getCommands().contains(cmd)){
                                    panelVEC.get(tabbedPages.getSelectedIndex()).getCommands().add(cmd);
                                }

                                panels.get(tabbedPages.getSelectedIndex()).repaint();
                                statusBarLabel.setText("Ending points points (x,y): "+x+","+y);
                                txtAreaVEC.removeAll();
                                // Add to undo support list
                                if(!undoList.contains(cmd.getCommand()+" "+cmd.getParameters().toString()+"\n")){
                                    undoList.add(undoList.size(),cmd.getCommand()+" "+cmd.getParameters().toString()+"\n");
                                }

                            }else{
                                statusBarLabel.setText("Mouse click beyond the canvas area, please redraw.");
                            }



                        }
                    }

                    @Override
                    public void mouseDragged(MouseEvent e) throws NullPointerException{

                        if(isEnabled && !currentTool.equals("POLYGON") && currentToolButton.isEnabled()) {

                            super.mouseDragged(e);
                            int x = e.getX();
                            int y = e.getY() - iconSize;
                            // Prevent triggering event if clicked outside the bounds
                            if( (x >= 0 && x <= Shape.getCanvasWidth()) && (y >= 0 && y <= Shape.getCanvasHeight()) ){
                                // Checks whether it is a plot to not add the other 2 elements
                                if(!currentTool.equals("PLOT")){
                                    parameters[2] = String.valueOf(x/ (double)Shape.getCanvasWidth());
                                    parameters[3] = String.valueOf(y/ (double)Shape.getCanvasHeight());
                                }

                                Command cmd = new Command(currentTool,
                                        new ArrayList<String>(Arrays.asList(parameters)));
                                // retrieves the tabbed panel vec file
                                if(!panelVEC.get(tabbedPages.getSelectedIndex()).getCommands().contains(cmd)){
                                    panelVEC.get(tabbedPages.getSelectedIndex()).getCommands().add(cmd);
                                }
                                panels.get(tabbedPages.getSelectedIndex()).repaint();
                                statusBarLabel.setText("Ending points points (x,y): "+x+","+y);
                                txtAreaVEC.removeAll();
                                // Add to undo support list
                                if(!undoList.contains(cmd.getCommand()+" "+cmd.getParameters().toString()+"\n")){
                                    undoList.add(undoList.size(),cmd.getCommand()+" "+cmd.getParameters().toString()+"\n");
                                }
                            }else{
                                statusBarLabel.setText("Mouse click beyond the canvas area, please redraw.");
                            }
                        }

                    }


                });
            }
        });



        /**
         * Close tab action listener
         */
        closeTabButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(tabbedPages.getSelectedIndex() >= 0 && tabbedPages.getSelectedIndex() < tabbedPages.getTabCount()){
                    tabbedPages.remove(tabbedPages.getSelectedIndex());

                }
            }
        });

        /**
         * No fill button, will add FILL OFF to the VEC
         */
        noFillButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Update current tool
                ImageIcon fill = new ImageIcon(getClass().getResource("/GUI/icons/fill_clear.png"));
                currentToolButton.setIcon(fill);
                currentToolButton.setEnabled(true);

                // Add entry to the VEC file
                ArrayList<String> parameters = new ArrayList<>();
                parameters.add("OFF");
                Command fillCommand = new Command("FILL",parameters);
                panelVEC.get(tabbedPages.getSelectedIndex()).getCommands().add(fillCommand);
                currentFillColourButton.setForeground(Color.WHITE);
                currentFillColourButton.setBackground(Color.WHITE);
                currentFillColourButton.repaint();
                noFillButton.setEnabled(false);
                undoList.add(undoList.size(),fillCommand.getCommand()+" "+fillCommand.getParameters().toString()+"\n");
            }
        });


    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }

    /** ChangeListener for ColorSelection changes the colour of the currentColour of fill and pen
     * to the colour returned from JColorChooser
     */
    class ColorSelection implements ChangeListener {
        public void stateChanged(ChangeEvent e) {

            // Checks whether the fill colour button is clicked to change the relevant colour
            if(isFillColour){
                Color color = jcc.getColor();
                if(!color.equals(currentFillColourButton.getBackground())){
                    currentFillColourButton.setForeground(color);
                    currentFillColourButton.setBackground(color);
                    currentFillColourButton.repaint();
                    // Retrieve the hex colour from the fill colour button
                    String hex = String.format("#%02x%02x%02x", currentFillColourButton.getBackground().getRed(),
                            currentFillColourButton.getBackground().getGreen(),
                            currentFillColourButton.getBackground().getBlue());
                    ArrayList<String> parameters = new ArrayList<>();
                    parameters.add(hex);
                    Command fillCommand = new Command("FILL",parameters);

                    panelVEC.get(tabbedPages.getSelectedIndex()).getCommands().add(fillCommand);
                    undoList.add(
                            undoList.size(),
                            fillCommand.getCommand()+" "+fillCommand.getParameters().toString()+"\n"
                    );
                    noFillButton.setEnabled(true);

                }

            }else{
                Color color = jcc.getColor();
                if(!color.equals(currentColourButton.getBackground())) {
                    currentColourButton.setForeground(color);
                    currentColourButton.setBackground(color);
                    currentColourButton.repaint();

                    // Convert the current selected colour to hex format
                    String hex = String.format("#%02x%02x%02x", currentColourButton.getBackground().getRed(),
                            currentColourButton.getBackground().getGreen(), currentColourButton.getBackground().getBlue());
                    ArrayList<String> parameters = new ArrayList<>();
                    parameters.add(hex);
                    Command penCommand = new Command("PEN", parameters);
                    // Adding it to the panels
                    panelVEC.get(tabbedPages.getSelectedIndex()).getCommands().add(penCommand);
                    undoList.add(
                            undoList.size(),
                            penCommand.getCommand()+" "+penCommand.getParameters().toString()+"\n"
                    );
                }

            }

        }
    }


    /**
     * Main Methos for the GUI
     * @param args arguments for the main method
     */
    public static void main(String[] args) {
        JFrame frame = new JFrame("Vector Design Tool");
        JPanel mainPanel = new vectorTool().mainPanel;
        frame.setJMenuBar(menuBar);
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);


    }
}
