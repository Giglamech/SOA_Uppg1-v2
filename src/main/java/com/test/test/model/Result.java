package com.test.test.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("Result")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result {
    @Id
    private String resultId;
    private String ssn;
    private String courseNr;
    private String module;
    private String date;
    private String grade;
    private String status;
}
