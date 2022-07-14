package com.stavvy.models;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class EmailPayload {
    String text;
    String body;
    String subject;
    List<String> to;
    String from;
}
