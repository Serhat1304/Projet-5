package com.safety.alerts.mapper;


import com.safety.alerts.model.Response;

import java.io.IOException;

public interface IJSONMapper {
    Response getResponse() throws IOException;
}
