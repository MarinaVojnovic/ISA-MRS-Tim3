package team_three_spring_project_isamrs.comparator;

import java.util.Comparator;

import team_three_spring_project_isamrs.model.User;

public class FriendsComparatorNameSurname implements Comparator<User>{
	@Override
    public int compare(User user1, User user2) { 

        // for comparison 
        int nameCompare = user1.getFirstName().compareTo(user1.getFirstName()); 
        int surnameCompare = user1.getLastName().compareTo(user1.getLastName()); 
        return nameCompare+surnameCompare;
       
	   }
}
