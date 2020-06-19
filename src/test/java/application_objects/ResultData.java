package application_objects;

import java.util.List;

public class ResultData {
    public String code;
    public List<User> data;

    public ResultData(String code, List<User> data) {
        this.code = code;
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public List<User> getData() {
        return data;
    }
}
