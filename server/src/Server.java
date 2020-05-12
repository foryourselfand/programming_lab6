import Commands.Command;
import Errors.InputErrors.InputError;
import Utils.Context;
import Utils.Response;
import Utils.SerializationManager;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class Server {
	private static final int DEFAULT_BUFFER_SIZE = 65536;
	private static final byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
	private static final SerializationManager<Command> commandSerializationManager = new SerializationManager<>();
	private static final SerializationManager<Response> responseSerializationManager = new SerializationManager<>();
	private static SocketAddress address;
	private static DatagramChannel channel;
	
	public static void connect(int port) throws IOException {
		address = new InetSocketAddress(port);
		channel = DatagramChannel.open();
		channel.configureBlocking(false);
		channel.bind(address);
	}
	
	public static void run(Context context) {
		try {
			while (true) {
				ByteBuffer byteBuffer = ByteBuffer.wrap(buffer);
				do {
					address = channel.receive(byteBuffer);
				} while (address == null);
				Command command = commandSerializationManager.readObject(buffer);
				System.out.println("Сервер получил команду " + command);
				String response = processCommand(context, command);
				
				System.out.println("Команда " + command + " выполнена, посылаю ответ клиенту...");
				
				byte[] answer = responseSerializationManager.writeObject(new Response(response));
				byteBuffer = ByteBuffer.wrap(answer);
				channel.send(byteBuffer, address);
			}
		} catch (ClassNotFoundException | IOException | ClassCastException e) {
			e.printStackTrace();
		}
	}
	
	public static String processCommand(Context context, Command command) {
//		if (command instanceof ExitCommand) {
//			new SaveCommand().execute(application);
//			log.info("Server receive command " + new SaveCommand().toString());
//		}
		try {
			command.showDescriptionAndExecute(context);
			context.commandsHistoryManager.addCommandToHistory(command);
			return command.getResponse();
		} catch (InputError inputError) {
			return inputError.getMessage() + "\n";
		}

//		application.getCommandHistory().add(command);
//		application.setCollection(command.getCollection());
//		application.setIdList(command.getIdList());
	}
}
