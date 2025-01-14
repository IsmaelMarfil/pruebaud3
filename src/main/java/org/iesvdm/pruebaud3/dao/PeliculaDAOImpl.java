package org.iesvdm.pruebaud3.dao;

import org.iesvdm.pruebaud3.modelo.Pelicula;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.batch.BatchProperties;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PeliculaDAOImpl implements PeliculaDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Pelicula> getAll() {

        List<Pelicula> listPel = jdbcTemplate.query(
                "SELECT * FROM pelicula",
                (rs, rowNum) -> new Pelicula(rs.getInt("id_pelicula"),rs.getString("titulo"), rs.getString("descripcion"), rs.getDate("fecha_lanzamiento"), rs.getInt("id_idioma"), rs.getInt("duracion_alquiler"), rs.getDouble("rental_rate"), rs.getInt("duracion"), rs.getDouble("replacement_cost"), rs.getDate("ultima_actualizacion"))
        );

        return listPel;

    }
    @Override
    public synchronized void create(Pelicula pelicula) {

        jdbcTemplate.update("INSERT INTO pelicula (titulo, descripcion, fecha_lanzamiento, id_idioma, duracion_alquiler, rental_rate, duracion, replacement_cost) VALUES (?, ?, ?, ?, ?, ?, ?, ?)",pelicula.getTitulo(), pelicula.getDescripcion(), pelicula.getFechaLanzamiento(), pelicula.getIdIdioma(), pelicula.getDuracionAlquiler(), pelicula.getRentalRate(), pelicula.getDuracion(), pelicula.getReplacementCost());

    }
}
