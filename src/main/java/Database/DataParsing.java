package Database;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

class WebConnection{
	String json;
	WebConnection() throws Exception{
		String address = "https://open.neis.go.kr/hub/hisTimetable?KEY=ef518bb597d145019913afca85cd9703&Type=json&plndex=1&pSize=100&ATPT_OFCDC_SC_CODE=J10&SD_SCHUL_CODE=7530185&GRADE=3&CLASS_NM=2&TI_FROM_YMD=20211004&TI_TO_YMD=20211008";
		BufferedReader br;
		URL url;
		HttpURLConnection conn;
		String protocol = "GET";
		
		url = new URL(address);
		conn = (HttpURLConnection)url.openConnection();
		conn.setRequestMethod(protocol);
		br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
		
		json = br.readLine();
	}
}

public class DataParsing {
	public static void main(String[] args) throws Exception {
		WebConnection wc = new WebConnection();
		String json = wc.json;
		DataDAO t = new DataDAO();
		
		JSONParser parser = new JSONParser();
		JSONObject obj = (JSONObject)parser.parse(json);
		JSONArray hisTimetable = (JSONArray)obj.get("hisTimetable");
		
		JSONObject row = (JSONObject)hisTimetable.get(1);
		JSONArray rowin = (JSONArray)row.get("row");
		for(int i=0; i<rowin.size(); i++) {
			JSONObject tmp = (JSONObject)rowin.get(i);
			String id = (String)tmp.get("ITRT_CNTNT");
			t.tableInsert(i, id);
		}
		t.tableSelect();
		
	}
}
