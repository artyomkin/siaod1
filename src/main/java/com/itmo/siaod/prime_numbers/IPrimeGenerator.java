package com.itmo.siaod.prime_numbers;

public interface IPrimeGenerator {
   static Long findNextPrime(Long n){
      if (n == null) return null;
      Integer cnt = 1;
      while (!isPrime((n + cnt))){
         cnt++;
      }
      return n + cnt;
   }

   static Boolean isPrime(Long n){
      if (n == null) return null;
      if (n <= 1) {
         return false;
      }
      if (n == 2 || n == 3){
         return true;
      }
      if (n % 2 == 0) {
         return false;
      }
      return IDeterministicMillerRabinTest.testMillerRabinDeterministic(n);
   }
}
