package com.stavvy.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BasicDataResponse {
    String detail;
    String error;
}
