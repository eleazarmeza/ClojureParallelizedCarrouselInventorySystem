(ns carrusel-cloj.busq_paralel
  (:require [carrusel-cloj.carru_paralel :as basesp]))




;-----------lee  PRECIOS y CANTIDADES por INDICE de CARRUSEL--------------------------------------------------------------------------------------------

(defn leer-PRECIOS [carrusel]
  (let [filename (str "/Users/cristobal/precios" carrusel ".txt")
        base-precios (slurp filename)]
    (read-string base-precios)))

(def base-precs (leer-PRECIOS 0))
(println base-precs)



(defn leer-CANTIDADES [carrusel]
  (let [filename (str "/Users/cristobal/cantidades" carrusel ".txt")
        base-cants (slurp filename)]
    (read-string base-cants)))

(def base-cants (leer-CANTIDADES 0))
(println base-cants)






; Fin de lectura de precios y cantidades
;--------------------------------------------------------------------------------------------




; BUSQUEDA POR PRODUCTO Y NUMERO DE CARRUSEL

; Ej. 1 La primera es sin paralelismo, utiliza funciones comunes

; Ej. la segunda usa map

; Ej. 3 la tercera usa pmap combinada con partition-all y es la más eficiente
; PERO despues de haber corrido varias veces.





;-- 1 ---busca POR Producto en UNA BASE y NUMEROdeCARRU y devuelve CANTIDAD--------------------------------------------------------------------------------------------


(defn buscar-prodDevuelveCant [nombre lista]
  (cond
    (empty? lista) false ; Si la lista está vacía, retorna false
    (= nombre (second (first lista))) (nth (first lista) 2) ; Si el nombre coincide, retorna la cantidad
    :else (buscar-prodDevuelveCant nombre (rest lista))))
    

;(buscar-prodDevuelveCant "Producto2" base-cants) ya no servira aqui


(defn buscaProd-CARRUSEL-DevuelveCant [nombre carrusel]
  (let [cantidades (leer-CANTIDADES carrusel)]
    (buscar-prodDevuelveCant nombre cantidades)))

(time (buscaProd-CARRUSEL-DevuelveCant "Producto2" 0))




;-- 2 - Busca en varias bases con MAP y devuelve cantidades--------------------------------------------------------------------------------------------

