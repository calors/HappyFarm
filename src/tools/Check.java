package tools;

public class Check {
	/*
	 * 判断用户名是否符合要求,长度2~15
	 */
	public static boolean checkName(String name) {
		if (name.length() > 1 && name.length() <= 15)
			if (!((name.trim()).isEmpty()))
				return true;
		return false;
	}

	/*
	 * 判断密码是否符合要求,长度3~15
	 */
	public static boolean checkPass(String pass) {
		if (pass.length() > 2 && pass.length() <= 15)
			if (!((pass.trim()).isEmpty()))
				return true;
		return false;
	}

	/*
	 * 判断字符串中的字符是不是都是数字。且输入的数字不能超过3位数
	 */
	public static boolean checkISNUM(String num) {
		/*
		 * 正则表达式 \d 表示任意数字，范围为0-9 {n，m}表示其前的表达式出现n到m次，即数字0~9，出现1~2次 注意 转移字符 \
		 */
		if (num.matches("\\d{1,3}"))
			return true;
		return false;
	}

}
