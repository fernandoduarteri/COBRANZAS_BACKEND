package Utilidades;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class GnuPG {
	
	//COmentario
	
	//Otro comentario
	
	//Otro comentario

	private final String kGnuPGCommand = "/usr/bin/gpg --batch --armor --output -";

	private File tmpFile;
	private int gpg_exitCode = -1;
	private String gpg_result;
	private String gpg_err;

	class ProcessStreamReader extends Thread {
		StringBuffer stream;
		InputStreamReader in;

		final static int BUFFER_SIZE = 1024;

		ProcessStreamReader(InputStream in) {
			super();
			this.in = new InputStreamReader(in);
			this.stream = new StringBuffer();
		}

		public void run() {
			try {
				int read;
				char[] c = new char[BUFFER_SIZE];

				while ((read = in.read(c, 0, BUFFER_SIZE - 1)) > 0) {
					stream.append(c, 0, read);
					if (read < BUFFER_SIZE - 1)
						break;
				}
			} catch (IOException io) {
			}
		}

		String getString() {
			return stream.toString();
		}
	}

	public boolean sign(String inStr, String passPhrase) {
		boolean success;

		success = createTempFile(inStr);

		if (success) {
			success = runGnuPG("--passphrase-fd 0 --sign "
					+ this.tmpFile.getAbsolutePath(), passPhrase);
			this.tmpFile.delete();
			if (success && this.gpg_exitCode != 0)
				success = false;
		}
		return success;
	}

	public boolean clearSign(String inStr, String passPhrase) {
		boolean success;

		success = createTempFile(inStr);

		if (success) {
			success = runGnuPG("--passphrase-fd 0 --clearsign "
					+ this.tmpFile.getAbsolutePath(), passPhrase);
			this.tmpFile.delete();
			if (success && this.gpg_exitCode != 0)
				success = false;
		}
		return success;
	}

	public boolean signAndEncrypt(String inStr, String keyID, String passPhrase) {
		boolean success;

		success = createTempFile(inStr);

		if (success) {
			success = runGnuPG("--passphrase-fd 0 -se "
					+ this.tmpFile.getAbsolutePath(), passPhrase);
			this.tmpFile.delete();
			if (success && this.gpg_exitCode != 0)
				success = false;
		}
		return success;
	}

	public boolean encrypt(String inStr, String keyID) {
		boolean success;

		success = runGnuPG("-r " + keyID + " --encrypt", inStr);
		if (success && this.gpg_exitCode != 0)
			success = false;
		return success;
	}

	public boolean decrypt(String inStr, String passPhrase) {
		boolean success;

		success = createTempFile(inStr);

		if (success) {
			success = runGnuPG("--passphrase-fd 0 --decrypt "
					+ this.tmpFile.getAbsolutePath(), passPhrase);
			this.tmpFile.delete();
			if (success && this.gpg_exitCode != 0)
				success = false;
		}
		return success;
	}

	public String getResult() {
		return gpg_result;
	}

	public String getErrorString() {
		return gpg_err;
	}

	public int getExitCode() {
		return gpg_exitCode;
	}

	private boolean runGnuPG(String commandArgs, String inputStr) {
		Process p;
		String fullCommand = kGnuPGCommand + " " + commandArgs;
		// String fullCommand = commandArgs;

		System.out.println(fullCommand);

		try {
			p = Runtime.getRuntime().exec(fullCommand);
		} catch (IOException io) {
			System.out.println("io Error" + io.getMessage());
			return false;
		}

		ProcessStreamReader psr_stdout = new ProcessStreamReader(p
				.getInputStream());
		ProcessStreamReader psr_stderr = new ProcessStreamReader(p
				.getErrorStream());
		psr_stdout.start();
		psr_stderr.start();
		if (inputStr != null) {
			BufferedWriter out = new BufferedWriter(new OutputStreamWriter(p
					.getOutputStream()));
			try {
				out.write(inputStr);
				out.close();
			} catch (IOException io) {
				System.out.println("Exception at write! " + io.getMessage());
				return false;
			}
		}

		try {
			p.waitFor();

			psr_stdout.join();
			psr_stderr.join();
		} catch (InterruptedException i) {
			System.out.println("Exception at waitfor! " + i.getMessage());
			return false;
		}

		try {
			gpg_exitCode = p.exitValue();
		} catch (IllegalThreadStateException itse) {
			return false;
		}

		gpg_result = psr_stdout.getString();
		gpg_err = psr_stdout.getString();

		return true;
	}

	private boolean createTempFile(String inStr) {
		this.tmpFile = null;
		FileWriter fw;

		try {
			this.tmpFile = File.createTempFile("YGnuPG", null);
		} catch (Exception e) {
			System.out.println("Cannot create temp file " + e.getMessage());
			return false;
		}

		try {
			fw = new FileWriter(this.tmpFile);
			fw.write(inStr);
			fw.flush();
			fw.close();
		} catch (Exception e) {
			// delete our file:
			tmpFile.delete();

			System.out.println("Cannot write temp file " + e.getMessage());
			return false;
		}

		return true;
	}

}
