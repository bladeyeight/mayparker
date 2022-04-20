package com.samperry.mayparker.controller;

import com.samperry.mayparker.database.dao.UserDAO;
import com.samperry.mayparker.database.dao.UserRoleDAO;
import com.samperry.mayparker.database.entity.User;
import com.samperry.mayparker.database.entity.UserRole;
import com.samperry.mayparker.formbean.RegisterFormBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Slf4j
@Controller
public class UserController {

    @Autowired
    private UserDAO userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRoleDAO userRoleDao;

    //      Pull up the register page
    @RequestMapping(value = "/user/register", method = RequestMethod.GET)
    public ModelAndView create() throws Exception {

        ModelAndView response = new ModelAndView();
        response.setViewName("user/register");

        RegisterFormBean form = new RegisterFormBean();
        response.addObject("form", form);

        return response;
    }

    @RequestMapping(value = "/user/registerSubmit", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView registerSubmit(@Valid RegisterFormBean form, BindingResult bindingResult) throws Exception {
        ModelAndView response = new ModelAndView();

//        dealing with binding result to display errors on JSP page
        if (bindingResult.hasErrors()) {

            for (ObjectError error : bindingResult.getAllErrors()) {
                log.info(((FieldError) error).getField() + " " + error.getDefaultMessage());
            }

            // add the form back to the model so we can fill up the input fields
            // so the user can correct the input and does not have type it all again
            response.addObject("form", form);

            // add the error list to the model
            response.addObject("bindingResult", bindingResult);

            // because there is 1 or more error we do not want to process the logic below
            // that will create a new user in the database.   We want to show the register.jsp
            response.setViewName("user/register");
            return response;
        }
        // we first assume that we are going to try to load the user from
        // the database using the incoming id on the form
        User user = userDao.findById(form.getId());

        // if the user is not null the know it is an edit
        if (user == null) {
            // now, if the user from the database is null then it means we did not
            // find this user.   Therefore, it is a create.
            user = new User();
        }
//        Set User Attributes
        user.setUsername(form.getUsername());
        String password = passwordEncoder.encode(form.getPassword());
        user.setPassword(password);

        userDao.save(user);

//        Add UserRole to the User and defaults at USER
        UserRole userRole = new UserRole();
        userRole.setUserId(user.getId());
        userRole.setUserRole("USER");

        userRoleDao.save(userRole);

//      Log out the new form
        log.info(form.toString());


        // here instaed of showing a view, we want to redirect to the edit page
        // the edit page will then be responsible for loading the user from the
        // database and dynamically creating the page
        // when you use redirect: as part of the view name it triggers spring to tell the
        // browser to do a redirect to the URL after the :    The big piece here to
        // recognize that redirect: uses an actual URL rather than a view name path.
        response.setViewName("redirect:/index");

        return response;
    }

}



