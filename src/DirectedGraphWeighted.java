import java.util.HashSet;

public class DirectedGraphWeighted {
    public HashSet<Vertex>[] neighbours;
    public int vertexCapacity;
    public int edgeQuantity;

    /* TODO Initialize de DirectedGraph */
    public void initialize(int numNodes) {
        neighbours = new HashSet[numNodes];
        for (int i = 0; i < numNodes; i++)
            neighbours[i] = new HashSet();

        vertexCapacity = numNodes;
        edgeQuantity = 0;
    }

    /*TODO Create an edge between the vertices - Veuillez vous referez aux notes de cours */
    public void connect(int v1, Vertex vertex){
        if (v1<0 || v1>=vertexCapacity)
            return;
        if (neighbours[v1].contains(vertex))
            return;

        // connect edge from v1 to v2
        neighbours[v1].add(vertex);
        edgeQuantity++;
    }

    /* TODO Print all the edges connecting vertices*/
    public String toString(){
        StringBuilder o = new StringBuilder();
        String ln = System.getProperty("line.separator");
        o.append(vertexCapacity).append(ln).append(edgeQuantity).append(ln);
        for (int v=0; v<vertexCapacity; v++)
            for (Vertex w : neighbours[v])
                o.append(v + "->" + w.cost + ln);

        return o.toString();
    }

    /* TODO Return a HashMap of adjacent edges / vertices */
    public HashSet<Vertex> adj(int v) {
        if (v<0 || v>=vertexCapacity)
            return null;

        return neighbours[v];
    }

    public DirectedGraphWeighted(int numNodes){
        initialize(numNodes);
    }

    public int findLowestCost() {
        /* NE PAS MODIFIER CE CODE */
        int totalCost = 0;

        Heap vertices = new Heap(vertexCapacity + 1);
        /* NE PAS MODIFIER CE CODE */

        /* TODO Add all of the vertices to the Heap start at Index 1. The default cost should be the largest possible value for an integer */
        for (int i = 1; i < vertexCapacity; i++)
            vertices.add(new Vertex(Integer.MAX_VALUE, i));

        int compt = 0;
        while (compt < vertexCapacity) {
            compt++;
            Vertex v = vertices.findSmallestUnknown();
            if (v == null)
                break;
            v.known = true;

            /* Nombre d'itérations maximale: vertexCapacity (si le sommet v est adjacent à tous les autres sommets du graphe)
                Nombre d'itérations minimale: 0 (si le sommet v n'a pas d'adjacents)
                Nombre de modifications du coût d'un sommet dans le pire cas: vertexCapacity - 1 (le sommet est adjacent à
                tous les autres sommets du graphe et ces sommets sont parcourus ne ordre décroissant de leur coût).
             */
            for (Vertex w : adj(v.index))
                /* TODO Decrease the cost of the vertex in the Heap using decreaseKey if conditions are met */
                if (!w.known)
                    if (v.cost + w.cost < vertices.Heap[w.index + 1].cost) {
                        vertices.decreaseKey(w, v.cost + w.cost);
                        w.path = v;
                    }
        }

        /*TODO Add up the total cost of the elements in the Heap */

        /* Nombre d'itérations pour :
           - 10 sommets : 9
           - 100 sommets : 99
           - 1000 sommets : 999
         */
        for (int i = 1; i < vertices.Heap.length; ++i)
            totalCost += vertices.Heap[i].cost;

        return totalCost;
    }
}
