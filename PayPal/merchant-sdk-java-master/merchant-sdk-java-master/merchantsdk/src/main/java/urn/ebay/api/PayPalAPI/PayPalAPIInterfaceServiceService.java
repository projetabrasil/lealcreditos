package urn.ebay.api.PayPalAPI;
import java.io.*;
import java.util.Map;
import java.util.Properties;
import com.paypal.core.BaseService;
import com.paypal.exception.*;
import com.paypal.core.credential.ICredential;
import com.paypal.core.APICallPreHandler;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;
import org.xml.sax.InputSource;
import javax.xml.parsers.ParserConfigurationException;
import urn.ebay.apis.eBLBaseComponents.AbstractRequestType;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import com.paypal.core.DefaultSOAPAPICallHandler;
import com.paypal.core.soap.MerchantAPICallPreHandler;
import urn.ebay.api.PayPalAPI.RefundTransactionReq;
import urn.ebay.api.PayPalAPI.RefundTransactionResponseType;
import urn.ebay.api.PayPalAPI.InitiateRecoupReq;
import urn.ebay.api.PayPalAPI.InitiateRecoupResponseType;
import urn.ebay.api.PayPalAPI.CompleteRecoupReq;
import urn.ebay.api.PayPalAPI.CompleteRecoupResponseType;
import urn.ebay.api.PayPalAPI.CancelRecoupReq;
import urn.ebay.api.PayPalAPI.CancelRecoupResponseType;
import urn.ebay.api.PayPalAPI.GetTransactionDetailsReq;
import urn.ebay.api.PayPalAPI.GetTransactionDetailsResponseType;
import urn.ebay.api.PayPalAPI.BillUserReq;
import urn.ebay.api.PayPalAPI.BillUserResponseType;
import urn.ebay.api.PayPalAPI.TransactionSearchReq;
import urn.ebay.api.PayPalAPI.TransactionSearchResponseType;
import urn.ebay.api.PayPalAPI.MassPayReq;
import urn.ebay.api.PayPalAPI.MassPayResponseType;
import urn.ebay.api.PayPalAPI.BillAgreementUpdateReq;
import urn.ebay.api.PayPalAPI.BAUpdateResponseType;
import urn.ebay.api.PayPalAPI.AddressVerifyReq;
import urn.ebay.api.PayPalAPI.AddressVerifyResponseType;
import urn.ebay.api.PayPalAPI.EnterBoardingReq;
import urn.ebay.api.PayPalAPI.EnterBoardingResponseType;
import urn.ebay.api.PayPalAPI.GetBoardingDetailsReq;
import urn.ebay.api.PayPalAPI.GetBoardingDetailsResponseType;
import urn.ebay.api.PayPalAPI.CreateMobilePaymentReq;
import urn.ebay.api.PayPalAPI.CreateMobilePaymentResponseType;
import urn.ebay.api.PayPalAPI.GetMobileStatusReq;
import urn.ebay.api.PayPalAPI.GetMobileStatusResponseType;
import urn.ebay.api.PayPalAPI.SetMobileCheckoutReq;
import urn.ebay.api.PayPalAPI.SetMobileCheckoutResponseType;
import urn.ebay.api.PayPalAPI.DoMobileCheckoutPaymentReq;
import urn.ebay.api.PayPalAPI.DoMobileCheckoutPaymentResponseType;
import urn.ebay.api.PayPalAPI.GetBalanceReq;
import urn.ebay.api.PayPalAPI.GetBalanceResponseType;
import urn.ebay.api.PayPalAPI.GetPalDetailsReq;
import urn.ebay.api.PayPalAPI.GetPalDetailsResponseType;
import urn.ebay.api.PayPalAPI.DoExpressCheckoutPaymentReq;
import urn.ebay.api.PayPalAPI.DoExpressCheckoutPaymentResponseType;
import urn.ebay.api.PayPalAPI.DoUATPExpressCheckoutPaymentReq;
import urn.ebay.api.PayPalAPI.DoUATPExpressCheckoutPaymentResponseType;
import urn.ebay.api.PayPalAPI.SetAuthFlowParamReq;
import urn.ebay.api.PayPalAPI.SetAuthFlowParamResponseType;
import urn.ebay.api.PayPalAPI.GetAuthDetailsReq;
import urn.ebay.api.PayPalAPI.GetAuthDetailsResponseType;
import urn.ebay.api.PayPalAPI.SetAccessPermissionsReq;
import urn.ebay.api.PayPalAPI.SetAccessPermissionsResponseType;
import urn.ebay.api.PayPalAPI.UpdateAccessPermissionsReq;
import urn.ebay.api.PayPalAPI.UpdateAccessPermissionsResponseType;
import urn.ebay.api.PayPalAPI.GetAccessPermissionDetailsReq;
import urn.ebay.api.PayPalAPI.GetAccessPermissionDetailsResponseType;
import urn.ebay.api.PayPalAPI.GetIncentiveEvaluationReq;
import urn.ebay.api.PayPalAPI.GetIncentiveEvaluationResponseType;
import urn.ebay.api.PayPalAPI.SetExpressCheckoutReq;
import urn.ebay.api.PayPalAPI.SetExpressCheckoutResponseType;
import urn.ebay.api.PayPalAPI.ExecuteCheckoutOperationsReq;
import urn.ebay.api.PayPalAPI.ExecuteCheckoutOperationsResponseType;
import urn.ebay.api.PayPalAPI.GetExpressCheckoutDetailsReq;
import urn.ebay.api.PayPalAPI.GetExpressCheckoutDetailsResponseType;
import urn.ebay.api.PayPalAPI.DoDirectPaymentReq;
import urn.ebay.api.PayPalAPI.DoDirectPaymentResponseType;
import urn.ebay.api.PayPalAPI.ManagePendingTransactionStatusReq;
import urn.ebay.api.PayPalAPI.ManagePendingTransactionStatusResponseType;
import urn.ebay.api.PayPalAPI.DoCancelReq;
import urn.ebay.api.PayPalAPI.DoCancelResponseType;
import urn.ebay.api.PayPalAPI.DoCaptureReq;
import urn.ebay.api.PayPalAPI.DoCaptureResponseType;
import urn.ebay.api.PayPalAPI.DoReauthorizationReq;
import urn.ebay.api.PayPalAPI.DoReauthorizationResponseType;
import urn.ebay.api.PayPalAPI.DoVoidReq;
import urn.ebay.api.PayPalAPI.DoVoidResponseType;
import urn.ebay.api.PayPalAPI.DoAuthorizationReq;
import urn.ebay.api.PayPalAPI.DoAuthorizationResponseType;
import urn.ebay.api.PayPalAPI.UpdateAuthorizationReq;
import urn.ebay.api.PayPalAPI.UpdateAuthorizationResponseType;
import urn.ebay.api.PayPalAPI.SetCustomerBillingAgreementReq;
import urn.ebay.api.PayPalAPI.SetCustomerBillingAgreementResponseType;
import urn.ebay.api.PayPalAPI.GetBillingAgreementCustomerDetailsReq;
import urn.ebay.api.PayPalAPI.GetBillingAgreementCustomerDetailsResponseType;
import urn.ebay.api.PayPalAPI.CreateBillingAgreementReq;
import urn.ebay.api.PayPalAPI.CreateBillingAgreementResponseType;
import urn.ebay.api.PayPalAPI.DoReferenceTransactionReq;
import urn.ebay.api.PayPalAPI.DoReferenceTransactionResponseType;
import urn.ebay.api.PayPalAPI.DoNonReferencedCreditReq;
import urn.ebay.api.PayPalAPI.DoNonReferencedCreditResponseType;
import urn.ebay.api.PayPalAPI.DoUATPAuthorizationReq;
import urn.ebay.api.PayPalAPI.DoUATPAuthorizationResponseType;
import urn.ebay.api.PayPalAPI.CreateRecurringPaymentsProfileReq;
import urn.ebay.api.PayPalAPI.CreateRecurringPaymentsProfileResponseType;
import urn.ebay.api.PayPalAPI.GetRecurringPaymentsProfileDetailsReq;
import urn.ebay.api.PayPalAPI.GetRecurringPaymentsProfileDetailsResponseType;
import urn.ebay.api.PayPalAPI.ManageRecurringPaymentsProfileStatusReq;
import urn.ebay.api.PayPalAPI.ManageRecurringPaymentsProfileStatusResponseType;
import urn.ebay.api.PayPalAPI.BillOutstandingAmountReq;
import urn.ebay.api.PayPalAPI.BillOutstandingAmountResponseType;
import urn.ebay.api.PayPalAPI.UpdateRecurringPaymentsProfileReq;
import urn.ebay.api.PayPalAPI.UpdateRecurringPaymentsProfileResponseType;
import urn.ebay.api.PayPalAPI.ReverseTransactionReq;
import urn.ebay.api.PayPalAPI.ReverseTransactionResponseType;
import urn.ebay.api.PayPalAPI.ExternalRememberMeOptOutReq;
import urn.ebay.api.PayPalAPI.ExternalRememberMeOptOutResponseType;
import com.paypal.sdk.exceptions.OAuthException;

public class PayPalAPIInterfaceServiceService extends BaseService {


	// Service Version
	public static final String SERVICE_VERSION = "117.0";

	// Service Name
	public static final String SERVICE_NAME = "PayPalAPIInterfaceService";

	//SDK Name
	private static final String SDK_NAME = "merchant-java-sdk";
	
	//SDK Version
	private static final String SDK_VERSION = "2.11.117";


	/**
	 * Default <code>PayPalAPIInterfaceServiceService</code> Constructor.
	 * Initializes the SDK system with the default configuration file named
	 * 'sdk_config.properties' found in the class-path
	 * 
	 */
	public PayPalAPIInterfaceServiceService() {
		super();
	}
	
	/**
	 * <code>PayPalAPIInterfaceServiceService</code> that uses the supplied path
	 * to initialize the SDK system. The initialization context is maintained
	 * only for this instance of the class. To initialize the SDK system
	 * globally use the default constructor.
	 * 
	 * @see PayPalAPIInterfaceServiceService
	 * @param configFilePath
	 *            Absolute path to a {@link Properties} file
	 * @throws IOException
	 */
	public PayPalAPIInterfaceServiceService(String configFilePath) throws IOException {
		this(new File(configFilePath));
	}
	
	/**
	 * <code>PayPalAPIInterfaceServiceService</code> that uses the supplied
	 * {@link File} object to initialize the SDK system. The initialization
	 * context is maintained only for this instance of the class. To initialize
	 * the SDK system globally use the default constructor
	 * 
	 * @see PayPalAPIInterfaceServiceService
	 * @param configFile
	 *            Configuration file in {@link Properties} format
	 * @throws IOException
	 */
	public PayPalAPIInterfaceServiceService(File configFile) throws IOException {
		this(new FileInputStream(configFile));
	}		

	/**
	 * <code>PayPalAPIInterfaceServiceService</code> that uses the supplied
	 * {@link InputStream} object to initialize the SDK system. The
	 * initialization context is maintained only for this instance of the class.
	 * To initialize the SDK system globally use the default constructor.
	 * 
	 * @see PayPalAPIInterfaceServiceService
	 * @param inputStream
	 *            InputStream of a {@link Properties} file
	 * @throws IOException
	 */
	public PayPalAPIInterfaceServiceService(InputStream inputStream) throws IOException {
		super(inputStream);
	}

	/**
	 * <code>PayPalAPIInterfaceServiceService</code> that uses the supplied
	 * {@link Properties} to initialize the SDK system. For values that the
	 * properties should hold consult the sample 'sdk_config.properties' file
	 * bundled with the SDK. The initialization context is maintained only for
	 * this instance of the class. To initialize the SDK system globally use the
	 * default constructor.
	 * 
	 * @see PayPalAPIInterfaceServiceService
	 * @param properties
	 *            {@link Properties} object
	 */	
	public PayPalAPIInterfaceServiceService(Properties properties) {
		super(properties);
	}
	
	/**
	 * <code>PayPalAPIInterfaceServiceService</code> that uses the supplied
	 * {@link Map} to initialize the SDK system. For values that the map should
	 * hold consult the sample 'sdk_config.properties' file bundled with the
	 * SDK. The initialization context is maintained only for this instance of
	 * the class. To initialize the SDK system globally use the default
	 * constructor.
	 * 
	 * @see PayPalAPIInterfaceServiceService
	 * @param configurationMap
	 *            {@link Map} object
	 */
	public PayPalAPIInterfaceServiceService(Map<String, String> configurationMap) {
		super(configurationMap);
	}



	/*
	 * Sets the version required for Merchant API calls
	 */
	private void setStandardParams(AbstractRequestType request) {
		if (request.getVersion() == null) {
			request.setVersion(SERVICE_VERSION);
		}
	}

	/** 
	 * 
	 * @throws SSLConfigurationException
	 * @throws InvalidCredentialException
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @throws HttpErrorException
	 * @throws InvalidResponseDataException
	 * @throws ClientActionRequiredException
	 * @throws MissingCredentialException
	 * @throws InterruptedException
	 * @throws OAuthException
	 */
	 public RefundTransactionResponseType refundTransaction(RefundTransactionReq refundTransactionReq) throws SSLConfigurationException, InvalidCredentialException, IOException, HttpErrorException, InvalidResponseDataException, ClientActionRequiredException, MissingCredentialException, InterruptedException, OAuthException, ParserConfigurationException, SAXException  {
	 	return refundTransaction(refundTransactionReq, (String) null);
	 }

	/** 
	 * 
	 * @throws SSLConfigurationException
	 * @throws InvalidCredentialException
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @throws HttpErrorException
	 * @throws InvalidResponseDataException
	 * @throws ClientActionRequiredException
	 * @throws MissingCredentialException
	 * @throws InterruptedException
	 * @throws OAuthException
	 */
	 public RefundTransactionResponseType refundTransaction(RefundTransactionReq refundTransactionReq, ICredential credential) throws SSLConfigurationException, InvalidCredentialException, IOException, HttpErrorException, InvalidResponseDataException, ClientActionRequiredException, MissingCredentialException, InterruptedException, OAuthException, ParserConfigurationException, SAXException  {
		setStandardParams(refundTransactionReq.getRefundTransactionRequest());
		DefaultSOAPAPICallHandler defaultHandler = new DefaultSOAPAPICallHandler(refundTransactionReq.toXMLString(null, "RefundTransactionReq"), null, null, this.configurationMap);
		APICallPreHandler apiCallPreHandler = new MerchantAPICallPreHandler(defaultHandler, credential, SDK_NAME, SDK_VERSION, "PayPalAPI", this.configurationMap);
	 	String response = call(apiCallPreHandler);
		InputSource inStream = new InputSource();
		inStream.setCharacterStream(new StringReader((String) response));
		try {
			Node node = (Node) XPathFactory.newInstance().newXPath().evaluate("Envelope/Body/RefundTransactionResponse", DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(inStream), XPathConstants.NODE);
			return new RefundTransactionResponseType(node);
		} catch (XPathExpressionException exe) {
			throw new RuntimeException("Unable to parse response", exe);
		}
	}
	
	/**	
	 * 
	 * @throws SSLConfigurationException
	 * @throws InvalidCredentialException
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @throws HttpErrorException
	 * @throws InvalidResponseDataException
	 * @throws ClientActionRequiredException
	 * @throws MissingCredentialException
	 * @throws InterruptedException
	 * @throws OAuthException
	 */
	 public RefundTransactionResponseType refundTransaction(RefundTransactionReq refundTransactionReq, String apiUsername) throws SSLConfigurationException, InvalidCredentialException, IOException, HttpErrorException, InvalidResponseDataException, ClientActionRequiredException, MissingCredentialException, InterruptedException, OAuthException, ParserConfigurationException, SAXException, IOException {
		setStandardParams(refundTransactionReq.getRefundTransactionRequest());
		DefaultSOAPAPICallHandler defaultHandler = new DefaultSOAPAPICallHandler(refundTransactionReq.toXMLString(null, "RefundTransactionReq"), null, null, this.configurationMap);
		APICallPreHandler apiCallPreHandler = new MerchantAPICallPreHandler(defaultHandler, apiUsername, getAccessToken(), getTokenSecret(), SDK_NAME, SDK_VERSION, "PayPalAPI", this.configurationMap);
	 	String response = call(apiCallPreHandler);
		InputSource inStream = new InputSource();
		inStream.setCharacterStream(new StringReader((String) response));
		try {
			Node node = (Node) XPathFactory.newInstance().newXPath().evaluate("Envelope/Body/RefundTransactionResponse", DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(inStream), XPathConstants.NODE);
			return new RefundTransactionResponseType(node);
		} catch (XPathExpressionException exe) {
			throw new RuntimeException("Unable to parse response", exe);
		}
	 }

	/** 
	 * 
	 * @throws SSLConfigurationException
	 * @throws InvalidCredentialException
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @throws HttpErrorException
	 * @throws InvalidResponseDataException
	 * @throws ClientActionRequiredException
	 * @throws MissingCredentialException
	 * @throws InterruptedException
	 * @throws OAuthException
	 */
	 public InitiateRecoupResponseType initiateRecoup(InitiateRecoupReq initiateRecoupReq) throws SSLConfigurationException, InvalidCredentialException, IOException, HttpErrorException, InvalidResponseDataException, ClientActionRequiredException, MissingCredentialException, InterruptedException, OAuthException, ParserConfigurationException, SAXException  {
	 	return initiateRecoup(initiateRecoupReq, (String) null);
	 }

	/** 
	 * 
	 * @throws SSLConfigurationException
	 * @throws InvalidCredentialException
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @throws HttpErrorException
	 * @throws InvalidResponseDataException
	 * @throws ClientActionRequiredException
	 * @throws MissingCredentialException
	 * @throws InterruptedException
	 * @throws OAuthException
	 */
	 public InitiateRecoupResponseType initiateRecoup(InitiateRecoupReq initiateRecoupReq, ICredential credential) throws SSLConfigurationException, InvalidCredentialException, IOException, HttpErrorException, InvalidResponseDataException, ClientActionRequiredException, MissingCredentialException, InterruptedException, OAuthException, ParserConfigurationException, SAXException  {
		setStandardParams(initiateRecoupReq.getInitiateRecoupRequest());
		DefaultSOAPAPICallHandler defaultHandler = new DefaultSOAPAPICallHandler(initiateRecoupReq.toXMLString(null, "InitiateRecoupReq"), null, null, this.configurationMap);
		APICallPreHandler apiCallPreHandler = new MerchantAPICallPreHandler(defaultHandler, credential, SDK_NAME, SDK_VERSION, "PayPalAPI", this.configurationMap);
	 	String response = call(apiCallPreHandler);
		InputSource inStream = new InputSource();
		inStream.setCharacterStream(new StringReader((String) response));
		try {
			Node node = (Node) XPathFactory.newInstance().newXPath().evaluate("Envelope/Body/InitiateRecoupResponse", DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(inStream), XPathConstants.NODE);
			return new InitiateRecoupResponseType(node);
		} catch (XPathExpressionException exe) {
			throw new RuntimeException("Unable to parse response", exe);
		}
	}
	
	/**	
	 * 
	 * @throws SSLConfigurationException
	 * @throws InvalidCredentialException
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @throws HttpErrorException
	 * @throws InvalidResponseDataException
	 * @throws ClientActionRequiredException
	 * @throws MissingCredentialException
	 * @throws InterruptedException
	 * @throws OAuthException
	 */
	 public InitiateRecoupResponseType initiateRecoup(InitiateRecoupReq initiateRecoupReq, String apiUsername) throws SSLConfigurationException, InvalidCredentialException, IOException, HttpErrorException, InvalidResponseDataException, ClientActionRequiredException, MissingCredentialException, InterruptedException, OAuthException, ParserConfigurationException, SAXException, IOException {
		setStandardParams(initiateRecoupReq.getInitiateRecoupRequest());
		DefaultSOAPAPICallHandler defaultHandler = new DefaultSOAPAPICallHandler(initiateRecoupReq.toXMLString(null, "InitiateRecoupReq"), null, null, this.configurationMap);
		APICallPreHandler apiCallPreHandler = new MerchantAPICallPreHandler(defaultHandler, apiUsername, getAccessToken(), getTokenSecret(), SDK_NAME, SDK_VERSION, "PayPalAPI", this.configurationMap);
	 	String response = call(apiCallPreHandler);
		InputSource inStream = new InputSource();
		inStream.setCharacterStream(new StringReader((String) response));
		try {
			Node node = (Node) XPathFactory.newInstance().newXPath().evaluate("Envelope/Body/InitiateRecoupResponse", DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(inStream), XPathConstants.NODE);
			return new InitiateRecoupResponseType(node);
		} catch (XPathExpressionException exe) {
			throw new RuntimeException("Unable to parse response", exe);
		}
	 }

	/** 
	 * 
	 * @throws SSLConfigurationException
	 * @throws InvalidCredentialException
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @throws HttpErrorException
	 * @throws InvalidResponseDataException
	 * @throws ClientActionRequiredException
	 * @throws MissingCredentialException
	 * @throws InterruptedException
	 * @throws OAuthException
	 */
	 public CompleteRecoupResponseType completeRecoup(CompleteRecoupReq completeRecoupReq) throws SSLConfigurationException, InvalidCredentialException, IOException, HttpErrorException, InvalidResponseDataException, ClientActionRequiredException, MissingCredentialException, InterruptedException, OAuthException, ParserConfigurationException, SAXException  {
	 	return completeRecoup(completeRecoupReq, (String) null);
	 }

	/** 
	 * 
	 * @throws SSLConfigurationException
	 * @throws InvalidCredentialException
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @throws HttpErrorException
	 * @throws InvalidResponseDataException
	 * @throws ClientActionRequiredException
	 * @throws MissingCredentialException
	 * @throws InterruptedException
	 * @throws OAuthException
	 */
	 public CompleteRecoupResponseType completeRecoup(CompleteRecoupReq completeRecoupReq, ICredential credential) throws SSLConfigurationException, InvalidCredentialException, IOException, HttpErrorException, InvalidResponseDataException, ClientActionRequiredException, MissingCredentialException, InterruptedException, OAuthException, ParserConfigurationException, SAXException  {
		setStandardParams(completeRecoupReq.getCompleteRecoupRequest());
		DefaultSOAPAPICallHandler defaultHandler = new DefaultSOAPAPICallHandler(completeRecoupReq.toXMLString(null, "CompleteRecoupReq"), null, null, this.configurationMap);
		APICallPreHandler apiCallPreHandler = new MerchantAPICallPreHandler(defaultHandler, credential, SDK_NAME, SDK_VERSION, "PayPalAPI", this.configurationMap);
	 	String response = call(apiCallPreHandler);
		InputSource inStream = new InputSource();
		inStream.setCharacterStream(new StringReader((String) response));
		try {
			Node node = (Node) XPathFactory.newInstance().newXPath().evaluate("Envelope/Body/CompleteRecoupResponse", DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(inStream), XPathConstants.NODE);
			return new CompleteRecoupResponseType(node);
		} catch (XPathExpressionException exe) {
			throw new RuntimeException("Unable to parse response", exe);
		}
	}
	
	/**	
	 * 
	 * @throws SSLConfigurationException
	 * @throws InvalidCredentialException
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @throws HttpErrorException
	 * @throws InvalidResponseDataException
	 * @throws ClientActionRequiredException
	 * @throws MissingCredentialException
	 * @throws InterruptedException
	 * @throws OAuthException
	 */
	 public CompleteRecoupResponseType completeRecoup(CompleteRecoupReq completeRecoupReq, String apiUsername) throws SSLConfigurationException, InvalidCredentialException, IOException, HttpErrorException, InvalidResponseDataException, ClientActionRequiredException, MissingCredentialException, InterruptedException, OAuthException, ParserConfigurationException, SAXException, IOException {
		setStandardParams(completeRecoupReq.getCompleteRecoupRequest());
		DefaultSOAPAPICallHandler defaultHandler = new DefaultSOAPAPICallHandler(completeRecoupReq.toXMLString(null, "CompleteRecoupReq"), null, null, this.configurationMap);
		APICallPreHandler apiCallPreHandler = new MerchantAPICallPreHandler(defaultHandler, apiUsername, getAccessToken(), getTokenSecret(), SDK_NAME, SDK_VERSION, "PayPalAPI", this.configurationMap);
	 	String response = call(apiCallPreHandler);
		InputSource inStream = new InputSource();
		inStream.setCharacterStream(new StringReader((String) response));
		try {
			Node node = (Node) XPathFactory.newInstance().newXPath().evaluate("Envelope/Body/CompleteRecoupResponse", DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(inStream), XPathConstants.NODE);
			return new CompleteRecoupResponseType(node);
		} catch (XPathExpressionException exe) {
			throw new RuntimeException("Unable to parse response", exe);
		}
	 }

	/** 
	 * 
	 * @throws SSLConfigurationException
	 * @throws InvalidCredentialException
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @throws HttpErrorException
	 * @throws InvalidResponseDataException
	 * @throws ClientActionRequiredException
	 * @throws MissingCredentialException
	 * @throws InterruptedException
	 * @throws OAuthException
	 */
	 public CancelRecoupResponseType cancelRecoup(CancelRecoupReq cancelRecoupReq) throws SSLConfigurationException, InvalidCredentialException, IOException, HttpErrorException, InvalidResponseDataException, ClientActionRequiredException, MissingCredentialException, InterruptedException, OAuthException, ParserConfigurationException, SAXException  {
	 	return cancelRecoup(cancelRecoupReq, (String) null);
	 }

	/** 
	 * 
	 * @throws SSLConfigurationException
	 * @throws InvalidCredentialException
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @throws HttpErrorException
	 * @throws InvalidResponseDataException
	 * @throws ClientActionRequiredException
	 * @throws MissingCredentialException
	 * @throws InterruptedException
	 * @throws OAuthException
	 */
	 public CancelRecoupResponseType cancelRecoup(CancelRecoupReq cancelRecoupReq, ICredential credential) throws SSLConfigurationException, InvalidCredentialException, IOException, HttpErrorException, InvalidResponseDataException, ClientActionRequiredException, MissingCredentialException, InterruptedException, OAuthException, ParserConfigurationException, SAXException  {
		setStandardParams(cancelRecoupReq.getCancelRecoupRequest());
		DefaultSOAPAPICallHandler defaultHandler = new DefaultSOAPAPICallHandler(cancelRecoupReq.toXMLString(null, "CancelRecoupReq"), null, null, this.configurationMap);
		APICallPreHandler apiCallPreHandler = new MerchantAPICallPreHandler(defaultHandler, credential, SDK_NAME, SDK_VERSION, "PayPalAPI", this.configurationMap);
	 	String response = call(apiCallPreHandler);
		InputSource inStream = new InputSource();
		inStream.setCharacterStream(new StringReader((String) response));
		try {
			Node node = (Node) XPathFactory.newInstance().newXPath().evaluate("Envelope/Body/CancelRecoupResponse", DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(inStream), XPathConstants.NODE);
			return new CancelRecoupResponseType(node);
		} catch (XPathExpressionException exe) {
			throw new RuntimeException("Unable to parse response", exe);
		}
	}
	
