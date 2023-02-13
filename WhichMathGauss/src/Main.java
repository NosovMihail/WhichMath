import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {

    private static int n;
    private static double[][] a = new double[n][n];
    private static double[] b = new double[n];

    public static void main (String[] args){
        read();
        Answer answer;
        try {
            answer = calculate(n, a, b);
            answer.print(true);
        } catch (badMatrixException e) {
            System.out.println("Введённая система несовместна");
        }
    }

    private static void read() {
        Scanner in = new Scanner(System.in);
        System.out.print("Введите n или название файла: ");
        String s = in.nextLine();
        try {
            n = Integer.parseInt(s); //если число, то переводим его из строки, если нет, ловим ошибку
        }catch (NumberFormatException e){
            try{
                in = new Scanner(Paths.get(s)); //ошибку поймали, проверяем файл
                n = in.nextInt(); //заменили способ чтения и прочитали n
            }catch (IOException ex) {
                System.out.println("Файл не найден"); //не файл и не число, спросим ещё раз
                read();
                return;
            }
        }
        System.out.println("Ввод коэффициентов матрицы");
        a = new double[n][n];
        b = new double[n];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                a[i][j] = in.nextDouble(); //читаем коэффициенты левой части
            }
            b[i] = in.nextDouble(); //... и правой
        }
        System.out.println("Коэффициенты считаны");
    }

    private static Answer calculate(int n, double[][] a, double[] b) throws badMatrixException {
        double[][] aR = a; //Для вычисления невязкости
        double[] bR = b;
        double[] x = new double[n];
        int swapCounter = 0;
        for(int i = 0; i < n - 1; i++){
            if(a[i][i] == 0) {  //если "первый" коэффициент в строке равен нулю
                boolean notZeroExists = false;
                for (int j = i + 1; j < n; j++) { //ищем строку с ненулевым
                    if (a[j][i] != 0){
                        a = swapA(a, i, j);  //меняем местами строки
                        b = swapB(b, i, j);
                        notZeroExists = true;
                        break;
                    }
                }
                if(!notZeroExists){
                    throw new badMatrixException(); //если не нашли, значит система несовмеснтна, кидаем ошибку ввода
                }
                swapCounter++; //увеличиваем счётчик замен
            }
            for(int k = i + 1; k < n; k++){
                double c = a[k][i]/a[i][i]; //коэффициент для вычитания строк
                a[k][i] = 0;
                for(int j = i + 1; j < n; j++){
                    a[k][j] -= c*a[i][j]; //вычитание строк
                }
                b[k] -= c*b[i];
            }
        }
        if(a[n-1][n-1] == 0){ // проверяем, что единственный коэффициент в последней строке не ноль
            throw new badMatrixException();
        }
        x[n-1] = b[n-1]/a[n-1][n-1]; //вычисляем неизвестную в последней строке
        for(int i = n - 2; i >= 0; i--){
            double s = 0;
            for(int j = i + 1; j < n; j++){
                s += a[i][j]*x[j]; //сумма очередной строки
            }
            x[i] = (b[i] - s)/a[i][i]; //единственная оставшаяся неизвестная в строке
        }
        double det = Math.pow(-1, swapCounter); //Вычисление детерминанта
        for(int i = 0; i < n; i++){
            det *= a[i][i];
        }
        double[] r = new double[n]; // невязка
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                r[i] += aR[i][j]*x[j];
            }
            r[i] -= bR[i];
        }
        Answer answer = new Answer(det, n, a, b, x, r);
        return answer;
    }

    private static  double[][] swapA(double[][] a, int i, int j){
        double[] line = a[i];
        a[i] = a[j];
        a[j] = line;
        return a;
    }

    private static  double[] swapB(double[] b, int i, int j){
        double l = b[i];
        b[i] = b[j];
        b[j] = l;
        return b;
    }
}
