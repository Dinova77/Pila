package pilas;
import java.util.Arrays;
import java.util.Scanner;

public class Pilas<T> {
    private Object[] arreglo;
    private int tamaño;
    private static final int Capacidadi = 10;

    // Constructor
    public Pilas() {
        arreglo = new Object[Capacidadi];
        tamaño = 0;
    }

    // Verificar si la pila está vacía
    public boolean Vacio() {
        return tamaño == 0;
    }

    // Obtener el tamaño de la pila
    public int Tamaño() {
        return tamaño;
    }

    // Empujar (push) un elemento a la pila
    public void push(T elemento) {
        if (tamaño == arreglo.length) {
            // Duplicar la capacidad del arreglo si está lleno
            aumentarCapacidad();
        }
        arreglo[tamaño] = elemento;
        tamaño++;
    }

    // Sacar  un elemento 
    public T pop() {
        if (Vacio()) {
            throw new IllegalStateException("La pila esta vacia");
        }
        T elemento = (T) arreglo[tamaño - 1];
        arreglo[tamaño - 1] = null; 
        tamaño--;
        return elemento;
    }

    // Obtener el elemento superior de la pila sin eliminarlo
    public T tope() {
        if (Vacio()) {
            throw new IllegalStateException("La pila esta vacia");
        }
        //System.out.print(arreglo[tamaño - 1]);
        return (T) arreglo[tamaño - 1];
    }

    // Aumentar la capacidad del arreglo
    private void aumentarCapacidad() {
        int nuevaCapacidad = arreglo.length * 2;
        arreglo = Arrays.copyOf(arreglo, nuevaCapacidad);
        
    }
      public static boolean Balanceo2(String expresion,Pilas<Character> pila) {
        int aperturaCont = 0; // Contador para signos de apertura ({[(
        int cierreCont = 0; // Contador para signos de cierre )}]}

        for (char caracter : expresion.toCharArray()) {
            if (caracter == '{' || caracter == '[' || caracter == '(') {
                aperturaCont++;
            } else if (caracter == '}' || caracter == ']' || caracter == ')') {
                cierreCont++;
            }
        }

        return aperturaCont == cierreCont;
    }
      public static String acomodarArreglo(String arreglo) {
        StringBuilder resultado = new StringBuilder();
        int balance = 0;

        for (char c : arreglo.toCharArray()) {
            if (c == '(' || c == '[' || c == '{') {
                balance++;
            } else if (c == ')' || c == ']' || c == '}') {
                balance--;
            }

            if (balance < 0) {
                balance = 0;
                resultado.append("{}");
            }
        }

        for (int i = 0; i < balance; i++) {
            resultado.insert(0, "{");
        }

        for (int i = 0; i > balance; i--) {
            resultado.append("}");
        }

        return resultado.toString();
    }
public static boolean Balanceo(String palabra, Pilas<Character> pila) {
    int contadorApertura = 0;
    int contadorCierre = 0;
    for (char caracter : palabra.toCharArray()) {
        if (caracter == '{' || caracter == '[' || caracter == '(') {
            pila.push(caracter);
            contadorApertura++;
        } else if (caracter == '}' || caracter == ']' || caracter == ')') {
            contadorCierre++;
            if (pila.Vacio() || !esPar(pila.tope(), caracter)) {
                return false; // No está balanceada
            }
            pila.pop(); // Elimina la apertura correspondiente de la pila
        }
    }

    return  pila.Vacio();
}    
    
   private static boolean esPar(char apertura, char cierre) {
    return (apertura == '{' && cierre == '}') ||
           (apertura == '[' && cierre == ']') ||
           (apertura == '(' && cierre == ')') ||
           (apertura == '}' && cierre == '{') ||
           (apertura == ']' && cierre == '[') ||
           (apertura == ')' && cierre == '(');
}
   
    public static void main(String[] args) {
        Pilas<Character> pila = new Pilas<>();
        Scanner scanner = new Scanner(System.in);
        Scanner scanner1 = new Scanner(System.in);
        int continuar;  
        do{
        System.out.println("Introduce una palabra:");
        String palabra = scanner.nextLine();
        /*String cadena = Pilas.acomodarArreglo(palabra);
        System.out.println(cadena + "\n");*/
         boolean Balanceo = Pilas.Balanceo(palabra,pila);
         boolean Balanceo2 = Pilas.Balanceo2(palabra,pila);

         if (Balanceo|| Balanceo2) {
            System.out.println("La expresion esta balanceada.\n");
        } else {
            System.out.println("La expresion NO esta balanceada\n.");
        }   
        System.out.println("quieres continuar da cualquier numero\n.");
        System.out.println("si quieres salir presiona 0 \n.");
             continuar = scanner1.nextInt();
       }while(continuar != 0);
       /*
        char[] letras = new char[palabra.length()];
        for (int i = 0; i < palabra.length(); i++) {
            char letra = palabra.charAt(i);
            letras[i] = letra;
            pila.push(letras[i]);
        }
        /*System.out.println("Letras de la palabra:");
        for (char letra : letras) {
            System.out.print(letra + " ");
        }
        for (int i = 1; i <= 20; i++) {
            pila.push("huevos putos perros");
        }
        for (int i = 0; i < letras.length-2; i++) {
                  pila.pop();
        
        }*/
       // pila.tope();
        /*while (!pila.Vacio()) {
            System.out.println(pila.pop());*/
        }
}