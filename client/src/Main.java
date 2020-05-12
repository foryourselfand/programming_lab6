import Commands.*;
import Utils.CommandsHolder;
import Utils.Context;
import Utils.LineReader;

import java.net.SocketException;
import java.net.UnknownHostException;

public class Main {
	public static void main(String[] args) {
		String host = "localhost";
		int port = 8000;
		
		try {
			Client.connect(host, port);
		} catch (UnknownHostException | SocketException e) {
			e.printStackTrace();
		}
		
		CommandsHolder commandsHolder = new CommandsHolder();
		commandsHolder
				.addCommand(new CommandHelp())
				.addCommand(new CommandInfo())
				.addCommand(new CommandShow())
				.addCommand(new CommandAdd())
				.addCommand(new CommandUpdateById())
				.addCommand(new CommandRemoveById())
				.addCommand(new CommandClear())
				.addCommand(new CommandLoad())
				.addCommand(new CommandSave())
				.addCommand(new CommandExecuteScript())
				.addCommand(new CommandExit())
				.addCommand(new CommandAddIfMax())
				.addCommand(new CommandRemoveGreater())
				.addCommand(new CommandHistory())
				.addCommand(new CommandAverageOfHeight())
				.addCommand(new CommandGroupCountingByArea())
				.addCommand(new CommandPrintUniqueHouse())
		;
		CommandsExecutor commandsExecutor = new CommandsExecutor(commandsHolder);
		
		while (Context.lineReader.hasSomethingToRead()) {
			String lineRead = Context.lineReader.readLine(">>> ");
			commandsExecutor.tryToExecuteCommand(lineRead);
		}
	}
}
