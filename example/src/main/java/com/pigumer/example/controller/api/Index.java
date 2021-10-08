package com.pigumer.example.controller.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.DefaultUriBuilderFactory;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Controller
public class Index implements HelloApi {

    private final static Logger logger = LoggerFactory.getLogger(Index.class);

    private final ObjectMapper mapper;

    public Index() {
        this.mapper = new ObjectMapper();
    }

    @Autowired
    private ObjectProvider<HttpServletRequest> requestProvider;

    @Override
    public ResponseEntity<Map<String, String>> helloGet() {
        HttpServletRequest request = requestProvider.getObject();
        try {
            Map<String, String> res = new HashMap<>();
            res.put("remoteHost", request.getRemoteHost());
            res.put("remoteAddr", request.getRemoteAddr());
            res.put("remotePort", String.valueOf(request.getRemotePort()));
            res.put("localName", request.getLocalName());
            res.put("requestURI", request.getRequestURI());
            for (Iterator<String> it = request.getHeaderNames().asIterator(); it.hasNext(); ) {
                String name = it.next();
                res.put(name, request.getHeader(name));
            }
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("test")
    public String test() {
        return "redirect:/hello";
    }
}
