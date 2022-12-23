/*
References:
https://zh.javascript.info/object-methods
https://zh.javascript.info/arrow-functions
https://zh.javascript.info/call-apply-decorators
https://zh.javascript.info/bind
*/

/*
####################################################
1-高阶函数中的this：arrow中的this为Window，不为user函数，
说明在高阶函数中，this不指向函数对象，而是指向Window，它
不像其他变量一样能够传递下去。
但是，在user定义的x能够在arrow中被使用。
####################################################
function user(){
    let x = 2;
    function arrow(){
      x++;
      this.y = 2;
      alert(this.firstName);
    }
    return arrow;
};
user.firstName = 'Ilya';
user()(); 
*/

/*
####################################################
##2-箭头函数中的this(示例一)：箭头函数没有this，不像一般
函数的this取值为Window，箭头函数的this取值从上下文中获取。
*行若改为一般函数，则this为Window而不为user。
####################################################
*/
let user = {
  firstName: "Ilya",
  sayHi() {
    let arrow = () => alert(this.firstName); // (*)
    arrow();
  }
};

user.sayHi(); // Ilya


/*
####################################################
##2-箭头函数中的this(示例二)：
*行若改为一般函数，则this.title中的this为Window而不为group。
####################################################
let group = {
  title: "Our Group",
  students: ["John", "Pete", "Alice"],

  showList() {
    this.students.forEach(
      student => alert(this.title + ': ' + student) / (*)
    );
  }
};

group.showList();
*/

/*
####################################################
##3-装饰器模式下的this：
*行中this = worker，采用func.call将其与对象绑定。
若改为let result = func(x)，
则无法调用worker中的someMethod。
func.call和func.bind效果类似，只不过func.call返回函数返回值，
而func.bind返回绑定对象后的函数本身。
####################################################
let worker = {
  someMethod() {
    return 1;
  },

  slow(x) {
    alert("Called with " + x);
    return x * this.someMethod(); // (*)
  }
};

function cachingDecorator(func) {
  let cache = new Map();
  return function(x) {
    if (cache.has(x)) {
      return cache.get(x);
    }
    let result = func.call(this, x); // 现在 "this" 被正确地传递了 (*)
    cache.set(x, result);
    return result;
  };
}

worker.slow = cachingDecorator(worker.slow); // 现在对其进行缓存

alert( worker.slow(2) ); // 工作正常
alert( worker.slow(2) ); // 工作正常，没有调用原始函数（使用的缓存）


/*
####################################################
##4-setTimeOut的this（示例一）：
*行采用包装器和箭头函数都能实现这一效果
####################################################
let user = {
  firstName: "John",
  sayHi() {
    alert(`Hello, ${this.firstName}!`);
  }
};

setTimeout(function() {
  user.sayHi(); // Hello, John! (*)
}, 1000);
*/

/*
####################################################
##4-setTimeOut的this（示例二）：
采用bind固定
####################################################
let user = {
  firstName: "John",
  sayHi() {
    alert(`Hello, ${this.firstName}!`);
  }
};

let sayHi = user.sayHi.bind(user); // (*)

// 可以在没有对象（译注：与对象分离）的情况下运行它
sayHi(); // Hello, John!

setTimeout(sayHi, 1000); // Hello, John!

// 即使 user 的值在不到 1 秒内发生了改变
// sayHi 还是会使用预先绑定（pre-bound）的值，该值是对旧的 user 对象的引用
user = {
  sayHi() { alert("Another user in setTimeout!"); }
};
*/

// let user = {
//   firstName: "John"
// };

// function func(phrase) {
//   alert(phrase + ', ' + this.firstName);
// }

// // 将 this 绑定到 user
// let funcUser = func.bind(user);

// funcUser("Hello"); // Hello, John（参数 "Hello" 被传递，并且 this=user）