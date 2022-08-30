package java_core_3_2;

import java.io.*;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {
    public static void main(String[] args) {
        GameProgress save1 = new GameProgress(100, 1, 1, 1.0);
        GameProgress save2 = new GameProgress(120, 3, 2, 15.5);
        GameProgress save3 = new GameProgress(150, 5, 5, 120.4);

        saveGame("C://Games//savesgames//save1.dat", save1);
        saveGame("C://Games//savesgames//save2.dat", save2);
        saveGame("C://Games//savesgames//save3.dat", save2);

        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("C://Games//savesgames//save1.dat");
        arrayList.add("C://Games//savesgames//save2.dat");
        arrayList.add("C://Games//savesgames//save3.dat");

        /*File zip = new File("C://Games//savesgames//zip.zip");
        try {
            if (zip.createNewFile()) {
                System.out.println("Zip создан");
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage() + '\n');
        }*/


        zipFiles("C://Games//savesgames//zip.zip", arrayList);

        List<File> saveDat = Arrays.asList(
                new File("C://Games//savesgames//save1.dat"),
                new File("C://Games//savesgames//save2.dat"),
                new File("C://Games//savesgames//save3.dat")
        );
        saveDat.stream().

                forEach(save ->

                {
                    if (save.delete()) {
                        System.out.println("Файл " + save + " удален");
                    }
                });
    }

    public static void saveGame(String urlFile, GameProgress save) {
        try (FileOutputStream fos = new FileOutputStream(urlFile);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(save);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void zipFiles(String urlZip, List<String> arrayList) {
        try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(urlZip))) {
            for (String arr : arrayList) {
                try (FileInputStream fis = new FileInputStream(arr)) {
                    ZipEntry entry = new ZipEntry(arr);
                    zout.putNextEntry(entry);
                    while (fis.available() > 0) {
                        zout.write(fis.read());
                    }
                    zout.closeEntry();
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
