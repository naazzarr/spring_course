package spring.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import spring.spring.entity.User;
import spring.spring.repository.UserRepository;

import java.util.List;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository repository;

    @Autowired
    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public User findUserById(Long id) {
        return repository.findById(id).orElse(new User());
    }

    public List<User> findAllByOrderByUsernameAsc() {
        return repository.findAllByOrderByUsernameAsc();
    }

    public User findByUsername(String username) {
        return repository.findByUsername(username);
    }

    public void saveUser(User user) {
        repository.save(user);
    }

    public void updateUser(User user) {
        repository.save(user);
    }

    public void deleteUserById(Long id) {
        repository.deleteById(id);
    }

    public void deleteAllUsers() {
        repository.deleteAll();
    }

    public List<User> findAllUsers() {
        return repository.findAll();
    }

    public void encryptPassword(User user) {
        String pwd = user.getPassword();
        BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
        String hashPwd = bc.encode(pwd);
        user.setPassword(hashPwd);

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User curruser = repository.findByUsername(username);

        UserDetails user = new org.springframework.security.core.userdetails.User(username, curruser.getPassword(), true,
                true, true, true, AuthorityUtils.createAuthorityList(curruser.getRole()));

        System.out.println("ROLE: " + curruser.getRole());
        return user;
    }

}
