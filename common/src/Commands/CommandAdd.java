package Commands;

import Input.Flat;
import Utils.Context;
import Utils.FlatCreator;

public class CommandAdd extends Command {
	private Flat flat;
	
	public CommandAdd() {
		super();
	}
	
	@Override
	public void preExecute() {
		flat = FlatCreator.getCreatedFlatFromTerminal(Context.lineReader);
	}
	
	@Override
	public void execute() {
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
