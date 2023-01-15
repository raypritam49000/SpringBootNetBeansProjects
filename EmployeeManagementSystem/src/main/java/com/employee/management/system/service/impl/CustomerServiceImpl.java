package com.employee.management.system.service.impl;

import com.employee.management.system.entity.Customer;
import com.employee.management.system.repository.CustomerRepository;
import com.employee.management.system.service.CustomerService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public void saveCustomer(Customer customer) {
       this.customerRepository.save(customer);
    }

    @Override
    public Customer getCustomer(int theId) {
      return this.customerRepository.findById(theId).get();
    }

    @Override
    public void deleteCustomer(int theId) {
       this.customerRepository.delete(this.customerRepository.findById(theId).get());
    }

}
