package se.iths.util;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

public class NonNumericalEntryException extends WebApplicationException {
    public NonNumericalEntryException(Response response) {
        super(response);
    }
}
