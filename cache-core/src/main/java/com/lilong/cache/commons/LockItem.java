package com.lilong.cache.commons;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : lilong
 * @date : 2025-03-12 20:35
 * @description :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LockItem {
    private String key;
    private String value;
}
