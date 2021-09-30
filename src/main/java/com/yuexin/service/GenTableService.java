package com.yuexin.service;

import com.yuexin.common.service.BaseService;
import com.yuexin.domain.GenTable;
import com.yuexin.domain.GenTableColumn;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

/**
 * 代码生成业务 服务层实现
 *
 * @author haiming
 * @date 2021-09-13
 */
public interface GenTableService extends BaseService<GenTable> {


    public void importGenTable(List<GenTable> tableList);

    /**
     * 预览代码
     *
     * @param id 表编号
     * @return 预览数据列表
     */
    public Map<String, String> previewCode(Long id);

    /**
     * 查询据库列表
     * @param tableName
     * @param tableComment
     * @param pageable
     * @return
     */
    public Page<GenTable> selectDbTableList(String tableName, String tableComment, Pageable pageable);

    /**
     * 根据表名称查询字段信息
     * @param tableName
     * @return
     */
    public List<GenTableColumn> selectTableColumnsByName(String tableName);
}
