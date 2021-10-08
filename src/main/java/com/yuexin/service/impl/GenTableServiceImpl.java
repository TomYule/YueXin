package com.yuexin.service.impl;

import cn.hutool.core.text.NamingCase;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.yuexin.common.constant.GenConstants;
import com.yuexin.common.util.GenUtils;
import com.yuexin.common.util.VelocityInitializer;
import com.yuexin.common.util.VelocityUtils;
import com.yuexin.config.Constants;
import com.yuexin.domain.GenTableColumn;
import com.yuexin.security.SecurityUtils;
import com.yuexin.service.converts.ITypeConvert;
import com.yuexin.service.converts.TypeConvertRegistry;
import com.yuexin.service.querys.DbQueryRegistry;
import com.yuexin.service.querys.IDbQuery;
import org.apache.commons.lang3.StringUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.nutz.dao.Cnd;
import org.nutz.dao.DB;
import org.nutz.dao.Dao;
import org.nutz.dao.entity.Entity;
import org.nutz.dao.entity.MappingField;
import org.nutz.dao.pager.Pager;
import org.nutz.dao.sql.Sql;
import org.nutz.dao.util.Daos;
import org.nutz.lang.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.yuexin.common.service.BaseServiceImpl;
import com.yuexin.domain.GenTable;
import com.yuexin.service.GenTableService;

import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 代码生成业务 服务层实现
 *
 * @author haiming
 * @date 2021-09-13
 */
@Service
public class GenTableServiceImpl extends BaseServiceImpl<GenTable> implements GenTableService {

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
     * sql 查询抽象类 接口实现
     *
     * @return
     */
    private synchronized IDbQuery getDbQuery() {
        if (null == dbQuery) {
            DbQueryRegistry dbQueryRegistry = new DbQueryRegistry();
            // 默认 MYSQL
            dbQuery = Optional.ofNullable(dbQueryRegistry.getDbQuery(dao.meta().getType()))
                .orElseGet(() -> dbQueryRegistry.getDbQuery(DB.MYSQL));
        }
        return dbQuery;
    }

    /**
     * 数据库类型抽象类 封装 实现
     *
     * @return
     */
    private synchronized ITypeConvert getTypeConvert() {
        if (null == typeConvert) {
            TypeConvertRegistry typeConvertRegistry = new TypeConvertRegistry();
            // 默认 MYSQL
            typeConvert = Optional.ofNullable(typeConvertRegistry.getTypeConvert(dao.meta().getType()))
                .orElseGet(() -> typeConvertRegistry.getTypeConvert(DB.MYSQL));
        }
        return typeConvert;
    }

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
     * 初始化列属性字段
     */
    public void initColumnField(GenTableColumn column, GenTable table) {
        String dataType = getTypeConvert().processTypeConvert(column.getColumnType()).getType();
        String columnName = column.getColumnName();
        column.setTableId(table.getId());
        column.setCreateBy(table.getCreateBy());
        // 设置java字段名
        column.setJavaField(NamingCase.toCamelCase(columnName));
        // 设置默认类型
        column.setJavaType(dataType);
        if (GenUtils.arraysContains(GenConstants.COLUMNTYPE_STR, dataType) || GenUtils.arraysContains(GenConstants.COLUMNTYPE_TEXT, dataType)) {
            // 字符串长度超过500设置为文本域
            Integer columnLength = GenUtils.getColumnLength(column.getColumnType());
            String htmlType = columnLength >= 500 || GenUtils.arraysContains(GenConstants.COLUMNTYPE_TEXT, dataType) ? GenConstants.HTML_TEXTAREA : GenConstants.HTML_INPUT;
            column.setHtmlType(htmlType);
        } else if (GenUtils.arraysContains(GenConstants.COLUMNTYPE_TIME, dataType)) {
            column.setJavaType(GenConstants.TYPE_DATE);
            column.setHtmlType(GenConstants.HTML_DATETIME);
        } else if (GenUtils.arraysContains(GenConstants.COLUMNTYPE_NUMBER, dataType)) {
            column.setHtmlType(GenConstants.HTML_INPUT);
            // 如果是浮点型 统一用BigDecimal
            String[] str = StringUtils.split(StringUtils.substringBetween(column.getColumnType(), "(", ")"), ",");
            if (str != null && str.length == 2 && Integer.parseInt(str[1]) > 0) {
                column.setJavaType(GenConstants.TYPE_BIGDECIMAL);
            }else if (str != null && str.length == 1 && Integer.parseInt(str[0]) <= 10) {
                // 如果是整形
                column.setJavaType(GenConstants.TYPE_INTEGER);
            } else {
                // 长整形
                column.setJavaType(GenConstants.TYPE_LONG);
            }
        }
        // 插入字段（默认所有字段都需要插入）
        column.setInsert(GenConstants.REQUIRE);
        // 编辑字段
        if (!GenUtils.arraysContains(GenConstants.COLUMNNAME_NOT_EDIT, columnName) && !column.getPk()) {
            column.setEdit(GenConstants.REQUIRE);
        }
        // 列表字段
        if (!GenUtils.arraysContains(GenConstants.COLUMNNAME_NOT_LIST, columnName) && !column.getPk()) {
            column.setList(GenConstants.REQUIRE);
        }
        // 查询字段
        if (!GenUtils.arraysContains(GenConstants.COLUMNNAME_NOT_QUERY, columnName) && !column.getPk()) {
            column.setQuery(GenConstants.REQUIRE);
        }
        // 查询字段类型
        if (StringUtils.endsWithIgnoreCase(columnName, "name")) {
            column.setQueryType(GenConstants.QUERY_LIKE);
        }
        // 状态字段设置单选框
        if (StringUtils.endsWithIgnoreCase(columnName, "status")) {
            column.setHtmlType(GenConstants.HTML_RADIO);
            // 类型&性别字段设置下拉框
        }else if (StringUtils.endsWithIgnoreCase(columnName, "type")
            || StringUtils.endsWithIgnoreCase(columnName, "gender")
            || StringUtils.endsWithIgnoreCase(columnName, "sex")) {
            column.setHtmlType(GenConstants.HTML_SELECT);
            // 图片字段设置图片上传控件
        } else if (StringUtils.endsWithIgnoreCase(columnName, "image")) {
            column.setHtmlType(GenConstants.HTML_IMAGE_UPLOAD);
            // 文件字段设置文件上传控件
        } else if (StringUtils.endsWithIgnoreCase(columnName, "file")) {
            column.setHtmlType(GenConstants.HTML_FILE_UPLOAD);
            // 内容字段设置富文本控件
        } else if (StringUtils.endsWithIgnoreCase(columnName, "content")) {
            column.setHtmlType(GenConstants.HTML_EDITOR);
        }
    }

