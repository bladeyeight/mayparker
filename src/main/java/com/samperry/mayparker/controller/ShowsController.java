package com.samperry.mayparker.controller;

import com.samperry.mayparker.database.dao.ShowDAO;
import com.samperry.mayparker.database.dao.SongDAO;
import com.samperry.mayparker.database.dao.UserDAO;
import com.samperry.mayparker.database.entity.Show;
import com.samperry.mayparker.database.entity.Song;
import com.samperry.mayparker.database.entity.User;
import com.samperry.mayparker.formbean.ShowFormBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
public class ShowsController {

    @Autowired
    private ShowDAO showDao;

    @Autowired
    private SongDAO songDao;

    @Autowired
    private UserDAO userDao;

    //Bring up the shows page
    @RequestMapping(value = "/shows", method = RequestMethod.GET)
    public ModelAndView Shows() throws Exception {
        ModelAndView response = new ModelAndView();
        response.setViewName("shows");

        List<Show> shows = new ArrayList();

        shows = showDao.findAll();

//        lambda
        shows.sort((o1, o2)
                -> o1.getDate().compareTo(
                o2.getDate()));

        response.addObject("shows", shows);

// stream for future enhancements
        LocalDate today = LocalDate.of(2022, 1, 1);
        List<Show> futureShows = shows.stream().filter(w -> w.getDate().toLocalDate().isAfter(today)).collect(Collectors.toList());
        for (int i = 0; i < futureShows.size(); i++) {
            log.info(futureShows.get(i) + "");
        }
        return response;
    }

    // Brings up the show edit form
    @RequestMapping(value = "/admin/showForm", method = RequestMethod.GET)
    public ModelAndView showForm() throws Exception {
        ModelAndView response = new ModelAndView();
        response.setViewName("admin/showForm");

        ShowFormBean form = new ShowFormBean();
        response.addObject("form", form);
        return response;
    }

    // submitting create for the show
    @RequestMapping(value = "/admin/showForm/registerShow", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView registerShow(@Valid ShowFormBean form, BindingResult bindingResult) throws Exception {
        ModelAndView response = new ModelAndView();

        log.info(form.toString());

//  makes page stay the same if there are errors
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
            response.setViewName("admin/showForm");
            return response;
        }

//        Load show from database
        Show show = showDao.findById(form.getId());

//         Edit show
        if (show == null) {

            show = new Show();
        }

//        creating the show
        show.setDate(form.getDate());
        show.setLocation(form.getLocation());
        show.setTime(form.getTime());
        showDao.save(show);

//        log.info(form.toString());

//  redirect back to shows page
        response.setViewName("redirect:/shows");

        return response;
    }

    @GetMapping("/admin/editShow/{showId}")
    public ModelAndView editShow(@PathVariable("showId") Integer showId) throws Exception {
        ModelAndView response = new ModelAndView();
        response.setViewName("admin/showForm");

//        pull up the show for edit
        Show show = showDao.findById(showId);

//        Utilize form bean to edit
        ShowFormBean form = new ShowFormBean();

        form.setId(show.getId());
        form.setDate(show.getDate());
        form.setLocation(show.getLocation());
        form.setTime(show.getTime());

//        pull up all the objects the JSP page needs
        List<Song> songs = new ArrayList<>();
        songs = songDao.findAll();
        List<User> users = new ArrayList<>();
        users = userDao.findAll();
        List<Song> showSongs = new ArrayList<Song>();
        showSongs = show.getSSongs();
        List<User> showUsers = new ArrayList<User>();
        showUsers = show.getSUsers();

//      Bring up the current user to add to a show
        User currentUser;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        currentUser = userDao.findByUsername(currentPrincipalName);


//      Literally adding everything to the JSP page
        response.addObject("form", form);
        response.addObject("songs", songs);
        response.addObject("users", users);
        response.addObject("showSongs", showSongs);
        response.addObject("showUsers", showUsers);
        response.addObject("currentUser", currentUser);

        return response;
    }

    @Transactional
    @RequestMapping(value = "/admin/removeShow/{showId}", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView deleteShow(@PathVariable("showId") Integer showId) throws Exception {
        ModelAndView response = new ModelAndView();

//      Pull up the show to delete
        showDao.deleteById(showId);

//        redirect back to show page
        response.setViewName("redirect:/shows");

        return response;

    }

    @RequestMapping(value = "/admin/addSong/{showId}/{songId}", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView addSongtoShow(@PathVariable("showId") Integer showId, @PathVariable("songId") Integer songId) throws Exception {
        ModelAndView response = new ModelAndView();

//        Pull up the show to add a song to
        Show show = showDao.findById(showId);

//        Pull up the song to add to the show
        Song song = songDao.findById(songId);

//        adding it
        List<Show> songShows = song.getSShows();
        songShows.add(show);
        song.setSShows(songShows);

//        Logs song and show to console
        log.info(song + " " + show.toString() + " " + show.getSSongs());

        songDao.save(song);

//        Redirect to edit show page
        response.setViewName("redirect:/admin/editShow/" + show.getId());
        return response;
    }

    @RequestMapping(value = "/admin/removeSong/{showId}/{songId}", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView removeSongFromShow(@PathVariable("showId") Integer showId, @PathVariable("songId") Integer songId) throws Exception {
        ModelAndView response = new ModelAndView();

//        Pull up the show to remove the song from
        Show show = showDao.findById(showId);

//        Pull up the song to remove
        Song song = songDao.findById(songId);

//        Remove it
        List<Show> songShows = song.getSShows();
        songShows.remove(show);
        song.setSShows(songShows);

//        Log it out
        log.info(song + " " + show.toString() + " " + show.getSSongs());

        songDao.save(song);

//        Redirect back to the edit show page
        response.setViewName("redirect:/admin/editShow/" + show.getId());
        return response;
    }

    @RequestMapping(value = "/admin/addUser/{showId}/{userId}", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView addUsertoShow(@PathVariable("showId") Integer showId, @PathVariable("userId") Integer userId) throws Exception {
        ModelAndView response = new ModelAndView();

//        Pull up the show to add a User to
        Show show = showDao.findById(showId);

//        Pull up the current user that is logged in
        User currentUser;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        currentUser = userDao.findByUsername(currentPrincipalName);

//        Add the show to the Users shows if the User isnt already attending the show
        List<Show> userShows = currentUser.getUShows();
        if (!show.getSUsers().contains(currentUser)) {
            userShows.add(show);
            currentUser.setUShows(userShows);
        }
        log.info(currentUser + " " + show + " " + show.getSSongs());

        userDao.save(currentUser);

//        Redirect back to edit show page
        response.setViewName("redirect:/admin/editShow/" + show.getId());
        return response;
    }

    @RequestMapping(value = "/admin/removeUser/{showId}/{userId}", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView removeUserFromShow(@PathVariable("showId") Integer showId, @PathVariable("userId") Integer userId) throws Exception {
        ModelAndView response = new ModelAndView();

//        Pull up the show to remove the User from
        Show show = showDao.findById(showId);

//        Pull up the User to remove from the show
        User user = userDao.findById(userId);

//      Remove the User from the show
        List<Show> userShows = user.getUShows();
        userShows.remove(show);
        user.setUShows(userShows);

//        Log out the removed User
        log.info(user + " " + show.toString() + " " + show.getSSongs());

        userDao.save(user);

//      Redirect back to Edit show page
        response.setViewName("redirect:/admin/editShow/" + show.getId());
        return response;
    }
}