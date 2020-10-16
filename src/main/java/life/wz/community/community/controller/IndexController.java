package life.wz.community.community.controller;

import life.wz.community.community.Service.QuestionService;
import life.wz.community.community.dto.PaginationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexController {



    @Autowired
    private QuestionService questionService;


    @GetMapping("/")
    public String index(Model model,
                        @RequestParam(name = "page", defaultValue = "1") Integer page,
                        @RequestParam(name = "size", defaultValue = "5") Integer size,
                        @RequestParam(name = "search", required = false) String search
                        ) {


        PaginationDTO paginationDTO = questionService.list(search,page, size);
        model.addAttribute("pagination", paginationDTO);
        model.addAttribute("search",search);

        return "index";
    }
}
