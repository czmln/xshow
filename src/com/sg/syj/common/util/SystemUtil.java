package com.sg.syj.common.util;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.Properties;
import java.util.StringTokenizer;

public class SystemUtil {

	/**
	 * array[0]: total mem, array[1]: free mem
	 * 
	 * @return
	 */
	public static long[] getMemInfo() {
		long[] result = null;
		String osname = SystemUtil.getOsname();
		if (null != osname
				&& osname.toLowerCase().contains("Windows".toLowerCase())) {
			result = getWinMemInfo();
		} else if (null != osname
				&& osname.toLowerCase().contains("Linux".toLowerCase())) {
			try {
				result = getLinuxMemInfo();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}

	public static float getCpuInfo() throws IOException, InterruptedException {
		float result = 0;
		String osname = SystemUtil.getOsname();
		if (null != osname
				&& osname.toLowerCase().contains("Windows".toLowerCase())) {
			result = (float) SystemUtil.getWinCpuInfo();
		} else if (null != osname
				&& osname.toLowerCase().contains("Linux".toLowerCase())) {
			result = SystemUtil.getLinuxCpuInfo();
		}
		return result;
	}

	private static float getLinuxCpuInfo() throws IOException,
			InterruptedException {
		File file = new File("/proc/stat");
		BufferedReader br = new BufferedReader(new InputStreamReader(
				new FileInputStream(file)));
		StringTokenizer token = new StringTokenizer(br.readLine());
		token.nextToken();
		int user1 = Integer.parseInt(token.nextToken());
		int nice1 = Integer.parseInt(token.nextToken());
		int sys1 = Integer.parseInt(token.nextToken());
		int idle1 = Integer.parseInt(token.nextToken());

		Thread.sleep(1000);

		br = new BufferedReader(
				new InputStreamReader(new FileInputStream(file)));
		token = new StringTokenizer(br.readLine());
		token.nextToken();
		int user2 = Integer.parseInt(token.nextToken());
		int nice2 = Integer.parseInt(token.nextToken());
		int sys2 = Integer.parseInt(token.nextToken());
		int idle2 = Integer.parseInt(token.nextToken());
//System.out.println("user2:"+user2+"nice2:"+nice2+"sys2:"+sys2+"idle2:"+idle2);
		return (float) ((user2 + sys2 + nice2) - (user1 + sys1 + nice1))
				/ (float) ((user2 + nice2 + sys2 + idle2) - (user1 + nice1
						+ sys1 + idle1));
	}

	/**
	 * 
	 * @return array[0]: total mem, array[1]: free mem
	 * @throws NumberFormatException
	 * @throws IOException
	 */
	private static long[] getLinuxMemInfo() throws NumberFormatException,
			IOException {
		File file = new File("/proc/meminfo");
		BufferedReader br = new BufferedReader(new InputStreamReader(
				new FileInputStream(file)));
		long[] result = new long[4];
		String str = null;
		StringTokenizer token = null;
		while ((str = br.readLine()) != null) {
			token = new StringTokenizer(str);
			if (!token.hasMoreTokens())
				continue;

			str = token.nextToken();
			if (!token.hasMoreTokens())
				continue;

			if (str.equalsIgnoreCase("MemTotal:"))
				result[0] = Integer.parseInt(token.nextToken());
			else if (str.equalsIgnoreCase("MemFree:"))
				result[1] = Integer.parseInt(token.nextToken());
			else if (str.equalsIgnoreCase("SwapTotal:"))
				result[2] = Integer.parseInt(token.nextToken());
			else if (str.equalsIgnoreCase("SwapFree:"))
				result[3] = Integer.parseInt(token.nextToken());
		}

		return result;

	}

	/**
	 * 
	 * @return array[0]: total mem, array[1]: free mem
	 */
	private static long[] getWinMemInfo() {
		long[] result = new long[4];
		long total = Runtime.getRuntime().totalMemory();
		long free = Runtime.getRuntime().freeMemory();
		long max = Runtime.getRuntime().maxMemory();
		result[0] = total;
		result[1] = free;
		result[2] = max;
		return result;
	}

	private static double getWinCpuInfo() throws IOException,
			InterruptedException {
		String procCmd = System.getenv("windir")
				+ "\\system32\\wbem\\wmic.exe process get Caption,CommandLine,"
				+ "KernelModeTime,ReadOperationCount,ThreadCount,UserModeTime,WriteOperationCount";

		// 取进程信�??
		long[] c0 = readCpu(Runtime.getRuntime().exec(procCmd));
		Thread.sleep(10);
		long[] c1 = readCpu(Runtime.getRuntime().exec(procCmd));
		if (c0 != null && c1 != null) {
			long idletime = c1[0] - c0[0];
			long busytime = c1[1] - c0[1];
			return Double.valueOf(100 * (busytime) / (busytime + idletime))
					.doubleValue();
		} else {
			return 0.0;
		}

	}

	public static String getOsname() {
		Properties props = System.getProperties();
		String osName = props.getProperty("os.name");

		return osName;
	}

	private static long[] readCpu(final Process proc) {
		long[] retn = new long[2];
		try {
			proc.getOutputStream().close();
			InputStreamReader ir = new InputStreamReader(proc.getInputStream());
			LineNumberReader input = new LineNumberReader(ir);
			String line = input.readLine();
			if (line == null || line.length() < 10) {
				return null;
			}
			int capidx = line.indexOf("Caption");
			int cmdidx = line.indexOf("CommandLine");
			int rocidx = line.indexOf("ReadOperationCount");
			int umtidx = line.indexOf("UserModeTime");
			int kmtidx = line.indexOf("KernelModeTime");
			int wocidx = line.indexOf("WriteOperationCount");
			long idletime = 0;
			long kneltime = 0;
			long usertime = 0;
			while ((line = input.readLine()) != null) {
				if (line.length() < wocidx) {
					continue;
				}
				// 字段出现顺序：Caption,CommandLine,KernelModeTime,ReadOperationCount,
				// ThreadCount,UserModeTime,WriteOperation
				String caption = Bytes.substring(line, capidx, cmdidx - 1)
						.trim();
				String cmd = Bytes.substring(line, cmdidx, kmtidx - 1).trim();
				if (cmd.indexOf("wmic.exe") >= 0) {
					continue;
				}
				// log.info("line="+line);
				if (caption.equals("System Idle Process")
						|| caption.equals("System")) {
					idletime += Long.valueOf(
							Bytes.substring(line, kmtidx, rocidx - 1).trim())
							.longValue();
					idletime += Long.valueOf(
							Bytes.substring(line, umtidx, wocidx - 1).trim())
							.longValue();
					continue;
				}

				kneltime += Long.valueOf(
						Bytes.substring(line, kmtidx, rocidx - 1).trim())
						.longValue();
				usertime += Long.valueOf(
						Bytes.substring(line, umtidx, wocidx - 1).trim())
						.longValue();
			}
			retn[0] = idletime;
			retn[1] = kneltime + usertime;
			return retn;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				proc.getInputStream().close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * @param args
	 * @throws Exception
	 * @throws NumberFormatException
	 */
	public static void main(String[] args) throws NumberFormatException,
			Exception {
		// System.out.println(SystemUtil.getOsname());
		// System.out.println("hello..");
		// System.out.println("MemTotal:"+SystemUtil.getLinuxMemInfo()[0]);
		// System.out.println("MemFree:"+SystemUtil.getLinuxMemInfo()[1]);
		// System.out.println("SwapTotal:"+SystemUtil.getLinuxMemInfo()[2]);
		// System.out.println("SwapFree:"+SystemUtil.getLinuxMemInfo()[3]);
		// System.out.println("cpu:"+SystemUtil.getCpuInfo());
		long[] mem = SystemUtil.getMemInfo();
		System.out.println("MemTotal:" + mem[0]);
		System.out.println("MemFree:" + mem[1]);
		float cpu = SystemUtil.getCpuInfo();
		System.out.println("Cpu:" + cpu);
	}

	public static class Bytes {
		public static String substring(String src, int start_idx, int end_idx) {
			byte[] b = src.getBytes();
			String tgt = "";
			for (int i = start_idx; i <= end_idx; i++) {
				tgt += (char) b[i];
			}
			return tgt;
		}
	}
}
