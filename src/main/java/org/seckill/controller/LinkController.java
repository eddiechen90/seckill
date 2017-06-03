package org.seckill.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Eddie on 2017/6/3.
 */

@Controller
public class LinkController {

    @RequestMapping("/")
    public String toList() {
        return "redirect:/seckill/list";
    }
}
