
;                                             inicia seccion de guardados de bases iniciales

(ns carrusel-cloj.bases
  (:require [clojure.java.io :as io])
  (:require [clojure.core.reducers :as reducers])

  )
  





;-------------------------------------------------------




;                GUARDADOS





;----------- guardar Base CANTIDAD y PRODUCTOS --------------------------------------------------------------------------------------------
;GENERA Y GUARDA-------


(defn guardarBaseCANTIDAD [base]
  (spit "/Users/cristobal/cantidades.txt"  (pr-str base))
  (let [ss (slurp "/Users/cristobal/cantidades.txt")]
    (println "lista leída:")
    (println ss)
    ss))

(defn generar-producto [id fila columna]
  [id (str "Producto" (str id)) 0])

(defn generar-matriz-PRODUCTOS [m n]
  (let [productos (map #(generar-producto % (quot (inc %) n) (rem (inc %) n)) (range 1 (inc (* m n))))]
    productos))

;(let [matriz (generar-matriz-PRODUCTOS 10 5)]
;  (guardarBaseCANTIDAD matriz))





;----------- guardar Base PRECIOS--------------------------------------------------------------------------------------------
;GENERA Y GUARDA-------


(defn guardarBasePRECIOS [base]
  (spit "/Users/cristobal/precios.txt" (pr-str base))
  (let [ss (slurp "/Users/cristobal/precios.txt")]
    (println "lista leída:")
    (println ss)
    ss))

(defn generar-precio [id fila columna]
  [id (str "Precio" (str id)) 0])

(defn generar-matriz-PRECIOS [m n]
  (let [precios (map #(generar-precio % (quot (inc %) n) (rem (inc %) n)) (range 1 (inc (* m n))))]
    precios))

;(let [matriz (generar-matriz-PRECIOS 10 5)]
 ; (guardarBasePRECIOS matriz))


;----------GUARDAR M     valorFilas -------------------------------------------------------------------------------------------


(defn guardar-M [base]
  (spit "/Users/cristobal/m.txt" (pr-str base))
  (let [ss (slurp "/Users/cristobal/m.txt")]
    (println "lista leída:")
    (println ss)
    ss))

(guardar-M 5)



;----------GUARDAR N     valorColumnas--------------------------------------------------------------------------------------------


(defn guardar-N [base]
  (spit "/Users/cristobal/n.txt"(pr-str base))
  (let [ss (slurp "/Users/cristobal/n.txt")]
    (println "lista leída:")
    (println ss)
    ss))




;----------GUARDAR   ULTIMA POSICION--------------------------------------------------------------------------------------------


(defn guardar-U [base]
  (spit "/Users/cristobal/u.txt" (pr-str base))
  (let [ss (slurp "/Users/cristobal/u.txt")]
    (println "lista leída:")
    (println ss)
    ss))

(guardar-U 1)



;----------GUARDAR    ULTIMA FILA--------------------------------------------------------------------------------------------


(defn guardar-F [base]
  (spit "/Users/cristobal/f.txt" (pr-str base))
  (let [ss (slurp "/Users/cristobal/f.txt")]
    (println "lista leída:")
    (println ss)
    ss))

(guardar-F 6)


;----------GUARDAR    ULTIMA COLUMNA--------------------------------------------------------------------------------------------


(defn guardar-C [base]
  (spit "/Users/cristobal/c.txt" (pr-str base))
  (let [ss (slurp "/Users/cristobal/c.txt")]
    (println "lista leída:")
    (println ss)
    ss))

;(guardar-C 6)








;-----------CREA CARRUSEL--------------------------------------------------------------------------------------------


(defn creacarru [m n]
  (do
    ;cantidades y productos
    (let [BDcant (generar-matriz-PRODUCTOS m n)]
      (guardarBaseCANTIDAD BDcant))
    ;precios
    (let [BDprec (generar-matriz-PRECIOS m n)]
      (guardarBasePRECIOS BDprec))

    ;guarda n, m, ultimaPos, ultimaFila, ultimaColumna
    (guardar-N n)
    (guardar-M m)
    (guardar-U 1)
    (guardar-F 1)
    (guardar-C 1)
    ))

; Ejemplo de creación de carrusel
(creacarru 25 4)









;-----------------------------------------------------------------




;                               LECTURAS





;-----------LEE M     valorFilas--------------------------------------------------------------------------------------------


(defn leer-m []
  (let [ss (slurp "/Users/cristobal/m.txt")]
    ;(println "lista leída:")
    ;(println ss)
    ss))

(def m (leer-m))
(println m)

(let [m-int (Integer/parseInt m)]
  (println (+ 100 m-int)))


;-----------LEE N      valorColumnas--------------------------------------------------------------------------------------------

(defn leer-n []
  (let [ss (slurp "/Users/cristobal/n.txt")]
    ss))

(def n (leer-n))
(println n)

(let [n-int (Integer/parseInt n)]
  (println (+ 100 n-int)))



    
;-----------LEE U      ultimaPosicion--------------------------------------------------------------------------------------------


(defn leer-U []
  (let [ultima-posicion (slurp "/Users/cristobal/u.txt")]
    ultima-posicion))

(def ultima-pos (leer-U))
(println (leer-U))




;-----------LEE F    ultimaFila--------------------------------------------------------------------------------------------


(defn leer-F []
  (let [ultima-fila (slurp "/Users/cristobal/f.txt")]
    ultima-fila))

(def ultima-pos (leer-F))
(println (leer-F))



;-----------LEE C    ultimaColumna--------------------------------------------------------------------------------------------


(defn leer-C []
  (let [ultima-columna (slurp "/Users/cristobal/c.txt")]
    ultima-columna))

(def ultima-pos (leer-C))
(println (leer-C))




;-----------lee base   PRECIOS--------------------------------------------------------------------------------------------

(defn leer-PRECIOS []
  (let [base-precios (slurp "/Users/cristobal/precios.txt")]
    (read-string base-precios)))

(def base-precios (leer-PRECIOS))
(println (leer-PRECIOS))




;-----------lee base  CANTIDADES--------------------------------------------------------------------------------------------

(defn leer-CANTIDADES []
  (let [base-cantidades (slurp "/Users/cristobal/cantidades.txt")]
    (read-string base-cantidades)))

(def base-cants (leer-CANTIDADES))
(println (leer-CANTIDADES))