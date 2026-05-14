import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class FileZipperGUI extends JFrame implements ActionListener {

    JButton compressButton;
    JButton decompressButton;

    JLabel statusLabel;

    public FileZipperGUI() {

        setTitle("File Zipper Software");

        setSize(500, 300);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new FlowLayout());

        compressButton = new JButton("Compress File");

        decompressButton = new JButton("Decompress File");

        statusLabel = new JLabel("Status: Waiting");

        compressButton.addActionListener(this);

        decompressButton.addActionListener(this);

        add(compressButton);

        add(decompressButton);

        add(statusLabel);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        try {

            String text =
                    FileHandler.readFile("input.txt");

            HuffmanNode root =
                    HuffmanEncoder.buildHuffmanTree(text);

            HashMap<Character, String> huffmanCodes =
                    new HashMap<>();

            HuffmanEncoder.generateCodes(root,
                    "",
                    huffmanCodes);

            StringBuilder encodedText =
                    new StringBuilder();

            for (char c : text.toCharArray()) {

                encodedText.append(
                        huffmanCodes.get(c));
            }

            if (e.getSource() == compressButton) {

                FileHandler.writeFile(
                        "compressed.txt",
                        encodedText.toString());

                statusLabel.setText(
                        "Status: Compression Complete");
            }

            if (e.getSource() == decompressButton) {

                String decodedText =
                        HuffmanDecoder.decode(
                                encodedText.toString(),
                                root);

                FileHandler.writeFile(
                        "decompressed.txt",
                        decodedText);

                statusLabel.setText(
                        "Status: Decompression Complete");
            }

        } catch (Exception ex) {

            ex.printStackTrace();

            statusLabel.setText("Error Occurred");
        }
    }
}