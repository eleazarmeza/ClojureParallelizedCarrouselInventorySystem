(ns carrusel-cloj.modif_paralel
  (:require [carrusel-cloj.carru_paralel :as basesp])
  (:require [carrusel-cloj.busq_paralel :as busquedasp])
  (:require [carrusel-cloj.movimiento :as movimiento]))



;-------- actualizacantidad de PRODUCTO en BD --------------------------------------------------------------------------------------------

(defn nueva-cant [id nueva-cantidad matriz1]
  (map (fn [registro]
         (if (= (first registro) id)
           (list id (second registro) nueva-cantidad) ;(list id (second registro) nueva-cantidad (nth registro 2) (nth registro 3))
           registro))
       matriz1))


(defn actcant [id nueva-cantidad carrusel]
  (do
    
    (println "actualiza cantidad BD")
    (println)

    ;; Leer la matriz desde el archivo
    (let [carrusel-num (- carrusel 1)
          archivo (str "/Users/cristobal/cantidades" carrusel-num ".txt")
          matriz (read-string (slurp archivo))]
      (println "lista leida:")
      (println matriz)

      ;; Actualizar la cantidad en la matriz
      (let [matriz-actualizada (nueva-cant id nueva-cantidad matriz)]

        ;; Guardar la matriz actualizada en el archivo
        (let [archivo-actualizado (str "/Users/cristobal/cantidades" carrusel-num ".txt")]
          (println "guardar")
          (println)
          (println matriz-actualizada)
          (spit archivo-actualizado (pr-str matriz-actualizada)))))))


;ID  CANTIDAD  CARRUSEL
(actcant 2 50 1)












;-------- actualizacantidad de PRECIO en BD --------------------------------------------------------------------------------------------

(defn nuevo-prec [id nuevo-precio matriz1]
  (map (fn [registro]
         (if (= (first registro) id)
           (list id (second registro) nuevo-precio) ;(list id (second registro) nueva-cantidad (nth registro 2) (nth registro 3))
           registro))
       matriz1))


(defn actprec [id nuevo-precio carrusel]
  (do
    (println "actualiza cantidad BD")
    (println)
    ;; Leer la matriz desde el archivo
    (let [carrusel-num (- carrusel 1)
          archivo (str "/Users/cristobal/precios" carrusel-num ".txt")
          matriz (read-string (slurp archivo))]
      (println "lista leida:")
      (println matriz)

      ;; Manda la bd y llama a la función de ajuste
      (let [matriz-actuaPrec (nuevo-prec id nuevo-precio matriz)]
        ;; Guardar la matriz actualizada en el archivo
        (let [archivo-actualizado (str "/Users/cristobal/precios" carrusel-num ".txt")]
          (println "guardar")
          (println)
          (println matriz-actuaPrec)
          (spit archivo-actualizado (pr-str matriz-actuaPrec)))))))


;ID  PRECIO  CARRUSEL
(actprec 2 50 1)



;-----------DEFINICIONES (me ayudan mas no se usan aqui directamente)--------------------------------------------------------------------------------------------


;ok devuelve ultimaPosicion
(def ult-Pos-int (Integer/parseInt (basesp/leer-u 0)))
(println ult-Pos-int)

;ok devuelve producto (si no utilizo su cola, en vez de def)
(busquedasp/buscar-IDDevuelvePRODUCTO ult-Pos-int busquedasp/base-cants)
(def producto (busquedasp/buscar-IDDevuelvePRODUCTO ult-Pos-int busquedasp/base-cants))
(println producto)

;ok devuelve cantidad
(busquedasp/buscar-IDDevuelveCANTIDAD ult-Pos-int busquedasp/base-cants)
(def cantExistente (busquedasp/buscar-IDDevuelveCANTIDAD ult-Pos-int busquedasp/base-cants))
(println cantExistente)
(def nueva-can (+ 10 cantExistente))
(println nueva-can)

;ok precioProd
(busquedasp/buscar-IDDevuelvePRECIO ult-Pos-int busquedasp/base-precs)
(def precioProd (busquedasp/buscar-IDDevuelvePRECIO ult-Pos-int busquedasp/base-precs))
(println precioProd)






;-----------Agrega ya (cantidad)--------------------------------------------------------------------------------------------


;(println (str ult-Pos-int " con precio: " precioProd))
;(def cantAjustada (atom 0))

(defn agregaya [cantidad carrusel]
  (let [carrusel-num (- carrusel 1)
        ult-Pos-int (Integer/parseInt (basesp/leer-u carrusel-num)) 
        base-cants1 (basesp/leer-CANTIDADES carrusel-num) 
        producto (busquedasp/buscar-IDDevuelvePRODUCTO ult-Pos-int base-cants1)
        cantExistente (busquedasp/buscaProd-CARRUSEL-DevuelveCant producto carrusel-num) ;maybe ok
        base-precs1 (basesp/leer-PRECIOS carrusel-num)
        precioProd (busquedasp/buscar-IDDevuelvePRECIO ult-Pos-int base-precs1)
        cantAjustada (+ cantExistente cantidad)]

    (if (not cantExistente)
      (do
        (println "Producto no encontrado")
        (newline))
      (do
        (newline)
        (actcant ult-Pos-int cantAjustada carrusel)
        (newline)
        (println "Product ID:" (str ult-Pos-int " con precio: " precioProd))
        (newline)
        (if (= ult-Pos-int ult-Pos-int)
          (do
            (println "El carrusel está en: ")
            (println producto)
            (newline)
            (println "Cantidad: ")
            (println cantAjustada)))))))

