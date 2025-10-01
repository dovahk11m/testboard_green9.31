package choong.board._core;

import choong.board._core.exceptions.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.ui.Model;

// 모든 컨트롤러에서 발생하는 예외 처리를 이 클래스에서 처리
@ControllerAdvice
public class MyExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(MyExceptionHandler.class);

    //공통로직
    private void logErrorDetails(Exception e,
                                 HttpServletRequest request,
                                 String errorType) {
        log.warn("=== {} 에러 발생 ===", errorType);
        log.warn("요청 URL : {}", request.getRequestURL());
        log.warn("오류 메시지: {}", e.getMessage());
        log.warn("User-Agent: {}", request.getHeader("User-Agent"));
        request.setAttribute("msg", e.getMessage());
    }

    //400 Bad Request
    @ExceptionHandler(Exception400.class)
    public String ex400(Exception400 e, HttpServletRequest request, Model model) {
        model.addAttribute("msg", e.getMessage());
        logErrorDetails(e, request, "400 Bad Request");
        return "err/400";
    }

    //401 Bad Request
    @ExceptionHandler(Exception401.class)
    public String ex401(Exception401 e, HttpServletRequest request, HttpSession session, Model model) {
        model.addAttribute("msg", e.getMessage());
        logErrorDetails(e, request, "401 Unauthorized");
        session.invalidate();
        return "redirect:/login-form";
    }

    //403 Bad Request
    @ExceptionHandler(Exception403.class)
    public String ex403(Exception403 e, HttpServletRequest request, Model model) {
        model.addAttribute("msg", e.getMessage());
        logErrorDetails(e, request, "403 Forbidden");
        return "err/403";
    }

    //404 Bad Request
    @ExceptionHandler(Exception404.class)
    public String ex404(Exception404 e, HttpServletRequest request, Model model) {
        model.addAttribute("msg", e.getMessage());
        logErrorDetails(e, request, "404 Not Found");
        return "err/404";
    }

    //500 Bad Request
    @ExceptionHandler(Exception500.class)
    public String ex500(Exception500 e, HttpServletRequest request, Model model) {
        model.addAttribute("msg", e.getMessage());
        logErrorDetails(e, request, "500 Internal Server Error");
        return "err/500";
    }

    //기타
    @ExceptionHandler(RuntimeException.class)
    public String handleRuntimeException(RuntimeException e, HttpServletRequest request, Model model) {
        model.addAttribute("msg", e.getMessage());
        logErrorDetails(e, request, "예상 못한 런타임 에러 발생");
        request.setAttribute("msg", "시스템 오류: 관리자에게 문의하세요");
        return "err/500";
    }
}
