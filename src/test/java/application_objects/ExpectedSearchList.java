package application_objects;

import java.util.List;

public class ExpectedSearchList {
    public List<ResultData> resultData;

    public ExpectedSearchList(List<ResultData> resultDataList) {
        this.resultData = resultDataList;
    }

    public List<ResultData> getResultDataList() {
        return resultData;
    }
}
