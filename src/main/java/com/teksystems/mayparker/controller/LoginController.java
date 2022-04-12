package com.teksystems.mayparker.controller;

import com.teksystems.mayparker.database.dao.UserDAO;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


import java.util.List;

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
