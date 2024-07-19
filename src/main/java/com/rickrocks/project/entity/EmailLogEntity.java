package com.rickrocks.project.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_email_log")
public class EmailLogEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "from_email", length = 255)
    private String fromEmail;

    @Column(name = "content", length = 255)
    private String content;

    @Column(name = "attachment_name", length = 255)
    private String attachmentName;

    @Column(name = "attachment_path", length = 255)
    private String attachmentPath;

    @Column(name = "timestamp")
    private LocalDateTime timestamp;

    @Column(name = "process_timestamp")
    private LocalDateTime processTimestamp;

    @Column(name = "outputfile_name", length = 255)
    private String outputfileName;

    @Column(name = "outputfile_path", length = 255)
    private String outputfilePath;

    @Column(name = "created_timestamp")
    private LocalDateTime createdTimestamp;

    @Column(name = "created_by", length = 255)
    private String createdBy;
}
