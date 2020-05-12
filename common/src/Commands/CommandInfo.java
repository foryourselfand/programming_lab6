package Commands;

/**
 * Команда вывода в стандартный поток информации о коллекции (тип, дата инициализации, количество элементов и т.д.)
 */
public class CommandInfo extends Command {
	public CommandInfo() {
		super();
	}
	
	@Override
	public void execute() {
		stringBuilder.append("Тип коллекции: ").append(context.collectionManager.getCollectionType()).append("\n");
		stringBuilder.append("Тип элементов коллекции: ").append(context.collectionManager.getCollectionElementType()).append("\n");
		stringBuilder.append("Дата инициализации коллекции: ").append(context.collectionManager.getCollectionInitializationDate()).append("\n");
		stringBuilder.append("Количество элементов в коллекции: ").append(context.collectionManager.getCollectionSize()).append("\n");
	}
	
	@Override
	public String getName() {
		return "info";
	}
	
	@Override
	public String getDescription() {
		return "вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)";
	}
}
