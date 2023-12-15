import java.io.*;

class FileIO {
    
    public FileIO(String fileName) {
        this.fileName = fileName;
    }

    public static void readFile() {
        try {
            File f = new File(fileName);
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);

            String line;
            while((line = br.readLine()) != null) {
                System.out.println(line);
            }

            br.close();
            fr.close();
        } catch (IOexception e) {
            e.printStackTrace();
        }
    }

    public static void writeFile(String content) {
        try {
            File file = new File(fileName);
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);

            bw.write(content);

            bw.close();
            fw.close();

        } catch (IOException e) {
            e.printStackTrace()
        }
    }

    public static void creatFile(String fileName) {
        try {
            File file = new File(fileName);
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch(IOException e) {
            e.printstackTrace();
        }
    }

    public static void main(String[] args) {
        FileIO fileIO = new FileIO();
        createFile("newFile.tct");
        writeFile("Hello World!");
        readFile();
    }
}
