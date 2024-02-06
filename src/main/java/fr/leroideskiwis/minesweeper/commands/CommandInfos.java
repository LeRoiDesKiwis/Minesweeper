package fr.leroideskiwis.minesweeper.commands;

public record CommandInfos(String name, String description) {

    @Override
    public String toString() {
        return name + " : " + description;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof CommandInfos && ((CommandInfos) obj).name.equals(name);
    }
}
