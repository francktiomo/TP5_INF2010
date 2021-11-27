public class Tests {
    UndirectedGraph undirectedGraph;
    DirectedGraphWeighted directedGraphWeighted;
    Vertex vertex;
    Interview interview;
    Heap heap;

    Tests(UndirectedGraph undirectedGraph, DirectedGraphWeighted directedGraphWeighted, Vertex vertex, Interview interview){
        this.undirectedGraph = undirectedGraph;
        this.directedGraphWeighted = directedGraphWeighted;
        this.vertex = vertex;
        this.interview = interview;
        runTests();
    }

    public void runTests(){
        undirectedGraphTests();
        vertexTests();
        DirectedGraphWeightedTests();
        HeapTests();
        DijkstraTests();
    }

    public void undirectedGraphTests(){
        undirectedGraphConnectTest();
    }

    public void undirectedGraphConnectTest(){
        int val = undirectedGraph.graphEdges;
        undirectedGraph.connect(0,1);
        if(val != undirectedGraph.graphEdges){
            System.out.println("UndirectedGraphConnectTest failed");
            return;
        }
        System.out.println("UndirectedGraphConnectTest passed.");
    }

    public void vertexTests(){
        vertexCompareToTest();
    }

    public void vertexCompareToTest(){
        Vertex m = new Vertex(12, 0);
        Vertex n = new Vertex(9, 0);
        Vertex q = new Vertex(10, 0);
        if(m.compareTo(vertex) > 0 && n.compareTo(vertex) < 0 && q.compareTo(vertex) == 0){
            System.out.println("VertexCompareToTest passed");
            return;
        }
        System.out.println("VertexCompareToTest failed");
    }

    public void DirectedGraphWeightedTests(){
        directedGraphInitializeTest();
        directedGraphConnectTest();
        directedGraphAdjTest();
    }

    public void directedGraphInitializeTest(){
        DirectedGraphWeighted newGraph = new DirectedGraphWeighted(10);
        if(newGraph.edgeQuantity == 0 && newGraph.vertexCapacity == 10 && newGraph.neighbours.length == 10){
            System.out.println("directedGraphInitializeTest passed");
            return;
        }
        System.out.println("directedGraphInitializeTest failed");
    }

    public void directedGraphConnectTest(){
        DirectedGraphWeighted newGraph = new DirectedGraphWeighted(3);
        Vertex testVertex =  new Vertex(3,2);
        newGraph.connect(0,new Vertex(3,1));
        newGraph.connect(0, testVertex);
        newGraph.connect(2, new Vertex(3,0));
        int connections = newGraph.edgeQuantity;
        newGraph.connect(0, testVertex);
        if(newGraph.edgeQuantity > connections){
            System.out.println("directedGraphConnectTest failed");
            return;
        }
        System.out.println("directedGraphConnectTest passed");
    }

    public void directedGraphAdjTest(){
        Object[] list = directedGraphWeighted.adj(0).toArray();
        Object[] comparedList = directedGraphWeighted.neighbours[0].toArray();
        for(int i = 0; i < directedGraphWeighted.neighbours[0].size(); i++){
            if(comparedList[i] != list[i]){
                System.out.println("directedGraphAdjTest failed");
                return;
            }
        }
        System.out.println("directedGraphAdjTest passed");
    }

    public void HeapTests(){
        heap = new Heap(4);
        Vertex v = new Vertex(Integer.MAX_VALUE,1);
        Vertex v2 = new Vertex(Integer.MAX_VALUE,2);
        heap.add(v);
        heap.add(v2);
        decreaseKeyHeapTest();
        v.known = true;
        findSmallestUnknownTest();
    }

    public void decreaseKeyHeapTest(){
        int v = 1;
        int index = Integer.MAX_VALUE;
        for(int i = 1; i < 3;i++){
            if(v == heap.Heap[i].index){
              index = i;
              break;
            }
        }
        heap.decreaseKey(new Vertex(1,1), 1);
        if(heap.Heap[index].cost == 1){
            System.out.println("decreaseKeyHeapTest passed");
            return;
        }
        System.out.println("decreaseKeyHeapTest failed");
    }

    public void findSmallestUnknownTest(){
        heap.Heap[1].known = true;
        Vertex v = heap.findSmallestUnknown();
        if(v.index == 2 && v.cost == Integer.MAX_VALUE){
            System.out.println("findSmallestUnknownTest passed");
            return;
        }
        System.out.println("findSmallestUnknownTest failed");
    }

    public void DijkstraTests(){
        DijkstraTest1();
    }

    public void DijkstraTest1(){
        DirectedGraphWeighted newGraph = new DirectedGraphWeighted(3);
        newGraph.connect(0, new Vertex(1, 1));
        newGraph.connect(1, new Vertex(1,2));
        if(newGraph.findLowestCost() == 3){
            System.out.println("DijkstraTest1 passed");
            return;
        }
        System.out.println("DijkstraTest1 failed");
    }

}