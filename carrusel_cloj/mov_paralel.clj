

(ns carrusel-cloj.mov_paralel
  (:require [carrusel-cloj.carru_paralel :as basesp])
  (:require [carrusel-cloj.busq_paralel :as busquedasp])
  (:require [carrusel-cloj.movimiento :as movimiento]))







;-----------UP --------------------------------------------------------------------------------------------


(defn up [indice]
  (let [ultima-posicion (basesp/leer-u indice)
        fila (basesp/leer-f indice)
        n-columnas (basesp/leer-n indice)
        m-filas (basesp/leer-m indice)
        u-int (Integer/parseInt ultima-posicion)
        m-int (Integer/parseInt m-filas)
        n-int (Integer/parseInt n-columnas)
        f-int (Integer/parseInt fila)]
    (if (= f-int m-int)
      (do
        (let [calc-nueva-pos (+ n-int (- u-int (* m-int n-int)))
              nueva-posicion (str calc-nueva-pos)
              nueva-fila 1]
          (basesp/guardarBaseF nueva-fila indice)
          (basesp/guardarBaseU calc-nueva-pos indice)))
      (do
        (let [suma (+ u-int n-int)
              nueva-posicion (str suma)
              nueva-fila (+ 1 f-int)]
          (basesp/guardarBaseF nueva-fila indice)
          (basesp/guardarBaseU suma indice))))))

;(up 1)

;(- u-int (+ n-int (* m-int n-int))
;(+ 4 (- 21 (* 6 4)))



;----------- DOWN --------------------------------------------------------------------------------------------


(defn down [indice]
  (let [ultima-posicion (basesp/leer-u indice)
        fila (basesp/leer-f indice)
        n-columnas (basesp/leer-n indice)
        m-filas (basesp/leer-m indice)
        u-int (Integer/parseInt ultima-posicion)
        m-int (Integer/parseInt m-filas)
        n-int (Integer/parseInt n-columnas)
        f-int (Integer/parseInt fila)]
    (if (= f-int 1)
      (do
        (let [calc-nueva-pos (abs (- n-int (+ u-int (* m-int n-int))))
              nueva-posicion (str calc-nueva-pos)
              nueva-fila m-int]
          (basesp/guardarBaseF nueva-fila indice)
          (basesp/guardarBaseU calc-nueva-pos indice)))
      (do
        (let [suma (- u-int n-int)
              nueva-posicion (str suma)
              nueva-fila (- f-int 1)]
          (basesp/guardarBaseF nueva-fila indice)
          (basesp/guardarBaseU suma indice))))))

;(down 1)

;(- n-int (- u-int (* m-int n-int)))
;(abs (- 4 (+ 1  (* 6 4))))

;(abs (- n-int (+ u-int (* m-int n-int))))




;----------- LEFT --------------------------------------------------------------------------------------------



(defn left [indice]
  (let [ultima-posicion (basesp/leer-u indice)
        fila (basesp/leer-f indice)
        columna (basesp/leer-c indice) 
        n-columnas (basesp/leer-n indice)
        m-filas (basesp/leer-m indice)
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
          (basesp/guardarBaseC nueva-columna indice)
          (basesp/guardarBaseU suma indice))))))


;(left 1)





;----------- RIGHT --------------------------------------------------------------------------------------------



(defn right [indice]
  (let [ultima-posicion (basesp/leer-u indice)
        fila (basesp/leer-f indice)
        columna (basesp/leer-c indice) 
        n-columnas (basesp/leer-n indice)
        m-filas (basesp/leer-m indice)
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
          (basesp/guardarBaseC nueva-columna indice)
          (basesp/guardarBaseU suma indice))))))

;(right 0)



