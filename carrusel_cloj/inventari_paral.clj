(ns carrusel-cloj.inventari_paral
  (:require [carrusel-cloj.carru_paralel :as basesp])
  (:require [carrusel-cloj.busq_paralel :as busquedasp])
  (:require [carrusel-cloj.busquedas :as busquedas]))


#_(def ult-Pos-int (Integer/parseInt (bases/leer-U)))
#_(def base-cants1 (bases/leer-CANTIDADES))
#_(def base-precs1 (bases/leer-PRECIOS))



(defn calcular-valor-inventario [base-cant base-prec]
  (cond
    (empty? base-cant) 0 ; Caso base: no hay más productos, devuelve 0
    :else
    (+ (* (busquedasp/buscar-prodDevuelveCant (second (first base-cant)) base-cant)
          (busquedasp/buscar-IDDevuelvePRECIO (first (first base-cant)) base-prec))
       (calcular-valor-inventario (rest base-cant) base-prec))))


;(def valor-total (calcular-valor-inventario base-cants1 base-precs1))

;(println valor-total)


; Esta devuelve el valor total de cada CARRUSEL SOLO ENLISTA
(defn calcular-valor-inventario2 []
  (let [num-carruseles (basesp/leer-NC)
        rango (range 0 (Integer/parseInt num-carruseles))
        particiones (partition-all 1 rango)
        productos (pmap busquedasp/leer-CANTIDADES (map first particiones))
        precios (pmap busquedasp/leer-PRECIOS (map first particiones))
        valor-total (pmap calcular-valor-inventario productos precios)]
    valor-total))

(calcular-valor-inventario2)





; Calcula el valor total de cada CARRUSEL y dice CUAL
(defn calcular-valor-inventario3 []
  (let [num-carruseles (basesp/leer-NC)
        rango (range 0 (Integer/parseInt num-carruseles))
        particiones (partition-all 1 rango)
        productos (pmap busquedasp/leer-CANTIDADES (map first particiones))
        precios (pmap busquedasp/leer-PRECIOS (map first particiones))
        valor-total (pmap (fn [[indice producto precio]]
                            (str "Carr" (inc indice) ": $"
                                 (calcular-valor-inventario producto precio)))
                          (pmap vector rango productos precios))]
    (println valor-total)))

(calcular-valor-inventario3)



; Calcula el valor total de todos los CARRUSELES


(defn valor-total-todos2 []
  (let [lista-valores (calcular-valor-inventario2)
        valor-total (reduce + lista-valores)]
    (println (str "El valor de inventario todos los carruseles es: $" valor-total))))

(valor-total-todos2)






(defn valor-total-todos3 []
  (let [lista-valores (calcular-valor-inventario2)
        valor-total (reduce + lista-valores)]
    valor-total))

(valor-total-todos3)



;----------------------------------------------------------------------------------------------------------------------------









;------Productos Bajos (menos de 10 productos)----------------------------------------------------------------------------------------------------------------------------




(defn enlista-bajos [base-cant]
  (if (empty? base-cant)
    '() ; Caso base: no hay más productos, devuelve una lista vacía
    (concat (if (or (nil? (nth (first base-cant) 2))
                    (< (nth (first base-cant) 2) 10))
              [(nth (first base-cant) 1)]
              '())
            (enlista-bajos (rest base-cant)))))

#_
(defn imprimir-productos-bajos []
  (let [productos-bajos (enlista-bajos (basesp/leer-CANTIDADES ))]
    (println "Productos con cantidad nula o menor a 10:")
    (doseq [producto productos-bajos]
      (println producto))))

;(imprimir-productos-bajos)




; Esta devuelve el valor total de cada CARRUSEL SOLO ENLISTA
(defn productos-bajos3 []
  (let [num-carruseles (basesp/leer-NC)
        rango (range 0 (Integer/parseInt num-carruseles))
        particiones (partition-all 1 rango)
        productos (pmap busquedasp/leer-CANTIDADES (map first particiones))
        precios (pmap busquedasp/leer-PRECIOS (map first particiones))
        valor-total (pmap enlista-bajos productos)]
    (println "Los productos con cantidad nula o menor a 10 son:")
    valor-total))

(productos-bajos3)



; Esta devuelve productos bajos presentados por carrusel
(defn productos-bajos4 []
  (let [num-carruseles (basesp/leer-NC)
        rango (range 0 (Integer/parseInt num-carruseles))
        particiones (partition-all 1 rango)
        productos (pmap busquedasp/leer-CANTIDADES (map first particiones)) 
        lista-bajos (pmap (fn [[indice producto]]
                            [(str "Carr" (inc indice) ": ") (enlista-bajos producto)])
                          (map vector rango productos))]
    (println)
    (println "Los productos con cantidad nula o menor a 10 son:")
    (doseq [[carrusel productos-bajos] lista-bajos]
      (print carrusel)
      (doseq [producto productos-bajos]
        (print producto " "))
      (println))
    (println)))

(productos-bajos4)




;-------- Top10% valor en invetnario----------------------------------------------------------------------------------------------------------------------------


(defn obtener-top-carruseles []
  (let [num-carruseles (basesp/leer-NC) ; Obtiene el número de carruseles desde una función llamada leer-NC en el espacio de nombres basesp
        rango (range 0 (Integer/parseInt num-carruseles))
        particiones (partition-all 1 rango)
        productos (pmap busquedasp/leer-CANTIDADES (map first particiones)) ; Obtiene las cantidades de productos para cada carrusel utilizando una función llamada leer-CANTIDADES en el espacio de nombres busquedasp
        precios (pmap busquedasp/leer-PRECIOS (map first particiones)) ; Obtiene los precios de productos para cada carrusel utilizando una función llamada leer-PRECIOS en el espacio de nombres busquedasp
        valor-total (Integer/parseInt valor-total-todos3) ; Obtiene un valor total (valor-total-todos3) y lo convierte en un entero
        valor-total-todos (reduce + valor-total) ; Calcula la suma de todos los valores totales
        limite (Math/round (* 0.1 valor-total-todos)) ; Calcula el límite del top 10% como el 10% del valor total redondeado
        carruseles (mapv #(str "Carr" (inc %)) rango) ; Genera una lista de nombres de carruseles
        productos-bajos (mapv #(enlista-bajos (nth productos %)) rango) ; Obtiene los productos bajos para cada carrusel utilizando una función llamada enlista-bajos
        carruseles-valor (mapv vector carruseles valor-total) ; Combina los nombres de carruseles con el valor total para cada carrusel
        carruseles-top (take-while #(<= (last %) limite) carruseles-valor)] ; Obtiene los carruseles cuyo valor total está por debajo o igual al límite
    (mapv first carruseles-top))) ; Obtiene solo los nombres de carruseles del resultado obtenido

(defn imprimir-top-carruseles []
  (let [top-carruseles (obtener-top-carruseles)]
    (println "Carruseles con el top 10% de mayor valor en inventario:")
    (doseq [carrusel top-carruseles]
      (println carrusel))))

(imprimir-top-carruseles)
