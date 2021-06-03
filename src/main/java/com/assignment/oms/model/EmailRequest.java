package com.assignment.oms.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmailRequest {
    private String from;
    private List<String> recipients;
    private String subject;
    private String templateName;
    private String emailBody;
    private String content;
}
