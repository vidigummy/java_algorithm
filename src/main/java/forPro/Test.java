package forPro;

public class Test {
    public static void main(String[] args){
        for(int i =0; i < 100; i++){
            double dValue = Math.random();
            int iValue = (int)(dValue*1000000000);
            System.out.printf("%d ", iValue);
        }
    }
}
