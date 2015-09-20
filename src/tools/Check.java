package tools;

public class Check {
	/*
	 * �ж��û����Ƿ����Ҫ��,����2~15
	 */
	public static boolean checkName(String name) {
		if (name.length() > 1 && name.length() <= 15)
			if (!((name.trim()).isEmpty()))
				return true;
		return false;
	}

	/*
	 * �ж������Ƿ����Ҫ��,����3~15
	 */
	public static boolean checkPass(String pass) {
		if (pass.length() > 2 && pass.length() <= 15)
			if (!((pass.trim()).isEmpty()))
				return true;
		return false;
	}

	/*
	 * �ж��ַ����е��ַ��ǲ��Ƕ������֡�����������ֲ��ܳ���3λ��
	 */
	public static boolean checkISNUM(String num) {
		/*
		 * ������ʽ \d ��ʾ�������֣���ΧΪ0-9 {n��m}��ʾ��ǰ�ı��ʽ����n��m�Σ�������0~9������1~2�� ע�� ת���ַ� \
		 */
		if (num.matches("\\d{1,3}"))
			return true;
		return false;
	}

}
