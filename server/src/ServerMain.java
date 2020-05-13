import Utils.Context;

import java.io.IOException;

public class ServerMain {
	public static void main(String[] args) {
		int port = 8000;
		
		Context context = new Context();
		context.loadCollectionFromArgs(args);
		
		try {
			Server.connect(port);
		} catch (IOException e) {
			e.printStackTrace();
		}
		Server.run(context);
	}
}
