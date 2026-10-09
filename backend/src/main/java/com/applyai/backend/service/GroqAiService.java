package com.applyai.backend.service;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.core.JacksonException;

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

    public List<String> generateInterviewQuestions(String companyName, String position) throws JacksonException{

        String prompt="Generate 5 interview questions for a "+position+" position at "
                +companyName+". Return only the questions, one question per line";

        Map<String, Object> requestBody=new HashMap<>();

        requestBody.put("model", "openai/gpt-oss-20b");

        Map<String,String> message=new HashMap<>();

        message.put("role","user");
        message.put("content",prompt);

        requestBody.put("messages",List.of(message));

        String response = restClient.post()
                .uri("/chat/completions")
                .header("Authorization", "Bearer " + apiKey)
                .body(requestBody)
                .retrieve()
                .body(String.class);

        JsonNode root=objectMapper.readTree(response);
        String content = root
                .get("choices")
                .get(0)
                .get("message")
                .get("content")
                .asText();


        List<String> questions = content
                .lines()
                .toList();

        return questions;
    }
}
