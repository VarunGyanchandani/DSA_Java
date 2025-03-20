class UF {
    int[] parent;
    int[] rank;

    public UF(int n) {
        parent = new int[n];
        rank = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
    }

    public int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    public void union(int x, int y) {
        int xr = find(x);
        int yr = find(y);
        if (xr == yr) {
            return;
        }
        if (rank[xr] < rank[yr]) {
            parent[xr] = yr;
        } else if (rank[xr] > rank[yr]) {
            parent[yr] = xr;
        } else {
            parent[yr] = xr;
            rank[xr]++;
        }
    }
}

class Solution {
    public int[] minimumCost(int n, int[][] edges, int[][] query) {
        UF uf = new UF(n);
        for (int[] edge : edges) {
            uf.union(edge[0], edge[1]);
        }

        int fullMask = (1 << 18) - 1;
        java.util.Map<Integer, Integer> compMask = new java.util.HashMap<>();
        for (int[] edge : edges) {
            int comp = uf.find(edge[0]);
            compMask.putIfAbsent(comp, fullMask);
            compMask.put(comp, compMask.get(comp) & edge[2]);
        }

        int[] ans = new int[query.length];
        for (int i = 0; i < query.length; i++) {
            int s = query[i][0];
            int t = query[i][1];
            if (uf.find(s) != uf.find(t)) {
                ans[i] = -1;
            } else {
                int comp = uf.find(s);
                ans[i] = compMask.getOrDefault(comp, -1);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int n = 6;
        int[][] edges = {{0, 1, 3}, {1, 2, 5}, {3, 4, 2}, {4, 5, 4}};
        int[][] query = {{0, 2}, {0, 3}, {3, 5}, {0, 4}, {0, 5}, {5, 2}};

        Solution solution = new Solution();
        int[] result = solution.minimumCost(n, edges, query);

        System.out.println(java.util.Arrays.toString(result));
    }
}