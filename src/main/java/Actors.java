import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.HttpURLConnection;
import java.util.Scanner;
import org.json.JSONObject;

public class Actors {
    public static final String API_KEY = "W+vh5nItJvK8jXhbKH2x0g==JJe0UK88tZZBGS1n";   // TODO --> add your api key about Actors here
    String netWorth;
    Boolean isAlive;

    public Actors(String netWorth, boolean isAlive) {
        //TODO --> (Write a proper constructor using the get_from_api functions)
        this.netWorth = netWorth;
        this.isAlive = isAlive;
    }

    @SuppressWarnings({"deprecation"})
    /**
     * Retrieves data for the specified actor.
     * @param name for which Actor should be retrieved
     * @return a string representation of the Actors info or null if an error occurred
     */
    public String getActorData(String name) {
        try {
            URL url = new URL("https://api.api-ninjas.com/v1/celebrity?name=" +
                    name.replace(" ", "+") + "&apikey=" + API_KEY);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("X-Api-Key", API_KEY);
            System.out.println(connection);
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }

                in.close();
                String res = response.toString();
                res = res.substring(1, res.length() - 1);
                return res;
            } else {
                return "Error: " + connection.getResponseCode() + " " + connection.getResponseMessage();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    public String getNameViaApi(String actorsInfoJson) {
        JSONObject jason = new JSONObject(actorsInfoJson);
        return jason.getString("name");
    }
    public double getNetWorthViaApi(String actorsInfoJson) {
        JSONObject jason = new JSONObject(actorsInfoJson);
        return jason.getDouble("net_worth");
    }
    public String getGenderViaApi(String actorsInfoJson) {
        JSONObject jason = new JSONObject(actorsInfoJson);
        return jason.getString("gender");
    }public float getHeightViaApi(String actorsInfoJson) {
        JSONObject jason = new JSONObject(actorsInfoJson);
        return jason.getFloat("height");
    }public String getDateOfBirthViaApi(String actorsInfoJson) {
        JSONObject jason = new JSONObject(actorsInfoJson);
        return jason.getString("birthday");
    }public int getAgeViaApi(String actorsInfoJson) {
        JSONObject jason = new JSONObject(actorsInfoJson);
        return jason.getInt("age");
    }



    public boolean isAlive(String actorsInfoJson) {
        //TODO --> (If your chosen actor is alive it must return true otherwise it must return false)
        JSONObject jason = new JSONObject(actorsInfoJson);
        return jason.getBoolean("is_alive");
    }

    public String getDateOfDeathViaApi(String actorsInfoJson) {
        //TODO --> (If your chosen actor is deceased it must return the date of death)  -->
        JSONObject jason = new JSONObject(actorsInfoJson);
        return jason.getString("death");
    }

}