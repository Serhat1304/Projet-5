package com.safety.alerts.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safety.alerts.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.tinylog.Logger;

import java.io.File;
import java.io.IOException;


@Component
@Order(1)
public class DataLoader implements CommandLineRunner {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private DataHolder dataHolder;



    @Override
    public void run(String... args) throws Exception {
        loadData();
    }

    private void loadData() {
        try {
            Response response = objectMapper.readValue(new File("src/main/resources/data.json"), Response.class);
            dataHolder.setResponse(response);
            Logger.info("Load Data successful");
        }catch (IOException e) {
            Logger.error("Load Data failed", e);
        }
    }
}