	/**	
	 * 
	 * @throws SSLConfigurationException
	 * @throws InvalidCredentialException
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @throws HttpErrorException
	 * @throws InvalidResponseDataException
	 * @throws ClientActionRequiredException
	 * @throws MissingCredentialException
	 * @throws InterruptedException
	 * @throws OAuthException
	 */
	 public CancelRecoupResponseType cancelRecoup(CancelRecoupReq cancelRecoupReq, String apiUsername) throws SSLConfigurationException, InvalidCredentialException, IOException, HttpErrorException, InvalidResponseDataException, ClientActionRequiredException, MissingCredentialException, InterruptedException, OAuthException, ParserConfigurationException, SAXException, IOException {
		setStandardParams(cancelRecoupReq.getCancelRecoupRequest());
		DefaultSOAPAPICallHandler defaultHandler = new DefaultSOAPAPICallHandler(cancelRecoupReq.toXMLString(null, "CancelRecoupReq"), null, null, this.configurationMap);
		APICallPreHandler apiCallPreHandler = new MerchantAPICallPreHandler(defaultHandler, apiUsername, getAccessToken(), getTokenSecret(), SDK_NAME, SDK_VERSION, "PayPalAPI", this.configurationMap);
	 	String response = call(apiCallPreHandler);
		InputSource inStream = new InputSource();
		inStream.setCharacterStream(new StringReader((String) response));
		try {
			Node node = (Node) XPathFactory.newInstance().newXPath().evaluate("Envelope/Body/CancelRecoupResponse", DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(inStream), XPathConstants.NODE);
			return new CancelRecoupResponseType(node);
		} catch (XPathExpressionException exe) {
			throw new RuntimeException("Unable to parse response", exe);
		}
	 }

	/** 
	 * 
	 * @throws SSLConfigurationException
	 * @throws InvalidCredentialException
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @throws HttpErrorException
	 * @throws InvalidResponseDataException
	 * @throws ClientActionRequiredException
	 * @throws MissingCredentialException
	 * @throws InterruptedException
	 * @throws OAuthException
	 */
	 public GetTransactionDetailsResponseType getTransactionDetails(GetTransactionDetailsReq getTransactionDetailsReq) throws SSLConfigurationException, InvalidCredentialException, IOException, HttpErrorException, InvalidResponseDataException, ClientActionRequiredException, MissingCredentialException, InterruptedException, OAuthException, ParserConfigurationException, SAXException  {
	 	return getTransactionDetails(getTransactionDetailsReq, (String) null);
	 }

	/** 
	 * 
	 * @throws SSLConfigurationException
	 * @throws InvalidCredentialException
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @throws HttpErrorException
	 * @throws InvalidResponseDataException
	 * @throws ClientActionRequiredException
	 * @throws MissingCredentialException
	 * @throws InterruptedException
	 * @throws OAuthException
	 */
	 public GetTransactionDetailsResponseType getTransactionDetails(GetTransactionDetailsReq getTransactionDetailsReq, ICredential credential) throws SSLConfigurationException, InvalidCredentialException, IOException, HttpErrorException, InvalidResponseDataException, ClientActionRequiredException, MissingCredentialException, InterruptedException, OAuthException, ParserConfigurationException, SAXException  {
		setStandardParams(getTransactionDetailsReq.getGetTransactionDetailsRequest());
		DefaultSOAPAPICallHandler defaultHandler = new DefaultSOAPAPICallHandler(getTransactionDetailsReq.toXMLString(null, "GetTransactionDetailsReq"), null, null, this.configurationMap);
		APICallPreHandler apiCallPreHandler = new MerchantAPICallPreHandler(defaultHandler, credential, SDK_NAME, SDK_VERSION, "PayPalAPI", this.configurationMap);
	 	String response = call(apiCallPreHandler);
		InputSource inStream = new InputSource();
		inStream.setCharacterStream(new StringReader((String) response));
		try {
			Node node = (Node) XPathFactory.newInstance().newXPath().evaluate("Envelope/Body/GetTransactionDetailsResponse", DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(inStream), XPathConstants.NODE);
			return new GetTransactionDetailsResponseType(node);
		} catch (XPathExpressionException exe) {
			throw new RuntimeException("Unable to parse response", exe);
		}
	}
	
	/**	
	 * 
	 * @throws SSLConfigurationException
	 * @throws InvalidCredentialException
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @throws HttpErrorException
	 * @throws InvalidResponseDataException
	 * @throws ClientActionRequiredException
	 * @throws MissingCredentialException
	 * @throws InterruptedException
	 * @throws OAuthException
	 */
	 public GetTransactionDetailsResponseType getTransactionDetails(GetTransactionDetailsReq getTransactionDetailsReq, String apiUsername) throws SSLConfigurationException, InvalidCredentialException, IOException, HttpErrorException, InvalidResponseDataException, ClientActionRequiredException, MissingCredentialException, InterruptedException, OAuthException, ParserConfigurationException, SAXException, IOException {
		setStandardParams(getTransactionDetailsReq.getGetTransactionDetailsRequest());
		DefaultSOAPAPICallHandler defaultHandler = new DefaultSOAPAPICallHandler(getTransactionDetailsReq.toXMLString(null, "GetTransactionDetailsReq"), null, null, this.configurationMap);
		APICallPreHandler apiCallPreHandler = new MerchantAPICallPreHandler(defaultHandler, apiUsername, getAccessToken(), getTokenSecret(), SDK_NAME, SDK_VERSION, "PayPalAPI", this.configurationMap);
	 	String response = call(apiCallPreHandler);
		InputSource inStream = new InputSource();
		inStream.setCharacterStream(new StringReader((String) response));
		try {
			Node node = (Node) XPathFactory.newInstance().newXPath().evaluate("Envelope/Body/GetTransactionDetailsResponse", DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(inStream), XPathConstants.NODE);
			return new GetTransactionDetailsResponseType(node);
		} catch (XPathExpressionException exe) {
			throw new RuntimeException("Unable to parse response", exe);
		}
	 }

	/** 
	 * 
	 * @throws SSLConfigurationException
	 * @throws InvalidCredentialException
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @throws HttpErrorException
	 * @throws InvalidResponseDataException
	 * @throws ClientActionRequiredException
	 * @throws MissingCredentialException
	 * @throws InterruptedException
	 * @throws OAuthException
	 */
	 public BillUserResponseType billUser(BillUserReq billUserReq) throws SSLConfigurationException, InvalidCredentialException, IOException, HttpErrorException, InvalidResponseDataException, ClientActionRequiredException, MissingCredentialException, InterruptedException, OAuthException, ParserConfigurationException, SAXException  {
	 	return billUser(billUserReq, (String) null);
	 }

	/** 
	 * 
	 * @throws SSLConfigurationException
	 * @throws InvalidCredentialException
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @throws HttpErrorException
	 * @throws InvalidResponseDataException
	 * @throws ClientActionRequiredException
	 * @throws MissingCredentialException
	 * @throws InterruptedException
	 * @throws OAuthException
	 */
	 public BillUserResponseType billUser(BillUserReq billUserReq, ICredential credential) throws SSLConfigurationException, InvalidCredentialException, IOException, HttpErrorException, InvalidResponseDataException, ClientActionRequiredException, MissingCredentialException, InterruptedException, OAuthException, ParserConfigurationException, SAXException  {
		setStandardParams(billUserReq.getBillUserRequest());
		DefaultSOAPAPICallHandler defaultHandler = new DefaultSOAPAPICallHandler(billUserReq.toXMLString(null, "BillUserReq"), null, null, this.configurationMap);
		APICallPreHandler apiCallPreHandler = new MerchantAPICallPreHandler(defaultHandler, credential, SDK_NAME, SDK_VERSION, "PayPalAPI", this.configurationMap);
	 	String response = call(apiCallPreHandler);
		InputSource inStream = new InputSource();
		inStream.setCharacterStream(new StringReader((String) response));
		try {
			Node node = (Node) XPathFactory.newInstance().newXPath().evaluate("Envelope/Body/BillUserResponse", DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(inStream), XPathConstants.NODE);
			return new BillUserResponseType(node);
		} catch (XPathExpressionException exe) {
			throw new RuntimeException("Unable to parse response", exe);
		}
	}
	
	/**	
	 * 
	 * @throws SSLConfigurationException
	 * @throws InvalidCredentialException
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @throws HttpErrorException
	 * @throws InvalidResponseDataException
	 * @throws ClientActionRequiredException
	 * @throws MissingCredentialException
	 * @throws InterruptedException
	 * @throws OAuthException
	 */
	 public BillUserResponseType billUser(BillUserReq billUserReq, String apiUsername) throws SSLConfigurationException, InvalidCredentialException, IOException, HttpErrorException, InvalidResponseDataException, ClientActionRequiredException, MissingCredentialException, InterruptedException, OAuthException, ParserConfigurationException, SAXException, IOException {
		setStandardParams(billUserReq.getBillUserRequest());
		DefaultSOAPAPICallHandler defaultHandler = new DefaultSOAPAPICallHandler(billUserReq.toXMLString(null, "BillUserReq"), null, null, this.configurationMap);
		APICallPreHandler apiCallPreHandler = new MerchantAPICallPreHandler(defaultHandler, apiUsername, getAccessToken(), getTokenSecret(), SDK_NAME, SDK_VERSION, "PayPalAPI", this.configurationMap);
	 	String response = call(apiCallPreHandler);
		InputSource inStream = new InputSource();
		inStream.setCharacterStream(new StringReader((String) response));
		try {
			Node node = (Node) XPathFactory.newInstance().newXPath().evaluate("Envelope/Body/BillUserResponse", DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(inStream), XPathConstants.NODE);
			return new BillUserResponseType(node);
		} catch (XPathExpressionException exe) {
			throw new RuntimeException("Unable to parse response", exe);
		}
	 }

	/** 
	 * 
	 * @throws SSLConfigurationException
	 * @throws InvalidCredentialException
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @throws HttpErrorException
	 * @throws InvalidResponseDataException
	 * @throws ClientActionRequiredException
	 * @throws MissingCredentialException
	 * @throws InterruptedException
	 * @throws OAuthException
	 */
	 public TransactionSearchResponseType transactionSearch(TransactionSearchReq transactionSearchReq) throws SSLConfigurationException, InvalidCredentialException, IOException, HttpErrorException, InvalidResponseDataException, ClientActionRequiredException, MissingCredentialException, InterruptedException, OAuthException, ParserConfigurationException, SAXException  {
	 	return transactionSearch(transactionSearchReq, (String) null);
	 }

	/** 
	 * 
	 * @throws SSLConfigurationException
	 * @throws InvalidCredentialException
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @throws HttpErrorException
	 * @throws InvalidResponseDataException
	 * @throws ClientActionRequiredException
	 * @throws MissingCredentialException
	 * @throws InterruptedException
	 * @throws OAuthException
	 */
	 public TransactionSearchResponseType transactionSearch(TransactionSearchReq transactionSearchReq, ICredential credential) throws SSLConfigurationException, InvalidCredentialException, IOException, HttpErrorException, InvalidResponseDataException, ClientActionRequiredException, MissingCredentialException, InterruptedException, OAuthException, ParserConfigurationException, SAXException  {
		setStandardParams(transactionSearchReq.getTransactionSearchRequest());
		DefaultSOAPAPICallHandler defaultHandler = new DefaultSOAPAPICallHandler(transactionSearchReq.toXMLString(null, "TransactionSearchReq"), null, null, this.configurationMap);
		APICallPreHandler apiCallPreHandler = new MerchantAPICallPreHandler(defaultHandler, credential, SDK_NAME, SDK_VERSION, "PayPalAPI", this.configurationMap);
	 	String response = call(apiCallPreHandler);
		InputSource inStream = new InputSource();
		inStream.setCharacterStream(new StringReader((String) response));
		try {
			Node node = (Node) XPathFactory.newInstance().newXPath().evaluate("Envelope/Body/TransactionSearchResponse", DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(inStream), XPathConstants.NODE);
			return new TransactionSearchResponseType(node);
		} catch (XPathExpressionException exe) {
			throw new RuntimeException("Unable to parse response", exe);
		}
	}
	
	/**	
	 * 
	 * @throws SSLConfigurationException
	 * @throws InvalidCredentialException
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @throws HttpErrorException
	 * @throws InvalidResponseDataException
	 * @throws ClientActionRequiredException
	 * @throws MissingCredentialException
	 * @throws InterruptedException
	 * @throws OAuthException
	 */
	 public TransactionSearchResponseType transactionSearch(TransactionSearchReq transactionSearchReq, String apiUsername) throws SSLConfigurationException, InvalidCredentialException, IOException, HttpErrorException, InvalidResponseDataException, ClientActionRequiredException, MissingCredentialException, InterruptedException, OAuthException, ParserConfigurationException, SAXException, IOException {
		setStandardParams(transactionSearchReq.getTransactionSearchRequest());
		DefaultSOAPAPICallHandler defaultHandler = new DefaultSOAPAPICallHandler(transactionSearchReq.toXMLString(null, "TransactionSearchReq"), null, null, this.configurationMap);
		APICallPreHandler apiCallPreHandler = new MerchantAPICallPreHandler(defaultHandler, apiUsername, getAccessToken(), getTokenSecret(), SDK_NAME, SDK_VERSION, "PayPalAPI", this.configurationMap);
	 	String response = call(apiCallPreHandler);
		InputSource inStream = new InputSource();
		inStream.setCharacterStream(new StringReader((String) response));
		try {
			Node node = (Node) XPathFactory.newInstance().newXPath().evaluate("Envelope/Body/TransactionSearchResponse", DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(inStream), XPathConstants.NODE);
			return new TransactionSearchResponseType(node);
		} catch (XPathExpressionException exe) {
			throw new RuntimeException("Unable to parse response", exe);
		}
	 }

	/** 
	 * 
	 * @throws SSLConfigurationException
	 * @throws InvalidCredentialException
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @throws HttpErrorException
	 * @throws InvalidResponseDataException
	 * @throws ClientActionRequiredException
	 * @throws MissingCredentialException
	 * @throws InterruptedException
	 * @throws OAuthException
	 */
	 public MassPayResponseType massPay(MassPayReq massPayReq) throws SSLConfigurationException, InvalidCredentialException, IOException, HttpErrorException, InvalidResponseDataException, ClientActionRequiredException, MissingCredentialException, InterruptedException, OAuthException, ParserConfigurationException, SAXException  {
	 	return massPay(massPayReq, (String) null);
	 }

	/** 
	 * 
	 * @throws SSLConfigurationException
	 * @throws InvalidCredentialException
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @throws HttpErrorException
	 * @throws InvalidResponseDataException
	 * @throws ClientActionRequiredException
	 * @throws MissingCredentialException
	 * @throws InterruptedException
	 * @throws OAuthException
	 */
	 public MassPayResponseType massPay(MassPayReq massPayReq, ICredential credential) throws SSLConfigurationException, InvalidCredentialException, IOException, HttpErrorException, InvalidResponseDataException, ClientActionRequiredException, MissingCredentialException, InterruptedException, OAuthException, ParserConfigurationException, SAXException  {
		setStandardParams(massPayReq.getMassPayRequest());
		DefaultSOAPAPICallHandler defaultHandler = new DefaultSOAPAPICallHandler(massPayReq.toXMLString(null, "MassPayReq"), null, null, this.configurationMap);
		APICallPreHandler apiCallPreHandler = new MerchantAPICallPreHandler(defaultHandler, credential, SDK_NAME, SDK_VERSION, "PayPalAPI", this.configurationMap);
	 	String response = call(apiCallPreHandler);
		InputSource inStream = new InputSource();
		inStream.setCharacterStream(new StringReader((String) response));
		try {
			Node node = (Node) XPathFactory.newInstance().newXPath().evaluate("Envelope/Body/MassPayResponse", DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(inStream), XPathConstants.NODE);
			return new MassPayResponseType(node);
		} catch (XPathExpressionException exe) {
			throw new RuntimeException("Unable to parse response", exe);
		}
	}
	
	/**	
	 * 
	 * @throws SSLConfigurationException
	 * @throws InvalidCredentialException
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @throws HttpErrorException
	 * @throws InvalidResponseDataException
	 * @throws ClientActionRequiredException
	 * @throws MissingCredentialException
	 * @throws InterruptedException
	 * @throws OAuthException
	 */
	 public MassPayResponseType massPay(MassPayReq massPayReq, String apiUsername) throws SSLConfigurationException, InvalidCredentialException, IOException, HttpErrorException, InvalidResponseDataException, ClientActionRequiredException, MissingCredentialException, InterruptedException, OAuthException, ParserConfigurationException, SAXException, IOException {
		setStandardParams(massPayReq.getMassPayRequest());
		DefaultSOAPAPICallHandler defaultHandler = new DefaultSOAPAPICallHandler(massPayReq.toXMLString(null, "MassPayReq"), null, null, this.configurationMap);
		APICallPreHandler apiCallPreHandler = new MerchantAPICallPreHandler(defaultHandler, apiUsername, getAccessToken(), getTokenSecret(), SDK_NAME, SDK_VERSION, "PayPalAPI", this.configurationMap);
	 	String response = call(apiCallPreHandler);
		InputSource inStream = new InputSource();
		inStream.setCharacterStream(new StringReader((String) response));
		try {
			Node node = (Node) XPathFactory.newInstance().newXPath().evaluate("Envelope/Body/MassPayResponse", DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(inStream), XPathConstants.NODE);
			return new MassPayResponseType(node);
		} catch (XPathExpressionException exe) {
			throw new RuntimeException("Unable to parse response", exe);
		}
	 }

	/** 
	 * 
	 * @throws SSLConfigurationException
	 * @throws InvalidCredentialException
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @throws HttpErrorException
	 * @throws InvalidResponseDataException
	 * @throws ClientActionRequiredException
	 * @throws MissingCredentialException
	 * @throws InterruptedException
	 * @throws OAuthException
	 */
	 public BAUpdateResponseType billAgreementUpdate(BillAgreementUpdateReq billAgreementUpdateReq) throws SSLConfigurationException, InvalidCredentialException, IOException, HttpErrorException, InvalidResponseDataException, ClientActionRequiredException, MissingCredentialException, InterruptedException, OAuthException, ParserConfigurationException, SAXException  {
	 	return billAgreementUpdate(billAgreementUpdateReq, (String) null);
	 }

	/** 
	 * 
	 * @throws SSLConfigurationException
	 * @throws InvalidCredentialException
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @throws HttpErrorException
	 * @throws InvalidResponseDataException
	 * @throws ClientActionRequiredException
	 * @throws MissingCredentialException
	 * @throws InterruptedException
	 * @throws OAuthException
	 */
	 public BAUpdateResponseType billAgreementUpdate(BillAgreementUpdateReq billAgreementUpdateReq, ICredential credential) throws SSLConfigurationException, InvalidCredentialException, IOException, HttpErrorException, InvalidResponseDataException, ClientActionRequiredException, MissingCredentialException, InterruptedException, OAuthException, ParserConfigurationException, SAXException  {
		setStandardParams(billAgreementUpdateReq.getBAUpdateRequest());
		DefaultSOAPAPICallHandler defaultHandler = new DefaultSOAPAPICallHandler(billAgreementUpdateReq.toXMLString(null, "BillAgreementUpdateReq"), null, null, this.configurationMap);
		APICallPreHandler apiCallPreHandler = new MerchantAPICallPreHandler(defaultHandler, credential, SDK_NAME, SDK_VERSION, "PayPalAPI", this.configurationMap);
	 	String response = call(apiCallPreHandler);
		InputSource inStream = new InputSource();
		inStream.setCharacterStream(new StringReader((String) response));
		try {
			Node node = (Node) XPathFactory.newInstance().newXPath().evaluate("Envelope/Body/BAUpdateResponse", DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(inStream), XPathConstants.NODE);
			return new BAUpdateResponseType(node);
		} catch (XPathExpressionException exe) {
			throw new RuntimeException("Unable to parse response", exe);
		}
	}
	
	/**	
	 * 
	 * @throws SSLConfigurationException
	 * @throws InvalidCredentialException
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @throws HttpErrorException
	 * @throws InvalidResponseDataException
	 * @throws ClientActionRequiredException
	 * @throws MissingCredentialException
	 * @throws InterruptedException
	 * @throws OAuthException
	 */
	 public BAUpdateResponseType billAgreementUpdate(BillAgreementUpdateReq billAgreementUpdateReq, String apiUsername) throws SSLConfigurationException, InvalidCredentialException, IOException, HttpErrorException, InvalidResponseDataException, ClientActionRequiredException, MissingCredentialException, InterruptedException, OAuthException, ParserConfigurationException, SAXException, IOException {
		setStandardParams(billAgreementUpdateReq.getBAUpdateRequest());
		DefaultSOAPAPICallHandler defaultHandler = new DefaultSOAPAPICallHandler(billAgreementUpdateReq.toXMLString(null, "BillAgreementUpdateReq"), null, null, this.configurationMap);
		APICallPreHandler apiCallPreHandler = new MerchantAPICallPreHandler(defaultHandler, apiUsername, getAccessToken(), getTokenSecret(), SDK_NAME, SDK_VERSION, "PayPalAPI", this.configurationMap);
	 	String response = call(apiCallPreHandler);
		InputSource inStream = new InputSource();
		inStream.setCharacterStream(new StringReader((String) response));
		try {
			Node node = (Node) XPathFactory.newInstance().newXPath().evaluate("Envelope/Body/BAUpdateResponse", DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(inStream), XPathConstants.NODE);
			return new BAUpdateResponseType(node);
		} catch (XPathExpressionException exe) {
			throw new RuntimeException("Unable to parse response", exe);
		}
	 }

	/** 
	 * 
	 * @throws SSLConfigurationException
	 * @throws InvalidCredentialException
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @throws HttpErrorException
	 * @throws InvalidResponseDataException
	 * @throws ClientActionRequiredException
	 * @throws MissingCredentialException
	 * @throws InterruptedException
	 * @throws OAuthException
	 */
	 public AddressVerifyResponseType addressVerify(AddressVerifyReq addressVerifyReq) throws SSLConfigurationException, InvalidCredentialException, IOException, HttpErrorException, InvalidResponseDataException, ClientActionRequiredException, MissingCredentialException, InterruptedException, OAuthException, ParserConfigurationException, SAXException  {
	 	return addressVerify(addressVerifyReq, (String) null);
	 }

	/** 
	 * 
	 * @throws SSLConfigurationException
	 * @throws InvalidCredentialException
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @throws HttpErrorException
	 * @throws InvalidResponseDataException
	 * @throws ClientActionRequiredException
	 * @throws MissingCredentialException
	 * @throws InterruptedException
	 * @throws OAuthException
	 */
	 public AddressVerifyResponseType addressVerify(AddressVerifyReq addressVerifyReq, ICredential credential) throws SSLConfigurationException, InvalidCredentialException, IOException, HttpErrorException, InvalidResponseDataException, ClientActionRequiredException, MissingCredentialException, InterruptedException, OAuthException, ParserConfigurationException, SAXException  {
		setStandardParams(addressVerifyReq.getAddressVerifyRequest());
		DefaultSOAPAPICallHandler defaultHandler = new DefaultSOAPAPICallHandler(addressVerifyReq.toXMLString(null, "AddressVerifyReq"), null, null, this.configurationMap);
		APICallPreHandler apiCallPreHandler = new MerchantAPICallPreHandler(defaultHandler, credential, SDK_NAME, SDK_VERSION, "PayPalAPI", this.configurationMap);
	 	String response = call(apiCallPreHandler);
		InputSource inStream = new InputSource();
		inStream.setCharacterStream(new StringReader((String) response));
		try {
			Node node = (Node) XPathFactory.newInstance().newXPath().evaluate("Envelope/Body/AddressVerifyResponse", DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(inStream), XPathConstants.NODE);
			return new AddressVerifyResponseType(node);
		} catch (XPathExpressionException exe) {
			throw new RuntimeException("Unable to parse response", exe);
		}
	}
	
	/**	
	 * 
	 * @throws SSLConfigurationException
	 * @throws InvalidCredentialException
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @throws HttpErrorException
	 * @throws InvalidResponseDataException
	 * @throws ClientActionRequiredException
	 * @throws MissingCredentialException
	 * @throws InterruptedException
	 * @throws OAuthException
	 */
	 public AddressVerifyResponseType addressVerify(AddressVerifyReq addressVerifyReq, String apiUsername) throws SSLConfigurationException, InvalidCredentialException, IOException, HttpErrorException, InvalidResponseDataException, ClientActionRequiredException, MissingCredentialException, InterruptedException, OAuthException, ParserConfigurationException, SAXException, IOException {
		setStandardParams(addressVerifyReq.getAddressVerifyRequest());
		DefaultSOAPAPICallHandler defaultHandler = new DefaultSOAPAPICallHandler(addressVerifyReq.toXMLString(null, "AddressVerifyReq"), null, null, this.configurationMap);
		APICallPreHandler apiCallPreHandler = new MerchantAPICallPreHandler(defaultHandler, apiUsername, getAccessToken(), getTokenSecret(), SDK_NAME, SDK_VERSION, "PayPalAPI", this.configurationMap);
	 	String response = call(apiCallPreHandler);
		InputSource inStream = new InputSource();
		inStream.setCharacterStream(new StringReader((String) response));
		try {
			Node node = (Node) XPathFactory.newInstance().newXPath().evaluate("Envelope/Body/AddressVerifyResponse", DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(inStream), XPathConstants.NODE);
			return new AddressVerifyResponseType(node);
		} catch (XPathExpressionException exe) {
			throw new RuntimeException("Unable to parse response", exe);
		}
	 }

	/** 
	 * 
	 * @throws SSLConfigurationException
	 * @throws InvalidCredentialException
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @throws HttpErrorException
	 * @throws InvalidResponseDataException
	 * @throws ClientActionRequiredException
	 * @throws MissingCredentialException
	 * @throws InterruptedException
	 * @throws OAuthException
	 */
	 public EnterBoardingResponseType enterBoarding(EnterBoardingReq enterBoardingReq) throws SSLConfigurationException, InvalidCredentialException, IOException, HttpErrorException, InvalidResponseDataException, ClientActionRequiredException, MissingCredentialException, InterruptedException, OAuthException, ParserConfigurationException, SAXException  {
	 	return enterBoarding(enterBoardingReq, (String) null);
	 }

	/** 
	 * 
	 * @throws SSLConfigurationException
	 * @throws InvalidCredentialException
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @throws HttpErrorException
	 * @throws InvalidResponseDataException
	 * @throws ClientActionRequiredException
	 * @throws MissingCredentialException
	 * @throws InterruptedException
	 * @throws OAuthException
	 */
	 public EnterBoardingResponseType enterBoarding(EnterBoardingReq enterBoardingReq, ICredential credential) throws SSLConfigurationException, InvalidCredentialException, IOException, HttpErrorException, InvalidResponseDataException, ClientActionRequiredException, MissingCredentialException, InterruptedException, OAuthException, ParserConfigurationException, SAXException  {
		setStandardParams(enterBoardingReq.getEnterBoardingRequest());
		DefaultSOAPAPICallHandler defaultHandler = new DefaultSOAPAPICallHandler(enterBoardingReq.toXMLString(null, "EnterBoardingReq"), null, null, this.configurationMap);
		APICallPreHandler apiCallPreHandler = new MerchantAPICallPreHandler(defaultHandler, credential, SDK_NAME, SDK_VERSION, "PayPalAPI", this.configurationMap);
	 	String response = call(apiCallPreHandler);
		InputSource inStream = new InputSource();
		inStream.setCharacterStream(new StringReader((String) response));
		try {
			Node node = (Node) XPathFactory.newInstance().newXPath().evaluate("Envelope/Body/EnterBoardingResponse", DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(inStream), XPathConstants.NODE);
			return new EnterBoardingResponseType(node);
		} catch (XPathExpressionException exe) {
			throw new RuntimeException("Unable to parse response", exe);
		}
	}
	
