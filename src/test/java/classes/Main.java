package classes;

public class Main {
    public static void main(String[] args) {
        Person person = new Person.Builder()
                .withName("John")
                .withSurName("Smith")
                .withAge(25)
                .withSalary(3000)
                .withSex("Male")
                .withJobTitle("QA Automation Engineer")
                .build();


        System.out.println(person);
    }


}
