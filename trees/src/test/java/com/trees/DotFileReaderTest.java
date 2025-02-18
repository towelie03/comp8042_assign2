package com.trees;

import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.function.Function;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.trees.TreeNodes.GenericTreeNode;

public class DotFileReaderTest {

    private Path tempFile;

    @BeforeEach
    public void setUp() throws IOException {
        tempFile = Files.createTempFile("testTree", ".dot");
    }

    @AfterEach
    public void tearDown() throws IOException {
        Files.deleteIfExists(tempFile);
    }

    @Test
    public void testCreateTreeFromDotFile() throws IOException {
        String dotContent = "digraph {\n" +
                            "A -> B;\n" +
                            "A -> C;\n" +
                            "B -> D;\n" +
                            "B -> E;\n" +
                            "}";
        Files.write(tempFile, dotContent.getBytes());

        Function<String, String> converter = s -> s;
        GenericTree<String> tree = DotFileReader.createTreeFromDotFile(tempFile.toString(), converter);

        assertNotNull(tree);
        GenericTreeNode<String> root = tree.getRoot();
        assertNotNull(root);
        assertEquals("A", root.getValue());

        GenericTreeNode<String> nodeB = root.getChildren().stream().filter(n -> n.getValue().equals("B")).findFirst().orElse(null);
        GenericTreeNode<String> nodeC = root.getChildren().stream().filter(n -> n.getValue().equals("C")).findFirst().orElse(null);

        assertNotNull(nodeB);
        assertNotNull(nodeC);
        assertEquals("B", nodeB.getValue());
        assertEquals("C", nodeC.getValue());

        GenericTreeNode<String> nodeD = nodeB.getChildren().stream().filter(n -> n.getValue().equals("D")).findFirst().orElse(null);
        GenericTreeNode<String> nodeE = nodeB.getChildren().stream().filter(n -> n.getValue().equals("E")).findFirst().orElse(null);

        assertNotNull(nodeD);
        assertNotNull(nodeE);
        assertEquals("D", nodeD.getValue());
        assertEquals("E", nodeE.getValue());
    }

    @Test
    public void testCreateTreeFromDotFileWithEmptyFile() throws IOException {
        String dotContent = "";
        Files.write(tempFile, dotContent.getBytes());

        Function<String, String> converter = s -> s;
        GenericTree<String> tree = DotFileReader.createTreeFromDotFile(tempFile.toString(), converter);

        assertNotNull(tree);
        assertNull(tree.getRoot());
    }

    @Test
    public void testCreateTreeFromDotFileWithInvalidFormat() throws IOException {
        String dotContent = "digraph {\n" +
                            "A - B;\n" +
                            "}";
        Files.write(tempFile, dotContent.getBytes());

        Function<String, String> converter = s -> s;
        GenericTree<String> tree = DotFileReader.createTreeFromDotFile(tempFile.toString(), converter);

        assertNotNull(tree);
        assertNull(tree.getRoot());
    }
}