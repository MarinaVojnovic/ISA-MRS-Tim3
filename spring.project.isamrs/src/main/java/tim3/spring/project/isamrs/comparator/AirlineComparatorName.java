package tim3.spring.project.isamrs.comparator;

import java.util.Comparator;

import tim3.spring.project.isamrs.model.Airline;

public class AirlineComparatorName implements Comparator<Airline>{
	@Override
    public int compare(Airline airline1, Airline airline2) { 

        // for comparison 
        int nameCompare = airline1.getName().compareTo(airline2.getName()); 
        return nameCompare;
       
	   }
}
