package grafos;

import java.util.*;

public class Ejecutable {
	public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese el número de vértices: ");
        int numVertices = scanner.nextInt();
        GrafoMatriz grafo = new GrafoMatriz(numVertices);

        for (int i = 0; i < numVertices; i++) {
            grafo.agregarVertice(new Vertice(i));
        }

        while (true) {
            System.out.println("\nMenú:");
            System.out.println("1. Agregar arista");
            System.out.println("2. Realizar DFS");
            System.out.println("3. Realizar BFS");
            System.out.println("4. Obtener profundidad desde un vértice");
            System.out.println("5. Obtener anchura desde un vértice");
            System.out.println("6. Encontrar camino mínimo");
            System.out.println("7. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese vértice origen: ");
                    int origen = scanner.nextInt();
                    System.out.print("Ingrese vértice destino: ");
                    int destino = scanner.nextInt();
                    System.out.print("Ingrese peso: ");
                    int peso = scanner.nextInt();
                    grafo.agregarArista(new Vertice(origen), new Vertice(destino), peso);
                    break;
                case 2:
                    System.out.print("Ingrese vértice inicial para DFS: ");
                    int verticeDFS = scanner.nextInt();
                    System.out.print("Recorrido DFS desde el vértice " + verticeDFS + ": ");
                    grafo.dfs(new Vertice(verticeDFS));
                    break;
                case 3:
                    System.out.print("Ingrese vértice inicial para BFS: ");
                    int verticeBFS = scanner.nextInt();
                    System.out.print("Recorrido BFS desde el vértice " + verticeBFS + ": ");
                    grafo.bfs(new Vertice(verticeBFS));
                    break;
                case 4:
                    System.out.print("Ingrese vértice inicial para obtener profundidad: ");
                    int verticeProfundidad = scanner.nextInt();
                    int profundidad = grafo.obtenerProfundidad(new Vertice(verticeProfundidad));
                    System.out.println("La profundidad desde el vértice " + verticeProfundidad + " es: " + profundidad);
                    break;
                case 5:
                    System.out.print("Ingrese vértice inicial para obtener anchura: ");
                    int verticeAnchura = scanner.nextInt();
                    int anchura = grafo.obtenerAnchura(new Vertice(verticeAnchura));
                    System.out.println("La anchura desde el vértice " + verticeAnchura + " es: " + anchura);
                    break;
                case 6:
                    System.out.print("Ingrese vértice origen para encontrar camino mínimo: ");
                    int verticeOrigen = scanner.nextInt();
                    CaminoMinimo caminoMinimo = new CaminoMinimo(grafo, verticeOrigen);
                    caminoMinimo.caminoMinimos();

                    System.out.print("Ingrese vértice destino: ");
                    int verticeDestino = scanner.nextInt();
                    System.out.print("Camino mínimo desde " + verticeOrigen + " a " + verticeDestino + ": ");
                    caminoMinimo.recuperaCamino(verticeDestino);
                    System.out.println(" con un costo total de " + caminoMinimo.D[verticeDestino]);
                    break;
                case 7:
                    System.out.println("Saliendo del programa...");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Opción inválida. Intente nuevamente.");
            }
        }
    }
}