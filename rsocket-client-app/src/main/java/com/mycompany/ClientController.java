package com.mycompany;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientController {

    @Autowired
    RSocketClient client;
    @GetMapping("/fireandforget")
    public void fireAndForget() {
        this.client.getRSocketRequester()
                .route("fire-and-forget")
                .data("John").send().block();
    }

    @GetMapping("/requestresponse")
    public String requestresponse() {
        return this.client.getRSocketRequester()
                .route("request-response")
                .data("John")
                .retrieveMono(String.class)
                .block();
    }
}
