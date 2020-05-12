package Utils;

import Commands.Command;
import Errors.NoSuchCommandError;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class CommandsHolder {
	private final Map<String, Command> commands;
	
	public CommandsHolder() {
		commands = new LinkedHashMap<>();
	}
	
	public CommandsHolder addCommand(Command command) {
		commands.put(command.getName(), command);
		return this;
	}
	
	public Command getCommandByName(String name) {
		if (! this.commands.containsKey(name))
			throw new NoSuchCommandError(name);
		
		return this.commands.get(name);
	}
	
	public Iterator<Command> getCommands() {
		return commands.values().iterator();
	}
}