	/**	
	 * 
	 * @throws SSLConfigurationException
	 * @throws InvalidCredentialException
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @throws HttpErrorException
	 * @throws InvalidResponseDataException
	 * @throws ClientActionRequiredException
	 * @throws MissingCredentialException
	 * @throws InterruptedException
	 * @throws OAuthException
	 */
	 public EnterBoardingResponseType enterBoarding(EnterBoardingReq enterBoardingReq, String apiUsername) throws SSLConfigurationException, InvalidCredentialException, IOException, HttpErrorException, InvalidResponseDataException, ClientActionRequiredException, MissingCredentialException, InterruptedException, OAuthException, ParserConfigurationException, SAXException, IOException {
		setStandardParams(enterBoardingReq.getEnterBoardingRequest());
		DefaultSOAPAPICallHandler defaultHandler = new DefaultSOAPAPICallHandler(enterBoardingReq.toXMLString(null, "EnterBoardingReq"), null, null, this.configurationMap);
		APICallPreHandler apiCallPreHandler = new MerchantAPICallPreHandler(defaultHandler, apiUsername, getAccessToken(), getTokenSecret(), SDK_NAME, SDK_VERSION, "PayPalAPI", this.configurationMap);
	 	String response = call(apiCallPreHandler);
		InputSource inStream = new InputSource();
		inStream.setCharacterStream(new StringReader((String) response));
		try {
			Node node = (Node) XPathFactory.newInstance().newXPath().evaluate("Envelope/Body/EnterBoardingResponse", DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(inStream), XPathConstants.NODE);
			return new EnterBoardingResponseType(node);
		} catch (XPathExpressionException exe) {
			throw new RuntimeException("Unable to parse response", exe);
		}
	 }

	/** 
	 * 
	 * @throws SSLConfigurationException
	 * @throws InvalidCredentialException
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @throws HttpErrorException
	 * @throws InvalidResponseDataException
	 * @throws ClientActionRequiredException
	 * @throws MissingCredentialException
	 * @throws InterruptedException
	 * @throws OAuthException
	 */
	 public GetBoardingDetailsResponseType getBoardingDetails(GetBoardingDetailsReq getBoardingDetailsReq) throws SSLConfigurationException, InvalidCredentialException, IOException, HttpErrorException, InvalidResponseDataException, ClientActionRequiredException, MissingCredentialException, InterruptedException, OAuthException, ParserConfigurationException, SAXException  {
	 	return getBoardingDetails(getBoardingDetailsReq, (String) null);
	 }

	/** 
	 * 
	 * @throws SSLConfigurationException
	 * @throws InvalidCredentialException
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @throws HttpErrorException
	 * @throws InvalidResponseDataException
	 * @throws ClientActionRequiredException
	 * @throws MissingCredentialException
	 * @throws InterruptedException
	 * @throws OAuthException
	 */
	 public GetBoardingDetailsResponseType getBoardingDetails(GetBoardingDetailsReq getBoardingDetailsReq, ICredential credential) throws SSLConfigurationException, InvalidCredentialException, IOException, HttpErrorException, InvalidResponseDataException, ClientActionRequiredException, MissingCredentialException, InterruptedException, OAuthException, ParserConfigurationException, SAXException  {
		setStandardParams(getBoardingDetailsReq.getGetBoardingDetailsRequest());
		DefaultSOAPAPICallHandler defaultHandler = new DefaultSOAPAPICallHandler(getBoardingDetailsReq.toXMLString(null, "GetBoardingDetailsReq"), null, null, this.configurationMap);
		APICallPreHandler apiCallPreHandler = new MerchantAPICallPreHandler(defaultHandler, credential, SDK_NAME, SDK_VERSION, "PayPalAPI", this.configurationMap);
	 	String response = call(apiCallPreHandler);
		InputSource inStream = new InputSource();
		inStream.setCharacterStream(new StringReader((String) response));
		try {
			Node node = (Node) XPathFactory.newInstance().newXPath().evaluate("Envelope/Body/GetBoardingDetailsResponse", DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(inStream), XPathConstants.NODE);
			return new GetBoardingDetailsResponseType(node);
		} catch (XPathExpressionException exe) {
			throw new RuntimeException("Unable to parse response", exe);
		}
	}
	
	/**	
	 * 
	 * @throws SSLConfigurationException
	 * @throws InvalidCredentialException
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @throws HttpErrorException
	 * @throws InvalidResponseDataException
	 * @throws ClientActionRequiredException
	 * @throws MissingCredentialException
	 * @throws InterruptedException
	 * @throws OAuthException
	 */
	 public GetBoardingDetailsResponseType getBoardingDetails(GetBoardingDetailsReq getBoardingDetailsReq, String apiUsername) throws SSLConfigurationException, InvalidCredentialException, IOException, HttpErrorException, InvalidResponseDataException, ClientActionRequiredException, MissingCredentialException, InterruptedException, OAuthException, ParserConfigurationException, SAXException, IOException {
		setStandardParams(getBoardingDetailsReq.getGetBoardingDetailsRequest());
		DefaultSOAPAPICallHandler defaultHandler = new DefaultSOAPAPICallHandler(getBoardingDetailsReq.toXMLString(null, "GetBoardingDetailsReq"), null, null, this.configurationMap);
		APICallPreHandler apiCallPreHandler = new MerchantAPICallPreHandler(defaultHandler, apiUsername, getAccessToken(), getTokenSecret(), SDK_NAME, SDK_VERSION, "PayPalAPI", this.configurationMap);
	 	String response = call(apiCallPreHandler);
		InputSource inStream = new InputSource();
		inStream.setCharacterStream(new StringReader((String) response));
		try {
			Node node = (Node) XPathFactory.newInstance().newXPath().evaluate("Envelope/Body/GetBoardingDetailsResponse", DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(inStream), XPathConstants.NODE);
			return new GetBoardingDetailsResponseType(node);
		} catch (XPathExpressionException exe) {
			throw new RuntimeException("Unable to parse response", exe);
		}
	 }

	/** 
	 * 
	 * @throws SSLConfigurationException
	 * @throws InvalidCredentialException
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @throws HttpErrorException
	 * @throws InvalidResponseDataException
	 * @throws ClientActionRequiredException
	 * @throws MissingCredentialException
	 * @throws InterruptedException
	 * @throws OAuthException
	 */
	 public CreateMobilePaymentResponseType createMobilePayment(CreateMobilePaymentReq createMobilePaymentReq) throws SSLConfigurationException, InvalidCredentialException, IOException, HttpErrorException, InvalidResponseDataException, ClientActionRequiredException, MissingCredentialException, InterruptedException, OAuthException, ParserConfigurationException, SAXException  {
	 	return createMobilePayment(createMobilePaymentReq, (String) null);
	 }

	/** 
	 * 
	 * @throws SSLConfigurationException
	 * @throws InvalidCredentialException
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @throws HttpErrorException
	 * @throws InvalidResponseDataException
	 * @throws ClientActionRequiredException
	 * @throws MissingCredentialException
	 * @throws InterruptedException
	 * @throws OAuthException
	 */
	 public CreateMobilePaymentResponseType createMobilePayment(CreateMobilePaymentReq createMobilePaymentReq, ICredential credential) throws SSLConfigurationException, InvalidCredentialException, IOException, HttpErrorException, InvalidResponseDataException, ClientActionRequiredException, MissingCredentialException, InterruptedException, OAuthException, ParserConfigurationException, SAXException  {
		setStandardParams(createMobilePaymentReq.getCreateMobilePaymentRequest());
		DefaultSOAPAPICallHandler defaultHandler = new DefaultSOAPAPICallHandler(createMobilePaymentReq.toXMLString(null, "CreateMobilePaymentReq"), null, null, this.configurationMap);
		APICallPreHandler apiCallPreHandler = new MerchantAPICallPreHandler(defaultHandler, credential, SDK_NAME, SDK_VERSION, "PayPalAPI", this.configurationMap);
	 	String response = call(apiCallPreHandler);
		InputSource inStream = new InputSource();
		inStream.setCharacterStream(new StringReader((String) response));
		try {
			Node node = (Node) XPathFactory.newInstance().newXPath().evaluate("Envelope/Body/CreateMobilePaymentResponse", DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(inStream), XPathConstants.NODE);
			return new CreateMobilePaymentResponseType(node);
		} catch (XPathExpressionException exe) {
			throw new RuntimeException("Unable to parse response", exe);
		}
	}
	
	/**	
	 * 
	 * @throws SSLConfigurationException
	 * @throws InvalidCredentialException
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @throws HttpErrorException
	 * @throws InvalidResponseDataException
	 * @throws ClientActionRequiredException
	 * @throws MissingCredentialException
	 * @throws InterruptedException
	 * @throws OAuthException
	 */
	 public CreateMobilePaymentResponseType createMobilePayment(CreateMobilePaymentReq createMobilePaymentReq, String apiUsername) throws SSLConfigurationException, InvalidCredentialException, IOException, HttpErrorException, InvalidResponseDataException, ClientActionRequiredException, MissingCredentialException, InterruptedException, OAuthException, ParserConfigurationException, SAXException, IOException {
		setStandardParams(createMobilePaymentReq.getCreateMobilePaymentRequest());
		DefaultSOAPAPICallHandler defaultHandler = new DefaultSOAPAPICallHandler(createMobilePaymentReq.toXMLString(null, "CreateMobilePaymentReq"), null, null, this.configurationMap);
		APICallPreHandler apiCallPreHandler = new MerchantAPICallPreHandler(defaultHandler, apiUsername, getAccessToken(), getTokenSecret(), SDK_NAME, SDK_VERSION, "PayPalAPI", this.configurationMap);
	 	String response = call(apiCallPreHandler);
		InputSource inStream = new InputSource();
		inStream.setCharacterStream(new StringReader((String) response));
		try {
			Node node = (Node) XPathFactory.newInstance().newXPath().evaluate("Envelope/Body/CreateMobilePaymentResponse", DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(inStream), XPathConstants.NODE);
			return new CreateMobilePaymentResponseType(node);
		} catch (XPathExpressionException exe) {
			throw new RuntimeException("Unable to parse response", exe);
		}
	 }

	/** 
	 * 
	 * @throws SSLConfigurationException
	 * @throws InvalidCredentialException
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @throws HttpErrorException
	 * @throws InvalidResponseDataException
	 * @throws ClientActionRequiredException
	 * @throws MissingCredentialException
	 * @throws InterruptedException
	 * @throws OAuthException
	 */
	 public GetMobileStatusResponseType getMobileStatus(GetMobileStatusReq getMobileStatusReq) throws SSLConfigurationException, InvalidCredentialException, IOException, HttpErrorException, InvalidResponseDataException, ClientActionRequiredException, MissingCredentialException, InterruptedException, OAuthException, ParserConfigurationException, SAXException  {
	 	return getMobileStatus(getMobileStatusReq, (String) null);
	 }

	/** 
	 * 
	 * @throws SSLConfigurationException
	 * @throws InvalidCredentialException
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @throws HttpErrorException
	 * @throws InvalidResponseDataException
	 * @throws ClientActionRequiredException
	 * @throws MissingCredentialException
	 * @throws InterruptedException
	 * @throws OAuthException
	 */
	 public GetMobileStatusResponseType getMobileStatus(GetMobileStatusReq getMobileStatusReq, ICredential credential) throws SSLConfigurationException, InvalidCredentialException, IOException, HttpErrorException, InvalidResponseDataException, ClientActionRequiredException, MissingCredentialException, InterruptedException, OAuthException, ParserConfigurationException, SAXException  {
		setStandardParams(getMobileStatusReq.getGetMobileStatusRequest());
		DefaultSOAPAPICallHandler defaultHandler = new DefaultSOAPAPICallHandler(getMobileStatusReq.toXMLString(null, "GetMobileStatusReq"), null, null, this.configurationMap);
		APICallPreHandler apiCallPreHandler = new MerchantAPICallPreHandler(defaultHandler, credential, SDK_NAME, SDK_VERSION, "PayPalAPI", this.configurationMap);
	 	String response = call(apiCallPreHandler);
		InputSource inStream = new InputSource();
		inStream.setCharacterStream(new StringReader((String) response));
		try {
			Node node = (Node) XPathFactory.newInstance().newXPath().evaluate("Envelope/Body/GetMobileStatusResponse", DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(inStream), XPathConstants.NODE);
			return new GetMobileStatusResponseType(node);
		} catch (XPathExpressionException exe) {
			throw new RuntimeException("Unable to parse response", exe);
		}
	}
	
	/**	
	 * 
	 * @throws SSLConfigurationException
	 * @throws InvalidCredentialException
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @throws HttpErrorException
	 * @throws InvalidResponseDataException
	 * @throws ClientActionRequiredException
	 * @throws MissingCredentialException
	 * @throws InterruptedException
	 * @throws OAuthException
	 */
	 public GetMobileStatusResponseType getMobileStatus(GetMobileStatusReq getMobileStatusReq, String apiUsername) throws SSLConfigurationException, InvalidCredentialException, IOException, HttpErrorException, InvalidResponseDataException, ClientActionRequiredException, MissingCredentialException, InterruptedException, OAuthException, ParserConfigurationException, SAXException, IOException {
		setStandardParams(getMobileStatusReq.getGetMobileStatusRequest());
		DefaultSOAPAPICallHandler defaultHandler = new DefaultSOAPAPICallHandler(getMobileStatusReq.toXMLString(null, "GetMobileStatusReq"), null, null, this.configurationMap);
		APICallPreHandler apiCallPreHandler = new MerchantAPICallPreHandler(defaultHandler, apiUsername, getAccessToken(), getTokenSecret(), SDK_NAME, SDK_VERSION, "PayPalAPI", this.configurationMap);
	 	String response = call(apiCallPreHandler);
		InputSource inStream = new InputSource();
		inStream.setCharacterStream(new StringReader((String) response));
		try {
			Node node = (Node) XPathFactory.newInstance().newXPath().evaluate("Envelope/Body/GetMobileStatusResponse", DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(inStream), XPathConstants.NODE);
			return new GetMobileStatusResponseType(node);
		} catch (XPathExpressionException exe) {
			throw new RuntimeException("Unable to parse response", exe);
		}
	 }

	/** 
	 * 
	 * @throws SSLConfigurationException
	 * @throws InvalidCredentialException
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @throws HttpErrorException
	 * @throws InvalidResponseDataException
	 * @throws ClientActionRequiredException
	 * @throws MissingCredentialException
	 * @throws InterruptedException
	 * @throws OAuthException
	 */
	 public SetMobileCheckoutResponseType setMobileCheckout(SetMobileCheckoutReq setMobileCheckoutReq) throws SSLConfigurationException, InvalidCredentialException, IOException, HttpErrorException, InvalidResponseDataException, ClientActionRequiredException, MissingCredentialException, InterruptedException, OAuthException, ParserConfigurationException, SAXException  {
	 	return setMobileCheckout(setMobileCheckoutReq, (String) null);
	 }

	/** 
	 * 
	 * @throws SSLConfigurationException
	 * @throws InvalidCredentialException
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @throws HttpErrorException
	 * @throws InvalidResponseDataException
	 * @throws ClientActionRequiredException
	 * @throws MissingCredentialException
	 * @throws InterruptedException
	 * @throws OAuthException
	 */
	 public SetMobileCheckoutResponseType setMobileCheckout(SetMobileCheckoutReq setMobileCheckoutReq, ICredential credential) throws SSLConfigurationException, InvalidCredentialException, IOException, HttpErrorException, InvalidResponseDataException, ClientActionRequiredException, MissingCredentialException, InterruptedException, OAuthException, ParserConfigurationException, SAXException  {
		setStandardParams(setMobileCheckoutReq.getSetMobileCheckoutRequest());
		DefaultSOAPAPICallHandler defaultHandler = new DefaultSOAPAPICallHandler(setMobileCheckoutReq.toXMLString(null, "SetMobileCheckoutReq"), null, null, this.configurationMap);
		APICallPreHandler apiCallPreHandler = new MerchantAPICallPreHandler(defaultHandler, credential, SDK_NAME, SDK_VERSION, "PayPalAPI", this.configurationMap);
	 	String response = call(apiCallPreHandler);
		InputSource inStream = new InputSource();
		inStream.setCharacterStream(new StringReader((String) response));
		try {
			Node node = (Node) XPathFactory.newInstance().newXPath().evaluate("Envelope/Body/SetMobileCheckoutResponse", DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(inStream), XPathConstants.NODE);
			return new SetMobileCheckoutResponseType(node);
		} catch (XPathExpressionException exe) {
			throw new RuntimeException("Unable to parse response", exe);
		}
	}
	
	/**	
	 * 
	 * @throws SSLConfigurationException
	 * @throws InvalidCredentialException
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @throws HttpErrorException
	 * @throws InvalidResponseDataException
	 * @throws ClientActionRequiredException
	 * @throws MissingCredentialException
	 * @throws InterruptedException
	 * @throws OAuthException
	 */
	 public SetMobileCheckoutResponseType setMobileCheckout(SetMobileCheckoutReq setMobileCheckoutReq, String apiUsername) throws SSLConfigurationException, InvalidCredentialException, IOException, HttpErrorException, InvalidResponseDataException, ClientActionRequiredException, MissingCredentialException, InterruptedException, OAuthException, ParserConfigurationException, SAXException, IOException {
		setStandardParams(setMobileCheckoutReq.getSetMobileCheckoutRequest());
		DefaultSOAPAPICallHandler defaultHandler = new DefaultSOAPAPICallHandler(setMobileCheckoutReq.toXMLString(null, "SetMobileCheckoutReq"), null, null, this.configurationMap);
		APICallPreHandler apiCallPreHandler = new MerchantAPICallPreHandler(defaultHandler, apiUsername, getAccessToken(), getTokenSecret(), SDK_NAME, SDK_VERSION, "PayPalAPI", this.configurationMap);
	 	String response = call(apiCallPreHandler);
		InputSource inStream = new InputSource();
		inStream.setCharacterStream(new StringReader((String) response));
		try {
			Node node = (Node) XPathFactory.newInstance().newXPath().evaluate("Envelope/Body/SetMobileCheckoutResponse", DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(inStream), XPathConstants.NODE);
			return new SetMobileCheckoutResponseType(node);
		} catch (XPathExpressionException exe) {
			throw new RuntimeException("Unable to parse response", exe);
		}
	 }

	/** 
	 * 
	 * @throws SSLConfigurationException
	 * @throws InvalidCredentialException
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @throws HttpErrorException
	 * @throws InvalidResponseDataException
	 * @throws ClientActionRequiredException
	 * @throws MissingCredentialException
	 * @throws InterruptedException
	 * @throws OAuthException
	 */
	 public DoMobileCheckoutPaymentResponseType doMobileCheckoutPayment(DoMobileCheckoutPaymentReq doMobileCheckoutPaymentReq) throws SSLConfigurationException, InvalidCredentialException, IOException, HttpErrorException, InvalidResponseDataException, ClientActionRequiredException, MissingCredentialException, InterruptedException, OAuthException, ParserConfigurationException, SAXException  {
	 	return doMobileCheckoutPayment(doMobileCheckoutPaymentReq, (String) null);
	 }

	/** 
	 * 
	 * @throws SSLConfigurationException
	 * @throws InvalidCredentialException
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @throws HttpErrorException
	 * @throws InvalidResponseDataException
	 * @throws ClientActionRequiredException
	 * @throws MissingCredentialException
	 * @throws InterruptedException
	 * @throws OAuthException
	 */
	 public DoMobileCheckoutPaymentResponseType doMobileCheckoutPayment(DoMobileCheckoutPaymentReq doMobileCheckoutPaymentReq, ICredential credential) throws SSLConfigurationException, InvalidCredentialException, IOException, HttpErrorException, InvalidResponseDataException, ClientActionRequiredException, MissingCredentialException, InterruptedException, OAuthException, ParserConfigurationException, SAXException  {
		setStandardParams(doMobileCheckoutPaymentReq.getDoMobileCheckoutPaymentRequest());
		DefaultSOAPAPICallHandler defaultHandler = new DefaultSOAPAPICallHandler(doMobileCheckoutPaymentReq.toXMLString(null, "DoMobileCheckoutPaymentReq"), null, null, this.configurationMap);
		APICallPreHandler apiCallPreHandler = new MerchantAPICallPreHandler(defaultHandler, credential, SDK_NAME, SDK_VERSION, "PayPalAPI", this.configurationMap);
	 	String response = call(apiCallPreHandler);
		InputSource inStream = new InputSource();
		inStream.setCharacterStream(new StringReader((String) response));
		try {
			Node node = (Node) XPathFactory.newInstance().newXPath().evaluate("Envelope/Body/DoMobileCheckoutPaymentResponse", DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(inStream), XPathConstants.NODE);
			return new DoMobileCheckoutPaymentResponseType(node);
		} catch (XPathExpressionException exe) {
			throw new RuntimeException("Unable to parse response", exe);
		}
	}
	
	/**	
	 * 
	 * @throws SSLConfigurationException
	 * @throws InvalidCredentialException
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @throws HttpErrorException
	 * @throws InvalidResponseDataException
	 * @throws ClientActionRequiredException
	 * @throws MissingCredentialException
	 * @throws InterruptedException
	 * @throws OAuthException
	 */
	 public DoMobileCheckoutPaymentResponseType doMobileCheckoutPayment(DoMobileCheckoutPaymentReq doMobileCheckoutPaymentReq, String apiUsername) throws SSLConfigurationException, InvalidCredentialException, IOException, HttpErrorException, InvalidResponseDataException, ClientActionRequiredException, MissingCredentialException, InterruptedException, OAuthException, ParserConfigurationException, SAXException, IOException {
		setStandardParams(doMobileCheckoutPaymentReq.getDoMobileCheckoutPaymentRequest());
		DefaultSOAPAPICallHandler defaultHandler = new DefaultSOAPAPICallHandler(doMobileCheckoutPaymentReq.toXMLString(null, "DoMobileCheckoutPaymentReq"), null, null, this.configurationMap);
		APICallPreHandler apiCallPreHandler = new MerchantAPICallPreHandler(defaultHandler, apiUsername, getAccessToken(), getTokenSecret(), SDK_NAME, SDK_VERSION, "PayPalAPI", this.configurationMap);
	 	String response = call(apiCallPreHandler);
		InputSource inStream = new InputSource();
		inStream.setCharacterStream(new StringReader((String) response));
		try {
			Node node = (Node) XPathFactory.newInstance().newXPath().evaluate("Envelope/Body/DoMobileCheckoutPaymentResponse", DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(inStream), XPathConstants.NODE);
			return new DoMobileCheckoutPaymentResponseType(node);
		} catch (XPathExpressionException exe) {
			throw new RuntimeException("Unable to parse response", exe);
		}
	 }

	/** 
	 * 
	 * @throws SSLConfigurationException
	 * @throws InvalidCredentialException
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @throws HttpErrorException
	 * @throws InvalidResponseDataException
	 * @throws ClientActionRequiredException
	 * @throws MissingCredentialException
	 * @throws InterruptedException
	 * @throws OAuthException
	 */
	 public GetBalanceResponseType getBalance(GetBalanceReq getBalanceReq) throws SSLConfigurationException, InvalidCredentialException, IOException, HttpErrorException, InvalidResponseDataException, ClientActionRequiredException, MissingCredentialException, InterruptedException, OAuthException, ParserConfigurationException, SAXException  {
	 	return getBalance(getBalanceReq, (String) null);
	 }

	/** 
	 * 
	 * @throws SSLConfigurationException
	 * @throws InvalidCredentialException
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @throws HttpErrorException
	 * @throws InvalidResponseDataException
	 * @throws ClientActionRequiredException
	 * @throws MissingCredentialException
	 * @throws InterruptedException
	 * @throws OAuthException
	 */
	 public GetBalanceResponseType getBalance(GetBalanceReq getBalanceReq, ICredential credential) throws SSLConfigurationException, InvalidCredentialException, IOException, HttpErrorException, InvalidResponseDataException, ClientActionRequiredException, MissingCredentialException, InterruptedException, OAuthException, ParserConfigurationException, SAXException  {
		setStandardParams(getBalanceReq.getGetBalanceRequest());
		DefaultSOAPAPICallHandler defaultHandler = new DefaultSOAPAPICallHandler(getBalanceReq.toXMLString(null, "GetBalanceReq"), null, null, this.configurationMap);
		APICallPreHandler apiCallPreHandler = new MerchantAPICallPreHandler(defaultHandler, credential, SDK_NAME, SDK_VERSION, "PayPalAPI", this.configurationMap);
	 	String response = call(apiCallPreHandler);
		InputSource inStream = new InputSource();
		inStream.setCharacterStream(new StringReader((String) response));
		try {
			Node node = (Node) XPathFactory.newInstance().newXPath().evaluate("Envelope/Body/GetBalanceResponse", DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(inStream), XPathConstants.NODE);
			return new GetBalanceResponseType(node);
		} catch (XPathExpressionException exe) {
			throw new RuntimeException("Unable to parse response", exe);
		}
	}
	
	/**	
	 * 
	 * @throws SSLConfigurationException
	 * @throws InvalidCredentialException
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @throws HttpErrorException
	 * @throws InvalidResponseDataException
	 * @throws ClientActionRequiredException
	 * @throws MissingCredentialException
	 * @throws InterruptedException
	 * @throws OAuthException
	 */
	 public GetBalanceResponseType getBalance(GetBalanceReq getBalanceReq, String apiUsername) throws SSLConfigurationException, InvalidCredentialException, IOException, HttpErrorException, InvalidResponseDataException, ClientActionRequiredException, MissingCredentialException, InterruptedException, OAuthException, ParserConfigurationException, SAXException, IOException {
		setStandardParams(getBalanceReq.getGetBalanceRequest());
		DefaultSOAPAPICallHandler defaultHandler = new DefaultSOAPAPICallHandler(getBalanceReq.toXMLString(null, "GetBalanceReq"), null, null, this.configurationMap);
		APICallPreHandler apiCallPreHandler = new MerchantAPICallPreHandler(defaultHandler, apiUsername, getAccessToken(), getTokenSecret(), SDK_NAME, SDK_VERSION, "PayPalAPI", this.configurationMap);
	 	String response = call(apiCallPreHandler);
		InputSource inStream = new InputSource();
		inStream.setCharacterStream(new StringReader((String) response));
		try {
			Node node = (Node) XPathFactory.newInstance().newXPath().evaluate("Envelope/Body/GetBalanceResponse", DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(inStream), XPathConstants.NODE);
			return new GetBalanceResponseType(node);
		} catch (XPathExpressionException exe) {
			throw new RuntimeException("Unable to parse response", exe);
		}
	 }

	/** 
	 * 
	 * @throws SSLConfigurationException
	 * @throws InvalidCredentialException
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @throws HttpErrorException
	 * @throws InvalidResponseDataException
	 * @throws ClientActionRequiredException
	 * @throws MissingCredentialException
	 * @throws InterruptedException
	 * @throws OAuthException
	 */
	 public GetPalDetailsResponseType getPalDetails(GetPalDetailsReq getPalDetailsReq) throws SSLConfigurationException, InvalidCredentialException, IOException, HttpErrorException, InvalidResponseDataException, ClientActionRequiredException, MissingCredentialException, InterruptedException, OAuthException, ParserConfigurationException, SAXException  {
	 	return getPalDetails(getPalDetailsReq, (String) null);
	 }

	/** 
	 * 
	 * @throws SSLConfigurationException
	 * @throws InvalidCredentialException
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @throws HttpErrorException
	 * @throws InvalidResponseDataException
	 * @throws ClientActionRequiredException
	 * @throws MissingCredentialException
	 * @throws InterruptedException
	 * @throws OAuthException
	 */
	 public GetPalDetailsResponseType getPalDetails(GetPalDetailsReq getPalDetailsReq, ICredential credential) throws SSLConfigurationException, InvalidCredentialException, IOException, HttpErrorException, InvalidResponseDataException, ClientActionRequiredException, MissingCredentialException, InterruptedException, OAuthException, ParserConfigurationException, SAXException  {
		setStandardParams(getPalDetailsReq.getGetPalDetailsRequest());
		DefaultSOAPAPICallHandler defaultHandler = new DefaultSOAPAPICallHandler(getPalDetailsReq.toXMLString(null, "GetPalDetailsReq"), null, null, this.configurationMap);
		APICallPreHandler apiCallPreHandler = new MerchantAPICallPreHandler(defaultHandler, credential, SDK_NAME, SDK_VERSION, "PayPalAPI", this.configurationMap);
	 	String response = call(apiCallPreHandler);
		InputSource inStream = new InputSource();
		inStream.setCharacterStream(new StringReader((String) response));
		try {
			Node node = (Node) XPathFactory.newInstance().newXPath().evaluate("Envelope/Body/GetPalDetailsResponse", DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(inStream), XPathConstants.NODE);
			return new GetPalDetailsResponseType(node);
		} catch (XPathExpressionException exe) {
			throw new RuntimeException("Unable to parse response", exe);
		}
	}
	
