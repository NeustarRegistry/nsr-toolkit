package neustar.registry.jtoolkit2.se.tmch.exception;

import java.security.cert.Certificate;

import neustar.registry.jtoolkit2.ErrorPkg;

public class TmchCertificateInvalidTypeException extends RuntimeException {
    public TmchCertificateInvalidTypeException(Class<? extends Certificate> certificateClass) {
        super(ErrorPkg.getMessage("tmch.cert.invalid.type", "<<provided-type>>", certificateClass.getSimpleName()));
    }
}
