package com.yuexin.service;

import com.yuexin.common.service.BaseService;
import com.yuexin.domain.GenTable;

import java.util.Map;

/**
 * 代码生成业务 服务层实现
 *
 * @author haiming
 * @date 2021-09-13
 */
public interface GenTableService extends BaseService<GenTable> {
    /**
     * 预览代码
     *
     * @param id 表编号
     * @return 预览数据列表
     */
    public Map<String, String> previewCode(Long id);
}
