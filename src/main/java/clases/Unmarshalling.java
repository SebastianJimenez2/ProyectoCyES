package clases;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class Unmarshalling {


    public static Double unmarshalling(String acción){
        String apiKey = "1KZ9MRIXWAGZ4CPB"; // Reemplaza esto con tu propia API Key de Alpha Vantage
        String symbol = acción;
        String apiUrl = "https://www.alphavantage.co/query?function=TIME_SERIES_DAILY&symbol=" + symbol + "&apikey=" + apiKey;

        try {
            URL url = new URL(apiUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = in.readLine()) != null) {
                response.append(line);
            }
            in.close();

            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(response.toString());

            JsonNode timeSeries = rootNode.get("Time Series (Daily)");
            if (timeSeries != null) {
                Map.Entry<String, JsonNode> firstEntry = ((Iterable<Map.Entry<String, JsonNode>>) timeSeries::fields).iterator().next();

                JsonNode data = firstEntry.getValue();
                return data.get("1. open").asDouble();
            } else {
                return 0.0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0.0;
    }
}
