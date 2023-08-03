package grafos;

import java.util.*;

public class Grafo {
    List<Vertice> vertices;
    private List<Arista> aristas;

    public Grafo(int numVertices) {
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

    // Implementación del algoritmo de Dijkstra para obtener el camino más corto desde un vértice inicial
    public int[] dijkstra(Vertice verticeInicial) {
        int numVertices = vertices.size();
        int[] distancias = new int[numVertices];
        Arrays.fill(distancias, Integer.MAX_VALUE);
        distancias[vertices.indexOf(verticeInicial)] = 0;

        List<Vertice> noVisitados = new ArrayList<>(vertices);

        while (!noVisitados.isEmpty()) {
            int indiceVerticeActual = obtenerIndiceVerticeConDistanciaMinima(distancias, noVisitados);
            Vertice verticeActual = noVisitados.remove(indiceVerticeActual);

            List<Vertice> adyacentes = obtenerVerticesAdyacentes(verticeActual);
            for (Vertice adyacente : adyacentes) {
                int distanciaNueva = distancias[vertices.indexOf(verticeActual)] + obtenerPesoArista(verticeActual, adyacente);
                int indiceAdyacente = vertices.indexOf(adyacente);
                if (distanciaNueva < distancias[indiceAdyacente]) {
                    distancias[indiceAdyacente] = distanciaNueva;
                }
            }
        }

        return distancias;
    }

    // Implementación del recorrido en profundidad (DFS)
    public void dfs(Vertice verticeInicial) {
        boolean[] visitados = new boolean[vertices.size()];
        dfsRecursivo(verticeInicial, visitados);
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
        }
    }


    // Método auxiliar para obtener el índice del vértice no visitado con la distancia más pequeña
    private int obtenerIndiceVerticeConDistanciaMinima(int[] distancias, List<Vertice> noVisitados) {
        int minDistancia = Integer.MAX_VALUE;
        int indice = -1;
        for (int i = 0; i < distancias.length; i++) {
            if (noVisitados.contains(vertices.get(i)) && distancias[i] < minDistancia) {
                minDistancia = distancias[i];
                indice = i;
            }
        }
        return indice;
    }

    // Método auxiliar para obtener el peso de una arista entre dos vértices
    private int obtenerPesoArista(Vertice origen, Vertice destino) {
        for (Arista arista : aristas) {
            if (arista.getOrigen().equals(origen) && arista.getDestino().equals(destino)) {
                return arista.getPeso();
            }
        }
        return Integer.MAX_VALUE;
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
                List<Vertice> adyacentes = obtenerVerticesAdyacentes(verticeActual);
                for (Vertice adyacente : adyacentes) {
                    int indexAdyacente = vertices.indexOf(adyacente);
                    if (!visitados[indexAdyacente]) {
                        visitados[indexAdyacente] = true;
                        nivel[indexAdyacente] = nivel[indexVerticeInicial] + 1;
                        cola.add(adyacente);
                        maxProfundidad = Math.max(maxProfundidad, nivel[indexAdyacente]);
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
