package Commands;

import Input.Flat;
import Utils.FlatCreator;

public class CommandAddIfMax extends CommandWithNotEmptyCollection {
	@Override
	public void execute() {
		Flat flatNew = getFlatNew();
		
		if (context.collectionManager.getIsCollectionEmpty()) {
			context.collectionManager.addFlatToCollection(flatNew);
		} else {
			Flat flatMax = getFlatMax();
			addFlatNewToCollectionIfGreaterThatFlatMax(flatNew, flatMax);
		}
	}
	
	private Flat getFlatNew() {
		Flat flatNew = FlatCreator.getCreatedFlatFromTerminal(context.lineReader);
		stringBuilder.append("Новый элемент ").append(flatNew.toString()).append("\n");
		System.out.println();
		return flatNew;
	}
	
	private Flat getFlatMax() {
		Flat flatMax = context.collectionManager.getFlatMax();
		stringBuilder.append("Наибольший элемент коллекции ").append(flatMax.toString()).append("\n");
		return flatMax;
	}
	
	private void addFlatNewToCollectionIfGreaterThatFlatMax(Flat flatNew, Flat flatMax) {
		if (flatNew.compareTo(flatMax) > 0)
			addFlatNewToCollection(flatNew);
		else
			dontAddFlatNewToCollection();
	}
	
	private void addFlatNewToCollection(Flat flatNew) {
		stringBuilder.append("Значение нового элемента превышает значение наибольшего элемента коллекции\n");
		context.collectionManager.addFlatToCollection(flatNew);
	}
	
	private void dontAddFlatNewToCollection() {
		stringBuilder.append("Значение нового элемента не превышает значение наибольшего элемента коллекции\n");
		stringBuilder.append("В коллекцию элемент не добавлен");
	}
	
	@Override
	public String getName() {
		return "add_if_max";
	}
	
	@Override
	public String getDescription() {
		return "добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции";
	}
}
