package conn.ra.security.UserDetail;

import conn.ra.model.entity.User;
import conn.ra.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserLoggedIn {
    private final UserService userService;

    public User getUserLoggedIn() {
        Authentication authentication = SecurityContextHolder.getContext ().getAuthentication ();
        if (authentication != null && authentication.isAuthenticated ()) {
            UserPrincipal userPrinciple = (UserPrincipal) authentication.getPrincipal ();
            return userService.findById ( userPrinciple.getUser ().getId () );
        } else {
            return null;
        }
    }
}
