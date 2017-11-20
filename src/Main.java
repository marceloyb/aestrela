import java.util.*;

import static java.lang.System.exit;
import static sun.swing.MenuItemLayoutHelper.max;

class Main {

    // IMPLEMENTAÇÃO DO ALGORITMO A*
    // ALUNO: MARCELO YURI BENESCIUTTI RA 90565
    // MOA CC UEM 2017

    // funçao para instanciar o estado final
    public static int[][] instanciatabuleiro(){
        int[][]tabuleirocorreto = new int[4][4];
        tabuleirocorreto[0][0] = 1;
        tabuleirocorreto[0][1] = 2;
        tabuleirocorreto[0][2] = 3;
        tabuleirocorreto[0][3] = 4;
        tabuleirocorreto[1][0] = 12;
        tabuleirocorreto[1][1] = 13;
        tabuleirocorreto[1][2] = 14;
        tabuleirocorreto[1][3] = 5;
        tabuleirocorreto[2][0] = 11;
        tabuleirocorreto[2][1] = 0;
        tabuleirocorreto[2][2] = 15;
        tabuleirocorreto[2][3] = 6;
        tabuleirocorreto[3][0] = 10;
        tabuleirocorreto[3][1] = 9;
        tabuleirocorreto[3][2] = 8;
        tabuleirocorreto[3][3] = 7;

        return tabuleirocorreto;
    }

    // funcao que converte matriz para vetor
    public static int[] convertepraVetor(int matrizentrada[][]){
        int []vetorentrada = new int[16];
        vetorentrada[0] = matrizentrada[0][0];
        vetorentrada[1] = matrizentrada[0][1];
        vetorentrada[2] = matrizentrada[0][2];
        vetorentrada[3] = matrizentrada[0][3];
        vetorentrada[4] = matrizentrada[1][0];
        vetorentrada[5] = matrizentrada[1][1];
        vetorentrada[6] = matrizentrada[1][2];
        vetorentrada[7] = matrizentrada[1][3];
        vetorentrada[8] = matrizentrada[2][0];
        vetorentrada[9] = matrizentrada[2][1];
        vetorentrada[10] = matrizentrada[2][2];
        vetorentrada[11] = matrizentrada[2][3];
        vetorentrada[12] = matrizentrada[3][0];
        vetorentrada[13] = matrizentrada[3][1];
        vetorentrada[14] = matrizentrada[3][2];
        vetorentrada[15] = matrizentrada[3][3];

        return vetorentrada;

    }

    // classe da celula (nó)
    public static class cell{
        int g;
        double h;
        int l;
        int c;
        int[][]matriz = new int[4][4];
    }

    // funçao para checar se a posicao está dentro da matriz
    public static boolean valido(int l, int c){
        return (l >= 0) && (l < 4) &&
                (c >= 0) && (c < 4);
    }

    // heuristica 1
    public static int h1(int[][] matrizentrada){
        int i, pecasfora = 0;
        int [][]tabuleirocorreto;
        tabuleirocorreto = instanciatabuleiro();
        int []vetorentrada;
        int []vetorcorreto;
        vetorentrada = convertepraVetor(matrizentrada);
        vetorcorreto = convertepraVetor(tabuleirocorreto);

        for (i = 0; i < 16; i++){
            if (vetorentrada[i] != vetorcorreto[i] && vetorentrada[i] != 0){
                pecasfora++;
            }
        }
        return pecasfora;
    }

