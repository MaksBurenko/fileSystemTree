import junit.framework.TestCase;
import org.junit.Test;

import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

public class FileSystemTreeTest extends TestCase {

    @Test
    public void calcSize(){
        var file1 = buildFile(1);
        var file2 = buildFile(10);
        var folder1 = buildFolder(List.of(file1, file2));
        var folder2 = buildFolder(List.of());
        var file3 = buildFile(50);
        var rootFolder = buildFolder(List.of(file1, file2, file3));

        assertThat(file2.calcSize()).isEqualTo(10);
        assertThat(folder1.calcSize()).isEqualTo(11);
        assertThat(folder2.calcSize()).isEqualTo(0);
        assertThat(rootFolder.calcSize()).isEqualTo(61);
    }

    private FileSystemTree.Node buildFile(long size) {
        return FileSystemTree.Node.builder()
                .type(FileSystemTree.NodeType.FILE)
                .size(size)
                .build();
    }

    private FileSystemTree.Node buildFolder(List<FileSystemTree.Node> children) {
        return FileSystemTree.Node.builder()
                .type(FileSystemTree.NodeType.FOLDER)
                .children(children)
                .build();
    }

}