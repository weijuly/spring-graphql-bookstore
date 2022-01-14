package org.people.weijuly.bookstore.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;

public class LoggerUtil {

    public static String format(Exception e) {
        return String.format("[%s: %s], caused by: %s",
                e.getClass().getName(), e.getMessage(), ExceptionUtils.getRootCauseMessage(e));
    }

    public static String dump(Object object) {
        try {
            return new ObjectMapper().writeValueAsString(object);
        } catch (JsonProcessingException e) {
            return StringUtils.EMPTY;
        }
    }
}
