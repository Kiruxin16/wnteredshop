package ru.geekbrains.wnteredshop.core.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.geekbrains.wnteredshop.api.AdminResponse;
import ru.geekbrains.wnteredshop.api.AppError;
import ru.geekbrains.wnteredshop.core.services.AdminService;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @GetMapping
    ResponseEntity<?> adminRequest(@RequestHeader(name = "username",required = false) String username,
                                   @RequestHeader(name = "roles",required = false) String roles){
        if(adminService.isAccessGranted(username,roles)){
            return new ResponseEntity<>(new AdminResponse("Admin"), HttpStatus.OK);
        }
        return new ResponseEntity<>(new AppError(HttpStatus.FORBIDDEN.value(),"Решил поиграть в бога ?"),HttpStatus.FORBIDDEN);
    }

}
