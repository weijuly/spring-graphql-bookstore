package org.people.weijuly.bookstore.util;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.StringWriter;
import java.util.Map;

@Component
public class QueryRenderer {

    @Autowired
    private VelocityEngine velocityEngine;

    public String render(String template, Map<String, Object> variables) {
        VelocityContext context = new VelocityContext();
        for (Map.Entry<String, Object> variable : variables.entrySet()) {
            context.put(variable.getKey(), variable.getValue());
        }
        StringWriter writer = new StringWriter();
        velocityEngine.getTemplate(template).merge(context, writer);
        return writer.toString();
    }

}
