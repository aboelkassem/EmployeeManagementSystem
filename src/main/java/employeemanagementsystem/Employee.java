/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package employeemanagementsystem;

import java.text.SimpleDateFormat;
import java.util.Date;



/**
 *
 * @author Mohamed
 */
public class Employee {
    
    private int Emp_No;
    private String Name;
    private int Salary;
    private Date DateOfBirth;
    private String Department;

    public Employee(int Emp_No, String Name, int Salary, String DateOfBirth, String Department) {
        this.Emp_No = Emp_No;
        this.Name = Name;
        this.Salary = Salary;
        this.DateOfBirth = new Date(DateOfBirth);
        this.Department = Department;
    }

    public String getDateOfBirth() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
        return formatter.format(DateOfBirth);
    }

    public String getDepartment() {
        return Department;
    }

    public int getEmp_No() {
        return Emp_No;
    }

    public String getName() {
        return Name;
    }

    public int getSalary() {
        return Salary;
    }    

    public void setEmp_No(int Emp_No) {
        this.Emp_No = Emp_No;
    }
}
