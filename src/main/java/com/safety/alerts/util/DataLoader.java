package com.safety.alerts.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safety.alerts.model.Response;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

@Log4j2
@Component
@Order(1)
public class DataLoader implements CommandLineRunner {

    private final ObjectMapper objectMapper;
    private final DataHolder dataHolder;

    public DataLoader(ObjectMapper objectMapper, DataHolder dataHolder) {
        this.objectMapper = objectMapper;
        this.dataHolder = dataHolder;
    }


    @Override
    public void run(String... args) throws Exception {
        loadData();
    }

    private void loadData() {
        try {
            Response response = objectMapper.readValue(new File("src/main/resources/data.json"), Response.class);
            dataHolder.setResponse(response);
            log.info("Load Data successful");
        }catch (IOException e) {
            log.error("Load Data failed", e);
        }
    }
}
