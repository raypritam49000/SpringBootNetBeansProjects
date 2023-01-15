package com.employee.management.system.service;

import com.employee.management.system.entity.Customer;
import java.util.List;

public interface CustomerService {
    public List < Customer > getCustomers();
    public void saveCustomer(Customer theCustomer);
    public Customer getCustomer(int theId);
    public void deleteCustomer(int theId);
}
