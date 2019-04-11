package tim3.spring.project.comparator;

import java.util.Comparator;

import tim3.spring.project.isamrs.model.Hotel;

public class HotelsComparatorName implements Comparator<Hotel> {  
	   @Override
       public int compare(Hotel hotel1, Hotel hotel2) { 
 
           // for comparison 
           int nameCompare = hotel1.getName().compareTo(hotel2.getName()); 
           return nameCompare;
          
	   }
}
