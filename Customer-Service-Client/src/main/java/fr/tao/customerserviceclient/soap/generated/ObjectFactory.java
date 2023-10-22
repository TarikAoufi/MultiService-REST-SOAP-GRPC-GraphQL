
package fr.tao.customerserviceclient.soap.generated;

import javax.xml.namespace.QName;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlElementDecl;
import jakarta.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the fr.tao.customerserviceclient.soap.generated package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _CustomerById_QNAME = new QName("http://soap.api.customerservice.tao.fr/", "customerById");
    private final static QName _CustomerByIdResponse_QNAME = new QName("http://soap.api.customerservice.tao.fr/", "customerByIdResponse");
    private final static QName _GetAllCustomer_QNAME = new QName("http://soap.api.customerservice.tao.fr/", "getAllCustomer");
    private final static QName _GetAllCustomerResponse_QNAME = new QName("http://soap.api.customerservice.tao.fr/", "getAllCustomerResponse");
    private final static QName _SaveCustomer_QNAME = new QName("http://soap.api.customerservice.tao.fr/", "saveCustomer");
    private final static QName _SaveCustomerResponse_QNAME = new QName("http://soap.api.customerservice.tao.fr/", "saveCustomerResponse");
    private final static QName _Exception_QNAME = new QName("http://soap.api.customerservice.tao.fr/", "Exception");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: fr.tao.customerserviceclient.soap.generated
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link CustomerById }
     * 
     */
    public CustomerById createCustomerById() {
        return new CustomerById();
    }

    /**
     * Create an instance of {@link CustomerByIdResponse }
     * 
     */
    public CustomerByIdResponse createCustomerByIdResponse() {
        return new CustomerByIdResponse();
    }

    /**
     * Create an instance of {@link GetAllCustomer }
     * 
     */
    public GetAllCustomer createGetAllCustomer() {
        return new GetAllCustomer();
    }

    /**
     * Create an instance of {@link GetAllCustomerResponse }
     * 
     */
    public GetAllCustomerResponse createGetAllCustomerResponse() {
        return new GetAllCustomerResponse();
    }

    /**
     * Create an instance of {@link SaveCustomer }
     * 
     */
    public SaveCustomer createSaveCustomer() {
        return new SaveCustomer();
    }

    /**
     * Create an instance of {@link SaveCustomerResponse }
     * 
     */
    public SaveCustomerResponse createSaveCustomerResponse() {
        return new SaveCustomerResponse();
    }

    /**
     * Create an instance of {@link Exception }
     * 
     */
    public Exception createException() {
        return new Exception();
    }

    /**
     * Create an instance of {@link CustomerDto }
     * 
     */
    public CustomerDto createCustomerDto() {
        return new CustomerDto();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CustomerById }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link CustomerById }{@code >}
     */
    @XmlElementDecl(namespace = "http://soap.api.customerservice.tao.fr/", name = "customerById")
    public JAXBElement<CustomerById> createCustomerById(CustomerById value) {
        return new JAXBElement<CustomerById>(_CustomerById_QNAME, CustomerById.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CustomerByIdResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link CustomerByIdResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://soap.api.customerservice.tao.fr/", name = "customerByIdResponse")
    public JAXBElement<CustomerByIdResponse> createCustomerByIdResponse(CustomerByIdResponse value) {
        return new JAXBElement<CustomerByIdResponse>(_CustomerByIdResponse_QNAME, CustomerByIdResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllCustomer }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetAllCustomer }{@code >}
     */
    @XmlElementDecl(namespace = "http://soap.api.customerservice.tao.fr/", name = "getAllCustomer")
    public JAXBElement<GetAllCustomer> createGetAllCustomer(GetAllCustomer value) {
        return new JAXBElement<GetAllCustomer>(_GetAllCustomer_QNAME, GetAllCustomer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllCustomerResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetAllCustomerResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://soap.api.customerservice.tao.fr/", name = "getAllCustomerResponse")
    public JAXBElement<GetAllCustomerResponse> createGetAllCustomerResponse(GetAllCustomerResponse value) {
        return new JAXBElement<GetAllCustomerResponse>(_GetAllCustomerResponse_QNAME, GetAllCustomerResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SaveCustomer }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link SaveCustomer }{@code >}
     */
    @XmlElementDecl(namespace = "http://soap.api.customerservice.tao.fr/", name = "saveCustomer")
    public JAXBElement<SaveCustomer> createSaveCustomer(SaveCustomer value) {
        return new JAXBElement<SaveCustomer>(_SaveCustomer_QNAME, SaveCustomer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SaveCustomerResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link SaveCustomerResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://soap.api.customerservice.tao.fr/", name = "saveCustomerResponse")
    public JAXBElement<SaveCustomerResponse> createSaveCustomerResponse(SaveCustomerResponse value) {
        return new JAXBElement<SaveCustomerResponse>(_SaveCustomerResponse_QNAME, SaveCustomerResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Exception }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Exception }{@code >}
     */
    @XmlElementDecl(namespace = "http://soap.api.customerservice.tao.fr/", name = "Exception")
    public JAXBElement<Exception> createException(Exception value) {
        return new JAXBElement<Exception>(_Exception_QNAME, Exception.class, null, value);
    }

}
