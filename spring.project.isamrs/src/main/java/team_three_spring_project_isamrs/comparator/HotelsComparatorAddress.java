package team_three_spring_project_isamrs.comparator;

import java.util.Comparator;

import team_three_spring_project_isamrs.model.Hotel;

public class HotelsComparatorAddress implements Comparator<Hotel> {
	@Override
	public int compare(Hotel hotel1, Hotel hotel2) {

		// for comparison
		return hotel1.getAddress().compareTo(hotel2.getAddress());

	}
}
