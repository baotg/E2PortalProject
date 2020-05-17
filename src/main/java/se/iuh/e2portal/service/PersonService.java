package se.iuh.e2portal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.iuh.e2portal.repository.PersonReposiory;
@Service
public class PersonService {
    @Autowired
    private PersonReposiory personReposiory;
    public String getName(String id){
        String names = personReposiory.getNameById(id);
        if(names!=null && !names.isEmpty()){
        	String[] temp = names.split(",");
        	String name = "";
        	for(String s : temp) {
        		name += " ";
        		name += s;
        	}
        	return name.trim();
        }
        return "";
    }
}
