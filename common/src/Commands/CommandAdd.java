package Commands;

import Input.Flat;
import Utils.Context;
import Utils.FlatCreator;

public class CommandAdd extends Command {
	public CommandAdd() {
		super();
	}
	
	@Override
	public void execute() {
		Flat flat = FlatCreator.getCreatedFlatFromTerminal(context.lineReader);
		context.collectionManager.addFlatToCollection(flat);
	}
	
	@Override
	public String getName() {
		return "add";
	}
	
	@Override
	public String getDescription() {
		return "добавить новый элемент в коллекцию";
	}
}
