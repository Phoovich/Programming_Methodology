# Outlines

- OOP
- Class
- Object Instantiation
- Using object data/methods
- Method
- Constructor
- Keywords "this", "static", "final"
- Package
- Method in more details
  - Access modifiers
  - Getter & Setters
  - Passing method arguments
  - toString(),equals()

---

## **OOP (Object-Oriented Programming)**

### **Class**

- A **class** serves as a blueprint to create objects.
- Use the keyword: `new ClassName` to create an object.

```java
class Car { // Class definition
    int speed; // Object property
    int acc;   // Object property
}

class TransportSim {
    public static void main(String[] args) {
        Car a = new Car(); // Create object a
        Car b = new Car(); // Create object b
    }
}
```

---

### **OOP Concepts**

- View everything as an **object**.
- Four main principles:
  - **Encapsulation (Structure)** → Lecture 1
  - **Inheritance (Structure)** → Lecture 2
  - **Abstraction (Structure)** → Lecture 3
  - **Polymorphism (Behavior)** → Lecture 4

---

#### **Encapsulation / Information Hiding (Structure)**

- Determines what should or shouldn't be accessible to users.
- **Accessibility Modifiers**:
  - `+` **public**: Accessible from anywhere.
  - `#` **protected**: Accessible within the class and its subclasses.
  - `-` **private**: Accessible only within the class.

```java
class Car {
    private int speed;  // Private property
    private int acc;    // Private property

    // Public method to safely modify speed
    public void pushAcc() {
        speed = speed + acc;
    }
}

class TransportSim {
    public static void main(String[] args) {
        Car a = new Car();
        Car b = new Car();

        // a.speed = -555; // Not allowed (private)
        a.pushAcc(); // Allowed (public)
    }
}
```

---

#### **Abstraction (Structure)**

- Defines a **template** (all subclasses must follow).
- Example:
  - A **vehicle** has essential components like an engine and wheels.
    - A **Car**, **Motorcycle**, or **Truck** will also have these components.

---

#### **Inheritance (Structure)**

- Subclasses inherit properties and behaviors from an existing class.
- Example:
  - All **CP students** are **engineer students**.
    - Engineer students have certain attributes; CP students inherit these attributes.
    - CP students may have additional attributes that engineer students don’t have.
- Benefits: Reduces code duplication.

```java
class Vehicle {
    private int speed;
    private int acc;

    public void pushAcc() {
        speed = speed + acc;
    }
}

class Car extends Vehicle { // Car is a subclass of Vehicle
    // Car inherits all properties and methods from Vehicle
}
```

---

#### **Polymorphism (Behavior)**

- The same code behaves differently based on the object.
- Example:
  - **Class Pet** includes subclasses: `Cat`, `Dog`, and `Duck`.
    - `cat.speak()` → Meow!
    - `dog.speak()` → Woof!
    - `duck.speak()` → Quack!

```java
class Pet {
    public void speak() {
        System.out.println("Some generic pet sound!");
    }
}

class Cat extends Pet {
    @Override
    public void speak() {
        System.out.println("Meow!");
    }
}

class Dog extends Pet {
    @Override
    public void speak() {
        System.out.println("Woof!");
    }
}

class Duck extends Pet {
    @Override
    public void speak() {
        System.out.println("Quack!");
    }
}

public class Main {
    public static void main(String[] args) {
        Pet myPet = new Dog();
        myPet.speak(); // Output: Woof!
    }
}
```

---

## Class

> class contains 2 main components:

1. data (attributes/propertyk)
2. method

   - Constructor is a special method to create clss

   ```java
   public class Dice {
       //1. attributes
       final static int MAX = 6;
       int facevalue;

       //2. Method

       //2.1 Constructor
       public SimpleDice(int facevalue){
           this.facevalue = facevalue;
       }

       // 2.2 ohter Method
       public int roll(){
           facevalue = (int) (Math.randonm() * MAX) +1;
           return facevalue;
       }

   }

   ```

### How new work

```java
SimpleDice diceA = new SimpleDice(1);
/*
- variable store address suppose address 700;
- diceA.facevalue = 1;
- diceA ---> address 700;
*/
SimpleDice diceB;
// diceB ---- null

diceB = diceA
/*
now diceB point same address diceA
- diceA ---> address 700
- diceB ---> address 700
- diceA.facevalue = 1;
- diceB.facevalue = 1;
*/

diceB.facevalue = 5;

/*
now diceB point same address diceA
- diceA ---> address 700
- diceB ---> address 700
- diceA.facevalue = 5;
- diceB.facevalue = 5;
*/
```

---

### Method Overloading

#### Method sigature

- **name** + **arguments list**

```java
add(int m,int n) ---> add(int,int)
add(doudle m,doudle n) ---> add(doudle,doudle)
add(int x,int y,int z) ---> add(int,int,int)
// same name different arguments list

```

---

### **Constructor**

1. The constructor has the **same name as the class**.
2. Constructors **do not have a return type**.

#### **Example: Constructor in Java**

```java
public class Dice {
    // Constructor with a parameter
    public Dice(int faceValue) {
        this.faceValue = faceValue; // Assign user-provided value
    }

    // Default constructor
    public Dice() {
        this.faceValue = 1; // Assign a default value
    }
}

```

