package cn.edu.sjtu.sip_server.util;

import io.swagger.annotations.ApiModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

@ApiModel
public enum TResultCode {

    /* base status code */
    SUCCESS(1, "success"),
    FAILURE(2, "failure"),

    /* parameter error：10001-19999 */
    PARAM_IS_INVALID(10001, "parameter is invalid"),
    PARAM_IS_BLANK(10002, "parameter is empty"),
    PARAM_TYPE_BIND_ERROR(10003, "the type of parameter error"),
    PARAM_NOT_COMPLETE(10004, "parameter not complete"),

    /* user error：20001-29999*/
    USER_NOT_LOGGED_IN(20001, "user not logged"),
    USER_LOGIN_ERROR(20002, "account not exist or password error"),
    USER_ACCOUNT_FORBIDDEN(20003, "account banned"),
    USER_NOT_EXIST(20004, "account not exist"),
    USER_HAS_EXISTED(20005, "account had existed"),

    /* business error：30001-39999 */
    BUSINESS_ERROR(30001, "business wrong"),

    /* system error：40001-49999 */
    SYSTEM_INNER_ERROR(40001, "system is busy，please try again later"),

    /* data error：50001-599999 */
    RESULE_DATA_NONE(50001, "data not found"),
    DATA_IS_WRONG(50002, "data error"),
    DATA_ALREADY_EXISTED(50003, "data had existed"),

    /* intface error：60001-69999 */
    INTERFACE_INNER_INVOKE_ERROR(60001, "inner system interface invoke error"),
    INTERFACE_OUTTER_INVOKE_ERROR(60002, "outer system interface invoke error"),
    INTERFACE_FORBID_VISIT(60003, "interface forbidden"),
    INTERFACE_ADDRESS_INVALID(60004, "interface address invalid"),
    INTERFACE_REQUEST_TIMEOUT(60005, "interface request timeout"),
    INTERFACE_EXCEED_LOAD(60006, "interface overload"),

    /* permission error：70001-79999 */
    PERMISSION_NO_ACCESS(70001, "no permission to access");

    private Integer code;

    private String message;
    public static final Logger logger = LoggerFactory.getLogger(TResultCode.class);

    TResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * vertify the same code
     *
     * @param args
     */
    public static void main(String[] args) {
        TResultCode[] apiTResultCodes = TResultCode.values();
        logger.info(apiTResultCodes[0].name());
        List<Integer> codeList = new ArrayList<>();
        for (TResultCode apiTResultCode : apiTResultCodes) {
            if (codeList.contains(apiTResultCode.code)) {
                logger.info("warning, find duplicate code:" + apiTResultCode.code);
            } else {
                codeList.add(apiTResultCode.getCode());
            }
        }
    }

    public Integer getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }

    @Override
    public String toString() {
        return this.name();
    }
}