	/**	
	 * 
	 * @throws SSLConfigurationException
	 * @throws InvalidCredentialException
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @throws HttpErrorException
	 * @throws InvalidResponseDataException
	 * @throws ClientActionRequiredException
	 * @throws MissingCredentialException
	 * @throws InterruptedException
	 * @throws OAuthException
	 */
	 public GetPalDetailsResponseType getPalDetails(GetPalDetailsReq getPalDetailsReq, String apiUsername) throws SSLConfigurationException, InvalidCredentialException, IOException, HttpErrorException, InvalidResponseDataException, ClientActionRequiredException, MissingCredentialException, InterruptedException, OAuthException, ParserConfigurationException, SAXException, IOException {
		setStandardParams(getPalDetailsReq.getGetPalDetailsRequest());
		DefaultSOAPAPICallHandler defaultHandler = new DefaultSOAPAPICallHandler(getPalDetailsReq.toXMLString(null, "GetPalDetailsReq"), null, null, this.configurationMap);
		APICallPreHandler apiCallPreHandler = new MerchantAPICallPreHandler(defaultHandler, apiUsername, getAccessToken(), getTokenSecret(), SDK_NAME, SDK_VERSION, "PayPalAPI", this.configurationMap);
	 	String response = call(apiCallPreHandler);
		InputSource inStream = new InputSource();
		inStream.setCharacterStream(new StringReader((String) response));
		try {
			Node node = (Node) XPathFactory.newInstance().newXPath().evaluate("Envelope/Body/GetPalDetailsResponse", DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(inStream), XPathConstants.NODE);
			return new GetPalDetailsResponseType(node);
		} catch (XPathExpressionException exe) {
			throw new RuntimeException("Unable to parse response", exe);
		}
	 }

	/** 
	 * 
	 * @throws SSLConfigurationException
	 * @throws InvalidCredentialException
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @throws HttpErrorException
	 * @throws InvalidResponseDataException
	 * @throws ClientActionRequiredException
	 * @throws MissingCredentialException
	 * @throws InterruptedException
	 * @throws OAuthException
	 */
	 public DoExpressCheckoutPaymentResponseType doExpressCheckoutPayment(DoExpressCheckoutPaymentReq doExpressCheckoutPaymentReq) throws SSLConfigurationException, InvalidCredentialException, IOException, HttpErrorException, InvalidResponseDataException, ClientActionRequiredException, MissingCredentialException, InterruptedException, OAuthException, ParserConfigurationException, SAXException  {
	 	return doExpressCheckoutPayment(doExpressCheckoutPaymentReq, (String) null);
	 }

	/** 
	 * 
	 * @throws SSLConfigurationException
	 * @throws InvalidCredentialException
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @throws HttpErrorException
	 * @throws InvalidResponseDataException
	 * @throws ClientActionRequiredException
	 * @throws MissingCredentialException
	 * @throws InterruptedException
	 * @throws OAuthException
	 */
	 public DoExpressCheckoutPaymentResponseType doExpressCheckoutPayment(DoExpressCheckoutPaymentReq doExpressCheckoutPaymentReq, ICredential credential) throws SSLConfigurationException, InvalidCredentialException, IOException, HttpErrorException, InvalidResponseDataException, ClientActionRequiredException, MissingCredentialException, InterruptedException, OAuthException, ParserConfigurationException, SAXException  {
		setStandardParams(doExpressCheckoutPaymentReq.getDoExpressCheckoutPaymentRequest());
		DefaultSOAPAPICallHandler defaultHandler = new DefaultSOAPAPICallHandler(doExpressCheckoutPaymentReq.toXMLString(null, "DoExpressCheckoutPaymentReq"), null, null, this.configurationMap);
		APICallPreHandler apiCallPreHandler = new MerchantAPICallPreHandler(defaultHandler, credential, SDK_NAME, SDK_VERSION, "PayPalAPIAA", this.configurationMap);
	 	String response = call(apiCallPreHandler);
		InputSource inStream = new InputSource();
		inStream.setCharacterStream(new StringReader((String) response));
		try {
			Node node = (Node) XPathFactory.newInstance().newXPath().evaluate("Envelope/Body/DoExpressCheckoutPaymentResponse", DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(inStream), XPathConstants.NODE);
			return new DoExpressCheckoutPaymentResponseType(node);
		} catch (XPathExpressionException exe) {
			throw new RuntimeException("Unable to parse response", exe);
		}
	}
	
	/**	
	 * 
	 * @throws SSLConfigurationException
	 * @throws InvalidCredentialException
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @throws HttpErrorException
	 * @throws InvalidResponseDataException
	 * @throws ClientActionRequiredException
	 * @throws MissingCredentialException
	 * @throws InterruptedException
	 * @throws OAuthException
	 */
	 public DoExpressCheckoutPaymentResponseType doExpressCheckoutPayment(DoExpressCheckoutPaymentReq doExpressCheckoutPaymentReq, String apiUsername) throws SSLConfigurationException, InvalidCredentialException, IOException, HttpErrorException, InvalidResponseDataException, ClientActionRequiredException, MissingCredentialException, InterruptedException, OAuthException, ParserConfigurationException, SAXException, IOException {
		setStandardParams(doExpressCheckoutPaymentReq.getDoExpressCheckoutPaymentRequest());
		DefaultSOAPAPICallHandler defaultHandler = new DefaultSOAPAPICallHandler(doExpressCheckoutPaymentReq.toXMLString(null, "DoExpressCheckoutPaymentReq"), null, null, this.configurationMap);
		APICallPreHandler apiCallPreHandler = new MerchantAPICallPreHandler(defaultHandler, apiUsername, getAccessToken(), getTokenSecret(), SDK_NAME, SDK_VERSION, "PayPalAPIAA", this.configurationMap);
	 	String response = call(apiCallPreHandler);
		InputSource inStream = new InputSource();
		inStream.setCharacterStream(new StringReader((String) response));
		try {
			Node node = (Node) XPathFactory.newInstance().newXPath().evaluate("Envelope/Body/DoExpressCheckoutPaymentResponse", DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(inStream), XPathConstants.NODE);
			return new DoExpressCheckoutPaymentResponseType(node);
		} catch (XPathExpressionException exe) {
			throw new RuntimeException("Unable to parse response", exe);
		}
	 }

	/** 
	 * 
	 * @throws SSLConfigurationException
	 * @throws InvalidCredentialException
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @throws HttpErrorException
	 * @throws InvalidResponseDataException
	 * @throws ClientActionRequiredException
	 * @throws MissingCredentialException
	 * @throws InterruptedException
	 * @throws OAuthException
	 */
	 public DoUATPExpressCheckoutPaymentResponseType doUATPExpressCheckoutPayment(DoUATPExpressCheckoutPaymentReq doUATPExpressCheckoutPaymentReq) throws SSLConfigurationException, InvalidCredentialException, IOException, HttpErrorException, InvalidResponseDataException, ClientActionRequiredException, MissingCredentialException, InterruptedException, OAuthException, ParserConfigurationException, SAXException  {
	 	return doUATPExpressCheckoutPayment(doUATPExpressCheckoutPaymentReq, (String) null);
	 }

	/** 
	 * 
	 * @throws SSLConfigurationException
	 * @throws InvalidCredentialException
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @throws HttpErrorException
	 * @throws InvalidResponseDataException
	 * @throws ClientActionRequiredException
	 * @throws MissingCredentialException
	 * @throws InterruptedException
	 * @throws OAuthException
	 */
	 public DoUATPExpressCheckoutPaymentResponseType doUATPExpressCheckoutPayment(DoUATPExpressCheckoutPaymentReq doUATPExpressCheckoutPaymentReq, ICredential credential) throws SSLConfigurationException, InvalidCredentialException, IOException, HttpErrorException, InvalidResponseDataException, ClientActionRequiredException, MissingCredentialException, InterruptedException, OAuthException, ParserConfigurationException, SAXException  {
		setStandardParams(doUATPExpressCheckoutPaymentReq.getDoUATPExpressCheckoutPaymentRequest());
		DefaultSOAPAPICallHandler defaultHandler = new DefaultSOAPAPICallHandler(doUATPExpressCheckoutPaymentReq.toXMLString(null, "DoUATPExpressCheckoutPaymentReq"), null, null, this.configurationMap);
		APICallPreHandler apiCallPreHandler = new MerchantAPICallPreHandler(defaultHandler, credential, SDK_NAME, SDK_VERSION, "PayPalAPIAA", this.configurationMap);
	 	String response = call(apiCallPreHandler);
		InputSource inStream = new InputSource();
		inStream.setCharacterStream(new StringReader((String) response));
		try {
			Node node = (Node) XPathFactory.newInstance().newXPath().evaluate("Envelope/Body/DoUATPExpressCheckoutPaymentResponse", DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(inStream), XPathConstants.NODE);
			return new DoUATPExpressCheckoutPaymentResponseType(node);
		} catch (XPathExpressionException exe) {
			throw new RuntimeException("Unable to parse response", exe);
		}
	}
	
	/**	
	 * 
	 * @throws SSLConfigurationException
	 * @throws InvalidCredentialException
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @throws HttpErrorException
	 * @throws InvalidResponseDataException
	 * @throws ClientActionRequiredException
	 * @throws MissingCredentialException
	 * @throws InterruptedException
	 * @throws OAuthException
	 */
	 public DoUATPExpressCheckoutPaymentResponseType doUATPExpressCheckoutPayment(DoUATPExpressCheckoutPaymentReq doUATPExpressCheckoutPaymentReq, String apiUsername) throws SSLConfigurationException, InvalidCredentialException, IOException, HttpErrorException, InvalidResponseDataException, ClientActionRequiredException, MissingCredentialException, InterruptedException, OAuthException, ParserConfigurationException, SAXException, IOException {
		setStandardParams(doUATPExpressCheckoutPaymentReq.getDoUATPExpressCheckoutPaymentRequest());
		DefaultSOAPAPICallHandler defaultHandler = new DefaultSOAPAPICallHandler(doUATPExpressCheckoutPaymentReq.toXMLString(null, "DoUATPExpressCheckoutPaymentReq"), null, null, this.configurationMap);
		APICallPreHandler apiCallPreHandler = new MerchantAPICallPreHandler(defaultHandler, apiUsername, getAccessToken(), getTokenSecret(), SDK_NAME, SDK_VERSION, "PayPalAPIAA", this.configurationMap);
	 	String response = call(apiCallPreHandler);
		InputSource inStream = new InputSource();
		inStream.setCharacterStream(new StringReader((String) response));
		try {
			Node node = (Node) XPathFactory.newInstance().newXPath().evaluate("Envelope/Body/DoUATPExpressCheckoutPaymentResponse", DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(inStream), XPathConstants.NODE);
			return new DoUATPExpressCheckoutPaymentResponseType(node);
		} catch (XPathExpressionException exe) {
			throw new RuntimeException("Unable to parse response", exe);
		}
	 }

	/** 
	 * 
	 * @throws SSLConfigurationException
	 * @throws InvalidCredentialException
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @throws HttpErrorException
	 * @throws InvalidResponseDataException
	 * @throws ClientActionRequiredException
	 * @throws MissingCredentialException
	 * @throws InterruptedException
	 * @throws OAuthException
	 */
	 public SetAuthFlowParamResponseType setAuthFlowParam(SetAuthFlowParamReq setAuthFlowParamReq) throws SSLConfigurationException, InvalidCredentialException, IOException, HttpErrorException, InvalidResponseDataException, ClientActionRequiredException, MissingCredentialException, InterruptedException, OAuthException, ParserConfigurationException, SAXException  {
	 	return setAuthFlowParam(setAuthFlowParamReq, (String) null);
	 }

	/** 
	 * 
	 * @throws SSLConfigurationException
	 * @throws InvalidCredentialException
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @throws HttpErrorException
	 * @throws InvalidResponseDataException
	 * @throws ClientActionRequiredException
	 * @throws MissingCredentialException
	 * @throws InterruptedException
	 * @throws OAuthException
	 */
	 public SetAuthFlowParamResponseType setAuthFlowParam(SetAuthFlowParamReq setAuthFlowParamReq, ICredential credential) throws SSLConfigurationException, InvalidCredentialException, IOException, HttpErrorException, InvalidResponseDataException, ClientActionRequiredException, MissingCredentialException, InterruptedException, OAuthException, ParserConfigurationException, SAXException  {
		setStandardParams(setAuthFlowParamReq.getSetAuthFlowParamRequest());
		DefaultSOAPAPICallHandler defaultHandler = new DefaultSOAPAPICallHandler(setAuthFlowParamReq.toXMLString(null, "SetAuthFlowParamReq"), null, null, this.configurationMap);
		APICallPreHandler apiCallPreHandler = new MerchantAPICallPreHandler(defaultHandler, credential, SDK_NAME, SDK_VERSION, "PayPalAPIAA", this.configurationMap);
	 	String response = call(apiCallPreHandler);
		InputSource inStream = new InputSource();
		inStream.setCharacterStream(new StringReader((String) response));
		try {
			Node node = (Node) XPathFactory.newInstance().newXPath().evaluate("Envelope/Body/SetAuthFlowParamResponse", DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(inStream), XPathConstants.NODE);
			return new SetAuthFlowParamResponseType(node);
		} catch (XPathExpressionException exe) {
			throw new RuntimeException("Unable to parse response", exe);
		}
	}
	
	/**	
	 * 
	 * @throws SSLConfigurationException
	 * @throws InvalidCredentialException
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @throws HttpErrorException
	 * @throws InvalidResponseDataException
	 * @throws ClientActionRequiredException
	 * @throws MissingCredentialException
	 * @throws InterruptedException
	 * @throws OAuthException
	 */
	 public SetAuthFlowParamResponseType setAuthFlowParam(SetAuthFlowParamReq setAuthFlowParamReq, String apiUsername) throws SSLConfigurationException, InvalidCredentialException, IOException, HttpErrorException, InvalidResponseDataException, ClientActionRequiredException, MissingCredentialException, InterruptedException, OAuthException, ParserConfigurationException, SAXException, IOException {
		setStandardParams(setAuthFlowParamReq.getSetAuthFlowParamRequest());
		DefaultSOAPAPICallHandler defaultHandler = new DefaultSOAPAPICallHandler(setAuthFlowParamReq.toXMLString(null, "SetAuthFlowParamReq"), null, null, this.configurationMap);
		APICallPreHandler apiCallPreHandler = new MerchantAPICallPreHandler(defaultHandler, apiUsername, getAccessToken(), getTokenSecret(), SDK_NAME, SDK_VERSION, "PayPalAPIAA", this.configurationMap);
	 	String response = call(apiCallPreHandler);
		InputSource inStream = new InputSource();
		inStream.setCharacterStream(new StringReader((String) response));
		try {
			Node node = (Node) XPathFactory.newInstance().newXPath().evaluate("Envelope/Body/SetAuthFlowParamResponse", DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(inStream), XPathConstants.NODE);
			return new SetAuthFlowParamResponseType(node);
		} catch (XPathExpressionException exe) {
			throw new RuntimeException("Unable to parse response", exe);
		}
	 }

	/** 
	 * 
	 * @throws SSLConfigurationException
	 * @throws InvalidCredentialException
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @throws HttpErrorException
	 * @throws InvalidResponseDataException
	 * @throws ClientActionRequiredException
	 * @throws MissingCredentialException
	 * @throws InterruptedException
	 * @throws OAuthException
	 */
	 public GetAuthDetailsResponseType getAuthDetails(GetAuthDetailsReq getAuthDetailsReq) throws SSLConfigurationException, InvalidCredentialException, IOException, HttpErrorException, InvalidResponseDataException, ClientActionRequiredException, MissingCredentialException, InterruptedException, OAuthException, ParserConfigurationException, SAXException  {
	 	return getAuthDetails(getAuthDetailsReq, (String) null);
	 }

	/** 
	 * 
	 * @throws SSLConfigurationException
	 * @throws InvalidCredentialException
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @throws HttpErrorException
	 * @throws InvalidResponseDataException
	 * @throws ClientActionRequiredException
	 * @throws MissingCredentialException
	 * @throws InterruptedException
	 * @throws OAuthException
	 */
	 public GetAuthDetailsResponseType getAuthDetails(GetAuthDetailsReq getAuthDetailsReq, ICredential credential) throws SSLConfigurationException, InvalidCredentialException, IOException, HttpErrorException, InvalidResponseDataException, ClientActionRequiredException, MissingCredentialException, InterruptedException, OAuthException, ParserConfigurationException, SAXException  {
		setStandardParams(getAuthDetailsReq.getGetAuthDetailsRequest());
		DefaultSOAPAPICallHandler defaultHandler = new DefaultSOAPAPICallHandler(getAuthDetailsReq.toXMLString(null, "GetAuthDetailsReq"), null, null, this.configurationMap);
		APICallPreHandler apiCallPreHandler = new MerchantAPICallPreHandler(defaultHandler, credential, SDK_NAME, SDK_VERSION, "PayPalAPIAA", this.configurationMap);
	 	String response = call(apiCallPreHandler);
		InputSource inStream = new InputSource();
		inStream.setCharacterStream(new StringReader((String) response));
		try {
			Node node = (Node) XPathFactory.newInstance().newXPath().evaluate("Envelope/Body/GetAuthDetailsResponse", DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(inStream), XPathConstants.NODE);
			return new GetAuthDetailsResponseType(node);
		} catch (XPathExpressionException exe) {
			throw new RuntimeException("Unable to parse response", exe);
		}
	}
	
	/**	
	 * 
	 * @throws SSLConfigurationException
	 * @throws InvalidCredentialException
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @throws HttpErrorException
	 * @throws InvalidResponseDataException
	 * @throws ClientActionRequiredException
	 * @throws MissingCredentialException
	 * @throws InterruptedException
	 * @throws OAuthException
	 */
	 public GetAuthDetailsResponseType getAuthDetails(GetAuthDetailsReq getAuthDetailsReq, String apiUsername) throws SSLConfigurationException, InvalidCredentialException, IOException, HttpErrorException, InvalidResponseDataException, ClientActionRequiredException, MissingCredentialException, InterruptedException, OAuthException, ParserConfigurationException, SAXException, IOException {
		setStandardParams(getAuthDetailsReq.getGetAuthDetailsRequest());
		DefaultSOAPAPICallHandler defaultHandler = new DefaultSOAPAPICallHandler(getAuthDetailsReq.toXMLString(null, "GetAuthDetailsReq"), null, null, this.configurationMap);
		APICallPreHandler apiCallPreHandler = new MerchantAPICallPreHandler(defaultHandler, apiUsername, getAccessToken(), getTokenSecret(), SDK_NAME, SDK_VERSION, "PayPalAPIAA", this.configurationMap);
	 	String response = call(apiCallPreHandler);
		InputSource inStream = new InputSource();
		inStream.setCharacterStream(new StringReader((String) response));
		try {
			Node node = (Node) XPathFactory.newInstance().newXPath().evaluate("Envelope/Body/GetAuthDetailsResponse", DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(inStream), XPathConstants.NODE);
			return new GetAuthDetailsResponseType(node);
		} catch (XPathExpressionException exe) {
			throw new RuntimeException("Unable to parse response", exe);
		}
	 }

	/** 
	 * 
	 * @throws SSLConfigurationException
	 * @throws InvalidCredentialException
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @throws HttpErrorException
	 * @throws InvalidResponseDataException
	 * @throws ClientActionRequiredException
	 * @throws MissingCredentialException
	 * @throws InterruptedException
	 * @throws OAuthException
	 */
	 public SetAccessPermissionsResponseType setAccessPermissions(SetAccessPermissionsReq setAccessPermissionsReq) throws SSLConfigurationException, InvalidCredentialException, IOException, HttpErrorException, InvalidResponseDataException, ClientActionRequiredException, MissingCredentialException, InterruptedException, OAuthException, ParserConfigurationException, SAXException  {
	 	return setAccessPermissions(setAccessPermissionsReq, (String) null);
	 }

	/** 
	 * 
	 * @throws SSLConfigurationException
	 * @throws InvalidCredentialException
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @throws HttpErrorException
	 * @throws InvalidResponseDataException
	 * @throws ClientActionRequiredException
	 * @throws MissingCredentialException
	 * @throws InterruptedException
	 * @throws OAuthException
	 */
	 public SetAccessPermissionsResponseType setAccessPermissions(SetAccessPermissionsReq setAccessPermissionsReq, ICredential credential) throws SSLConfigurationException, InvalidCredentialException, IOException, HttpErrorException, InvalidResponseDataException, ClientActionRequiredException, MissingCredentialException, InterruptedException, OAuthException, ParserConfigurationException, SAXException  {
		setStandardParams(setAccessPermissionsReq.getSetAccessPermissionsRequest());
		DefaultSOAPAPICallHandler defaultHandler = new DefaultSOAPAPICallHandler(setAccessPermissionsReq.toXMLString(null, "SetAccessPermissionsReq"), null, null, this.configurationMap);
		APICallPreHandler apiCallPreHandler = new MerchantAPICallPreHandler(defaultHandler, credential, SDK_NAME, SDK_VERSION, "PayPalAPIAA", this.configurationMap);
	 	String response = call(apiCallPreHandler);
		InputSource inStream = new InputSource();
		inStream.setCharacterStream(new StringReader((String) response));
		try {
			Node node = (Node) XPathFactory.newInstance().newXPath().evaluate("Envelope/Body/SetAccessPermissionsResponse", DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(inStream), XPathConstants.NODE);
			return new SetAccessPermissionsResponseType(node);
		} catch (XPathExpressionException exe) {
			throw new RuntimeException("Unable to parse response", exe);
		}
	}
	
	/**	
	 * 
	 * @throws SSLConfigurationException
	 * @throws InvalidCredentialException
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @throws HttpErrorException
	 * @throws InvalidResponseDataException
	 * @throws ClientActionRequiredException
	 * @throws MissingCredentialException
	 * @throws InterruptedException
	 * @throws OAuthException
	 */
	 public SetAccessPermissionsResponseType setAccessPermissions(SetAccessPermissionsReq setAccessPermissionsReq, String apiUsername) throws SSLConfigurationException, InvalidCredentialException, IOException, HttpErrorException, InvalidResponseDataException, ClientActionRequiredException, MissingCredentialException, InterruptedException, OAuthException, ParserConfigurationException, SAXException, IOException {
		setStandardParams(setAccessPermissionsReq.getSetAccessPermissionsRequest());
		DefaultSOAPAPICallHandler defaultHandler = new DefaultSOAPAPICallHandler(setAccessPermissionsReq.toXMLString(null, "SetAccessPermissionsReq"), null, null, this.configurationMap);
		APICallPreHandler apiCallPreHandler = new MerchantAPICallPreHandler(defaultHandler, apiUsername, getAccessToken(), getTokenSecret(), SDK_NAME, SDK_VERSION, "PayPalAPIAA", this.configurationMap);
	 	String response = call(apiCallPreHandler);
		InputSource inStream = new InputSource();
		inStream.setCharacterStream(new StringReader((String) response));
		try {
			Node node = (Node) XPathFactory.newInstance().newXPath().evaluate("Envelope/Body/SetAccessPermissionsResponse", DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(inStream), XPathConstants.NODE);
			return new SetAccessPermissionsResponseType(node);
		} catch (XPathExpressionException exe) {
			throw new RuntimeException("Unable to parse response", exe);
		}
	 }

	/** 
	 * 
	 * @throws SSLConfigurationException
	 * @throws InvalidCredentialException
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @throws HttpErrorException
	 * @throws InvalidResponseDataException
	 * @throws ClientActionRequiredException
	 * @throws MissingCredentialException
	 * @throws InterruptedException
	 * @throws OAuthException
	 */
	 public UpdateAccessPermissionsResponseType updateAccessPermissions(UpdateAccessPermissionsReq updateAccessPermissionsReq) throws SSLConfigurationException, InvalidCredentialException, IOException, HttpErrorException, InvalidResponseDataException, ClientActionRequiredException, MissingCredentialException, InterruptedException, OAuthException, ParserConfigurationException, SAXException  {
	 	return updateAccessPermissions(updateAccessPermissionsReq, (String) null);
	 }

	/** 
	 * 
	 * @throws SSLConfigurationException
	 * @throws InvalidCredentialException
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @throws HttpErrorException
	 * @throws InvalidResponseDataException
	 * @throws ClientActionRequiredException
	 * @throws MissingCredentialException
	 * @throws InterruptedException
	 * @throws OAuthException
	 */
	 public UpdateAccessPermissionsResponseType updateAccessPermissions(UpdateAccessPermissionsReq updateAccessPermissionsReq, ICredential credential) throws SSLConfigurationException, InvalidCredentialException, IOException, HttpErrorException, InvalidResponseDataException, ClientActionRequiredException, MissingCredentialException, InterruptedException, OAuthException, ParserConfigurationException, SAXException  {
		setStandardParams(updateAccessPermissionsReq.getUpdateAccessPermissionsRequest());
		DefaultSOAPAPICallHandler defaultHandler = new DefaultSOAPAPICallHandler(updateAccessPermissionsReq.toXMLString(null, "UpdateAccessPermissionsReq"), null, null, this.configurationMap);
		APICallPreHandler apiCallPreHandler = new MerchantAPICallPreHandler(defaultHandler, credential, SDK_NAME, SDK_VERSION, "PayPalAPIAA", this.configurationMap);
	 	String response = call(apiCallPreHandler);
		InputSource inStream = new InputSource();
		inStream.setCharacterStream(new StringReader((String) response));
		try {
			Node node = (Node) XPathFactory.newInstance().newXPath().evaluate("Envelope/Body/UpdateAccessPermissionsResponse", DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(inStream), XPathConstants.NODE);
			return new UpdateAccessPermissionsResponseType(node);
		} catch (XPathExpressionException exe) {
			throw new RuntimeException("Unable to parse response", exe);
		}
	}
	
	/**	
	 * 
	 * @throws SSLConfigurationException
	 * @throws InvalidCredentialException
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @throws HttpErrorException
	 * @throws InvalidResponseDataException
	 * @throws ClientActionRequiredException
	 * @throws MissingCredentialException
	 * @throws InterruptedException
	 * @throws OAuthException
	 */
	 public UpdateAccessPermissionsResponseType updateAccessPermissions(UpdateAccessPermissionsReq updateAccessPermissionsReq, String apiUsername) throws SSLConfigurationException, InvalidCredentialException, IOException, HttpErrorException, InvalidResponseDataException, ClientActionRequiredException, MissingCredentialException, InterruptedException, OAuthException, ParserConfigurationException, SAXException, IOException {
		setStandardParams(updateAccessPermissionsReq.getUpdateAccessPermissionsRequest());
		DefaultSOAPAPICallHandler defaultHandler = new DefaultSOAPAPICallHandler(updateAccessPermissionsReq.toXMLString(null, "UpdateAccessPermissionsReq"), null, null, this.configurationMap);
		APICallPreHandler apiCallPreHandler = new MerchantAPICallPreHandler(defaultHandler, apiUsername, getAccessToken(), getTokenSecret(), SDK_NAME, SDK_VERSION, "PayPalAPIAA", this.configurationMap);
	 	String response = call(apiCallPreHandler);
		InputSource inStream = new InputSource();
		inStream.setCharacterStream(new StringReader((String) response));
		try {
			Node node = (Node) XPathFactory.newInstance().newXPath().evaluate("Envelope/Body/UpdateAccessPermissionsResponse", DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(inStream), XPathConstants.NODE);
			return new UpdateAccessPermissionsResponseType(node);
		} catch (XPathExpressionException exe) {
			throw new RuntimeException("Unable to parse response", exe);
		}
	 }

