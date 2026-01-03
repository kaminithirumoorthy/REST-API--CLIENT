import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class RestApiClient {

    public static void main(String[] args) {

        try {
            // Public REST API URL
            String apiUrl = "https://api.agify.io/?name=Kamini";

            // Create URL object
            URL url = new URL(apiUrl);

            // Open HTTP connection
            HttpURLConnection connection =
                    (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");

            // Read API response
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));

            String line;
            StringBuilder response = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            // JSON response
            String json = response.toString();

            // Parsing JSON data
            String name = json.split("\"name\":\"")[1].split("\"")[0];
            String age = json.split("\"age\":")[1].split(",")[0];
            String count = json.split("\"count\":")[1].split("}")[0];

            // Display structured output
            System.out.println("REST API DATA");
            System.out.println("-------------");
            System.out.println("Name  : " + name);
            System.out.println("Age   : " + age);
            System.out.println("Count : " + count);

        } catch (Exception e) {
            System.out.println("Error occurred while consuming REST API");
        }
    }
}
