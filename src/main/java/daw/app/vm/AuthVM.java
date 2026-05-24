package daw.app.vm;

import daw.app.dao.UsuarioDao;
import daw.app.dao.qualifier.DAOJData;
import daw.app.model.Usuario;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpServletRequest;

@Named("authVM")
@RequestScoped
public class AuthVM {

    @Inject
    private HttpServletRequest request;

    @Inject
    @DAOJData
    private UsuarioDao usuarioDao;

    private String email;
    private String password;

    public String login() {
        Usuario usuario = usuarioDao.findByEmail(email);

        if (usuario == null || password == null || !password.equals(usuario.getPasswordHash())) {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(
                            FacesMessage.SEVERITY_ERROR,
                            "Error de autenticación",
                            "El email o la contraseña no son correctos."
                    )
            );
            return null;
        }

        request.getSession(true).setAttribute("usuarioEmail", usuario.getEmail());
        request.getSession(true).setAttribute("usuarioNombre", usuario.getNombre());
        request.getSession(true).setAttribute("rol", usuario.getRol().name());

        if (usuario.getRol() == Usuario.Rol.ADMIN) {
            return "/admin-alojamientos?faces-redirect=true";
        }

        return "/index?faces-redirect=true";
    }

    public String logout() {
        if (request.getSession(false) != null) {
            request.getSession(false).invalidate();
        }

        return "/index?faces-redirect=true";
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}