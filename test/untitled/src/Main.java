public class Main {
    public static void main(String[] args) {
        String cadena = "t1e1s2t, Hell1o, 1th1e, fir2st1";
        String[] parts = cadena.split("|");
        String res="";
        for(int i = 0; i<parts.length;i++){
            if( parts[i] == "1" ){
                System.out.println(9);
            }


        }

        String a = "a";
        if(a == "a" ){
            System.out.println(1);
        }
        System.out.println(res);
    }
}