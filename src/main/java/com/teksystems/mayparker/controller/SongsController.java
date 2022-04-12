package com.teksystems.mayparker.controller;

import com.teksystems.mayparker.database.dao.SongDAO;
import com.teksystems.mayparker.database.entity.Show;
import com.teksystems.mayparker.database.entity.Song;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.teksystems.mayparker.database.dao.UserDAO;
import com.teksystems.mayparker.database.entity.User;

import java.util.ArrayList;
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
}