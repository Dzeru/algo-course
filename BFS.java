import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Optional;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;

public class BFS {
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

        Optional<Node<String>> target = bfs("Офис", home);

        if (target.isPresent()) {
            System.out.println("Вася дошел до офиса!");
        }
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

    public static <T> Optional<Node<T>> bfs(T value, Node<T> start) {
        Set<Node<T>> alreadyVisited = new HashSet<>();

        Queue<Node<T>> queue = new ArrayDeque<>();
        queue.add(start);

        Node<T> currentNode;

        while (!queue.isEmpty()) {
            System.out.println("Очередь вершин: " + queue);
            System.out.println("Уже посещенные вершины: " + alreadyVisited);
            currentNode = queue.remove();
            System.out.println("Текущая вершина: " + currentNode);
            System.out.println("Очередь после удаления вершины: " + queue);

            if (currentNode.value.equals(value)) {
                System.out.println("Целевая вершина найдена\n");
                return Optional.of(currentNode);
            } else {
                alreadyVisited.add(currentNode);
                System.out.println("Добавили текущую вершину в посещенные: " + alreadyVisited);
                queue.addAll(currentNode.neighbors);
                System.out.println("Добавили в очередь соседей текущей вершины, очередь: " + queue);
                queue.removeAll(alreadyVisited);
                System.out.println("Удаляем из очереди все посещенные вершины, очередь: " + queue);
            }
            System.out.println("\n***\n");
        }

        return Optional.empty();
    }
}
