
# Clojure Parallelized Vertical Multiple-Carrousel Inventory System

Use multiple vertical carrusels in a Parallelized system, which is more efficent due to the use of a thread per carrousel, rather than a single thread for all carrousels (the traditional way).

## Single Vertical Carrousel example

![App Screenshot](https://cardinalintegrated.com/wp-content/uploads/2021/03/Vertical-Carousel.jpg)


## Automaton movement solution
![App Screenshot]([https://cardinalintegrated.com/wp-content/uploads/2021/03/Vertical-Carousel.jpg](https://raw.githubusercontent.com/eleazarmeza/ClojureParallelizedCarrouselInventorySystem/main/LeftRightAutomaton.png))

![App Screenshot]([https://cardinalintegrated.com/wp-content/uploads/2021/03/Vertical-Carousel.jpg](https://raw.githubusercontent.com/eleazarmeza/ClojureParallelizedCarrouselInventorySystem/main/UpDownAutomaton.png))


## Features

m = rows
n = columns
w = number of carrousels
q = quantity
x = specific carrusel


- (Create m n x) carrousels

- (go-up-in x) carrousel
- (go-down-in x) carrousel
- (go-left-in x) carrousel
- (go-right-in x) carrousel

- (add q in x) carrousel
- (take q in x) carrousel

- (add q "product" in x) carrousel
- (take q "product" in x) carrousel

- (total-inventory-value in x) carrousel
- (total-inventory-value in all) carrousels
- (lower-products in all) carrousels

- (change price in x) carrousel

- (search "product" in specific/all) carrousels
