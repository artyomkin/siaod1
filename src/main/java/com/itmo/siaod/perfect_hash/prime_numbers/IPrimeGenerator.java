package com.itmo.siaod.perfect_hash.prime_numbers;

public interface IPrimeGenerator {
   static long findNextPrime(long n){
      int cnt = 1;
      while (!isPrime((n + cnt))){
         cnt++;
      }
      return n + cnt;
   }

   static boolean isPrime(long n){
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