    // heuristica 2
    public static int h2(int[][] matrizentrada){
        int i, pecasfora = 0;
        int []vetorespiral = new int[16];
        int [][]tabuleirocorreto;
        tabuleirocorreto = instanciatabuleiro();
        int []vetorentrada;
        int []vetorcorreto;
        vetorentrada = convertepraVetor(matrizentrada);

        vetorespiral[0] = vetorentrada[0];
        vetorespiral[1] = vetorentrada[1];
        vetorespiral[2] = vetorentrada[2];
        vetorespiral[3] = vetorentrada[3];
        vetorespiral[4] = vetorentrada[7];
        vetorespiral[5] = vetorentrada[11];
        vetorespiral[6] = vetorentrada[15];
        vetorespiral[7] = vetorentrada[14];
        vetorespiral[8] = vetorentrada[13];
        vetorespiral[9] = vetorentrada[12];
        vetorespiral[10] = vetorentrada[8];
        vetorespiral[11] = vetorentrada[4];
        vetorespiral[12] = vetorentrada[5];
        vetorespiral[13] = vetorentrada[6];
        vetorespiral[14] = vetorentrada[10];
        vetorespiral[15] = vetorentrada[9];

        for (i = 1; i < 16; i++){
            if (vetorespiral[i]-1 != vetorespiral [i-1] && vetorespiral[i-1] != 0){
                    pecasfora++;
            }
        }

        return pecasfora;
    }