	/** 
	 * 
	 * @throws SSLConfigurationException
	 * @throws InvalidCredentialException
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @throws HttpErrorException
	 * @throws InvalidResponseDataException
	 * @throws ClientActionRequiredException
	 * @throws MissingCredentialException
	 * @throws InterruptedException
	 * @throws OAuthException
	 */
	 public GetAccessPermissionDetailsResponseType getAccessPermissionDetails(GetAccessPermissionDetailsReq getAccessPermissionDetailsReq) throws SSLConfigurationException, InvalidCredentialException, IOException, HttpErrorException, InvalidResponseDataException, ClientActionRequiredException, MissingCredentialException, InterruptedException, OAuthException, ParserConfigurationException, SAXException  {
	 	return getAccessPermissionDetails(getAccessPermissionDetailsReq, (String) null);
	 }

	/** 
	 * 
	 * @throws SSLConfigurationException
	 * @throws InvalidCredentialException
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @throws HttpErrorException
	 * @throws InvalidResponseDataException
	 * @throws ClientActionRequiredException
	 * @throws MissingCredentialException
	 * @throws InterruptedException
	 * @throws OAuthException
	 */
	 public GetAccessPermissionDetailsResponseType getAccessPermissionDetails(GetAccessPermissionDetailsReq getAccessPermissionDetailsReq, ICredential credential) throws SSLConfigurationException, InvalidCredentialException, IOException, HttpErrorException, InvalidResponseDataException, ClientActionRequiredException, MissingCredentialException, InterruptedException, OAuthException, ParserConfigurationException, SAXException  {
		setStandardParams(getAccessPermissionDetailsReq.getGetAccessPermissionDetailsRequest());
		DefaultSOAPAPICallHandler defaultHandler = new DefaultSOAPAPICallHandler(getAccessPermissionDetailsReq.toXMLString(null, "GetAccessPermissionDetailsReq"), null, null, this.configurationMap);
		APICallPreHandler apiCallPreHandler = new MerchantAPICallPreHandler(defaultHandler, credential, SDK_NAME, SDK_VERSION, "PayPalAPIAA", this.configurationMap);
	 	String response = call(apiCallPreHandler);
		InputSource inStream = new InputSource();
		inStream.setCharacterStream(new StringReader((String) response));
		try {
			Node node = (Node) XPathFactory.newInstance().newXPath().evaluate("Envelope/Body/GetAccessPermissionDetailsResponse", DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(inStream), XPathConstants.NODE);
			return new GetAccessPermissionDetailsResponseType(node);
		} catch (XPathExpressionException exe) {
			throw new RuntimeException("Unable to parse response", exe);
		}
	}
	
	/**	
	 * 
	 * @throws SSLConfigurationException
	 * @throws InvalidCredentialException
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @throws HttpErrorException
	 * @throws InvalidResponseDataException
	 * @throws ClientActionRequiredException
	 * @throws MissingCredentialException
	 * @throws InterruptedException
	 * @throws OAuthException
	 */
	 public GetAccessPermissionDetailsResponseType getAccessPermissionDetails(GetAccessPermissionDetailsReq getAccessPermissionDetailsReq, String apiUsername) throws SSLConfigurationException, InvalidCredentialException, IOException, HttpErrorException, InvalidResponseDataException, ClientActionRequiredException, MissingCredentialException, InterruptedException, OAuthException, ParserConfigurationException, SAXException, IOException {
		setStandardParams(getAccessPermissionDetailsReq.getGetAccessPermissionDetailsRequest());
		DefaultSOAPAPICallHandler defaultHandler = new DefaultSOAPAPICallHandler(getAccessPermissionDetailsReq.toXMLString(null, "GetAccessPermissionDetailsReq"), null, null, this.configurationMap);
		APICallPreHandler apiCallPreHandler = new MerchantAPICallPreHandler(defaultHandler, apiUsername, getAccessToken(), getTokenSecret(), SDK_NAME, SDK_VERSION, "PayPalAPIAA", this.configurationMap);
	 	String response = call(apiCallPreHandler);
		InputSource inStream = new InputSource();
		inStream.setCharacterStream(new StringReader((String) response));
		try {
			Node node = (Node) XPathFactory.newInstance().newXPath().evaluate("Envelope/Body/GetAccessPermissionDetailsResponse", DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(inStream), XPathConstants.NODE);
			return new GetAccessPermissionDetailsResponseType(node);
		} catch (XPathExpressionException exe) {
			throw new RuntimeException("Unable to parse response", exe);
		}
	 }

	/** 
	 * 
	 * @throws SSLConfigurationException
	 * @throws InvalidCredentialException
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @throws HttpErrorException
	 * @throws InvalidResponseDataException
	 * @throws ClientActionRequiredException
	 * @throws MissingCredentialException
	 * @throws InterruptedException
	 * @throws OAuthException
	 */
	 public GetIncentiveEvaluationResponseType getIncentiveEvaluation(GetIncentiveEvaluationReq getIncentiveEvaluationReq) throws SSLConfigurationException, InvalidCredentialException, IOException, HttpErrorException, InvalidResponseDataException, ClientActionRequiredException, MissingCredentialException, InterruptedException, OAuthException, ParserConfigurationException, SAXException  {
	 	return getIncentiveEvaluation(getIncentiveEvaluationReq, (String) null);
	 }

	/** 
	 * 
	 * @throws SSLConfigurationException
	 * @throws InvalidCredentialException
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @throws HttpErrorException
	 * @throws InvalidResponseDataException
	 * @throws ClientActionRequiredException
	 * @throws MissingCredentialException
	 * @throws InterruptedException
	 * @throws OAuthException
	 */
	 public GetIncentiveEvaluationResponseType getIncentiveEvaluation(GetIncentiveEvaluationReq getIncentiveEvaluationReq, ICredential credential) throws SSLConfigurationException, InvalidCredentialException, IOException, HttpErrorException, InvalidResponseDataException, ClientActionRequiredException, MissingCredentialException, InterruptedException, OAuthException, ParserConfigurationException, SAXException  {
		setStandardParams(getIncentiveEvaluationReq.getGetIncentiveEvaluationRequest());
		DefaultSOAPAPICallHandler defaultHandler = new DefaultSOAPAPICallHandler(getIncentiveEvaluationReq.toXMLString(null, "GetIncentiveEvaluationReq"), null, null, this.configurationMap);
		APICallPreHandler apiCallPreHandler = new MerchantAPICallPreHandler(defaultHandler, credential, SDK_NAME, SDK_VERSION, "PayPalAPIAA", this.configurationMap);
	 	String response = call(apiCallPreHandler);
		InputSource inStream = new InputSource();
		inStream.setCharacterStream(new StringReader((String) response));
		try {
			Node node = (Node) XPathFactory.newInstance().newXPath().evaluate("Envelope/Body/GetIncentiveEvaluationResponse", DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(inStream), XPathConstants.NODE);
			return new GetIncentiveEvaluationResponseType(node);
		} catch (XPathExpressionException exe) {
			throw new RuntimeException("Unable to parse response", exe);
		}
	}
	
	/**	
	 * 
	 * @throws SSLConfigurationException
	 * @throws InvalidCredentialException
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @throws HttpErrorException
	 * @throws InvalidResponseDataException
	 * @throws ClientActionRequiredException
	 * @throws MissingCredentialException
	 * @throws InterruptedException
	 * @throws OAuthException
	 */
	 public GetIncentiveEvaluationResponseType getIncentiveEvaluation(GetIncentiveEvaluationReq getIncentiveEvaluationReq, String apiUsername) throws SSLConfigurationException, InvalidCredentialException, IOException, HttpErrorException, InvalidResponseDataException, ClientActionRequiredException, MissingCredentialException, InterruptedException, OAuthException, ParserConfigurationException, SAXException, IOException {
		setStandardParams(getIncentiveEvaluationReq.getGetIncentiveEvaluationRequest());
		DefaultSOAPAPICallHandler defaultHandler = new DefaultSOAPAPICallHandler(getIncentiveEvaluationReq.toXMLString(null, "GetIncentiveEvaluationReq"), null, null, this.configurationMap);
		APICallPreHandler apiCallPreHandler = new MerchantAPICallPreHandler(defaultHandler, apiUsername, getAccessToken(), getTokenSecret(), SDK_NAME, SDK_VERSION, "PayPalAPIAA", this.configurationMap);
	 	String response = call(apiCallPreHandler);
		InputSource inStream = new InputSource();
		inStream.setCharacterStream(new StringReader((String) response));
		try {
			Node node = (Node) XPathFactory.newInstance().newXPath().evaluate("Envelope/Body/GetIncentiveEvaluationResponse", DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(inStream), XPathConstants.NODE);
			return new GetIncentiveEvaluationResponseType(node);
		} catch (XPathExpressionException exe) {
			throw new RuntimeException("Unable to parse response", exe);
		}
	 }

	/** 
	 * 
	 * @throws SSLConfigurationException
	 * @throws InvalidCredentialException
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @throws HttpErrorException
	 * @throws InvalidResponseDataException
	 * @throws ClientActionRequiredException
	 * @throws MissingCredentialException
	 * @throws InterruptedException
	 * @throws OAuthException
	 */
	 public SetExpressCheckoutResponseType setExpressCheckout(SetExpressCheckoutReq setExpressCheckoutReq) throws SSLConfigurationException, InvalidCredentialException, IOException, HttpErrorException, InvalidResponseDataException, ClientActionRequiredException, MissingCredentialException, InterruptedException, OAuthException, ParserConfigurationException, SAXException  {
	 	return setExpressCheckout(setExpressCheckoutReq, (String) null);
	 }

	/** 
	 * 
	 * @throws SSLConfigurationException
	 * @throws InvalidCredentialException
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @throws HttpErrorException
	 * @throws InvalidResponseDataException
	 * @throws ClientActionRequiredException
	 * @throws MissingCredentialException
	 * @throws InterruptedException
	 * @throws OAuthException
	 */
	 public SetExpressCheckoutResponseType setExpressCheckout(SetExpressCheckoutReq setExpressCheckoutReq, ICredential credential) throws SSLConfigurationException, InvalidCredentialException, IOException, HttpErrorException, InvalidResponseDataException, ClientActionRequiredException, MissingCredentialException, InterruptedException, OAuthException, ParserConfigurationException, SAXException  {
		setStandardParams(setExpressCheckoutReq.getSetExpressCheckoutRequest());
		DefaultSOAPAPICallHandler defaultHandler = new DefaultSOAPAPICallHandler(setExpressCheckoutReq.toXMLString(null, "SetExpressCheckoutReq"), null, null, this.configurationMap);
		APICallPreHandler apiCallPreHandler = new MerchantAPICallPreHandler(defaultHandler, credential, SDK_NAME, SDK_VERSION, "PayPalAPIAA", this.configurationMap);
	 	String response = call(apiCallPreHandler);
		InputSource inStream = new InputSource();
		inStream.setCharacterStream(new StringReader((String) response));
		try {
			Node node = (Node) XPathFactory.newInstance().newXPath().evaluate("Envelope/Body/SetExpressCheckoutResponse", DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(inStream), XPathConstants.NODE);
			return new SetExpressCheckoutResponseType(node);
		} catch (XPathExpressionException exe) {
			throw new RuntimeException("Unable to parse response", exe);
		}
	}
	
	/**	
	 * 
	 * @throws SSLConfigurationException
	 * @throws InvalidCredentialException
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @throws HttpErrorException
	 * @throws InvalidResponseDataException
	 * @throws ClientActionRequiredException
	 * @throws MissingCredentialException
	 * @throws InterruptedException
	 * @throws OAuthException
	 */
	 public SetExpressCheckoutResponseType setExpressCheckout(SetExpressCheckoutReq setExpressCheckoutReq, String apiUsername) throws SSLConfigurationException, InvalidCredentialException, IOException, HttpErrorException, InvalidResponseDataException, ClientActionRequiredException, MissingCredentialException, InterruptedException, OAuthException, ParserConfigurationException, SAXException, IOException {
		setStandardParams(setExpressCheckoutReq.getSetExpressCheckoutRequest());
		DefaultSOAPAPICallHandler defaultHandler = new DefaultSOAPAPICallHandler(setExpressCheckoutReq.toXMLString(null, "SetExpressCheckoutReq"), null, null, this.configurationMap);
		APICallPreHandler apiCallPreHandler = new MerchantAPICallPreHandler(defaultHandler, apiUsername, getAccessToken(), getTokenSecret(), SDK_NAME, SDK_VERSION, "PayPalAPIAA", this.configurationMap);
	 	String response = call(apiCallPreHandler);
		InputSource inStream = new InputSource();
		inStream.setCharacterStream(new StringReader((String) response));
		try {
			Node node = (Node) XPathFactory.newInstance().newXPath().evaluate("Envelope/Body/SetExpressCheckoutResponse", DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(inStream), XPathConstants.NODE);
			return new SetExpressCheckoutResponseType(node);
		} catch (XPathExpressionException exe) {
			throw new RuntimeException("Unable to parse response", exe);
		}
	 }

	/** 
	 * 
	 * @throws SSLConfigurationException
	 * @throws InvalidCredentialException
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @throws HttpErrorException
	 * @throws InvalidResponseDataException
	 * @throws ClientActionRequiredException
	 * @throws MissingCredentialException
	 * @throws InterruptedException
	 * @throws OAuthException
	 */
	 public ExecuteCheckoutOperationsResponseType executeCheckoutOperations(ExecuteCheckoutOperationsReq executeCheckoutOperationsReq) throws SSLConfigurationException, InvalidCredentialException, IOException, HttpErrorException, InvalidResponseDataException, ClientActionRequiredException, MissingCredentialException, InterruptedException, OAuthException, ParserConfigurationException, SAXException  {
	 	return executeCheckoutOperations(executeCheckoutOperationsReq, (String) null);
	 }

	/** 
	 * 
	 * @throws SSLConfigurationException
	 * @throws InvalidCredentialException
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @throws HttpErrorException
	 * @throws InvalidResponseDataException
	 * @throws ClientActionRequiredException
	 * @throws MissingCredentialException
	 * @throws InterruptedException
	 * @throws OAuthException
	 */
	 public ExecuteCheckoutOperationsResponseType executeCheckoutOperations(ExecuteCheckoutOperationsReq executeCheckoutOperationsReq, ICredential credential) throws SSLConfigurationException, InvalidCredentialException, IOException, HttpErrorException, InvalidResponseDataException, ClientActionRequiredException, MissingCredentialException, InterruptedException, OAuthException, ParserConfigurationException, SAXException  {
		setStandardParams(executeCheckoutOperationsReq.getExecuteCheckoutOperationsRequest());
		DefaultSOAPAPICallHandler defaultHandler = new DefaultSOAPAPICallHandler(executeCheckoutOperationsReq.toXMLString(null, "ExecuteCheckoutOperationsReq"), null, null, this.configurationMap);
		APICallPreHandler apiCallPreHandler = new MerchantAPICallPreHandler(defaultHandler, credential, SDK_NAME, SDK_VERSION, "PayPalAPIAA", this.configurationMap);
	 	String response = call(apiCallPreHandler);
		InputSource inStream = new InputSource();
		inStream.setCharacterStream(new StringReader((String) response));
		try {
			Node node = (Node) XPathFactory.newInstance().newXPath().evaluate("Envelope/Body/ExecuteCheckoutOperationsResponse", DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(inStream), XPathConstants.NODE);
			return new ExecuteCheckoutOperationsResponseType(node);
		} catch (XPathExpressionException exe) {
			throw new RuntimeException("Unable to parse response", exe);
		}
	}
	
	/**	
	 * 
	 * @throws SSLConfigurationException
	 * @throws InvalidCredentialException
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @throws HttpErrorException
	 * @throws InvalidResponseDataException
	 * @throws ClientActionRequiredException
	 * @throws MissingCredentialException
	 * @throws InterruptedException
	 * @throws OAuthException
	 */
	 public ExecuteCheckoutOperationsResponseType executeCheckoutOperations(ExecuteCheckoutOperationsReq executeCheckoutOperationsReq, String apiUsername) throws SSLConfigurationException, InvalidCredentialException, IOException, HttpErrorException, InvalidResponseDataException, ClientActionRequiredException, MissingCredentialException, InterruptedException, OAuthException, ParserConfigurationException, SAXException, IOException {
		setStandardParams(executeCheckoutOperationsReq.getExecuteCheckoutOperationsRequest());
		DefaultSOAPAPICallHandler defaultHandler = new DefaultSOAPAPICallHandler(executeCheckoutOperationsReq.toXMLString(null, "ExecuteCheckoutOperationsReq"), null, null, this.configurationMap);
		APICallPreHandler apiCallPreHandler = new MerchantAPICallPreHandler(defaultHandler, apiUsername, getAccessToken(), getTokenSecret(), SDK_NAME, SDK_VERSION, "PayPalAPIAA", this.configurationMap);
	 	String response = call(apiCallPreHandler);
		InputSource inStream = new InputSource();
		inStream.setCharacterStream(new StringReader((String) response));
		try {
			Node node = (Node) XPathFactory.newInstance().newXPath().evaluate("Envelope/Body/ExecuteCheckoutOperationsResponse", DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(inStream), XPathConstants.NODE);
			return new ExecuteCheckoutOperationsResponseType(node);
		} catch (XPathExpressionException exe) {
			throw new RuntimeException("Unable to parse response", exe);
		}
	 }

	/** 
	 * 
	 * @throws SSLConfigurationException
	 * @throws InvalidCredentialException
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @throws HttpErrorException
	 * @throws InvalidResponseDataException
	 * @throws ClientActionRequiredException
	 * @throws MissingCredentialException
	 * @throws InterruptedException
	 * @throws OAuthException
	 */
	 public GetExpressCheckoutDetailsResponseType getExpressCheckoutDetails(GetExpressCheckoutDetailsReq getExpressCheckoutDetailsReq) throws SSLConfigurationException, InvalidCredentialException, IOException, HttpErrorException, InvalidResponseDataException, ClientActionRequiredException, MissingCredentialException, InterruptedException, OAuthException, ParserConfigurationException, SAXException  {
	 	return getExpressCheckoutDetails(getExpressCheckoutDetailsReq, (String) null);
	 }

	/** 
	 * 
	 * @throws SSLConfigurationException
	 * @throws InvalidCredentialException
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @throws HttpErrorException
	 * @throws InvalidResponseDataException
	 * @throws ClientActionRequiredException
	 * @throws MissingCredentialException
	 * @throws InterruptedException
	 * @throws OAuthException
	 */
	 public GetExpressCheckoutDetailsResponseType getExpressCheckoutDetails(GetExpressCheckoutDetailsReq getExpressCheckoutDetailsReq, ICredential credential) throws SSLConfigurationException, InvalidCredentialException, IOException, HttpErrorException, InvalidResponseDataException, ClientActionRequiredException, MissingCredentialException, InterruptedException, OAuthException, ParserConfigurationException, SAXException  {
		setStandardParams(getExpressCheckoutDetailsReq.getGetExpressCheckoutDetailsRequest());
		DefaultSOAPAPICallHandler defaultHandler = new DefaultSOAPAPICallHandler(getExpressCheckoutDetailsReq.toXMLString(null, "GetExpressCheckoutDetailsReq"), null, null, this.configurationMap);
		APICallPreHandler apiCallPreHandler = new MerchantAPICallPreHandler(defaultHandler, credential, SDK_NAME, SDK_VERSION, "PayPalAPIAA", this.configurationMap);
	 	String response = call(apiCallPreHandler);
		InputSource inStream = new InputSource();
		inStream.setCharacterStream(new StringReader((String) response));
		try {
			Node node = (Node) XPathFactory.newInstance().newXPath().evaluate("Envelope/Body/GetExpressCheckoutDetailsResponse", DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(inStream), XPathConstants.NODE);
			return new GetExpressCheckoutDetailsResponseType(node);
		} catch (XPathExpressionException exe) {
			throw new RuntimeException("Unable to parse response", exe);
		}
	}
	
	/**	
	 * 
	 * @throws SSLConfigurationException
	 * @throws InvalidCredentialException
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @throws HttpErrorException
	 * @throws InvalidResponseDataException
	 * @throws ClientActionRequiredException
	 * @throws MissingCredentialException
	 * @throws InterruptedException
	 * @throws OAuthException
	 */
	 public GetExpressCheckoutDetailsResponseType getExpressCheckoutDetails(GetExpressCheckoutDetailsReq getExpressCheckoutDetailsReq, String apiUsername) throws SSLConfigurationException, InvalidCredentialException, IOException, HttpErrorException, InvalidResponseDataException, ClientActionRequiredException, MissingCredentialException, InterruptedException, OAuthException, ParserConfigurationException, SAXException, IOException {
		setStandardParams(getExpressCheckoutDetailsReq.getGetExpressCheckoutDetailsRequest());
		DefaultSOAPAPICallHandler defaultHandler = new DefaultSOAPAPICallHandler(getExpressCheckoutDetailsReq.toXMLString(null, "GetExpressCheckoutDetailsReq"), null, null, this.configurationMap);
		APICallPreHandler apiCallPreHandler = new MerchantAPICallPreHandler(defaultHandler, apiUsername, getAccessToken(), getTokenSecret(), SDK_NAME, SDK_VERSION, "PayPalAPIAA", this.configurationMap);
	 	String response = call(apiCallPreHandler);
		InputSource inStream = new InputSource();
		inStream.setCharacterStream(new StringReader((String) response));
		try {
			Node node = (Node) XPathFactory.newInstance().newXPath().evaluate("Envelope/Body/GetExpressCheckoutDetailsResponse", DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(inStream), XPathConstants.NODE);
			return new GetExpressCheckoutDetailsResponseType(node);
		} catch (XPathExpressionException exe) {
			throw new RuntimeException("Unable to parse response", exe);
		}
	 }

	/** 
	 * 
	 * @throws SSLConfigurationException
	 * @throws InvalidCredentialException
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @throws HttpErrorException
	 * @throws InvalidResponseDataException
	 * @throws ClientActionRequiredException
	 * @throws MissingCredentialException
	 * @throws InterruptedException
	 * @throws OAuthException
	 */
	 public DoDirectPaymentResponseType doDirectPayment(DoDirectPaymentReq doDirectPaymentReq) throws SSLConfigurationException, InvalidCredentialException, IOException, HttpErrorException, InvalidResponseDataException, ClientActionRequiredException, MissingCredentialException, InterruptedException, OAuthException, ParserConfigurationException, SAXException  {
	 	return doDirectPayment(doDirectPaymentReq, (String) null);
	 }

	/** 
	 * 
	 * @throws SSLConfigurationException
	 * @throws InvalidCredentialException
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @throws HttpErrorException
	 * @throws InvalidResponseDataException
	 * @throws ClientActionRequiredException
	 * @throws MissingCredentialException
	 * @throws InterruptedException
	 * @throws OAuthException
	 */
	 public DoDirectPaymentResponseType doDirectPayment(DoDirectPaymentReq doDirectPaymentReq, ICredential credential) throws SSLConfigurationException, InvalidCredentialException, IOException, HttpErrorException, InvalidResponseDataException, ClientActionRequiredException, MissingCredentialException, InterruptedException, OAuthException, ParserConfigurationException, SAXException  {
		setStandardParams(doDirectPaymentReq.getDoDirectPaymentRequest());
		DefaultSOAPAPICallHandler defaultHandler = new DefaultSOAPAPICallHandler(doDirectPaymentReq.toXMLString(null, "DoDirectPaymentReq"), null, null, this.configurationMap);
		APICallPreHandler apiCallPreHandler = new MerchantAPICallPreHandler(defaultHandler, credential, SDK_NAME, SDK_VERSION, "PayPalAPIAA", this.configurationMap);
	 	String response = call(apiCallPreHandler);
		InputSource inStream = new InputSource();
		inStream.setCharacterStream(new StringReader((String) response));
		try {
			Node node = (Node) XPathFactory.newInstance().newXPath().evaluate("Envelope/Body/DoDirectPaymentResponse", DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(inStream), XPathConstants.NODE);
			return new DoDirectPaymentResponseType(node);
		} catch (XPathExpressionException exe) {
			throw new RuntimeException("Unable to parse response", exe);
		}
	}
	
	/**	
	 * 
	 * @throws SSLConfigurationException
	 * @throws InvalidCredentialException
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @throws HttpErrorException
	 * @throws InvalidResponseDataException
	 * @throws ClientActionRequiredException
	 * @throws MissingCredentialException
	 * @throws InterruptedException
	 * @throws OAuthException
	 */
	 public DoDirectPaymentResponseType doDirectPayment(DoDirectPaymentReq doDirectPaymentReq, String apiUsername) throws SSLConfigurationException, InvalidCredentialException, IOException, HttpErrorException, InvalidResponseDataException, ClientActionRequiredException, MissingCredentialException, InterruptedException, OAuthException, ParserConfigurationException, SAXException, IOException {
		setStandardParams(doDirectPaymentReq.getDoDirectPaymentRequest());
		DefaultSOAPAPICallHandler defaultHandler = new DefaultSOAPAPICallHandler(doDirectPaymentReq.toXMLString(null, "DoDirectPaymentReq"), null, null, this.configurationMap);
		APICallPreHandler apiCallPreHandler = new MerchantAPICallPreHandler(defaultHandler, apiUsername, getAccessToken(), getTokenSecret(), SDK_NAME, SDK_VERSION, "PayPalAPIAA", this.configurationMap);
	 	String response = call(apiCallPreHandler);
		InputSource inStream = new InputSource();
		inStream.setCharacterStream(new StringReader((String) response));
		try {
			Node node = (Node) XPathFactory.newInstance().newXPath().evaluate("Envelope/Body/DoDirectPaymentResponse", DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(inStream), XPathConstants.NODE);
			return new DoDirectPaymentResponseType(node);
		} catch (XPathExpressionException exe) {
			throw new RuntimeException("Unable to parse response", exe);
		}
	 }

	/** 
	 * 
	 * @throws SSLConfigurationException
	 * @throws InvalidCredentialException
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @throws HttpErrorException
	 * @throws InvalidResponseDataException
	 * @throws ClientActionRequiredException
	 * @throws MissingCredentialException
	 * @throws InterruptedException
	 * @throws OAuthException
	 */
	 public ManagePendingTransactionStatusResponseType managePendingTransactionStatus(ManagePendingTransactionStatusReq managePendingTransactionStatusReq) throws SSLConfigurationException, InvalidCredentialException, IOException, HttpErrorException, InvalidResponseDataException, ClientActionRequiredException, MissingCredentialException, InterruptedException, OAuthException, ParserConfigurationException, SAXException  {
	 	return managePendingTransactionStatus(managePendingTransactionStatusReq, (String) null);
	 }

	/** 
	 * 
	 * @throws SSLConfigurationException
	 * @throws InvalidCredentialException
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @throws HttpErrorException
	 * @throws InvalidResponseDataException
	 * @throws ClientActionRequiredException
	 * @throws MissingCredentialException
	 * @throws InterruptedException
	 * @throws OAuthException
	 */
	 public ManagePendingTransactionStatusResponseType managePendingTransactionStatus(ManagePendingTransactionStatusReq managePendingTransactionStatusReq, ICredential credential) throws SSLConfigurationException, InvalidCredentialException, IOException, HttpErrorException, InvalidResponseDataException, ClientActionRequiredException, MissingCredentialException, InterruptedException, OAuthException, ParserConfigurationException, SAXException  {
		setStandardParams(managePendingTransactionStatusReq.getManagePendingTransactionStatusRequest());
		DefaultSOAPAPICallHandler defaultHandler = new DefaultSOAPAPICallHandler(managePendingTransactionStatusReq.toXMLString(null, "ManagePendingTransactionStatusReq"), null, null, this.configurationMap);
		APICallPreHandler apiCallPreHandler = new MerchantAPICallPreHandler(defaultHandler, credential, SDK_NAME, SDK_VERSION, "PayPalAPIAA", this.configurationMap);
	 	String response = call(apiCallPreHandler);
		InputSource inStream = new InputSource();
		inStream.setCharacterStream(new StringReader((String) response));
		try {
			Node node = (Node) XPathFactory.newInstance().newXPath().evaluate("Envelope/Body/ManagePendingTransactionStatusResponse", DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(inStream), XPathConstants.NODE);
			return new ManagePendingTransactionStatusResponseType(node);
		} catch (XPathExpressionException exe) {
			throw new RuntimeException("Unable to parse response", exe);
		}
	}
	