    @Override
    public void importGenTable(String tables) {
        // 查询表信息
        List<GenTable> tableList = this.selectTableList(tables);
        Optional<String> operName = SecurityUtils.getCurrentUserLogin();
        try {
            for (GenTable table : tableList) {
                String tableName = table.getTableName();
                GenUtils.initTable(table, operName.toString());
                List<GenTableColumn> genTableColumns = this.selectTableColumnsByName(tableName);
                for (GenTableColumn column : genTableColumns) {
                    initColumnField(column, table);
                }
                table.setColumns(genTableColumns);
                // 保存信息
                this._insertWith(table, "columns");
            }
        } catch (Exception e) {
            e.printStackTrace();
//            throw new ServiceException("导入失败：" + e.getMessage());
        }
    }

    /**
     * 预览代码
     *
     * @param id 表编号
     * @return 预览数据列表
     */
    @Override
    public Map<String, String> previewCode(Long id) {
        Map<String, String> dataMap = new LinkedHashMap<>();
        // 查询表信息
        GenTable table = this.fetch(id);
        table = fetchLinks(table, "columns");
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
    public List<GenTable> selectTableList(String tableName) {
        Sql sql = getDbQuery().tableByName(tableName);
        Entity<GenTable> entity = dao.getEntity(GenTable.class);
        sql.setEntity(entity).setCondition(Cnd.wrap(sql.getSourceSql()));
        dao.execute(sql);
        return sql.getList(GenTable.class);
    }

    @Override
    public Page<GenTable> selectDbTableList(String tableName, String tableComment, Pageable pageable) {
        AtomicReference<String> orderByColumn = new AtomicReference();
        AtomicReference<String> isAsc = new AtomicReference();
        pageable.getSort().stream().forEach(sort -> {
//            System.out.println(sort.getProperty());
            orderByColumn.set(sort.getProperty());
            if (sort.getDirection().isAscending()) {
                isAsc.set("asc");
            }
            if (sort.getDirection().isDescending()) {
                isAsc.set("desc");
            }
        });
        if (Strings.isNotBlank(orderByColumn.get())) {
            MappingField field = dao.getEntity(GenTable.class).getField(orderByColumn.get());
            orderByColumn.set(field.getColumnName());
        }
        Sql sql = getDbQuery().tableNotInList(tableName, tableComment, orderByColumn.get(), isAsc.get());
        Entity<GenTable> entity = dao.getEntity(GenTable.class);
        Pager pager = this.dao().createPager(pageable.getPageNumber() + 1, pageable.getPageSize());
        //记录数需手动设置
        pager.setRecordCount((int) Daos.queryCount(dao, sql));
        sql.setPager(pager);
        sql.setEntity(entity).setCondition(Cnd.wrap(sql.getSourceSql()));
        dao.execute(sql);
        return new PageImpl(sql.getList(GenTable.class), pageable, pager.getRecordCount());
    }

    @Override
    public List<GenTableColumn> selectTableColumnsByName(String tableName) {
        Sql sql = getDbQuery().tableColumnsByName(tableName);
        Entity<GenTableColumn> entity = dao.getEntity(GenTableColumn.class);
        sql.setEntity(entity);
        dao.execute(sql);
        return sql.getList(GenTableColumn.class);
    }


}
