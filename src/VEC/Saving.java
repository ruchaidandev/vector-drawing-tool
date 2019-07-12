package VEC;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

/**
 * Create function
 *    get instance
 *    make string , 4 lines
 *    write to a file
 *    create main
 *    create instance and pass value
 *
 * @author Oscar Li
 * @version 1.1
 */

public class Saving {
    private static VEC vecFile = null;

    /**
     * Saves file in the given location
     * @param comms : Command array list to pass to the file
     * @param file : File location
     * @return : the vec file
     */
    public static VEC fileSave(ArrayList<Command> comms, File file){

        String fileName = file.getAbsolutePath();
        // Checks whether it has the extension
        // If not it adds the extension to the end
        if(!fileName.endsWith(".vec")){
            String filePath = fileName+".vec";
            file = new File(filePath);
        }
        try {
            FileWriter fileWriter = new FileWriter(file);
            /** Accessing elements of the arraylist, extract values into a VEC formatted file */

            for(int i = 0; i < comms.size(); i++){
                fileWriter.write((comms.get(i).getCommand() + " " + comms.get(i).getParameters()
                        .toString()+ "\n").replace("[", " ").replace("]", " ")
                .replace(",","")) ;

            }

            fileWriter.close();


        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
