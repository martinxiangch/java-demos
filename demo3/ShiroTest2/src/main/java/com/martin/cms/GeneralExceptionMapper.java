package com.martin.cms;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.apache.shiro.ShiroException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.springframework.stereotype.Component;

@Provider
@Component
public class GeneralExceptionMapper implements ExceptionMapper<ShiroException> {

    @Override
    public Response toResponse(ShiroException ex) {
        MyController.printException(ex);
        return Response.status(ex instanceof UnauthenticatedException ? Response.Status.UNAUTHORIZED : Response.Status.FORBIDDEN)
                .entity(ex.getMessage())
                .type(MediaType.TEXT_PLAIN_TYPE)
                .build();
    }

}
