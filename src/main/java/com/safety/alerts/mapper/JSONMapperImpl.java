package com.safety.alerts.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safety.alerts.model.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.IOException;

public class JSONMapperImpl implements IJSONMapper {

    private static final Logger logger = LoggerFactory.getLogger(JSONMapperImpl.class);

    private ObjectMapper objectMapper;
    private Response response;

    @Autowired
    public JSONMapperImpl(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public Response getResponse() throws IOException {
        if(response !=null) {
            return response;
        }
        try{
            response = objectMapper.readValue(new File("src/main/ressources/data.json"), Response.class);
        }catch (IOException e) {
            logger.error("JSON mapping error", e);
            throw new RuntimeException(e);
        }
        return response;
    }
}
