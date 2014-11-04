package com.github.nicolediana;

import com.github.nicolediana.model.Film;
import com.github.nicolediana.exception.FieldValidationException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by pcnicole on 31/10/2014.
 */
public class FilmResource extends Controller {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            Film film = Film.validate(Film.class, request.getParameter("id"));
            if(film == null)
                throw new FieldValidationException();

            PrintWriter writer = response.getWriter();
            writer.println(film.toJson());
        } catch (FieldValidationException e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String json = this.readBody(request.getReader());
            Film nuovoFilm = Film.fromJson(Film.class, json);
            System.out.println(nuovoFilm.toJson());
            nuovoFilm.save();

            PrintWriter writer = response.getWriter();
            writer.println(nuovoFilm.toJson());
        } catch(FieldValidationException e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    @Override
    public void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String json = this.readBody(request.getReader());
            Film nuovoFilm = Film.fromJson(Film.class, json);
            nuovoFilm.update();
            PrintWriter writer = response.getWriter();
            writer.println(nuovoFilm.toJson());
        } catch(FieldValidationException e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    @Override
    public void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            Film film = Film.validate(Film.class, request.getParameter("id"));
            film.delete();
        } catch (FieldValidationException e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

}
