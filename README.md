# external file structure
db: store all the .db file.  
lib: include the sqlite JDBC.  
sqlite: sqlite tools.  

# project structure
The project used MVC pattern.  
V: View. In this level, you should do all things about UI.  
M: Model. In this level, you should do all things about BD.  
C: Controller. In the level, you should connect the M and V, which means all the logic things are there.  

## example
EX: User make a transfer from bank account A to bank account B.  
In V, user do a transfer action. Then, the transfer amount will be passed to C.  
In C, it will do some check, like whether the user has enough money. And then, use M to change all related data in A and B.  
In M, will use sqlite to update all data.

Note:  
You should use interface to contract your method.  

