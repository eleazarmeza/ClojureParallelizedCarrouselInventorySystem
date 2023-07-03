
(ns carrusel-cloj.modificaciones
  (:require [carrusel-cloj.bases :as bases])
  (:require [carrusel-cloj.busquedas :as busquedas])
  (:require [carrusel-cloj.movimiento :as movimiento]))



;-------- actualizacantidad de PRODUCTO en BD --------------------------------------------------------------------------------------------

(defn nueva-cant [id nueva-cantidad matriz1]
  (map (fn [registro]
         (if (= (first registro) id)
           (list id (second registro) nueva-cantidad) ;(list id (second registro) nueva-cantidad (nth registro 2) (nth registro 3))
           registro))
       matriz1))


(defn actcant [id nueva-cantidad]
  (do
    (println "actualiza cantidad BD")
    (println)
    ;; Leer la matriz desde el archivo
    (let [listaw (clojure.java.io/reader "/Users/cristobal/cantidades.txt")
          w (read-string (slurp listaw))]
      (println "lista leida:")
      (println w)
      
      ;; Manda la bd y llama a la función de ajuste
      (let [matriz-actuaCant (nueva-cant id nueva-cantidad w)]
        ;; Guardar la matriz actualizada en el archivo
        (let [archivoq (clojure.java.io/writer "/Users/cristobal/cantidades.txt")]
          (println "guardar")
          (println)
          (println matriz-actuaCant)
          (spit archivoq (pr-str matriz-actuaCant))
          )))))


;(actcant 2 50)




;-------- actualizacantidad de PRECIO en BD --------------------------------------------------------------------------------------------

(defn nuevo-prec [id nuevo-precio matriz1]
  (map (fn [registro]
         (if (= (first registro) id)
           (list id (second registro) nuevo-precio) ;(list id (second registro) nueva-cantidad (nth registro 2) (nth registro 3))
           registro))
       matriz1))


(defn actprec [id nuevo-precio]
  (do
    (println "actualiza cantidad BD")
    (println)
    ;; Leer la matriz desde el archivo
    (let [listaw (clojure.java.io/reader "/Users/cristobal/precios.txt")
          w (read-string (slurp listaw))]
      (println "lista leida:")
      (println w)

      ;; Manda la bd y llama a la función de ajuste
      (let [matriz-actuaPrec (nuevo-prec id nuevo-precio w)]
        ;; Guardar la matriz actualizada en el archivo
        (let [archivoq (clojure.java.io/writer "/Users/cristobal/precios.txt")]
          (println "guardar")
          (println)
          (println matriz-actuaPrec)
          (spit archivoq (pr-str matriz-actuaPrec)))))))


;(actprec 2 50)



;-----------DEFINICIONES (me ayudan mas no se usan aqui directamente)--------------------------------------------------------------------------------------------
#_
(
;ok devuelve ultimaPosicion
(def ult-Pos-int (Integer/parseInt (bases/leer-U)))
(println ult-Pos-int)

;ok devuelve producto (si no utilizo su cola, en vez de def)
(busquedas/buscar-IDDevuelvePRODUCTO ult-Pos-int busquedas/base-cants)
(def producto (busquedas/buscar-IDDevuelvePRODUCTO ult-Pos-int busquedas/base-cants))

;ok devuelve cantidad
(busquedas/buscar-IDDevuelveCANTIDAD ult-Pos-int busquedas/base-cants)
(def cantExistente (busquedas/buscar-IDDevuelveCANTIDAD ult-Pos-int busquedas/base-cants))
(println cantExistente)
(def nueva-can (+ 10 cantExistente))
(println nueva-can)

;ok precioProd
(busquedas/buscar-IDDevuelvePRECIO ult-Pos-int busquedas/base-precs)
(def precioProd(busquedas/buscar-IDDevuelvePRECIO ult-Pos-int busquedas/base-precs))
(println precioProd)
)





;-----------Agrega ya (cantidad)--------------------------------------------------------------------------------------------


