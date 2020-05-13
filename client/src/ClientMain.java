import Utils.CommandsHolder;
import Utils.Context;

import java.net.SocketException;
import java.net.UnknownHostException;

public class ClientMain {
	public static void main(String[] args) {
		String host = "localhost";
		int port = 8000;
		
		try {
			Client.connect(host, port);
		} catch (UnknownHostException | SocketException e) {
			e.printStackTrace();
		}
		
		CommandsHolder commandsHolder = new CommandsHolder();
		CommandsSender commandsSender = new CommandsSender(commandsHolder);
		
		while (Context.lineReader.hasSomethingToRead()) {
			String lineRead = Context.lineReader.readLine(">>> ");
			commandsSender.tryToExecuteCommand(lineRead);
		}
	}
}
