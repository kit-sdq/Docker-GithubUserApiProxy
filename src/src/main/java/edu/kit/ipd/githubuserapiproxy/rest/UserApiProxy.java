package edu.kit.ipd.githubuserapiproxy.rest;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class UserApiProxy {

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public LinkedHashMap<?, ?> getUserInfo(@RequestHeader(value = HttpHeaders.AUTHORIZATION) String authHeader)
            throws IOException {
        LinkedHashMap<?, ?> userEntity = callGithubApi("https://api.github.com/user", authHeader, LinkedHashMap.class);
        if (userEntity.get("email") == null) {
            List<?> emails = callGithubApi("https://api.github.com/user/emails", authHeader, List.class);
            emails.stream().map(LinkedHashMap.class::cast).filter(m -> Boolean.TRUE.equals(m.get("primary")))
                    .map(m -> m.get("email")).map(String.class::cast).findAny()
                    .ifPresent(email -> ((Map<String, String>) userEntity).put("email", email));
        }
        return userEntity;
    }

    private static <T> T callGithubApi(String url, String authHeader, Class<T> resultType) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add(HttpHeaders.AUTHORIZATION, authHeader);
        HttpEntity<T> httpEntity = new HttpEntity<>(requestHeaders);
        ResponseEntity<T> result = restTemplate.exchange(url, HttpMethod.GET, httpEntity, resultType);
        return result.getBody();
    }

}
