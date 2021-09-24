package com.yuexin.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.yuexin.common.constant.GenConstants;
import com.yuexin.common.util.VelocityInitializer;
import com.yuexin.common.util.VelocityUtils;
import com.yuexin.config.Constants;
import com.yuexin.domain.GenTableColumn;
import com.yuexin.service.converts.ITypeConvert;
import com.yuexin.service.querys.IDbQuery;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.nutz.dao.Dao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yuexin.common.service.BaseServiceImpl;
import com.yuexin.domain.GenTable;
import com.yuexin.service.GenTableService;

import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 代码生成业务 服务层实现
 *
 * @author haiming
 * @date 2021-09-13
 */
@Service
public class GenTableServiceImpl extends BaseServiceImpl<GenTable> implements GenTableService{

    /**
     * 注入同名的一个ioc对象
     */
    @Autowired
    protected Dao dao;

    /**
     * 数据库信息查询
     */
    private static IDbQuery dbQuery;

    /**
     * 类型转换
     */
    private static ITypeConvert typeConvert;

    /**
     * 设置主子表信息
     *
     * @param table 业务表信息
     */
    public void setSubTable(GenTable table) {
        String subTableName = table.getSubTableName();
        if (StrUtil.isNotEmpty(subTableName)) {
            table.setSubTable(this.fetch(subTableName));
        }
    }

    /**
     * 设置主键列信息
     *
     * @param table 业务表信息
     */
    public void setPkColumn(GenTable table) {
        for (GenTableColumn column : table.getColumns()) {
            if (column.getPk()) {
                table.setPkColumn(column);
                break;
            }
        }
        if (ObjectUtil.isNull(table.getPkColumn())) {
            table.setPkColumn(table.getColumns().get(0));
        }
        if (GenConstants.TPL_SUB.equals(table.getTplCategory())) {
            for (GenTableColumn column : table.getSubTable().getColumns()) {
                if (column.getPk()) {
                    table.getSubTable().setPkColumn(column);
                    break;
                }
            }
            if (ObjectUtil.isNull(table.getSubTable().getPkColumn())) {
                table.getSubTable().setPkColumn(table.getSubTable().getColumns().get(0));
            }
        }
    }

    /**
     * 预览代码
     * @param id 表编号
     * @return 预览数据列表
     */
    @Override
    public Map<String, String> previewCode(Long id) {
        Map<String, String> dataMap = new LinkedHashMap<>();
        // 查询表信息
        GenTable table = this.fetch(id);
        table = fetchLinks(table,"columns");
        // 设置主子表信息
        setSubTable(table);
        // 设置主键列信息
        setPkColumn(table);
        VelocityInitializer.initVelocity();
        VelocityContext context = VelocityUtils.prepareContext(table);
        // 获取模板列表
        List<String> templates = VelocityUtils.getTemplateList(table.getTplCategory());
        for (String template : templates) {
            // 渲染模板
            StringWriter sw = new StringWriter();
            Template tpl = Velocity.getTemplate(template, Constants.UTF8);
            tpl.merge(context, sw);
            dataMap.put(template, sw.toString());
        }
        return dataMap;
    }

    @Override
    public List<GenTable> selectDbTableList(GenTable genTable) {

        return null;
    }
}
