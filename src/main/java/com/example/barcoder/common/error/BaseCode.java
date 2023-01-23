package com.example.barcoder.common.error;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum BaseCode {
    /**
     * Common
     */
    SUCCESS(200, "C001", "요청이 성공적으로 처리되었습니다."),
    CREATED(201, "C002", "요청이 성공적으로 생성되었습니다."),
    INVALID_INPUT_VALUE(400, "C003", "요청 정보가 올바르지 않습니다."),
    INVALID_TYPE_VALUE(400, "C004", "요청 타입이 올바르지 않습니다."),
    HANDLE_ACCESS_DENIED(403, "C005", "접근 권한이 존재하지 않습니다."),
    METHOD_NOT_ALLOWED(405, "C006", "요청 Method 가 올바르지 않습니다."),
    INTERNAL_SERVER_ERROR(500, "C007", "내부 서버 오류입니다."),

    /**
     * User
     */
    EXISTS_USERNAME(400, "U001", "이미 존재하는 아이디 입니다."),
    EXISTS_PHONE_NUMBER(400, "U002", "이미 존재하는 번호 입니다."),
    UNSIGN_USERNAME_OR_PHONE(400, "U003", "가입되지 않은 username 또는 번호입니다."),
    WRONG_PASSWORD(400, "U004", "잘못된 비밀번호 입니다."),
    WITHOUT_AUTHORIZATION_TOKEN(403,"U005", "권한 정보가 없는 토큰입니다."),
    UNREGISTERD_PHONE_NUMBER(400,"U006", "등록되지 않은 핸드폰 번호입니다."),

    INVALID_REFRESH_TOKEN(400,"U007", "검증되지 않은 토큰입니다."),
    NOT_FOUND_USER(400,"U008", "유저를 찾지 못했습니다."),

    /**
     * 제품
     */
    NOT_FOUNT_BARCODE(400, "I001", "존재하지 않는 바코드입니다.")
    ;

    private final String code;
    private final String message;
    private int status;

    BaseCode(final int status, final String code, final String message) {
        this.status = status;
        this.message = message;
        this.code = code;
    }
}
