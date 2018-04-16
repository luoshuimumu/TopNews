package com.example.luoshuimumu.TopNews.gank.entity.response;

import java.util.List;

/**
 * Created by luoshuimumu on 2018/1/11.
 */

public class BaseResponse<T> {
    List<String> category;
    private boolean error;
    private int count;
    private T results;

    public List<String> getCategory() {
        return category;
    }

    public void setCategory(List<String> category) {
        this.category = category;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public T getResults() {
        return results;
    }

    public void setResults(T results) {
        this.results = results;
    }
}
