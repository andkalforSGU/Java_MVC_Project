package com.example.servingwebcontent;

import com.example.servingwebcontent.entities.Massage;
import com.example.servingwebcontent.repos.MessageRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
public class MainController {

    @Autowired
    private MessageRepos messagesRepos;

    @GetMapping("/greeting")
    public String greeting(Map<String, Object> model) {

        return "greeting";
    }

    @GetMapping
    public String main(Map<String, Object> model) {
        Iterable<Massage> messages = messagesRepos.findAll();

        return(homePage(model, messages));
    }



    @PostMapping("add")
    public String add(Map<String, Object> model, @RequestParam String text, @RequestParam String tag){
        Massage message = new Massage(text, tag);
        messagesRepos.save(message);
        Iterable<Massage> messages = messagesRepos.findAll();

        return(homePage(model, messages));
    }

    @PostMapping("filter")
    public String filter(Map<String, Object> model, @RequestParam String filter) {

        Iterable<Massage> messages;
        if (filter != null && !filter.isEmpty()){
            messages = messagesRepos.findByTag(filter);
        } else messages = messagesRepos.findAll();

        return(homePage(model, messages));
    }

    @PostMapping("deleteAll")
    public String deleteAll(Map<String, Object> model) {

        Iterable<Massage> messages;
        messagesRepos.deleteAll();
        messages = messagesRepos.findAll();

        return(homePage(model, messages));
    }

    @PostMapping("home")
    public String home(Map<String, Object> model){
        Iterable<Massage> messages = messagesRepos.findAll();
        //model.put("messages", messages);
        //return "main";
        return(homePage(model, messages));
    }

    private String homePage(Map<String, Object> model, Iterable<Massage> messages){
        model.put("messages", messages);
        return "main";
    }
}
