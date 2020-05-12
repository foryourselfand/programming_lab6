package Utils;

import Commands.*;
import Generators.CreationDateGenerator;

import java.time.LocalDate;

public class Context {
	public static final LocalDate INITIALIZATION_DATE = CreationDateGenerator.generateCreationDate();
	public static final int HISTORY_SIZE = 12;
	
	public CommandsHolder commandsHolder;
	public LineReader lineReader;
	public CollectionManager collectionManager;
	public FlatRemover flatRemover;
	public CSVSaver csvSaver;
	
	public Context() {
		this.collectionManager = new CollectionManager();
		this.lineReader = new LineReader();
		this.commandsHolder = new CommandsHolder();
		this.flatRemover = new FlatRemover(collectionManager);
		this.csvSaver = new CSVSaver();
		
		setUpCommands();
	}
	
	public void setUpCommands() {
		this.commandsHolder
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
	}
	
	public void loadCollectionFromArgsOrRestoreFromTempFile(String[] args) {
		if (args.length == 1) {
			CommandLoad commandLoad = new CommandLoad();
			commandLoad.validateArguments(args);
			commandLoad.printDescriptionAndExecute(this);
		}
//		else
//			this.tryToLoadTempFile();
	}

//	public void tryToLoadTempFile() {
//		if (TempFileManager.isTempFileExist()) {
//			System.out.println("Найден временный файл");
//
//			String choice;
//			do {
//				choice = this.lineReader.readLine("Восстановить коллекцию из временного файла? y / n: ").trim().toLowerCase();
//			} while (! choice.equals("y") && ! choice.equals("n"));
//
//			if (choice.equals("y"))
//				this.commandsExecutor.tryToExecuteCommand("load " + TempFileManager.getTempFilePath());
//		}
//	}
}
