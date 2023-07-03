
(ns carrusel-cloj.movimiento
  (:require [carrusel-cloj.bases :as bases]))






;-----------UP --------------------------------------------------------------------------------------------


(defn up []
  (let [ultima-posicion (bases/leer-U)
        fila (bases/leer-F)
        n-columnas (bases/leer-n)
        m-filas (bases/leer-m)
        u-int (Integer/parseInt ultima-posicion)
        m-int (Integer/parseInt m-filas)
        n-int (Integer/parseInt n-columnas)
        f-int (Integer/parseInt fila)]
    (if (= f-int m-int)
      (do
        (let [calc-nueva-pos (+ n-int (- u-int (* m-int n-int)))
              nueva-posicion (str calc-nueva-pos)
              nueva-fila 1]
          (bases/guardar-F nueva-fila)
          (bases/guardar-U calc-nueva-pos)))
      (do
        (let [suma (+ u-int n-int)
              nueva-posicion (str suma)
              nueva-fila (+ 1 f-int)]
          (bases/guardar-F nueva-fila)
          (bases/guardar-U suma))))))

;(up)

;(- u-int (+ n-int (* m-int n-int))
;(+ 4 (- 21 (* 6 4)))



;----------- DOWN --------------------------------------------------------------------------------------------


(defn down []
  (let [ultima-posicion (bases/leer-U)
        fila (bases/leer-F)
        n-columnas (bases/leer-n)
        m-filas (bases/leer-m)
        u-int (Integer/parseInt ultima-posicion)
        m-int (Integer/parseInt m-filas)
        n-int (Integer/parseInt n-columnas)
        f-int (Integer/parseInt fila)]
    (if (= f-int 1)
      (do
        (let [calc-nueva-pos (abs (- n-int (+ u-int (* m-int n-int))))
              nueva-posicion (str calc-nueva-pos)
              nueva-fila m-int]
          (bases/guardar-F nueva-fila)
          (bases/guardar-U calc-nueva-pos)))
      (do
        (let [suma (- u-int n-int)
              nueva-posicion (str suma)
              nueva-fila (- f-int 1)]
          (bases/guardar-F nueva-fila)
          (bases/guardar-U suma))))))

;(down)

;(- n-int (- u-int (* m-int n-int)))
;(abs (- 4 (+ 1  (* 6 4))))

;(abs (- n-int (+ u-int (* m-int n-int))))




;----------- LEFT --------------------------------------------------------------------------------------------



(defn left []
  (let [ultima-posicion (bases/leer-U)
        fila (bases/leer-F)
        columna (bases/leer-C)
        n-columnas (bases/leer-n)
        m-filas (bases/leer-m)
        u-int (Integer/parseInt ultima-posicion)
        m-int (Integer/parseInt m-filas)
        n-int (Integer/parseInt n-columnas)
        c-int (Integer/parseInt columna)
        f-int (Integer/parseInt fila)]
    (if (= c-int 1)
      (do
        (println "Estoy en la primer columna, no puedo avanzar más a la izquierda"))
      (do
        (let [suma (- u-int 1)
              nueva-posicion (str suma)
              nueva-columna (- c-int 1)]
          (println "Nueva posición: " nueva-posicion)
          (bases/guardar-C nueva-columna)
          (bases/guardar-U suma))))))


;(left)





;----------- RIGHT --------------------------------------------------------------------------------------------



(defn right []
  (let [ultima-posicion (bases/leer-U)
        fila (bases/leer-F)
        columna (bases/leer-C)
        n-columnas (bases/leer-n)
        m-filas (bases/leer-m)
        u-int (Integer/parseInt ultima-posicion)
        m-int (Integer/parseInt m-filas)
        n-int (Integer/parseInt n-columnas)
        c-int (Integer/parseInt columna)
        f-int (Integer/parseInt fila)]
    (if (= c-int n-int)
      (do
        (println "Estoy en la última columna, no puedo avanzar más a la derecha"))
      (do
        (let [suma (+ u-int 1)
              nueva-posicion (str suma)
              nueva-columna (+ c-int 1)]
          (println "Nueva posición: " nueva-posicion)
          (bases/guardar-C nueva-columna)
          (bases/guardar-U suma))))))

;(right)



