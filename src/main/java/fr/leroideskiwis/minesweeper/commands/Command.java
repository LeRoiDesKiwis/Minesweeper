package fr.leroideskiwis.minesweeper.commands;

import fr.leroideskiwis.minesweeper.Location;

public interface Command {

    void execute(Location location, String[] args);

}
