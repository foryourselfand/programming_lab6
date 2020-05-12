package Commands;

import Input.Flat;

public class CommandAverageOfHeight extends CommandWithNotEmptyCollection {
	public CommandAverageOfHeight() {
		super();
	}
	
	@Override
	public void execute() {
		int heightTotal = 0;
		
		for (Flat flat : this.context.collectionManager.getCollection()) {
			Integer heightCurrent = flat.getHeight();
			if (heightCurrent == null)
				continue;
			heightTotal += heightCurrent;
		}
		
		int itemsTotal = this.context.collectionManager.getCollectionSize();
		float heightAverage = (float) heightTotal / itemsTotal;
		
		stringBuilder.append(String.format("Среднее значение поля height для всех элементов коллекции: %.2f\n", heightAverage));
	}
	
	@Override
	public String getName() {
		return "average_of_height";
	}
	
	@Override
	public String getDescription() {
		return "вывести среднее значение поля height для всех элементов коллекции";
	}
}
