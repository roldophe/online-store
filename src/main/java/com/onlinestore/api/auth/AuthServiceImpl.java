package com.onlinestore.api.auth;

import com.onlinestore.api.auth.web.LoginDto;
import com.onlinestore.api.auth.web.RegisterDto;
import com.onlinestore.api.auth.web.VerifyDto;
import com.onlinestore.api.mail.Mail;
import com.onlinestore.api.mail.MailService;
import com.onlinestore.api.user.User;
import com.onlinestore.api.user.UserService;
import com.onlinestore.api.user.web.NewUserDto;
import com.onlinestore.utils.RandomUtil;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserService userService;
    private final AuthMapper authMapper;
    private final AuthRepository authRepository;
    private final MailService mailService;
    @Value("${spring.mail.username}")
    private String adminMail;

    @Transactional
    @Override
    public void register(RegisterDto registerDto) throws MessagingException {
        NewUserDto newUserDto = authMapper.mapRegisterDtoToNewUserDto(registerDto);
        userService.createNewUser(newUserDto);
        String verifiedCode = RandomUtil.generateCode();
        //Store verifiedCode in database
        authRepository.updateVerifiedCode(registerDto.username(), verifiedCode);
        //Send verifiedCode via email
        Mail<String> verifiedMail = new Mail<>();
        verifiedMail.setSubject("Email Verification");
        verifiedMail.setSender(adminMail);
        verifiedMail.setReceiver(newUserDto.email());
        verifiedMail.setTemplate("auth/verify-mail");
        verifiedMail.setMetaData(verifiedCode);

        mailService.sendMail(verifiedMail);
    }

    @Override
    public void verify(VerifyDto verifyDto) {
        User verifiedUser = authRepository.findByEmailAndVerifiedCodeIsAndIsDeletedFalse(verifyDto.email(), verifyDto.verifiedCode())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.UNAUTHORIZED,
                        "Verify email has been failed..!"
                ));
        verifiedUser.setIsVerified(true);
        verifiedUser.setVerifiedCode(null);
        authRepository.save(verifiedUser);
    }

    @Override
    public void login(LoginDto loginDto) {

    }

}
