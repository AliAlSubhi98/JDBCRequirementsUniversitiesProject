package src;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.Gson;

public class APIConsumer {

	public static void main(String[] args) {
		String apiUrl = "http://universities.hipolabs.com/search?country=Oman";// United%20statesz
		try {
			URL url = new URL(apiUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("HTTP error code : " + conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
			String output;
			StringBuilder json = new StringBuilder();

			while ((output = br.readLine()) != null) {
				json.append(output);
			}

			conn.disconnect();

			Gson gson = new Gson();
			University[] universities = gson.fromJson(json.toString(), University[].class);

			// Use universities array for further processing
			for (int i = 0; i < universities.length; i++) {
				University university = universities[i];
				System.out.println( (i+1) + ":" + university.state_province + " - " + university.country + " - "
						+ university.name + " - " + university.alpha_two_code);

				// iterate over domains
				for (int j = 0; j < university.domains.length; j++) {
					System.out.println("\tDomain " + (j + 1) + ": " + university.domains[j]);
				}

				// iterate over web_pages
				for (int k = 0; k < university.web_pages.length; k++) {
					System.out.println("\tWeb page " + (k + 1) + ": " + university.web_pages[k]);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
