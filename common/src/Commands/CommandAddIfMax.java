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
		System.out.println("Новый элемент " + flatNew.toString());
		return flatNew;
	}
	
	private Flat getFlatMax() {
		Flat flatMax = context.collectionManager.getFlatMax();
		System.out.println("Наибольший элемент коллекции " + flatMax.toString());
		return flatMax;
	}
	
	private void addFlatNewToCollectionIfGreaterThatFlatMax(Flat flatNew, Flat flatMax) {
		if (flatNew.compareTo(flatMax) > 0)
			addFlatNewToCollection(flatNew);
		else
			dontAddFlatNewToCollection();
	}
	
	private void addFlatNewToCollection(Flat flatNew) {
		System.out.println("Значение нового элемента превышает значение наибольшего элемента коллекции");
		context.collectionManager.addFlatToCollection(flatNew);
	}
	
	private void dontAddFlatNewToCollection() {
		System.out.println("Значение нового элемента не превышает значение наибольшего элемента коллекции");
		System.out.println("В коллекцию элемент не добавлен");
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