    // heuristica 3
    public static int h3(int[][] matrizentrada) {
        int i, npassos = 0;
        int [][]tabuleirocorreto;
        tabuleirocorreto = instanciatabuleiro();
        int []vetorentrada;
        int []vetorcorreto;
        vetorentrada = convertepraVetor(matrizentrada);
        vetorcorreto = convertepraVetor(tabuleirocorreto);

        for (i = 0; i < 16; i++) {
            if (vetorentrada[i] != vetorcorreto[i] && vetorentrada[i] != 0) {
                switch (i) {
                    case 0:
                        if (vetorentrada[i] == 2) npassos += 1;
                        if (vetorentrada[i] == 3) npassos += 2;
                        if (vetorentrada[i] == 4) npassos += 3;
                        if (vetorentrada[i] == 5) npassos += 4;
                        if (vetorentrada[i] == 6) npassos += 5;
                        if (vetorentrada[i] == 7) npassos += 6;
                        if (vetorentrada[i] == 8) npassos += 5;
                        if (vetorentrada[i] == 9) npassos += 4;
                        if (vetorentrada[i] == 10) npassos += 3;
                        if (vetorentrada[i] == 11) npassos += 2;
                        if (vetorentrada[i] == 12) npassos += 1;
                        if (vetorentrada[i] == 13) npassos += 2;
                        if (vetorentrada[i] == 14) npassos += 3;
                        if (vetorentrada[i] == 15) npassos += 4;
                        break;

                    case 1:
                        if (vetorentrada[i] == 1) npassos += 1;
                        if (vetorentrada[i] == 3) npassos += 1;
                        if (vetorentrada[i] == 4) npassos += 2;
                        if (vetorentrada[i] == 5) npassos += 3;
                        if (vetorentrada[i] == 6) npassos += 4;
                        if (vetorentrada[i] == 7) npassos += 5;
                        if (vetorentrada[i] == 8) npassos += 4;
                        if (vetorentrada[i] == 9) npassos += 3;
                        if (vetorentrada[i] == 10) npassos += 4;
                        if (vetorentrada[i] == 11) npassos += 3;
                        if (vetorentrada[i] == 12) npassos += 2;
                        if (vetorentrada[i] == 13) npassos += 1;
                        if (vetorentrada[i] == 14) npassos += 2;
                        if (vetorentrada[i] == 15) npassos += 3;
                        break;

                    case 2:
                        if (vetorentrada[i] == 1) npassos += 2;
                        if (vetorentrada[i] == 2) npassos += 1;
                        if (vetorentrada[i] == 4) npassos += 1;
                        if (vetorentrada[i] == 5) npassos += 2;
                        if (vetorentrada[i] == 6) npassos += 3;
                        if (vetorentrada[i] == 7) npassos += 4;
                        if (vetorentrada[i] == 8) npassos += 3;
                        if (vetorentrada[i] == 9) npassos += 4;
                        if (vetorentrada[i] == 10) npassos += 5;
                        if (vetorentrada[i] == 11) npassos += 4;
                        if (vetorentrada[i] == 12) npassos += 3;
                        if (vetorentrada[i] == 13) npassos += 2;
                        if (vetorentrada[i] == 14) npassos += 1;
                        if (vetorentrada[i] == 15) npassos += 2;
                        break;

                    case 3:
                        if (vetorentrada[i] == 2) npassos += 2;
                        if (vetorentrada[i] == 3) npassos += 1;
                        if (vetorentrada[i] == 1) npassos += 3;
                        if (vetorentrada[i] == 5) npassos += 1;
                        if (vetorentrada[i] == 6) npassos += 2;
                        if (vetorentrada[i] == 7) npassos += 3;
                        if (vetorentrada[i] == 8) npassos += 4;
                        if (vetorentrada[i] == 9) npassos += 5;
                        if (vetorentrada[i] == 10) npassos += 6;
                        if (vetorentrada[i] == 11) npassos += 5;
                        if (vetorentrada[i] == 12) npassos += 4;
                        if (vetorentrada[i] == 13) npassos += 3;
                        if (vetorentrada[i] == 14) npassos += 2;
                        if (vetorentrada[i] == 15) npassos += 3;
                        break;

                    case 4:
                        if (vetorentrada[i] == 2) npassos += 2;
                        if (vetorentrada[i] == 3) npassos += 3;
                        if (vetorentrada[i] == 4) npassos += 4;
                        if (vetorentrada[i] == 5) npassos += 3;
                        if (vetorentrada[i] == 6) npassos += 4;
                        if (vetorentrada[i] == 7) npassos += 5;
                        if (vetorentrada[i] == 8) npassos += 4;
                        if (vetorentrada[i] == 9) npassos += 3;
                        if (vetorentrada[i] == 10) npassos += 2;
                        if (vetorentrada[i] == 11) npassos += 1;
                        if (vetorentrada[i] == 1) npassos += 1;
                        if (vetorentrada[i] == 13) npassos += 1;
                        if (vetorentrada[i] == 14) npassos += 2;
                        if (vetorentrada[i] == 15) npassos += 3;
                        break;

                    case 5:
                        if (vetorentrada[i] == 2) npassos += 1;
                        if (vetorentrada[i] == 3) npassos += 2;
                        if (vetorentrada[i] == 4) npassos += 3;
                        if (vetorentrada[i] == 5) npassos += 2;
                        if (vetorentrada[i] == 6) npassos += 3;
                        if (vetorentrada[i] == 7) npassos += 4;
                        if (vetorentrada[i] == 8) npassos += 3;
                        if (vetorentrada[i] == 9) npassos += 2;
                        if (vetorentrada[i] == 10) npassos += 3;
                        if (vetorentrada[i] == 11) npassos += 2;
                        if (vetorentrada[i] == 12) npassos += 1;
                        if (vetorentrada[i] == 1) npassos += 2;
                        if (vetorentrada[i] == 14) npassos += 1;
                        if (vetorentrada[i] == 15) npassos += 2;
                        break;

                    case 6:  //14
                        if (vetorentrada[i] == 2) npassos += 2;
                        if (vetorentrada[i] == 3) npassos += 1;
                        if (vetorentrada[i] == 4) npassos += 2;
                        if (vetorentrada[i] == 5) npassos += 1;
                        if (vetorentrada[i] == 6) npassos += 2;
                        if (vetorentrada[i] == 7) npassos += 3;
                        if (vetorentrada[i] == 8) npassos += 2;
                        if (vetorentrada[i] == 9) npassos += 3;
                        if (vetorentrada[i] == 10) npassos += 4;
                        if (vetorentrada[i] == 11) npassos += 3;
                        if (vetorentrada[i] == 12) npassos += 2;
                        if (vetorentrada[i] == 13) npassos += 1;
                        if (vetorentrada[i] == 1) npassos += 3;
                        if (vetorentrada[i] == 15) npassos += 1;
                        break;

                    case 7: // 5
                        if (vetorentrada[i] == 2) npassos += 3;
                        if (vetorentrada[i] == 3) npassos += 2;
                        if (vetorentrada[i] == 4) npassos += 1;
                        if (vetorentrada[i] == 1) npassos += 4;
                        if (vetorentrada[i] == 6) npassos += 1;
                        if (vetorentrada[i] == 7) npassos += 2;
                        if (vetorentrada[i] == 8) npassos += 3;
                        if (vetorentrada[i] == 9) npassos += 4;
                        if (vetorentrada[i] == 10) npassos += 5;
                        if (vetorentrada[i] == 11) npassos += 4;
                        if (vetorentrada[i] == 12) npassos += 3;
                        if (vetorentrada[i] == 13) npassos += 2;
                        if (vetorentrada[i] == 14) npassos += 1;
                        if (vetorentrada[i] == 15) npassos += 2;
                        break;

                    case 8: //11
                        if (vetorentrada[i] == 2) npassos += 3;
                        if (vetorentrada[i] == 3) npassos += 4;
                        if (vetorentrada[i] == 4) npassos += 5;
                        if (vetorentrada[i] == 5) npassos += 4;
                        if (vetorentrada[i] == 6) npassos += 3;
                        if (vetorentrada[i] == 7) npassos += 4;
                        if (vetorentrada[i] == 8) npassos += 3;
                        if (vetorentrada[i] == 9) npassos += 2;
                        if (vetorentrada[i] == 10) npassos += 1;
                        if (vetorentrada[i] == 1) npassos += 2;
                        if (vetorentrada[i] == 12) npassos += 1;
                        if (vetorentrada[i] == 13) npassos += 2;
                        if (vetorentrada[i] == 14) npassos += 3;
                        if (vetorentrada[i] == 15) npassos += 2;
                        break;

                    case 9: //0
                        if (vetorentrada[i] == 1) npassos += 3;
                        if (vetorentrada[i] == 2) npassos += 2;
                        if (vetorentrada[i] == 3) npassos += 3;
                        if (vetorentrada[i] == 4) npassos += 4;
                        if (vetorentrada[i] == 5) npassos += 3;
                        if (vetorentrada[i] == 6) npassos += 2;
                        if (vetorentrada[i] == 7) npassos += 3;
                        if (vetorentrada[i] == 8) npassos += 2;
                        if (vetorentrada[i] == 9) npassos += 1;
                        if (vetorentrada[i] == 10) npassos += 2;
                        if (vetorentrada[i] == 11) npassos += 1;
                        if (vetorentrada[i] == 12) npassos += 2;
                        if (vetorentrada[i] == 13) npassos += 1;
                        if (vetorentrada[i] == 14) npassos += 2;
                        if (vetorentrada[i] == 15) npassos += 1;
                        break;

                    case 10: //15
                        if (vetorentrada[i] == 2) npassos += 3;
                        if (vetorentrada[i] == 3) npassos += 2;
                        if (vetorentrada[i] == 4) npassos += 3;
                        if (vetorentrada[i] == 5) npassos += 2;
                        if (vetorentrada[i] == 6) npassos += 1;
                        if (vetorentrada[i] == 7) npassos += 2;
                        if (vetorentrada[i] == 8) npassos += 1;
                        if (vetorentrada[i] == 9) npassos += 2;
                        if (vetorentrada[i] == 10) npassos += 3;
                        if (vetorentrada[i] == 11) npassos += 2;
                        if (vetorentrada[i] == 12) npassos += 3;
                        if (vetorentrada[i] == 13) npassos += 2;
                        if (vetorentrada[i] == 14) npassos += 1;
                        if (vetorentrada[i] == 1) npassos += 4;
                        break;

                    case 11: // 6
                        if (vetorentrada[i] == 2) npassos += 4;
                        if (vetorentrada[i] == 3) npassos += 3;
                        if (vetorentrada[i] == 4) npassos += 2;
                        if (vetorentrada[i] == 5) npassos += 1;
                        if (vetorentrada[i] == 1) npassos += 5;
                        if (vetorentrada[i] == 7) npassos += 1;
                        if (vetorentrada[i] == 8) npassos += 2;
                        if (vetorentrada[i] == 9) npassos += 3;
                        if (vetorentrada[i] == 10) npassos += 4;
                        if (vetorentrada[i] == 11) npassos += 3;
                        if (vetorentrada[i] == 12) npassos += 4;
                        if (vetorentrada[i] == 13) npassos += 3;
                        if (vetorentrada[i] == 14) npassos += 2;
                        if (vetorentrada[i] == 15) npassos += 1;
                        break;

                    case 12: //10
                        if (vetorentrada[i] == 2) npassos += 4;
                        if (vetorentrada[i] == 3) npassos += 5;
                        if (vetorentrada[i] == 4) npassos += 6;
                        if (vetorentrada[i] == 5) npassos += 5;
                        if (vetorentrada[i] == 6) npassos += 4;
                        if (vetorentrada[i] == 7) npassos += 3;
                        if (vetorentrada[i] == 8) npassos += 2;
                        if (vetorentrada[i] == 9) npassos += 1;
                        if (vetorentrada[i] == 1) npassos += 3;
                        if (vetorentrada[i] == 11) npassos += 1;
                        if (vetorentrada[i] == 12) npassos += 2;
                        if (vetorentrada[i] == 13) npassos += 3;
                        if (vetorentrada[i] == 14) npassos += 4;
                        if (vetorentrada[i] == 15) npassos += 3;
                        break;

                    case 13: //9
                        if (vetorentrada[i] == 2) npassos += 3;
                        if (vetorentrada[i] == 3) npassos += 4;
                        if (vetorentrada[i] == 4) npassos += 5;
                        if (vetorentrada[i] == 5) npassos += 4;
                        if (vetorentrada[i] == 6) npassos += 3;
                        if (vetorentrada[i] == 7) npassos += 2;
                        if (vetorentrada[i] == 8) npassos += 1;
                        if (vetorentrada[i] == 1) npassos += 4;
                        if (vetorentrada[i] == 10) npassos += 1;
                        if (vetorentrada[i] == 11) npassos += 2;
                        if (vetorentrada[i] == 12) npassos += 3;
                        if (vetorentrada[i] == 13) npassos += 2;
                        if (vetorentrada[i] == 14) npassos += 3;
                        if (vetorentrada[i] == 15) npassos += 2;
                        break;

                    case 14: //8
                        if (vetorentrada[i] == 2) npassos += 4;
                        if (vetorentrada[i] == 3) npassos += 3;
                        if (vetorentrada[i] == 4) npassos += 4;
                        if (vetorentrada[i] == 5) npassos += 3;
                        if (vetorentrada[i] == 6) npassos += 2;
                        if (vetorentrada[i] == 7) npassos += 1;
                        if (vetorentrada[i] == 1) npassos += 5;
                        if (vetorentrada[i] == 9) npassos += 1;
                        if (vetorentrada[i] == 10) npassos += 2;
                        if (vetorentrada[i] == 11) npassos += 3;
                        if (vetorentrada[i] == 12) npassos += 4;
                        if (vetorentrada[i] == 13) npassos += 3;
                        if (vetorentrada[i] == 14) npassos += 2;
                        if (vetorentrada[i] == 15) npassos += 1;
                        break;

                    case 15: //7
                        if (vetorentrada[i] == 2) npassos += 5;
                        if (vetorentrada[i] == 3) npassos += 4;
                        if (vetorentrada[i] == 4) npassos += 3;
                        if (vetorentrada[i] == 5) npassos += 2;
                        if (vetorentrada[i] == 6) npassos += 1;
                        if (vetorentrada[i] == 1) npassos += 6;
                        if (vetorentrada[i] == 8) npassos += 1;
                        if (vetorentrada[i] == 9) npassos += 2;
                        if (vetorentrada[i] == 10) npassos += 3;
                        if (vetorentrada[i] == 11) npassos += 4;
                        if (vetorentrada[i] == 12) npassos += 5;
                        if (vetorentrada[i] == 13) npassos += 4;
                        if (vetorentrada[i] == 14) npassos += 3;
                        if (vetorentrada[i] == 15) npassos += 2;
                        break;
                }
            }
        }
        return npassos;
    }

