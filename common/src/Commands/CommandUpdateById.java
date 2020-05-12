package Commands;

import Expectations.Argument;
import Expectations.ExpectedIdExist;
import Expectations.ExpectedType.ExpectedLong;
import Input.Flat;
import Utils.FlatCreator;

import java.util.List;

public class CommandUpdateById extends CommandWithNotEmptyCollection {
	public CommandUpdateById() {
		super();
	}
	
	@Override
	protected void addArgumentValidators(List<Argument> arguments) {
		arguments.add(new Argument("id",
				new ExpectedLong(),
				new ExpectedIdExist()));
	}
	
	@Override
	public void execute() {
		long idToRemove = Long.parseLong(commandArguments[0]);
		Flat flatNew = createFlatNew();
		removeFlatOld(idToRemove);
		addFlatNew(flatNew);
	}
	
	private Flat createFlatNew() {
		return FlatCreator.getCreatedFlatFromTerminal(this.context.lineReader);
	}
	
	private void removeFlatOld(long idToRemove) {
		context.flatRemover.removeFlatsById(idToRemove);
	}
	
	private void addFlatNew(Flat flatNew) {
		context.collectionManager.addFlatToCollection(flatNew);
	}
	
	@Override
	public String getName() {
		return "update_by_id";
	}
	
	@Override
	public String getDescription() {
		return "обновить значение элемента коллекции, id которого равен заданному";
	}
}
