package com.samperry.mayparker.controller;

import com.samperry.mayparker.database.dao.UserDAO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
public class LoginController {

    @Autowired
    private UserDAO userDao;

    @RequestMapping(value = "/login/login", method = RequestMethod.GET )
    public ModelAndView Login() throws Exception {
        ModelAndView response = new ModelAndView();

        response.setViewName("login/loginForm");

        return response;
    }
}
