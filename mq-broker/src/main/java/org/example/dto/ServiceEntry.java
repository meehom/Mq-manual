package org.example.dto;

import lombok.Data;

/**
 * @version 1.0
 * @Author meehom
 * @Date 2023/7/23 15:02
 */
@Data
public class ServiceEntry {
    /**
     * address 信息
     * @since 0.0.3
     */
    private String address;

    /**
     * 端口号
     * @since 0.0.3
     */
    private int port;

    /**
     * 分组名称
     */
    private String groupName;
}
