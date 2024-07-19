public class Main {
    public static void main(String[] args) {
        String txtPath = "src/filesReaded/metin.txt";
        String jsonPath = "src/filesReaded/sample1.json";
        String excelPath = "src/filesReaded/sample-xls-file-for-testing.xls";

        FileReaderr txtReader = new TxtReader();

        Service jsonReader = new jsonReader();
        FileReaderr fileReader1 = new ServiceAdapter(jsonReader);

        Service excelReader = new ExcelReader();
        FileReaderr fileReader2 = new ServiceAdapter(excelReader);




        // txtReader.readFile(txtPath);
        fileReader1.readFile(jsonPath);
        // fileReader2.readFile(excelPath);

    }
}