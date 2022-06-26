package com.example.demo.api.employee;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.employee.Employee;
import com.example.demo.model.employee.EmployeeResponse;
import com.example.demo.model.response.OperationResponse;
import com.example.demo.model.response.OperationResponse.ResponseStatusEnum;
import com.example.demo.repo.EmployeeRepo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@Transactional
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(tags = {"Employee"})
public class EmployeeController {

	@Autowired private EmployeeRepo employeeRepo;

	@ApiOperation(value = "List of employees", response = EmployeeResponse.class)
	@RequestMapping(value = "/employees", method = RequestMethod.GET)
	public EmployeeResponse getEmployeesByPage(
			@ApiParam(value = ""    )               @RequestParam(value = "page"  ,  defaultValue="0"   ,  required = false) Integer page,
			@ApiParam(value = "between 1 to 1000" ) @RequestParam(value = "size"  ,  defaultValue="20"  ,  required = false) Integer size,
			@RequestParam(value = "employeeid"  , required = false) Integer employeeId,
			Pageable pageable
			) {
		EmployeeResponse resp = new EmployeeResponse();
		Employee qry = new Employee();
		if (employeeId != null)  { qry.setId(employeeId); }

		Page<Employee> pg = employeeRepo.findAll(org.springframework.data.domain.Example.of(qry), pageable);
		resp.setPageStats(pg, true);
		resp.setItems(pg.getContent());
		return resp;
	}

	@ApiOperation(value = "Add new employee", response = OperationResponse.class)
	@RequestMapping(value = "/employees", method = RequestMethod.POST, produces = {"application/json"})
	public OperationResponse addNewEmployee(@RequestBody Employee employee, HttpServletRequest req) {
		OperationResponse resp = new OperationResponse();
		if (this.employeeRepo.existsById(employee.getId()) ){
			resp.setOperationStatus(ResponseStatusEnum.ERROR);
			resp.setOperationMessage("Unable to add Employee - Employee allready exist ");
		}
		else{
			this.employeeRepo.save(employee);
			resp.setOperationStatus(ResponseStatusEnum.SUCCESS);
			resp.setOperationMessage("Employee Added");
		}
		return resp;
	}


	@ApiOperation(value = "Delete a Employee", response = OperationResponse.class)
	@RequestMapping(value = "/Employees/{employeeId}", method = RequestMethod.DELETE, produces = {"application/json"})
	public OperationResponse deleteEmployee(@PathVariable("employeeId") Integer employeeId, HttpServletRequest req) {
		OperationResponse resp = new OperationResponse();
		try {
			if (this.employeeRepo.existsById(employeeId) ){
				this.employeeRepo.deleteById(employeeId);
				resp.setOperationStatus(ResponseStatusEnum.SUCCESS);
				resp.setOperationMessage("Employee Deleted");
			}
			else{
				resp.setOperationStatus(ResponseStatusEnum.ERROR);
				resp.setOperationMessage("No Employee Exist");
			}
		}
		catch ( Exception ge ){
			System.out.println("========= MRIN GENERIC EXCEPTION ============");
			resp.setOperationStatus(ResponseStatusEnum.ERROR);
			resp.setOperationMessage(ge.getMessage());
		}

		return resp;
	}
}
