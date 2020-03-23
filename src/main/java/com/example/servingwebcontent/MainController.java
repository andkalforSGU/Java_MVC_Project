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
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Map<String, Object> model) {
        model.put("name", name);
        return "greeting";
    }

    @GetMapping
    public String main(Map<String, Object> model) {
        Iterable<Massage> messages = messagesRepos.findAll();

        model.put("messages", messages);
        return "main";
    }



    @PostMapping
    public String add(Map<String, Object> model, @RequestParam String text, @RequestParam String tag){
        Massage message = new Massage(text, tag);
        messagesRepos.save(message);
        Iterable<Massage> massages = messagesRepos.findAll();

        model.put("messages", massages);
        return "main";
    }

    @PostMapping("filter")
    public String filter(Map<String, Object> model, @RequestParam String filter) {

        List<Massage> massages = messagesRepos.findByTag(filter);

        model.put("messages", massages);
        return "main";
    }

    @PostMapping("home")
    public String home(Map<String, Object> model){
        Iterable<Massage> messages = messagesRepos.findAll();
        model.put("messages", messages);
        return "main";
    }
}
