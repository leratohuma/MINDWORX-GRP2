package com.mindworx.alumnibackend.model.users.administrator;


import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;


import com.mindworx.alumnibackend.model.users.MindworxUser;
import com.mindworx.alumnibackend.model.users.Usertype;

@Entity(name = "Adminstrator")
public class Adminstrator extends MindworxUser {

    //Known fields for an Administrator
    
    @Column(
        name = "Admin Dept. "
    )
    private String  adminDept;


    //Constructors to initialize the fields
    public Adminstrator(int sSID, String firstName, String lastName, String userName, String initials,
            LocalDate dateofBirth, boolean gender, String email, String password, Usertype typeofuser, boolean active,
             String adminDept) {
        super(sSID, firstName, lastName, userName, initials, dateofBirth, gender, email, password, typeofuser, active);
   
        this.adminDept = adminDept;
    }

    public Adminstrator() {
    }


    //Accessor methods

    public String getAdminDept() {
        return adminDept;
    }

    public void setAdminDept(String adminDept) {
        this.adminDept = adminDept;
    }

    //string for admin details
    @Override
    public String toString() {
        return "Adminstrator [ adminDept=" + adminDept + "]";
    }

    
}
