package com.rickrocks.project.mapper;

import com.rickrocks.project.entity.EmailLogEntity;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class EmailLogRowMapper implements RowMapper<EmailLogEntity> {

    @Override
    public EmailLogEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
        EmailLogEntity emailLog = new EmailLogEntity();
        emailLog.setId(rs.getInt("id"));
        emailLog.setFromEmail(rs.getString("from_email"));
        emailLog.setContent(rs.getString("content"));
        emailLog.setAttachmentName(rs.getString("attachment_name"));
        emailLog.setAttachmentPath(rs.getString("attachment_path"));
        emailLog.setTimestamp(rs.getTimestamp("timestamp").toLocalDateTime());
        emailLog.setProcessTimestamp(rs.getTimestamp("process_timestamp").toLocalDateTime());
        emailLog.setOutputfileName(rs.getString("outputfile_name"));
        emailLog.setOutputfilePath(rs.getString("outputfile_path"));
        emailLog.setCreatedTimestamp(rs.getTimestamp("created_timestamp").toLocalDateTime());
        emailLog.setCreatedBy(rs.getString("created_by"));
        return emailLog;
    }
}

