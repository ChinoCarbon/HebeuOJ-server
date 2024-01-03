import java.util.HashMap;
import java.util.Scanner;
import java.util.Stack;

public class Main {
	// 目标复杂度
	public static int tarComp;
	// 行数
	public static int L;
	// 目标复杂度字符串
	public static String tar;

	// 变量名
	public static HashMap<Character, Integer> varName;
	// 循环的栈,内容是该循环声明的变量i
	public static Stack<Character> fStack;
	// 复杂度栈
	public static Stack<Integer> cStack;
	// 实际复杂度
	public static Integer comp;
	// 出错标志位
	public static boolean ERROR;
	// 封锁层数，大于该层的都被封锁，直至等于该层解封
	public static int invalid;
	// 循环过程中最大复杂度
	public static int max_c;

	// 更新记录型数据结构
	private static void flush() {
		varName = new HashMap<>();
		fStack = new Stack<>();
		cStack = new Stack<>();
		comp = 0;
		ERROR = false;
		invalid = Integer.MAX_VALUE;
		max_c = 0;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		for (int time = 0; time < t; time++) {
			flush();
			L = sc.nextInt();
			if (L % 2 != 0) {
				ERROR = true;
			}
			tar = sc.next();
			// 处理字符串提取目标复杂度
			if (tar.length() == 4) {
				tarComp = 0;
			} else {
				if (tar.charAt(5) == ')') {
					tarComp = tar.charAt(4) - '0';
				} else {
					tarComp = tar.charAt(5) - '0' + (tar.charAt(4) - '0') * 10;
				}
			}
			// 读每行
			for (int lineNum = 1; lineNum <= L; lineNum++) {
				String c = sc.next();
				// 如果为结束符 E
				if (c.charAt(0) == 'E') {
					// 如果栈为空，说明存在语法错误
					if (fStack.isEmpty()) {
						ERROR = true;
					}
					// 如果栈不空，则出栈F并删除变量名
					else {
						// 解封
						// if (fStack.size() == invalid)
						// 	invalid = Integer.MAX_VALUE;
						// 如果当前嵌套层数中尚未+1，且当前循环复杂度为1
						if (cStack.pop() == 1 && fStack.size() <= invalid) {
							// 设置本层已经+1复杂度
							--max_c;
							//解封
							invalid = Integer.MAX_VALUE;
						}
						// 最近的循环出栈，代表结束
						Character nowVar = fStack.pop();
						// 从变量表中移除该循环声明的变量
						varName.remove(nowVar);
					}
				}
				// 如果为开始符号 F
				else {
					String var = sc.next();
					// 循环f：入栈
					fStack.push(var.charAt(0));
					//当前循环的复杂度
					int tempComp;
					//当前栈高度（循环嵌套数）
					int stackSize=fStack.size();
					String x = sc.next();
					String y = sc.next();
					// 如果初值为n，复杂度一定为常数
					if (x.charAt(0) == 'n') {
						tempComp = 0;
						// 如果为无效循环，则封锁
						if (y.charAt(0) != 'n')
							invalid = Math.min(invalid, stackSize);
					}
					// 如果初值不为n且终值不为n，复杂度为常数
					else if (y.charAt(0) != 'n') {
						tempComp = 0;
						// 如果为无效循环，则封锁
						if (Integer.parseInt(x) > Integer.parseInt(y))
							invalid = Math.min(invalid, stackSize);
					}
					// 如果初值不为n且终值为n，复杂度为n^1
					else {
						tempComp = 1;
						if (stackSize <= invalid) {
							max_c += 1;
							comp = Math.max(comp, max_c);
						}
					}
					// 复杂度入栈
					cStack.push(tempComp);
					// 如果已经存在变量名
					if (varName.containsKey(var.charAt(0))) {
						// 查看自己是否为可以被执行的循环体
						ERROR = true;
					}
					// 如果本次循环内声明的变量名不冲突
					else {
						// 加入变量表
						varName.put(var.charAt(0), 1);
					}
				}
			}
			// 如果循环栈非空，说明有语法错误
			if (!fStack.isEmpty()) {
				ERROR = true;
			}
			// 如果出错标识符被置为true说明出错
			if (ERROR) {
				System.out.println("ERR");
				continue;
			}
			if (tarComp == comp) {
				System.out.println("Yes");
			} else {
				System.out.println("No");
			}
		}
		sc.close();
	}
}