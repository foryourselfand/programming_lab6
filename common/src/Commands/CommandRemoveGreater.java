package Commands;

import Input.Flat;
import Utils.FlatCreator;

import java.util.ArrayList;
import java.util.List;

/**
 * Команда удаления из коллекции элементов, превышающих заданный
 */
public class CommandRemoveGreater extends CommandWithNotEmptyCollection {
	public CommandRemoveGreater() {
		super();
	}
	
	@Override
	public void execute() {
		Flat flatNew = getCreatedFlat();
		List<Long> idsToRemove = getIdsOfFlatsGreaterThatFlat(flatNew);
		removeFlatsGreaterThatFlat(idsToRemove);
	}
	
	private Flat getCreatedFlat() {
		Flat createdFlat = FlatCreator.getCreatedFlatFromTerminal(context.lineReader);
		stringBuilder.append("Созданный элемент для сравнения ").append(createdFlat.toString());
		return createdFlat;
	}
	
	private List<Long> getIdsOfFlatsGreaterThatFlat(Flat flatNew) {
		List<Long> idsToRemove = new ArrayList<>();
		for (Flat flatCurrent : context.collectionManager.getCollection()) {
			if (flatCurrent.compareTo(flatNew) > 0)
				idsToRemove.add(flatCurrent.getId());
		}
		return idsToRemove;
	}
	
	private void removeFlatsGreaterThatFlat(List<Long> idsToRemove) {
		context.flatRemover.removeFlatsById(idsToRemove);
	}
	
	@Override
	public String getName() {
		return "remove_greater";
	}
	
	@Override
	public String getDescription() {
		return "удалить из коллекции все элементы, превышающие заданный";
	}
}
