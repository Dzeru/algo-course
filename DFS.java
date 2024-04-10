import java.util.HashSet;
import java.util.Set;
import java.util.Stack;
import java.util.stream.Collectors;

public class DFS {
    public static void main(String[] args) {
        Node<String> home = new Node<>("Дом");
        Node<String> apricot = new Node<>("Абрикосовая");
        home.addNeighbor(apricot);

        Node<String> grape = new Node<>("Виноградная");
        apricot.addNeighbor(grape);

        Node<String> shadow = new Node<>("Тенистая");
        home.addNeighbor(shadow);

        Node<String> office = new Node<>("Офис");
        shadow.addNeighbor(office);

//        dfs("Офис", home);

        dfsWithoutRecursion("Офис", home);
    }

    public static class Node<T> {
        public final T value; // не пишите public в реальных проектах!
        public final Set<Node<T>> neighbors;

        public Node(T value) {
            this.value = value;
            this.neighbors = new HashSet<>();
        }

        public void addNeighbor(Node<T> node) {
            neighbors.add(node);
            node.neighbors.add(this);
        }

        @Override
        public String toString() {
            return "{вершина \"" + value + "\", соседи: " + neighbors.stream().map(n -> n.value).collect(Collectors.toSet()) + "}";
        }
    }

    public static <T> void dfs(T value, Node<T> start) {
        Set<Node<T>> alreadyVisited = new HashSet<>();
        dfsRecursive(value, start, alreadyVisited);
    }

    private static <T> void dfsRecursive(T value, Node<T> current, Set<Node<T>> alreadyVisited) {
        alreadyVisited.add(current);
        System.out.println("Уже посещенные вершины: " + alreadyVisited);

        System.out.println("Текущая вершина: " + current);
        if (current.value.equals(value)) {
            System.out.println("Целевая вершина найдена\n");
            System.out.println("Вася дошел до офиса!");
        }

        for (Node<T> neighbor : current.neighbors) {
            if (!alreadyVisited.contains(neighbor)) {
                dfsRecursive(value, neighbor, alreadyVisited);
            }
        }
    }

    public static <T> void dfsWithoutRecursion(T value, Node<T> start) {
        Stack<Node<T>> stack = new Stack<>();
        Set<Node<T>> alreadyVisited = new HashSet<>();
        stack.push(start);

        System.out.println("Изначально в стеке только вершина " + start);

        while (!stack.isEmpty()) {
            Node<T> current = stack.pop();

            System.out.println("Текущая вершина: " + current);
            System.out.println("Стек: " + stack);
            System.out.println("Уже посещенные вершины: " + alreadyVisited);
            if (!alreadyVisited.contains(current)) {
                alreadyVisited.add(current);

                if (current.value.equals(value)) {
                    System.out.println("Целевая вершина найдена\n");
                    System.out.println("Вася дошел до офиса!");
                }

                for (Node<T> neighbor : current.neighbors) {
                    if (!alreadyVisited.contains(neighbor)) {
                        stack.push(neighbor);
                    }
                }
                System.out.println("Стек после добавления соседей: " + stack);

                System.out.println();
            }
        }
    }
}
