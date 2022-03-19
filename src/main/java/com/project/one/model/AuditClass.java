package com.project.one.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Version;
import java.time.LocalDateTime;


@Setter
@Getter
//@MappedSuperclass
public class AuditClass {

    @CreatedDate
    LocalDateTime createdDate;
    @LastModifiedDate
    LocalDateTime lastModifiedDate;
    @Version
    Long version;

}


