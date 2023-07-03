
(ns carrusel-cloj.busquedas
  (:require [carrusel-cloj.carru_paralel :as basesp]) 
  (:require [carrusel-cloj.movimiento :as movimiento]))




; -------- LEER CANTIDADES Y PRECIOS --------------------------------------------------------------------------------------------

(defn leer-CANTIDADES []
  (let [base-cantidades (slurp "/Users/cristobal/cantidades.txt")]
    (read-string base-cantidades)))

(def base-cants (leer-CANTIDADES))
(println base-cants)


(defn leer-PRECIOS []
  (let [base-precios (slurp "/Users/cristobal/precios.txt")]
    (read-string base-precios)))

(def base-precs (leer-PRECIOS))
(println base-precs)





;-----------busca PORProducto y devuelve CANTIDAD--------------------------------------------------------------------------------------------



(defn buscar-prodDevuelveCant [nombre lista]
  (cond
    (empty? lista) false ; Si la lista está vacía, retorna false
    (= nombre (second (first lista))) (nth (first lista) 2) ; Si el nombre coincide, retorna la cantidad
    :else (buscar-prodDevuelveCant nombre (rest lista))))

(buscar-prodDevuelveCant "Producto2" base-cants)



;-----------busca POR ID y devuelve PRODUCTO--------------------------------------------------------------------------------------------


(defn buscar-IDDevuelvePRODUCTO [nombre lista]
  (cond
    (empty? lista) false ; Si la lista está vacía, retorna false
    (= nombre (first (first lista))) (nth (first lista) 1) ; Si el nombre coincide, retorna la cantidad
    :else (buscar-IDDevuelvePRODUCTO nombre (rest lista))))

(buscar-IDDevuelvePRODUCTO 2 base-cants)


 #_
(def ultimaPosicion (Integer/parseInt (bases/leer-U)))
#_
(println ultimaPosicion)
#_
(buscar-IDDevuelvePRODUCTO ultimaPosicion base-cants)



;-----------busca POR ID y devuelve CANTIDAD--------------------------------------------------------------------------------------------


(defn buscar-IDDevuelveCANTIDAD [nombre lista]
  (cond
    (empty? lista) false ; Si la lista está vacía, retorna false
    (= nombre (first (first lista))) (nth (first lista) 2) ; Si el nombre coincide, retorna la cantidad
    :else (buscar-IDDevuelveCANTIDAD nombre (rest lista))))

(buscar-IDDevuelveCANTIDAD 2 base-cants)







;-----------busca Producto y devuelve ID--------------------------------------------------------------------------------------------



(defn buscar-prodDevuelveID [nombre lista]
  (cond
    (empty? lista) false ; Si la lista está vacía, retorna false
    (= nombre (second (first lista))) (nth (first lista) 0) ; Si el nombre coincide, retorna la cantidad
    :else (buscar-prodDevuelveID nombre (rest lista))))

(buscar-prodDevuelveID "Producto2" base-cants)




;-----------busca Producto, toma ID, busca Producto y devuelve Precio--------------------------------------------------------------------------------------------



(defn buscar-prodDevuelvePrecio [nombre listaProductos listaPrecios]
  (let [ID (buscar-prodDevuelveID nombre listaProductos)]
    (cond
      (empty? listaPrecios) false ; Si la lista está vacía, retorna false
      (= ID (first (first listaPrecios))) (nth (first listaPrecios) 2) ; Si el nombre coincide, retorna el precio
      :else (buscar-prodDevuelvePrecio nombre listaProductos (rest listaPrecios)))))

(buscar-prodDevuelvePrecio "Producto2" base-cants base-precs)


;-----------busca POR ID y devuelve PRECIO--------------------------------------------------------------------------------------------


(defn buscar-IDDevuelvePRECIO [nombre lista]
  (cond
    (empty? lista) false ; Si la lista está vacía, retorna false
    (= nombre (first (first lista))) (nth (first lista) 2) ; Si el nombre coincide, retorna la cantidad
    :else (buscar-IDDevuelvePRECIO nombre (rest lista))))

(buscar-IDDevuelvePRECIO 2 base-precs)




