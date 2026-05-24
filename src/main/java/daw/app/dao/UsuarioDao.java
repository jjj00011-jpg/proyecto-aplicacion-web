package daw.app.dao;

import daw.app.model.Usuario;

import java.util.List;

public interface UsuarioDao {
    List<Usuario> findAll();
    Usuario findById(Long id);
    Usuario findByEmail(String email);
    void save(Usuario usuario);
    void delete(Long id);
}