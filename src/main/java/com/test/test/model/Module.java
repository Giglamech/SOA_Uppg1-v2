package com.test.test.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.lang.NonNull;

@Document("Module")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Module {
    @Id
    private String moduleId;
    @NonNull
    private String moduleCode;
    @NonNull
    private String courseCode;
    @NonNull
    private String moduleType;
    @NonNull
    private String[] grades;

    public String getModuleId() {
        return moduleId;
    }

    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }

    public String getModuleCode() {
        return moduleCode;
    }

    public void setModuleCode(String moduleCode) {
        this.moduleCode = moduleCode;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getModuleType() {
        return moduleType;
    }

    public void setModuleType(String moduleType) {
        this.moduleType = moduleType;
    }

    public String[] getGrades() {
        return grades;
    }

    public void setGrades(String[] assignments) {
        this.grades = assignments;
    }


}