    // heuristica 4
    public static double h4(int[][] matrizentrada){
        double p1, p2, p3, valorh4;
        p1 = 0.1;
        p2 = 0.2;
        p3 = 0.8;

        valorh4 = p1*h1(matrizentrada) + p2*h2(matrizentrada) + p3*h3(matrizentrada);

        return valorh4;
    }

    // heuristica 5
    public static int h5(int[][] matrizentrada){
        int valorh5;
        valorh5 = max(h1(matrizentrada), h2(matrizentrada), h3(matrizentrada));
        return valorh5;
    }

    // funçao para checar se duas matrizes são iguais
    public static boolean checkMatrix(int matriz1[][], int matriz2[][]){
        int i, j;
        for(i = 0; i < 4; i++){
            for(j = 0; j < 4; j++){
                if(matriz1[i][j] != matriz2[i][j]){
                    return false;
                }
            }
        }
        return true;
    }

    // funcao para achar os vizinhos de um nó (sucessores)
    public static Set<cell> findNeighbors(cell Node){
        int i, j, aux;
        Set<cell> Neighbors = new HashSet<>();

        // VIZINHO NORTE
        if(valido(Node.l-1, Node.c)){
            cell Neighbor = new cell();
            Neighbor.g = Node.g + 1;
            for(i = 0; i < 4; i++){
                for(j = 0; j < 4; j++){
                    Neighbor.matriz[i][j] = Node.matriz[i][j];
                }
            }
            aux = Neighbor.matriz[Node.l][Node.c];
            Neighbor.matriz[Node.l][Node.c] = Neighbor.matriz[Node.l-1][Node.c];
            Neighbor.matriz[Node.l-1][Node.c] = aux;
            Neighbor.l = Node.l-1;
            Neighbor.c = Node.c;
            Neighbors.add(Neighbor);
        }

        // VIZINHO SUL
        if(valido(Node.l+1, Node.c)){
            cell Neighbor = new cell();
            Neighbor.g = Node.g + 1;
            for(i = 0; i < 4; i++){
                for(j = 0; j < 4; j++){
                    Neighbor.matriz[i][j] = Node.matriz[i][j];
                }
            }
            aux = Neighbor.matriz[Node.l][Node.c];
            Neighbor.matriz[Node.l][Node.c] = Neighbor.matriz[Node.l+1][Node.c];
            Neighbor.matriz[Node.l+1][Node.c] = aux;
            Neighbor.l = Node.l+1;
            Neighbor.c = Node.c;
            Neighbors.add(Neighbor);
        }
        //VIZINHO EAST
        if(valido(Node.l, Node.c+1)){
            cell Neighbor = new cell();
            Neighbor.g = Node.g + 1;
            for(i = 0; i < 4; i++){
                for(j = 0; j < 4; j++){
                    Neighbor.matriz[i][j] = Node.matriz[i][j];
                }
            }
            aux = Neighbor.matriz[Node.l][Node.c];
            Neighbor.matriz[Node.l][Node.c] = Neighbor.matriz[Node.l][Node.c+1];
            Neighbor.matriz[Node.l][Node.c+1] = aux;
            Neighbor.l = Node.l;
            Neighbor.c = Node.c+1;
            Neighbors.add(Neighbor);
        }
        //VIZINHO WEST
        if (valido(Node.l, Node.c-1)){
            cell Neighbor = new cell();

            Neighbor.g = Node.g + 1;
            for(i = 0; i < 4; i++){
                for(j = 0; j < 4; j++){
                    Neighbor.matriz[i][j] = Node.matriz[i][j];
                }
            }
            aux = Neighbor.matriz[Node.l][Node.c];
            Neighbor.matriz[Node.l][Node.c] = Neighbor.matriz[Node.l][Node.c-1];
            Neighbor.matriz[Node.l][Node.c-1] = aux;
            Neighbor.l = Node.l;
            Neighbor.c = Node.c-1;
            Neighbors.add(Neighbor);
        }
        return Neighbors;
    }

