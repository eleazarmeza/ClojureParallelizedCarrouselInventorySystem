(ns carrusel-cloj.carru_paralel 
    (:require [clojure.java.io :as io])
    (:require [clojure.core.reducers :as reducers]))
  





;-----Guardar Cant (permite paralelizarla)----------------------------------------------



(defn generar-nombre-archivo-cantidades [indice]
  (str "/Users/cristobal/cantidades" indice ".txt"))

(defn guardarBaseCANTIDAD [base indice]
  (let [filename (generar-nombre-archivo-cantidades indice)]
    (spit filename (pr-str base))
    (let [ss (slurp filename)]
      (println "lista leída:")
      (println ss)
      ss)))

(defn generar-producto [id fila columna]
  [id (str "Producto" (str id)) 0])

(defn generar-matriz-PRODUCTOS [m n]
  (let [productos (map #(generar-producto % (quot (inc %) n) (rem (inc %) n)) (range 1 (inc (* m n))))]
    productos))

(defn guardar-productos-en-archivos [matriz num-archivos]
  (doseq [indice (range num-archivos)]
    (future (guardarBaseCANTIDAD matriz indice))))

(let [m 3
      n 2
      productos (generar-matriz-PRODUCTOS m n)
      num-archivos 5] ; Define el número de archivos que deseas generar
  (guardar-productos-en-archivos productos num-archivos))










;------Guardar Precios (permite paralelizarla)----------------------------------------------



(defn generar-nombre-archivo-precios [indice]
  (str "/Users/cristobal/precios" indice ".txt"))

(defn guardarBasePRECIOS [base indice]
  (let [filename (generar-nombre-archivo-precios indice)]
    (spit filename (pr-str base))
    (let [ss (slurp filename)]
      (println "lista leída:")
      (println ss)
      ss)))

(defn generar-precio [id fila columna]
  [id (str "Precio" (str id)) 0])

(defn generar-matriz-PRECIOS [m n]
  (let [productos (map #(generar-precio % (quot (inc %) n) (rem (inc %) n)) (range 1 (inc (* m n))))]
    productos))

(defn guardar-PRECIOS-en-archivos [matriz num-archivos]
  (doseq [indice (range num-archivos)]
    (future (guardarBasePRECIOS matriz indice))))

(let [m 3
      n 2
      precios (generar-matriz-PRECIOS m n)
      num-archivos 5] ; Define el número de archivos que deseas generar
  (guardar-PRECIOS-en-archivos precios num-archivos))








;------Guardar Comandos ---------------------------------------------





(defn generar-nombre-archivo-comandos [indice]
  (str "/Users/cristobal/comandos" indice ".txt"))

(defn guardarBaseCOMANDOS [base indice comandos]
  (let [comandos-con-indice (vec (map #(conj % (inc indice)) comandos))
        filename (generar-nombre-archivo-comandos indice)]
    (spit filename (pr-str comandos-con-indice))
    (let [ss (slurp filename)]
      (println "lista leída:")
      (println ss)
      ss)))

(defn guardar-COMANDOS-en-archivos [comandos num-archivos]
  (doseq [indice (range num-archivos)]
    (future (guardarBaseCOMANDOS comandos indice comandos))))





(guardar-COMANDOS-en-archivos comandos 5)



















;-----Guarda M con indices (sirve para paralelizarla)----------------------------------------------




(defn generar-indices-m [indice]
  (str "/Users/cristobal/m" indice ".txt"))

(defn guardarBaseM [base indice]
  (let [filename (generar-indices-m indice)]
    (spit filename (pr-str base))
    (let [ss (slurp filename)]
      (println "lista leída:")
      (println ss)
      ss)))

(defn guardar-M-en-archivos [m num-archivos]
  (doseq [indice (range num-archivos)]
    (future (guardarBaseM m indice))))


(guardar-M-en-archivos 5 5)








;-----Guarda N con indices (sirve para paralelizarla)----------------------------------------------



(defn generar-indices-n [indice]
  (str "/Users/cristobal/n" indice ".txt"))

(defn guardarBaseN [base indice]
  (let [filename (generar-indices-n indice)]
    (spit filename (pr-str base))
    (let [ss (slurp filename)]
      (println "lista leída:")
      (println ss)
      ss)))

(defn guardar-N-en-archivos [n num-archivos]
  (doseq [indice (range num-archivos)]
    (future (guardarBaseN n indice))))


;(guardar-N-en-archivos 2 5)










;--- Guardar -- NUM -- de carruseles ----------------------------------------------


(defn guardar-NC [base]
  (spit "/Users/cristobal/nc.txt" (pr-str base))
  (let [ss (slurp "/Users/cristobal/nc.txt")]
    (println "lista leída:")
    (println ss)
    ss))

;(guardar-NC 6)




;-----Guarda U con indices (sirve para paralelizarla)----------------------------------------------



(defn generar-indices-U [indice]
  (str "/Users/cristobal/u" indice ".txt"))

(defn guardarBaseU [ult-pos indice]
  (let [filename (generar-indices-U indice)]
    (spit filename (pr-str ult-pos))
    (let [ss (slurp filename)]
      (println "lista leída:")
      (println ss)
      ss)))

(defn guardar-U-en-archivos [u-pos num-archivos]
  (doseq [indice (range num-archivos)]
    (future (guardarBaseU u-pos indice))))


;(guardar-U-en-archivos 1 5)




;-----Guarda C de ultimaCOLUMNA VISITADA con indices (sirve para paralelizarla)----------------------------------------------



(defn generar-indices-C [indice]
  (str "/Users/cristobal/c" indice ".txt"))

(defn guardarBaseC [base indice]
  (let [filename (generar-indices-C indice)]
    (spit filename (pr-str base))
    (let [ss (slurp filename)]
      (println "lista leída:")
      (println ss)
      ss)))

(defn guardar-C-en-archivos [c num-archivos]
  (doseq [indice (range num-archivos)]
    (future (guardarBaseC c indice))))


;(guardar-C-en-archivos 1 5)




;-----Guarda F de ultimaFILA VISITADA con indices (sirve para paralelizarla)----------------------------------------------



(defn generar-indices-F [indice]
  (str "/Users/cristobal/f" indice ".txt"))

(defn guardarBaseF [num-fila indice]
  (let [filename (generar-indices-F indice)]
    (spit filename (pr-str num-fila))
    (let [ss (slurp filename)]
      (println "lista leída:")
      (println ss)
      ss)))

(defn guardar-F-en-archivos [fila num-archivos]
  (doseq [indice (range num-archivos)]
    (future (guardarBaseF fila indice))))


;(guardar-F-en-archivos 1 5)



;-----Crea Carruseles de manera paralelizada----------------------------------------------

(defn crearrus-p [m n num-archivos comandos]
  (let [productos (generar-matriz-PRODUCTOS m n)
        precios (generar-matriz-PRECIOS m n)
        num-archivos (+ num-archivos 1)
        acciones #(do
                    (guardar-productos-en-archivos productos %)
                    (guardar-PRECIOS-en-archivos precios %)
                    (guardar-M-en-archivos m %)
                    (guardar-N-en-archivos n %)
                    (guardar-U-en-archivos 1 %)
                    (guardar-C-en-archivos 1 %)
                    (guardar-F-en-archivos 1 %))]
    (let [archivos (partition-all num-archivos (range num-archivos))]
      (pmap #(doseq [indice %] (acciones indice)) archivos)))
  (guardar-NC num-archivos)
  (guardar-COMANDOS-en-archivos comandos num-archivos)
  )






(def comandos ['(agregayap 100)
               '(retirayap 50)])

(crearrus-p 3 2 5 comandos)
;(crearrus-p 3 2 5)


  (guardar-COMANDOS-en-archivos comandos 5)




;-----------------------------------------------------------------




;                               LECTURAS





;-----------LEE M     valorFilas--------------------------------------------------------------------------------------------


(defn leer-m [indice]
  (let [filename (str "/Users/cristobal/m" indice ".txt")
        ss (slurp filename)]
    ;(println "lista leída:")
    ;(println ss)
    ss))

(def m (leer-m 0)) ; Leer el archivo m0.txt
;(println m)

(let [m-int (Integer/parseInt m)]
  (println (+ 100 m-int)))



;-----------LEE N      valorColumnas--------------------------------------------------------------------------------------------

(defn leer-n [indice]
  (let [filename (str "/Users/cristobal/n" indice ".txt")
        ss (slurp filename)]
    ;(println "lista leída:")
    ;(println ss)
    ss))

(def n (leer-n 0)) ; Leer el archivo m0.txt
;(println n)

(let [n-int (Integer/parseInt n)]
  (println (+ 100 n-int)))





;-----------LEE U      ultimaPosicion--------------------------------------------------------------------------------------------


(defn leer-u [indice]
  (let [filename (str "/Users/cristobal/u" indice ".txt")
        ss (slurp filename)]
    ;(println "lista leída:")
    ;(println ss)
    ss))

(def u (leer-u 0)) ; Leer el archivo m0.txt
;(println u)




;-----------LEE F    ultimaFila--------------------------------------------------------------------------------------------


(defn leer-f [indice]
  (let [filename (str "/Users/cristobal/f" indice ".txt")
        ss (slurp filename)]
    ;(println "lista leída:")
    ;(println ss)
    ss))


(def f (leer-f 0)) ; Leer el archivo m0.txt
;(println f)



;-----------LEE C    ultimaColumna--------------------------------------------------------------------------------------------


(defn leer-c [indice]
  (let [filename (str "/Users/cristobal/c" indice ".txt")
        ss (slurp filename)]
    ;(println "lista leída:")
    ;(println ss)
    ss))

(def c (leer-c 0)) ; Leer el archivo m0.txt
;(println c)




;-----------lee NUM de CARRUSELES--------------------------------------------------------------------------------------------

(defn leer-NC []
  (let [ss (slurp "/Users/cristobal/nc.txt")]
    ;(println "lista leída:")
    ;(println ss)
    ss))

(def nc (leer-NC))
(println nc)

(let [nc-int (Integer/parseInt nc)]
  (println (+ 100 nc-int)))






;-----------lee base   PRECIOS--------------------------------------------------------------------------------------------

(defn leer-PRECIOS [indice]
  (let [filename (str "/Users/cristobal/precios" indice ".txt")
        base-precios (slurp filename)]
    (read-string base-precios)))

(def base-precios (leer-PRECIOS 0))
;(println base-precios)





;-----------lee base  CANTIDADES--------------------------------------------------------------------------------------------



(defn leer-CANTIDADES [indice]
  (let [filename (str "/Users/cristobal/cantidades" indice ".txt")
        base-cants (slurp filename)]
    (read-string base-cants)))

(def base-cants (leer-CANTIDADES 0))
;(println base-cants)