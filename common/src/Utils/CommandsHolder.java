package Utils;

import Commands.Command;
import Errors.NoSuchCommandError;

import java.util.*;

public class CommandsHolder {
	private final Map<String, Command> commands;
	private final Deque<Command> commandsHistory;
	
	public CommandsHolder() {
		commands = new LinkedHashMap<>();
		commandsHistory = new ArrayDeque<>();
	}
	
	public CommandsHolder addCommand(Command command) {
		commands.put(command.getName(), command);
		return this;
	}
	
	public Command getCommandByName(String name) {
		if (! this.commands.containsKey(name))
			throw new NoSuchCommandError(name);
		
		Command command = this.commands.get(name);
		addCommandToHistory(command);
		return command;
	}
	
	private void addCommandToHistory(Command command) {
		if (commandsHistory.size() == Context.HISTORY_SIZE)
			commandsHistory.pollFirst();
		commandsHistory.addLast(command);
	}
	
	public Iterator<Command> getCommands() {
		return commands.values().iterator();
	}
	
	public Iterator<Command> getCommandsHistory() {
		return commandsHistory.iterator();
	}
}
