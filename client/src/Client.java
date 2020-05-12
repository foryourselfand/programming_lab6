import Commands.Command;
import Utils.Response;
import Utils.SerializationManager;

import java.io.IOException;
import java.net.*;

public class Client {
	private static final int DEFAULT_BUFFER_SIZE = 65536;
	private static final SerializationManager<Command> commandSerializationManager = new SerializationManager<>();
	private static final SerializationManager<Response> responseSerializationManager = new SerializationManager<>();
	private static SocketAddress socketAddress;
	private static DatagramSocket datagramSocket;
	
	public static void connect(String host, int port) throws UnknownHostException, SocketException {
		InetAddress inetAddress = InetAddress.getByName(host);
		System.out.println("Подключение к " + inetAddress);
		socketAddress = new InetSocketAddress(inetAddress, port);
		datagramSocket = new DatagramSocket();
		datagramSocket.connect(socketAddress);
	}
	
	public static void sendCommandAndReceiveAnswer(Command command) {
		try {
			byte[] commandInBytes = commandSerializationManager.writeObject(command);
			DatagramPacket datagramPacket = new DatagramPacket(commandInBytes, commandInBytes.length, socketAddress);
			datagramSocket.send(datagramPacket);
			System.out.println("Запрос отправлен на сервер...");
			byte[] answerInBytes = new byte[DEFAULT_BUFFER_SIZE];
			datagramPacket = new DatagramPacket(answerInBytes, answerInBytes.length);
			datagramSocket.receive(datagramPacket);
			String result = responseSerializationManager.readObject(answerInBytes).getResponse();
			System.out.println("Получен ответ от сервера: ");
			System.out.print(result);
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
