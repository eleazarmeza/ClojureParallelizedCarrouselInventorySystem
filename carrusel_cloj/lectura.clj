(ns carrusel-cloj.lectura
    (:require [carrusel-cloj.bases :as bases])
  (:require [carrusel-cloj.movimiento :as movimiento]) 
  (:require [carrusel-cloj.modificaciones :as modificaciones])
  (:require [carrusel-cloj.busquedas :as busquedas]) 
  (:require [carrusel-cloj.inventarios :as inventarios])
  (:require [carrusel-cloj.lectura :as lectura])
  (:require [carrusel-cloj.busq_paralel :as busquedasp])
  (:require [carrusel-cloj.carru_paralel :as basesp])
  (:require [carrusel-cloj.inventari_paral :as inventariosp])
  (:require [carrusel-cloj.modif_paralel :as modificacionesp])
  (:require [carrusel-cloj.mov_paralel :as movimientop]) 
  )



;------Definiciones de funciones-------------------------------------------------------------------------------------------------------------------
(def creacarru bases/creacarru)
(def up movimiento/up)
(def down movimiento/down)
(def left movimiento/left)
(def right movimiento/right)
(def retiraya modificaciones/retiraya)
(def agregaya modificaciones/agregaya)
(def agregar modificaciones/agregar)
(def retirar modificaciones/retirar)
(def calcular-valor-inventario2 inventarios/calcular-valor-inventario2)
(def imprimir-productos-bajos inventarios/imprimir-productos-bajos)
(def actprec modificaciones/actprec)

;----------Definiciones de comandos de VARIOS CARRUSELES-------------------------------------------------------------------------------------------------------------------

(def creacarrus-p basesp/crearrus-p)
(def upp movimientop/up)
(def downp movimientop/down)
(def leftp movimientop/left)
(def rightp movimientop/right)
(def retirayap modificacionesp/retiraya)
(def agregayap modificacionesp/agregaya)
(def agregarp modificacionesp/agregar)
(def retirarp modificacionesp/retirar)
(def valor-inventario-por-carrusp inventariosp/calcular-valor-inventario3)
(def valor-total-todos-carrusp inventariosp/valor-total-todos2)
(def productos-bajos-todos-carrusp inventariosp/productos-bajos4)
(def actprecp modificacionesp/actprec)
(def busca-producto-determinados-carrus-devuelve-cantidades busquedasp/buscaProd-CARRUSELES-DevuelveCant3)
(def busca-producto-determinados-carrus-devuelve-productos-en-carrus busquedasp/buscaProd-CARRUSELES-DevuelvePRODUCTO3)
(def busca-producto-determinados-carrus-devuelve-cantidad-cadauno busquedasp/buscaProd-CARRUSELES-DevuelveCANTIDAD3)
(def busca-producto-determinados-carrus-devuelve-ids-porcarru busquedasp/buscaProd-DevuelveID3)
(def busca-producto-determinados-carrus-devuelve-precio-porcarru busquedasp/buscaProd-DevuelvePRECIO3)
(def busca-producto-porID-determinados-carrus-devuelve-precio-porcarru busquedasp/buscar-IDDevuelvePRECIO3)





(def comandos ['(creacarru 3 4)
               '(up)
               '(down)
               '(left)
               '(right)
               '(retiraya 50)
               '(agregaya 50)
               '(agregar 10 "Producto2")
               '(retirar 1 "Producto1")
               '(calcular-valor-inventario2)
               '(imprimir-productos-bajos)
               '(actprec 2 50)])


(doseq [comando comandos]
  (eval comando))


(defn guardarCOMANDOS [base]
  (spit "/Users/cristobal/comandos.txt"  (pr-str base))
  (let [ss (slurp "/Users/cristobal/comandos.txt")]
    (println "lista leída:")
    (println ss)
    ss))

(guardarCOMANDOS comandos)



;----- Ejecutar comandos desde archivo-------------------------------------------------------------------------------------------------------------------

(defn ejecutar-comandos-desde-archivo [ruta-archivo]
  (let [comandos-texto (slurp ruta-archivo)
        comandos (read-string comandos-texto)]
    (doseq [comando comandos]
      (eval comando))))

(ejecutar-comandos-desde-archivo "/Users/cristobal/comandos.txt")








;----- Ejecutar comandos desde archivo PARALELIZADO-------------------------------------------------------------------------------------------------------------------

(defn leer-NC []
  (let [ss (slurp "/Users/cristobal/nc.txt")]
    ss))

(defn ejecutar-comandos-desde-archivos [ruta-archivo-base]
  (let [nc (Integer/parseInt (leer-NC))
        archivos (map #(str ruta-archivo-base % ".txt") (range nc))
        comandos (->> archivos
                      (pmap #(->> % slurp read-string))
                      (apply concat))]
    (doseq [comando comandos]
      (eval comando))))




(ejecutar-comandos-desde-archivos "/Users/cristobal/comandos")
