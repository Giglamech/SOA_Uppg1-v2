package com.test.test.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("Module")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Module {
    @Id
    private String courseCode;
    private String moduleCode;
    private String moduleType;
}
