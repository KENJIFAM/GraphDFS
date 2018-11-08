# GraphDFS
This application implement a maze resolving algorithm by using undirected graph with Depth First Search method. <br>
More info: https://algs4.cs.princeton.edu/41graph/

## Methods:
- `int nodes()`: return how many nodes a graph has <br>
- `boolean readGraph(File file)`: read a graph from the file <br>
- `void printGraph()`: print a graph <br>
- `void dfs(int start, boolean visited[], int pred[])`: Depth First Search 
in which start is the node from where the search begins, 
visited is an array of all the visited nodes, 
pred is an describing the search path <br>
- `int mazeSolution(int from, int to, int pred[], int steps[])`: find a maze solution,
return how many vertices from the start to the end <br>
