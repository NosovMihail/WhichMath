public class Answer {

    private double det;
    private int n;
    private double[][] a;
    private double[] b;
    private double[] x;
    private double[] r;

    public Answer(double det, int n, double[][] a, double[] b, double[] x, double[] r){
        this.det = det;
        this.n = n;
        this.a = a;
        this.b = b;
        this.x = x;
        this.r = r;
    }

    public void setDet(double det) {
        this.det = det;
    }

    public void setN(int n) {
        this.n = n;
    }

    public void setA(double[][] a) {
        this.a = a;
    }

    public void setB(double[] b) {
        this.b = b;
    }

    public void setX(double[] x) {
        this.x = x;
    }

    public void setR(double[] r) {
        this.r = r;
    }

    public void print(boolean exect){
        if(exect){
            System.out.printf("Детерминант: %s", det);
            System.out.println();
            for(int i = 0; i < n; i++){
                System.out.print("(");
                for(double j: a[i]) System.out.printf("%s \t\t", j);
                System.out.printf("| %s )", b[i]);
                System.out.println();
            }
            System.out.println("Вектор неизвестных:");
            for(double i: x) System.out.printf("%s \t\t", i);
            System.out.println();
            System.out.println("Вектор невязок:");
            for(double i: r) System.out.printf("%e \t\t", i);
            System.out.println();
        } else {
            System.out.printf("Определитель: %.2f", det);
            System.out.println();
            System.out.println("Треугольная матрица:");
            for(int i = 0; i < n; i++){
                System.out.print("(");
                for(double j: a[i]) System.out.printf("%.2f \t\t", j);
                System.out.printf("| %.2f\t\t)", b[i]);
                System.out.println();
            }
            System.out.println("Вектор неизвестных:");
            for(double i: x) System.out.printf("%.2f \t\t", i);
            System.out.println();
            System.out.println("Вектор невязок:");
            for(double i: r) System.out.printf("%e \t\t", i);
            System.out.println();
        }
    }
}
