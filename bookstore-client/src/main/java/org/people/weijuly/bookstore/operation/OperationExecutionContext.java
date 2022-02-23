package org.people.weijuly.bookstore.operation;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OperationExecutionContext {

    private String query;

    private String field;

    private Class<?> type;
}
