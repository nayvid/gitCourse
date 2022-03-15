package blockchain;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.LinkedList;

import com.google.gson.GsonBuilder;

public class Blockchain {
    private static final String CHAIN_FILE = " ";

    private static LinkedList<Block> DB = new LinkedList<>();

    private static final String LEDGER_FILE = " ";

    public static void genesis() {
        Block genesis = new Block("0");
        DB.add(genesis);
        Blockchain.persist();
        Blockchain.distribute();
    }

    public static void nextBlock(Block newBlock) {
        DB = get();
        DB.add(newBlock);
        Blockchain.persist();
    }

    private static void persist() {
        try (
                FileOutputStream fout = new FileOutputStream(CHAIN_FILE);
                ObjectOutputStream out = new ObjectOutputStream(fout);) {
            out.writeObject(DB);
            System.out.println(">>> Master file updated!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static LinkedList<Block> get() {
        try (
                FileInputStream fin = new FileInputStream(CHAIN_FILE);
                ObjectInputStream in = new ObjectInputStream(fin);) {
            return (LinkedList<Block>) in.readObject();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void distribute() {
        /**
         * convert the chain to the textform using Gson API
         */
        String chain = new GsonBuilder().setPrettyPrinting().create().toJson(DB);
        System.out.println(chain);
        try {
            Files.write(Paths.get(LEDGER_FILE), chain.getBytes(), StandardOpenOption.CREATE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
