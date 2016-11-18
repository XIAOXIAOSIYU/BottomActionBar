package com.jackzhao.www.bottomactionbar.webservices;

import com.jackzhao.www.bottomactionbar.models.Company;

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

        //Log.i("WSDL", WSDL_URL);

        JSONObject response = this.CallWCFServicesWithWSDL(WSDL_URL);
        JSONArray companies = new JSONArray();
        try {
            companies = response.getJSONArray("GetSearchResultsResult");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return companies;

    }

    public Company GetCompanyDetails(int company_id) {

        Company company = new Company();
        String WSDL_URL = "http://iccyp.com/GetFirstMenu.svc/json/detail/" + company_id;
        JSONObject object = this.CallWCFServicesWithWSDL(WSDL_URL);

        try {

            JSONObject response = object.getJSONArray("GetSearchResultsResult").getJSONObject(0);
            company.setBranchID(Integer.parseInt(response.getString("BranchID")));
            company.setChineseName(response.getString("ChineseName"));
            company.setEnglishName(response.getString("EnglishName"));
            company.setPhone(response.getString("Phone"));
            company.setStreet(response.getString("Street"));
            company.setCity(response.getString("City"));
            company.setFax(response.getString("Fax"));
            company.setLatitude(response.getString("Latitude"));
            company.setLongitude(response.getString("Longitude"));
            company.setOpenHour(response.getString("OpenHour"));
            company.setWebSite(response.getString("WebSite"));
            company.setEmail(response.getString("Email"));
            company.setZipCode(response.getString("ZipCode"));
            company.setState(response.getString("State"));
            company.setOpenHour(response.getString("OpenHour"));
            company.setTags(response.getString("Tags"));
            company.setListID(response.getString("listID"));
            company.setAboutUsDesc(response.getString("AboutUsDesc"));
            company.setAboutUsTitle(response.getString("AboutUsTitle"));

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return company;

    }

}
