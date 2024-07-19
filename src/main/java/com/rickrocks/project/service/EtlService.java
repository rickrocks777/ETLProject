
package com.rickrocks.project.service;

import com.rickrocks.project.entity.EmailLogEntity;
import com.rickrocks.project.mapper.EmailLogRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class EtlService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Bean
    public ApplicationRunner initialize() {
        return args -> createTargetTableIfNotExists();
    }

    private void createTargetTableIfNotExists() {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS another_table (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "from_email VARCHAR(255), " +
                "content VARCHAR(255), " +
                "attachment_name VARCHAR(255), " +
                "attachment_path VARCHAR(255), " +
                "timestamp TIMESTAMP, " +
                "process_timestamp TIMESTAMP, " +
                "outputfile_name VARCHAR(255), " +
                "outputfile_path VARCHAR(255), " +
                "created_timestamp TIMESTAMP, " +
                "created_by VARCHAR(255)" +
                ")";
        jdbcTemplate.execute(createTableSQL);
    }

    @Scheduled(fixedRate = 3600000) // Runs every hour
    public void runEtlProcess() {
        // Extract
        List<EmailLogEntity> emailLogs = jdbcTemplate.query("SELECT * FROM t_email_log", new EmailLogRowMapper());

        // Transform and Load
        for (EmailLogEntity emailLog : emailLogs) {
            // Convert LocalDateTime to Timestamp
            Timestamp timestamp = Timestamp.valueOf(emailLog.getTimestamp());
            Timestamp processTimestamp = Timestamp.valueOf(emailLog.getProcessTimestamp());
            Timestamp createdTimestamp = Timestamp.valueOf(emailLog.getCreatedTimestamp());

            // Insert data into another table
            jdbcTemplate.update(
                    "INSERT INTO another_table (from_email, content, attachment_name, attachment_path, timestamp, process_timestamp, outputfile_name, outputfile_path, created_timestamp, created_by) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                    emailLog.getFromEmail(), emailLog.getContent(), emailLog.getAttachmentName(), emailLog.getAttachmentPath(),
                    timestamp, processTimestamp, emailLog.getOutputfileName(), emailLog.getOutputfilePath(), createdTimestamp, emailLog.getCreatedBy()
            );
        }
    }
}
