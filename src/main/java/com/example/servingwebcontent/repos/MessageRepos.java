package com.example.servingwebcontent.repos;

import com.example.servingwebcontent.entities.Massage;
import org.springframework.data.repository.CrudRepository;

public interface MessageRepos extends CrudRepository<Massage, Integer> {

}
