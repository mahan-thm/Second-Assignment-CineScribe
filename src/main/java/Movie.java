import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.io.BufferedReader;
import java.util.ArrayList;

public class Movie {
    public static final String API_KEY = "e5520821";   // TODO --> add your api key about Movie here
    int ImdbVotes;
    ArrayList<String> actorsList;
    String rating;

    public Movie(ArrayList<String> actorsList, String rating, int ImdbVotes) {
        //TODO --> (Write a proper constructor using the get_from_api functions)
    }

    @SuppressWarnings("deprecation")
    /**
     * Retrieves data for the specified movie.
     *
     * @param title the name of the title for which MovieData should be retrieved
     * @return a string representation of the MovieData, or null if an error occurred
     */

    public String getMovieData(String title) {
        try {
            URL url = new URL("https://www.omdbapi.com/?t=" + title + "&apikey=" + API_KEY);
            URLConnection Url = url.openConnection();
            Url.setRequestProperty("Authorization", "Key" + API_KEY);
            BufferedReader reader = new BufferedReader(new InputStreamReader(Url.getInputStream()));
            String line;
            StringBuilder stringBuilder = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
            reader.close();
            String movieData = stringBuilder.toString();
            // Check if the movie data contains an error message
            if (movieData.contains("{\"Response\":\"False\",\"Error\":\"Movie not found!\"}")) {
                throw new IOException("Movie not found");
            }
            return movieData;
        } catch (MalformedURLException e) {
            System.out.println("The URL is not in a valid form");
        } catch (IOException e) {
            System.out.println("ERROR: Invalid name... Movie not found\nTry again");
        }
        return null;
    }


    public String getTitleViaApi(String moviesInfoJson) {
        JSONObject jason = new JSONObject(moviesInfoJson);
        return jason.getString("Title");
    }

    public int getYearViaApi(String moviesInfoJson) {
        JSONObject jason = new JSONObject(moviesInfoJson);
        return jason.getInt("Year");
    }

    public int getImdbVotesViaApi(String moviesInfoJson) {
        JSONObject json = new JSONObject(moviesInfoJson);
        String imdbVotesString = json.getString("imdbVotes");
        // Remove any non-digit characters
        imdbVotesString = imdbVotesString.replaceAll("\\D+","");
        return Integer.parseInt(imdbVotesString);
    }


    public String getRatingViaApi(String moviesInfoJson) {
        //TODO --> (This function must return the rating in the "Ratings" part
        // where the source is "Internet Movie Database")  -->
        JSONObject jason = new JSONObject(moviesInfoJson);
        JSONArray imdbRating = jason.getJSONArray("Ratings");
        return imdbRating.getJSONObject(0).getString("Value");


    }

    public String getActorListViaApi(String movieInfoJson) {
        //TODO --> (This function must return the "Actors" in actorsList)
        JSONObject jason = new JSONObject(movieInfoJson);
        return jason.getString("Actors");
    }
}