package neustar.registry.jtoolkit2.se.tmch.exception;

import neustar.registry.jtoolkit2.ErrorPkg;

import java.security.cert.CertPathValidatorException;
import java.security.cert.X509Certificate;

public class TmchInvalidCertificateException extends RuntimeException {
    private final X509Certificate certificate;

    public TmchInvalidCertificateException(X509Certificate certificate, CertPathValidatorException cause) {
        super(cause);
        this.certificate = certificate;
    }

    public X509Certificate getCertificate() {
        return certificate;
    }

    @Override
    public String getMessage() {
        return ErrorPkg.getMessage("tmch.smd.cert.invalid", "<<cert-detailed-msg>>", certificate);
    }
}
