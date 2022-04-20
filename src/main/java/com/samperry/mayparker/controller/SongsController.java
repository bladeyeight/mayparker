package com.samperry.mayparker.controller;

import com.samperry.mayparker.database.dao.SongDAO;
import com.samperry.mayparker.database.entity.Song;
import com.samperry.mayparker.formbean.SongFormBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
public class SongsController {

    @Autowired
    private SongDAO songDao;

    //    Serve up the songs page
    @RequestMapping(value = "/songs", method = RequestMethod.GET)
    public ModelAndView Songs() throws Exception {
        ModelAndView response = new ModelAndView();
        response.setViewName("songs");

//        Bring up the songs from the database
        List<Song> songs = new ArrayList();
        songs = songDao.findAll();
        List<Integer> sLength = new ArrayList();

//        Add the number of shows each song is in to the page
        for (int i = 0; i < songs.size(); i++) {
            int length = songs.get(i).getSShows().size();
            sLength.add(length);
        }

//        Add the objects to the page
        response.addObject("songs", songs);
        response.addObject("sLength", sLength);


        return response;
    }

    //    Display the song edit page
    @RequestMapping(value = "/admin/songForm", method = RequestMethod.GET)
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

//

//        Keep the page the same if theres errors
        if (bindingResult.hasErrors()) {
            for (ObjectError error : bindingResult.getAllErrors()) {
                log.info(((FieldError) error).getField() + " " + error.getDefaultMessage());
            }
            response.addObject("form", form);
            response.addObject("bindingResult", bindingResult);
            response.setViewName("admin/songForm");
            return response;
        }
//  Check if the song already exists before creating
        Song song = songDao.findById(form.getId());
        if (song == null) {
            song = new Song();
        }

        song.setName(form.getName());
        songDao.save(song);

//        Log out my form
        log.info(form.toString());

//          Redirect back to the songs page
        response.setViewName("redirect:/songs");

        return response;
    }

    //    Only allow Admin to edit song
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/admin/edit/{songId}")
    public ModelAndView editSong(@PathVariable("songId") Integer songId) throws Exception {
        ModelAndView response = new ModelAndView();
        response.setViewName("admin/songForm");

//        Find the song to edit
        Song song = songDao.findById(songId);

//        Use form to edit the song
        SongFormBean form = new SongFormBean();
        form.setId(song.getId());
        form.setName(song.getName());

        // adding the SongFormBean to the model
        response.addObject("form", form);

        return response;
    }

    @Transactional
    @RequestMapping(value = "/admin/remove/{songId}", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView deleteSong(@PathVariable("songId") Integer songId) throws Exception {
        ModelAndView response = new ModelAndView();

//        Just delete the song

        songDao.deleteById(songId);

        response.setViewName("redirect:/songs");

        return response;

    }
}