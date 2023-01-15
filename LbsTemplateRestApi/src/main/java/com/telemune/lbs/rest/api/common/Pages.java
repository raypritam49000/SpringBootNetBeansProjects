package com.telemune.lbs.rest.api.common;

import java.util.List;

public class Pages {

    private long numberOfElements;
    private long totalElements;
    private int totalPages;
    private int size;
    private List<?> content;

    public long getNumberOfElements() {
        return numberOfElements;
    }

    public void setNumberOfElements(long numberOfElements) {
        this.numberOfElements = numberOfElements;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    /**
     * @return the content
     */
    public List<?> getContent() {
        return content;
    }

    /**
     * @param content the content to set
     */
    public void setContent(List<?> content) {
        this.content = content;
    }

    /* (non-Javadoc)
	 * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Pages [numberOfElements=" + numberOfElements + ", totalElements=" + totalElements + ", totalPages="
                + totalPages + ", size=" + size + ", content=" + content + "]";
    }
}
