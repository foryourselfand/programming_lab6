package Commands;

import Errors.CollectionIsEmptyError;
import Utils.Context;

/**
 * Команда, для выполнения которых необходима не пустая коллекция
 */
public abstract class CommandWithNotEmptyCollection extends Command {
	public CommandWithNotEmptyCollection() {
		super();
	}
	
	@Override
	public void printDescriptionAndExecute(Context context) {
		this.validateCollectionSize();
		super.printDescriptionAndExecute(context);
	}
	
	public void validateCollectionSize() {
		boolean collectionIsEmpty = context.collectionManager.getIsCollectionEmpty();
		if (collectionIsEmpty) {
			throw new CollectionIsEmptyError();
		}
	}
}
