package com.jackzhao.www.bottomactionbar.webservices;

import org.json.JSONException;
import org.json.JSONObject;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.List;

public class BaseWebServices {

    private static final String SOAP_NAMESPACE = "http://tempuri.org/";

    public static String ExecuteString(String soap_action,
                                       String soap_url,
                                       String soap_method_name,
                                       List<PropertyInfo> params) {

        SoapSerializationEnvelope envelope = initializeEnvelope(soap_method_name, params);
        initializeTransport(envelope, soap_action, soap_url);

        SoapObject object = (SoapObject) envelope.bodyIn;
        String response = object.getProperty(0).toString();

        return response;
    }

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
