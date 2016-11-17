package com.jackzhao.www.bottomactionbar.webservices;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CompanyWebServices extends BaseWebServices {


    public JSONArray GetCompanies(String searching_keys,
                                  String searching_locations,
                                  String longitude,
                                  String latitude,
                                  String array_count) {


        String WSDL_URL = "http://iccyp.com/GetFirstMenu.svc/json/searchresults/"
                + searching_keys + ","
                + searching_locations + ","
                + longitude + ","
                + latitude + ","
                + array_count;

        Log.i("WSDL", WSDL_URL);

        JSONObject response = this.ExecuteWCFWithWSDL(WSDL_URL);
        JSONArray companies = new JSONArray();
        try {
            companies = response.getJSONArray("GetSearchResultsResult");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return companies;

    }

//    public Company GetCompanyDetails(int branch_id) {
//
//        String SOAP_URL_COMPANY = "http://www.iccyp.com/GetFirstMenu.svc/json/searchresults";
//        String SOAP_ACTION_COMPANY = "http://tempuri.org/IGetFirstMenu/GetListResponse";
//        String SOAP_METHOD_NAME = "GetListResponse";
//        List<PropertyInfo> pis = new ArrayList<>();
//        Company company = new Company();
//        JSONObject json = this.ExecuteJSONObject(SOAP_ACTION_COMPANY, SOAP_URL_COMPANY, SOAP_METHOD_NAME, pis);
//        return company;
//
//    }

}
