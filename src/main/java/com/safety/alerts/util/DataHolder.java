package com.safety.alerts.util;

import com.safety.alerts.model.Response;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class DataHolder {
    private Response response;
}
