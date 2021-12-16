package application;

import java.math.*;

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

        fillMatriz(matriz,message);
        printMatriz(matriz);

        System.out.printf("----------------\n");

        configMatriz(matriz);
        printMatriz(matriz);

        //System.out.println(comparator(matriz, 1));


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
}




















