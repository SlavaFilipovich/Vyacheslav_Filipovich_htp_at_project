package at_classes.classes;

public class Person {
    private String name;
    private String surName;
    private int age;
    private double salary;
    private String sex;
    private String jobTitle;

    public static class Builder{
        private Person newPerson;

        public Builder(){
            newPerson = new Person();
        }

        public Builder withName(String name){
            newPerson.name = name;
            return this;
        }

        public Builder withSurName(String surName){
            newPerson.surName = surName;
            return this;
        }

        public Builder withAge(int age){
            newPerson.age = age;
            return this;
        }

        public Builder withSalary(double salary){
            newPerson.salary = salary;
            return this;
        }

        public Builder withSex(String sex){
            newPerson.sex = sex;
            return this;
        }

        public Builder withJobTitle(String jobTitle){
            newPerson.jobTitle = jobTitle;
            return this;
        }

        public Person build(){
            return newPerson;
        }

    }

public Person(String name){
    this.name = name;
}

public Person(){
}




    public String getName() {
        return name;
    }

    public String getSurName() {
        return surName;
    }

    public int getAge() {
        return age;
    }

    public double getSalary() {
        return salary;
    }

    public String getSex() {
        return sex;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", surName='" + surName + '\'' +
                ", age=" + age +
                ", salary=" + salary +
                ", sex='" + sex + '\'' +
                ", jobTitle='" + jobTitle + '\'' +
                '}';
    }
}
