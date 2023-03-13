package src;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import com.google.gson.Gson;

public class APIConsumer {

    public static void main(String[] args) {
        String apiUrl = "http://universities.hipolabs.com/search?country=Oman";
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
            for (University university : universities) {
                System.out.println(university.state_province + " - " + university.domains[0] + " - " + university.country + " - " + university.web_pages[0] + " - " + university.name + " - " + university.alpha_two_code);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