	/**	
	 * 
	 * @throws SSLConfigurationException
	 * @throws InvalidCredentialException
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @throws HttpErrorException
	 * @throws InvalidResponseDataException
	 * @throws ClientActionRequiredException
	 * @throws MissingCredentialException
	 * @throws InterruptedException
	 * @throws OAuthException
	 */
	 public ManagePendingTransactionStatusResponseType managePendingTransactionStatus(ManagePendingTransactionStatusReq managePendingTransactionStatusReq, String apiUsername) throws SSLConfigurationException, InvalidCredentialException, IOException, HttpErrorException, InvalidResponseDataException, ClientActionRequiredException, MissingCredentialException, InterruptedException, OAuthException, ParserConfigurationException, SAXException, IOException {
		setStandardParams(managePendingTransactionStatusReq.getManagePendingTransactionStatusRequest());
		DefaultSOAPAPICallHandler defaultHandler = new DefaultSOAPAPICallHandler(managePendingTransactionStatusReq.toXMLString(null, "ManagePendingTransactionStatusReq"), null, null, this.configurationMap);
		APICallPreHandler apiCallPreHandler = new MerchantAPICallPreHandler(defaultHandler, apiUsername, getAccessToken(), getTokenSecret(), SDK_NAME, SDK_VERSION, "PayPalAPIAA", this.configurationMap);
	 	String response = call(apiCallPreHandler);
		InputSource inStream = new InputSource();
		inStream.setCharacterStream(new StringReader((String) response));
		try {
			Node node = (Node) XPathFactory.newInstance().newXPath().evaluate("Envelope/Body/ManagePendingTransactionStatusResponse", DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(inStream), XPathConstants.NODE);
			return new ManagePendingTransactionStatusResponseType(node);
		} catch (XPathExpressionException exe) {
			throw new RuntimeException("Unable to parse response", exe);
		}
	 }

	/** 
	 * 
	 * @throws SSLConfigurationException
	 * @throws InvalidCredentialException
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @throws HttpErrorException
	 * @throws InvalidResponseDataException
	 * @throws ClientActionRequiredException
	 * @throws MissingCredentialException
	 * @throws InterruptedException
	 * @throws OAuthException
	 */
	 public DoCancelResponseType doCancel(DoCancelReq doCancelReq) throws SSLConfigurationException, InvalidCredentialException, IOException, HttpErrorException, InvalidResponseDataException, ClientActionRequiredException, MissingCredentialException, InterruptedException, OAuthException, ParserConfigurationException, SAXException  {
	 	return doCancel(doCancelReq, (String) null);
	 }

	/** 
	 * 
	 * @throws SSLConfigurationException
	 * @throws InvalidCredentialException
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @throws HttpErrorException
	 * @throws InvalidResponseDataException
	 * @throws ClientActionRequiredException
	 * @throws MissingCredentialException
	 * @throws InterruptedException
	 * @throws OAuthException
	 */
	 public DoCancelResponseType doCancel(DoCancelReq doCancelReq, ICredential credential) throws SSLConfigurationException, InvalidCredentialException, IOException, HttpErrorException, InvalidResponseDataException, ClientActionRequiredException, MissingCredentialException, InterruptedException, OAuthException, ParserConfigurationException, SAXException  {
		setStandardParams(doCancelReq.getDoCancelRequest());
		DefaultSOAPAPICallHandler defaultHandler = new DefaultSOAPAPICallHandler(doCancelReq.toXMLString(null, "DoCancelReq"), null, null, this.configurationMap);
		APICallPreHandler apiCallPreHandler = new MerchantAPICallPreHandler(defaultHandler, credential, SDK_NAME, SDK_VERSION, "PayPalAPIAA", this.configurationMap);
	 	String response = call(apiCallPreHandler);
		InputSource inStream = new InputSource();
		inStream.setCharacterStream(new StringReader((String) response));
		try {
			Node node = (Node) XPathFactory.newInstance().newXPath().evaluate("Envelope/Body/DoCancelResponse", DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(inStream), XPathConstants.NODE);
			return new DoCancelResponseType(node);
		} catch (XPathExpressionException exe) {
			throw new RuntimeException("Unable to parse response", exe);
		}
	}
	
	/**	
	 * 
	 * @throws SSLConfigurationException
	 * @throws InvalidCredentialException
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @throws HttpErrorException
	 * @throws InvalidResponseDataException
	 * @throws ClientActionRequiredException
	 * @throws MissingCredentialException
	 * @throws InterruptedException
	 * @throws OAuthException
	 */
	 public DoCancelResponseType doCancel(DoCancelReq doCancelReq, String apiUsername) throws SSLConfigurationException, InvalidCredentialException, IOException, HttpErrorException, InvalidResponseDataException, ClientActionRequiredException, MissingCredentialException, InterruptedException, OAuthException, ParserConfigurationException, SAXException, IOException {
		setStandardParams(doCancelReq.getDoCancelRequest());
		DefaultSOAPAPICallHandler defaultHandler = new DefaultSOAPAPICallHandler(doCancelReq.toXMLString(null, "DoCancelReq"), null, null, this.configurationMap);
		APICallPreHandler apiCallPreHandler = new MerchantAPICallPreHandler(defaultHandler, apiUsername, getAccessToken(), getTokenSecret(), SDK_NAME, SDK_VERSION, "PayPalAPIAA", this.configurationMap);
	 	String response = call(apiCallPreHandler);
		InputSource inStream = new InputSource();
		inStream.setCharacterStream(new StringReader((String) response));
		try {
			Node node = (Node) XPathFactory.newInstance().newXPath().evaluate("Envelope/Body/DoCancelResponse", DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(inStream), XPathConstants.NODE);
			return new DoCancelResponseType(node);
		} catch (XPathExpressionException exe) {
			throw new RuntimeException("Unable to parse response", exe);
		}
	 }

	/** 
	 * 
	 * @throws SSLConfigurationException
	 * @throws InvalidCredentialException
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @throws HttpErrorException
	 * @throws InvalidResponseDataException
	 * @throws ClientActionRequiredException
	 * @throws MissingCredentialException
	 * @throws InterruptedException
	 * @throws OAuthException
	 */
	 public DoCaptureResponseType doCapture(DoCaptureReq doCaptureReq) throws SSLConfigurationException, InvalidCredentialException, IOException, HttpErrorException, InvalidResponseDataException, ClientActionRequiredException, MissingCredentialException, InterruptedException, OAuthException, ParserConfigurationException, SAXException  {
	 	return doCapture(doCaptureReq, (String) null);
	 }

	/** 
	 * 
	 * @throws SSLConfigurationException
	 * @throws InvalidCredentialException
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @throws HttpErrorException
	 * @throws InvalidResponseDataException
	 * @throws ClientActionRequiredException
	 * @throws MissingCredentialException
	 * @throws InterruptedException
	 * @throws OAuthException
	 */
	 public DoCaptureResponseType doCapture(DoCaptureReq doCaptureReq, ICredential credential) throws SSLConfigurationException, InvalidCredentialException, IOException, HttpErrorException, InvalidResponseDataException, ClientActionRequiredException, MissingCredentialException, InterruptedException, OAuthException, ParserConfigurationException, SAXException  {
		setStandardParams(doCaptureReq.getDoCaptureRequest());
		DefaultSOAPAPICallHandler defaultHandler = new DefaultSOAPAPICallHandler(doCaptureReq.toXMLString(null, "DoCaptureReq"), null, null, this.configurationMap);
		APICallPreHandler apiCallPreHandler = new MerchantAPICallPreHandler(defaultHandler, credential, SDK_NAME, SDK_VERSION, "PayPalAPIAA", this.configurationMap);
	 	String response = call(apiCallPreHandler);
		InputSource inStream = new InputSource();
		inStream.setCharacterStream(new StringReader((String) response));
		try {
			Node node = (Node) XPathFactory.newInstance().newXPath().evaluate("Envelope/Body/DoCaptureResponse", DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(inStream), XPathConstants.NODE);
			return new DoCaptureResponseType(node);
		} catch (XPathExpressionException exe) {
			throw new RuntimeException("Unable to parse response", exe);
		}
	}
	
	/**	
	 * 
	 * @throws SSLConfigurationException
	 * @throws InvalidCredentialException
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @throws HttpErrorException
	 * @throws InvalidResponseDataException
	 * @throws ClientActionRequiredException
	 * @throws MissingCredentialException
	 * @throws InterruptedException
	 * @throws OAuthException
	 */
	 public DoCaptureResponseType doCapture(DoCaptureReq doCaptureReq, String apiUsername) throws SSLConfigurationException, InvalidCredentialException, IOException, HttpErrorException, InvalidResponseDataException, ClientActionRequiredException, MissingCredentialException, InterruptedException, OAuthException, ParserConfigurationException, SAXException, IOException {
		setStandardParams(doCaptureReq.getDoCaptureRequest());
		DefaultSOAPAPICallHandler defaultHandler = new DefaultSOAPAPICallHandler(doCaptureReq.toXMLString(null, "DoCaptureReq"), null, null, this.configurationMap);
		APICallPreHandler apiCallPreHandler = new MerchantAPICallPreHandler(defaultHandler, apiUsername, getAccessToken(), getTokenSecret(), SDK_NAME, SDK_VERSION, "PayPalAPIAA", this.configurationMap);
	 	String response = call(apiCallPreHandler);
		InputSource inStream = new InputSource();
		inStream.setCharacterStream(new StringReader((String) response));
		try {
			Node node = (Node) XPathFactory.newInstance().newXPath().evaluate("Envelope/Body/DoCaptureResponse", DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(inStream), XPathConstants.NODE);
			return new DoCaptureResponseType(node);
		} catch (XPathExpressionException exe) {
			throw new RuntimeException("Unable to parse response", exe);
		}
	 }

	/** 
	 * 
	 * @throws SSLConfigurationException
	 * @throws InvalidCredentialException
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @throws HttpErrorException
	 * @throws InvalidResponseDataException
	 * @throws ClientActionRequiredException
	 * @throws MissingCredentialException
	 * @throws InterruptedException
	 * @throws OAuthException
	 */
	 public DoReauthorizationResponseType doReauthorization(DoReauthorizationReq doReauthorizationReq) throws SSLConfigurationException, InvalidCredentialException, IOException, HttpErrorException, InvalidResponseDataException, ClientActionRequiredException, MissingCredentialException, InterruptedException, OAuthException, ParserConfigurationException, SAXException  {
	 	return doReauthorization(doReauthorizationReq, (String) null);
	 }

	/** 
	 * 
	 * @throws SSLConfigurationException
	 * @throws InvalidCredentialException
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @throws HttpErrorException
	 * @throws InvalidResponseDataException
	 * @throws ClientActionRequiredException
	 * @throws MissingCredentialException
	 * @throws InterruptedException
	 * @throws OAuthException
	 */
	 public DoReauthorizationResponseType doReauthorization(DoReauthorizationReq doReauthorizationReq, ICredential credential) throws SSLConfigurationException, InvalidCredentialException, IOException, HttpErrorException, InvalidResponseDataException, ClientActionRequiredException, MissingCredentialException, InterruptedException, OAuthException, ParserConfigurationException, SAXException  {
		setStandardParams(doReauthorizationReq.getDoReauthorizationRequest());
		DefaultSOAPAPICallHandler defaultHandler = new DefaultSOAPAPICallHandler(doReauthorizationReq.toXMLString(null, "DoReauthorizationReq"), null, null, this.configurationMap);
		APICallPreHandler apiCallPreHandler = new MerchantAPICallPreHandler(defaultHandler, credential, SDK_NAME, SDK_VERSION, "PayPalAPIAA", this.configurationMap);
	 	String response = call(apiCallPreHandler);
		InputSource inStream = new InputSource();
		inStream.setCharacterStream(new StringReader((String) response));
		try {
			Node node = (Node) XPathFactory.newInstance().newXPath().evaluate("Envelope/Body/DoReauthorizationResponse", DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(inStream), XPathConstants.NODE);
			return new DoReauthorizationResponseType(node);
		} catch (XPathExpressionException exe) {
			throw new RuntimeException("Unable to parse response", exe);
		}
	}
	
	/**	
	 * 
	 * @throws SSLConfigurationException
	 * @throws InvalidCredentialException
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @throws HttpErrorException
	 * @throws InvalidResponseDataException
	 * @throws ClientActionRequiredException
	 * @throws MissingCredentialException
	 * @throws InterruptedException
	 * @throws OAuthException
	 */
	 public DoReauthorizationResponseType doReauthorization(DoReauthorizationReq doReauthorizationReq, String apiUsername) throws SSLConfigurationException, InvalidCredentialException, IOException, HttpErrorException, InvalidResponseDataException, ClientActionRequiredException, MissingCredentialException, InterruptedException, OAuthException, ParserConfigurationException, SAXException, IOException {
		setStandardParams(doReauthorizationReq.getDoReauthorizationRequest());
		DefaultSOAPAPICallHandler defaultHandler = new DefaultSOAPAPICallHandler(doReauthorizationReq.toXMLString(null, "DoReauthorizationReq"), null, null, this.configurationMap);
		APICallPreHandler apiCallPreHandler = new MerchantAPICallPreHandler(defaultHandler, apiUsername, getAccessToken(), getTokenSecret(), SDK_NAME, SDK_VERSION, "PayPalAPIAA", this.configurationMap);
	 	String response = call(apiCallPreHandler);
		InputSource inStream = new InputSource();
		inStream.setCharacterStream(new StringReader((String) response));
		try {
			Node node = (Node) XPathFactory.newInstance().newXPath().evaluate("Envelope/Body/DoReauthorizationResponse", DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(inStream), XPathConstants.NODE);
			return new DoReauthorizationResponseType(node);
		} catch (XPathExpressionException exe) {
			throw new RuntimeException("Unable to parse response", exe);
		}
	 }

	/** 
	 * 
	 * @throws SSLConfigurationException
	 * @throws InvalidCredentialException
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @throws HttpErrorException
	 * @throws InvalidResponseDataException
	 * @throws ClientActionRequiredException
	 * @throws MissingCredentialException
	 * @throws InterruptedException
	 * @throws OAuthException
	 */
	 public DoVoidResponseType doVoid(DoVoidReq doVoidReq) throws SSLConfigurationException, InvalidCredentialException, IOException, HttpErrorException, InvalidResponseDataException, ClientActionRequiredException, MissingCredentialException, InterruptedException, OAuthException, ParserConfigurationException, SAXException  {
	 	return doVoid(doVoidReq, (String) null);
	 }

	/** 
	 * 
	 * @throws SSLConfigurationException
	 * @throws InvalidCredentialException
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @throws HttpErrorException
	 * @throws InvalidResponseDataException
	 * @throws ClientActionRequiredException
	 * @throws MissingCredentialException
	 * @throws InterruptedException
	 * @throws OAuthException
	 */
	 public DoVoidResponseType doVoid(DoVoidReq doVoidReq, ICredential credential) throws SSLConfigurationException, InvalidCredentialException, IOException, HttpErrorException, InvalidResponseDataException, ClientActionRequiredException, MissingCredentialException, InterruptedException, OAuthException, ParserConfigurationException, SAXException  {
		setStandardParams(doVoidReq.getDoVoidRequest());
		DefaultSOAPAPICallHandler defaultHandler = new DefaultSOAPAPICallHandler(doVoidReq.toXMLString(null, "DoVoidReq"), null, null, this.configurationMap);
		APICallPreHandler apiCallPreHandler = new MerchantAPICallPreHandler(defaultHandler, credential, SDK_NAME, SDK_VERSION, "PayPalAPIAA", this.configurationMap);
	 	String response = call(apiCallPreHandler);
		InputSource inStream = new InputSource();
		inStream.setCharacterStream(new StringReader((String) response));
		try {
			Node node = (Node) XPathFactory.newInstance().newXPath().evaluate("Envelope/Body/DoVoidResponse", DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(inStream), XPathConstants.NODE);
			return new DoVoidResponseType(node);
		} catch (XPathExpressionException exe) {
			throw new RuntimeException("Unable to parse response", exe);
		}
	}
	
	/**	
	 * 
	 * @throws SSLConfigurationException
	 * @throws InvalidCredentialException
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @throws HttpErrorException
	 * @throws InvalidResponseDataException
	 * @throws ClientActionRequiredException
	 * @throws MissingCredentialException
	 * @throws InterruptedException
	 * @throws OAuthException
	 */
	 public DoVoidResponseType doVoid(DoVoidReq doVoidReq, String apiUsername) throws SSLConfigurationException, InvalidCredentialException, IOException, HttpErrorException, InvalidResponseDataException, ClientActionRequiredException, MissingCredentialException, InterruptedException, OAuthException, ParserConfigurationException, SAXException, IOException {
		setStandardParams(doVoidReq.getDoVoidRequest());
		DefaultSOAPAPICallHandler defaultHandler = new DefaultSOAPAPICallHandler(doVoidReq.toXMLString(null, "DoVoidReq"), null, null, this.configurationMap);
		APICallPreHandler apiCallPreHandler = new MerchantAPICallPreHandler(defaultHandler, apiUsername, getAccessToken(), getTokenSecret(), SDK_NAME, SDK_VERSION, "PayPalAPIAA", this.configurationMap);
	 	String response = call(apiCallPreHandler);
		InputSource inStream = new InputSource();
		inStream.setCharacterStream(new StringReader((String) response));
		try {
			Node node = (Node) XPathFactory.newInstance().newXPath().evaluate("Envelope/Body/DoVoidResponse", DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(inStream), XPathConstants.NODE);
			return new DoVoidResponseType(node);
		} catch (XPathExpressionException exe) {
			throw new RuntimeException("Unable to parse response", exe);
		}
	 }

	/** 
	 * 
	 * @throws SSLConfigurationException
	 * @throws InvalidCredentialException
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @throws HttpErrorException
	 * @throws InvalidResponseDataException
	 * @throws ClientActionRequiredException
	 * @throws MissingCredentialException
	 * @throws InterruptedException
	 * @throws OAuthException
	 */
	 public DoAuthorizationResponseType doAuthorization(DoAuthorizationReq doAuthorizationReq) throws SSLConfigurationException, InvalidCredentialException, IOException, HttpErrorException, InvalidResponseDataException, ClientActionRequiredException, MissingCredentialException, InterruptedException, OAuthException, ParserConfigurationException, SAXException  {
	 	return doAuthorization(doAuthorizationReq, (String) null);
	 }

	/** 
	 * 
	 * @throws SSLConfigurationException
	 * @throws InvalidCredentialException
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @throws HttpErrorException
	 * @throws InvalidResponseDataException
	 * @throws ClientActionRequiredException
	 * @throws MissingCredentialException
	 * @throws InterruptedException
	 * @throws OAuthException
	 */
	 public DoAuthorizationResponseType doAuthorization(DoAuthorizationReq doAuthorizationReq, ICredential credential) throws SSLConfigurationException, InvalidCredentialException, IOException, HttpErrorException, InvalidResponseDataException, ClientActionRequiredException, MissingCredentialException, InterruptedException, OAuthException, ParserConfigurationException, SAXException  {
		setStandardParams(doAuthorizationReq.getDoAuthorizationRequest());
		DefaultSOAPAPICallHandler defaultHandler = new DefaultSOAPAPICallHandler(doAuthorizationReq.toXMLString(null, "DoAuthorizationReq"), null, null, this.configurationMap);
		APICallPreHandler apiCallPreHandler = new MerchantAPICallPreHandler(defaultHandler, credential, SDK_NAME, SDK_VERSION, "PayPalAPIAA", this.configurationMap);
	 	String response = call(apiCallPreHandler);
		InputSource inStream = new InputSource();
		inStream.setCharacterStream(new StringReader((String) response));
		try {
			Node node = (Node) XPathFactory.newInstance().newXPath().evaluate("Envelope/Body/DoAuthorizationResponse", DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(inStream), XPathConstants.NODE);
			return new DoAuthorizationResponseType(node);
		} catch (XPathExpressionException exe) {
			throw new RuntimeException("Unable to parse response", exe);
		}
	}
	
	/**	
	 * 
	 * @throws SSLConfigurationException
	 * @throws InvalidCredentialException
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @throws HttpErrorException
	 * @throws InvalidResponseDataException
	 * @throws ClientActionRequiredException
	 * @throws MissingCredentialException
	 * @throws InterruptedException
	 * @throws OAuthException
	 */
	 public DoAuthorizationResponseType doAuthorization(DoAuthorizationReq doAuthorizationReq, String apiUsername) throws SSLConfigurationException, InvalidCredentialException, IOException, HttpErrorException, InvalidResponseDataException, ClientActionRequiredException, MissingCredentialException, InterruptedException, OAuthException, ParserConfigurationException, SAXException, IOException {
		setStandardParams(doAuthorizationReq.getDoAuthorizationRequest());
		DefaultSOAPAPICallHandler defaultHandler = new DefaultSOAPAPICallHandler(doAuthorizationReq.toXMLString(null, "DoAuthorizationReq"), null, null, this.configurationMap);
		APICallPreHandler apiCallPreHandler = new MerchantAPICallPreHandler(defaultHandler, apiUsername, getAccessToken(), getTokenSecret(), SDK_NAME, SDK_VERSION, "PayPalAPIAA", this.configurationMap);
	 	String response = call(apiCallPreHandler);
		InputSource inStream = new InputSource();
		inStream.setCharacterStream(new StringReader((String) response));
		try {
			Node node = (Node) XPathFactory.newInstance().newXPath().evaluate("Envelope/Body/DoAuthorizationResponse", DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(inStream), XPathConstants.NODE);
			return new DoAuthorizationResponseType(node);
		} catch (XPathExpressionException exe) {
			throw new RuntimeException("Unable to parse response", exe);
		}
	 }

	/** 
	 * 
	 * @throws SSLConfigurationException
	 * @throws InvalidCredentialException
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @throws HttpErrorException
	 * @throws InvalidResponseDataException
	 * @throws ClientActionRequiredException
	 * @throws MissingCredentialException
	 * @throws InterruptedException
	 * @throws OAuthException
	 */
	 public UpdateAuthorizationResponseType updateAuthorization(UpdateAuthorizationReq updateAuthorizationReq) throws SSLConfigurationException, InvalidCredentialException, IOException, HttpErrorException, InvalidResponseDataException, ClientActionRequiredException, MissingCredentialException, InterruptedException, OAuthException, ParserConfigurationException, SAXException  {
	 	return updateAuthorization(updateAuthorizationReq, (String) null);
	 }

	/** 
	 * 
	 * @throws SSLConfigurationException
	 * @throws InvalidCredentialException
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @throws HttpErrorException
	 * @throws InvalidResponseDataException
	 * @throws ClientActionRequiredException
	 * @throws MissingCredentialException
	 * @throws InterruptedException
	 * @throws OAuthException
	 */
	 public UpdateAuthorizationResponseType updateAuthorization(UpdateAuthorizationReq updateAuthorizationReq, ICredential credential) throws SSLConfigurationException, InvalidCredentialException, IOException, HttpErrorException, InvalidResponseDataException, ClientActionRequiredException, MissingCredentialException, InterruptedException, OAuthException, ParserConfigurationException, SAXException  {
		setStandardParams(updateAuthorizationReq.getUpdateAuthorizationRequest());
		DefaultSOAPAPICallHandler defaultHandler = new DefaultSOAPAPICallHandler(updateAuthorizationReq.toXMLString(null, "UpdateAuthorizationReq"), null, null, this.configurationMap);
		APICallPreHandler apiCallPreHandler = new MerchantAPICallPreHandler(defaultHandler, credential, SDK_NAME, SDK_VERSION, "PayPalAPIAA", this.configurationMap);
	 	String response = call(apiCallPreHandler);
		InputSource inStream = new InputSource();
		inStream.setCharacterStream(new StringReader((String) response));
		try {
			Node node = (Node) XPathFactory.newInstance().newXPath().evaluate("Envelope/Body/UpdateAuthorizationResponse", DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(inStream), XPathConstants.NODE);
			return new UpdateAuthorizationResponseType(node);
		} catch (XPathExpressionException exe) {
			throw new RuntimeException("Unable to parse response", exe);
		}
	}
	
	/**	
	 * 
	 * @throws SSLConfigurationException
	 * @throws InvalidCredentialException
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @throws HttpErrorException
	 * @throws InvalidResponseDataException
	 * @throws ClientActionRequiredException
	 * @throws MissingCredentialException
	 * @throws InterruptedException
	 * @throws OAuthException
	 */
	 public UpdateAuthorizationResponseType updateAuthorization(UpdateAuthorizationReq updateAuthorizationReq, String apiUsername) throws SSLConfigurationException, InvalidCredentialException, IOException, HttpErrorException, InvalidResponseDataException, ClientActionRequiredException, MissingCredentialException, InterruptedException, OAuthException, ParserConfigurationException, SAXException, IOException {
		setStandardParams(updateAuthorizationReq.getUpdateAuthorizationRequest());
		DefaultSOAPAPICallHandler defaultHandler = new DefaultSOAPAPICallHandler(updateAuthorizationReq.toXMLString(null, "UpdateAuthorizationReq"), null, null, this.configurationMap);
		APICallPreHandler apiCallPreHandler = new MerchantAPICallPreHandler(defaultHandler, apiUsername, getAccessToken(), getTokenSecret(), SDK_NAME, SDK_VERSION, "PayPalAPIAA", this.configurationMap);
	 	String response = call(apiCallPreHandler);
		InputSource inStream = new InputSource();
		inStream.setCharacterStream(new StringReader((String) response));
		try {
			Node node = (Node) XPathFactory.newInstance().newXPath().evaluate("Envelope/Body/UpdateAuthorizationResponse", DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(inStream), XPathConstants.NODE);
			return new UpdateAuthorizationResponseType(node);
		} catch (XPathExpressionException exe) {
			throw new RuntimeException("Unable to parse response", exe);
		}
	 }

	/** 
	 * 
	 * @throws SSLConfigurationException
	 * @throws InvalidCredentialException
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @throws HttpErrorException
	 * @throws InvalidResponseDataException
	 * @throws ClientActionRequiredException
	 * @throws MissingCredentialException
	 * @throws InterruptedException
	 * @throws OAuthException
	 */
	 public SetCustomerBillingAgreementResponseType setCustomerBillingAgreement(SetCustomerBillingAgreementReq setCustomerBillingAgreementReq) throws SSLConfigurationException, InvalidCredentialException, IOException, HttpErrorException, InvalidResponseDataException, ClientActionRequiredException, MissingCredentialException, InterruptedException, OAuthException, ParserConfigurationException, SAXException  {
	 	return setCustomerBillingAgreement(setCustomerBillingAgreementReq, (String) null);
	 }

