package request;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class RequestJSON implements RequestObject {

    @Override
    public String contentType() {
        return "application/json";
    }

    @Override
    public String convert() {
        return "{" +
                getValues().keySet().stream()
                        .map(key -> mapKey(key) + mapValue(getValues().get(key)))
                        .collect(Collectors.joining(",")) +
                "}";
    }

    private String mapKey(String key) {
        return sanitize(key) + ":";
    }

    private String mapValue(Object value) {
        if (value == null) {
            return "null";
        } else if (value instanceof RequestJSON) {
            return ((RequestJSON) value).convert();
        } else if (value instanceof Collection) {
            StringBuilder builder = new StringBuilder("[");
            ((Collection<?>) value).forEach(val -> builder.append(mapValue(val)));
            return builder.toString();
        } else if (value.getClass() == String.class) {
            return sanitize((String) value);
        } else if (value.getClass() == Integer.class) {
            return Integer.toString((Integer) value);
        } else if (value.getClass() == Boolean.class) {
            return ((Boolean) value).toString();
        } else {
            return "null";
        }
    }

    private String sanitize(String input) {
        return "\"" + input.replace("\"", "\\\"") + "\"";
    }

    @Override
    public abstract Map<String, Object> getValues();
}
