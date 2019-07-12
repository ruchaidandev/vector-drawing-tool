package VEC;

import java.util.ArrayList;

/**
 * Represents the VEC file
 * @author Aidan Perera
 * @version 1.1
 */
public class VEC {

    /**
     * Commands in the VEC
     */
    private ArrayList<Command> commands;

    /**
     * Constructor for the VEC
     * @param commands Commands for the VEC
     */
    public VEC(ArrayList<Command> commands) {
        this.commands = commands;
    }

    /**
     * Retrieve comamnds
     * @return commands in the VEC
     */
    public ArrayList<Command> getCommands() {
        return commands;
    }

    /**
     * Setting comamnds
     * @param commands commands in the VEC
     */
    public void setCommands(ArrayList<Command> commands) {
        this.commands = commands;
    }
}
