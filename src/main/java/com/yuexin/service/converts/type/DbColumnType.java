package com.yuexin.service.converts.type;

/**
 * 表字段类型
 * @author Haiming
 * @date 2020/8/4 4:17 PM
 */
public enum DbColumnType implements IColumnType {
    // 基本类型
    BASE_BYTE("byte", "any", null),
    BASE_SHORT("short", "any", null),
    BASE_CHAR("char", "string", null),
    BASE_INT("int", "number", null),
    BASE_LONG("long", "number",null),
    BASE_FLOAT("float", "number", null),
    BASE_DOUBLE("double", "number",null),
    BASE_BOOLEAN("boolean", "boolean", null),

    // 包装类型
    BYTE("Byte", "any", null),
    SHORT("Short", "any", null),
    CHARACTER("Character", "string", null),
    INTEGER("Integer", "number", null),
    LONG("Long", "number", null),
    FLOAT("Float", "number", null),
    DOUBLE("Double", "number", null),
    BOOLEAN("Boolean", "boolean", null),
    STRING("String", "string", null),

    // sql 包下数据类型
    DATE_SQL("Date", "Date","java.util.Date"),
    TIME("Time", "Date","java.util.Date"),
    TIMESTAMP("Timestamp", "Date","java.util.Date"),
    BLOB("Blob", "any","java.sql.Blob"),
    CLOB("Clob", "any","java.sql.Clob"),

    // java8 新时间类型
    LOCAL_DATE("LocalDate", "Date","java.time.LocalDate"),
    LOCAL_TIME("LocalTime", "Date","java.time.LocalTime"),
    YEAR("Year", "Date","java.time.Year"),
    YEAR_MONTH("YearMonth", "Date","java.time.YearMonth"),
    LOCAL_DATE_TIME("LocalDateTime", "Date","java.time.LocalDateTime"),
    INSTANT("Instant", "Date","java.time.Instant"),

    // 其他杂类
    BYTE_ARRAY("byte[]", "any",null),
    OBJECT("Object", "any",null),
    DATE("Date", "Date","java.util.Date"),
    BIG_INTEGER("BigInteger", "number","java.math.BigInteger"),
    BIG_DECIMAL("BigDecimal", "number","java.math.BigDecimal");

    /**
     * 类型
     */
    private final String type;

    /**
     * ts类型
     */
    private final String tsType;

    /**
     * 包路径
     */
    private final String pkg;

//    DbColumnType(final String type, final String pkg) {
//        this.type = type;
//        this.pkg = pkg;
//    }

    DbColumnType(String type, String tsType, String pkg) {
        this.type = type;
        this.tsType = tsType;
        this.pkg = pkg;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public String getPkg() {
        return pkg;
    }

    @Override
    public String getTsType() {
        return tsType;
    }
}
