package com.yuexin.common.util;

import cn.hutool.core.text.NamingCase;
import cn.hutool.core.util.StrUtil;
import com.google.common.base.CaseFormat;
import com.yuexin.common.constant.GenConstants;
import com.yuexin.config.GenConfig;
import com.yuexin.domain.GenTable;
import com.yuexin.domain.GenTableColumn;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;

/**
 * @description: some desc
 * @author: haiming
 * @date: 2021/9/29 下午5:48
 */
public class GenUtils {
    /**
     * 初始化表信息
     */
    public static void initTable(GenTable genTable, String operName) {
        genTable.setClassName(convertClassName(genTable.getTableName()));
        genTable.setPackAgeName(GenConfig.getPackageName());
        genTable.setModuleName(getModuleName(GenConfig.getPackageName()));
        genTable.setBusinessName(getBusinessName(genTable.getTableName()));
        genTable.setFunctionName(genTable.getTableComment());
        genTable.setFunctionAuthor(GenConfig.getAuthor());
        genTable.setCreateBy(operName);
    }


    /**
     * 表名转换成Java类名
     *
     * @param tableName 表名称
     * @return 类名
     */
    public static String convertClassName(String tableName) {
        boolean autoRemovePre = GenConfig.getAutoRemovePre();
        String tablePrefix = GenConfig.getTablePrefix();
        if (autoRemovePre && StrUtil.isNotEmpty(tablePrefix)) {
            List<String> searchList = StrUtil.split(tablePrefix, ",");
            tableName = replaceFirst(tableName, searchList);
        }
//        return CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, tableName);
        return NamingCase.toPascalCase(tableName);
    }

    /**
     * 获取业务名
     *
     * @param tableName 表名
     * @return 业务名
     */
    public static String getBusinessName(String tableName) {
        int lastIndex = tableName.lastIndexOf("_");
        int nameLength = tableName.length();
        String businessName = StringUtils.substring(tableName, lastIndex + 1, nameLength);
        return businessName;
    }

    /**
     * 获取模块名
     *
     * @param packageName 包名
     * @return 模块名
     */
    public static String getModuleName(String packageName) {
        int lastIndex = packageName.lastIndexOf(".");
        int nameLength = packageName.length();
        String moduleName = StringUtils.substring(packageName, lastIndex + 1, nameLength);
        return moduleName;
    }

    /**
     * 批量替换前缀
     *
     * @param replacementm 替换值
     * @param searchList   替换列表
     * @return
     */
    public static String replaceFirst(String replacementm, List<String> searchList) {
        String text = replacementm;
        for (String searchString : searchList) {
            if (replacementm.startsWith(searchString)) {
                text = replacementm.replaceFirst(searchString, "");
                break;
            }
        }
        return text;
    }


    /**
     * 校验数组是否包含指定值
     *
     * @param arr         数组
     * @param targetValue 值
     * @return 是否包含
     */
    public static boolean arraysContains(String[] arr, String targetValue) {
        return Arrays.asList(arr).contains(targetValue);
    }

    /**
     * 获取字段长度
     *
     * @param columnType 列类型
     * @return 截取后的列类型
     */
    public static Integer getColumnLength(String columnType) {
        if (StringUtils.indexOf(columnType, "(") > 0) {
            String length = StringUtils.substringBetween(columnType, "(", ")");
            return Integer.valueOf(length);
        } else {
            return 0;
        }
    }
}
