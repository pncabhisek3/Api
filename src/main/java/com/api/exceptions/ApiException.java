package com.api.exceptions;

import io.swagger.annotations.Api;
import lombok.*;
import org.apache.commons.lang3.StringUtils;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ApiException extends RuntimeException{

    private int errCode;
    private String errMsg;

    private Code code;

    public ApiException(Code err){
       this(err, StringUtils.EMPTY);
    }

    public ApiException(Code err, String customMsg){
        this.errCode = err.getErrCode();
        this.errMsg = String.format(err.getErrMsg(), customMsg);
        this.code = err;
    }

    @AllArgsConstructor
    @Getter
    public static enum Code{
        UPLOAD_ERROR(1, "ERROR: FILE/DATA UPLOAD::%s")
        ,DOWNLOAD_ERROR(2, "ERROR: FILE/DATA DOWNLOAD::%s")
        ,SAVE_ERROR(3, "ERROR: DATA NOT SAVED::%s")
        ,UPDATE_ERROR(4, "ERROR: DATA NOT UPDATED::%s")
        ,FETCH_ERROR(5, "ERROR: DATA NOT FETCHED::%s")
        ,DELETE_ERROR(6, "ERROR: DATA NOT DELETED::%s")
        ;

        private int errCode;
        private String errMsg;

    }
}
