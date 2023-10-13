# expresiones
cadena = "Hola Mundo" # 1
number = 100 # 2
boolean = True # 3

# operadores aritmeticos
suma = 10 + 10 # 4
resta = 10 - 10 # 5
multiplicacion = 10 * 10 # 6
division = 10 / 10 # 7
exponente = 10 ** 10 # 8
modulo = 10 % 10 # 9

# operadores de asignacion
number += 10 # 10
number -= 10 # 11
number *= 10 # 12
number /= 10 # 13
number **= 10 # 14
number %= 10 # 15

# operadores de comparacion
resultado = 10 == 10 # 16
resultado = 10 != 10 # 17
resultado = 10 > 10 # 18
resultado = 10 >= 10 # 19
resultado = 10 < 10 # 20
resultado = 10 <= 10 # 21

# operadores logicos
resultado = 10 == 10 and 10 != 10 # 22
resultado = 10 == 10 or 10 != 10 # 23
resultado = not 10 == 10 # 24

# operadores de identidad
resultado = 10 is 10 # 25
resultado = 10 is not 10 # 26

# operadores de pertenencia
resultado = 10 in [10, 20, 30] # 27
resultado = 10 not in [10, 20, 30] # 28

# operadores de bits
resultado = 10 & 10 # 29
resultado = 10 | 10 # 30
resultado = 10 ^ 10 # 31
resultado = 10 << 10 # 32
resultado = 10 >> 10 # 33

# operadores ternarios
resultado = "Hola" if 10 == 10 else "Mundo" # 34

# operadores de entrada y salida
print("Hola Mundo") # 35
print("Hola", "Mundo") # 36
print("Hola" + "Mundo") # 37

# condiciones
if 10 == 10: # 38 -if-59
    print("if") # 1
elif 10 != 10: # 39 -elif-61
    print("elif") # 1
else: # 40 -else-63
    print("else") # 1


# bucles
while 10 == 10: # 41 -while-68
    print("while") # 1
    if resultado: # 2 -if-70
        print("if dentro del while")  # 1
        mi_variable = 10 # 2
        arreglo = [10, 20, 30] # 3
        diccionario = {"nombre": "Adrian"} # 4
    break # 3
else: # 42 -else-76
    print("else") # 1

for i in range(10): # 43 -for-79
    print("for") # 1
else: # 44 -else-81
    print("else")   # 1 


print(cadena) # 45

# instruccione globales 45
# if's  2
# elif's 1
# else's 3
# while's 1
# for's 1
# break's 1
# print's 3
# operaciones 33


def mi_funcion(): # 46
    print("Hola Mundo") # 1
    return "Hola Mundo" # 2

mi_funcion() # 47

# funciones
def fibonacci(n): # 48
    if n == 0 or n == 1: # 3 -if-106
        return 1 # 4
    return fibonacci(n - 1) + fibonacci(n - 2) # 5

def factorial(n): # 49
    if n == 0: # 6 -if-110
        return 1 # 7
    return n * factorial(n - 1) # 8

def imprimir_arreglo(arreglo): # 50
    for i in arreglo: # 9 -for-114
        print(i) # 1

def imprimir_diccionario(diccionario): # 51
    for key, value in diccionario.items(): # 10 -for-118
        print(key, value) # 2

def imprimir_tupla(tupla): # 52
    for i in tupla: # 11 -for-122
        print(i) # 1

def sumar(a, b): # 53
    return a + b # 9

def restar(a, b): # 54
    return a - b # 10

def multiplicar(a, b): # 55
    return a * b # 11

def dividir(a, b): # 56
    return a / b # 12

def potencia(a, b): # 57
    return a ** b # 13

def modulo(a, b): # 58
    return a % b # 14

# funciones 13

# llamadas a funciones
mi_funcion() # 59
fibonacci(10) # 60
factorial(10) # 61
imprimir_arreglo([10, 20, 30]) # 62
imprimir_diccionario({"nombre": "Adrian"}) # 63
imprimir_tupla((10, 20, 30)) # 64
sumar(10, 10) # 65
restar(10, 10) # 66
multiplicar(10, 10) # 67
dividir(10, 10) # 68
potencia(10, 10) # 69
modulo(10, 10) # 70

# llamadas a funciones 11

# imprimir funciones con retorno
print(mi_funcion()) # 71
print(fibonacci(10)) # 72
print(factorial(10)) # 73
print(sumar(10, 10)) # 74
print(restar(10, 10)) # 75
print(multiplicar(10, 10)) # 76
print(dividir(10, 10)) # 77
print(potencia(10, 10)) # 78
print(modulo(10, 10)) # 79


# imprimir funciones con retorno 9

# instrucciones globales 79

# operaciones aritmeticas combinadas
print(10 + 10 * 10) # 80
print(suma + 10 * resta / multiplicacion ** division % exponente) # 81

n1 = 10 # 82
n2 = 20 # 83
n3 = 30 # 84
n4 = 40 # 85
n5 = 50 # 86

result = n1 + n2 + n3 + n4 + n5 # result = 150
print (result) # 87

result = n1 - n2 - n3 - n4 - n5 # result = -130
print (result) # 88

result = n1 * n2 * n3 * n4 * n5 # result = 12000000
print (result) # 89

result = n1 / n2 / n3 / n4 / n5 # result = 1.25e-05

result = n3*(n1*n2+n3-n4 )/(n5 - n1*n2) # result = 30.0
print (result) # 90

