package com.pi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@RestController
//@RequestMapping("/person")
public class RobotPiController {


    @RequestMapping(method = RequestMethod.POST)
    public void addPerson(@RequestBody String person) {
       // th

    }

    //    @RequestMapping(method = RequestMethod.GET)
    @GetMapping
    public String getName() {
        return "i am robot pi";
    }
}