    // funçao para checar se determinada matriz é o estado final desejado
    public static boolean checkEnd(int matriz[][]){
        int i, j, s = 0;
        int[][]tabuleirocorreto = new int[4][4];
        tabuleirocorreto = instanciatabuleiro();
        for(i = 0; i < 4; i++){
            for(j = 0; j < 4; j++){
                if(matriz[i][j] == tabuleirocorreto[i][j]){
                    s++;
                }
            }
        }
        if (s >= 16){
            return true;
        }
        else
            return false;
    }

    // algoritmo a*
    public static void aStar(int[][] tabuleiroentrada) {

        // declaracao de variaveis e estruturas de dados utilizadas
        int i, j, neighborhash, ncriados = 0, nanalisados = 0;
        int []vetorhash;
        cell Node;
        cell auxCell;

        Comparator<cell> comparator = new fComparator();
        Set<cell> Neighbors;
        PriorityQueue<cell> openQueue = new PriorityQueue<>(comparator);

        Map<Integer, cell> openMap = new HashMap<Integer, cell>();
        Map<Integer, cell> closedList = new HashMap<Integer, cell>();



        // colocando o estado inicial na open queue
        cell Inicial = new cell();
        Inicial.matriz = tabuleiroentrada;
        for (i = 0; i < 4; i++) {
            for (j = 0; j < 4; j++) {
                if (tabuleiroentrada[i][j] == 0) {
                    Inicial.l = i;
                    Inicial.c = j;
                }
            }
        }
        Inicial.g = 0;
        Inicial.h = h3(Inicial.matriz);
        openQueue.add(Inicial);

        // comeca o a*
        while (openQueue.size() != 0) {
            // remove o menor nó da open queue
            Node = openQueue.poll();

            vetorhash = convertepraVetor(Node.matriz);

            closedList.put(Arrays.hashCode(vetorhash), Node);

            // para cada sucessor calcula o g
            if (!checkEnd(Node.matriz)) {
                Neighbors = findNeighbors(Node);

                // procura o sucessor na open e closed
                CHECKNEIGHBORS:
                for (cell neighbor : Neighbors) {
                    vetorhash = convertepraVetor(neighbor.matriz);
                    neighborhash = Arrays.hashCode(vetorhash);
                    ncriados++;

                    // procura um hash igual o do sucessor na open com um g menor
                    auxCell = openMap.get(neighborhash);
                    if (auxCell != null && auxCell.g <= neighbor.g){
                        continue CHECKNEIGHBORS;
                    }

                    // procura um hash igual o do sucessor na closed com um g menor
                    auxCell = closedList.get(neighborhash);
                    if (auxCell != null && auxCell.g <= neighbor.g){
                        continue CHECKNEIGHBORS;
                    }

                    // nao achou um igual na closed e na open com g menor, add na open
                    nanalisados++;
                    vetorhash = convertepraVetor(neighbor.matriz);
                    openMap.put(Arrays.hashCode(vetorhash), neighbor);
                    neighbor.h = h3(neighbor.matriz);
                    openQueue.add(neighbor);
                }
            }
            // achou a solução, fim do algoritmo
            else{
                    System.out.println("Numero de celulas criadas "+ncriados);
                    System.out.println("Numero de celulas processadas "+nanalisados);
                    System.out.println(Node.g);
                    exit(0);
                }
            }
        }

    // main onde é recebida a matriz de entrada e onde o algoritmo é chamado
    public static void main(String[] args){
        int i,j;
        Scanner s = new Scanner(System.in);
        int[][]tabuleiroteste = new int[4][4];
        for (i = 0; i < 4; i++){
            for (j = 0; j < 4; j++){
                tabuleiroteste[i][j] = s.nextInt();
            }
        }
        aStar(tabuleiroteste);

    }

    // funçao necessária para ordenar a priority queue
    public static class fComparator implements Comparator<cell> {
        @Override
        public int compare(cell x, cell y) {
            if (x.g + x.h < y.g + y.h) {
                return -1;
            }
            else if (x.g + x.h > y.g + y.h) {
                return 1;
            }
            else if(checkMatrix(x.matriz, y.matriz))
                return 0;
            else
                return 1;
        }
    }

}


