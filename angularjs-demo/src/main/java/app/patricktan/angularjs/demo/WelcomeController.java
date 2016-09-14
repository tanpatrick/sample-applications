package app.patricktan.angularjs.demo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class WelcomeController {

    @Value("${application.message:Hello World}")
    private String message = "Hello World";

    @GetMapping("/")
    public String welcome(Map<String, Object> model) throws JsonProcessingException {
        Map<String, String> required = new HashMap();
        required.put("lastName", "Last name is required");
        required.put("firstName", "First name is required");

        Map<String, String> invalid = new HashMap();
        invalid.put("lastName", "Last name is invalid");
        invalid.put("firstName", "First name is invalid");

        Map<String, Map<String, String>> messages = new HashMap();

        messages.put("invalid", invalid);
        messages.put("required", required);

        String json = new ObjectMapper().writeValueAsString(messages);

        model.put("messages", json);

        model.put("time", new Date());
        model.put("message", this.message);
        return "welcome";
    }

    @RequestMapping("/foo")
    public String foo(Map<String, Object> model) {
        throw new RuntimeException("Foo");
    }

    @RequestMapping("/hello")
    @ResponseBody
    public Map<String, String> hello() {
        Map<String, String> data = new HashMap();
        data.put("message", "Hello World");
        return data;
    }

}
