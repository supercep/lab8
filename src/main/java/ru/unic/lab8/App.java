package ru.unic.lab8;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {


        if ((null==args) || (args.length<2))
        {
            System.out.println("Error \n\nPlease enter correct formule like \n\njava -jar calc.jar 2 + 2 \n\n or \n\njava -jar calc.jar 2 - 2\n\n or \n\njava -jar calc.jar 2 x 2\n\n or \n\njava -jar calc.jar 2 / 2\n\n");
        }
        else{

            String action = args[1];
            int first = Integer.parseInt(args[0]), second = Integer.parseInt(args[2]), result;

            switch (action) {
                case "+":
                    result = first + second;
                    System.out.println(first + " " + action + " " + second + " = " + result);
                    break;
                case "-":
                    result = first - second;
                    System.out.println(first + " " + action + " " + second + " = " + result);
                    break;
                case "x":
                    result = first * second;
                    System.out.println(first + " " + action + " " + second + " = " + result);
                    break;
                case "/":
                    result = first / second;
                    System.out.println(first + " " + action + " " + second + " = " + result);
                    break;
                default:
                    System.out.println("Error \n Please enter correct formule like \n java -jar calc.jar 2 + 2 \n\n or \n\n  java -jar calc.jar 2 - 2 \n\n or \n\n java -jar calc.jar 2 x 2\n\n or \n\njava -jar calc.jar 2 / 2");
            }
        }
    }
}
