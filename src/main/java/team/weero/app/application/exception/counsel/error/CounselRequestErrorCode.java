package team.weero.app.application.exception.counsel.error;

import team.weero.app.global.error.ErrorProperty;

public enum CounselRequestErrorCode implements ErrorProperty {
    COUNSEL_REQUEST_NOT_FOUND(404, "Counsel Request Not Found", 1),
    FORBIDDEN_COUNSEL_REQUEST_ACCESS(403, "Forbidden Counsel Request Access", 1),
    INVALID_COUNSEL_REQUEST_STATUS(400, "Invalid Counsel Request Status", 1);

    private final int status;
    private final String message;
    private final int sequence;

    CounselRequestErrorCode(int status, String message, int sequence) {
        this.status = status;
        this.message = message;
        this.sequence = sequence;
    }

    @Override
    public int status() {
        return status;
    }

    @Override
    public String message() {
        return message;
    }

    @Override
    public String code() {
        return "COUNSEL_REQUEST-" + status + "-" + sequence;
    }
}
