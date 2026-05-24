package daw.app.dao.jdata;

import daw.app.dao.UsuarioDao;
import daw.app.dao.qualifier.DAOJData;
import daw.app.model.Usuario;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
@DAOJData
public class UsuarioDaoJData implements UsuarioDao {

    @Inject
    private UsuarioRepository repo;

    @Override
    public List<Usuario> findAll() {
        return repo.findAll().toList();
    }

    @Override
    public Usuario findById(Long id) {
        if (id == null) {
            return null;
        }
        return repo.findById(id).orElse(null);
    }

    @Override
    public Usuario findByEmail(String email) {
        if (email == null || email.isBlank()) {
            return null;
        }
        return repo.findByEmail(email).orElse(null);
    }

    @Override
    public void save(Usuario usuario) {
        if (usuario != null) {
            repo.save(usuario);
        }
    }

    @Override
    public void delete(Long id) {
        if (id != null) {
            repo.deleteById(id);
        }
    }
}