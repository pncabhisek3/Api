package com.api.entity.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse<T> {

    T data;
    boolean success = true;
    List<Object> errors;
    String msg;

    public ApiResponse(T data) {
        this.success = (!ObjectUtils.isEmpty(data));
        this.data = data;
        this.msg = (this.success) ? "SUCCESS!" : "ERROR";

    }

    public ApiResponse(boolean success, T data) {
        this(data);
        this.success = success;
    }

    public ApiResponse(boolean success) {
        this.data = null;
        this.errors = null;
        this.success = success;
        this.msg = (this.success) ? "SUCCESS!" : "ERROR";
    }

    public ApiResponse(List<Object> err) {
        this(null, err, null);
    }

    public ApiResponse(T data, List<Object> err, String msg) {
        this.success = ObjectUtils.isEmpty(err);
        this.data = data;
        this.errors = err;
        this.msg = (this.success) ? ((StringUtils.isNoneBlank(msg)) ? msg : "SUCCESS!") : "ERROR";
    }

}
