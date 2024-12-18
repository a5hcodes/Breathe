package com.app.breathe;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class hello {

    @GetMapping("abc")
    public String hell(){
        return "Hellow";
    }
}
