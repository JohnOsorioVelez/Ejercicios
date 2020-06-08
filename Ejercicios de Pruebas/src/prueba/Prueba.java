package prueba;

public class Prueba {

	/**
	 * Haga que la función ExOh (str) tome el parámetro str que se pasa y devuelva
	 * la cadena verdadera si hay un número igual de x y o, de lo contrario,
	 * devuelva la cadena falsa. Solo se ingresarán estas dos letras en la cadena,
	 * sin puntuación ni números. Por ejemplo: si str es "xooxxxxooxo", la salida
	 * debería devolver falso porque hay 6 x y 5 o.
	 */
	public static String ExOh(String str) {
		int numeroX = 0;
		int numeroO = 0;

		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) == 'x') {
				numeroX++;
			} else {
				numeroO++;
			}
		}

		if (numeroX == numeroO) {
			return "true";
		}
		return "false";
	}

	/**
	 * Haga que la función StringPeriods (str) tome el parámetro str que se pasa y
	 * determine si hay alguna subcadena K que se pueda repetir N>1 veces para
	 * producir la cadena de entrada exactamente como aparece. Su programa debería
	 * devolver la subcadena K más larga y, si no hay ninguna, debería devolver la
	 * cadena -1.
	 * 
	 * Por ejemplo: si str es "abcababcababcab", entonces su programa debería
	 * devolver abcab porque esa es la subcadena más larga que se repite 3 veces
	 * para crear la cadena final. Otro ejemplo: si str es "abababababab", entonces
	 * su programa debería devolver ababab porque es la subcadena más larga. Si la
	 * cadena de entrada contiene solo un carácter, su programa debería devolver la
	 * cadena -1.
	 */
	public static String StringPeriods(String str) {
		String aux = "";
		int indexAux = 0;

		for (int i = 1; i <= str.length(); i++) {

			int indexFin = i + str.substring(indexAux, i).length();

			if (indexFin <= str.length() && 
				str.substring(indexAux, i).equals(str.substring(i, indexFin))) {

				aux = str.substring(indexAux, i);
				System.out.println("aux:    " + aux + "     " + i + "\n");
				int a = indexAux;
				indexAux = i;
				i = i + str.substring(a, i).length() - 1;
			}

		}

		if (indexAux == str.length() - aux.length()) {
			return aux;
		} else {
			return "-1";
		}
	}

	/**
	 * Haga que la función MissingDigit (str) tome el parámetro str, que será una
	 * fórmula matemática simple con tres números, un solo operador (, -, * o /) y
	 * un signo igual (=) y devolverá el dígito que completa la ecuación . En uno de
	 * los números de la ecuación, habrá un carácter x, y su programa debe
	 * determinar qué dígito falta. Por ejemplo, si str es "3x 12 = 46", entonces su
	 * programa debería generar 4. El carácter x puede aparecer en cualquiera de los
	 * tres números y los tres números serán mayores o iguales a 0 y menores o
	 * iguales a 1000000.
	 */
	public static String MissingDigit(String str) {
		int aux = 0;
		String s[] = new String[3];
		String operador = "";
		int j = 0;

		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) == '+' || str.charAt(i) == '-' || str.charAt(i) == '*' || str.charAt(i) == '/') {
				operador = String.valueOf(str.charAt(i));
				System.out.println(operador);
				String separacion[] = str.split("\\" + operador);
				s[0] = separacion[0].replaceAll(" ", "");
				s[1] = separacion[1].split("=")[0].replaceAll(" ", "");
				s[2] = separacion[1].split("=")[1].replaceAll(" ", "");
			}
		}

		for (int i = 0; i < s.length; i++) {
			if (s[i].contains("x")) {
				j = i;
			}
		}

		if (j == 0) {
			int a = Integer.parseInt(s[1]);
			int b = Integer.parseInt(s[2]);
			if (operador.equals("+")) {
				String valor = String.valueOf(b - a);
				return String.valueOf(valor.charAt(s[j].indexOf("x")));
			} else if (operador.equals("-")) {
				String valor = String.valueOf(b + a);
				return String.valueOf(valor.charAt(s[j].indexOf("x")));
			} else if (operador.equals("*")) {
				String valor = String.valueOf(b / a);
				return String.valueOf(valor.charAt(s[j].indexOf("x")));
			} else {
				String valor = String.valueOf(b * a);
				return String.valueOf(valor.charAt(s[j].indexOf("x")));
			}
		} else if (j == 1) {
			int a = Integer.parseInt(s[0]);
			int b = Integer.parseInt(s[2]);
			if (operador.equals("+")) {
				String valor = String.valueOf(b - a);
				return String.valueOf(valor.charAt(s[j].indexOf("x")));
			} else if (operador.equals("-")) {
				String valor = String.valueOf(b + a);
				return String.valueOf(valor.charAt(s[j].indexOf("x")));
			} else if (operador.equals("*")) {
				String valor = String.valueOf(b / a);
				return String.valueOf(valor.charAt(s[j].indexOf("x")));
			} else {
				String valor = String.valueOf(b * a);
				return String.valueOf(valor.charAt(s[j].indexOf("x")));
			}
		} else {
			int a = Integer.parseInt(s[0]);
			int b = Integer.parseInt(s[1]);
			if (operador.equals("+")) {
				String valor = String.valueOf(a + b);
				return String.valueOf(valor.charAt(s[j].indexOf("x")));
			} else if (operador.equals("-")) {
				String valor = String.valueOf(a - b);
				return String.valueOf(valor.charAt(s[j].indexOf("x")));
			} else if (operador.equals("*")) {
				String valor = String.valueOf(a * b);
				return String.valueOf(valor.charAt(s[j].indexOf("x")));
			} else {
				String valor = String.valueOf(a / b);
				return String.valueOf(valor.charAt(s[j].indexOf("x")));
			}
		}
	}

	/**
	 * Haga que la función StepWalking (num) tome el parámetro num que se pasa, que
	 * será un número entero entre 1 y 15 que representa el número de escaleras que
	 * tendrá que subir. Puede subir el conjunto de escaleras dando 1 paso o 2
	 * pasos, y puede combinarlos en cualquier orden. Entonces, por ejemplo, para
	 * subir 3 pasos puede hacer: (1 paso, 1 paso, 1 paso) o (2, 1) o (1, 2).
	 * Entonces, para 3 pasos, tenemos 3 formas diferentes de escalarlos, por lo que
	 * su programa debería devolver 3. Su programa debería devolver el número de
	 * combinaciones de pasos numéricos de escalada.
	 */
	public static int StepWalking(int num, Integer cantidad) {
		if (num == 0) {
			cantidad++;
		} else {
			return StepWalking(num - 1, cantidad);
			// return StepWalking(num-2, cantidad);
		}
		return cantidad;
	}
	
	
	public static int diverseDeputation(int m, int w) {

        int contador = 0;

        int totalPersonas = m + w;

        for (int i = 0; i < totalPersonas; i++) {
            for (int j = 0; j < totalPersonas; j++) {
                for (int k = 0; k < totalPersonas; k++) {
                    if(i!=j && i!=k && j!=k){
                        if(!((i<m && j<m && k<m) || (i>=m && j>=m && k>=m))){
                        	System.out.println(i +" | "+ j +" | " + k);
                            contador++;
                        }
                    }
                }
            }
        }

        return contador;
    }
	
	public static long substringCalculator(String s) {

        long contador = 0;
        
        for (int i = 0; i < s.length()-1; i++) {
            for (int j = s.length(); j > i; j--) {
                String m = s.substring(i, j);
                if(m.length()>0){
                	System.out.println(contador +". "+m + "      " + i + "      " + j);
                    contador ++;
                }
            }
        }

        return contador;
    }

	public static void main(String[] args) {
		// System.out.println(StringPeriods("ababa"));
		// System.out.println(StepWalking(8, 0));
		//diverseDeputation(2, 2);
		
		//System.out.println("abcde".substring(0, 0));
		System.out.println("1234567890".charAt(9));
		//System.out.println("contador"+substringCalculator("ghaqjdrmnegmrlrlfpjmnnngpwalzknsencuzwsnhfltwohdgbmvfuwtquosrnyerucntxxkfqehjqygcarxogvcfkljzbzutxphpyykapncjfclnhndzxghelyvzpylazhuutmcquusexzbhsfsmbnlvnlemzvfqbfzwquairhpylnbvyhiyamztlhfchhbwrqddmuzsprfdwuqqchcpeakkexackwwzihkfenwzwckynymgqydvjtovaoezkjjurylqcuonsujycziobnfnmuwnoxcdtahpituykvgpyyshvukrstcbmnsqtjseflwywnslmvnqrtnzkyaddkjamrezprqgoenzsdryygbkeahfiduozpwkrgmatszaxmwodsqiocvagbvxyqotpaujnqvqgjmfxnxhfbwqjpgodlxdrxpjpmzeabpgqrzpxomniknjkdiwtfgyvwvekrnoupwkcbtmpcfamzrghgrznuedkybmfwctdghcfawajlxfkzhdamuygjbcwnyglkjlfmpxfdtovkqbshhrfrnyjrgxgiozsuuncnwofkqzsypwgeikpfbhryhpszegdfajzvqlwwqlnvdtdiuckcvvosrdweohnmawqonjbxyjjhlccuteeshfrxxdhzgakwjqbymnaeudcmibsytyajsgdpfvrutcpglzxdevenevmkgalcrpknuvcrnkuboennhyzirfwvtozzijujsckbxqpocakzrbwgpqgjjmsrtwmvhwyraukbuxfvebeylfpipzwjdzlmgslbtwzataxgqpasrssnfwndldwkdutdqcmcpyanrbdsxrvcvpsywjambtbzlcrvzesuhvyvwwuwwdznigxjxknfajpknqutfvvqynkpvkzgypasevrpxofbymdzcitoqolwqegocuyqsexhumzmckzuuwkamolbltlifongpvkcnrnnuplftqbxpdnegdqlymftqyrxcnzmu"));
	}
}