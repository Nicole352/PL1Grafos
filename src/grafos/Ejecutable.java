package grafos;

import java.util.Scanner;

public class Ejecutable {

	 public static void main(String[] args) {
		 
	        Scanner scanner = new Scanner(System.in);

	        System.out.print("Ingrese el número de vértices del grafo: ");
	        int numVertices = scanner.nextInt();
	        Grafo grafo = new Grafo(numVertices);

	        System.out.println("Ingrese los valores de los vértices:");
	        for (int i = 0; i < numVertices; i++) {
	            int valor = scanner.nextInt();
	            grafo.agregarVertice(new Vertice(valor));
	        }

	        System.out.println("Ingrese las aristas (origen y destino) separadas por espacio (Ingrese -1 -1 para terminar):");
	        while (true) {
	            int origen = scanner.nextInt();
	            int destino = scanner.nextInt();
	            if (origen == -1 || destino == -1) {
	                break;
	            }
	            grafo.agregarArista(grafo.vertices.get(origen), grafo.vertices.get(destino), destino);
	        }

	        System.out.println("Recorrido DFS desde el vértice 0:");
	        grafo.dfs(grafo.vertices.get(0));
	        System.out.println();

	        System.out.println("Recorrido BFS desde el vértice 0:");
	        grafo.bfs(grafo.vertices.get(0));
	        System.out.println();


	        System.out.println("Profundidad del grafo desde el vértice 0: " + grafo.obtenerProfundidad(grafo.vertices.get(0)));

	        System.out.println("Anchura del grafo desde el vértice 0: " + grafo.obtenerAnchura(grafo.vertices.get(0)));
	        
	        scanner.close();
	    }
	}

