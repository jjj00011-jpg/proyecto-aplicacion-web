package daw.app.rest;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import java.util.ArrayList;
import java.util.List;

@Provider
public class ValidationExceptionMapper implements ExceptionMapper<ConstraintViolationException> {

    @Override
    public Response toResponse(ConstraintViolationException exception) {
        List<ValidationError> errors = new ArrayList<>();

        for (ConstraintViolation<?> violation : exception.getConstraintViolations()) {
            String[] parts = violation.getPropertyPath().toString().split("\\.");
            String name = parts[parts.length - 1];

            errors.add(new ValidationError(name, violation.getMessage()));
        }

        return Response
                .status(Response.Status.BAD_REQUEST)
                .entity(errors)
                .build();
    }
}