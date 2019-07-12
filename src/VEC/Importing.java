package VEC;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Importing functionality
 * @author Aidan Perera
 * @version 1.1
 */
public class Importing {

    /**
     * vec File
     */
    private static VEC vecFile = null;

    /**
     * Uploading the file and Saving it to a VEC instance
     * Pass the file path to the function, will create the VEC file
     * @param filePath fileName VEC
     * @return VEC vector file for imported file
     */
    public static VEC fileUpload(String filePath){
        try {

            // Array of commands
            ArrayList<Command> commands = new ArrayList<Command>();
            /**
             * Reading file line by line
             */
            Scanner scanner = new Scanner(new File(filePath));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] words = line.split("\\s+");
                // Creating commands instance
                Command cmd = new Command();
                cmd.setCommand(words[0]);
                // Adding parameters to the counter
                for(int counter = 1; counter < words.length; counter++){
                    cmd.addParameters(words[counter]);
                }
                cmd.createShape();
                commands.add(cmd);

            }
            vecFile = new VEC(commands);
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return vecFile;
    }



}
