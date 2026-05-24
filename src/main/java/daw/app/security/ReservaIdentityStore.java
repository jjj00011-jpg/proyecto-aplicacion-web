package daw.app.security;

import daw.app.dao.UsuarioDao;
import daw.app.dao.qualifier.DAOJData;
import daw.app.model.Usuario;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.security.enterprise.credential.Credential;
import jakarta.security.enterprise.credential.UsernamePasswordCredential;
import jakarta.security.enterprise.identitystore.CredentialValidationResult;
import jakarta.security.enterprise.identitystore.IdentityStore;

import java.util.Set;
import java.util.logging.Logger;

@ApplicationScoped
public class ReservaIdentityStore implements IdentityStore {

    private static final Logger log = Logger.getLogger(ReservaIdentityStore.class.getName());

    @Inject
    @DAOJData
    private UsuarioDao usuarioDao;

    @Override
    public CredentialValidationResult validate(Credential credential) {
        if (!(credential instanceof UsernamePasswordCredential upc)) {
            log.warning("Credencial no compatible con UsernamePasswordCredential");
            return CredentialValidationResult.INVALID_RESULT;
        }

        String email = upc.getCaller();
        String password = upc.getPasswordAsString();

        log.info("Intento de login para: " + email);

        if (email == null || email.isBlank()) {
            log.warning("Email vacío en login");
            return CredentialValidationResult.INVALID_RESULT;
        }

        Usuario usuario = usuarioDao.findByEmail(email);

        if (usuario == null) {
            log.warning("Usuario no encontrado: " + email);
            return CredentialValidationResult.INVALID_RESULT;
        }

        if (password == null || !password.equals(usuario.getPasswordHash())) {
            log.warning("Contraseña incorrecta para usuario: " + email);
            return CredentialValidationResult.INVALID_RESULT;
        }

        log.info("Login correcto para usuario: " + email + " con rol " + usuario.getRol());

        return new CredentialValidationResult(
                usuario.getEmail(),
                Set.of(usuario.getRol().name())
        );
    }
}