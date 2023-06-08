package com.team5.plateful.services;

import com.team5.plateful.models.PlatefulUserDetails;
import com.team5.plateful.models.User;
import com.team5.plateful.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class PlatefulUserDetailsService implements UserDetailsService {

    public final UserRepository usersDao;

    public PlatefulUserDetailsService(UserRepository usersDao){
        this.usersDao = usersDao;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = usersDao.findUserByUsername(username);
        if (user == null){
            throw new UsernameNotFoundException("User details not found for user: " + username);
        } else {
            return new PlatefulUserDetails(user.getUsername(), user.getEmail(), user.getPassword(), user.getAvatar_url());
        }
    }
}
