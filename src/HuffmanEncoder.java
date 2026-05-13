import java.util.*;

public class HuffmanEncoder {

    public static void generateCodes(HuffmanNode root,
                                     String code,
                                     HashMap<Character, String> huffmanCodes) {

        if (root == null)
            return;

        if (root.left == null && root.right == null) {
            huffmanCodes.put(root.character, code);
        }

        generateCodes(root.left, code + "0", huffmanCodes);
        generateCodes(root.right, code + "1", huffmanCodes);
    }

    public static HuffmanNode buildHuffmanTree(String text) {

        HashMap<Character, Integer> frequencyMap = new HashMap<>();

        for (char c : text.toCharArray()) {
            frequencyMap.put(c,
                    frequencyMap.getOrDefault(c, 0) + 1);
        }

        PriorityQueue<HuffmanNode> priorityQueue =
                new PriorityQueue<>(new HuffmanComparator());

        for (Map.Entry<Character, Integer> entry :
                frequencyMap.entrySet()) {

            priorityQueue.add(
                    new HuffmanNode(entry.getKey(),
                            entry.getValue()));
        }

        while (priorityQueue.size() > 1) {

            HuffmanNode left = priorityQueue.poll();
            HuffmanNode right = priorityQueue.poll();

            HuffmanNode newNode =
                    new HuffmanNode('-', left.frequency + right.frequency);

            newNode.left = left;
            newNode.right = right;

            priorityQueue.add(newNode);
        }

        return priorityQueue.poll();
    }
}