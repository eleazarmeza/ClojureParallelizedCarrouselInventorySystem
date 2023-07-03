(ns carrusel-cloj.inventarios
  (:require [carrusel-cloj.bases :as bases])
  (:require [carrusel-cloj.busquedas :as busquedas]))


#_
(def ult-Pos-int (Integer/parseInt (bases/leer-U)))
#_
(def base-cants1 (bases/leer-CANTIDADES))
#_
(def base-precs1 (bases/leer-PRECIOS))



(defn calcular-valor-inventario [base-cant base-prec]
  (cond
    (empty? base-cant) 0 ; Caso base: no hay más productos, devuelve 0
    :else
    (+ (* (busquedas/buscar-prodDevuelveCant (second (first base-cant)) base-cant)
          (busquedas/buscar-IDDevuelvePRECIO (first (first base-cant)) base-prec))
       (calcular-valor-inventario (rest base-cant) base-prec))))

;(def valor-total (calcular-valor-inventario base-cants1 base-precs1))

;(println valor-total)

(defn calcular-valor-inventario2 []
  (let [base-cants1 (bases/leer-CANTIDADES)
        base-precs1 (bases/leer-PRECIOS)
        valor-total (calcular-valor-inventario base-cants1 base-precs1)]
    (println valor-total)))

(calcular-valor-inventario2)




;------Productos Bajos (menos de 10 productos)----------------------------------------------------------------------------------------------------------------------------




(defn enlista-bajos [base-cant]
  (if (empty? base-cant)
    '() ; Caso base: no hay más productos, devuelve una lista vacía
    (concat (if (or (nil? (nth (first base-cant) 2))
                    (< (nth (first base-cant) 2) 10))
              [(nth (first base-cant) 1)]
              '())
            (enlista-bajos (rest base-cant)))))

(defn imprimir-productos-bajos []
  (let [productos-bajos (enlista-bajos (bases/leer-CANTIDADES))]
    (println "Productos con cantidad nula o menor a 10:")
    (doseq [producto productos-bajos]
      (println producto))))

(imprimir-productos-bajos)