	/** 
	 * 
	 * @throws SSLConfigurationException
	 * @throws InvalidCredentialException
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @throws HttpErrorException
	 * @throws InvalidResponseDataException
	 * @throws ClientActionRequiredException
	 * @throws MissingCredentialException
	 * @throws InterruptedException
	 * @throws OAuthException
	 */
	 public SetCustomerBillingAgreementResponseType setCustomerBillingAgreement(SetCustomerBillingAgreementReq setCustomerBillingAgreementReq, ICredential credential) throws SSLConfigurationException, InvalidCredentialException, IOException, HttpErrorException, InvalidResponseDataException, ClientActionRequiredException, MissingCredentialException, InterruptedException, OAuthException, ParserConfigurationException, SAXException  {
		setStandardParams(setCustomerBillingAgreementReq.getSetCustomerBillingAgreementRequest());
		DefaultSOAPAPICallHandler defaultHandler = new DefaultSOAPAPICallHandler(setCustomerBillingAgreementReq.toXMLString(null, "SetCustomerBillingAgreementReq"), null, null, this.configurationMap);
		APICallPreHandler apiCallPreHandler = new MerchantAPICallPreHandler(defaultHandler, credential, SDK_NAME, SDK_VERSION, "PayPalAPIAA", this.configurationMap);
	 	String response = call(apiCallPreHandler);
		InputSource inStream = new InputSource();
		inStream.setCharacterStream(new StringReader((String) response));
		try {
			Node node = (Node) XPathFactory.newInstance().newXPath().evaluate("Envelope/Body/SetCustomerBillingAgreementResponse", DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(inStream), XPathConstants.NODE);
			return new SetCustomerBillingAgreementResponseType(node);
		} catch (XPathExpressionException exe) {
			throw new RuntimeException("Unable to parse response", exe);
		}
	}
	
	/**	
	 * 
	 * @throws SSLConfigurationException
	 * @throws InvalidCredentialException
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @throws HttpErrorException
	 * @throws InvalidResponseDataException
	 * @throws ClientActionRequiredException
	 * @throws MissingCredentialException
	 * @throws InterruptedException
	 * @throws OAuthException
	 */
	 public SetCustomerBillingAgreementResponseType setCustomerBillingAgreement(SetCustomerBillingAgreementReq setCustomerBillingAgreementReq, String apiUsername) throws SSLConfigurationException, InvalidCredentialException, IOException, HttpErrorException, InvalidResponseDataException, ClientActionRequiredException, MissingCredentialException, InterruptedException, OAuthException, ParserConfigurationException, SAXException, IOException {
		setStandardParams(setCustomerBillingAgreementReq.getSetCustomerBillingAgreementRequest());
		DefaultSOAPAPICallHandler defaultHandler = new DefaultSOAPAPICallHandler(setCustomerBillingAgreementReq.toXMLString(null, "SetCustomerBillingAgreementReq"), null, null, this.configurationMap);
		APICallPreHandler apiCallPreHandler = new MerchantAPICallPreHandler(defaultHandler, apiUsername, getAccessToken(), getTokenSecret(), SDK_NAME, SDK_VERSION, "PayPalAPIAA", this.configurationMap);
	 	String response = call(apiCallPreHandler);
		InputSource inStream = new InputSource();
		inStream.setCharacterStream(new StringReader((String) response));
		try {
			Node node = (Node) XPathFactory.newInstance().newXPath().evaluate("Envelope/Body/SetCustomerBillingAgreementResponse", DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(inStream), XPathConstants.NODE);
			return new SetCustomerBillingAgreementResponseType(node);
		} catch (XPathExpressionException exe) {
			throw new RuntimeException("Unable to parse response", exe);
		}
	 }

	/** 
	 * 
	 * @throws SSLConfigurationException
	 * @throws InvalidCredentialException
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @throws HttpErrorException
	 * @throws InvalidResponseDataException
	 * @throws ClientActionRequiredException
	 * @throws MissingCredentialException
	 * @throws InterruptedException
	 * @throws OAuthException
	 */
	 public GetBillingAgreementCustomerDetailsResponseType getBillingAgreementCustomerDetails(GetBillingAgreementCustomerDetailsReq getBillingAgreementCustomerDetailsReq) throws SSLConfigurationException, InvalidCredentialException, IOException, HttpErrorException, InvalidResponseDataException, ClientActionRequiredException, MissingCredentialException, InterruptedException, OAuthException, ParserConfigurationException, SAXException  {
	 	return getBillingAgreementCustomerDetails(getBillingAgreementCustomerDetailsReq, (String) null);
	 }

	/** 
	 * 
	 * @throws SSLConfigurationException
	 * @throws InvalidCredentialException
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @throws HttpErrorException
	 * @throws InvalidResponseDataException
	 * @throws ClientActionRequiredException
	 * @throws MissingCredentialException
	 * @throws InterruptedException
	 * @throws OAuthException
	 */
	 public GetBillingAgreementCustomerDetailsResponseType getBillingAgreementCustomerDetails(GetBillingAgreementCustomerDetailsReq getBillingAgreementCustomerDetailsReq, ICredential credential) throws SSLConfigurationException, InvalidCredentialException, IOException, HttpErrorException, InvalidResponseDataException, ClientActionRequiredException, MissingCredentialException, InterruptedException, OAuthException, ParserConfigurationException, SAXException  {
		setStandardParams(getBillingAgreementCustomerDetailsReq.getGetBillingAgreementCustomerDetailsRequest());
		DefaultSOAPAPICallHandler defaultHandler = new DefaultSOAPAPICallHandler(getBillingAgreementCustomerDetailsReq.toXMLString(null, "GetBillingAgreementCustomerDetailsReq"), null, null, this.configurationMap);
		APICallPreHandler apiCallPreHandler = new MerchantAPICallPreHandler(defaultHandler, credential, SDK_NAME, SDK_VERSION, "PayPalAPIAA", this.configurationMap);
	 	String response = call(apiCallPreHandler);
		InputSource inStream = new InputSource();
		inStream.setCharacterStream(new StringReader((String) response));
		try {
			Node node = (Node) XPathFactory.newInstance().newXPath().evaluate("Envelope/Body/GetBillingAgreementCustomerDetailsResponse", DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(inStream), XPathConstants.NODE);
			return new GetBillingAgreementCustomerDetailsResponseType(node);
		} catch (XPathExpressionException exe) {
			throw new RuntimeException("Unable to parse response", exe);
		}
	}
	
	/**	
	 * 
	 * @throws SSLConfigurationException
	 * @throws InvalidCredentialException
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @throws HttpErrorException
	 * @throws InvalidResponseDataException
	 * @throws ClientActionRequiredException
	 * @throws MissingCredentialException
	 * @throws InterruptedException
	 * @throws OAuthException
	 */
	 public GetBillingAgreementCustomerDetailsResponseType getBillingAgreementCustomerDetails(GetBillingAgreementCustomerDetailsReq getBillingAgreementCustomerDetailsReq, String apiUsername) throws SSLConfigurationException, InvalidCredentialException, IOException, HttpErrorException, InvalidResponseDataException, ClientActionRequiredException, MissingCredentialException, InterruptedException, OAuthException, ParserConfigurationException, SAXException, IOException {
		setStandardParams(getBillingAgreementCustomerDetailsReq.getGetBillingAgreementCustomerDetailsRequest());
		DefaultSOAPAPICallHandler defaultHandler = new DefaultSOAPAPICallHandler(getBillingAgreementCustomerDetailsReq.toXMLString(null, "GetBillingAgreementCustomerDetailsReq"), null, null, this.configurationMap);
		APICallPreHandler apiCallPreHandler = new MerchantAPICallPreHandler(defaultHandler, apiUsername, getAccessToken(), getTokenSecret(), SDK_NAME, SDK_VERSION, "PayPalAPIAA", this.configurationMap);
	 	String response = call(apiCallPreHandler);
		InputSource inStream = new InputSource();
		inStream.setCharacterStream(new StringReader((String) response));
		try {
			Node node = (Node) XPathFactory.newInstance().newXPath().evaluate("Envelope/Body/GetBillingAgreementCustomerDetailsResponse", DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(inStream), XPathConstants.NODE);
			return new GetBillingAgreementCustomerDetailsResponseType(node);
		} catch (XPathExpressionException exe) {
			throw new RuntimeException("Unable to parse response", exe);
		}
	 }

	/** 
	 * 
	 * @throws SSLConfigurationException
	 * @throws InvalidCredentialException
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @throws HttpErrorException
	 * @throws InvalidResponseDataException
	 * @throws ClientActionRequiredException
	 * @throws MissingCredentialException
	 * @throws InterruptedException
	 * @throws OAuthException
	 */
	 public CreateBillingAgreementResponseType createBillingAgreement(CreateBillingAgreementReq createBillingAgreementReq) throws SSLConfigurationException, InvalidCredentialException, IOException, HttpErrorException, InvalidResponseDataException, ClientActionRequiredException, MissingCredentialException, InterruptedException, OAuthException, ParserConfigurationException, SAXException  {
	 	return createBillingAgreement(createBillingAgreementReq, (String) null);
	 }

	/** 
	 * 
	 * @throws SSLConfigurationException
	 * @throws InvalidCredentialException
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @throws HttpErrorException
	 * @throws InvalidResponseDataException
	 * @throws ClientActionRequiredException
	 * @throws MissingCredentialException
	 * @throws InterruptedException
	 * @throws OAuthException
	 */
	 public CreateBillingAgreementResponseType createBillingAgreement(CreateBillingAgreementReq createBillingAgreementReq, ICredential credential) throws SSLConfigurationException, InvalidCredentialException, IOException, HttpErrorException, InvalidResponseDataException, ClientActionRequiredException, MissingCredentialException, InterruptedException, OAuthException, ParserConfigurationException, SAXException  {
		setStandardParams(createBillingAgreementReq.getCreateBillingAgreementRequest());
		DefaultSOAPAPICallHandler defaultHandler = new DefaultSOAPAPICallHandler(createBillingAgreementReq.toXMLString(null, "CreateBillingAgreementReq"), null, null, this.configurationMap);
		APICallPreHandler apiCallPreHandler = new MerchantAPICallPreHandler(defaultHandler, credential, SDK_NAME, SDK_VERSION, "PayPalAPIAA", this.configurationMap);
	 	String response = call(apiCallPreHandler);
		InputSource inStream = new InputSource();
		inStream.setCharacterStream(new StringReader((String) response));
		try {
			Node node = (Node) XPathFactory.newInstance().newXPath().evaluate("Envelope/Body/CreateBillingAgreementResponse", DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(inStream), XPathConstants.NODE);
			return new CreateBillingAgreementResponseType(node);
		} catch (XPathExpressionException exe) {
			throw new RuntimeException("Unable to parse response", exe);
		}
	}
	
	/**	
	 * 
	 * @throws SSLConfigurationException
	 * @throws InvalidCredentialException
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @throws HttpErrorException
	 * @throws InvalidResponseDataException
	 * @throws ClientActionRequiredException
	 * @throws MissingCredentialException
	 * @throws InterruptedException
	 * @throws OAuthException
	 */
	 public CreateBillingAgreementResponseType createBillingAgreement(CreateBillingAgreementReq createBillingAgreementReq, String apiUsername) throws SSLConfigurationException, InvalidCredentialException, IOException, HttpErrorException, InvalidResponseDataException, ClientActionRequiredException, MissingCredentialException, InterruptedException, OAuthException, ParserConfigurationException, SAXException, IOException {
		setStandardParams(createBillingAgreementReq.getCreateBillingAgreementRequest());
		DefaultSOAPAPICallHandler defaultHandler = new DefaultSOAPAPICallHandler(createBillingAgreementReq.toXMLString(null, "CreateBillingAgreementReq"), null, null, this.configurationMap);
		APICallPreHandler apiCallPreHandler = new MerchantAPICallPreHandler(defaultHandler, apiUsername, getAccessToken(), getTokenSecret(), SDK_NAME, SDK_VERSION, "PayPalAPIAA", this.configurationMap);
	 	String response = call(apiCallPreHandler);
		InputSource inStream = new InputSource();
		inStream.setCharacterStream(new StringReader((String) response));
		try {
			Node node = (Node) XPathFactory.newInstance().newXPath().evaluate("Envelope/Body/CreateBillingAgreementResponse", DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(inStream), XPathConstants.NODE);
			return new CreateBillingAgreementResponseType(node);
		} catch (XPathExpressionException exe) {
			throw new RuntimeException("Unable to parse response", exe);
		}
	 }

	/** 
	 * 
	 * @throws SSLConfigurationException
	 * @throws InvalidCredentialException
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @throws HttpErrorException
	 * @throws InvalidResponseDataException
	 * @throws ClientActionRequiredException
	 * @throws MissingCredentialException
	 * @throws InterruptedException
	 * @throws OAuthException
	 */
	 public DoReferenceTransactionResponseType doReferenceTransaction(DoReferenceTransactionReq doReferenceTransactionReq) throws SSLConfigurationException, InvalidCredentialException, IOException, HttpErrorException, InvalidResponseDataException, ClientActionRequiredException, MissingCredentialException, InterruptedException, OAuthException, ParserConfigurationException, SAXException  {
	 	return doReferenceTransaction(doReferenceTransactionReq, (String) null);
	 }

	/** 
	 * 
	 * @throws SSLConfigurationException
	 * @throws InvalidCredentialException
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @throws HttpErrorException
	 * @throws InvalidResponseDataException
	 * @throws ClientActionRequiredException
	 * @throws MissingCredentialException
	 * @throws InterruptedException
	 * @throws OAuthException
	 */
	 public DoReferenceTransactionResponseType doReferenceTransaction(DoReferenceTransactionReq doReferenceTransactionReq, ICredential credential) throws SSLConfigurationException, InvalidCredentialException, IOException, HttpErrorException, InvalidResponseDataException, ClientActionRequiredException, MissingCredentialException, InterruptedException, OAuthException, ParserConfigurationException, SAXException  {
		setStandardParams(doReferenceTransactionReq.getDoReferenceTransactionRequest());
		DefaultSOAPAPICallHandler defaultHandler = new DefaultSOAPAPICallHandler(doReferenceTransactionReq.toXMLString(null, "DoReferenceTransactionReq"), null, null, this.configurationMap);
		APICallPreHandler apiCallPreHandler = new MerchantAPICallPreHandler(defaultHandler, credential, SDK_NAME, SDK_VERSION, "PayPalAPIAA", this.configurationMap);
	 	String response = call(apiCallPreHandler);
		InputSource inStream = new InputSource();
		inStream.setCharacterStream(new StringReader((String) response));
		try {
			Node node = (Node) XPathFactory.newInstance().newXPath().evaluate("Envelope/Body/DoReferenceTransactionResponse", DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(inStream), XPathConstants.NODE);
			return new DoReferenceTransactionResponseType(node);
		} catch (XPathExpressionException exe) {
			throw new RuntimeException("Unable to parse response", exe);
		}
	}
	
	/**	
	 * 
	 * @throws SSLConfigurationException
	 * @throws InvalidCredentialException
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @throws HttpErrorException
	 * @throws InvalidResponseDataException
	 * @throws ClientActionRequiredException
	 * @throws MissingCredentialException
	 * @throws InterruptedException
	 * @throws OAuthException
	 */
	 public DoReferenceTransactionResponseType doReferenceTransaction(DoReferenceTransactionReq doReferenceTransactionReq, String apiUsername) throws SSLConfigurationException, InvalidCredentialException, IOException, HttpErrorException, InvalidResponseDataException, ClientActionRequiredException, MissingCredentialException, InterruptedException, OAuthException, ParserConfigurationException, SAXException, IOException {
		setStandardParams(doReferenceTransactionReq.getDoReferenceTransactionRequest());
		DefaultSOAPAPICallHandler defaultHandler = new DefaultSOAPAPICallHandler(doReferenceTransactionReq.toXMLString(null, "DoReferenceTransactionReq"), null, null, this.configurationMap);
		APICallPreHandler apiCallPreHandler = new MerchantAPICallPreHandler(defaultHandler, apiUsername, getAccessToken(), getTokenSecret(), SDK_NAME, SDK_VERSION, "PayPalAPIAA", this.configurationMap);
	 	String response = call(apiCallPreHandler);
		InputSource inStream = new InputSource();
		inStream.setCharacterStream(new StringReader((String) response));
		try {
			Node node = (Node) XPathFactory.newInstance().newXPath().evaluate("Envelope/Body/DoReferenceTransactionResponse", DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(inStream), XPathConstants.NODE);
			return new DoReferenceTransactionResponseType(node);
		} catch (XPathExpressionException exe) {
			throw new RuntimeException("Unable to parse response", exe);
		}
	 }

	/** 
	 * 
	 * @throws SSLConfigurationException
	 * @throws InvalidCredentialException
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @throws HttpErrorException
	 * @throws InvalidResponseDataException
	 * @throws ClientActionRequiredException
	 * @throws MissingCredentialException
	 * @throws InterruptedException
	 * @throws OAuthException
	 */
	 public DoNonReferencedCreditResponseType doNonReferencedCredit(DoNonReferencedCreditReq doNonReferencedCreditReq) throws SSLConfigurationException, InvalidCredentialException, IOException, HttpErrorException, InvalidResponseDataException, ClientActionRequiredException, MissingCredentialException, InterruptedException, OAuthException, ParserConfigurationException, SAXException  {
	 	return doNonReferencedCredit(doNonReferencedCreditReq, (String) null);
	 }

	/** 
	 * 
	 * @throws SSLConfigurationException
	 * @throws InvalidCredentialException
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @throws HttpErrorException
	 * @throws InvalidResponseDataException
	 * @throws ClientActionRequiredException
	 * @throws MissingCredentialException
	 * @throws InterruptedException
	 * @throws OAuthException
	 */
	 public DoNonReferencedCreditResponseType doNonReferencedCredit(DoNonReferencedCreditReq doNonReferencedCreditReq, ICredential credential) throws SSLConfigurationException, InvalidCredentialException, IOException, HttpErrorException, InvalidResponseDataException, ClientActionRequiredException, MissingCredentialException, InterruptedException, OAuthException, ParserConfigurationException, SAXException  {
		setStandardParams(doNonReferencedCreditReq.getDoNonReferencedCreditRequest());
		DefaultSOAPAPICallHandler defaultHandler = new DefaultSOAPAPICallHandler(doNonReferencedCreditReq.toXMLString(null, "DoNonReferencedCreditReq"), null, null, this.configurationMap);
		APICallPreHandler apiCallPreHandler = new MerchantAPICallPreHandler(defaultHandler, credential, SDK_NAME, SDK_VERSION, "PayPalAPIAA", this.configurationMap);
	 	String response = call(apiCallPreHandler);
		InputSource inStream = new InputSource();
		inStream.setCharacterStream(new StringReader((String) response));
		try {
			Node node = (Node) XPathFactory.newInstance().newXPath().evaluate("Envelope/Body/DoNonReferencedCreditResponse", DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(inStream), XPathConstants.NODE);
			return new DoNonReferencedCreditResponseType(node);
		} catch (XPathExpressionException exe) {
			throw new RuntimeException("Unable to parse response", exe);
		}
	}
	
	/**	
	 * 
	 * @throws SSLConfigurationException
	 * @throws InvalidCredentialException
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @throws HttpErrorException
	 * @throws InvalidResponseDataException
	 * @throws ClientActionRequiredException
	 * @throws MissingCredentialException
	 * @throws InterruptedException
	 * @throws OAuthException
	 */
	 public DoNonReferencedCreditResponseType doNonReferencedCredit(DoNonReferencedCreditReq doNonReferencedCreditReq, String apiUsername) throws SSLConfigurationException, InvalidCredentialException, IOException, HttpErrorException, InvalidResponseDataException, ClientActionRequiredException, MissingCredentialException, InterruptedException, OAuthException, ParserConfigurationException, SAXException, IOException {
		setStandardParams(doNonReferencedCreditReq.getDoNonReferencedCreditRequest());
		DefaultSOAPAPICallHandler defaultHandler = new DefaultSOAPAPICallHandler(doNonReferencedCreditReq.toXMLString(null, "DoNonReferencedCreditReq"), null, null, this.configurationMap);
		APICallPreHandler apiCallPreHandler = new MerchantAPICallPreHandler(defaultHandler, apiUsername, getAccessToken(), getTokenSecret(), SDK_NAME, SDK_VERSION, "PayPalAPIAA", this.configurationMap);
	 	String response = call(apiCallPreHandler);
		InputSource inStream = new InputSource();
		inStream.setCharacterStream(new StringReader((String) response));
		try {
			Node node = (Node) XPathFactory.newInstance().newXPath().evaluate("Envelope/Body/DoNonReferencedCreditResponse", DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(inStream), XPathConstants.NODE);
			return new DoNonReferencedCreditResponseType(node);
		} catch (XPathExpressionException exe) {
			throw new RuntimeException("Unable to parse response", exe);
		}
	 }

	/** 
	 * 
	 * @throws SSLConfigurationException
	 * @throws InvalidCredentialException
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @throws HttpErrorException
	 * @throws InvalidResponseDataException
	 * @throws ClientActionRequiredException
	 * @throws MissingCredentialException
	 * @throws InterruptedException
	 * @throws OAuthException
	 */
	 public DoUATPAuthorizationResponseType doUATPAuthorization(DoUATPAuthorizationReq doUATPAuthorizationReq) throws SSLConfigurationException, InvalidCredentialException, IOException, HttpErrorException, InvalidResponseDataException, ClientActionRequiredException, MissingCredentialException, InterruptedException, OAuthException, ParserConfigurationException, SAXException  {
	 	return doUATPAuthorization(doUATPAuthorizationReq, (String) null);
	 }

	/** 
	 * 
	 * @throws SSLConfigurationException
	 * @throws InvalidCredentialException
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @throws HttpErrorException
	 * @throws InvalidResponseDataException
	 * @throws ClientActionRequiredException
	 * @throws MissingCredentialException
	 * @throws InterruptedException
	 * @throws OAuthException
	 */
	 public DoUATPAuthorizationResponseType doUATPAuthorization(DoUATPAuthorizationReq doUATPAuthorizationReq, ICredential credential) throws SSLConfigurationException, InvalidCredentialException, IOException, HttpErrorException, InvalidResponseDataException, ClientActionRequiredException, MissingCredentialException, InterruptedException, OAuthException, ParserConfigurationException, SAXException  {
		setStandardParams(doUATPAuthorizationReq.getDoUATPAuthorizationRequest());
		DefaultSOAPAPICallHandler defaultHandler = new DefaultSOAPAPICallHandler(doUATPAuthorizationReq.toXMLString(null, "DoUATPAuthorizationReq"), null, null, this.configurationMap);
		APICallPreHandler apiCallPreHandler = new MerchantAPICallPreHandler(defaultHandler, credential, SDK_NAME, SDK_VERSION, "PayPalAPIAA", this.configurationMap);
	 	String response = call(apiCallPreHandler);
		InputSource inStream = new InputSource();
		inStream.setCharacterStream(new StringReader((String) response));
		try {
			Node node = (Node) XPathFactory.newInstance().newXPath().evaluate("Envelope/Body/DoUATPAuthorizationResponse", DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(inStream), XPathConstants.NODE);
			return new DoUATPAuthorizationResponseType(node);
		} catch (XPathExpressionException exe) {
			throw new RuntimeException("Unable to parse response", exe);
		}
	}
	
	/**	
	 * 
	 * @throws SSLConfigurationException
	 * @throws InvalidCredentialException
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @throws HttpErrorException
	 * @throws InvalidResponseDataException
	 * @throws ClientActionRequiredException
	 * @throws MissingCredentialException
	 * @throws InterruptedException
	 * @throws OAuthException
	 */
	 public DoUATPAuthorizationResponseType doUATPAuthorization(DoUATPAuthorizationReq doUATPAuthorizationReq, String apiUsername) throws SSLConfigurationException, InvalidCredentialException, IOException, HttpErrorException, InvalidResponseDataException, ClientActionRequiredException, MissingCredentialException, InterruptedException, OAuthException, ParserConfigurationException, SAXException, IOException {
		setStandardParams(doUATPAuthorizationReq.getDoUATPAuthorizationRequest());
		DefaultSOAPAPICallHandler defaultHandler = new DefaultSOAPAPICallHandler(doUATPAuthorizationReq.toXMLString(null, "DoUATPAuthorizationReq"), null, null, this.configurationMap);
		APICallPreHandler apiCallPreHandler = new MerchantAPICallPreHandler(defaultHandler, apiUsername, getAccessToken(), getTokenSecret(), SDK_NAME, SDK_VERSION, "PayPalAPIAA", this.configurationMap);
	 	String response = call(apiCallPreHandler);
		InputSource inStream = new InputSource();
		inStream.setCharacterStream(new StringReader((String) response));
		try {
			Node node = (Node) XPathFactory.newInstance().newXPath().evaluate("Envelope/Body/DoUATPAuthorizationResponse", DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(inStream), XPathConstants.NODE);
			return new DoUATPAuthorizationResponseType(node);
		} catch (XPathExpressionException exe) {
			throw new RuntimeException("Unable to parse response", exe);
		}
	 }

	/** 
	 * 
	 * @throws SSLConfigurationException
	 * @throws InvalidCredentialException
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @throws HttpErrorException
	 * @throws InvalidResponseDataException
	 * @throws ClientActionRequiredException
	 * @throws MissingCredentialException
	 * @throws InterruptedException
	 * @throws OAuthException
	 */
	 public CreateRecurringPaymentsProfileResponseType createRecurringPaymentsProfile(CreateRecurringPaymentsProfileReq createRecurringPaymentsProfileReq) throws SSLConfigurationException, InvalidCredentialException, IOException, HttpErrorException, InvalidResponseDataException, ClientActionRequiredException, MissingCredentialException, InterruptedException, OAuthException, ParserConfigurationException, SAXException  {
	 	return createRecurringPaymentsProfile(createRecurringPaymentsProfileReq, (String) null);
	 }

	/** 
	 * 
	 * @throws SSLConfigurationException
	 * @throws InvalidCredentialException
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @throws HttpErrorException
	 * @throws InvalidResponseDataException
	 * @throws ClientActionRequiredException
	 * @throws MissingCredentialException
	 * @throws InterruptedException
	 * @throws OAuthException
	 */
	 public CreateRecurringPaymentsProfileResponseType createRecurringPaymentsProfile(CreateRecurringPaymentsProfileReq createRecurringPaymentsProfileReq, ICredential credential) throws SSLConfigurationException, InvalidCredentialException, IOException, HttpErrorException, InvalidResponseDataException, ClientActionRequiredException, MissingCredentialException, InterruptedException, OAuthException, ParserConfigurationException, SAXException  {
		setStandardParams(createRecurringPaymentsProfileReq.getCreateRecurringPaymentsProfileRequest());
		DefaultSOAPAPICallHandler defaultHandler = new DefaultSOAPAPICallHandler(createRecurringPaymentsProfileReq.toXMLString(null, "CreateRecurringPaymentsProfileReq"), null, null, this.configurationMap);
		APICallPreHandler apiCallPreHandler = new MerchantAPICallPreHandler(defaultHandler, credential, SDK_NAME, SDK_VERSION, "PayPalAPIAA", this.configurationMap);
	 	String response = call(apiCallPreHandler);
		InputSource inStream = new InputSource();
		inStream.setCharacterStream(new StringReader((String) response));
		try {
			Node node = (Node) XPathFactory.newInstance().newXPath().evaluate("Envelope/Body/CreateRecurringPaymentsProfileResponse", DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(inStream), XPathConstants.NODE);
			return new CreateRecurringPaymentsProfileResponseType(node);
		} catch (XPathExpressionException exe) {
			throw new RuntimeException("Unable to parse response", exe);
		}
	}
	
