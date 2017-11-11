import java.awt.Point;
import java.util.*;

public class ShortestPath {
    public static int N,M,Q,S;

    public static ArrayList<Point>[] edges;

    public static int[] bestCosts;

    public static void dijkstra(int start) {
        bestCosts = new int[N];
        Arrays.fill(bestCosts, Integer.MAX_VALUE);

        PriorityQueue<Point> nodesToTry = new PriorityQueue<Point>(new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                if (o1.y != o2.y)
                    return o1.y-o2.y;
                return o1.x-o2.x;
            }
        });

        nodesToTry.add(new Point(start,0));

        while (!nodesToTry.isEmpty()) {
            Point p = nodesToTry.remove();
            int node = p.x;
            int cost = p.y;

            if (cost >= bestCosts[node])
                continue;
            bestCosts[node] = cost;

            for (Point p2 : edges[node]) {
                int destNode = p2.x;
                int edgeCost = p2.y;
                nodesToTry.add(new Point(destNode, cost + edgeCost));
            }
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        while(true) {
            N = in.nextInt();
            M = in.nextInt();
            Q = in.nextInt();
            S = in.nextInt();

            if (N == 0)
                break;

            edges = new ArrayList[N];
            for (int i = 0; i < N; i++)
                edges[i] = new ArrayList<Point>();


            // Read edges
            for (int i = 0; i < M; i++) {
                int u, v, w;
                u = in.nextInt();
                v = in.nextInt();
                w = in.nextInt(); 

                edges[u].add(new Point(v, w));
            }

            dijkstra(S);

            for (int i = 0; i < Q; i++) {
                int dest = in.nextInt();
                if (bestCosts[dest] == Integer.MAX_VALUE)
                    System.out.println("Impossible");
                else
                    System.out.println(bestCosts[dest]);
            }
            System.out.println();
        }
    }
}