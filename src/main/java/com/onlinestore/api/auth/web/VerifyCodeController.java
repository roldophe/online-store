package com.onlinestore.api.auth.web;

import com.onlinestore.api.auth.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class VerifyCodeController {
    private final AuthService authService;

    @GetMapping("/auth/verify")
    public String viewVerifiedCode(ModelMap modelMap, VerifyCodeDto verifyCOdeDto,
                                   @RequestParam String email) {
        modelMap.addAttribute("verifiedCodeDto", verifyCOdeDto);
        modelMap.addAttribute("email", email);
        return "auth/verify-code";
    }

    @PostMapping("/auth/verify")
    public String doVerifiedCode(VerifyCodeDto verifyCodeDto,
                                 @RequestParam String email) {
        String sixDigits = verifyCodeDto.sexDigits();

        authService.verify(new VerifyDto(email, sixDigits));
        return "auth/verify-succeed";
    }
}


