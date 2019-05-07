package tim3.spring.project.isamrs.comparator;

import java.util.Comparator;

import tim3.spring.project.isamrs.model.Airline;

public class AirlineComparatorAddress implements Comparator<Airline> {
	@Override
	public int compare(Airline airline1, Airline airline2) {

		// for comparison
		return airline1.getAddress().compareTo(airline2.getAddress());

	}

}
