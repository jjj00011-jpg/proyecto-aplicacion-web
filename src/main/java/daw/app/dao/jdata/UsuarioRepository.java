package daw.app.dao.jdata;

import daw.app.model.Usuario;
import jakarta.data.repository.By;
import jakarta.data.repository.CrudRepository;
import jakarta.data.repository.Find;
import jakarta.data.repository.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, Long> {

    @Find
    Optional<Usuario> findByEmail(@By("email") String email);
}