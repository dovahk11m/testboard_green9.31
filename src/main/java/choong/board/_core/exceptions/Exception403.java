package choong.board._core.exceptions;

// 400 Bad Request 상황에서 사용할 커스텀 예외 클래스
public class Exception403 extends RuntimeException {

    // 에러 메세지로 사용할 문자열을 super 클래스에게 전달
    public Exception403(String message) {
        super(message);
    }
}