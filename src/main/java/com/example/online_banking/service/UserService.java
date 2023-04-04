package com.example.online_banking.service;

import com.example.online_banking.exception.DataInvalidException;
import com.example.online_banking.model.User;
import com.example.online_banking.repository.UserRepository;
import com.example.online_banking.repository.custom.UserRepositoryCustom;
import com.example.online_banking.rest.model.ChangeUserStatusInput;
import com.example.online_banking.rest.model.ErrorCode;
import com.example.online_banking.rest.model.Page;
import com.example.online_banking.rest.model.PagingRequest;
import com.example.online_banking.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepositoryCustom userRepositoryCustom;
    @Autowired
    private UserRepository userRepository;

    // manage customer
    public Page<User> getCustomerList(PagingRequest pagingRequest) {
        Integer total = userRepositoryCustom.getTotalUser(pagingRequest);
        List<User> userList = userRepositoryCustom.getUserList(pagingRequest);
        Page<User> page = new Page<>(userList);
        page.setRecordsFiltered(userList.size());
        page.setRecordsTotal(total);
        page.setDraw(pagingRequest.getDraw());
        return page;
    }

    //TODO: manage admin
    public Page<User> getAdminList(PagingRequest pagingRequest) {
        Integer total = userRepositoryCustom.getTotalAdmin(pagingRequest);
        List<User> adminList = userRepositoryCustom.getAdminList(pagingRequest);
        Page<User> page = new Page<>(adminList);
        page.setRecordsFiltered(adminList.size());
        page.setRecordsTotal(total);
        page.setDraw(pagingRequest.getDraw());
        return page;
    }

//    public Page<User> getAdminById(PagingRequest pagingRequest) {
//        return userRepositoryCustom.
//    }

    public Long deleteUser(Long id) throws DataInvalidException {
        User user = userRepository.getById(id);
        if (user.getStatus() == null || user.getStatus().equals(Constants.STATUS_INACTIVE)) {
            throw new DataInvalidException(ErrorCode.USER_NOT_EXIST);
        }
        user.setStatus(Constants.STATUS_INACTIVE);
        userRepository.save(user);
        return user.getId();
    }

    public Long changeStatus(ChangeUserStatusInput input) throws DataInvalidException {
        User user = userRepository.getById(input.getId());
        if (user.getStatus() == null || user.getStatus().equals(Constants.STATUS_INACTIVE)) {
            throw new DataInvalidException(ErrorCode.USER_NOT_EXIST);
        }
        user.setStatus(input.getStatus());
        userRepository.save(user);
        return user.getId();
    }
}
