public class error_java{
    public class error_java{
        public static void main(String[] args){
            int a =  10;
            int b = 0;
            float c;
            try{
                c = a/b;
                System.out.print(c);
            } catch(ArithmeticException e){
                System.out.println("Error: Division by zero");
            }
        }
    }
}