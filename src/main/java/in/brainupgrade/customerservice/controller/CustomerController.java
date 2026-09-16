package in.brainupgrade.customerservice.controller;

import java.net.BindException;
import java.time.DateTimeException;
import java.util.List;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import in.brainupgrade.customerservice.feign.AuthorizationFeign;
import in.brainupgrade.customerservice.model.CustomerEntity;
import in.brainupgrade.customerservice.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
//@CrossOrigin(origins = "http://localhost:4200")
@RestController
@CrossOrigin(origins = "*")
public class CustomerController {
	private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(CustomerController.class);
	@Autowired
	private CustomerService customerService;
	@Autowired
	AuthorizationFeign authorizationFeign;

	/**
	 * Creates a customer
	 * 
	 * @param token
	 * @param customer
	 * @param bindingResult
	 * @return
	 * @throws DateTimeException
	 * @throws BindException
	 */
	@PostMapping("/createCustomer")
	@Operation(summary = "Create a new customer", description = "Create a new customer with the details provided")
	public ResponseEntity<?> createCustomer(@Parameter(description = "Token for authentication passed in header", required = true) @RequestHeader("Authorization") String token, @Parameter(description = "Customer details for creating a customer", required = true) @Valid @RequestBody CustomerEntity customer, BindingResult bindingResult) throws DateTimeException, BindException {
		if (bindingResult.hasErrors()) {
			throw new BindException();
		}
		customerService.hasEmployeePermission(token);
		CustomerEntity customerEntity = customerService.createCustomer(token, customer);
		if (customerEntity != null) return new ResponseEntity<>(customerEntity, HttpStatus.CREATED);
		 else return new ResponseEntity<>("Customer Creation is UNSUCCESSFUL", HttpStatus.NOT_ACCEPTABLE);
	}

	/**
	 * Save customer data into database
	 * 
	 * @param token
	 * @param customer
	 * @return
	 */
	@PostMapping("/saveCustomer")
	@Operation(summary = "Saves customer into the database", description = "Saves a customer after getting the details ")
	public CustomerEntity saveCustomer(@Parameter(description = "token for authentication passed in header", required = true) @RequestHeader("Authorization") String token, @Parameter(description = "customer details", required = true) @Valid @RequestBody CustomerEntity customer) {
		customerService.hasEmployeePermission(token);
		CustomerEntity customerEntity = customerService.saveCustomer(token, customer);
		if (customerEntity != null) return customerEntity;
		 else return null;
	}

	/**
	 * Updates the customer in database
	 * 
	 * @param token
	 * @param customer
	 * @return
	 */
	@PutMapping("/updateCustomer")
	@Operation(summary = "Update Customer", description = "Updates a customer after making the desired changes ")
	public CustomerEntity updateCustomer(@Parameter(description = "token for authentication passed in header", required = true) @RequestHeader("Authorization") String token, @Parameter(description = "updated customer details", required = true) @Valid @RequestBody CustomerEntity customer) {
		customerService.hasEmployeePermission(token);
		return customerService.updateCustomer(token, customer);
	}

	/**
	 * Gets a customer details given it's userid
	 * 
	 * @param token
	 * @param id
	 * @return
	 */
	@GetMapping("/getCustomerDetails/{id}")
	@Operation(summary = "Get customer details given their userid ", description = "get the details of a specific customer ")
	public ResponseEntity<?> getCustomerDetails(@Parameter(description = "token for authentication passed in header", required = true) @RequestHeader("Authorization") String token, @Parameter(description = "Id of customer whose detail is to be retrieved", required = true) @PathVariable String id) {
		customerService.hasPermission(token);
		CustomerEntity toReturnCustomerDetails = customerService.getCustomerDetail(token, id);
		if (toReturnCustomerDetails == null) return new ResponseEntity<>("Customer Userid " + id + " DOES NOT EXISTS", HttpStatus.NOT_ACCEPTABLE);
		toReturnCustomerDetails.setPassword(null);
		return new ResponseEntity<>(toReturnCustomerDetails, HttpStatus.OK);
	}

	/**
	 * Deletes a customer from database
	 * 
	 * @param token
	 * @param id
	 * @return
	 */
	@DeleteMapping("deleteCustomer/{id}")
	@Operation(summary = "Delete customer given its userid", description = "Deletes a specific customer ")
	@ResponseStatus(code = HttpStatus.OK)
	public ResponseEntity<?> deleteCustomer(@Parameter(description = "token for authentication passed in header", required = true) @RequestHeader("Authorization") String token, @Parameter(description = "id of customer that is to be deleted", required = true) @PathVariable String id) {
		customerService.hasEmployeePermission(token);
		CustomerEntity checkCustomerIdExists = null;
		checkCustomerIdExists = customerService.getCustomerDetail(token, id);
		if (checkCustomerIdExists == null) {
			return new ResponseEntity<>("Customer Userid DOES NOT EXISTS", HttpStatus.NOT_ACCEPTABLE);
		}
		log.info("Starting deletion of --> {}", id);
		customerService.deleteCustomer(id);
		log.info("Deleted");
		return new ResponseEntity<>("Deleted SUCCESSFULLY", HttpStatus.OK);
	}

	@GetMapping("/getCustomers")
	public ResponseEntity<?> getCustomers(@RequestHeader("Authorization") String token) {
		customerService.hasPermission(token);
		List<CustomerEntity> customers = customerService.getCustomers(token);
		if (customers == null) return new ResponseEntity<>("No customers available", HttpStatus.NOT_ACCEPTABLE);
		return new ResponseEntity<>(customers, HttpStatus.OK);
	}
}
