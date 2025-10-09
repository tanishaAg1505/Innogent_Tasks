import java.util.ArrayList;
import java.util.List;

class Employee{
    private int id ;
    private String name;
    private int salary;
    private String Department;

    Employee(int id , String name , int salary ,  String Department){
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.Department= Department;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getSalary() {
        return salary;
    }

    public String getDepartment() {
        return Department;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                ", Department='" + Department + '\'' +
                '}';
    }
}
public class StreamDemo {
    public static void main(String[] args) {
        Employee emp1 = new Employee(101 , "Tanisha" , 5000, "Software Engineer");
        Employee emp2 = new Employee(104 , "Ujjwal" , 6000,"Software Developer");
        Employee emp3 = new Employee(102 , "Missy" , 4000, "HR");
        Employee emp4 = new Employee(103 , "Dewanshi" , 3000,"Business Development Executive");
        Employee emp5 = new Employee(105 , "Advaitha" , 5000,"Lecturer");

        List<Employee> list = new ArrayList<>();
        list.add(emp1);
        list.add(emp2);
        list.add(emp3);
        list.add(emp4);
        list.add(emp5);
        //list.stream().reduce((x,y)->x.getSalary()+y.getSalary());
        System.out.println(list.stream().map(x->x.getSalary()).reduce((x,y)->x+y));
        System.out.println(list.stream().map(x->x.getName().toUpperCase()).toList());

    }
}
