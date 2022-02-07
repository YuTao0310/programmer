let n = 28
let p = 29
showPrimes(n)
showPrimes(p)
function showPrimes(n) {
    nextPrime:
    for (let i = 2; i < n; i++) {
  
      // 检测 i 是否是一个质数（素数）
      for (let j = 2; j < i; j++) {
        if (i % j == 0) continue nextPrime;
      }
  
      console.log(i);
    }
  }