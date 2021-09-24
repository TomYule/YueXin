package com.yuexin.service.converts;

import com.yuexin.common.base.ColumnInfo;
import com.yuexin.service.converts.type.IColumnType;

/**
 * @author Haiming
 * @date 2020/8/4 4:15 PM
 */
public interface ITypeConvert {
    /**
     * 执行类型转换
     *
     * @param columnInfo   字段列信息
     * @return ignore
     */
    default IColumnType processTypeConvert(ColumnInfo columnInfo) {
        // 该方法提供重写
        return processTypeConvert( columnInfo.getDataType());
    }


    /**
     * 执行类型转换
     *
     * @param fieldType    字段类型
     * @return ignore
     */
    IColumnType processTypeConvert(String fieldType);
}
