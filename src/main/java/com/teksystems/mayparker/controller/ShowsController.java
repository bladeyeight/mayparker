package com.teksystems.mayparker.controller;

import com.teksystems.mayparker.database.dao.ShowDAO;
import com.teksystems.mayparker.database.dao.SongDAO;
import com.teksystems.mayparker.database.entity.Show;
import com.teksystems.mayparker.database.entity.Song;
import com.teksystems.mayparker.formbean.ShowFormBean;
import com.teksystems.mayparker.formbean.SongFormBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
public class ShowsController {

    @Autowired
    private ShowDAO showDao;

    @Autowired
    private SongDAO songDao;

    @RequestMapping(value = "/shows", method = RequestMethod.GET)
    public ModelAndView Shows() throws Exception {
        ModelAndView response = new ModelAndView();
        response.setViewName("shows");

        List<Show> shows = new ArrayList();

        shows = showDao.findAll();
        shows.sort((o1, o2)
                -> o1.getDate().compareTo(
                o2.getDate()));

        response.addObject("shows", shows);


        return response;
    }

    @RequestMapping(value = "/admin/showForm", method = RequestMethod.GET )
    public ModelAndView showForm() throws Exception {
        ModelAndView response = new ModelAndView();
        response.setViewName("admin/showForm");

        ShowFormBean form = new ShowFormBean();
        response.addObject("form", form);
        return response;
    }
    @RequestMapping(value = "/admin/showForm/registerShow", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView registerShow(@Valid ShowFormBean form, BindingResult bindingResult) throws Exception {
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
            response.setViewName("admin/showForm");
            return response;
        }

        // we first assume that we are going to try to load the user from
        // the database using the incoming id on the form
        Show show = showDao.findById(form.getId());

        // if the song is not null know it is an edit
        if (show == null) {
            // now, if the song from the database is null then it means we did not
            // find this song.   Therefore, it is a create.
            show = new Show();
        }

        show.setDate(form.getDate());
        show.setLocation(form.getLocation());
        show.setTime(form.getTime());
        showDao.save(show);

//        log.info(form.toString());

        // here instaed of showing a view, we want to redirect to the edit page
        // the edit page will then be responsible for loading the user from the
        // database and dynamically creating the page
        // when you use redirect: as part of the view name it triggers spring to tell the
        // browser to do a redirect to the URL after the :    The big piece here to
        // recognize that redirect: uses an actual URL rather than a view name path.
        response.setViewName("redirect:/shows");

        return response;
    }
    @GetMapping("/admin/editShow/{showId}")
    //public ModelAndView editUser(@RequestParam("userId") Integer userId) throws Exception {
    public ModelAndView editShow(@PathVariable("showId") Integer showId) throws Exception {
        ModelAndView response = new ModelAndView();
        response.setViewName("admin/showForm");

        Show show = showDao.findById(showId);

        ShowFormBean form = new ShowFormBean();

        form.setId(show.getId());
        form.setDate(show.getDate());
        form.setLocation(show.getLocation());
        form.setTime(show.getTime());

        List<Song> songs = new ArrayList<>();
        songs = songDao.findAll();

        List<Song> showSongs = new ArrayList<Song>();
        showSongs = show.getSSongs();

        // in this case we are adding the SongFormBean to the model
        response.addObject("form", form);
        response.addObject("songs", songs);
        response.addObject("showSongs", showSongs);

        return response;
    }
    @Transactional
    @RequestMapping(value = "/admin/removeShow/{showId}", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView deleteShow(@PathVariable("showId") Integer showId) throws Exception {
        ModelAndView response = new ModelAndView();


        showDao.deleteById(showId);

        response.setViewName("redirect:/shows");

        return response;

    }
    @RequestMapping(value = "/admin/addSong/{showId}/{songId}", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView addSongtoShow(@PathVariable("showId") Integer showId, @PathVariable("songId") Integer songId) throws Exception {
        ModelAndView response = new ModelAndView();

        Show show = showDao.findById(showId);


        Song song = songDao.findById(songId);

//        List<Song> showSongs = show.getSSongs();
//        showSongs.add(song);
//        show.setSSongs(showSongs);

        List<Show> songShows = song.getSShows();
        songShows.add(show);
        song.setSShows(songShows);

        log.info(song.toString() + " " + show.toString() + " " + show.getSSongs());

        songDao.save(song);

        response.setViewName("redirect:/admin/editShow/" + show.getId());
        return response;
    }

    @RequestMapping(value = "/admin/removeSong/{showId}/{songId}", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView removeSongFromShow(@PathVariable("showId") Integer showId, @PathVariable("songId") Integer songId) throws Exception {
        ModelAndView response = new ModelAndView();

        Show show = showDao.findById(showId);


        Song song = songDao.findById(songId);

//        List<Song> showSongs = show.getSSongs();
//        showSongs.add(song);
//        show.setSSongs(showSongs);

        List<Show> songShows = song.getSShows();
        songShows.remove(show);
        song.setSShows(songShows);

        log.info(song.toString() + " " + show.toString() + " " + show.getSSongs());

        songDao.save(song);

        response.setViewName("redirect:/admin/editShow/" + show.getId());
        return response;
    }
}