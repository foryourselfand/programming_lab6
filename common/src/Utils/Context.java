package Utils;

import Commands.Command;
import Generators.CreationDateGenerator;
import Generators.IdGenerator;

import java.time.LocalDate;

public class Context {
	public static final LocalDate INITIALIZATION_DATE = CreationDateGenerator.generateCreationDate();
	public static final int HISTORY_SIZE = 12;
	public static final IdGenerator idGenerator = new IdGenerator();
	public static LineReader lineReader = new LineReader();
	public CommandsHolder commandsHolder;
	public CommandsHistoryManager commandsHistoryManager;
	public CollectionManager collectionManager;
	public CSVSaver csvSaver;
	
	public Context() {
		this.collectionManager = new CollectionManager();
		this.commandsHolder = new CommandsHolder();
		this.commandsHistoryManager = new CommandsHistoryManager();
		this.csvSaver = new CSVSaver();
	}
	
	public void loadCollectionFromArgs(String[] args) {
		if (args.length == 1) {
			Command commandLoad = commandsHolder.getCommandByName("load");
			commandLoad.validateArguments(args);
			commandLoad.showDescriptionAndExecute(this);
		}
	}
}
