package co.com.technicaltestbamcolombia.api;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.MediaType;

@RestController
@RequestMapping( produces = MediaType.APPLICATION_JSON_VALUE )
@AllArgsConstructor
public class ApiRest {


    @PostMapping
    public String soy(){
        return "Hello World";
    }

    @PostMapping(path = "/auth")
    public String soydos(){
        return "Hello World auth";
    }
}
