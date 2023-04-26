# TP Compilation
###### Antoine DEPOISIER & Jules FINCK

Notre TP est contenu dans le dossier I805_TP3_4.

Pour lancer le programme, il faut d'abord générer le cup et le jflex avec le gradle.
Suite à ça, vous pouvez exécuter le main et écrire du code à compiler.
Une fois le code entré, ajouter un point à la fin pour spécifier au compilateur la fin du code.

Vous pouvez ensuite appuyer sur la touche entrez pour générer l'arbre abstrait.

Voici un exemple d'arbre abstrait dans la console :

```
        a
    LET
        INPUT
PV
            b
        LET
            INPUT
    PV
                a
            <
                b
        IFTHENELSE
                    a
                OUTPUT
            THENELSE
                    b
                OUTPUT
```

Ensuite, vous devez appuyer sur les touches CTRL + D en même temps pour générer le code assembleur, en voici un exemple :

```
DATA SEGMENT
    a DD
    b DD
DATA ENDS
CODE SEGMENT
    in eax
    mov a, eax
    in eax
    mov b, eax
cond_if_1:
    mov eax, a
    push eax
    mov eax, b
    pop ebx
    sub eax,ebx
    jle else_if_1
    jmp then_if_1
then_if_1:
    mov eax, a
    out eax
    jmp sortie_if_1
else_if_1:
    mov eax, b
    out eax
    jmp sortie_if_1
sortie_if_1:
CODE ENDS
```

Suite à ça, vous pouvez prendre le code générer et le mettre dans le fichier `pgcd.asm`.

Après avoir mis le code dans le fichier, utilisez la commande : `java -jar vm-0.9.jar pgcd.asm` pour exécuter le code assembleur.

Pour l'avancement de ce TP, nous avons développé le while, le if avec un then et un else, le moins unaire, les opérateurs de comparaison `<` `=` `<=` et les opérateurs arithmétiques `+` `-` `/` `*` `mod`.

## Exemples d'utilisation

Voici 4 exemples d'utilisation de notre programme :

```
let prixHt = input;
let prixTtc =  prixHt * 119 / 100;
output prixTtc .
```

Ce bout de code nous calcule le prix TTC d'un produit dont on renseigne le prix.

```
let a = input;
let b = input;
while (0 < b)
do (let aux=(a mod b); let a=b; let b=aux );
output a .
```

Ce bout de code calcule le PGCD entre deux nombres donnés en paramètre.

```
let a = input;
let b = input;
if (a < b)
then (output a)
else (output b)
endif .
```

Ce bout de code permet de savoir quel est le plus petit nombre entre deux nombres entrés en paramètre.

```
let a = input;
if (a < 0)
then (let b = -a; output b)
endif .
```

Ce bout de code permet de rendre positif un nombre si le nombre entré en paramètre est négatif.