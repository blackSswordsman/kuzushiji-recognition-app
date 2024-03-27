package com.astra.kuzushiji.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.StreamUtils;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


@RequiredArgsConstructor
@Service
public class TensorFlowServerClient {
    @Value("${tensorFlowServerHost}")
    private String tensorFlowServerHost;

    private final RestTemplate restTemplate;

    public File processImage(Resource image) {
        var headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        var requestBody = new LinkedMultiValueMap<String, Object>();
        requestBody.add("file", image);
        var request = new HttpEntity<>(requestBody, headers);
        var requestCallback = restTemplate.httpEntityCallback(request, File.class);
        return restTemplate.execute(this.tensorFlowServerHost, HttpMethod.POST, requestCallback, this::extractImage);
    }

    private File extractImage(ClientHttpResponse clientHttpResponse) throws IOException {
        var tempFile = File.createTempFile("tmp", "processedImage");
        StreamUtils.copy(clientHttpResponse.getBody(), new FileOutputStream(tempFile));
        return tempFile;
    }
}
