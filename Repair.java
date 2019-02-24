import java.util.Scanner;

class Repair {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String outLines = "", temp = "", newLine = "", m[];
		String uid, gid, mode, dmode, fmode, path;
		int j;
		int n = 0;

		while(true) {
			temp = sc.nextLine();
			// temp = "set_perm_recursive(0, 0, 0755, 0644, \"/system\");";
			if (temp.equals("."))
				break;
			if (temp.lastIndexOf("set_perm_recursive") == -1) {
				//set_perm(0, 3003, 06755, "/system/bin/ip");
				//set_metadata("/system/test/test","uid", 0, "gid", 3003, "mode", 0644);
				m = temp.split(", ");
				uid = "";
				j = 1;
				//48-57
				while (m[0].charAt(m[0].length() - j) >=48 && m[0].charAt(m[0].length() - j) <=57) {
					uid += m[0].charAt(m[0].length() - j);
					j++;
				}
				gid = m[1];
				mode = m[2];
				path = m[3].substring(0, m[3].length()-2);

				newLine += "set_metadata(" + path + ", \"uid\", " + uid + ", \"gid\", " + gid + ", \"mode\", " + mode + ");\n";
			} else {
				//set_perm_recursive(0, 0, 0755, 0644, "/system");
				//set_metadata_recursive("/system/test", "dmode", 0755, "fmode", 0644);
				m = temp.split(", ");

				uid = "";
				j = 1;
				//48-57
				while (m[0].charAt(m[0].length() - j) >=48 && m[0].charAt(m[0].length() - j) <=57) {
					uid += m[0].charAt(m[0].length() - j);
					j++;
				}
				gid = m[1];
				dmode = m[2];
				fmode = m[3];
				path = m[4].substring(0, m[4].length()-2);
				newLine += "set_metadata_recursive(" + path + ", \"uid\", " + uid + ", \"gid\", " + gid + ", \"dmode\", " + dmode + ", \"fmode\", " + fmode +");\n";
			}
		}
		System.out.print(newLine);
	}
}