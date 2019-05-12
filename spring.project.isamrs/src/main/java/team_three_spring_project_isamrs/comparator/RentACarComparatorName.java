package team_three_spring_project_isamrs.comparator;

import java.util.Comparator;

import team_three_spring_project_isamrs.model.Rentacar;

public class RentACarComparatorName implements Comparator<Rentacar>{
	@Override
    public int compare(Rentacar r1, Rentacar r2) { 

        // for comparison 
        return r1.getName().compareTo(r2.getName()); 
       
	   }
}