---

### **Keyword: `this`**

```java
public SimpleDice(int faceValue) {
    this.faceValue = faceValue;
    // Explanation:
    // 1. `this.faceValue` refers to the `faceValue` property of the class.
    // 2. `faceValue` refers to the parameter provided by the user.
}
```

---

### **Keyword: `final`**

- The `final` keyword is used to **prevent modification** of variables, methods, or classes.
- Often used with **constants** to ensure their values remain unchanged.

#### **Example: Using `final` in Java**

```java
public class SimpleDice {
    // A constant value that cannot be changed
    final static int MAX = 6;
    int faceValue;

    public SimpleDice(int faceValue) {
        // this.MAX = 7; // Not allowed: final variables cannot be reassigned
        this.faceValue = faceValue;
    }
}
```

---

### **Keyword: `static`**

- this variable belong to class not belong to object

```java
public class SimpleDice {
    static int count = 0;
    final static int MAX = 6;
}

public class SimpleDiceTest1{
    public static void main(String[] args){
        SimpleDice diceA = new SimpleDice(1);
        SimpleDice diceB = new SimpleDice(5);
        SimpleDice diceC = new SimpleDice();

        system.out.println(diceB.facevalue);
        system.out.println(diceC.facevalue);
        // System.out.println(SimpleDice.faceValue);  --> error

        System.out.println(SimpleDice.count); // print 0
        System.out.println(diceA.count); // print 0

        diceB.count = 1;
        System.out.println(SimpleDice.count); // print 1
        System.out.println(diceC.count); // print 1
    }
}
```

1. object variable ---> faceValue

   - ไม่สามารถเรียกผ่าน class ได้

2. class variable ---> count (statice)
   - สามารถเรียกผ่าน class ได้โดยตรง
   - ทุกๆ object แชร์ตัวแปรตัวนี้กัน
   - constant variable --> `final static` and cappitalize

---

## Method and more details

### Access modifiers

| **Specifier**         | **Class** | **Package** | **Subclass** | **World** | **UML Symbol** |
| --------------------- | :-------: | :---------: | :----------: | :-------: | :------------: |
| **private**           |    ✅     |             |              |           |      `-`       |
| **package** (default) |    ✅     |     ✅      |              |           |      `~`       |
| **protected**         |    ✅     |     ✅      |      ✅      |           |      `#`       |
| **public**            |    ✅     |     ✅      |      ✅      |    ✅     |      `+`       |

### Encapsulation

- Information Hiding
- property ใน class จะไม่ยอมให้ access โดยตรง (ใช้ rivate)
- สามารถ เข้าถึงได้ผ่าน getter (mutator),setter (accessor) method
  - setter --> setXXX
  - getter --> getXXX

```java
// setter
public void setFacevalue(int faceValue){
    this.faceValue = Math.max(0,Math.min(MAX, faceValue))
}
//getter
public void getFacevalue(){
    return this.faceValue;
}

public static void main(String[] args)
    SimpleDice diceA = new SimpleDice();
    diceA.setFacevalue(100);
    System.out.println(diceA.getFacevalue()); // print 6
```

---

### `toString()`

```java
class SimpleDice{
    public to String toString(){
        return "The face is " + this.faceValue;
    }
}

public static void  main(String[] atgss){
    SimpleDice diceA = new SimpleDice(5);
    System.out.println(diceA); } // print The face is 5;
// if not have toString() print @xxxxxxxx(address)

```

### `equals()`

```java
  public static void main(String[] args) {
    SimpleDice diceA = new SimpleDice(1);
    SimpleDice diceB = new SimpleDice(1);

    System.out.println(diceA == diceB); // false
    /*
    diceA store address @xxxxxxxx
    diceB store address @yyyyyyyy

    if you want compair use method equals();
    */
  }
```

#### Method equals

```java
    public boolean equals(Object o){
        SimpleDice ohterDice = (SimpleDice) o;
        if(this.faceValue == otherDice.faceValue)
            return true;
        else
            return false
    }


  public static void main(String[] args) {
    SimpleDice diceA = new SimpleDice(1);
    SimpleDice diceB = new SimpleDice(1);

    System.out.println(diceA.equals(diceB)); // true

  }
```

---

### Does `equals` Work as Expected?

| **Property**   | **Description**                                                                                                                                                 |
| -------------- | --------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| **Reflexive**  | For any non-null reference value `x`, `x.equals(x)` should return `true`.                                                                                       |
| **Symmetric**  | For any non-null reference values `x` and `y`, `x.equals(y)` should return `true` if and only if `y.equals(x)` also returns `true`.                             |
| **Transitive** | If `x.equals(y)` returns `true` and `y.equals(z)` returns `true`, then `x.equals(z)` should also return `true`.                                                 |
| **Consistent** | For any non-null reference values `x` and `y`, multiple calls to `x.equals(y)` should consistently return the same result, provided no information is modified. |
| **Non-null**   | For any non-null reference value `x`, `x.equals(null)` should return `false`.                                                                                   |

---

### Notes:

- The `equals` method must be implemented to adhere to these properties.
- Proper implementation ensures reliable behavior when using `equals` for object comparisons.
