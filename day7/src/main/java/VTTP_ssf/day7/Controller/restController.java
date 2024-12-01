package VTTP_ssf.day7.Controller;

import java.util.Date;
import java.util.Map;

import jakarta.json.Json;
import jakarta.json.JsonObject;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class restController {

    // GET /time
    // Accept: application/json
    @GetMapping(path = "/time", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getTime() {
        // For image--> ResponseEntity<byteArray>
        JsonObject resp = Json.createObjectBuilder()
                .add("time", (new Date()).toString())
                .build();

        // 200 Ok
        // Content-Type = application/json
        // {"time": "a time"}
        // ResponseEntity.status(200).body(resp.toString()) similar to below

        // resp.toString() ==> return JSON object as String
        return ResponseEntity.ok(resp.toString());
    }

    // Send as text/plain
    // Get/timeText
    // Accept: text/plain
    @GetMapping(path = "/time", produces = "text/plain")
    public ResponseEntity<String> getTimeText() {
        String time = (new Date()).toString();
        System.out.printf(">>>Time:%s\n", time);
        return ResponseEntity.ok(time);
    }

    // POST /customer
    // Content-Type = application/json
    // Accept: application/json

    @PostMapping(path = "/customer", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> postCustomer(@RequestBody String payload) {

        System.out.printf(">>>>payload: %s\n", payload);
        // 202 Accepted
        // Content-type = application/json
        // X-MyHeader = abc123
        // {}
        return ResponseEntity.accepted()
                .header("X-MyHeader", "abc123") // to add header
                .body("{}");
    }

    // POST /customer
    // Content-Type = application/x-www-form-urlencoded
    // Accept: text/html
    @PostMapping(path = "/customer", produces = MediaType.TEXT_HTML_VALUE, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<String> postCustomerText(@RequestParam MultiValueMap<String, String> form) {
        System.out.printf(">>>Form :%s\n",form);
        String html = "<h1> %s has been regsitered</h1>".formatted(form.getFirst("name"));
        return ResponseEntity.ok(html);
    }

}
