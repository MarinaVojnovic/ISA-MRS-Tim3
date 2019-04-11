package tim3.spring.project.comparator;

import java.util.Comparator;

import tim3.spring.project.isamrs.model.Hotel;
import tim3.spring.project.isamrs.model.Rentacar;

public class RentACarComparatorName implements Comparator<Rentacar>{
	@Override
    public int compare(Rentacar r1, Rentacar r2) { 

        // for comparison 
        int nameCompare = r1.getName().compareTo(r2.getName()); 
        return nameCompare;
       
	   }
}