(defn buscaProd-CARRUSELES-DevuelveCant2 [nombre carruseles]
  (let [cantidades (map leer-CANTIDADES carruseles)]
    (println "Inventario de" nombre "en: ")
    (map #(str "Carr" (inc %1) ": " %2) carruseles (map #(buscar-prodDevuelveCant nombre %) cantidades))))


(time(buscaProd-CARRUSELES-DevuelveCant2 "Producto2" [0 1 2 3 4]))

(time (buscaProd-CARRUSELES-DevuelveCant2 "Producto2" (range 0 5)))




;-- 3 Mas eficiente, usa pmap y partition-all, y busca en varias bases

(defn buscaProd-CARRUSELES-DevuelveCant3 [nombre carruseles]
  (let [particiones (partition-all 1 carruseles)
        cantidades (pmap leer-CANTIDADES (map first particiones))]
    (map (fn [[indice cantidad]]
            (str "Carr" (inc indice) ": " (buscar-prodDevuelveCant nombre cantidad)))
          (map vector carruseles cantidades))))


(time (buscaProd-CARRUSELES-DevuelveCant3 "Producto2" [0 1 2 3 4]))
;ok



; FIN de BUSQUEDA POR PRODUCTO que devuelve CANTIDADES--------------------------------------------------------------------------------------------
;--------------------------------------------------------------------------------------------






; Inicio e BUSQUEDA POR ID que devuelve PRODUCTO

; La primera solo busca un ID en una base

; La segunda busca un ID en varias bases (y es mas eficiente por paralelizacion)







;------  busca POR ID  y devuelve PRODUCTO   --------------------------------------------------------------------------------------------

; 1 Menos eficiente, usa funciones comunes, y solo busca en una base


(defn buscar-IDDevuelvePRODUCTO [nombre lista]
  (cond
    (empty? lista) false ; Si la lista está vacía, retorna false
    (= nombre (first (first lista))) (nth (first lista) 1) ; Si el id coincide, retorna la cantidad
    :else (buscar-IDDevuelvePRODUCTO nombre (rest lista))))

;(time (buscar-IDDevuelvePRODUCTO 2 base-cants))


#_(def ultimaPosicion (Integer/parseInt (bases/leer-U)))
#_(println ultimaPosicion)
#_(buscar-IDDevuelvePRODUCTO ultimaPosicion base-cants)



; 2 Mas eficiente, usa PMAP con partition-all, y busca en varias bases

(defn buscaProd-CARRUSELES-DevuelvePRODUCTO3 [nombre carruseles]
  (let [particiones (partition-all 1 carruseles)
        cantidades (pmap leer-CANTIDADES (map first particiones))]
    (map (fn [[indice cantidad]]
           (str "Carr" (inc indice) ": " (buscar-IDDevuelvePRODUCTO nombre cantidad)))
         (map vector carruseles cantidades))))


;(time (buscaProd-CARRUSELES-DevuelvePRODUCTO3 2 [0 1 2 3 4]))
;ok







; Fin de busqueda por ID que devuelve PRODUCTO--------------------------------------------------------------------------------------------
;--------------------------------------------------------------------------------------------









;-----------busca POR ID y devuelve CANTIDAD--------------------------------------------------------------------------------------------


; 1 Menos eficiente, usa funciones comunes, y solo busca en una base

(defn buscar-IDDevuelveCANTIDAD [nombre lista]
  (cond
    (empty? lista) false ; Si la lista está vacía, retorna false
    (= nombre (first (first lista))) (nth (first lista) 2) ; Si el nombre coincide, retorna la cantidad
    :else (buscar-IDDevuelveCANTIDAD nombre (rest lista))))

;(time (buscar-IDDevuelveCANTIDAD 2 base-cants))



; 2 Mas eficiente, usa PMAP con partition-all, y busca en varias bases

(defn buscaProd-CARRUSELES-DevuelveCANTIDAD3 [nombre carruseles]
  (let [particiones (partition-all 1 carruseles)
        cantidades (pmap leer-CANTIDADES (map first particiones))]
    (map (fn [[indice cantidad]]
           (str "Carr" (inc indice) ": " (buscar-IDDevuelveCANTIDAD nombre cantidad)))
         (map vector carruseles cantidades))))


;(time (buscaProd-CARRUSELES-DevuelveCANTIDAD3 2 [0 1 2 3 4]))
;ok





;-----------busca Producto y devuelve ID--------------------------------------------------------------------------------------------



(defn buscar-prodDevuelveID [nombre lista]
  (cond
    (empty? lista) false ; Si la lista está vacía, retorna false
    (= nombre (second (first lista))) (nth (first lista) 0) ; Si el nombre coincide, retorna la cantidad
    :else (buscar-prodDevuelveID nombre (rest lista))))

;(buscar-prodDevuelveID "Producto2" base-cants)


(defn buscaProd-DevuelveID3 [nombre carruseles]
  (let [particiones (partition-all 1 carruseles)
        cantidades (pmap leer-CANTIDADES (map first particiones))]
    (map (fn [[indice cantidad]]
           (str "Carr" (inc indice) ": " (buscar-prodDevuelveID nombre cantidad)))
         (map vector carruseles cantidades))))


;(time (buscaProd-DevuelveID3 "Producto2" [0 1 2 3 4]))
;ok





;-----------busca Producto, toma ID, busca Producto y devuelve Precio--------------------------------------------------------------------------------------------



(defn buscar-prodDevuelvePrecio [nombre listaProductos listaPrecios]
  (let [ID (buscar-prodDevuelveID nombre listaProductos)]
    (cond
      (empty? listaPrecios) false ; Si la lista está vacía, retorna false
      (= ID (first (first listaPrecios))) (nth (first listaPrecios) 2) ; Si el nombre coincide, retorna el precio
      :else (buscar-prodDevuelvePrecio nombre listaProductos (rest listaPrecios)))))

;(buscar-prodDevuelvePrecio "Producto2" base-cants base-precs)



(defn buscaProd-DevuelvePRECIO3 [nombre carruseles]
  (let [particiones (partition-all 1 carruseles)
        precios (pmap leer-PRECIOS (map first particiones))
        productos (pmap leer-CANTIDADES (map first particiones))]
    (map (fn [[indice producto precio]]
           (str "Carr" (inc indice) ": " (buscar-prodDevuelvePrecio nombre producto precio)))
         (map vector carruseles productos precios))))



;(time (buscaProd-DevuelvePRECIO3 "Producto2" [0 1 2 3 4]))


;(time (buscaProd-DevuelvePRECIO3 "Producto2" (range 4)))

;ok




;-----------busca POR ID y devuelve PRECIO--------------------------------------------------------------------------------------------


(defn buscar-IDDevuelvePRECIO [nombre lista]
  (cond
    (empty? lista) false ; Si la lista está vacía, retorna false
    (= nombre (first (first lista))) (nth (first lista) 2) ; Si el nombre coincide, retorna la cantidad
    :else (buscar-IDDevuelvePRECIO nombre (rest lista))))

;(buscar-IDDevuelvePRECIO 2 base-precs)



(defn buscar-IDDevuelvePRECIO3 [nombre carruseles]
  (let [particiones (partition-all 1 carruseles)
        precios (pmap leer-PRECIOS (map first particiones))]
    (map (fn [[indice precio]]
           (str "Carr" (inc indice) ": " (buscar-IDDevuelvePRECIO nombre precio)))
         (map vector carruseles precios))))

;(time (buscar-IDDevuelvePRECIO3 2 [0 1 2 3 4]))


