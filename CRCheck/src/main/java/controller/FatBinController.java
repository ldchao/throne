package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by mm on 2016/7/25.
 */
@Controller
public class FatBinController {
    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView getFirstPage(ModelMap map){
        ModelAndView modelAndView=new ModelAndView("shabichao");
        return modelAndView;
    }
}

