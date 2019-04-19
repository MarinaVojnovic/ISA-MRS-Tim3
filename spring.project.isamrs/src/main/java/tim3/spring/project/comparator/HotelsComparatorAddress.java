package tim3.spring.project.comparator;

import java.util.Comparator;

import tim3.spring.project.isamrs.model.Hotel;

public class HotelsComparatorAddress implements Comparator<Hotel> {
	@Override
	public int compare(Hotel hotel1, Hotel hotel2) {

		// for comparison
		int addressCompare = hotel1.getAddress().compareTo(hotel2.getAddress());
		return addressCompare;

	}
}
