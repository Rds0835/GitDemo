package controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description:
 *
 * @author:
 * @date: 2018-08-02 上午11:11
 **/

@RestController
public class DemoController {

    @PostMapping(value="/demo")
    public String demo() {
        return "demo";
    }

    @PostMapping(value="/test")
    public String test(){
        return "test";
    }
}
