import Errors.InputErrors.InputError;
import Utils.Context;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ServerMain {
	public static final Logger logger = Logger.getLogger(ServerMain.class.getName());
	
	public static void main(String[] args) {
		int port = 8000;
		
		Context context = new Context();
		
		context.loadCollectionFromArgs(args);
		
		try {
			Server.connect(port);
		} catch (InputError inputError) {
			logger.log(Level.SEVERE, "Error while trying initialize server", inputError);
		}
		Server.run(context);
	}
}
