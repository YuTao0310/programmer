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