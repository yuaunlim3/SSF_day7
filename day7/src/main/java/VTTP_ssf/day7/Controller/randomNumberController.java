package VTTP_ssf.day7.Controller;

import java.security.SecureRandom;
import java.util.Random;

import javax.print.attribute.standard.MediaTray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import VTTP_ssf.day7.Services.randomNumService;
import jakarta.json.Json;
import jakarta.json.JsonObject;

@Controller
@RequestMapping(path = "/random")
public class randomNumberController {
    @Autowired
    private randomNumService randomSvc;

    // Get /random
    // Accept text/html
    @GetMapping()
    public ModelAndView getRandom(Model model) {
        // model.addAttribute("rndNum",rand.nextInt(100));

        ModelAndView mav = new ModelAndView("random-number.html");
        mav.addObject("randomNum", randomSvc.getRandom());

        return mav;

    }

    // Get /random
    // Accept application/json
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> getRandomNumasJSON(@RequestParam(defaultValue = "100") int bound) {
        JsonObject result = Json.createObjectBuilder()
                .add("randomNum", randomSvc.getRandom(bound))
                .build();

        return ResponseEntity.ok(result.toString());
    }
}
