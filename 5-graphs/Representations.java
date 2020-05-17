import java.util.Random;
import java.util.List;
import java.util.ArrayList;
import java.util.Queue;
import java.util.PriorityQueue;
import java.util.Arrays;

public class Representations {
  public static void main(String[] args) {
    System.out.println("Starting Graph Stuff");


    Graph g = Graph.getRandomGraph(20, 20);
    System.out.println(g);
    g.dfs(0);
    // if (found) {
    //   System.out.println("Found it");
    // } else {
    //   System.out.println("Could not find it!");
    // }
    // int start = 0;
    // int end = 9;
    // System.out.println("min pathLength from " + start + " -> " + end + ": " + g.bfs(start, end));
  }
}

class Graph {
  int nodeCount;
  int edgeCount;
  List<List<Integer>> adjList = new ArrayList<List<Integer>>();

  static Random random = new Random();

  public void dfs(int startNode) {
    int[] nodeState = new int[nodeCount];
    int[] nodeEntry = new int[nodeCount];
    int[] nodeExit = new int[nodeCount];
    int[] nodeDepth = new int[nodeCount];

    dfs(startNode, nodeState, nodeEntry, nodeExit, nodeDepth, 0);
    System.out.println();
    System.out.println(Arrays.toString(nodeEntry));
    System.out.println(Arrays.toString(nodeExit));

  }

  public int dfs(int startNode, int[] nodeState,
    int[] nodeEntry, int[] nodeExit, int[] nodeDepth, int time) {
    String depthPad = "";
    for (int i = 0; i < nodeDepth[startNode]; i++) {
      depthPad += "  ";
    }
    System.out.print(depthPad);
    System.out.println(startNode + "");
    time++;
    nodeEntry[startNode] = time;

    nodeState[startNode] = 1;
    List<Integer> startNodeList = adjList.get(startNode);
    for (Integer nodeNum : startNodeList) {
      if (nodeState[nodeNum] == 0) {
        nodeDepth[nodeNum] = nodeDepth[startNode] + 1;
        time = dfs(nodeNum, nodeState, nodeEntry, nodeExit, nodeDepth, time);
      }
    }

    time++;
    nodeExit[startNode] = time;
    nodeState[startNode] = 2;
    System.out.print(depthPad);
    System.out.println(startNode + "");
    return time;
  }

  public int bfs(int startNode, int endNode) {
    int[] nodeState = new int[nodeCount];
    int[] nodeDistance = new int[nodeCount];
    int[] nodeParent = new int[nodeCount];
    // 0 - undiscovered
    // 1 - discovered
    // 2 - processed

    Queue<Integer> q = new PriorityQueue<Integer>();
    q.add(startNode);
    nodeDistance[startNode] = 0;
    nodeParent[startNode] = -1;
    int pathLength = 0;
    int iterations = 0;
    while (!q.isEmpty()) {
      int nodeNumber = q.poll();
      iterations++;

      nodeState[nodeNumber] = 1;
      List<Integer> neighbors = adjList.get(nodeNumber);

      for (Integer neighborNumber : neighbors) {
        if (nodeState[neighborNumber] == 0) {
          q.add(neighborNumber);
          nodeState[neighborNumber] = 1;
          nodeDistance[neighborNumber] = nodeDistance[nodeNumber] + 1;
          nodeParent[neighborNumber] = nodeNumber;

          if (neighborNumber == endNode) {
            System.out.println("iterations: " + iterations);
            String shortestPath = getShortestPath(nodeParent, neighborNumber);
            System.out.println(shortestPath);

            return nodeDistance[neighborNumber];
          }
        }
      }
      nodeState[nodeNumber] = 2;
    }

    return -1; // No path found
  }

  public static String getShortestPath(int[] nodeParent, int lastNode) {
    String shortestPath = "" + lastNode;
    int parentNodeNumber = lastNode;

    while (nodeParent[parentNodeNumber] != -1) {
      shortestPath = nodeParent[parentNodeNumber] + " -> " + shortestPath;
      parentNodeNumber = nodeParent[parentNodeNumber];
    }

    return shortestPath;
  }

  public Graph(int nodeCount, int edgeCount) {
    this.nodeCount = nodeCount;
    this.edgeCount = edgeCount;
    for (int i = 0; i < nodeCount; i++) {
      adjList.add(new ArrayList<Integer>());
    }
  }

  public static Graph getRandomGraph(int nodeCount, int edgeCount) {
    Graph g = new Graph(nodeCount, edgeCount);

    for (int i = 0; i < edgeCount; i++) {
      try {
        Edge edge = Edge.getRandomEdge(nodeCount);
        g.addEdge(edge);
      } catch (Exception e) {
        System.out.println(e);
        System.out.println("Graph too small");
      }
    }

    return g;
  }

  public static Graph getRandomGraph(int nodeCount) {
    int loadFactor = random.nextInt(nodeCount - 1);
    int edgeCount = nodeCount * loadFactor;

    return getRandomGraph(nodeCount, edgeCount);
  }

  public void addEdge(Edge e) {
    addToList(e.from, e.to);
    addToList(e.to, e.from);
  }

  public void addToList(int from, int to) {
    if (!adjList.get(from).contains(to)) {
      adjList.get(from).add(to);
    }
  }

  public String toString() {
    int lineSize = 60;
    String title = "Displaying Graph Adjacency List";
    String spacer = "=";
    String result = "";
    String spacerLine = "";

    for (int i = 0; i < lineSize; i++) {
      spacerLine += spacer;
    }

    int leftSize = (lineSize - title.length())/2;
    int rightSize = lineSize - leftSize - title.length();

    for (int i = 0; i < lineSize; i++) {
      if (i < leftSize) {
        title = spacer + title;
      } else if (i >= lineSize - rightSize) {
        title += spacer;
      }
    }
    result += spacerLine + "\n";
    result += title + "\n";
    result += spacerLine + "\n";

    result += "Nodes: " + nodeCount + "\n";
    result += "Edges: " + edgeCount + "\n";

    for (int i = 0; i < nodeCount; i++) {
      List<Integer> nodeList = adjList.get(i);
      result += "Node " + i + " ::: ";
      for (int j = 0; j < nodeList.size(); j++) {
        result += nodeList.get(j);
        if (j != nodeList.size() - 1) {
          result += " -> ";
        }
      }

      result += "\n";
    }

    return result;
  }
}


class Edge {
  int from;
  int to;

  static Random random = new Random();

  public Edge(int from, int to) {
    this.from = from;
    this.to = to;
  }

  public static Edge getRandomEdge(int nodeCount) throws Exception {
    if (nodeCount <= 1) {
      throw new Exception();
    }
    int from = random.nextInt(nodeCount);
    int to = random.nextInt(nodeCount);

    while (from == to) {
      to = random.nextInt(nodeCount);
    }

    return new Edge(from, to);
  }
}