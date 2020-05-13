package Utils;

import Commands.Command;
import Generators.CreationDateGenerator;
import Input.Flat;

import java.time.LocalDate;
import java.util.Comparator;

public class Context {
	public static final LocalDate INITIALIZATION_DATE = CreationDateGenerator.generateCreationDate();
	public static final int HISTORY_SIZE = 12;
	public static LineReader lineReader = new LineReader();
	public CommandsHolder commandsHolder;
	public CommandsHistoryManager commandsHistoryManager;
	public CollectionManager collectionManager;
	public FlatRemover flatRemover;
	public CSVSaver csvSaver;
	
	public Context() {
		this.collectionManager = new CollectionManager();
		this.commandsHolder = new CommandsHolder();
		this.commandsHistoryManager = new CommandsHistoryManager();
		this.flatRemover = new FlatRemover(collectionManager);
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
