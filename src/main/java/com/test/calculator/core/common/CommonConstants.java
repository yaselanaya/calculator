package com.test.calculator.core.common;

public final class CommonConstants {

    /* Validation Error Keys */
    public static final String UPDATE_ERROR_MAP_KEY_FIELD = "field";

    public static final String UPDATE_ERROR_MAP_KEY_CODE = "code";

    public static final String UPDATE_ERROR_MAP_KEY_MESSAGE = "message";

    public static final String UPDATE_ERROR_MAP_KEY_VALUE = "value";

    public static final String LANGUAGE_PROPERTY = "lang";

    /* Key */
    public static final String CUSTOM_PARAM_PAGEABLE_DATA = "data";

    /* Mapping */
    public static final String MAPPING_FIND_ALL = "/findAll";

    public static final String MAPPING_FIND_ALL_PAGE = "/findAllPage";

    public static final String MAPPING_POST_SAVE = "/save";

    public static final String MAPPING_GET_BY_ID = "/getById/{id}";

    /* PARAMS */
    public static final String VALUE_DATA_PAGE_FILTER = "String with format " +
            "{\"filters\":[{\"field\":\"Name of the entity field\", \"value\":\"Value to filter\", " +
            "\"operator\":\"Operator value enum to compare\"}], \"pageable\":{\"page\":\"Default 0\", \"size\":\"Default 10\", " +
            "\"orders\":[{\"direction\":\"DESC|ASC\", \"property\":\"Name of the entity property\"}]}}";

}
