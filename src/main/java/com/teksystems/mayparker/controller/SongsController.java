package com.teksystems.mayparker.controller;

import com.teksystems.mayparker.database.dao.SongDAO;
import com.teksystems.mayparker.database.entity.Show;
import com.teksystems.mayparker.database.entity.Song;
import com.teksystems.mayparker.formbean.RegisterFormBean;
import com.teksystems.mayparker.formbean.SongFormBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import com.teksystems.mayparker.database.dao.UserDAO;
import com.teksystems.mayparker.database.entity.User;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Controller
public class SongsController {

    @Autowired
    private SongDAO songDao;

    @RequestMapping(value = "/songs", method = RequestMethod.GET )
    public ModelAndView Songs() throws Exception {
        ModelAndView response = new ModelAndView();
        response.setViewName("songs");

        List<Song> songs = new ArrayList();

        songs = songDao.findAll();
        List<Integer> sLength = new ArrayList();


        for (int i = 0; i< songs.size(); i++){
            int length = songs.get(i).getSShows().size();
            sLength.add(length);
        }

        response.addObject("songs", songs);
        response.addObject("sLength", sLength);


        return response;
    }

    @RequestMapping(value = "/admin/songForm", method = RequestMethod.GET )
    public ModelAndView songForm() throws Exception {
        ModelAndView response = new ModelAndView();
        response.setViewName("admin/songForm");

        SongFormBean form = new SongFormBean();
        response.addObject("form", form);
        return response;
    }
    @RequestMapping(value = "/admin/songForm/registerSong", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView registerSong(@Valid SongFormBean form, BindingResult bindingResult) throws Exception {
        ModelAndView response = new ModelAndView();

        log.info(form.toString());

//        int i = 10/0;

        if (bindingResult.hasErrors()) {

            for (ObjectError error : bindingResult.getAllErrors()) {
                log.info( ((FieldError)error).getField() + " " +  error.getDefaultMessage());
            }

            // add the form back to the model so we can fill up the input fields
            // so the user can correct the input and does not have type it all again
            response.addObject("form", form);

            // add the error list to the model
            response.addObject("bindingResult", bindingResult);

            // because there is 1 or more error we do not want to process the logic below
            // that will create a new user in the database.   We want to show the register.jsp
            response.setViewName("admin/songForm");
            return response;
        }

        // we first assume that we are going to try to load the user from
        // the database using the incoming id on the form
        Song song = songDao.findById(form.getId());

        // if the song is not null know it is an edit
        if (song == null) {
            // now, if the song from the database is null then it means we did not
            // find this song.   Therefore, it is a create.
            song = new Song();
        }

        song.setName(form.getName());
        songDao.save(song);

        log.info(form.toString());

        // here instaed of showing a view, we want to redirect to the edit page
        // the edit page will then be responsible for loading the user from the
        // database and dynamically creating the page
        // when you use redirect: as part of the view name it triggers spring to tell the
        // browser to do a redirect to the URL after the :    The big piece here to
        // recognize that redirect: uses an actual URL rather than a view name path.
        response.setViewName("redirect:/songs");

        return response;
    }
//    @PreAuthorize("hasAuthority('ADMIN')")
    //@RequestMapping(value = "/user/edit/{userId}", method = RequestMethod.GET)
    @GetMapping("/admin/edit/{songId}")
    //public ModelAndView editUser(@RequestParam("userId") Integer userId) throws Exception {
    public ModelAndView editSong(@PathVariable("songId") Integer songId) throws Exception {
        ModelAndView response = new ModelAndView();
        response.setViewName("admin/songForm");

        Song song = songDao.findById(songId);

        SongFormBean form = new SongFormBean();

        form.setId(song.getId());
        form.setName(song.getName());

        // in this case we are adding the SongFormBean to the model
        response.addObject("form", form);

        return response;
    }
    @Transactional
    @RequestMapping(value = "/admin/remove/{songId}", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView deleteSong(@PathVariable("songId") Integer songId) throws Exception {
        ModelAndView response = new ModelAndView();


        songDao.deleteById(songId);

        response.setViewName("redirect:/songs");

        return response;

    }
}