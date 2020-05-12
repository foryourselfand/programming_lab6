package Commands;

import Input.Flat;
import Input.House;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Команда вывода уникальных значений поля дом
 */
public class CommandPrintUniqueHouse extends CommandWithNotEmptyCollection {
	public CommandPrintUniqueHouse() {
		super();
	}
	
	@Override
	public void execute() {
		Map<House, Integer> houseToCountMap = getHouseToCountMap();
		List<House> uniqueHouses = getUniqueHouses(houseToCountMap);
		printUniqueHouses(uniqueHouses);
	}
	
	private Map<House, Integer> getHouseToCountMap() {
		Map<House, Integer> houseToCountMap = new HashMap<>();
		
		for (Flat flat : this.context.collectionManager.getCollection()) {
			House houseCurrent = flat.getHouse();
			
			if (! houseToCountMap.containsKey(houseCurrent))
				houseToCountMap.put(houseCurrent, 1);
			else {
				Integer houseToCountPrevious = houseToCountMap.get(houseCurrent);
				Integer houseToCountIncremented = houseToCountPrevious + 1;
				houseToCountMap.put(houseCurrent, houseToCountIncremented);
			}
		}
		return houseToCountMap;
	}
	
	private List<House> getUniqueHouses(Map<House, Integer> houseToCountMap) {
		List<House> uniqueHouses = new ArrayList<>();
		for (Map.Entry<House, Integer> houseToCount : houseToCountMap.entrySet()) {
			if (houseToCount.getValue() == 1)
				uniqueHouses.add(houseToCount.getKey());
		}
		return uniqueHouses;
	}
	
	private void printUniqueHouses(List<House> uniqueHouses) {
		if (uniqueHouses.isEmpty())
			stringBuilderResponse.append("Уникальных значений поля house - нет\n");
		else {
			stringBuilderResponse.append("Уникальные значения поля house: \n");
			for (House house : uniqueHouses)
				stringBuilderResponse.append(house.toString()).append("\n");
		}
	}
	
	
	@Override
	public String getName() {
		return "print_unique_house";
	}
	
	@Override
	public String getDescription() {
		return "вывести уникальные значения поля house";
	}
}
