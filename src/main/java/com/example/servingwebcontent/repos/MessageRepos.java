package com.example.servingwebcontent.repos;

import com.example.servingwebcontent.entities.Massage;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MessageRepos extends CrudRepository<Massage, Integer> {

    List<Massage>findByTag(String tag);

}
