package com.lilong.massage.general.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author : lilong
 * @date : 2024-12-29 17:06
 * @description : 消息配置表
 */

@Data
@TableName("message_config")
public class MessageConfig {
    @TableId(type = IdType.AUTO)
    private Long id;

    @TableField("config_key")
    private String configKey;

    @TableField("config_value")
    private String configValue;

    @TableField("description")
    private String description;

    @TableField("created_at")
    private java.sql.Timestamp createdAt;

    @TableField("updated_at")
    private java.sql.Timestamp updatedAt;
}
