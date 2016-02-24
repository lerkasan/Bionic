package hometasks;

public class Name {

	public static void main(String[] args) {
		String name = "Лапицкая Валерия Валентиновна";
		String[] initials = new String[3];
		String[] words = name.split(" ");
		initials[0] = words[0];
		for (int i = 1; i < words.length; i++) {
			initials[i] = words[i].substring(0, 1) + " ";
			System.out.print(initials[i]);
			i++;
		}
		for (String s : initials) {
			System.out.print(s + " ");
		}
	}

}
