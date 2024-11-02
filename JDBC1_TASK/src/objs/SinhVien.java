/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objs;

/**
 *
 * @author Moment
 */
public class SinhVien {
    private String id,name,classId;
    private double gpa;
    public SinhVien(String id,String name,String classId,double gpa)
    {
        this.id=id;
        this.name=name;
        this.classId=classId;
        this.gpa=gpa;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getClassId() {
        return classId;
    }

    public double getGpa() {
        return gpa;
    }
    public Object[] toRowTable()
    {
        return new Object[] {
          id,name,classId,gpa  
        };
    }
    
    
}
