package main.java.com.wismut.javacore.chapter21;


import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class DirList {
    public static void main(String[] args) {
        String dirName = ".idea";

        DirectoryStream.Filter<Path> how = new DirectoryStream.Filter<Path>() {
            @Override
            public boolean accept(Path filename) throws IOException {
                if (Files.isWritable(filename)) {
                    return true;
                }
                return false;
            }
        };

        try (DirectoryStream<Path> dirstrm = Files.newDirectoryStream(Paths.get(dirName), how)) {
            System.out.println("Directory of " + dirName);

            for (Path entry : dirstrm) {
                BasicFileAttributes attribs = Files.readAttributes(entry, BasicFileAttributes.class);

                if (attribs.isDirectory()) {
                    System.out.print("<DIR>  ");
                } else {
                    System.out.print("       ");
                }

                System.out.println(entry.getName(1));
            }

        } catch (InvalidPathException e) {
            System.out.println("Path error " + e);
        } catch (NotDirectoryException e) {
            System.out.println(dirName + " is not a directory");
        } catch (IOException e) {
            System.out.println("I/O error: " + e);
        }
    }
}
