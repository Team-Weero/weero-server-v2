package team.weero.app.global.error;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;

@Component
public class DiscordWebhookNotifier {

  private static final String WEBHOOK_URL =
      "https://discord.com/api/webhooks/1470676196151791699/-iOpN04ZC84kSopiMyrj-Tb9d2GRyHAk1zw5Wx0TrbSRl_WyJZXYFujUfA4nPGGypREB";
  private static final int MAX_STACKTRACE_LENGTH = 1500;

  private final HttpClient httpClient;
  private final ObjectMapper objectMapper;

  public DiscordWebhookNotifier(ObjectMapper objectMapper) {
    this.httpClient = HttpClient.newHttpClient();
    this.objectMapper = objectMapper;
  }

  public void sendErrorNotification(Exception exception, String endpoint) {
    try {
      String stackTrace = getStackTrace(exception);
      String truncatedStackTrace = truncateStackTrace(stackTrace);
      String timestamp =
          LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

      Map<String, Object> embed =
          Map.of(
              "title",
              "🚨 서버 에러 발생",
              "description",
              String.format(
                  "**Endpoint:** %s\n**Exception:** %s",
                  endpoint, exception.getClass().getSimpleName()),
              "color",
              15158332,
              "fields",
              List.of(
                  Map.of(
                      "name",
                      "Error Message",
                      "value",
                      exception.getMessage() != null ? exception.getMessage() : "No message"),
                  Map.of("name", "Stack Trace", "value", "```\n" + truncatedStackTrace + "\n```")),
              "timestamp",
              timestamp);

      Map<String, Object> payload = Map.of("embeds", List.of(embed));

      String jsonPayload = objectMapper.writeValueAsString(payload);

      HttpRequest request =
          HttpRequest.newBuilder()
              .uri(URI.create(WEBHOOK_URL))
              .header("Content-Type", "application/json")
              .POST(HttpRequest.BodyPublishers.ofString(jsonPayload))
              .build();

      httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString());

    } catch (Exception e) {
      System.err.println("Failed to send Discord notification: " + e.getMessage());
    }
  }

  private String getStackTrace(Exception exception) {
    StringWriter sw = new StringWriter();
    PrintWriter pw = new PrintWriter(sw);
    exception.printStackTrace(pw);
    return sw.toString();
  }

  private String truncateStackTrace(String stackTrace) {
    if (stackTrace.length() <= MAX_STACKTRACE_LENGTH) {
      return stackTrace;
    }

    int halfLength = MAX_STACKTRACE_LENGTH / 2;
    String start = stackTrace.substring(0, halfLength);
    String end = stackTrace.substring(stackTrace.length() - halfLength);

    return start + "\n...\n" + end;
  }
}
