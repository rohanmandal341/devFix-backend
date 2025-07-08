package com.setupsnap.setup_snap_api.service;

import com.setupsnap.setup_snap_api.Dto.GeminiRequest;
import com.setupsnap.setup_snap_api.Dto.GeminiResponse;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GeminiService {

    private final WebClient webClient;

    @Value("${gemini.api.key}")
    private String key;


    public GeminiRequest buildRequest(String prompt){
        GeminiRequest.Content.Part parts = new GeminiRequest.Content.Part(prompt);
        GeminiRequest.Content content = new GeminiRequest.Content(List.of(parts));
        return new GeminiRequest(List.of(content));

    }

    public GeminiResponse extractText(String json){
        org.json.JSONObject object = new JSONObject(json);

     String text = object.getJSONArray("candidates")
                .getJSONObject(0)
                .getJSONObject("content")
                .getJSONArray("parts")
                .getJSONObject(0)
                .getString("text");

        return new GeminiResponse(text);
    }

    public Mono<GeminiResponse> askGemini(String prompt){
        return webClient.post().uri(uriBuilder -> uriBuilder.queryParam("key",key).build())
                .bodyValue(buildRequest(prompt))
                .retrieve()
                .bodyToMono(String.class)
                .map(this::extractText);
    }
}
