import java.util.HashSet;

public class HuetaGenerator {
    public static HashSet<String> sraka = new HashSet<>();
    public static void main(String[] ass) {
        nextDoor(8, 10);
        sraka.forEach(x -> System.out.println(x));
        System.out.println("parent(X,Y):-\nmother(X,Y);\nfather(X,Y).");
        for(int i = 2; i < 14; i++){
            char a = 'A';
            System.out.println("gen" + i + "(B," + (char)(a + 1 + i) + "):-");
            for(int j = i; j > 0; j--){
                char a1 = (char)(a + j);
                char a2 = (char)(a1 + 1);
                System.out.print("parent(" + a1 + "," + a2 + ")");
                if(j == 1){
                    System.out.println(".");
                }else{
                    System.out.println(",");
                }
            }
            System.out.println("fatherGen" + i + "(B," + (char)(a + 1 + i) + "):-");
            for(int j = i; j > 0; j--){
                char a1 = (char)(a + j);
                char a2 = (char)(a1 + 1);
                System.out.print("father(" + a1 + "," + a2 + ")");
                if(j == 1){
                    System.out.println(".");
                }else{
                    System.out.println(",");
                }
            }
        }
    }
    public static void nextDoor(int jeppa, int perdole){
        perdole--;
        if(perdole != 0) {
            if ((jeppa - 1) % 3 == 0) {
                sraka.add(new String("mother(" + jeppa + "," + (jeppa - 1) / 3 + ")."));
                System.out.println("father(" + jeppa + "," + jeppa * 2 + ").");
                nextDoor(jeppa * 2, perdole);
                nextDoor((jeppa - 1) / 3, perdole);
            } else {
                System.out.println("father(" + jeppa + "," + jeppa * 2 + ").");
                nextDoor(jeppa * 2, perdole);
            }
        }
    }
}
//father(8,16).
//father(16,32).
//father(32,64).
//father(64,128).
//father(128,256).
//father(256,512).
//father(512,1024).
//father(1024,2048).
//father(2048,4096).
//father(341,682).
//father(85,170).
//father(170,340).
//father(340,680).
//father(28,56).
//father(56,112).
//father(9,18).
//father(21,42).
//father(42,84).
//father(84,168).
//father(168,336).
//father(336,672).
//father(5,10).
//father(10,20).
//father(20,40).
//father(40,80).
//father(80,160).
//father(160,320).
//father(320,640).
//father(53,106).
//father(13,26).
//father(26,52).
//father(52,104).
//father(4,8).
//father(8,16).
//father(1,2).
//father(3,6).
//father(6,12).
//father(12,24).
//father(24,48).
//father(48,96).
//mother(4,1).
//mother(16,5).
//mother(40,13).
//mother(85,28).
//mother(10,3).
//mother(1024,341).
//mother(160,53).
//mother(256,85).
//mother(1,0).
//mother(13,4).
//mother(64,21).
//mother(52,17).
//mother(340,113).
//mother(28,9).
//parent(X,Y):-
//mother(X,Y);
//father(X,Y).
//gen2(B,D):-
//parent(C,D),
//parent(B,C).
//fatherGen2(B,D):-
//father(C,D),
//father(B,C).
//gen3(B,E):-
//parent(D,E),
//parent(C,D),
//parent(B,C).
//fatherGen3(B,E):-
//father(D,E),
//father(C,D),
//father(B,C).
//gen4(B,F):-
//parent(E,F),
//parent(D,E),
//parent(C,D),
//parent(B,C).
//fatherGen4(B,F):-
//father(E,F),
//father(D,E),
//father(C,D),
//father(B,C).
//gen5(B,G):-
//parent(F,G),
//parent(E,F),
//parent(D,E),
//parent(C,D),
//parent(B,C).
//fatherGen5(B,G):-
//father(F,G),
//father(E,F),
//father(D,E),
//father(C,D),
//father(B,C).
//gen6(B,H):-
//parent(G,H),
//parent(F,G),
//parent(E,F),
//parent(D,E),
//parent(C,D),
//parent(B,C).
//fatherGen6(B,H):-
//father(G,H),
//father(F,G),
//father(E,F),
//father(D,E),
//father(C,D),
//father(B,C).
//gen7(B,I):-
//parent(H,I),
//parent(G,H),
//parent(F,G),
//parent(E,F),
//parent(D,E),
//parent(C,D),
//parent(B,C).
//fatherGen7(B,I):-
//father(H,I),
//father(G,H),
//father(F,G),
//father(E,F),
//father(D,E),
//father(C,D),
//father(B,C).
//gen8(B,J):-
//parent(I,J),
//parent(H,I),
//parent(G,H),
//parent(F,G),
//parent(E,F),
//parent(D,E),
//parent(C,D),
//parent(B,C).
//fatherGen8(B,J):-
//father(I,J),
//father(H,I),
//father(G,H),
//father(F,G),
//father(E,F),
//father(D,E),
//father(C,D),
//father(B,C).
//gen9(B,K):-
//parent(J,K),
//parent(I,J),
//parent(H,I),
//parent(G,H),
//parent(F,G),
//parent(E,F),
//parent(D,E),
//parent(C,D),
//parent(B,C).
//fatherGen9(B,K):-
//father(J,K),
//father(I,J),
//father(H,I),
//father(G,H),
//father(F,G),
//father(E,F),
//father(D,E),
//father(C,D),
//father(B,C).
//gen10(B,L):-
//parent(K,L),
//parent(J,K),
//parent(I,J),
//parent(H,I),
//parent(G,H),
//parent(F,G),
//parent(E,F),
//parent(D,E),
//parent(C,D),
//parent(B,C).
//fatherGen10(B,L):-
//father(K,L),
//father(J,K),
//father(I,J),
//father(H,I),
//father(G,H),
//father(F,G),
//father(E,F),
//father(D,E),
//father(C,D),
//father(B,C).
//gen11(B,M):-
//parent(L,M),
//parent(K,L),
//parent(J,K),
//parent(I,J),
//parent(H,I),
//parent(G,H),
//parent(F,G),
//parent(E,F),
//parent(D,E),
//parent(C,D),
//parent(B,C).
//fatherGen11(B,M):-
//father(L,M),
//father(K,L),
//father(J,K),
//father(I,J),
//father(H,I),
//father(G,H),
//father(F,G),
//father(E,F),
//father(D,E),
//father(C,D),
//father(B,C).
//gen12(B,N):-
//parent(M,N),
//parent(L,M),
//parent(K,L),
//parent(J,K),
//parent(I,J),
//parent(H,I),
//parent(G,H),
//parent(F,G),
//parent(E,F),
//parent(D,E),
//parent(C,D),
//parent(B,C).
//fatherGen12(B,N):-
//father(M,N),
//father(L,M),
//father(K,L),
//father(J,K),
//father(I,J),
//father(H,I),
//father(G,H),
//father(F,G),
//father(E,F),
//father(D,E),
//father(C,D),
//father(B,C).
//gen13(B,O):-
//parent(N,O),
//parent(M,N),
//parent(L,M),
//parent(K,L),
//parent(J,K),
//parent(I,J),
//parent(H,I),
//parent(G,H),
//parent(F,G),
//parent(E,F),
//parent(D,E),
//parent(C,D),
//parent(B,C).
//fatherGen13(B,O):-
//father(N,O),
//father(M,N),
//father(L,M),
//father(K,L),
//father(J,K),
//father(I,J),
//father(H,I),
//father(G,H),
//father(F,G),
//father(E,F),
//father(D,E),
//father(C,D),
//father(B,C).
