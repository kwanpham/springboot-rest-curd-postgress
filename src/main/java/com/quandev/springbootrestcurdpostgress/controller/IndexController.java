package com.quandev.springbootrestcurdpostgress.controller;

import com.quandev.springbootrestcurdpostgress.model.Tutorial;
import com.quandev.springbootrestcurdpostgress.model.TutorialEnum;
import com.quandev.springbootrestcurdpostgress.model.TutorialReq;
import com.quandev.springbootrestcurdpostgress.repository.TutorialRepository;
import org.apache.commons.lang3.EnumUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {

    @Autowired
    TutorialRepository tr;

    @GetMapping("/")
    public String indexPage(Model model) {
        model.addAttribute("tutorialReq" , new TutorialReq());
        return "index";
    }

    @ResponseBody
    @PostMapping("/search")
    public ResponseEntity searchPage(@RequestBody TutorialReq req) {
        System.out.println(req);
        Page<Tutorial> page= tr.findDysnamic(req.getTitle() , req.getDescription() , req.getPublished() , EnumUtils.getEnum(TutorialEnum.class,req.getStatus()) , PageRequest.of(req.getPage() , req.getSize()));
        if (page.isEmpty()){
            return ResponseEntity.noContent().build();
        } else
            return ResponseEntity.ok(page);
    }

}
