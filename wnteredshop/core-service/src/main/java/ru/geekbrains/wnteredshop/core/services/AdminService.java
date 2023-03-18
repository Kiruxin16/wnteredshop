package ru.geekbrains.wnteredshop.core.services;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class AdminService {

    public boolean isAccessGranted(String name, String roles){
        if(name !=null){
            if(Arrays.stream(roles.split(" ")).anyMatch(role -> role.equals("ROLE_ADMIN"))){
                return true;
            }
        }
        return false;
    }
}
