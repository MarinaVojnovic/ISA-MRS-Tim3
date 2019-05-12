package team_three_spring_project_isamrs.comparator;

import java.util.Comparator;

import team_three_spring_project_isamrs.model.Airline;

public class AirlineComparatorAddress implements Comparator<Airline> {
	@Override
	public int compare(Airline airline1, Airline airline2) {

		// for comparison
		return airline1.getAddress().compareTo(airline2.getAddress());

	}

}
