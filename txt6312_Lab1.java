import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.concurrent.atomic.AtomicLong;

public class txt6312_Lab1 {  

    public static void main(String[] args) {
        Path startingDirectory = Paths.get(".");  // Explicitly denotes starting point 
        AtomicLong totalSize = new AtomicLong(0);

        try {
            Files.walkFileTree(startingDirectory, new FileVisitor<Path>() {
                @Override
                public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                    return FileVisitResult.CONTINUE;  // Proceed with directory traversal
                }

                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    totalSize.addAndGet(attrs.size());
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                    System.err.println("Encountered error while processing " + file + ": " + exc.getMessage());  // Provide informative error message
                    return FileVisitResult.CONTINUE;  // Skip problematic files
                }

                @Override
                public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                    return FileVisitResult.CONTINUE;  // Continue after directory processing
                }
            });

            System.out.println("Total size of files within the directory: " + totalSize.get() + " bytes");  // Clear output message
        } catch (IOException e) {
            System.err.println("Error occurred during directory traversal: " + e.getMessage());  // Informative error handling
        }
    }
}
