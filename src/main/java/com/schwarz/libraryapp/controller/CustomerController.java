package com.schwarz.libraryapp.controller;


import com.schwarz.libraryapp.entity.Customer;
import com.schwarz.libraryapp.payload.ApiResponse;
import com.schwarz.libraryapp.payload.SignUpRequest;
import com.schwarz.libraryapp.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RequestMapping( "/api/customer/")
@RestController
@Controller
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService){
        this.customerService = customerService;
    }

    @GetMapping(path = "/allcustomer")
    public ResponseEntity<?> getAllCustomer(){
        return ResponseEntity.ok(customerService.getAllCustomer());
    }

    @GetMapping(path = "/getcustomer/{id}")
    public ResponseEntity<?> getCustomer(@PathVariable Long id){
        if(!customerService.existById(id)){
            return new ResponseEntity(new ApiResponse(false,"this customer doesn't exist"), HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(customerService.getCustomer(id));
    }

    @PostMapping(path = "/addcustomer")
    public ResponseEntity<?> addCustomer(@Valid @RequestBody SignUpRequest signUpRequest){

        return ResponseEntity.ok(customerService.saveCustomer(signUpRequest));
    }

    @PutMapping("/updatecustomer/{id}")
    public ResponseEntity<?> updateCustomer(@PathVariable Long id, @RequestBody Customer customer){
        if(!customerService.existById(id)){
            return new ResponseEntity(new ApiResponse(false,"this customer doesn't exist"), HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(customerService.updateCustomer(id,customer));
    }

    @DeleteMapping(path = "/deletecustomer/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable Long id){
        if(!customerService.existById(id)){
            return new ResponseEntity(new ApiResponse(false,"this customer doesn't exist"), HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(customerService.deleteCustomer(id));
    }


}
