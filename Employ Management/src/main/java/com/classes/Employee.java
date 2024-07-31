package com.classes;

public class Employee {
	
		private String name;
		private String password;
		private String Role;
		private String project;
		private int Admin;
		private String emp_id;
	
	public Employee(String emp_id,String username,String password,String Role,String project,int Admin) {
			
			this.name = username;
			this.password = password;
			this.Role= Role;
			this.project = project;
			this.Admin = Admin;
			this.emp_id =emp_id;

			
		}

	
	public String getEmp_id() {
        return this.emp_id;
    }

    public void setEmp_id(String emp_id) {
        this.emp_id = emp_id;
    } 
    
    public String getName() {
        return this.name;
    }

    public void setName(String username) {
        this.name = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return this.Role;
    }

    public void setRole(String Role) {
        this.Role = Role;
    }

    public String getProject() {
        return this.project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public int getAdmin() {
        return this.Admin;
    }

    public void setAdmin(int Admin) {
        this.Admin = Admin;
    }
		
		
	
		
		
	

}
