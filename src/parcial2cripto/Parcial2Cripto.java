/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parcial2cripto;
import java.util.ArrayList;
import java.util.List;
import java.math.BigInteger;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;
/**
 *
 * @author Sunny
 */
public class Parcial2Cripto {

    /**
     * @param args the command line arguments
     */
    
    static BigInteger biginteger_2 = new BigInteger("2");
    public static void main(String[] args) {
        // TODO code application logic here
        /*List<BigInteger> response = new ArrayList<>();
        BigInteger a = BigDecimal.valueOf(Math.pow(372, 1)).toBigInteger();
        BigInteger b = BigDecimal.valueOf(Math.pow(321, 1) + 0).toBigInteger();
        response = EEA(a,b);
        System.out.println(Power_Mod(BigInteger.valueOf(2),BigInteger.valueOf(23456),BigInteger.valueOf(8970)));
        System.out.println(floorDiv(BigInteger.valueOf(22),BigInteger.valueOf(6)));
        System.out.println("Respuesta");
        for (int i = 0; i < response.size();i++){
            System.out.println(response.get(i));
        }*/
        
        System.out.println("Es primo??");
        System.out.println(MillerRabin(new BigInteger("1000000039"),biginteger_2));
        
        //FoundPrimitiveElement(39,b,39);
    }
    
    //Extended Euclides Algorithm
    public  static List<BigInteger> EEA(BigInteger a, BigInteger b){
        List<BigInteger> dxy = new ArrayList<>();
        List<BigInteger> _dxy = new ArrayList<>();
        if (b == BigInteger.valueOf(0)){
            dxy.add(a);
            dxy.add(BigInteger.valueOf(1));
            dxy.add(BigInteger.valueOf(0));
            return dxy;
        }else{
        BigInteger q =  floorDiv(a, b);
        _dxy = EEA(b, a.mod(b));
        dxy.add(_dxy.get(0));
        dxy.add(_dxy.get(2));
        dxy.add(_dxy.get(1).subtract(q.multiply(_dxy.get(2))));
        
        }
        return dxy;
    }
    
    public static BigInteger floorDiv(BigInteger a, BigInteger b) {
    // divideAndRemainder returns quotient and remainder in array
        BigInteger[] qr = a.divideAndRemainder(b);  
        return qr[0].signum() >= 0 || qr[1].signum() == 0 ? 
             qr[0] : qr[0].subtract(BigInteger.ONE);
    }
    
    
    //Power mod
    
    public static BigInteger Power_Mod(BigInteger a, BigInteger b, BigInteger n){
        BigInteger curr = a.mod(n);
        BigInteger res = BigInteger.valueOf(1);
        while (b.compareTo(BigInteger.valueOf(0)) == 1){
            if (b.mod(BigInteger.valueOf(2)).compareTo(BigInteger.valueOf(1)) == 0){
                res = res.multiply(curr).mod(n);
            }
            curr = curr.multiply(curr).mod(n);
            b = floorDiv(b,BigInteger.valueOf(2));
        }
        return res;
    }
    
    //Miller Rabin
    public static String MillerRabin(BigInteger n,BigInteger s){ 
        s = s.subtract(BigInteger.ONE);
        for (BigInteger i = BigInteger.ZERO; s.compareTo(i) != 0; i = i.add(BigInteger.ONE)){
            BigInteger a = getRandomBigInteger(n);
            if (Witness(a,n) == false){
                return "Compuesto";
               
            }
        }
        return "Primo";
    }
    //Random Biginteger
    public static BigInteger getRandomBigInteger(BigInteger n) {
        Random rand = new Random();
        BigInteger upperLimit = n;
        BigInteger result;
        do {
            result = new BigInteger(upperLimit.bitLength(), rand); // (2^4-1) = 15 is the maximum value
        }while(result.compareTo(upperLimit) >= 0);   // exclusive of 13
        return result;
    }
    
    //Witness
    public static boolean Witness(BigInteger a,BigInteger p){
        BigInteger k = new BigInteger("0");
        BigInteger b = new BigInteger("2");
        BigInteger m = p.subtract(BigInteger.ONE);
        
        if (p.mod(biginteger_2).compareTo(BigInteger.valueOf(0)) == 0){
            return false;
        }
        while (m.mod(biginteger_2).compareTo(BigInteger.valueOf(0)) == 0){
            m = m.divide(biginteger_2);
            k = k.divide(BigInteger.ONE);
        }
        b = Power_Mod(biginteger_2,m,p);
        if (b.compareTo(BigInteger.ONE) == 0 || b.compareTo(p.subtract(BigInteger.ONE)) == 0){
           return true; 
        }
        for (BigInteger j = BigInteger.ZERO; j.compareTo(k.subtract(BigInteger.ONE)) != 0; j.add(BigInteger.ONE)){
           b = Power_Mod(b,biginteger_2,p);
           if (b.compareTo(p.subtract(BigInteger.ONE)) == 0){
               return true;
           }           
           if (b.compareTo(BigInteger.ONE) == 1){
               return false;
           }
        }
        return false;
    }
    
    
    /*
    def my_power(a,b,mod):
    b_bin = bin(b)[2:]
    z = 1
    for bit in b_bin:
        if bit =='0':
          z = (z**2)%mod
        else:
          z = ((z**2)*a)%mod   
    return z
    */
          
 
    public static int FoundPrimitiveElement(int a, int b,int n){
        int result = 0;
        for (int i =  0; i < n; i++){
            int q = Math.floorDiv((int) Math.pow(a, i),(int) b);       
            int r = a - b*q;
            System.out.println(r);
        }
        return result;
    }
}
