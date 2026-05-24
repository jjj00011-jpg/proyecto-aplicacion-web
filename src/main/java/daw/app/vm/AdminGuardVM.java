package daw.app.vm;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpServletRequest;

@Named("adminGuardVM")
@RequestScoped
public class AdminGuardVM {

    @Inject
    private HttpServletRequest request;

    public String checkAdmin() {
        if (request.getSession(false) == null) {
            return "/login?faces-redirect=true";
        }

        Object rol = request.getSession(false).getAttribute("rol");

        if (!"ADMIN".equals(rol)) {
            return "/login?faces-redirect=true";
        }

        return null;
    }
}