package edu.trs.service.security;

import edu.trs.model.ApplicationUser;
import edu.trs.model.security.SecurityUser;
import edu.trs.service.ApplicationUserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ApplicationUserDetailsService implements UserDetailsService {

    private final ApplicationUserService applicationUserService;

    public ApplicationUserDetailsService(ApplicationUserService applicationUserService) {
        this.applicationUserService = applicationUserService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ApplicationUser user = applicationUserService.findByUserName(username);
        if(user == null){
            throw new UsernameNotFoundException("User " + username + " not found");
        }
        return new SecurityUser(user);
    }
}
