package org.people.weijuly.bookstore.util;

import org.apache.commons.lang3.exception.ExceptionUtils;

public class ExceptionUtil {

	public static String format(Exception e) {
		return String.format("[%s: %s], caused by: %s",
				e.getClass().getName(), e.getMessage(), ExceptionUtils.getRootCauseMessage(e));
	}
}
