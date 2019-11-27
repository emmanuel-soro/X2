""" Para el desarrollo del procesamiento de imagenes, se opto utilizar la libreria OPENCV (desarrollada por intel)
    dado que se encuentra muy bien optimizada. Se obtuvieron tiempos de procesamiento mucho mas bajos que los algoritmos
    implementados con la libreria Image. 
    SW utilizado:
        -IDE Eclipse
        -Python 3.7.5
        -Libreria opencv 4.1.1"""

import cv2
import numpy as np
import sys

def procesar_imagen(ruta):
    
    #Separamos verde
    img = cv2.imread(ruta) 
    #Convertimos a hsv 
    hsv = cv2.cvtColor(img, cv2.COLOR_BGR2HSV)
    #El verde se encuentra entre (36,25,25) y (86, 255,255) aprox
    mask = cv2.inRange(hsv, (36, 25, 25), (86, 255,255))
    #Sacamos solo lo que no este dentro del rango verde 
    imask = mask>0 
    green = np.zeros_like(img, np.uint8) 
    green[imask] = img[imask] 
    
    #Reducimos ruido con el metodo de gauss
      
    img_sinRuido = cv2.GaussianBlur(green,(5,5),0)

    #Escalamos a grises y binarizamos
    
    umbral = 27.8
    umbralMax = 255
    gray = cv2.cvtColor(img_sinRuido, cv2.COLOR_BGRA2GRAY)
    t, binaria = cv2.threshold(gray, umbral, umbralMax, cv2.THRESH_BINARY)
    
    #Obtenemos follaje por la relacion pixesles blancos (verde) con negros

    cant_pix_blancos = cv2.countNonZero(binaria) #Obtengo la cantidad de pixeles blancos
    cant_pix_totales = binaria.size
    
    #Retornamos el % de follaje
    
    return (cant_pix_blancos * 100) / cant_pix_totales

# FUNCION MAIN

res = procesar_imagen(sys.argv[1])
print(res)

# FIN FUNCION MAIN