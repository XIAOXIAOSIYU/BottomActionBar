package com.jackzhao.www.bottomactionbar.webservices;

import org.json.JSONException;
import org.json.JSONObject;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

public class BaseWebServices {

    private static final String SOAP_NAMESPACE = "http://tempuri.org/";

    /**
     * Execute WCF Services with SOAP
     *
     * @param soap_action
     * @param soap_url
     * @param soap_method_name
     * @param params
     * @return String SOAP_URL = "http://iccyp.com/GetFirstMenu.svc";
     * String SOAP_ACTION = "http://tempuri.org/IGetFirstMenu/GetSearchResults";
     * String SOAP_METHOD_NAME = "GetSearchResults";
     */
    public static String CallWebServicesWithSOAP(String soap_action,
                                                 String soap_url,
                                                 String soap_method_name,
                                                 List<PropertyInfo> params) {

        SoapSerializationEnvelope envelope = initializeEnvelope(soap_method_name, params);
        initializeTransport(envelope, soap_action, soap_url);

        SoapObject object = (SoapObject) envelope.bodyIn;
        String response = object.getProperty(0).toString();

        return response;
    }

    /**
     * Execute WCF Services with SOAP
     *
     * @param soap_action
     * @param soap_url
     * @param soap_method_name
     * @param params
     * @return
     */
    public static JSONObject ExecuteJSONObject(String soap_action,
                                               String soap_url,
                                               String soap_method_name,
                                               List<PropertyInfo> params) {

        SoapSerializationEnvelope envelope = initializeEnvelope(soap_method_name, params);
        initializeTransport(envelope, soap_action, soap_url);

        JSONObject json = null;
        try {
            json = new JSONObject("...");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return json;
    }

    public static JSONObject CallWCFServicesWithWSDL(String wsdl_path) {

        JSONObject object = new JSONObject();

        try {

            URL url = new URL(wsdl_path);
            URLConnection connection = url.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line = reader.readLine();
            object = new JSONObject(line);
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return object;
    }

    /**
     *
     *
     */
    private static SoapSerializationEnvelope initializeEnvelope(String soap_method_name, List<PropertyInfo> params) {

        SoapObject request = new SoapObject(SOAP_NAMESPACE, soap_method_name);
        additionalPropertyInfo(request, params);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = true;
        envelope.setOutputSoapObject(request);

        return envelope;
    }

    /**
     *
     *
     */
    private static void initializeTransport(SoapSerializationEnvelope envelope,
                                            String soap_action,
                                            String soap_url) {

        HttpTransportSE transport = new HttpTransportSE(soap_url);
        transport.setXmlVersionTag("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
        transport.debug = true;

        try {
            transport.call(soap_action, envelope);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }

    }

    private static void additionalPropertyInfo(SoapObject request, List<PropertyInfo> params) {

        int size = params.size();
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                PropertyInfo pi = params.get(i);
                request.addProperty(pi);
            }
        }

    }
}