(agregaya 50 1)




;-----------Retira ya (cantidad)--------------------------------------------------------------------------------------------


;(def cantAjustada2 (atom 0))

(defn retiraya [cantidad carrusel]
  (let [carrusel-num (- carrusel 1)
        ult-Pos-int (Integer/parseInt (basesp/leer-u carrusel-num)) 
        base-cants1 (basesp/leer-CANTIDADES carrusel-num) 
        producto (busquedasp/buscar-IDDevuelvePRODUCTO ult-Pos-int base-cants1)
        cantExistente (busquedasp/buscaProd-CARRUSEL-DevuelveCant producto carrusel-num) ;maybe ok
        base-precs1 (basesp/leer-PRECIOS carrusel-num)
        precioProd (busquedasp/buscar-IDDevuelvePRECIO ult-Pos-int base-precs1)
        cantAjustada (- cantExistente cantidad)]

    (if (or (< cantExistente cantidad) (<= cantExistente 0))
      (do
        (println "Tipo de cantExistente:" (type cantExistente))
        (println "No hay suficiente producto, solo hay: " cantExistente " unidades")
        (newline)
        (println "Revisa por favor."))
      (do
        (newline)
        (actcant ult-Pos-int cantAjustada carrusel)
        (newline)
        (println "Product ID:" (str ult-Pos-int " con precio: " precioProd))
        (newline)
        (if (= ult-Pos-int ult-Pos-int)
          (do
            (println "El carrusel está en: ")
            (println producto)
            (newline)
            (println "Cantidad: ")
            (println cantAjustada)))))))

(retiraya 100 1)




;-----------AGREGA a un PRODUCTO especifico (cantidad)--------------------------------------------------------------------------------------------




(defn agregar [cantidad nombre carrusel]
  (let [carrusel-num (- carrusel 1)
        ult-Pos-int (Integer/parseInt (basesp/leer-u carrusel-num))
        base-cants1 (basesp/leer-CANTIDADES carrusel-num)
        cantExistente (busquedasp/buscaProd-CARRUSEL-DevuelveCant nombre carrusel-num)
        idnum (busquedasp/buscar-prodDevuelveID nombre base-cants1)
        base-precs1 (basesp/leer-PRECIOS carrusel-num)
        precioProd (busquedasp/buscar-IDDevuelvePRECIO ult-Pos-int base-precs1)
        cantAjustada (+ cantExistente cantidad)]

    (if (not cantExistente)
      (do
        (println "Producto no encontrado")
        (newline))
      (do
        (if (< cantidad 0)
          (println "No es posible agregar eso")
          (do
            (println "Existen:" cantExistente)
            (newline)
            (actcant idnum cantAjustada carrusel)
            (newline)
            (println (str "La cantidad de " nombre " se ajustó a: " cantAjustada " ID num: " idnum " con precio " precioProd))
            (newline)
            (println "Determina si se mueve:")
            (newline)
            (if (= idnum ult-Pos-int)
              (println "No se movió")
              (do
                ;(println (moverBien baseCant ult-Pos-int idnum))
                ;(println "Se movió"))
                (newline)
                (basesp/guardarBaseU idnum carrusel)
                (newline)
                (println "Ahora el carrusel está en:")
                (println nombre)
                (newline)
                (newline)))))))))


(agregar 10 "Producto2" 1)

;(agregar 10 "Produ")




;-----------RETIRA de un PRODUCTO especifico (cantidad)--------------------------------------------------------------------------------------------




(defn retirar [cantidad nombre carrusel]
  (let [carrusel-num (- carrusel 1)
        ult-Pos-int (Integer/parseInt (basesp/leer-u carrusel-num))
        base-cants1 (basesp/leer-CANTIDADES carrusel-num)
        cantExistente (busquedasp/buscaProd-CARRUSEL-DevuelveCant nombre carrusel-num)
        idnum (busquedasp/buscar-prodDevuelveID nombre base-cants1)
        producto-e (busquedasp/buscar-IDDevuelvePRODUCTO idnum base-cants1)
        base-precs1 (basesp/leer-PRECIOS carrusel-num)
        precioProd (busquedasp/buscar-IDDevuelvePRECIO ult-Pos-int base-precs1)
        cantAjustada (- cantExistente cantidad)]

    (if (not producto-e)
      (do
        (newline)
        (println "Producto no encontrado")
        (newline))
      (if (< cantExistente cantidad)
        (do
          (newline)
          (println "No es posible retirar eso")
          (println "Existen:" cantExistente))
        (do
          (println "Existen:" cantExistente)
          (newline)
          (actcant idnum cantAjustada carrusel)
          (newline)
          (println (str "La cantidad de " nombre " se ajustó a: " cantAjustada " ID num: " idnum " con precio " precioProd))
          (newline)
          (println "Determina si se mueve:")
          (newline)
          (if (= idnum ult-Pos-int)
            (println "No se movió")
            (do
              (newline)
              ;(println (moverBien baseCant ult-Pos-int idnum))
              ;(println "Se movió"))
              (newline)
              (basesp/guardarBaseU idnum carrusel)
              (newline)
              (println "Ahora el carrusel está en:")
              (println nombre)
              (newline)
              (newline))))))))


(retirar 1 "Producto1" 1)

;(retirar 10 "Produ")
