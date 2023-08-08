package grafos;

import java.util.*;

public class GrafoMatriz {
    List<Vertice> vertices;
    private List<Arista> aristas;
	public int numVerts;
	public int[][] matAd;

    public GrafoMatriz(int numVertices) {
        vertices = new ArrayList<>();
        aristas = new ArrayList<>();
    }

    public void agregarVertice(Vertice vertice) {
        vertices.add(vertice);
    }

    public void agregarArista(Vertice origen, Vertice destino, int peso) {
        aristas.add(new Arista(origen, destino, peso));
        // Si es un grafo no dirigido, agregar también la arista inversa
        aristas.add(new Arista(destino, origen, peso));
    }
   
    public List<Arista> obtenerAristas() {
        return aristas;
    }

    public List<Vertice> obtenerVerticesAdyacentes(Vertice vertice) {
        List<Vertice> adyacentes = new ArrayList<>();
        for (Arista arista : aristas) {
            if (arista.getOrigen().equals(vertice)) {
                adyacentes.add(arista.getDestino());
            }
        }
        return adyacentes;
    }

 // Implementación del recorrido en profundidad (DFS)
    public void dfs(Vertice verticeInicial) {
        boolean[] visitados = new boolean[vertices.size()];
        dfsRecursivo(verticeInicial, visitados);
        System.out.println();
    }

    private void dfsRecursivo(Vertice vertice, boolean[] visitados) {
        int indexVertice = vertices.indexOf(vertice);
        if (indexVertice != -1) {
            visitados[indexVertice] = true;
            System.out.print(vertice + " ");

            List<Vertice> adyacentes = obtenerVerticesAdyacentes(vertice);
            for (Vertice adyacente : adyacentes) {
                int indexAdyacente = vertices.indexOf(adyacente);
                if (!visitados[indexAdyacente]) {
                    dfsRecursivo(adyacente, visitados);
                }
            }
        }
    }

    // Implementación del recorrido en anchura (BFS)
    public void bfs(Vertice verticeInicial) {
        boolean[] visitados = new boolean[vertices.size()];
        Queue<Vertice> cola = new LinkedList<>();

        int indexVerticeInicial = vertices.indexOf(verticeInicial);
        if (indexVerticeInicial != -1) {
            visitados[indexVerticeInicial] = true;
            cola.add(verticeInicial);

            while (!cola.isEmpty()) {
                Vertice verticeActual = cola.poll();
                System.out.print(verticeActual + " ");

                List<Vertice> adyacentes = obtenerVerticesAdyacentes(verticeActual);
                for (Vertice adyacente : adyacentes) {
                    int indexAdyacente = vertices.indexOf(adyacente);
                    if (!visitados[indexAdyacente]) {
                        visitados[indexAdyacente] = true;
                        cola.add(adyacente);
                    }
                }
            }
            System.out.println();
        }
    }

    public int obtenerProfundidad(Vertice verticeInicial) {
        int indexVerticeInicial = vertices.indexOf(verticeInicial);
        if (indexVerticeInicial != -1) {
            boolean[] visitados = new boolean[vertices.size()];
            int[] nivel = new int[vertices.size()];
            Queue<Vertice> cola = new LinkedList<>();

            visitados[indexVerticeInicial] = true;
            nivel[indexVerticeInicial] = 0;
            cola.add(verticeInicial);

            int maxProfundidad = 0;

            while (!cola.isEmpty()) {
                Vertice verticeActual = cola.poll();
                int nivelActual = nivel[vertices.indexOf(verticeActual)];

                maxProfundidad = Math.max(maxProfundidad, nivelActual);

                List<Vertice> adyacentes = obtenerVerticesAdyacentes(verticeActual);
                for (Vertice adyacente : adyacentes) {
                    int indexAdyacente = vertices.indexOf(adyacente);
                    if (!visitados[indexAdyacente]) {
                        visitados[indexAdyacente] = true;
                        nivel[indexAdyacente] = nivelActual + 1;
                        cola.add(adyacente);
                    }
                }
            }

            return maxProfundidad;
        }

        return -1;
    }

    public int obtenerAnchura(Vertice verticeInicial) {
        int indexVerticeInicial = vertices.indexOf(verticeInicial);
        if (indexVerticeInicial != -1) {
            boolean[] visitados = new boolean[vertices.size()];
            Queue<Vertice> cola = new LinkedList<>();

            visitados[indexVerticeInicial] = true;
            cola.add(verticeInicial);

            int maxAnchura = 0;

            while (!cola.isEmpty()) {
                int nivelSize = cola.size();
                maxAnchura = Math.max(maxAnchura, nivelSize);

                for (int i = 0; i < nivelSize; i++) {
                    Vertice verticeActual = cola.poll();
                    List<Vertice> adyacentes = obtenerVerticesAdyacentes(verticeActual);
                    for (Vertice adyacente : adyacentes) {
                        int indexAdyacente = vertices.indexOf(adyacente);
                        if (!visitados[indexAdyacente]) {
                            visitados[indexAdyacente] = true;
                            cola.add(adyacente);
                        }
                    }
                }
            }

            return maxAnchura;
        }

        return -1;
    }
    
}
