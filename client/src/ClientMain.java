import Errors.InputErrors.InputError;
import Utils.CommandsHolder;
import Utils.Context;

public class ClientMain {
	public static void main(String[] args) {
		String host = "localhost";
		int port = 8000;
		
		try {
			Client.connect(host, port);
		} catch (InputError inputError) {
			System.out.println(inputError.getMessage());
		}
		
		CommandsHolder commandsHolder = new CommandsHolder();
		CommandsSender commandsSender = new CommandsSender(commandsHolder);
		
		while (Context.lineReader.hasSomethingToRead()) {
			String lineRead = Context.lineReader.readLine(">>> ");
			commandsSender.tryToExecuteCommand(lineRead);
		}
	}
}
