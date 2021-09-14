package com.yuexin.domain;

import com.yuexin.common.base.BaseModel;
import io.swagger.annotations.ApiModel;
import org.nutz.dao.entity.annotation.*;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

import java.io.Serializable;
import java.util.List;

/**
 * 代码生成业务表 gen_table
 */
@ApiModel(description = "代码生成业务表 entity.\n@author haiming")
@Table("gen_table")
public class GenTable extends BaseModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column("id")
    @Comment("编号")
    private Long id;

    /** 表名称 */
    @Column("table_name")
    @Comment("表名称")
    @Name
    private String tableName;

    /** 表描述 */
    @Column("table_comment")
    @Comment("表描述")
    private String tableComment;

    /** 关联子表的表名 */
    @Column("sub_table_name")
    @Comment("关联子表的表名")
    private String subTableName;

    /** 子表关联的外键名 */
    @Column("sub_table_fk_name")
    @Comment("子表关联的外键名")
    private String subTableFkName;

    /** 实体类名称 */
    @Column("class_name")
    @Comment("实体类名称")
    private String className;

    /** 使用的模板（crud单表操作 tree树表操作） */
    @Column("tpl_category")
    @Comment("使用的模板（crud单表操作 tree树表操作）")
    private String tplCategory;

    /** 生成包路径 */
    @Column("package_name")
    @Comment("生成包路径")
    private String packAgeName;

    /** 生成模块名 */
    @Column("module_name")
    @Comment("生成模块名")
    private String moduleName;

    /** 生成业务名 */
    @Column("business_name")
    @Comment("生成业务名")
    private String businessName;

    /** 生成功能名 */
    @Column("function_name")
    @Comment("生成功能名")
    private String functionName;

    /** 生成功能作者 */
    @Column("function_author")
    @Comment("生成功能作者")
    private String functionAuthor;

    /** 生成代码方式（0zip压缩包 1自定义路径） */
    @Column("gen_type")
    @Comment("生成代码方式（0zip压缩包 1自定义路径）")
    private String genType;

    /** 生成路径（不填默认项目路径） */
    @Column("gen_path")
    @Comment("生成路径（不填默认项目路径）")
    private String genPath;

    /** 其它生成选项 */
    @Column("options")
    @Comment("其它生成选项")
    private String options;

    /** 子表信息 */
    private GenTable subTable;
    /** 主键信息 */
    private GenTableColumn pkColumn;

    /** 表列信息 */
    @Many(field = "tableId" ,key = "id")
    private List<GenTableColumn> columns;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTableComment() {
        return tableComment;
    }

    public void setTableComment(String tableComment) {
        this.tableComment = tableComment;
    }

    public String getSubTableName() {
        return subTableName;
    }

    public void setSubTableName(String subTableName) {
        this.subTableName = subTableName;
    }

    public String getSubTableFkName() {
        return subTableFkName;
    }

    public void setSubTableFkName(String subTableFkName) {
        this.subTableFkName = subTableFkName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getTplCategory() {
        return tplCategory;
    }

    public void setTplCategory(String tplCategory) {
        this.tplCategory = tplCategory;
    }

    public String getPackAgeName() {
        return packAgeName;
    }

    public void setPackAgeName(String packAgeName) {
        this.packAgeName = packAgeName;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getFunctionName() {
        return functionName;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

    public String getFunctionAuthor() {
        return functionAuthor;
    }

    public void setFunctionAuthor(String functionAuthor) {
        this.functionAuthor = functionAuthor;
    }

    public String getGenType() {
        return genType;
    }

    public void setGenType(String genType) {
        this.genType = genType;
    }

    public String getGenPath() {
        return genPath;
    }

    public void setGenPath(String genPath) {
        this.genPath = genPath;
    }

    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options;
    }

    public GenTable getSubTable() {
        return subTable;
    }

    public void setSubTable(GenTable subTable) {
        this.subTable = subTable;
    }

    public GenTableColumn getPkColumn() {
        return pkColumn;
    }

    public void setPkColumn(GenTableColumn pkColumn) {
        this.pkColumn = pkColumn;
    }

    public List<GenTableColumn> getColumns() {
        return columns;
    }

    public void setColumns(List<GenTableColumn> columns) {
        this.columns = columns;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof GenTable)) {
            return false;
        }
        return id != null && id.equals(((GenTable) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "GenTable{" +
            "id=" + id +
            ", tableName='" + tableName + '\'' +
            ", tableComment='" + tableComment + '\'' +
            ", subTableName='" + subTableName + '\'' +
            ", subTableFkName='" + subTableFkName + '\'' +
            ", className='" + className + '\'' +
            ", tplCategory='" + tplCategory + '\'' +
            ", packAgeName='" + packAgeName + '\'' +
            ", moduleName='" + moduleName + '\'' +
            ", businessName='" + businessName + '\'' +
            ", functionName='" + functionName + '\'' +
            ", functionAuthor='" + functionAuthor + '\'' +
            ", genType='" + genType + '\'' +
            ", genPath='" + genPath + '\'' +
            ", options='" + options + '\'' +
            ", createBy='" + createBy + '\'' +
            ", createTime=" + createTime +
            ", updateBy='" + updateBy + '\'' +
            ", updateTime=" + updateTime +
            ", remark='" + remark + '\'' +
            '}';
    }
}
