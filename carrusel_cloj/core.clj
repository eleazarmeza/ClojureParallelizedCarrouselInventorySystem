
(ns carrusel-cloj.core
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


(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))


;-------------------------------------------------------------------------------------------------------------------

;                                    RETO 2.2 
;                                 PARALELIZACION

;                          Cristóbal Eleazar Meza Aranda
;                                   A01661792


; 1) Definiciones de comandos


; 2) Comandos del RETO ANTERIOR (scheme a un solo carrusel)


; 3) Comandos del RETO ACTUAL (paralelización)


; 4) GUARDADO de ARCHIVO de comandos 


; 5) EJECUCION de ARCHIVO de comandos



;----------Definiciones de comandos de UN SOLO CARRUSEL-------------------------------------------------------------------------------------------------------------------

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
(def ejecutar-comandos-desde-archivos lectura/ejecutar-comandos-desde-archivos)


;----------Definicion para 1 o varios carruseles-------------------------------------------------------------------------------------------------------------------

(def guardarCOMANDOS lectura/guardarCOMANDOS)
(def ejecutar-comandos-desde-archivo lectura/ejecutar-comandos-desde-archivo)


;-------------------------------------------------------------------------------------------------------------------




;                  Comandos disponibles de UN SOLO CARRUSEL (reto Scheme)    


; creacarru m n
(creacarru 6 4)   


;comando cantidad
(retiraya 50)
(agregaya 50)

;comando cantidad producto
(agregar 10 "Producto2")
(retirar 1 "Producto1")

;comando idProd precio
(actprec 2 80)

;comando
(up)
(down)
(left)
(right)
(calcular-valor-inventario2)
(imprimir-productos-bajos)


;-------------------------------------------------------------------------------------------------------------------

;                          RETO ACTUAL DE PARALELIZACION


;                      Comandos disponibles de VARIOS CARRUSELES 



; creacarrus-p m n CantCarruseles
(creacarrus-p 3 2 5) 

; comando Carrusel
(upp 1)
(downp 1)
(leftp 1)
(rightp 1)

; comando cantidad Carrusel
(agregayap 50 1)
(retirayap 50 1)


; comando cantidad producto Carrusel
(agregarp 10 "Producto2" 2)  ;maybe la det de mov
(retirarp 1 "Producto1" 1)  ;maybe la det de mov

; comando
(valor-inventario-por-carrusp)
(valor-total-todos-carrusp)
(productos-bajos-todos-carrusp)
 
;comando ID PRECIO CARRUSEL
(actprecp 2 50 2)
 

; -------------- Funciones especiales ----------------------------------------------


; Con nombre de producto indica en que carruseles hay existencias y cuantas
;     comando           nombreProducto    [Carrusel1 Carrusel2 Carrusel3 ...]
; o
;     comando           nombreProducto    (range 0 cantCarruseles)
(busca-producto-determinados-carrus-devuelve-cantidades "Producto2" [0 1 2 3 4])



; Con ID de producto indica en que carruseles hay existencias y cuantas
;     comando           nombreProducto    [Carrusel1 Carrusel2 Carrusel3 ...]
; o
;     comando           nombreProducto    (range 0 cantCarruseles)
(busca-producto-determinados-carrus-devuelve-productos-en-carrus 2 [0 1 2 3 4])



; Con  ID de producto indica en que carruseles hay existencias y cuantas
;     comando           nombreProducto    [Carrusel1 Carrusel2 Carrusel3 ...]
; o
;     comando           nombreProducto    (range 0 cantCarruseles)
(busca-producto-determinados-carrus-devuelve-cantidad-cadauno 2 [0 1 2 3 4])



; Con  nombre de producto indica ID de producto por carrusel
;     comando           nombreProducto    [Carrusel1 Carrusel2 Carrusel3 ...]
; o
;     comando           nombreProducto    (range 0 cantCarruseles)
(time (busca-producto-determinados-carrus-devuelve-ids-porcarru  "Producto2" [0 1 2 3 4]))
(time (busca-producto-determinados-carrus-devuelve-ids-porcarru  "Producto2" (range 4)))



; Con  nombre de producto devuelve precio por carrusel
;     comando           nombreProducto    [Carrusel1 Carrusel2 Carrusel3 ...]
; o
;     comando           nombreProducto    (range 0 cantCarruseles)
(time (busca-producto-determinados-carrus-devuelve-precio-porcarru "Producto2" [0 1 2 3 4]))
(time (busca-producto-determinados-carrus-devuelve-precio-porcarru "Producto2" (range 2 4)))

  
; Con  ID de producto devuelve precio por carrusel
;     comando           nombreProducto    [Carrusel1 Carrusel2 Carrusel3 ...]
; o
;     comando           nombreProducto    (range 0 cantCarruseles)
(time (busca-producto-porID-determinados-carrus-devuelve-precio-porcarru 2 [0 1 2 3 4]))
(time (busca-producto-porID-determinados-carrus-devuelve-precio-porcarru 2 (range 2 4)))

;----------Guardar comandos en archivo-------------------------------------------------------------------------------------------------------------------

; Para guardar los comandos en un archivo, 
; solo necesitas modificar el archivo a guardar,
; desde aquí o abriendolo desde tu editor de texto favorito.

#_
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
               '(actprec 2 50)
               ])



(def comandos [
               '(upp 1)
               '(downp 1)
               '(rightp 1)
               '(leftp 1)
               '(agregayap 50 1)
               '(retirayap 50 1)
               '(agregarp 10 "Producto2" 1)
               '(retirarp 1 "Producto2" 1)
               '(valor-inventario-por-carrusp)
               '(valor-total-todos-carrusp)
               '(productos-bajos-todos-carrusp)
               '(actprecp 2 50 1)
               '(busca-producto-determinados-carrus-devuelve-cantidades "Producto2" [0 1 2 3 4])
               '(busca-producto-determinados-carrus-devuelve-productos-en-carrus 2 [0 1 2 3 4])
               '(busca-producto-determinados-carrus-devuelve-cantidad-cadauno 2 [0 1 2 3 4])
               '(busca-producto-determinados-carrus-devuelve-ids-porcarru  "Producto2" (range 4))
               '(busca-producto-determinados-carrus-devuelve-precio-porcarru "Producto2" (range 2 4))
               ])

(guardarCOMANDOS comandos)





;----------Ejecutar comandos desde archivo-------------------------------------------------------------------------------------------------------------------



; Para iniciar, 
; solo presiona   Option + Enter  en la siguiente línea:



(ejecutar-comandos-desde-archivo "/Users/cristobal/comandos.txt")




;----------Ejecutar comandos desde archivos-------------------------------------------------------------------------------------------------------------------


;        ****** PARALELIZADA *****


; Para iniciar, 
; solo presiona   Option + Enter  en la siguiente línea:



(ejecutar-comandos-desde-archivos "/Users/cristobal/comandos")

;----------Fin-------------------------------------------------------------------------------------------------------------------
;  :)

