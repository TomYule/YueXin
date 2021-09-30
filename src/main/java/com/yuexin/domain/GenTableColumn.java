package com.yuexin.domain;

import com.yuexin.common.base.BaseModel;
import io.swagger.annotations.ApiModel;
import org.apache.commons.lang3.StringUtils;
import org.nutz.dao.entity.annotation.Comment;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

import java.io.Serializable;

/**
 * 代码生成业务表字段 entity.\n@author haiming
 *
 * @author haiming
 * @date 2021-09-14
 */
@ApiModel(description = "代码生成业务表字段 entity.\n@author haiming")
@Table("gen_table_column")
public class GenTableColumn extends BaseModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column("id")
    @Comment("编号")
    private Long id;

    /** 归属表编号 */
    @Column("table_id")
    @Comment("归属表编号")
    private Long tableId;

    /** 列名称 */
    @Column("column_name")
    @Comment("列名称")
    private String columnName;

    /** 列描述 */
    @Column("column_comment")
    @Comment("列描述")
    private String columnComment;

    /** 列类型 */
    @Column("column_type")
    @Comment("列类型")
    private String columnType;

    /** JAVA类型 */
    @Column("java_type")
    @Comment("JAVA类型")
    private String javaType;

    /** JAVA字段名 */
    @Column("java_field")
    @Comment("JAVA字段名")
    private String javaField;

    /** 是否主键（1是） */
    @Column("is_pk")
    @Comment("是否主键（1是）")
    private Boolean isPk;

    /** 是否自增（1是） */
    @Column("is_increment")
    @Comment("是否自增（1是）")
    private Boolean isIncrement;

    /** 是否必填（1是） */
    @Column("is_required")
    @Comment("是否必填（1是）")
    private Boolean isRequired;

    /** 是否为插入字段（1是） */
    @Column("is_insert")
    @Comment("是否为插入字段（1是）")
    private Boolean isInsert;

    /** 是否编辑字段（1是） */
    @Column("is_edit")
    @Comment("是否编辑字段（1是）")
    private Boolean isEdit;

    /** 是否列表字段（1是） */
    @Column("is_list")
    @Comment("是否列表字段（1是）")
    private Boolean isList;

    /** 是否查询字段（1是） */
    @Column("is_query")
    @Comment("是否查询字段（1是）")
    private Boolean isQuery;

    /** 查询方式（等于、不等于、大于、小于、范围） */
    @Column("query_type")
    @Comment("查询方式（等于、不等于、大于、小于、范围）")
    private String queryType;

    /** 显示类型（文本框、文本域、下拉框、复选框、单选框、日期控件） */
    @Column("html_type")
    @Comment("显示类型（文本框、文本域、下拉框、复选框、单选框、日期控件）")
    private String htmlType;

    /** 字典类型 */
    @Column("dict_type")
    @Comment("字典类型")
    private String dictType;

    /** 排序 */
    @Column("sort")
    @Comment("排序")
    private Integer sort;


//    @ManyToOne
//    @JsonIgnoreProperties(value = "tableIds", allowSetters = true)
    private GenTable genTable;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTableId() {
        return tableId;
    }

    public void setTableId(Long tableId) {
        this.tableId = tableId;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnComment() {
        return columnComment;
    }

    public void setColumnComment(String columnComment) {
        this.columnComment = columnComment;
    }

    public String getColumnType() {
        return columnType;
    }

    public void setColumnType(String columnType) {
        this.columnType = columnType;
    }

    public String getJavaType() {
        return javaType;
    }

    public void setJavaType(String javaType) {
        this.javaType = javaType;
    }

    public String getJavaField() {
        return javaField;
    }

    public void setJavaField(String javaField) {
        this.javaField = javaField;
    }

    public Boolean getPk() {
        return isPk;
    }

    public void setPk(Boolean pk) {
        isPk = pk;
    }

    public Boolean getIncrement() {
        return isIncrement;
    }

    public void setIncrement(Boolean increment) {
        isIncrement = increment;
    }

    public Boolean getRequired() {
        return isRequired;
    }

    public void setRequired(Boolean required) {
        isRequired = required;
    }

    public Boolean getInsert() {
        return isInsert;
    }

    public void setInsert(Boolean insert) {
        isInsert = insert;
    }

    public Boolean getEdit() {
        return isEdit;
    }

    public void setEdit(Boolean edit) {
        isEdit = edit;
    }

    public Boolean getList() {
        return isList;
    }

    public void setList(Boolean list) {
        isList = list;
    }

    public Boolean getQuery() {
        return isQuery;
    }

    public void setQuery(Boolean query) {
        isQuery = query;
    }

    public String getQueryType() {
        return queryType;
    }

    public void setQueryType(String queryType) {
        this.queryType = queryType;
    }

    public String getHtmlType() {
        return htmlType;
    }

    public void setHtmlType(String htmlType) {
        this.htmlType = htmlType;
    }

    public String getDictType() {
        return dictType;
    }

    public void setDictType(String dictType) {
        this.dictType = dictType;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public GenTable getGenTable() {
        return genTable;
    }

    public void setGenTable(GenTable genTable) {
        this.genTable = genTable;
    }


    public boolean isSuperColumn() {
        return isSuperColumn(this.javaField);
    }

    public static boolean isSuperColumn(String javaField) {
        return StringUtils.equalsAnyIgnoreCase(javaField,
            // BaseEntity
            "createBy", "createTime", "updateBy", "updateTime", "remark",
            // TreeEntity
            "parentName", "parentId", "orderNum", "ancestors");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof GenTableColumn)) {
            return false;
        }
        return id != null && id.equals(((GenTableColumn) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "GenTableColumn{" +
            "id=" + id +
            ", tableId='" + tableId + '\'' +
            ", columnName='" + columnName + '\'' +
            ", columnComment='" + columnComment + '\'' +
            ", columnType='" + columnType + '\'' +
            ", javaType='" + javaType + '\'' +
            ", javaField='" + javaField + '\'' +
            ", isPk=" + isPk +
            ", isIncrement=" + isIncrement +
            ", isRequired=" + isRequired +
            ", isInsert=" + isInsert +
            ", isEdit=" + isEdit +
            ", isList=" + isList +
            ", isQuery=" + isQuery +
            ", queryType='" + queryType + '\'' +
            ", htmlType='" + htmlType + '\'' +
            ", dictType='" + dictType + '\'' +
            ", sort=" + sort +
            ", genTable=" + genTable +
            ", createBy='" + createBy + '\'' +
            ", createTime=" + createTime +
            ", updateBy='" + updateBy + '\'' +
            ", updateTime=" + updateTime +
            '}';
    }
}
