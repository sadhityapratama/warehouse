package com.miniproject.warehouse.response;

import lombok.Data;

@Data
public class HttpResponse {
    private int status;
    private String message;
    private Object object;
}
