package conversor;

import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import org.json.JSONObject;

public class Conversor {
    private static final String API_KEY = "6fc5522c1ca48437dfb7dce6";
    private static final String API_URL = "https://v6.exchangerate-api.com/v6/" + API_KEY + "/latest/";

    public double convertir(String base, String destino, double monto) {
        if (monto < 0) {
            return -1;
        }

        try {
            String urlStr = API_URL + base;
            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            int responseCode = conn.getResponseCode();
            if (responseCode != 200) {
                return -1;
            }

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            JSONObject jsonResponse = new JSONObject(response.toString());
            String result = jsonResponse.getString("result");
            if (!"success".equals(result)) {
                return -1;
            }

            JSONObject rates = jsonResponse.getJSONObject("conversion_rates");
            if (!rates.has(destino)) {
                return -1;
            }

            double tasa = rates.getDouble(destino);
            return monto * tasa;

        } catch (Exception e) {
            System.out.println("Error al conectar con la API: " + e.getMessage());
            return -1;
        }
    }
}