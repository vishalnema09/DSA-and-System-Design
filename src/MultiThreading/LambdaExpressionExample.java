package MultiThreading;


public class LambdaExpressionExample {
    public static void main(String[] args) {
        Student engineerStudent = new Student() {
            @Override
            public void getBio(String name) {
                System.out.println(name + " is engineer student ");
            }
        };

        Student lawStudent = (name) ->{
            System.out.println(name + " is law student");
        };

        engineerStudent.getBio("vishal");
        lawStudent.getBio("rahul");
    }
}
