package com.schwarz.libraryapp.service;

import com.schwarz.libraryapp.entity.Customer;
import com.schwarz.libraryapp.entity.Role;
import com.schwarz.libraryapp.entity.RoleName;
import com.schwarz.libraryapp.exception.AppException;
import com.schwarz.libraryapp.payload.SignUpRequest;
import com.schwarz.libraryapp.repository.CustomerRepository;
import com.schwarz.libraryapp.repository.RoleRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;


@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public CustomerService(CustomerRepository customerRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.customerRepository = customerRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Customer getCustomer(Long id){

        Customer customer=customerRepository.findById(id).orElseThrow(()-> new IllegalStateException("customer not found"));

        return customer;

    }
    public List<Customer> getAllCustomer() {
        return customerRepository.findAll();
    }


    public Customer saveCustomer(SignUpRequest signUpRequest) {

        Customer customer = new Customer(signUpRequest.getName(),
                signUpRequest.getEmail(), signUpRequest.getPassword());

        customer.setPassword(passwordEncoder.encode(customer.getPassword()));

        Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
                .orElseThrow(() -> new AppException("User Role not set."));

        customer.setRoles(Collections.singleton(userRole));


        return customerRepository.save(customer);
    }

    public String updateCustomer(Long id, Customer customer) {

        Optional<Customer> result =customerRepository.findById(id);
        result.ifPresent(cus -> {
            cus.setName(customer.getName());
            cus.setEmail(customer.getEmail());
            cus.setPassword(customer.getPassword());
            customerRepository.save(cus);
        });

        return "save successfully";
    }

    public String deleteCustomer(Long id) {

        customerRepository.deleteById(id);
        return "delete customer successfully";
    }

    public boolean existsByEmail(String email) {
        return customerRepository.existsByEmail(email);
    }

    public boolean existById(Long id) {
        return customerRepository.existsById(id);
    }
}
