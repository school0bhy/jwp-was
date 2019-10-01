package webserver.httphandler.exception;

public class ResourceNotFoundException extends RuntimeException {
    private static final String RESOURCE_NOT_FOUND_EXCEPTION_MESSAGE = "Resource를 찾을 수 없습니다.";

    public ResourceNotFoundException(Throwable cause) {
        super(RESOURCE_NOT_FOUND_EXCEPTION_MESSAGE, cause);
    }
}
