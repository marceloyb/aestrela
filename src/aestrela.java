import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Vector;

import static java.lang.System.exit;

public class aestrela{

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




    public static class cell{
        int g, h, l, c;
        int[][]matriz = new int[4][4];
    }

    public static boolean valido(int l, int c){
        return (l >= 0) && (l < 4) &&
                (c >= 0) && (c < 4);
    }

    public static int h2(int matrizentrada[][]){
        int i, j, pecasfora = 0;
        int[][]tabuleirocorreto = new int[4][4];
        tabuleirocorreto = instanciatabuleiro();
        for(i = 0; i < 4; i++){
            for(j = 0; j < 4; j++){
                if((matrizentrada[i][j] != tabuleirocorreto[i][j]) && tabuleirocorreto[i][j] != 0)
                    pecasfora++;
            }
        }
        return pecasfora;
    }


    public static boolean checkMatrix(int matriz1[][], int matriz2[][]){
        int i, j, s = 0;
        for(i = 0; i < 4; i++){
            for(j = 0; j < 4; j++){
                if(matriz1[i][j] == matriz2[i][j]){
                    s++;
                }
            }
        }
        if (s >= 16)
            return true;
        else
            return false;
    }

    public static Vector<cell> findNeighbors(cell Node){
        int i, j, aux, elementoatual = 0;
        Vector<cell> Neighbors = new Vector<cell>(4);
        cell Test = new cell();
        // VIZINHO NORTE
        // FAZER CASO DE NAO ESTAR NA OPEN SET
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
            Neighbor.h = h2(Neighbor.matriz);
            Neighbor.l = Node.l-1;
            Neighbor.c = Node.c;
            Neighbors.addElement(Neighbor);
            elementoatual++;
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
            Neighbor.h = h2(Neighbor.matriz);
            Neighbor.l = Node.l+1;
            Neighbor.c = Node.c;
            Neighbors.addElement(Neighbor);
            elementoatual++;
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
            Neighbor.h = h2(Neighbor.matriz);
            Neighbor.l = Node.l;
            Neighbor.c = Node.c+1;
            Neighbors.add(Neighbor);
            elementoatual++;
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
            Neighbor.h = h2(Neighbor.matriz);
            Neighbor.l = Node.l;
            Neighbor.c = Node.c-1;
            Neighbors.add(Neighbor);
            elementoatual++;
        }
        return Neighbors;
    }

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

    public static void aStar(int[][] tabuleiroentrada){
        int i, j, count, auxiliar;
        cell Neighbor, Node, auxCell, openCell;
        Vector<cell> Neighbors = new Vector<cell>();
        Vector<cell> closedList = new Vector<cell>();
        Comparator<cell> comparator = new fComparator();
        PriorityQueue<cell> openQueue = new PriorityQueue<cell>(comparator);
        cell Inicial = new cell();
        Inicial.matriz = tabuleiroentrada;
        for (i = 0; i < 4; i++){
            for(j = 0; j < 4; j++){
                if (tabuleiroentrada[i][j] == 0){
                    Inicial.l = i;
                    Inicial.c = j;
                }
            }
        }
        Inicial.g = 0;
        Inicial.h = h2(Inicial.matriz);
        openQueue.add(Inicial);

        // comeca funcionar
        while(openQueue.size() != 0){
             Node = openQueue.remove();
            closedList.add(Node);
            if(!checkEnd(Node.matriz)) {
                Neighbors = findNeighbors(Node);
                count = 0;
                if (!Neighbors.isEmpty()) {
                    FINDNEIGHBORS:
                    while (count < Neighbors.size()) {
                        Neighbor = Neighbors.get(count);
                        for (i = 0; i < 4; i++){
                            for(j = 0; j < 4; j++){
                                System.out.print(" "+Neighbor.matriz[i][j]);
                            }
                            System.out.println(" ");
                        }
                        for (cell node : closedList) {
                            if (node.equals(Neighbor)) {
                                System.out.println("ACHOU NA CLOSED");
                                continue FINDNEIGHBORS;
                            }
                        }
                        for (cell node : openQueue) {
                            if (node.equals(Neighbor) && node.g <= Neighbor.g) {
                                System.out.println("ACHOU NA OPEN");
                                continue FINDNEIGHBORS;
                            }
                        }
                        openQueue.add(Neighbor);
                        count++;
                    }
                }
            }
            else{
                System.out.println(Node.g);
                exit(0);
            }
        }
    }



    public static void main(String[] args){
        int[][]tabuleiroteste = new int[4][4];
        cell[] Neighbors = new cell[4];

        tabuleiroteste[0][0] = 1;
        tabuleiroteste[0][1] = 2;
        tabuleiroteste[0][2] = 3;
        tabuleiroteste[0][3] = 4;
        tabuleiroteste[1][0] = 12;
        tabuleiroteste[1][1] = 13;
        tabuleiroteste[1][2] = 14;
        tabuleiroteste[1][3] = 5;
        tabuleiroteste[2][0] = 11;
        tabuleiroteste[2][1] = 0;
        tabuleiroteste[2][2] = 15;
        tabuleiroteste[2][3] = 6;
        tabuleiroteste[3][0] = 10;
        tabuleiroteste[3][1] = 9;
        tabuleiroteste[3][2] = 8;
        tabuleiroteste[3][3] = 7;


//        tabuleiroteste[0][0] = 3;
//        tabuleiroteste[0][1] = 4;
//        tabuleiroteste[0][2] = 5;
//        tabuleiroteste[0][3] = 6;
//        tabuleiroteste[1][0] = 1;
//        tabuleiroteste[1][1] = 2;
//        tabuleiroteste[1][2] = 14;
//        tabuleiroteste[1][3] = 7;
//        tabuleiroteste[2][0] = 12;
//        tabuleiroteste[2][1] = 13;
//        tabuleiroteste[2][2] = 15;
//        tabuleiroteste[2][3] = 8;
//        tabuleiroteste[3][0] = 11;
//        tabuleiroteste[3][1] = 0;
//        tabuleiroteste[3][2] = 10;
//        tabuleiroteste[3][3] = 9;
        aStar(tabuleiroteste);

    }

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


