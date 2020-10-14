package com.quandev.springbootrestcurdpostgress.controller;

import com.quandev.springbootrestcurdpostgress.model.Tutorial;
import com.quandev.springbootrestcurdpostgress.model.TutorialEnum;
import com.quandev.springbootrestcurdpostgress.model.TutorialReq;
import com.quandev.springbootrestcurdpostgress.repository.TutorialRepository;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.EnumUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Slf4j
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
        log.debug(req.toString());
        Page<Tutorial> page= tr.findDysnamic(req.getTitle() , req.getDescription() , req.getPublished() , EnumUtils.getEnum(TutorialEnum.class,req.getStatus()) , PageRequest.of(req.getPage() , req.getSize()));
        if (page.isEmpty()){
            return ResponseEntity.noContent().build();
        } else
            return ResponseEntity.ok(page);
    }

    @ResponseBody
    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteMutil(@RequestBody List<Long> ids) {
        log.debug(ids.toString());
        if (ids.isEmpty()){
            return ResponseEntity.badRequest().build();
        } else {
            ids.forEach(id -> tr.deleteToturial(TutorialEnum.INACTIVE , id));
        }
        return ResponseEntity.ok().build();

    }


}
