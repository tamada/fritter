public class HelloWorld {
  public static void violatedMethod() { // no_static_method
    // do nothing.
  }

  public static HelloWorld of() { // this is generate method, not violated.
    return new HelloWorld();
  }

  public void printHello() {
    System.out.printf("%s\n", getMessage());
    System.exit(0); // this call is NG in no_system_exit validator.
  }

  public String getMessage() { // no_accessor violation.
    return "Hello World";
  }

  public static void main(String[] args){
    System.out.println("Hello World");
    System.exit(0); // this call is OK, because the call is in the main method.
  }
}
