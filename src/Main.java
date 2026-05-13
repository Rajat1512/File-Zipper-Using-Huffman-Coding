import java.util.HashMap;

public class Main {

    public static void main(String[] args) {

        try {

            // Read original file
            String text =
                    FileHandler.readFile("input.txt");

            // Build Huffman Tree
            HuffmanNode root =
                    HuffmanEncoder.buildHuffmanTree(text);

            // Store Huffman Codes
            HashMap<Character, String> huffmanCodes =
                    new HashMap<>();

            // Generate Codes
            HuffmanEncoder.generateCodes(root,
                    "",
                    huffmanCodes);

            System.out.println("Huffman Codes:");

            for (Character c : huffmanCodes.keySet()) {

                System.out.println(c + " : "
                        + huffmanCodes.get(c));
            }

            // Encode Text
            StringBuilder encodedText =
                    new StringBuilder();

            for (char c : text.toCharArray()) {

                encodedText.append(
                        huffmanCodes.get(c));
            }

            // Save compressed file
            FileHandler.writeFile(
                    "compressed.txt",
                    encodedText.toString());

            System.out.println("\nCompression Complete!");

            System.out.println("\nEncoded Text:");
            System.out.println(encodedText);

            // Decode compressed text
            String decodedText =
                    HuffmanDecoder.decode(
                            encodedText.toString(),
                            root);

            // Save decompressed file
            FileHandler.writeFile(
                    "decompressed.txt",
                    decodedText);

            System.out.println("\nDecompression Complete!");

            System.out.println("\nDecoded Text:");
            System.out.println(decodedText);

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}