	/**	
	 * 
	 * @throws SSLConfigurationException
	 * @throws InvalidCredentialException
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @throws HttpErrorException
	 * @throws InvalidResponseDataException
	 * @throws ClientActionRequiredException
	 * @throws MissingCredentialException
	 * @throws InterruptedException
	 * @throws OAuthException
	 */
	 public CreateRecurringPaymentsProfileResponseType createRecurringPaymentsProfile(CreateRecurringPaymentsProfileReq createRecurringPaymentsProfileReq, String apiUsername) throws SSLConfigurationException, InvalidCredentialException, IOException, HttpErrorException, InvalidResponseDataException, ClientActionRequiredException, MissingCredentialException, InterruptedException, OAuthException, ParserConfigurationException, SAXException, IOException {
		setStandardParams(createRecurringPaymentsProfileReq.getCreateRecurringPaymentsProfileRequest());
		DefaultSOAPAPICallHandler defaultHandler = new DefaultSOAPAPICallHandler(createRecurringPaymentsProfileReq.toXMLString(null, "CreateRecurringPaymentsProfileReq"), null, null, this.configurationMap);
		APICallPreHandler apiCallPreHandler = new MerchantAPICallPreHandler(defaultHandler, apiUsername, getAccessToken(), getTokenSecret(), SDK_NAME, SDK_VERSION, "PayPalAPIAA", this.configurationMap);
	 	String response = call(apiCallPreHandler);
		InputSource inStream = new InputSource();
		inStream.setCharacterStream(new StringReader((String) response));
		try {
			Node node = (Node) XPathFactory.newInstance().newXPath().evaluate("Envelope/Body/CreateRecurringPaymentsProfileResponse", DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(inStream), XPathConstants.NODE);
			return new CreateRecurringPaymentsProfileResponseType(node);
		} catch (XPathExpressionException exe) {
			throw new RuntimeException("Unable to parse response", exe);
		}
	 }

	/** 
	 * 
	 * @throws SSLConfigurationException
	 * @throws InvalidCredentialException
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @throws HttpErrorException
	 * @throws InvalidResponseDataException
	 * @throws ClientActionRequiredException
	 * @throws MissingCredentialException
	 * @throws InterruptedException
	 * @throws OAuthException
	 */
	 public GetRecurringPaymentsProfileDetailsResponseType getRecurringPaymentsProfileDetails(GetRecurringPaymentsProfileDetailsReq getRecurringPaymentsProfileDetailsReq) throws SSLConfigurationException, InvalidCredentialException, IOException, HttpErrorException, InvalidResponseDataException, ClientActionRequiredException, MissingCredentialException, InterruptedException, OAuthException, ParserConfigurationException, SAXException  {
	 	return getRecurringPaymentsProfileDetails(getRecurringPaymentsProfileDetailsReq, (String) null);
	 }

	/** 
	 * 
	 * @throws SSLConfigurationException
	 * @throws InvalidCredentialException
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @throws HttpErrorException
	 * @throws InvalidResponseDataException
	 * @throws ClientActionRequiredException
	 * @throws MissingCredentialException
	 * @throws InterruptedException
	 * @throws OAuthException
	 */
	 public GetRecurringPaymentsProfileDetailsResponseType getRecurringPaymentsProfileDetails(GetRecurringPaymentsProfileDetailsReq getRecurringPaymentsProfileDetailsReq, ICredential credential) throws SSLConfigurationException, InvalidCredentialException, IOException, HttpErrorException, InvalidResponseDataException, ClientActionRequiredException, MissingCredentialException, InterruptedException, OAuthException, ParserConfigurationException, SAXException  {
		setStandardParams(getRecurringPaymentsProfileDetailsReq.getGetRecurringPaymentsProfileDetailsRequest());
		DefaultSOAPAPICallHandler defaultHandler = new DefaultSOAPAPICallHandler(getRecurringPaymentsProfileDetailsReq.toXMLString(null, "GetRecurringPaymentsProfileDetailsReq"), null, null, this.configurationMap);
		APICallPreHandler apiCallPreHandler = new MerchantAPICallPreHandler(defaultHandler, credential, SDK_NAME, SDK_VERSION, "PayPalAPIAA", this.configurationMap);
	 	String response = call(apiCallPreHandler);
		InputSource inStream = new InputSource();
		inStream.setCharacterStream(new StringReader((String) response));
		try {
			Node node = (Node) XPathFactory.newInstance().newXPath().evaluate("Envelope/Body/GetRecurringPaymentsProfileDetailsResponse", DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(inStream), XPathConstants.NODE);
			return new GetRecurringPaymentsProfileDetailsResponseType(node);
		} catch (XPathExpressionException exe) {
			throw new RuntimeException("Unable to parse response", exe);
		}
	}
	
	/**	
	 * 
	 * @throws SSLConfigurationException
	 * @throws InvalidCredentialException
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @throws HttpErrorException
	 * @throws InvalidResponseDataException
	 * @throws ClientActionRequiredException
	 * @throws MissingCredentialException
	 * @throws InterruptedException
	 * @throws OAuthException
	 */
	 public GetRecurringPaymentsProfileDetailsResponseType getRecurringPaymentsProfileDetails(GetRecurringPaymentsProfileDetailsReq getRecurringPaymentsProfileDetailsReq, String apiUsername) throws SSLConfigurationException, InvalidCredentialException, IOException, HttpErrorException, InvalidResponseDataException, ClientActionRequiredException, MissingCredentialException, InterruptedException, OAuthException, ParserConfigurationException, SAXException, IOException {
		setStandardParams(getRecurringPaymentsProfileDetailsReq.getGetRecurringPaymentsProfileDetailsRequest());
		DefaultSOAPAPICallHandler defaultHandler = new DefaultSOAPAPICallHandler(getRecurringPaymentsProfileDetailsReq.toXMLString(null, "GetRecurringPaymentsProfileDetailsReq"), null, null, this.configurationMap);
		APICallPreHandler apiCallPreHandler = new MerchantAPICallPreHandler(defaultHandler, apiUsername, getAccessToken(), getTokenSecret(), SDK_NAME, SDK_VERSION, "PayPalAPIAA", this.configurationMap);
	 	String response = call(apiCallPreHandler);
		InputSource inStream = new InputSource();
		inStream.setCharacterStream(new StringReader((String) response));
		try {
			Node node = (Node) XPathFactory.newInstance().newXPath().evaluate("Envelope/Body/GetRecurringPaymentsProfileDetailsResponse", DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(inStream), XPathConstants.NODE);
			return new GetRecurringPaymentsProfileDetailsResponseType(node);
		} catch (XPathExpressionException exe) {
			throw new RuntimeException("Unable to parse response", exe);
		}
	 }

	/** 
	 * 
	 * @throws SSLConfigurationException
	 * @throws InvalidCredentialException
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @throws HttpErrorException
	 * @throws InvalidResponseDataException
	 * @throws ClientActionRequiredException
	 * @throws MissingCredentialException
	 * @throws InterruptedException
	 * @throws OAuthException
	 */
	 public ManageRecurringPaymentsProfileStatusResponseType manageRecurringPaymentsProfileStatus(ManageRecurringPaymentsProfileStatusReq manageRecurringPaymentsProfileStatusReq) throws SSLConfigurationException, InvalidCredentialException, IOException, HttpErrorException, InvalidResponseDataException, ClientActionRequiredException, MissingCredentialException, InterruptedException, OAuthException, ParserConfigurationException, SAXException  {
	 	return manageRecurringPaymentsProfileStatus(manageRecurringPaymentsProfileStatusReq, (String) null);
	 }

	/** 
	 * 
	 * @throws SSLConfigurationException
	 * @throws InvalidCredentialException
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @throws HttpErrorException
	 * @throws InvalidResponseDataException
	 * @throws ClientActionRequiredException
	 * @throws MissingCredentialException
	 * @throws InterruptedException
	 * @throws OAuthException
	 */
	 public ManageRecurringPaymentsProfileStatusResponseType manageRecurringPaymentsProfileStatus(ManageRecurringPaymentsProfileStatusReq manageRecurringPaymentsProfileStatusReq, ICredential credential) throws SSLConfigurationException, InvalidCredentialException, IOException, HttpErrorException, InvalidResponseDataException, ClientActionRequiredException, MissingCredentialException, InterruptedException, OAuthException, ParserConfigurationException, SAXException  {
		setStandardParams(manageRecurringPaymentsProfileStatusReq.getManageRecurringPaymentsProfileStatusRequest());
		DefaultSOAPAPICallHandler defaultHandler = new DefaultSOAPAPICallHandler(manageRecurringPaymentsProfileStatusReq.toXMLString(null, "ManageRecurringPaymentsProfileStatusReq"), null, null, this.configurationMap);
		APICallPreHandler apiCallPreHandler = new MerchantAPICallPreHandler(defaultHandler, credential, SDK_NAME, SDK_VERSION, "PayPalAPIAA", this.configurationMap);
	 	String response = call(apiCallPreHandler);
		InputSource inStream = new InputSource();
		inStream.setCharacterStream(new StringReader((String) response));
		try {
			Node node = (Node) XPathFactory.newInstance().newXPath().evaluate("Envelope/Body/ManageRecurringPaymentsProfileStatusResponse", DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(inStream), XPathConstants.NODE);
			return new ManageRecurringPaymentsProfileStatusResponseType(node);
		} catch (XPathExpressionException exe) {
			throw new RuntimeException("Unable to parse response", exe);
		}
	}
	
	/**	
	 * 
	 * @throws SSLConfigurationException
	 * @throws InvalidCredentialException
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @throws HttpErrorException
	 * @throws InvalidResponseDataException
	 * @throws ClientActionRequiredException
	 * @throws MissingCredentialException
	 * @throws InterruptedException
	 * @throws OAuthException
	 */
	 public ManageRecurringPaymentsProfileStatusResponseType manageRecurringPaymentsProfileStatus(ManageRecurringPaymentsProfileStatusReq manageRecurringPaymentsProfileStatusReq, String apiUsername) throws SSLConfigurationException, InvalidCredentialException, IOException, HttpErrorException, InvalidResponseDataException, ClientActionRequiredException, MissingCredentialException, InterruptedException, OAuthException, ParserConfigurationException, SAXException, IOException {
		setStandardParams(manageRecurringPaymentsProfileStatusReq.getManageRecurringPaymentsProfileStatusRequest());
		DefaultSOAPAPICallHandler defaultHandler = new DefaultSOAPAPICallHandler(manageRecurringPaymentsProfileStatusReq.toXMLString(null, "ManageRecurringPaymentsProfileStatusReq"), null, null, this.configurationMap);
		APICallPreHandler apiCallPreHandler = new MerchantAPICallPreHandler(defaultHandler, apiUsername, getAccessToken(), getTokenSecret(), SDK_NAME, SDK_VERSION, "PayPalAPIAA", this.configurationMap);
	 	String response = call(apiCallPreHandler);
		InputSource inStream = new InputSource();
		inStream.setCharacterStream(new StringReader((String) response));
		try {
			Node node = (Node) XPathFactory.newInstance().newXPath().evaluate("Envelope/Body/ManageRecurringPaymentsProfileStatusResponse", DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(inStream), XPathConstants.NODE);
			return new ManageRecurringPaymentsProfileStatusResponseType(node);
		} catch (XPathExpressionException exe) {
			throw new RuntimeException("Unable to parse response", exe);
		}
	 }

	/** 
	 * 
	 * @throws SSLConfigurationException
	 * @throws InvalidCredentialException
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @throws HttpErrorException
	 * @throws InvalidResponseDataException
	 * @throws ClientActionRequiredException
	 * @throws MissingCredentialException
	 * @throws InterruptedException
	 * @throws OAuthException
	 */
	 public BillOutstandingAmountResponseType billOutstandingAmount(BillOutstandingAmountReq billOutstandingAmountReq) throws SSLConfigurationException, InvalidCredentialException, IOException, HttpErrorException, InvalidResponseDataException, ClientActionRequiredException, MissingCredentialException, InterruptedException, OAuthException, ParserConfigurationException, SAXException  {
	 	return billOutstandingAmount(billOutstandingAmountReq, (String) null);
	 }

	/** 
	 * 
	 * @throws SSLConfigurationException
	 * @throws InvalidCredentialException
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @throws HttpErrorException
	 * @throws InvalidResponseDataException
	 * @throws ClientActionRequiredException
	 * @throws MissingCredentialException
	 * @throws InterruptedException
	 * @throws OAuthException
	 */
	 public BillOutstandingAmountResponseType billOutstandingAmount(BillOutstandingAmountReq billOutstandingAmountReq, ICredential credential) throws SSLConfigurationException, InvalidCredentialException, IOException, HttpErrorException, InvalidResponseDataException, ClientActionRequiredException, MissingCredentialException, InterruptedException, OAuthException, ParserConfigurationException, SAXException  {
		setStandardParams(billOutstandingAmountReq.getBillOutstandingAmountRequest());
		DefaultSOAPAPICallHandler defaultHandler = new DefaultSOAPAPICallHandler(billOutstandingAmountReq.toXMLString(null, "BillOutstandingAmountReq"), null, null, this.configurationMap);
		APICallPreHandler apiCallPreHandler = new MerchantAPICallPreHandler(defaultHandler, credential, SDK_NAME, SDK_VERSION, "PayPalAPIAA", this.configurationMap);
	 	String response = call(apiCallPreHandler);
		InputSource inStream = new InputSource();
		inStream.setCharacterStream(new StringReader((String) response));
		try {
			Node node = (Node) XPathFactory.newInstance().newXPath().evaluate("Envelope/Body/BillOutstandingAmountResponse", DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(inStream), XPathConstants.NODE);
			return new BillOutstandingAmountResponseType(node);
		} catch (XPathExpressionException exe) {
			throw new RuntimeException("Unable to parse response", exe);
		}
	}
	
	/**	
	 * 
	 * @throws SSLConfigurationException
	 * @throws InvalidCredentialException
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @throws HttpErrorException
	 * @throws InvalidResponseDataException
	 * @throws ClientActionRequiredException
	 * @throws MissingCredentialException
	 * @throws InterruptedException
	 * @throws OAuthException
	 */
	 public BillOutstandingAmountResponseType billOutstandingAmount(BillOutstandingAmountReq billOutstandingAmountReq, String apiUsername) throws SSLConfigurationException, InvalidCredentialException, IOException, HttpErrorException, InvalidResponseDataException, ClientActionRequiredException, MissingCredentialException, InterruptedException, OAuthException, ParserConfigurationException, SAXException, IOException {
		setStandardParams(billOutstandingAmountReq.getBillOutstandingAmountRequest());
		DefaultSOAPAPICallHandler defaultHandler = new DefaultSOAPAPICallHandler(billOutstandingAmountReq.toXMLString(null, "BillOutstandingAmountReq"), null, null, this.configurationMap);
		APICallPreHandler apiCallPreHandler = new MerchantAPICallPreHandler(defaultHandler, apiUsername, getAccessToken(), getTokenSecret(), SDK_NAME, SDK_VERSION, "PayPalAPIAA", this.configurationMap);
	 	String response = call(apiCallPreHandler);
		InputSource inStream = new InputSource();
		inStream.setCharacterStream(new StringReader((String) response));
		try {
			Node node = (Node) XPathFactory.newInstance().newXPath().evaluate("Envelope/Body/BillOutstandingAmountResponse", DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(inStream), XPathConstants.NODE);
			return new BillOutstandingAmountResponseType(node);
		} catch (XPathExpressionException exe) {
			throw new RuntimeException("Unable to parse response", exe);
		}
	 }

	/** 
	 * 
	 * @throws SSLConfigurationException
	 * @throws InvalidCredentialException
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @throws HttpErrorException
	 * @throws InvalidResponseDataException
	 * @throws ClientActionRequiredException
	 * @throws MissingCredentialException
	 * @throws InterruptedException
	 * @throws OAuthException
	 */
	 public UpdateRecurringPaymentsProfileResponseType updateRecurringPaymentsProfile(UpdateRecurringPaymentsProfileReq updateRecurringPaymentsProfileReq) throws SSLConfigurationException, InvalidCredentialException, IOException, HttpErrorException, InvalidResponseDataException, ClientActionRequiredException, MissingCredentialException, InterruptedException, OAuthException, ParserConfigurationException, SAXException  {
	 	return updateRecurringPaymentsProfile(updateRecurringPaymentsProfileReq, (String) null);
	 }

	/** 
	 * 
	 * @throws SSLConfigurationException
	 * @throws InvalidCredentialException
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @throws HttpErrorException
	 * @throws InvalidResponseDataException
	 * @throws ClientActionRequiredException
	 * @throws MissingCredentialException
	 * @throws InterruptedException
	 * @throws OAuthException
	 */
	 public UpdateRecurringPaymentsProfileResponseType updateRecurringPaymentsProfile(UpdateRecurringPaymentsProfileReq updateRecurringPaymentsProfileReq, ICredential credential) throws SSLConfigurationException, InvalidCredentialException, IOException, HttpErrorException, InvalidResponseDataException, ClientActionRequiredException, MissingCredentialException, InterruptedException, OAuthException, ParserConfigurationException, SAXException  {
		setStandardParams(updateRecurringPaymentsProfileReq.getUpdateRecurringPaymentsProfileRequest());
		DefaultSOAPAPICallHandler defaultHandler = new DefaultSOAPAPICallHandler(updateRecurringPaymentsProfileReq.toXMLString(null, "UpdateRecurringPaymentsProfileReq"), null, null, this.configurationMap);
		APICallPreHandler apiCallPreHandler = new MerchantAPICallPreHandler(defaultHandler, credential, SDK_NAME, SDK_VERSION, "PayPalAPIAA", this.configurationMap);
	 	String response = call(apiCallPreHandler);
		InputSource inStream = new InputSource();
		inStream.setCharacterStream(new StringReader((String) response));
		try {
			Node node = (Node) XPathFactory.newInstance().newXPath().evaluate("Envelope/Body/UpdateRecurringPaymentsProfileResponse", DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(inStream), XPathConstants.NODE);
			return new UpdateRecurringPaymentsProfileResponseType(node);
		} catch (XPathExpressionException exe) {
			throw new RuntimeException("Unable to parse response", exe);
		}
	}
	
	/**	
	 * 
	 * @throws SSLConfigurationException
	 * @throws InvalidCredentialException
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @throws HttpErrorException
	 * @throws InvalidResponseDataException
	 * @throws ClientActionRequiredException
	 * @throws MissingCredentialException
	 * @throws InterruptedException
	 * @throws OAuthException
	 */
	 public UpdateRecurringPaymentsProfileResponseType updateRecurringPaymentsProfile(UpdateRecurringPaymentsProfileReq updateRecurringPaymentsProfileReq, String apiUsername) throws SSLConfigurationException, InvalidCredentialException, IOException, HttpErrorException, InvalidResponseDataException, ClientActionRequiredException, MissingCredentialException, InterruptedException, OAuthException, ParserConfigurationException, SAXException, IOException {
		setStandardParams(updateRecurringPaymentsProfileReq.getUpdateRecurringPaymentsProfileRequest());
		DefaultSOAPAPICallHandler defaultHandler = new DefaultSOAPAPICallHandler(updateRecurringPaymentsProfileReq.toXMLString(null, "UpdateRecurringPaymentsProfileReq"), null, null, this.configurationMap);
		APICallPreHandler apiCallPreHandler = new MerchantAPICallPreHandler(defaultHandler, apiUsername, getAccessToken(), getTokenSecret(), SDK_NAME, SDK_VERSION, "PayPalAPIAA", this.configurationMap);
	 	String response = call(apiCallPreHandler);
		InputSource inStream = new InputSource();
		inStream.setCharacterStream(new StringReader((String) response));
		try {
			Node node = (Node) XPathFactory.newInstance().newXPath().evaluate("Envelope/Body/UpdateRecurringPaymentsProfileResponse", DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(inStream), XPathConstants.NODE);
			return new UpdateRecurringPaymentsProfileResponseType(node);
		} catch (XPathExpressionException exe) {
			throw new RuntimeException("Unable to parse response", exe);
		}
	 }

	/** 
	 * 
	 * @throws SSLConfigurationException
	 * @throws InvalidCredentialException
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @throws HttpErrorException
	 * @throws InvalidResponseDataException
	 * @throws ClientActionRequiredException
	 * @throws MissingCredentialException
	 * @throws InterruptedException
	 * @throws OAuthException
	 */
	 public ReverseTransactionResponseType reverseTransaction(ReverseTransactionReq reverseTransactionReq) throws SSLConfigurationException, InvalidCredentialException, IOException, HttpErrorException, InvalidResponseDataException, ClientActionRequiredException, MissingCredentialException, InterruptedException, OAuthException, ParserConfigurationException, SAXException  {
	 	return reverseTransaction(reverseTransactionReq, (String) null);
	 }

	/** 
	 * 
	 * @throws SSLConfigurationException
	 * @throws InvalidCredentialException
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @throws HttpErrorException
	 * @throws InvalidResponseDataException
	 * @throws ClientActionRequiredException
	 * @throws MissingCredentialException
	 * @throws InterruptedException
	 * @throws OAuthException
	 */
	 public ReverseTransactionResponseType reverseTransaction(ReverseTransactionReq reverseTransactionReq, ICredential credential) throws SSLConfigurationException, InvalidCredentialException, IOException, HttpErrorException, InvalidResponseDataException, ClientActionRequiredException, MissingCredentialException, InterruptedException, OAuthException, ParserConfigurationException, SAXException  {
		setStandardParams(reverseTransactionReq.getReverseTransactionRequest());
		DefaultSOAPAPICallHandler defaultHandler = new DefaultSOAPAPICallHandler(reverseTransactionReq.toXMLString(null, "ReverseTransactionReq"), null, null, this.configurationMap);
		APICallPreHandler apiCallPreHandler = new MerchantAPICallPreHandler(defaultHandler, credential, SDK_NAME, SDK_VERSION, "PayPalAPIAA", this.configurationMap);
	 	String response = call(apiCallPreHandler);
		InputSource inStream = new InputSource();
		inStream.setCharacterStream(new StringReader((String) response));
		try {
			Node node = (Node) XPathFactory.newInstance().newXPath().evaluate("Envelope/Body/ReverseTransactionResponse", DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(inStream), XPathConstants.NODE);
			return new ReverseTransactionResponseType(node);
		} catch (XPathExpressionException exe) {
			throw new RuntimeException("Unable to parse response", exe);
		}
	}
	
	/**	
	 * 
	 * @throws SSLConfigurationException
	 * @throws InvalidCredentialException
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @throws HttpErrorException
	 * @throws InvalidResponseDataException
	 * @throws ClientActionRequiredException
	 * @throws MissingCredentialException
	 * @throws InterruptedException
	 * @throws OAuthException
	 */
	 public ReverseTransactionResponseType reverseTransaction(ReverseTransactionReq reverseTransactionReq, String apiUsername) throws SSLConfigurationException, InvalidCredentialException, IOException, HttpErrorException, InvalidResponseDataException, ClientActionRequiredException, MissingCredentialException, InterruptedException, OAuthException, ParserConfigurationException, SAXException, IOException {
		setStandardParams(reverseTransactionReq.getReverseTransactionRequest());
		DefaultSOAPAPICallHandler defaultHandler = new DefaultSOAPAPICallHandler(reverseTransactionReq.toXMLString(null, "ReverseTransactionReq"), null, null, this.configurationMap);
		APICallPreHandler apiCallPreHandler = new MerchantAPICallPreHandler(defaultHandler, apiUsername, getAccessToken(), getTokenSecret(), SDK_NAME, SDK_VERSION, "PayPalAPIAA", this.configurationMap);
	 	String response = call(apiCallPreHandler);
		InputSource inStream = new InputSource();
		inStream.setCharacterStream(new StringReader((String) response));
		try {
			Node node = (Node) XPathFactory.newInstance().newXPath().evaluate("Envelope/Body/ReverseTransactionResponse", DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(inStream), XPathConstants.NODE);
			return new ReverseTransactionResponseType(node);
		} catch (XPathExpressionException exe) {
			throw new RuntimeException("Unable to parse response", exe);
		}
	 }

	/** 
	 * 
	 * @throws SSLConfigurationException
	 * @throws InvalidCredentialException
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @throws HttpErrorException
	 * @throws InvalidResponseDataException
	 * @throws ClientActionRequiredException
	 * @throws MissingCredentialException
	 * @throws InterruptedException
	 * @throws OAuthException
	 */
	 public ExternalRememberMeOptOutResponseType externalRememberMeOptOut(ExternalRememberMeOptOutReq externalRememberMeOptOutReq) throws SSLConfigurationException, InvalidCredentialException, IOException, HttpErrorException, InvalidResponseDataException, ClientActionRequiredException, MissingCredentialException, InterruptedException, OAuthException, ParserConfigurationException, SAXException  {
	 	return externalRememberMeOptOut(externalRememberMeOptOutReq, (String) null);
	 }

	/** 
	 * 
	 * @throws SSLConfigurationException
	 * @throws InvalidCredentialException
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @throws HttpErrorException
	 * @throws InvalidResponseDataException
	 * @throws ClientActionRequiredException
	 * @throws MissingCredentialException
	 * @throws InterruptedException
	 * @throws OAuthException
	 */
	 public ExternalRememberMeOptOutResponseType externalRememberMeOptOut(ExternalRememberMeOptOutReq externalRememberMeOptOutReq, ICredential credential) throws SSLConfigurationException, InvalidCredentialException, IOException, HttpErrorException, InvalidResponseDataException, ClientActionRequiredException, MissingCredentialException, InterruptedException, OAuthException, ParserConfigurationException, SAXException  {
		setStandardParams(externalRememberMeOptOutReq.getExternalRememberMeOptOutRequest());
		DefaultSOAPAPICallHandler defaultHandler = new DefaultSOAPAPICallHandler(externalRememberMeOptOutReq.toXMLString(null, "ExternalRememberMeOptOutReq"), null, null, this.configurationMap);
		APICallPreHandler apiCallPreHandler = new MerchantAPICallPreHandler(defaultHandler, credential, SDK_NAME, SDK_VERSION, "PayPalAPIAA", this.configurationMap);
	 	String response = call(apiCallPreHandler);
		InputSource inStream = new InputSource();
		inStream.setCharacterStream(new StringReader((String) response));
		try {
			Node node = (Node) XPathFactory.newInstance().newXPath().evaluate("Envelope/Body/ExternalRememberMeOptOutResponse", DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(inStream), XPathConstants.NODE);
			return new ExternalRememberMeOptOutResponseType(node);
		} catch (XPathExpressionException exe) {
			throw new RuntimeException("Unable to parse response", exe);
		}
	}
	
	/**	
	 * 
	 * @throws SSLConfigurationException
	 * @throws InvalidCredentialException
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @throws HttpErrorException
	 * @throws InvalidResponseDataException
	 * @throws ClientActionRequiredException
	 * @throws MissingCredentialException
	 * @throws InterruptedException
	 * @throws OAuthException
	 */
	 public ExternalRememberMeOptOutResponseType externalRememberMeOptOut(ExternalRememberMeOptOutReq externalRememberMeOptOutReq, String apiUsername) throws SSLConfigurationException, InvalidCredentialException, IOException, HttpErrorException, InvalidResponseDataException, ClientActionRequiredException, MissingCredentialException, InterruptedException, OAuthException, ParserConfigurationException, SAXException, IOException {
		setStandardParams(externalRememberMeOptOutReq.getExternalRememberMeOptOutRequest());
		DefaultSOAPAPICallHandler defaultHandler = new DefaultSOAPAPICallHandler(externalRememberMeOptOutReq.toXMLString(null, "ExternalRememberMeOptOutReq"), null, null, this.configurationMap);
		APICallPreHandler apiCallPreHandler = new MerchantAPICallPreHandler(defaultHandler, apiUsername, getAccessToken(), getTokenSecret(), SDK_NAME, SDK_VERSION, "PayPalAPIAA", this.configurationMap);
	 	String response = call(apiCallPreHandler);
		InputSource inStream = new InputSource();
		inStream.setCharacterStream(new StringReader((String) response));
		try {
			Node node = (Node) XPathFactory.newInstance().newXPath().evaluate("Envelope/Body/ExternalRememberMeOptOutResponse", DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(inStream), XPathConstants.NODE);
			return new ExternalRememberMeOptOutResponseType(node);
		} catch (XPathExpressionException exe) {
			throw new RuntimeException("Unable to parse response", exe);
		}
	 }

}