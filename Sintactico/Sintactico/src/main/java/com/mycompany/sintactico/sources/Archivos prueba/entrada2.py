#declaracion y asignacion de variables
miVariable = "cadena de texto"
miVariable2 = 5
miId = 10
miDe2 = 20
arreglo1 = [20,2,3]
arreglo2 = [{"id":1, "nombre":"Sin nombre"}, {"id":2, "nombre":"Xalarga"}]
dicc = {}

# condicional simple
if True:
    # Bloque de código
    miVariable = "Verdadero"
else:
    miVariable = "Falso"

# condicional anidado
a = 5
v = 8
if a > v:
    # Bloque de código
    resultado = "a es mayor que v"
elif a < v:
    # Bloque de código
    resultado = "a es menor que v"
else:
    resultado = "a es igual a v"

# ciclos
for i in range(5):
    print(i)

contador = 0
while contador < 5:
    print(contador)
    contador += 1


#funciones
def suma(a, b):
    return a + b

resultado = suma(3, 5)


def funcion_compleja(a, b):
    if a > b:
        for i in range(b, a):
            print(i)
    else:
        while a < b:
            print(a)
            a += 1
    return a

resultado_final = funcion_compleja(3, 8)
