
package fr.tao.customerserviceclient.soap.generated;

import jakarta.xml.ws.WebFault;


/**
 * This class was generated by Apache CXF 4.0.3
 * 2023-10-21T22:55:49.446+02:00
 * Generated source version: 4.0.3
 */

@WebFault(name = "Exception", targetNamespace = "http://soap.api.customerservice.tao.fr/")
public class Exception_Exception extends java.lang.Exception {
    public static final long serialVersionUID = 1L;

    private fr.tao.customerserviceclient.soap.generated.Exception faultInfo;

    public Exception_Exception() {
        super();
    }

    public Exception_Exception(String message) {
        super(message);
    }

    public Exception_Exception(String message, java.lang.Throwable cause) {
        super(message, cause);
    }

    public Exception_Exception(String message, fr.tao.customerserviceclient.soap.generated.Exception exception) {
        super(message);
        this.faultInfo = exception;
    }

    public Exception_Exception(String message, fr.tao.customerserviceclient.soap.generated.Exception exception, java.lang.Throwable cause) {
        super(message, cause);
        this.faultInfo = exception;
    }

    public fr.tao.customerserviceclient.soap.generated.Exception getFaultInfo() {
        return this.faultInfo;
    }
}
