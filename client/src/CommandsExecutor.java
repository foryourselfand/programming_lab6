import Commands.Command;
import Errors.InputErrors.InputError;
import Utils.CommandsHolder;

import java.util.Arrays;

public class CommandsExecutor {
	private final CommandsHolder commandsHolder;
	
	public CommandsExecutor(CommandsHolder commandsHolder) {
		this.commandsHolder = commandsHolder;
	}
	
	public void tryToExecuteCommand(String line) {
		if (line.equals(""))
			return;
		
		String[] lineSplit = this.getLineSplit(line);
		
		String commandName = this.getCommandName(lineSplit);
		String[] commandArguments = this.getCommandArguments(lineSplit);
		
		try {
			Command commandToExecute = commandsHolder.getCommandByName(commandName);
			commandToExecute.validateArguments(commandArguments);
			Client.sendCommandAndReceiveAnswer(commandToExecute);
		} catch (InputError error) {
			System.out.println(error.getMessage());
		}
	}
	
	private String[] getLineSplit(String lineRaw) {
		String lineTrim = lineRaw.trim();
		return lineTrim.split("\\s+");
	}
	
	private String getCommandName(String[] lineSplit) {
		return lineSplit[0].toLowerCase();
	}
	
	private String[] getCommandArguments(String[] lineSplit) {
		return Arrays.copyOfRange(lineSplit, 1, lineSplit.length);
	}
}
