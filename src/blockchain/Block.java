package blockchain;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.sql.Timestamp;

public class Block implements Serializable {

	public Header header;
	public Records record;

	// constructor
	public Block(String prevHash) {
		header = new Header();
		header.setPreviousHash(prevHash);
		header.setTimestamp(new Timestamp(
				System.currentTimeMillis()).getTime());
		byte[] blockByte = this.getBytes();
		String blockHash = Hasher.hash(blockByte, "SHA-256");
		header.setCurrentHash(blockHash);
	}

	public Header getHeader() {
		return header;
	}

	public Records getTranx() {
		return record;
	}

	public void setTranx(Records tranx) {
		this.record = tranx;
	}

	// nested class for composition
	public class Header implements Serializable {

		private int index;
		private String currentHash;
		private String previousHash;
		private long timestamp;

		public String getCurrentHash() {
			return currentHash;
		}

		public void setCurrentHash(String currentHash) {
			this.currentHash = currentHash;
		}

		public String getPreviousHash() {
			return previousHash;
		}

		public void setPreviousHash(String previousHash) {
			this.previousHash = previousHash;
		}

		public long getTimestamp() {
			return timestamp;
		}

		public void setTimestamp(long timestamp) {
			this.timestamp = timestamp;
		}

		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder("Header [");
			sb.append("index=" + this.index + ", ");
			sb.append("currentHash=" + this.currentHash + ", ");
			sb.append("previousHash=" + this.previousHash + ", ");
			sb.append("timestamp=" + this.timestamp + ", ");
			return sb.append("]").toString();
		}

	}

	@Override
	public String toString() {
		return "Block [header=" + this.header + ", Records=" + this.record + "]";
	}

	/**
	 * Converting the Block object into byte[]
	 */
	private byte[] getBytes() {
		try (
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				ObjectOutputStream out = new ObjectOutputStream(baos);) {

			out.writeObject(this);
			return baos.toByteArray();

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
