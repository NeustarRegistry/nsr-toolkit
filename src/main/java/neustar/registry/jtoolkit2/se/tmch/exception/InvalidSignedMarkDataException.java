package neustar.registry.jtoolkit2.se.tmch.exception;

import neustar.registry.jtoolkit2.ErrorPkg;

public class InvalidSignedMarkDataException extends RuntimeException {
    public InvalidSignedMarkDataException(Throwable exception) {
        super(ErrorPkg.getMessage("tmch.smd.invalid"), exception);
    }

    public InvalidSignedMarkDataException() {
        super(ErrorPkg.getMessage("tmch.smd.invalid"));
    }
}
