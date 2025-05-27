import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;

public class GitHubApiClient {
    private static final String GITHUB_API_URL = "https://api.github.com";
    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;

    public GitHubApiClient() {
        this.httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .connectTimeout(Duration.ofSeconds(10))
                .build();
        this.objectMapper = new ObjectMapper();
    }

    public void getRepositoryInfo(String owner, String repo) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(GITHUB_API_URL + "/repos/" + owner + "/" + repo))
                    .header("Accept", "application/vnd.github.v3+json")
                    .GET()
                    .build();

            HttpResponse<String> response = httpClient.send(request, 
                    HttpResponse.BodyHandlers.ofString());

            // Print response status
            System.out.println("Response Status Code: " + response.statusCode());
            
            // Parse and print JSON response
            JsonNode jsonNode = objectMapper.readTree(response.body());
            System.out.println("\nRepository Information:");
            System.out.println("Name: " + jsonNode.get("name").asText());
            System.out.println("Description: " + jsonNode.get("description").asText());
            System.out.println("Stars: " + jsonNode.get("stargazers_count").asInt());
            System.out.println("Forks: " + jsonNode.get("forks_count").asInt());
            System.out.println("Language: " + jsonNode.get("language").asText());
            System.out.println("Created at: " + jsonNode.get("created_at").asText());

        } catch (IOException | InterruptedException e) {
            System.out.println("Error fetching repository information: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        GitHubApiClient client = new GitHubApiClient();
        // Example: Fetch information about the Spring Boot repository
        client.getRepositoryInfo("spring-projects", "spring-boot");
    }
} 