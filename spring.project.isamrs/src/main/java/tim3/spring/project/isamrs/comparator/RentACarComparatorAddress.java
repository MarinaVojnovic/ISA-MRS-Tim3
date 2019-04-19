package tim3.spring.project.isamrs.comparator;

import java.util.Comparator;

import tim3.spring.project.isamrs.model.Rentacar;

public class RentACarComparatorAddress implements Comparator<Rentacar>{
	@Override
    public int compare(Rentacar r1, Rentacar r2) { 

        // for comparison 
        int addressCompare = r1.getAddress().compareTo(r2.getAddress()); 
        return addressCompare;
       
	   }
}
