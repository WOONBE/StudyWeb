package StudyWeb.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class TimerController {
    @GetMapping("/timer")
    public String timer(){
        return "";//front 경로
    }
}
