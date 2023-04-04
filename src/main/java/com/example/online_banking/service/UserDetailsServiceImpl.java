package com.example.online_banking.service;

import com.example.online_banking.repository.UserRepository;
import com.example.online_banking.repository.UserRoleRepository;
import com.example.online_banking.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        // đầu tiên mình query xuống database xem có user đó không
        com.example.online_banking.model.User appUser;
        appUser = this.userRepository.findByUsername(userName);

        // Nếu không tìm thấy User thì mình thông báo lỗi
        if (appUser == null || Constants.STATUS_INACTIVE.equals(appUser.getStatus())) {
            throw new UsernameNotFoundException("User " + userName + " was not found in the database");
        }
        if (Constants.STATUS_LOCKED.equals(appUser.getStatus())) {
            throw new UsernameNotFoundException("User " + userName + " is locked");
        }

        // Khi đã có user rồi thì mình query xem user đó có những quyền gì (Admin hay User)
        // [ROLE_USER, ROLE_ADMIN,..]
        List<String> roleNames = this.userRoleRepository.findRoleNameByUser(appUser.getId());

        // Dựa vào list quyền trả về mình tạo đối tượng GrantedAuthority của spring cho quyền đó
        List<GrantedAuthority> grantList = new ArrayList<>();
        if (roleNames != null) {
            for (String role : roleNames) {
                // ROLE_USER, ROLE_ADMIN,..
                GrantedAuthority authority = new SimpleGrantedAuthority(role);
                grantList.add(authority);
            }
        }

        // Cuối cùng mình tạo đối tượng UserDetails của Spring và mình cung cấp cá thông số như tên, password và quyền
        // Đối tượng userDetails sẽ chứa đựng các thông tin cần thiết về user từ đó giúp Spring Security quản lý được phân quyền như ta đã
        // cấu hình trong bước 4 method configure
        UserDetails userDetails = (UserDetails) new User(appUser.getUsername(),
                appUser.getEncryptedPassword(), grantList);

        return userDetails;
    }
}
