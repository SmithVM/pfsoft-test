package net.mnp.spring.view;

import net.mnp.spring.model.User;
import net.mnp.spring.service.UserService;
import net.mnp.spring.service.security.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Dmitry Natalenko on 27.10.2015.
 */
@Controller
public class AuthController {

    @Autowired
    private CustomUserDetailService customUserDetailService;

    @Autowired
    private RememberMeServices rememberMeServices;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> doRegistration(HttpServletRequest request,
                                                 HttpServletResponse response,
                                                 @RequestBody User newUser) {
        User user = userService.createUser(newUser);
        String login = user.getLogin();
        String password =  user.getPassword();
        try {
            UserDetails userDetails = customUserDetailService.loadUserByUsername(login);
            Authentication auth = new UsernamePasswordAuthenticationToken(
                    login, password, userDetails.getAuthorities());

            if(auth.isAuthenticated()) {
                SecurityContextHolder.getContext().setAuthentication(auth);
                rememberMeServices.loginSuccess(request, response, auth);
                return new ResponseEntity<String>("OK", HttpStatus.OK);
            }
        } catch (Exception e) {
            SecurityContextHolder.getContext().setAuthentication(null);
        }
        return new ResponseEntity<String>("ERROR", HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login() {
        return new ModelAndView();
    }


    @RequestMapping(value = "/403")
    public String accesssDenied() {
        return "403.page";
    }

    @RequestMapping(value = "/home")
    public String method() {
        return "home.page";
    }
}
