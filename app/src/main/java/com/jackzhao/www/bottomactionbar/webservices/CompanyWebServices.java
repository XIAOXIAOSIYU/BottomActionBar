package com.jackzhao.www.bottomactionbar.webservices;

import com.jackzhao.www.bottomactionbar.models.Company;
import org.json.JSONObject;
import org.ksoap2.serialization.PropertyInfo;

public class CompanyWebServices extends BaseWebServices {


    public String GetCompanies(String searching_keys,
                               String searching_locations,
                               String latitude,
                               String longitude,
                               String array_count) {

        String SOAP_URL = "http://iccyp.com/GetFirstMenu.svc";
        String SOAP_ACTION = "http://tempuri.org/IGetFirstMenu/GetSearchResults";
        String SOAP_METHOD_NAME = "GetSearchResults";

        PropertyInfo pi_keys= new PropertyInfo();
        pi_keys.setName("text");
        pi_keys.setValue(searching_keys);
        pi_keys.setType(String.class);

        PropertyInfo pi_city= new PropertyInfo();
        pi_city.setName("city");
        pi_city.setValue(searching_locations);
        pi_city.setType(String.class);

        PropertyInfo pi_longitude= new PropertyInfo();
        pi_longitude.setName("longitudeString");
        pi_longitude.setValue(longitude);
        pi_longitude.setType(String.class);

        PropertyInfo pi_latitude= new PropertyInfo();
        pi_latitude.setName("latitudeString");
        pi_latitude.setValue(latitude);
        pi_latitude.setType(String.class);

        PropertyInfo pi_count= new PropertyInfo();
        pi_count.setName("count");
        pi_count.setValue(array_count);
        pi_count.setType(String.class);

        String companies = this.ExecuteString(SOAP_ACTION, SOAP_URL, SOAP_METHOD_NAME);
        return companies;

    }

    public Company GetCompanyDetails(int branch_id) {

        String SOAP_URL_COMPANY = "http://iccyp.com/GetFirstMenu.svc/json/searchresults";
        String SOAP_ACTION_COMPANY = "http://tempuri.org/IGetFirstMenu/GetListResponse";
        String SOAP_METHOD_NAME = "GetListResponse";

        Company company = new Company();
        JSONObject json = this.ExecuteJSONObject(SOAP_ACTION_COMPANY, SOAP_URL_COMPANY, SOAP_METHOD_NAME);
        return company;

    }

}
