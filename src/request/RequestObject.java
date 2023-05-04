package request;

import java.util.Map;

public interface RequestObject {

    String contentType();

    String convert();

    Map<String, Object> getValues();
}
