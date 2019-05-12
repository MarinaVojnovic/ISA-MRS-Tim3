package team_three_spring_project_isamrs.comparator;

import java.util.Comparator;

import team_three_spring_project_isamrs.model.Rentacar;

public class RentACarComparatorAddress implements Comparator<Rentacar>{
	@Override
    public int compare(Rentacar r1, Rentacar r2) { 

        // for comparison 
        return r1.getAddress().compareTo(r2.getAddress()); 
       
	   }
}
