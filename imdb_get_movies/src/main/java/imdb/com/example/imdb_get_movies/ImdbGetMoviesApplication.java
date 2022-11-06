package imdb.com.example.imdb_get_movies;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ImdbGetMoviesApplication {

	public static void main(String[] args) throws IOException, InterruptedException {
		SpringApplication.run(ImdbGetMoviesApplication.class, args);

		// connect to imDb top 250 movies
		String imDbUrl = "https://imdb-api.com/en/API/Top250Movies/k_9vfy0yd3";
		URI imDbUri = URI.create(imDbUrl);
		HttpClient client = HttpClient.newHttpClient();

		// get data (title, poster, classification)
		HttpRequest request = HttpRequest.newBuilder(imDbUri).GET().build();
		HttpResponse<String> response = client.send(request, BodyHandlers.ofString());

		// show and handle data
		String body = response.body();

		JsonParser parser = new JsonParser();
		List<Map<String, String>> movieList = parser.parse(body);

		for (Map<String,String> movie : movieList) {
			System.out.println(movie.get("title"));
		}

	}

}