;(println (str ult-Pos-int " con precio: " precioProd))
;(def cantAjustada (atom 0))

(defn agregaya [cantidad]
  (let [ult-Pos-int (Integer/parseInt (bases/leer-U))
        base-cants1 (bases/leer-CANTIDADES)
        cantExistente (busquedas/buscar-IDDevuelveCANTIDAD ult-Pos-int base-cants1)
        producto (busquedas/buscar-IDDevuelvePRODUCTO ult-Pos-int base-cants1)
        base-precs1 (bases/leer-PRECIOS)
        precioProd (busquedas/buscar-IDDevuelvePRECIO ult-Pos-int base-precs1)
        cantAjustada (+ cantExistente cantidad)]

    (if (not cantExistente)
      (do
        (println "Producto no encontrado")
        (newline))
      (do
        (newline)
        (actcant ult-Pos-int cantAjustada)
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

;(agregaya 50)




;-----------Retira ya (cantidad)--------------------------------------------------------------------------------------------


;(def cantAjustada2 (atom 0))

(defn retiraya [cantidad]
  (let [ult-Pos-int (Integer/parseInt (bases/leer-U))
        base-cants1 (bases/leer-CANTIDADES)
        cantExistente (busquedas/buscar-IDDevuelveCANTIDAD ult-Pos-int base-cants1)
        producto (busquedas/buscar-IDDevuelvePRODUCTO ult-Pos-int base-cants1)
        base-precs1 (bases/leer-PRECIOS)
        precioProd (busquedas/buscar-IDDevuelvePRECIO ult-Pos-int base-precs1)
        cantAjustada (if (or (< cantExistente cantidad) (<= cantExistente 0))
                       cantExistente
                       (- cantExistente cantidad))]

    (if (or (< cantExistente cantidad) (<= cantExistente 0))
      (do
        (println "Tipo de cantExistente:" (type cantExistente))
        (println "No hay suficiente producto, solo hay: " cantExistente " unidades")
        (newline)
        (println "Revisa por favor."))
      (do
        (newline)
        (actcant ult-Pos-int cantAjustada)
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


;(retiraya 100)




;-----------AGREGA a un PRODUCTO especifico (cantidad)--------------------------------------------------------------------------------------------



(defn agregar [cantidad nombre]
  (let [ult-Pos-int (Integer/parseInt (bases/leer-U))
        base-cants1 (bases/leer-CANTIDADES)
        cantExistente (busquedas/buscar-prodDevuelveCant nombre base-cants1)
        idnum (busquedas/buscar-prodDevuelveID nombre base-cants1)
        base-precs1 (bases/leer-PRECIOS)
        precioProd (busquedas/buscar-IDDevuelvePRECIO ult-Pos-int base-precs1)
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
            (actcant idnum cantAjustada)
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
                (bases/guardar-U idnum)
                (newline)
                (println "Ahora el carrusel está en:")
                (println nombre)
                (newline)
                (newline)))))))))

;(agregar 10 "Producto2")


;(agregar 10 "Produ")




;-----------RETIRA de un PRODUCTO especifico (cantidad)--------------------------------------------------------------------------------------------


(defn retirar [cantidad nombre]
  (let [ult-Pos-int (Integer/parseInt (bases/leer-U))
        base-cants1 (bases/leer-CANTIDADES)
        idnum (busquedas/buscar-prodDevuelveID nombre base-cants1)
        producto-e (busquedas/buscar-IDDevuelvePRODUCTO idnum base-cants1)
        cantExistente (busquedas/buscar-IDDevuelveCANTIDAD idnum base-cants1)

        base-precs1 (bases/leer-PRECIOS)
        precioProd (busquedas/buscar-IDDevuelvePRECIO ult-Pos-int base-precs1)
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
          (actcant idnum cantAjustada)
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
              (bases/guardar-U idnum)
              (newline)
              (println "Ahora el carrusel está en:")
              (println nombre)
              (newline)
              (newline))))))))

;(retirar 1 "Producto2")

;(retirar 10 "Produ")
