package lab1_angelrisso;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Lab1_AngelRisso {

    static Scanner sc = new Scanner(System.in);
    static int turnos = 0;

    public static void main(String[] args) {
        int fil = 11, col = 11;

        String[][] tablero = {
            {"[ ]", "[ ]", "[ ]", " X ", " X ", " X ", " X ", " X ", "[ ]", "[ ]", "[ ]"},
            {"[ ]", "[ ]", "[ ]", "[ ]", "[ ]", " X ", "[ ]", "[ ]", "[ ]", "[ ]", "[ ]"},
            {"[ ]", "[ ]", "[ ]", "[ ]", "[ ]", " 0 ", "[ ]", "[ ]", "[ ]", "[ ]", "[ ]"},
            {" X ", "[ ]", "[ ]", "[ ]", "[ ]", " 0 ", "[ ]", "[ ]", "[ ]", "[ ]", " X "},
            {" X ", "[ ]", "[ ]", "[ ]", "[ ]", " 0 ", "[ ]", "[ ]", "[ ]", "[ ]", " X "},
            {" X ", " X ", " 0 ", " 0 ", " 0 ", " K ", " 0 ", " 0 ", " 0 ", " X ", " X "},
            {" X ", "[ ]", "[ ]", "[ ]", "[ ]", " 0 ", "[ ]", "[ ]", "[ ]", "[ ]", " X "},
            {" X ", "[ ]", "[ ]", "[ ]", "[ ]", " 0 ", "[ ]", "[ ]", "[ ]", "[ ]", " X "},
            {"[ ]", "[ ]", "[ ]", "[ ]", "[ ]", " 0 ", "[ ]", "[ ]", "[ ]", "[ ]", "[ ]"},
            {"[ ]", "[ ]", "[ ]", "[ ]", "[ ]", " X ", "[ ]", "[ ]", "[ ]", "[ ]", "[ ]"},
            {"[ ]", "[ ]", "[ ]", " X ", " X ", " X ", " X ", " X ", "[ ]", "[ ]", "[ ]"}};
        juego(tablero);

    }

    public static void imp(String[][] tab, int fil, int col) {
        if (fil == tab.length - 1 && col == tab[0].length - 1) {
            System.out.print(tab[fil][col]);
        } else if (col == tab[0].length - 1) {
            System.out.println(tab[fil][col]);
            imp(tab, fil + 1, 0);
        } else {
            System.out.print(tab[fil][col]);
            imp(tab, fil, col + 1);
        }

    }

    public static void juego(String[][] tabla) {
        boolean turn = true;
        int coord1 = 0, coord2 = 0, movimiento, vert = 0, hori = 0, m_rey, ban = 0, ganar = 0;
        while (ganar == 0) {
            turnos++;
            System.out.println("Turno numero:" + turnos);
            if (turn == true) {
                System.out.println("turno del atacante");
                imp(tabla, 0, 0);

                System.out.println();
                System.out.println("ingrese coordenada en x y en y para seleccionar su pieza: ");
                try {
                    coord1 = sc.nextInt();
                    coord2 = sc.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("error en el input");
                } catch (Exception e) {

                }

                while (coord1 > tabla.length - 1 || coord2 > tabla.length - 1) {
                    System.out.println("Coordenadas no validas, ingrese de nuevo: ");
                }
                while (!tabla[coord2][coord1].equals(" X ")) {
                    System.out.println("pieza no valida, ingrese de nuevo");
                    coord1 = sc.nextInt();
                    coord2 = sc.nextInt();
                }
                System.out.println("1)Movimiento vertical, 2) movimiento horizontal");
                movimiento = sc.nextInt();
                if (movimiento == 1) {
                    try {
                        do {
                            ban = 0;
                            System.out.println("ingrese su coordenada en Y: , si desea pasar su turno, ingrese -1");
                            vert = sc.nextInt();
                            if (vert < 0) {
                                System.out.println("pasando su turno, va el otro jugador");
                                turn = false;
                            }
                            if (!tabla[vert][coord1].equals("[ ]")) {
                                while (!tabla[vert][coord1].equals("[ ]")) {
                                    System.out.println("esta otra pieza ahi, ingrese otra coordenada");
                                    vert = sc.nextInt();
                                }
                            } else {
                                int dir = 0;
                                if (vert - coord1 < 0) {
                                    dir = -1;
                                } else {
                                    dir = 1;
                                }
                                for (int i = coord1; i < vert; i++) {
                                    if (!tabla[i][coord1].equals("[ ]")) {
                                        ban = 1;
                                        if (ban == 1) {
                                            break;
                                        }
                                    }
                                }
                                if (ban == 0) {
                                    tabla[vert][coord1] = " X ";
                                    tabla[coord2][coord1] = "[ ]";
                                } else {
                                    System.out.println("hay algo bloqueando la pasada, intente de nuevo");

                                }
                            }
                        } while (ban == 1);

                    } catch (InputMismatchException e) {
                        System.out.println("error en el input");
                    } catch (Exception e) {

                    }
                } else if (movimiento == 2) {
                    try {
                        do {
                            ban = 0;
                            System.out.println("ingrese su coordenada en X: , si desea no mover esta pieza, ingrese -1 para terminar su turno");
                            hori = sc.nextInt();
                            if (hori < 0) {
                                System.out.println("pasando turno al otro jugador");
                                turn = false;
                                ban = 0;
                            }
                            if (!tabla[coord2][hori].equals("[ ]")) {
                                while (!tabla[coord2][hori].equals("[ ]")) {
                                    System.out.println("esta otra pieza ahi, ingrese otra coordenada");
                                    hori = sc.nextInt();
                                }
                            } else {

                                int dir = 0;
                                if (hori - coord2 < 0) {
                                    dir = -1;
                                } else {
                                    dir = 1;
                                }
                                for (int i = coord2; i != hori; i += dir) {
                                    if (!tabla[coord2][i].equals("[ ]")) {
                                        ban = 1;
                                        if (ban == 1) {
                                            break;
                                        }
                                    }
                                }

                                if (ban == 0) {
                                    tabla[coord2][hori] = " X ";
                                    tabla[coord2][coord1] = "[ ]";
                                } else {
                                    System.out.println("hay algo bloqueando la pasada, intente de nuevo");
                                    ban = 0;
                                }
                            }
                        } while (ban == 1);
                    } catch (InputMismatchException e) {
                        System.out.println("occurio un error en el input");
                    } catch (Exception e) {
                    }

                } else {
                    System.out.println("movimento no valido, termina turno");
                }
                try {
                    for (int i = 1; i < tabla.length - 1; i++) {
                        for (int j = 1; j < tabla[0].length - 1; j++) {
                            if (tabla[i][j].equals(" 0 ")) {
                                if (tabla[i + 1][j].equals(" X ") && tabla[i - 1][j].equals(" X ") || tabla[i][j + 1].equals(" X ") && tabla[i][j - 1].equals(" X ")) {
                                    tabla[i][j] = "[ ]";
                                }
                                if (tabla[i + 2][j].equals(" X ") && tabla[i - 2][j].equals(" X ") && tabla[i + 1][j].equals(" 0 ")) {
                                    tabla[i][j] = "[ ]";
                                    tabla[i + 1][j] = "[ ]";
                                }
                                if (tabla[i][j + 2].equals(" X ") && tabla[i][j - 2].equals(" X ") && tabla[i][j + 1].equals(" 0 ")) {
                                    tabla[i][j] = "[ ]";
                                    tabla[i][j + 1] = "[ ]";
                                }

                                if (tabla[i + 3][j].equals(" X ") && tabla[i - 3][j].equals(" X ") && tabla[i + 2][j].equals(" 0 ")) {
                                    tabla[i][j] = "[ ]";
                                    tabla[i + 1][j] = "[ ]";
                                    tabla[i + 2][j] = "[ ]";
                                }
                                if (tabla[i][j + 3].equals(" X ") && tabla[i][j - 3].equals(" X ") && tabla[i][j + 2].equals(" 0 ")) {
                                    tabla[i][j] = "[ ]";
                                    tabla[i][j + 1] = "[ ]";
                                    tabla[i][j + 2] = "[ ]";

                                }

                            }
                        }
                    }
                } catch (Exception e) {
                }

                for (int i = 1; i < tabla.length - 1; i++) {
                    for (int j = 1; j < tabla[0].length - 1; j++) {
                        if (tabla[i][j].equals(" K ")) {
                            if (tabla[i + 1][j].equals(" X ") && tabla[i - 1][j].equals(" X ") || tabla[i][j + 1].equals(" X ") && tabla[i][j - 1].equals(" X ")) {
                                tabla[i][j] = "[ ]";
                                ganar = 1;
                            }
                        }
                    }
                }
                turn = false;
                ban = 0;
            } else {

                System.out.println("turno del defensor");
                imp(tabla, 0, 0);
                System.out.println();
                System.out.println("1)mover rey. algo mas es pieza. ingrese uno: ");
                m_rey = sc.nextInt();
                if (m_rey == 1) {
                    System.out.println("ingrese coordenada en Y y en X para seleccionar su pieza: ");
                    coord1 = sc.nextInt();
                    coord2 = sc.nextInt();
                    while (coord1 > tabla.length - 1 || coord2 > tabla.length - 1) {
                        System.out.println("Coordenadas no validas, ingrese de nuevo: ");

                    }
                    while (!tabla[coord1][coord2].equals(" K ")) {
                        System.out.println("pieza no valida, ingrese de nuevo");
                        coord1 = sc.nextInt();
                        coord2 = sc.nextInt();
                    }

                    System.out.println("1) movimiento vertica;, 2) movimiento horizontal");
                    movimiento = sc.nextInt();
                    if (movimiento == 1) {
                        System.out.println("ingrese su coordenada en Y: ");
                        vert = sc.nextInt();
                        if (!tabla[vert][coord2].equals("[ ]")) {
                            while (!tabla[vert][coord2].equals("[ ]")) {
                                System.out.println("esta otra pieza ahi, ingrese otra coordenada");
                                vert = sc.nextInt();
                            }
                        }
                        tabla[vert][coord2] = " K ";
                        tabla[coord1][coord2] = "[ ]";
                    } else if (movimiento == 2) {
                        System.out.println("ingrese su coordenada en X: ");
                        hori = sc.nextInt();
                        if (!tabla[coord1][hori].equals("[ ]")) {
                            while (!tabla[coord1][hori].equals("[ ]")) {
                                System.out.println("esta otra pieza ahi, ingrese otra coordenada");
                                hori = sc.nextInt();
                            }
                        }
                        tabla[coord1][hori] = " K ";
                        tabla[coord1][coord2] = "[ ]";

                    } else {
                        System.out.println("movimento no valido, termina turno");
                    }
                    if (tabla[0][0].equals(" K ") || tabla[0][1].equals(" K ") || tabla[0][2].equals(" K ") || tabla[0][3].equals(" K ") || tabla[0][4].equals(" K ") || tabla[0][5].equals(" K ") || tabla[0][6].equals(" K ")
                            || tabla[0][7].equals(" K ") || tabla[0][8].equals(" K ") || tabla[0][9].equals(" K ") || tabla[0][10].equals(" K ") || tabla[1][0].equals(" K ") || tabla[2][0].equals(" K ") || tabla[3][0].equals(" K ")
                            || tabla[4][0].equals(" K ") || tabla[5][0].equals(" K ") || tabla[6][0].equals(" K ") || tabla[7][0].equals(" K ") || tabla[8][0].equals(" K ") || tabla[9][0].equals(" K ") || tabla[10][0].equals(" K ")
                            || tabla[10][1].equals(" K ") || tabla[10][2].equals(" K ") || tabla[10][3].equals(" K ") || tabla[10][4].equals(" K ") || tabla[10][5].equals(" K ") || tabla[10][6].equals(" K ")
                            || tabla[10][7].equals(" K ") || tabla[10][8].equals(" K ") || tabla[10][9].equals(" K ") || tabla[10][10].equals(" K ") || tabla[1][10].equals(" K ") || tabla[2][10].equals(" K ") || tabla[3][10].equals(" K ")
                            || tabla[4][10].equals(" K ") || tabla[5][10].equals(" K ") || tabla[6][10].equals(" K ") || tabla[7][10].equals(" K ") || tabla[8][10].equals(" K ") || tabla[9][10].equals(" K ")) {
                        System.out.println("felicidades, gano el equipo defensor");
                        break;
                    }
                } else {
                    System.out.println("ingrese coordenada en x y en y para seleccionar su pieza: ");
                    coord1 = sc.nextInt();
                    coord2 = sc.nextInt();
                    while (coord1 > tabla.length - 1 || coord2 > tabla.length - 1) {
                        System.out.println("Coordenadas no validas, ingrese de nuevo: ");

                    }
                    while (!tabla[coord2][coord1].equals(" 0 ")) {
                        System.out.println("pieza no valida, ingrese de nuevo");
                        coord1 = sc.nextInt();
                        coord2 = sc.nextInt();
                    }
                    System.out.println("1)Movimiento vertical, 2) movimiento horizontal");
                    movimiento = sc.nextInt();
                    if (movimiento == 1) {
                        try {
                            do {
                                ban = 0;
                                System.out.println("ingrese su coordenada en Y: , si desea pasar su turno, ingrese -1");
                                vert = sc.nextInt();
                                if (vert < 0) {
                                    System.out.println("pasando su turno, va el otro jugador");
                                    turn = false;
                                }
                                if (!tabla[vert][coord1].equals("[ ]")) {
                                    while (!tabla[vert][coord1].equals("[ ]")) {
                                        System.out.println("esta otra pieza ahi, ingrese otra coordenada");
                                        vert = sc.nextInt();
                                    }
                                } else {
                                    for (int i = coord1; i < vert; i++) {
                                        if (!tabla[i][coord1].equals("[ ]")) {
                                            ban = 1;
                                            if (ban == 1) {
                                                break;
                                            }
                                        }
                                    }
                                    if (ban == 0) {
                                        tabla[vert][coord1] = " 0 ";
                                        tabla[coord2][coord1] = "[ ]";
                                    } else {
                                        System.out.println("hay algo bloqueando la pasada, intente de nuevo");
                                        ban = 0;
                                    }
                                }
                            } while (ban == 1);

                        } catch (InputMismatchException e) {
                            System.out.println("error en el input");
                        } catch (Exception e) {

                        }
                    } else if (movimiento == 2) {
                        try {
                            do {
                                ban = 0;
                                System.out.println("ingrese su coordenada en X: , si desea no mover esta pieza, ingrese -1 para terminar su turno");
                                hori = sc.nextInt();
                                if (hori < 0) {
                                    System.out.println("pasando turno al otro jugador");
                                    turn = false;
                                    ban = 0;
                                }
                                if (!tabla[coord2][hori].equals("[ ]")) {
                                    while (!tabla[coord2][hori].equals("[ ]")) {
                                        System.out.println("esta otra pieza ahi, ingrese otra coordenada");
                                        hori = sc.nextInt();
                                    }
                                } else {

                                    for (int i = coord2; i < hori; i++) {
                                        if (!tabla[coord2][i].equals("[ ]")) {
                                            ban = 1;
                                            if (ban == 1) {
                                                break;
                                            }
                                        }
                                    }
                                    if (ban == 0) {
                                        tabla[coord2][hori] = " 0 ";
                                        tabla[coord2][coord1] = "[ ]";
                                    } else {
                                        System.out.println("hay algo bloqueando la pasada, intente de nuevo");
                                    }
                                }
                            } while (ban == 1);
                        } catch (InputMismatchException e) {
                            System.out.println("occurio un error en el input");
                        } catch (Exception e) {
                        }

                    } else {
                        System.out.println("movimento no valido, termina turno");
                    }
                    for (int i = 1; i < tabla.length - 1; i++) {
                        for (int j = 1; j < tabla[0].length - 1; j++) {
                            if (tabla[i][j].equals(" X ")) {
                                if (tabla[i + 1][j].equals(" 0 ") && tabla[i - 1][j].equals(" 0 ") || tabla[i][j + 1].equals(" 0 ") && tabla[i][j - 1].equals(" 0 ")
                                        || tabla[i + 1][j].equals(" K ") && tabla[i - 1][j].equals(" 0 ") || tabla[i][j + 1].equals(" K ") && tabla[i][j - 1].equals(" 0 ")
                                        || tabla[i + 1][j].equals(" 0 ") && tabla[i - 1][j].equals(" K ") || tabla[i][j + 1].equals(" 0 ") && tabla[i][j - 1].equals(" K ")) {
                                    tabla[i][j] = "[ ]";
                                }
                                if (tabla[i + 2][j].equals(" 0 ") && tabla[i - 2][j].equals(" 0 ") && tabla[i + 1][j].equals(" X ")) {
                                    tabla[i][j] = "[ ]";
                                    tabla[i + 1][j] = "[ ]";
                                }
                                if (tabla[i][j + 2].equals(" 0 ") && tabla[i][j - 2].equals(" 0 ") && tabla[i][j + 1].equals(" X ")) {
                                    tabla[i][j] = "[ ]";
                                    tabla[i][j + 1] = "[ ]";
                                }

                                if (tabla[i + 3][j].equals(" 0 ") && tabla[i - 3][j].equals(" 0 ") && tabla[i + 2][j].equals(" X ")) {
                                    tabla[i][j] = "[ ]";
                                    tabla[i + 1][j] = "[ ]";
                                    tabla[i + 2][j] = "[ ]";
                                }
                                if (tabla[i][j + 3].equals(" 0 ") && tabla[i][j - 3].equals(" 0 ") && tabla[i][j + 2].equals(" X ")) {
                                    tabla[i][j] = "[ ]";
                                    tabla[i][j + 1] = "[ ]";
                                    tabla[i][j + 2] = "[ ]";

                                }
                            }
                        }
                    }
                }
                turn = true;
            }
        }
        if (ganar == 1) {
            System.out.println("gano los atacantes");
        } else {
            System.out.println("gano los defensores");
        }
    }

}
