package Commands;

import Expectations.Argument;
import Expectations.ExpectedFile.*;
import Utils.TempFileManager;

import java.util.List;

public class CommandSave extends CommandWithNotEmptyCollection {
	public CommandSave() {
		super();
	}
	
	@Override
	protected void addArgumentValidators(List<Argument> arguments) {
		arguments.add(new Argument(
				"file_name",
				new ExpectedFileExtensionCsv(),
				new ExpectedCreateFileIfNotExist(),
				new ExpectedFileExist(),
				new ExpectedFileRegular(),
				new ExpectedFileWritable()
		));
	}
	
	@Override
	public void execute() {
		String filePath = commandArguments[0];
		
		this.context.csvSaver.saveCollectionToCSVFile(this.context.collectionManager.getCollection(), filePath);
		stringBuilder.append("Коллекция сохранена в файл\n");
		System.out.println();
		
		if (TempFileManager.isTempFileExist()) {
			TempFileManager.deleteTempFile();
			stringBuilder.append("Временный файл удален, т.к. есть нормальная версия\n");
		}
	}
	
	
	@Override
	public String getName() {
		return "save";
	}
	
	@Override
	public String getDescription() {
		return "сохранить коллекцию в файл";
	}
}
