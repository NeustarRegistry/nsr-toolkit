package neustar.registry.jtoolkit2.se.tmch.exception;

import java.security.cert.X509Certificate;

import neustar.registry.jtoolkit2.ErrorPkg;

public class TmchCertificateRevokedException extends RuntimeException {

    private final X509Certificate certificate;

    public TmchCertificateRevokedException(X509Certificate certificate) {
        this.certificate = certificate;
    }

    public X509Certificate getCertificate() {
        return certificate;
    }

    @Override
    public String getMessage() {
        return ErrorPkg.getMessage("tmch.smd.cert.revoked", "<<cert-detailed-msg>>", certificate);
    }
}
