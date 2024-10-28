package menuutama;
import java.text.DecimalFormat;

public class KriteriaAhp {
    protected int nBanyak = 5; // Ubah jumlah kriteria menjadi 5

    protected double RI[] = {0.0, 0.0, 0.58, 0.90, 1.12, 1.24, 1.32, 1.41, 1.45, 1.49, 1.51, 1.48, 1.56, 1.57, 1.59};
    
    double[][] matriksBerpasangan = new double[nBanyak][nBanyak];
    double[] jumlahMatriksBerpasangan = new double[nBanyak];
    
    double[][] matriksNormalisasi = new double[nBanyak][nBanyak];
    double[] jumlahMatriksNormalisasi = new double[nBanyak];
    double[] prioritas = new double[nBanyak];
    
    double[][] matriksPenjumlahan = new double[nBanyak][nBanyak];
    double[] jumlahMatriksPenjumlahan = new double[nBanyak];
    
    double[] jumlahCekKonsistensi = new double[nBanyak];
    
    DecimalFormat df = new DecimalFormat("#.##");
    
    double IR;

    double getIR(){
        if (nBanyak < RI.length) {
            IR = RI[nBanyak];
        } else {
            IR = 0.0;
        }
        return IR;
    }

    public void setNilaiKriteria(){
        double matriks[][] = {
            {1, 3.0, 5.0, 7.0, 9.0},
            {1.0/3.0, 1, 3.0, 5.0, 7.0},
            {1.0/5.0, 1.0/3.0, 1, 3.0, 5.0},
            {1.0/7.0, 1.0/5.0, 1.0/3.0, 1, 3.0},
            {1.0/9.0, 1.0/7.0, 1.0/5.0, 1.0/3.0, 1}
        };
        for (int row = 0; row < nBanyak; row++) {
            for (int col = 0; col < nBanyak; col++) {
                matriksBerpasangan[row][col] =  matriks[row][col];
            }
        }
    }

    public void MatriksBerpasangan(){
        setNilaiKriteria();
        for (int row = 0; row < nBanyak; row++) {
            for (int col = 0; col < nBanyak; col++) {
                jumlahMatriksBerpasangan[col] +=  matriksBerpasangan[row][col];
            }
        }
    }

    public void MatriksNormalisasi(){
        for (int row = 0; row < nBanyak; row++) {
            for (int col = 0; col < nBanyak; col++) {
                matriksNormalisasi[row][col] = matriksBerpasangan[row][col] / jumlahMatriksBerpasangan[col];
                jumlahMatriksNormalisasi[row] += matriksNormalisasi[row][col];
                prioritas[row] = jumlahMatriksNormalisasi[row] / nBanyak; 
            }
        }
    }

    public void MatriksPenjumlahan(){
        for (int row = 0; row < nBanyak; row++) {
            for (int col = 0; col < nBanyak; col++) {
                matriksPenjumlahan[row][col] = matriksBerpasangan[row][col] * prioritas[col];
                jumlahMatriksPenjumlahan[row] += matriksPenjumlahan[row][col];
            }
        }
    }

    public String getCekKonsistensi(){
        double totalJumlah = 0;
        for (int row = 0; row < nBanyak; row++) {
            jumlahCekKonsistensi[row] = jumlahMatriksPenjumlahan[row] + prioritas[row];
            totalJumlah += jumlahCekKonsistensi[row];
        }
        double ir = getIR();
        double lamdaMaks = totalJumlah / nBanyak;
        double CI = (lamdaMaks - nBanyak) / (nBanyak - 1);
        double CR = CI / ir;
        if (CR <= 0.1) {
            return "Konsisten";
        } else {
            return "Tidak Konsisten"; 
        }    
   }

    public KriteriaAhp(){
        MatriksBerpasangan();
        MatriksNormalisasi();
        MatriksPenjumlahan();
    }
}
