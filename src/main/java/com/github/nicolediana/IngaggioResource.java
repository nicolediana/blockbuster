package com.github.nicolediana;

import com.github.nicolediana.exception.FieldValidationException;
import com.github.nicolediana.model.Film;
import com.github.nicolediana.model.Attore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by pcnicole on 04/11/2014.
 */

public class IngaggioResource extends Controller {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            Attore attore = Attore.validate(Attore.class, request.getParameter("idattore"));
            if(attore == null)
                throw new FieldValidationException();

            Film film = Film.validate(Film.class, request.getParameter("idfilm"));
            if(film == null)
                throw new FieldValidationException();

            attore.getFilms().add(film);
            attore.update();

            PrintWriter writer = response.getWriter();
            writer.println(attore.toJson());
        } catch(FieldValidationException e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

}
