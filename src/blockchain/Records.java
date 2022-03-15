package blockchain;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Records {

    private String merkleRoot = "";

    private List<String> dataLst;

    public Records() {
        dataLst = new ArrayList<>();
    }

    public void add(String records) {
        dataLst.add(records);
    }

    @Override
    public String toString() {
        return "Records [" + dataLst + "]";
    }

    public List<String> readRecordsFile(String LEDGER_FILE) {
        List<String> datas = null;
        try (BufferedReader br = new BufferedReader(new FileReader(LEDGER_FILE))) {
            for (String line; (line = br.readLine()) != null;) {
                datas.add(line);
            }
        } catch (Exception e) {

        }

        return datas;
    }

    public int size() {
        return dataLst.size();
    }

    public void clear(){
        dataLst.clear();
    }

}
