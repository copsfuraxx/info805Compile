DATA SEGMENT
	a DD
	b DD
DATA ENDS
CODE SEGMENT
	in eax
	mov a, eax
cond_if_1:
	mov eax, a
	push eax
	mov eax, 0
	pop ebx
	sub eax,ebx
	jle sortie_if_1
	jmp then_if_1
then_if_1:
	mov eax, a
	push eax
	mov eax, 0
	pop ebx
	sub eax, ebx
	mov b, eax
	mov eax, b
	out eax
	jmp sortie_if_1
sortie_if_1:
CODE ENDS