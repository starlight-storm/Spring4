package sample.customer.web.controller;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import java.nio.charset.Charset;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import sample.customer.biz.domain.Customer;
import sample.customer.biz.service.CustomerService;
import sample.customer.biz.service.DataNotFoundException;

@RestController
@RequestMapping("/api/customer")
public class CustomerRestController {

    @Autowired
    private CustomerService customerService;

    @RequestMapping(method = POST)
    @ResponseStatus(HttpStatus.OK)
    public String register(@RequestBody Customer customer) {
        customerService.register(customer);
        return "OK";
    }

    @RequestMapping(value = "/{customerId}", method = GET)
    public ResponseEntity<Customer> findById(@PathVariable int customerId)
                                        throws DataNotFoundException {
        Customer customer = customerService.findById(customerId);

        return ResponseEntity.ok()
                .header("My-Header", "MyHeaderValue")
                .contentType(new MediaType("text", "xml", Charset.forName("UTF-8")))
                .body(customer);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleException(DataNotFoundException e) {
        return "customer is not found";
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleException(Exception e) {
        return "server error";
    }
}
