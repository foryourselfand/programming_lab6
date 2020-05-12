package Commands;

import Input.Flat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Команда группировки эллементов коллекции по значению поля площади
 */
public class CommandGroupCountingByArea extends CommandWithNotEmptyCollection {
	public CommandGroupCountingByArea() {
		super();
	}
	
	@Override
	public void execute() {
		Map<Integer, List<Flat>> areaToFlatListMap = getAreaToFlatListMap();
		printGroupCountingByArea(areaToFlatListMap);
	}
	
	private Map<Integer, List<Flat>> getAreaToFlatListMap() {
		Map<Integer, List<Flat>> areaToFlatListMap = new HashMap<>();
		for (Flat flat : context.collectionManager.getCollection()) {
			Integer areaCurrent = flat.getArea();
			
			if (! areaToFlatListMap.containsKey(areaCurrent))
				areaToFlatListMap.put(areaCurrent, new ArrayList<>());
			
			List<Flat> areaToFlatList = areaToFlatListMap.get(areaCurrent);
			areaToFlatList.add(flat);
			areaToFlatListMap.put(areaCurrent, areaToFlatList);
		}
		return areaToFlatListMap;
	}
	
	private void printGroupCountingByArea(Map<Integer, List<Flat>> areaToFlatListMap) {
		for (Map.Entry<Integer, List<Flat>> areaToFlatList : areaToFlatListMap.entrySet()) {
			stringBuilder.append("Группа area=").append(areaToFlatList.getKey()).append("; Количество=").append(areaToFlatList.getValue().size());
			for (Flat flat : areaToFlatList.getValue())
				stringBuilder.append(flat.toString());
			stringBuilder.append("\n");
		}
	}
	
	@Override
	public String getName() {
		return "group_counting_by_area";
	}
	
	@Override
	public String getDescription() {
		return "сгруппировать элементы коллекции по значению поле area, вывести количество элементов в каждой группе";
	}
}
