package application;

import java.math.*;
import java.util.Random;

/*
* algoritmo de auto-correção, corrige uma matriz binária de 4x4 a partir de comparação
*
* [x][1][1][-]
* [1][-][-][-]
* [1][-][-][-]
* [-][-][-][-]
*
* mensagem de 11 bits | utilização de log
* if(Math.log(cont) / Math.log(2) == (int)(Math.log(cont) / Math.log(2)) && cont == 0){}
*
* String g = "caramele";
* System.out.println(g.charAt(g.length()-1));
*
* */

public class Program {
    public static void main(String[] args) {
        String message = "11010010101";
        int[] matriz  = new int[16];

        System.out.printf("----------------original:\n");

        fillMatriz(matriz,message);
        printMatriz(matriz);

        System.out.printf("----------------config:\n");

        configMatriz(matriz);
        printMatriz(matriz);

        System.out.printf("----------------corrupt:\n");

        crptData(matriz);
        printMatriz(matriz);

        printMessage(matriz);
        System.out.printf("\n--Enviada : "+ message);

        System.out.printf("\n\n----------------correction:\n");

        selfCorrection(matriz);
        printMatriz(matriz);

        printMessage(matriz);
        System.out.printf("\n--Enviada : "+ message);


    }

    public static String toBinary(int n) {
        if (n == 0) {
            return "";
        }

        return toBinary(n / 2) + (n % 2);
    }

    public static void fillMatriz(int[] matriz, String message) {
        int aux = 0;

        for(int i=0; i<16; i++){    //matriz[i]
            if(Math.log(i) / Math.log(2) != (int)(Math.log(i) / Math.log(2)) && i !=0 ){
                matriz[i] = Integer.parseInt(String.valueOf(message.charAt(aux)));
                aux++;
            }
        }
    }

    public static boolean comparator(int[] matriz, int comp){
        boolean dec;
        int sum = 0;

        for(int i=0; i<16; i++){
            int z = Integer.parseInt(Character.toString(String.format("%4s", Integer.toBinaryString(i)).replace(" ", "0").charAt(comp)));

            // pega o digito da posicao comp do binário da posicao do vetor e converte em inteiro
            if(z == 1) {
                if (matriz[i] == 1) {
                    sum++;
                }
            }
        }

        if(sum % 2 == 0){
            dec = true;
        } else {
            dec = false;
        }

        return dec;
    };

    public static boolean comparator(int[] matriz){
        int sum = 0;

        for(int i=0; i<15;i++){
            if(matriz[i] == 1){
                sum++;
            }
        }

        if(sum % 2 == 0){
            return true;
        } else {
            return false;
        }
    };

    public static void configMatriz(int[] matriz){
        int aux = 3;

        for(int n=0; n<4;n++){
            if(!comparator(matriz, aux)){
                matriz[(int)Math.pow(2, n)] = 1;
            }

            aux--;
        }

        int sum = 0;
        for(int n=0; n<15; n++){
            if (matriz[n] == 1) {
                sum++;
            }
        }

        if(sum % 2 == 0){
            matriz[0] = 1;
        } else {
            matriz[0] = 0;
        }

    }

    public static void printMatriz(int[] matriz){
        int cont = 0;

        for (int i = 0; i < matriz.length; i++) {
            System.out.print("["+matriz[i]+"]");
            if(cont == 3){
                System.out.println();
                cont = 0;
            } else {
                cont++;
            }

        }
        System.out.println();

    };

    public static void crptData(int[] matriz){   //corrupt data
        int random = new Random().nextInt(15);
        if(matriz[random] == 1){
            matriz[random] = 0;
        }else{
            matriz[random] = 1;
        }
    }

    public static void selfCorrection(int[] matriz){
        if(!comparator(matriz)){     // par

        } else {                    // impar
            int aux = 3;
            String dec = "";

            String x;

            for(int n=0; n<4;n++){
                if(!comparator(matriz, aux)){
                    dec = String.format(dec  +"1");
                } else {dec = String.format(dec  +"0");}

                aux--;
            }

            dec = new StringBuilder(dec).reverse().toString();
            int n = Integer.parseInt(dec, 2);

            //System.out.println(dec+"--------------->>>>>>-");

            if(matriz[n] == 1){
                matriz[n] = 0;
            }else {
                matriz[n] = 1;
            }
        }
    }

    public static void printMessage(int[] matriz){


        System.out.printf("\n-Recebida : ");
        for(int n=0; n<matriz.length;n++) {
            if(Math.log(n) / Math.log(2) != (int)(Math.log(n) / Math.log(2)) && n !=0 )
                System.out.print(matriz[n]);
        };
    };

}




















