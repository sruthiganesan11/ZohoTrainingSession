package com.guvi.zoho.service.impl;

import com.guvi.zoho.dto.EmployeeDto;
import com.guvi.zoho.entities.Employee;
import com.guvi.zoho.exception.EmailAlreadyExistException;
import com.guvi.zoho.exception.ResourceNotFoundException;
import com.guvi.zoho.mapper.EmployeeMapper;
import com.guvi.zoho.repo.EmployeeRepo;
import com.guvi.zoho.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeImpl implements EmployeeService {

    private EmployeeRepo employeeRepo;
    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {

        Optional<Employee> optionalEmployee = employeeRepo.findByEmail(employeeDto.getEmail());

        if (optionalEmployee.isPresent()) {
            throw new EmailAlreadyExistException("Email Already Exist");
        }

        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
        Employee savedEmployee = employeeRepo.save(employee);

        return EmployeeMapper.mapToEmployeeDto(savedEmployee);
    }

    @Override
    public EmployeeDto getEmployeeById(Long employeeId) {

        Employee employee = employeeRepo.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException
                        ("Employee", "id", employeeId));
        return EmployeeMapper.mapToEmployeeDto(employee);
    }

    @Override
    public List<EmployeeDto> getAllEmployee() {
        List<Employee> employees = employeeRepo.findAll();
        return employees.stream().map(EmployeeMapper::mapToEmployeeDto)
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDto updateEmployee(Long employeeId, EmployeeDto updatedEmployee) {
        Employee employee = employeeRepo.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException
                        ("Employee", "id", employeeId));

        Optional<Employee> optionalEmployee = employeeRepo.findByEmail(updatedEmployee.getEmail());

        if (optionalEmployee.isPresent()) {
            throw new EmailAlreadyExistException("Email Already Exist");
        }

        employee.setFirstName(updatedEmployee.getFirstName());
        employee.setLastName(updatedEmployee.getLastName());
        employee.setEmail(updatedEmployee.getEmail());
        employee.setDepartment(updatedEmployee.getDepartment());

        Employee updatedEmployeeObj = employeeRepo.save(employee);
        return EmployeeMapper.mapToEmployeeDto(updatedEmployeeObj);
    }

    @Override
    public void deleteEmployee(Long employeeId) {

        Employee employee = employeeRepo.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException
                        ("Employee", "id", employeeId));
        employeeRepo.deleteById(employeeId);
    }
}
