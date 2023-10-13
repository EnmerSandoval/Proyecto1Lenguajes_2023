variable1 = 100
texto = "Hola Mundo"
booleano = True
lista_vacia = []
diccionario = {"clave": "valor"}

# simples
if variable1 > 50:
    resultado = "mayor"
else:
    resultado = "menor"

if texto == "Hola Mundo":
    saludo = True
else:
    saludo = False

if booleano:
    estado = "Activo"
else:
    estado = "Inactivo"

if not lista_vacia:
    lista_estado = "Lista está vacía"
else:
    lista_estado = "Lista no está vacía"

if "clave" in diccionario:
    clave_estado = "Clave existe"
else:
    clave_estado = "Clave no existe"



# anidados 
x = 15
y = 20
if x > y:
    comparacion = "x es mayor que y"
elif x < y:
    comparacion = "x es menor que y"
else:
    comparacion = "x es igual a y"

#ciclos
for i in range(10, 15):
    print(f"Número {i}")

contador = 10
while contador < 15:
    print(f"Contador {contador}")
    contador += 1

# funciones
def multiplicar(a, b):
    return a * b

def dividir(a, b):
    if b != 0:
        return a / b
    else:
        return "No se puede dividir entre cero"

def longitud_texto(texto):
    return len(texto)

def es_par(numero):
    return numero % 2 == 0

def convertir_a_lista(*args):
    for arg in args:
        print(arg)
    return list(args)
