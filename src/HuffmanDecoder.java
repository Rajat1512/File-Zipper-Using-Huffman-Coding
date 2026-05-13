public class HuffmanDecoder {

    public static String decode(String encodedText,
                                HuffmanNode root) {

        StringBuilder decodedText =
                new StringBuilder();

        HuffmanNode current = root;

        for (int i = 0; i < encodedText.length(); i++) {

            char bit = encodedText.charAt(i);

            if (bit == '0') {
                current = current.left;
            }
            else {
                current = current.right;
            }

            // Leaf node found
            if (current.left == null &&
                    current.right == null) {

                decodedText.append(current.character);

                current = root;
            }
        }

        return decodedText.toString();
    }
}