package fr.leroideskiwis.minesweeper.commands;

import fr.leroideskiwis.minesweeper.GameMap;
import fr.leroideskiwis.minesweeper.Location;

import java.util.*;

public class CommandManager {

    private final GameMap gameMap;
    private final Map<CommandInfos, Command> commands = new HashMap<>();

    public CommandManager(GameMap gameMap) {
        this.gameMap = gameMap;
        commands.put(new CommandInfos("flag", "poser un drapeau"), (loc, args) -> gameMap.flag(loc));
        commands.put(new CommandInfos("reveal", "reveler une case"), (loc, args) -> gameMap.reveal(loc));
        commands.put(new CommandInfos("exit", "quitter la partie"), (loc, args) -> System.exit(0));

        commands.put(new CommandInfos("help", "afficher l'aide"), (loc, args) -> {
            System.out.println("Commands :");
            for (CommandInfos command : commands.keySet()) {
                System.out.println("\t" + command);
            }
        });
    }

    public void handleCommand(Scanner scanner) {
        String commandName;
        System.out.print("> ");
        commandName = scanner.nextLine();

        String[] args = commandName.split(" ");
        int x = args.length > 1 ? Integer.parseInt(args[1]) : -1;
        int y = args.length > 2 ? Integer.parseInt(args[2]) : -1;
        Location location = new Location(x - 1, y - 1);
        getCommand(args[0])
                .stream()
                .findFirst()
                .ifPresentOrElse(c -> c.execute(location, args), () -> System.out.println("Command not found"));
    }

    private List<Command> getCommand(String command) {
        return commands.entrySet().stream().filter(entry -> entry.getKey().name().startsWith(command)).map(Map.Entry::getValue).toList();
    }
}
