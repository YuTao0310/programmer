// describe("pow", function() {

//     it("raises to n-th power", function() {
//       assert.equal(pow(2, 3), 8);
//     });

//     it("3 raised to power 4 is 81", function() {
//         assert.equal(pow(3, 4), 81);
//     });

//     it("3 raised to power 4 is 81", function() {
//     assert.equal(pow(3, 0), 1);
//    });
  
//   });

describe("pow", function() {

    describe("raises x to power 3", function() {
  
      function makeTest(x) {
        let expected = x * x * x;
        it(`${x} in the power 3 is ${expected}`, function() {
          assert.equal(pow(x, 3), expected);
        });
      }
  
      for (let x = 1; x <= 5; x++) {
        makeTest(x);
      }
  
    });

    // describe("raises x to power 3", function() {
  
    //     function makeTest(x) {
    //       let expected = x * x * x * 4;
    //       it(`${x} in the power 3 is ${expected}`, function() {
    //         assert.equal(pow(x, 4), expected);
    //       });
    //     }
    
    //     for (let x = 1; x <= 5; x++) {
    //       makeTest(x);
    //     }
    
    //   });
  
    // ……可以在这里写更多的测试代码，describe 和 it 都可以添加在这。
  });