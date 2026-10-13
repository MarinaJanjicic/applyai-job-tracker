package com.applyai.backend.service;


import com.applyai.backend.exception.GroqApiException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientResponseException;
import tools.jackson.core.JacksonException;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class GroqAiService {

    @Value("${groq_api_key}")
    private String apiKey;

    private final RestClient restClient;
    private final ObjectMapper objectMapper;

    public GroqAiService(ObjectMapper objectMapper){
        this.objectMapper=objectMapper;
        restClient= RestClient.builder().baseUrl("https://api.groq.com/openai/v1").build();
    }

    public List<String> generateInterviewQuestions(String companyName, String position){

        String prompt="Generate 5 interview questions for a "+position+" position at "
                +companyName+". Return only the questions, one question per line";

        Map<String, Object> requestBody=new HashMap<>();

        requestBody.put("model", "openai/gpt-oss-20b");

        Map<String,String> message=new HashMap<>();

        message.put("role","user");
        message.put("content",prompt);

        requestBody.put("messages",List.of(message));

        String response;

        try{
            response = restClient.post()
                    .uri("/chat/completions")
                    .header("Authorization", "Bearer " + apiKey)
                    .body(requestBody)
                    .retrieve()
                    .body(String.class);
        }catch (RestClientResponseException e){
            throw new GroqApiException("Failed to generate interview questions");
        }

        JsonNode root;

        try{
            root=objectMapper.readTree(response);
        }catch(JacksonException e){
            throw new GroqApiException("Failed to parse Groq response");
        }

        JsonNode choices=root.get("choices");

        if(choices==null || choices.isEmpty()){
            throw new GroqApiException("Groq returned an invalid response");
        }

        JsonNode firstChoice=choices.get(0);
        JsonNode messageNode=firstChoice.get("message");

        if(messageNode==null){
            throw new GroqApiException("Groq returned an invalid response");
        }

        JsonNode contentNode=messageNode.get("content");

        if(contentNode==null){
            throw new GroqApiException("Groq returned an invalid response");
        }

        String content=contentNode.asText();

        if(content.isBlank()){
            throw new GroqApiException("Groq returned an empty response");
        }

        List<String> questions = content
                .lines()
                .map(String::trim)
                .filter(line -> !line.isEmpty())
                .toList();

        return questions;
    }
}
