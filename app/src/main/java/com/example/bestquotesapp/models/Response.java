package com.example.bestquotesapp.models;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Response {

    @SerializedName("count")
    @Expose
    private Integer count;
    @SerializedName("totalCount")
    @Expose
    private Integer totalCount;
    @SerializedName("page")
    @Expose
    private Integer page;
    @SerializedName("totalPages")
    @Expose
    private Integer totalPages;
    @SerializedName("lastItemIndex")
    @Expose
    private Integer lastItemIndex;
    @SerializedName("results")
    @Expose
    private List<Result> results = null;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public Integer getLastItemIndex() {
        return lastItemIndex;
    }

    public void setLastItemIndex(Integer lastItemIndex) {
        this.lastItemIndex = lastItemIndex;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

}