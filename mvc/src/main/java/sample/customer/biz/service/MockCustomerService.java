package sample.customer.biz.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Service;

import sample.customer.biz.domain.Customer;

@Service
public class MockCustomerService implements CustomerService {
    private Map<Integer, Customer> customerMap = new LinkedHashMap<Integer, Customer>();

    private int nextId = 1;

    private boolean isExists(int id) {
        return customerMap.containsKey(id);
    }

    public List<Customer> findAll() {
        List<Customer> list = new LinkedList<Customer>();
        for (Customer customer : customerMap.values()) {
            list.add(newCustomer(customer));
        }
        return list;
    }

    public Customer findById(int id) throws DataNotFoundException {
        if (!isExists(id)) {
            throw new DataNotFoundException();
        }
        return newCustomer(customerMap.get(id));
    }

    public Customer register(Customer customer) {
        customer.setId(nextId++);
        customerMap.put(customer.getId(), newCustomer(customer));

        return customer;
    }

    public void update(Customer customer) throws DataNotFoundException {
        if (!isExists(customer.getId())) {
            throw new DataNotFoundException();
        }
        customerMap.put(customer.getId(), newCustomer(customer));
    }

    public void delete(int id) throws DataNotFoundException {
        if (!isExists(id)) {
            throw new DataNotFoundException();
        }
        customerMap.remove(id);
    }

    @PostConstruct
    public void initCustomer() {
        nextId = 1;

        register(new Customer("太郎", "taro@aa.bb.cc", date("19750111"), 1));
        register(new Customer("次郎", "jiro@aa.bb.cc", date("19760212"), 2));
        register(new Customer("三郎", "sabu@aa.bb.cc", date("19770313"), 3));
    }

    private static Date date(String dateString) {
        DateFormat df = new SimpleDateFormat("yyyyMMdd");
        try {
            return df.parse(dateString);
        } catch (ParseException e) {
            throw new RuntimeException("yyyyMMdd format faild", e);
        }
    }

    private Customer newCustomer(Customer orig) {
        Customer dest = new Customer();
        try {
            BeanUtils.copyProperties(dest, orig);
        } catch (Exception e) {
            throw new RuntimeException("Exception threw in Customer copy ", e);
        }
        return dest;
    }
}
