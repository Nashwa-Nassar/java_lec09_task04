# E-Commerce Inventory & Shopping Cart System

**DEPI – Software Testing Track | Part 01: Java Fundamentals**
Instructor: Mina Younan — Session 09, Task A

A Java Maven console application for a small online store that sells
**Clothing** and **Devices**. Customers can browse inventory, add
items to a cart, view the cart, check out, and return items to stock —
all through an interactive command-line menu.

## Class hierarchy

```
                Item (abstract)
        name, price, availableCopies
     sell() / returnItem() / getCategory()*
                    ▲
        ┌───────────┴───────────┐
   Clothing (abstract)      Device (abstract)
   size, color              brand, warrantyMonths
        ▲                        ▲
  ┌─────┼─────┐            ┌─────┼──────┐
Shirt  Socks  Hat       Printer Laptop Projector
material pairsInPack style  isColor  ramGB  lumens
```

- **`Item`** — abstract base class. Holds `name`, `price`,
  `availableCopies`. `sell()` decrements stock and throws
  `OutOfStockException` if none is left; `returnItem()` increments
  stock; `getCategory()` is abstract, implemented polymorphically by
  every leaf class.
- **`Clothing`** — abstract, extends `Item`. Adds `size`, `color`.
- **`Device`** — abstract, extends `Item`. Adds `brand`, `warrantyMonths`.
- **`Shirt` / `Socks` / `Hat`** — concrete clothing types, extending
  `Clothing`, each adding one specific attribute (`material`,
  `pairsInPack`, `style`) and returning `"Clothing - Shirt"`,
  `"Clothing - Socks"`, `"Clothing - Hat"` from `getCategory()`.
- **`Printer` / `Laptop` / `Projector`** — concrete device types,
  extending `Device`, each adding one specific attribute (`isColor`,
  `ramGB`, `lumens`) and returning `"Device - Printer"`,
  `"Device - Laptop"`, `"Device - Projector"` from `getCategory()`.
- **`OutOfStockException`** — small unchecked exception thrown by
  `sell()` when stock has run out, caught and handled gracefully by
  `Cart.checkout()`.
- **`Cart`** — aggregates a `List<Item>` (not owning the items'
  lifecycle — they still belong to the store inventory). Provides
  `addItem()`, `removeItem()`, `calculateTotal()`, and `checkout()`.

## How checkout works

`Cart.checkout()` calls `sell()` on every item currently in the cart.
Items that sell successfully are charged, printed on an itemized
receipt, and removed from the cart. Any item that's run out of stock
in the meantime is reported and simply left in the cart instead of
failing the whole checkout.

## Console menu

```
1. View Store Inventory   - lists every item with stock, category, and details
2. Add Item to Cart       - pick an item by index from the inventory
3. View Cart              - shows cart contents (via getCategory()) and the total
4. Checkout                - sells cart items, prints a receipt, clears the cart
5. Return Item             - returns a unit of an inventory item back to stock
6. Exit                    - terminates the program
```

## Project structure

```
ecommerce-store/
├── pom.xml
└── src/
    └── main/
        └── java/
            └── com/
                └── depi/
                    └── store/
                        ├── Item.java
                        ├── Clothing.java
                        ├── Device.java
                        ├── Shirt.java
                        ├── Socks.java
                        ├── Hat.java
                        ├── Printer.java
                        ├── Laptop.java
                        ├── Projector.java
                        ├── OutOfStockException.java
                        ├── Cart.java
                        └── Main.java   # interactive CLI entry point
```

## Requirements

- Java 11+
- Maven 3.6+

## Build & run

```bash
mvn compile
mvn exec:java -Dexec.mainClass="com.depi.store.Main"

# or
mvn package
java -jar target/ecommerce-store.jar
```

The app pre-populates a small catalog (a shirt, socks, a hat, a
printer, a laptop, and a projector) on startup so you can try every
menu option immediately.

## License

Coursework project for the DEPI Software Testing Track — free to use
for learning and reference.
