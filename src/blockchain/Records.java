package blockchain;

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

}
